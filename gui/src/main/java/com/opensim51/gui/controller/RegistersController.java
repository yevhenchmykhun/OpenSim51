package com.opensim51.gui.controller;

import com.opensim51.gui.util.IntegerUtil;
import com.opensim51.simulator.Simulator;
import com.opensim51.simulator.memory.InternalData;
import com.opensim51.simulator.memory.Memory;
import com.opensim51.simulator.memory.datatype.UInt16;
import com.opensim51.simulator.memory.datatype.UInt8;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.fxml.FXML;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.TextFieldTreeTableCell;
import org.apache.commons.lang3.StringUtils;

public class RegistersController implements Updatable, MainWindowDependant {

    private static final String TREE_ITEM_NAME_PC = "pc";

    private static final String TREE_ITEM_NAME_DPTR = "dptr";

    @FXML
    private TreeTableView<TreeTableViewEntry> registersView;

    @FXML
    private TreeTableColumn<TreeTableViewEntry, String> registerColumn;

    @FXML
    private TreeTableColumn<TreeTableViewEntry, String> valueColumn;

    private final Simulator simulator = Simulator.getInstance();

    private MainWindow mainWindow;

    @FXML
    public void initialize() {
        registersView.setShowRoot(false);

        registerColumn.setCellValueFactory(
                (TreeTableColumn.CellDataFeatures<TreeTableViewEntry, String> param) ->
                        new ReadOnlyStringWrapper(param.getValue().getValue().getName())
        );

        valueColumn.setCellValueFactory(
                param -> new ReadOnlyStringWrapper(param.getValue().getValue().getValue()));
        valueColumn.setCellFactory(TextFieldTreeTableCell.forTreeTableColumn());
        valueColumn.setOnEditCommit(event -> {
            TreeTableViewEntry entry = event.getRowValue().getValue();
            entry.setValue(event.getNewValue());

            mainWindow.updateUserInterface();
        });

        InternalData id = simulator.getInternalData();

        TreeItem<TreeTableViewEntry> root = new TreeItem<>();

        TreeItem<TreeTableViewEntry> regs = new TreeItem<>(new TreeTableViewEntry("Regs", null));
        regs.getChildren().add(new TreeItem<>(new TreeTableViewEntry("r0", id.R0)));
        regs.getChildren().add(new TreeItem<>(new TreeTableViewEntry("r1", id.R1)));
        regs.getChildren().add(new TreeItem<>(new TreeTableViewEntry("r2", id.R2)));
        regs.getChildren().add(new TreeItem<>(new TreeTableViewEntry("r3", id.R3)));
        regs.getChildren().add(new TreeItem<>(new TreeTableViewEntry("r4", id.R4)));
        regs.getChildren().add(new TreeItem<>(new TreeTableViewEntry("r5", id.R5)));
        regs.getChildren().add(new TreeItem<>(new TreeTableViewEntry("r6", id.R6)));
        regs.getChildren().add(new TreeItem<>(new TreeTableViewEntry("r7", id.R7)));
        regs.setExpanded(true);

        TreeItem<TreeTableViewEntry> sys = new TreeItem<>(new TreeTableViewEntry("Sys", null));
        sys.getChildren().add(new TreeItem<>(new TreeTableViewEntry("a", id.ACC)));
        sys.getChildren().add(new TreeItem<>(new TreeTableViewEntry("b", id.B)));
        sys.getChildren().add(new TreeItem<>(new TreeTableViewEntry("sp", id.SP)));
        sys.getChildren().add(new TreeItem<>(new TreeTableViewEntry(TREE_ITEM_NAME_PC, null)));

        TreeItem<TreeTableViewEntry> dptr = new TreeItem<>(new TreeTableViewEntry(TREE_ITEM_NAME_DPTR, null));
        dptr.getChildren().add(new TreeItem<>(new TreeTableViewEntry("[0]", id.DPL)));
        dptr.getChildren().add(new TreeItem<>(new TreeTableViewEntry("[1]", id.DPH)));
        sys.getChildren().add(dptr);

        TreeItem<TreeTableViewEntry> psw = new TreeItem<>(new TreeTableViewEntry("psw", id.PSW));
        psw.getChildren().add(new TreeItem<>(new TreeTableViewEntry("p", id.bitField.P)));
        psw.getChildren().add(new TreeItem<>(new TreeTableViewEntry("ud", id.bitField.UD)));
        psw.getChildren().add(new TreeItem<>(new TreeTableViewEntry("ov", id.bitField.OV)));
        psw.getChildren().add(new TreeItem<>(new TreeTableViewEntry("rs0", id.bitField.RS0)));
        psw.getChildren().add(new TreeItem<>(new TreeTableViewEntry("rs1", id.bitField.RS1)));
        psw.getChildren().add(new TreeItem<>(new TreeTableViewEntry("f0", id.bitField.F0)));
        psw.getChildren().add(new TreeItem<>(new TreeTableViewEntry("ac", id.bitField.AC)));
        psw.getChildren().add(new TreeItem<>(new TreeTableViewEntry("cy", id.bitField.CY)));

        sys.getChildren().add(psw);
        sys.setExpanded(true);

        root.getChildren().add(regs);
        root.getChildren().add(sys);

        registersView.setRoot(root);
    }

