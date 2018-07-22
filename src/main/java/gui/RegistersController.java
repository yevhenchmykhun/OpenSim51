package gui;

import gui.util.IntegerUtil;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.fxml.FXML;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.TextFieldTreeTableCell;
import simulator.memory.Memory;
import simulator.memory.datatype.UnsignedInt8;

public class RegistersController implements Updatable {

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
        TreeItem<TreeTableViewEntry> root = new TreeItem<>();

        TreeItem<TreeTableViewEntry> regs = new TreeItem<>(new TreeTableViewEntry("Regs", null));
        regs.getChildren().add(new TreeItem<>(new TreeTableViewEntry("r0", MainWindow.data.R0)));
        regs.getChildren().add(new TreeItem<>(new TreeTableViewEntry("r1", MainWindow.data.R1)));
        regs.getChildren().add(new TreeItem<>(new TreeTableViewEntry("r2", MainWindow.data.R2)));
        regs.getChildren().add(new TreeItem<>(new TreeTableViewEntry("r3", MainWindow.data.R3)));
        regs.getChildren().add(new TreeItem<>(new TreeTableViewEntry("r4", MainWindow.data.R4)));
        regs.getChildren().add(new TreeItem<>(new TreeTableViewEntry("r5", MainWindow.data.R5)));
        regs.getChildren().add(new TreeItem<>(new TreeTableViewEntry("r6", MainWindow.data.R6)));
        regs.getChildren().add(new TreeItem<>(new TreeTableViewEntry("r7", MainWindow.data.R7)));
        regs.setExpanded(true);

        TreeItem<TreeTableViewEntry> sys = new TreeItem<>(new TreeTableViewEntry("Sys", null));
        sys.getChildren().add(new TreeItem<>(new TreeTableViewEntry("a", MainWindow.data.ACC)));
        sys.getChildren().add(new TreeItem<>(new TreeTableViewEntry("b", MainWindow.data.B)));
        sys.getChildren().add(new TreeItem<>(new TreeTableViewEntry("sp", MainWindow.data.SP)));
        sys.getChildren().add(new TreeItem<>(new TreeTableViewEntry("pc", null)));

        TreeItem<TreeTableViewEntry> dptr = new TreeItem<>(new TreeTableViewEntry("dptr", null));
        dptr.getChildren().add(new TreeItem<>(new TreeTableViewEntry("[0]", null)));
        dptr.getChildren().add(new TreeItem<>(new TreeTableViewEntry("[1]", null)));
        sys.getChildren().add(dptr);

        TreeItem<TreeTableViewEntry> psw = new TreeItem<>(new TreeTableViewEntry("psw", MainWindow.data.PSW));
        psw.getChildren().add(new TreeItem<>(new TreeTableViewEntry("p", MainWindow.data.bitMap.P)));
        psw.getChildren().add(new TreeItem<>(new TreeTableViewEntry("ud", MainWindow.data.bitMap.UD)));
        psw.getChildren().add(new TreeItem<>(new TreeTableViewEntry("ov", MainWindow.data.bitMap.OV)));
        psw.getChildren().add(new TreeItem<>(new TreeTableViewEntry("rs0", MainWindow.data.bitMap.RS0)));
        psw.getChildren().add(new TreeItem<>(new TreeTableViewEntry("rs1", MainWindow.data.bitMap.RS1)));
        psw.getChildren().add(new TreeItem<>(new TreeTableViewEntry("f0", MainWindow.data.bitMap.F0)));
        psw.getChildren().add(new TreeItem<>(new TreeTableViewEntry("ac", MainWindow.data.bitMap.AC)));
        psw.getChildren().add(new TreeItem<>(new TreeTableViewEntry("cy", MainWindow.data.bitMap.CY)));

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
