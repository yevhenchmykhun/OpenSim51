package gui.controller;

import gui.util.IntegerUtil;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.fxml.FXML;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.TextFieldTreeTableCell;
import simulator.Simulator;
import simulator.memory.InternalData;
import simulator.memory.Memory;
import simulator.memory.datatype.UnsignedInt8;

public class RegistersController implements Updatable {
    
    private Simulator simulator = Simulator.getInstance();

    @FXML
    public TreeTableView<TreeTableViewEntry> registersView;

    @FXML
    public TreeTableColumn<TreeTableViewEntry, String> registerColumn;

    @FXML
    public TreeTableColumn<TreeTableViewEntry, String> valueColumn;

    private MainWindow mainWindow;

    @FXML
    public void initialize() {
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

        registersView.setShowRoot(false);
        registersView.getColumns().setAll(registerColumn, valueColumn);

        update();
    }

    public void setMainWindow(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }

    @Override
    public void update() {
        InternalData internalData = simulator.getInternalData();

        TreeItem<TreeTableViewEntry> root = new TreeItem<>();

        TreeItem<TreeTableViewEntry> regs = new TreeItem<>(new TreeTableViewEntry("Regs", null));
        regs.getChildren().add(new TreeItem<>(new TreeTableViewEntry("r0", internalData.R0)));
        regs.getChildren().add(new TreeItem<>(new TreeTableViewEntry("r1", internalData.R1)));
        regs.getChildren().add(new TreeItem<>(new TreeTableViewEntry("r2", internalData.R2)));
        regs.getChildren().add(new TreeItem<>(new TreeTableViewEntry("r3", internalData.R3)));
        regs.getChildren().add(new TreeItem<>(new TreeTableViewEntry("r4", internalData.R4)));
        regs.getChildren().add(new TreeItem<>(new TreeTableViewEntry("r5", internalData.R5)));
        regs.getChildren().add(new TreeItem<>(new TreeTableViewEntry("r6", internalData.R6)));
        regs.getChildren().add(new TreeItem<>(new TreeTableViewEntry("r7", internalData.R7)));
        regs.setExpanded(true);

        TreeItem<TreeTableViewEntry> sys = new TreeItem<>(new TreeTableViewEntry("Sys", null));
        sys.getChildren().add(new TreeItem<>(new TreeTableViewEntry("a", internalData.ACC)));
        sys.getChildren().add(new TreeItem<>(new TreeTableViewEntry("b", internalData.B)));
        sys.getChildren().add(new TreeItem<>(new TreeTableViewEntry("sp", internalData.SP)));
        sys.getChildren().add(new TreeItem<>(new TreeTableViewEntry("pc", null)));

        TreeItem<TreeTableViewEntry> dptr = new TreeItem<>(new TreeTableViewEntry("dptr", null));
        dptr.getChildren().add(new TreeItem<>(new TreeTableViewEntry("[0]", null)));
        dptr.getChildren().add(new TreeItem<>(new TreeTableViewEntry("[1]", null)));
        sys.getChildren().add(dptr);

        TreeItem<TreeTableViewEntry> psw = new TreeItem<>(new TreeTableViewEntry("psw", internalData.PSW));
        psw.getChildren().add(new TreeItem<>(new TreeTableViewEntry("p", internalData.bitMap.P)));
        psw.getChildren().add(new TreeItem<>(new TreeTableViewEntry("ud", internalData.bitMap.UD)));
        psw.getChildren().add(new TreeItem<>(new TreeTableViewEntry("ov", internalData.bitMap.OV)));
        psw.getChildren().add(new TreeItem<>(new TreeTableViewEntry("rs0", internalData.bitMap.RS0)));
        psw.getChildren().add(new TreeItem<>(new TreeTableViewEntry("rs1", internalData.bitMap.RS1)));
        psw.getChildren().add(new TreeItem<>(new TreeTableViewEntry("f0", internalData.bitMap.F0)));
        psw.getChildren().add(new TreeItem<>(new TreeTableViewEntry("ac", internalData.bitMap.AC)));
        psw.getChildren().add(new TreeItem<>(new TreeTableViewEntry("cy", internalData.bitMap.CY)));

        sys.getChildren().add(psw);
        sys.setExpanded(true);

        root.getChildren().addAll(regs, sys);

        registersView.setRoot(root);
    }

    private class TreeTableViewEntry {
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
                String hex = IntegerUtil.toString(((Memory.Cell) value).getValue().toInt(), 16, 2);
                return "0x" + hex;
            } else if (value instanceof Memory.Bit) {
                return ((Memory.Bit) value).getValue() ? "1" : "0";
            }

            return "";
        }

        void setValue(String value) {
            if (this.value instanceof Memory.Cell) {
                ((Memory.Cell) this.value).setValue(new UnsignedInt8(IntegerUtil.parseInt(value)));
            } else if (this.value instanceof Memory.Bit) {
                ((Memory.Bit) this.value).setValue(Integer.parseUnsignedInt(value) != 0);
            }
        }
    }

}
