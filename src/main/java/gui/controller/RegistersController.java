package gui.controller;

import gui.util.IntegerUtil;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.fxml.FXML;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.TextFieldTreeTableCell;
import org.apache.commons.lang3.StringUtils;
import simulator.Simulator;
import simulator.memory.InternalData;
import simulator.memory.Memory;
import simulator.memory.datatype.UInt16;
import simulator.memory.datatype.UInt8;

public class RegistersController implements Updatable {

    private static final String TREE_ITEM_NAME_PC = "pc";

    private static final String TREE_ITEM_NAME_DPTR = "dptr";

    @FXML
    private TreeTableView<TreeTableViewEntry> registersView;

    @FXML
    private TreeTableColumn<TreeTableViewEntry, String> registerColumn;

    @FXML
    private TreeTableColumn<TreeTableViewEntry, String> valueColumn;

    private Simulator simulator = Simulator.getInstance();

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
        psw.getChildren().add(new TreeItem<>(new TreeTableViewEntry("p", id.bitMap.P)));
        psw.getChildren().add(new TreeItem<>(new TreeTableViewEntry("ud", id.bitMap.UD)));
        psw.getChildren().add(new TreeItem<>(new TreeTableViewEntry("ov", id.bitMap.OV)));
        psw.getChildren().add(new TreeItem<>(new TreeTableViewEntry("rs0", id.bitMap.RS0)));
        psw.getChildren().add(new TreeItem<>(new TreeTableViewEntry("rs1", id.bitMap.RS1)));
        psw.getChildren().add(new TreeItem<>(new TreeTableViewEntry("f0", id.bitMap.F0)));
        psw.getChildren().add(new TreeItem<>(new TreeTableViewEntry("ac", id.bitMap.AC)));
        psw.getChildren().add(new TreeItem<>(new TreeTableViewEntry("cy", id.bitMap.CY)));

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
        private static final String PREFIX_HEX = "0x";

        private String name;
        private Object value;

        TreeTableViewEntry(String name, Object value) {
            this.name = name;
            this.value = value;
        }

        String getName() {
            return name;
        }

        String getValue() {
            if (value instanceof Memory.Cell) {
                return PREFIX_HEX + IntegerUtil.toString(((Memory.Cell) value).getValue().toInt(), RADIX_HEX, 2);
            } else if (value instanceof Memory.Bit) {
                return ((Memory.Bit) value).getValue() ? "1" : "0";
            } else if (value == null) {
                if (StringUtils.equals(name, TREE_ITEM_NAME_PC)) {
                    return PREFIX_HEX + IntegerUtil.toString(simulator.getPC().toInt(), RADIX_HEX, 4);
                } else if (StringUtils.equals(name, TREE_ITEM_NAME_DPTR)) {
                    UInt8 dph = simulator.getInternalData().DPH.getValue();
                    UInt8 dpl = simulator.getInternalData().DPL.getValue();
                    UInt16 dptr = dph.toUInt16().shiftLeft(8).or(dpl.toUInt16());
                    return PREFIX_HEX + IntegerUtil.toString(dptr.toInt(), RADIX_HEX, 4);
                }
            }

            return "";
        }

        void setValue(String value) {
            if (this.value instanceof Memory.Cell) {
                if (IntegerUtil.isValid(value)) {
                    ((Memory.Cell) this.value).setValue(new UInt8(IntegerUtil.parseInt(value)));
                }
            } else if (this.value instanceof Memory.Bit) {
                if (StringUtils.isNotBlank(value) && value.matches("[01]")) {
                    ((Memory.Bit) this.value).setValue(Integer.parseInt(value) != 0);
                }
            } else if (this.value == null) {
                if (!IntegerUtil.isValid(value)) {
                    return;
                }

                int parsedValue = IntegerUtil.parseInt(value);
                if (StringUtils.equals(name, TREE_ITEM_NAME_PC)) {
                    simulator.setPC(new UInt16(parsedValue));
                } else if (StringUtils.equals(name, TREE_ITEM_NAME_DPTR)) {
                    simulator.getInternalData().DPL.setValue(new UInt8(parsedValue & 0xff));
                    simulator.getInternalData().DPH.setValue(new UInt8((parsedValue >> 8) & 0xff));
                }
            }
        }
    }

}