    public void setMainWindow(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }

    @Override
    public void update() {
        registersView.refresh();
    }

    private class TreeTableViewEntry {
        private static final int RADIX_HEX = 16;

        private final String name;
        private final Object value;

        TreeTableViewEntry(String name, Object value) {
            this.name = name;
            this.value = value;
        }

        String getName() {
            return name;
        }

        String getValue() {
            if (value instanceof Memory.Cell) {
                return IntegerUtil.toStringWithPrefix(((Memory.Cell) value).getValue().toInt(), RADIX_HEX, 2);
            } else if (value instanceof InternalData.Bit) {
                return ((InternalData.Bit) value).getValue() ? "1" : "0";
            } else if (value == null) {
                if (StringUtils.equals(name, TREE_ITEM_NAME_PC)) {
                    return IntegerUtil.toStringWithPrefix(simulator.getProgramCounter().toInt(), RADIX_HEX, 4);
                } else if (StringUtils.equals(name, TREE_ITEM_NAME_DPTR)) {
                    UInt8 dph = simulator.getInternalData().DPH.getValue();
                    UInt8 dpl = simulator.getInternalData().DPL.getValue();
                    UInt16 dptr = dph.x16().shl(8).or(dpl.x16());
                    return IntegerUtil.toStringWithPrefix(dptr.toInt(), RADIX_HEX, 4);
                }
            }

            return "";
        }

        void setValue(String value) {
            if (this.value instanceof Memory.Cell) {
                if (IntegerUtil.isValid(value)) {
                    ((Memory.Cell) this.value).setValue(UInt8.valueOf(IntegerUtil.parseInt(value)));
                }
            } else if (this.value instanceof InternalData.Bit) {
                if (StringUtils.isNotBlank(value) && value.matches("[01]")) {
                    ((InternalData.Bit) this.value).setValue(Integer.parseInt(value) != 0);
                }
            } else if (this.value == null) {
                if (!IntegerUtil.isValid(value)) {
                    return;
                }

                int parsedValue = IntegerUtil.parseInt(value);
                if (StringUtils.equals(name, TREE_ITEM_NAME_PC)) {
                    simulator.setProgramCounter(UInt16.valueOf(parsedValue));
                } else if (StringUtils.equals(name, TREE_ITEM_NAME_DPTR)) {
                    simulator.getInternalData().DPL.setValue(UInt8.valueOf(parsedValue & 0xff));
                    simulator.getInternalData().DPH.setValue(UInt8.valueOf((parsedValue >> 8) & 0xff));
                }
            }
        }
    }

}
