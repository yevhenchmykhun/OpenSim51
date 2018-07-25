package gui;

import gui.util.IntegerUtil;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import simulator.Simulator;
import simulator.memory.InternalData;
import simulator.memory.Memory;

import java.util.Arrays;
import java.util.List;

public class InterruptController implements Updatable {

    private InternalData internalData = Simulator.getInstance().getInternalData();

    @FXML
    public TableView<Interrupt> tableView;

    @FXML
    public TableColumn<Interrupt, String> intSourceColumn;

    @FXML
    public TableColumn<Interrupt, String> vectorColumn;

    @FXML
    public TableColumn<Interrupt, String> modeColumn;

    @FXML
    public TableColumn<Interrupt, String> reqColumn;

    @FXML
    public TableColumn<Interrupt, String> enaColumn;

    @FXML
    public TableColumn<Interrupt, String> priColumn;

    @FXML
    public GridPane flagsGridPane;

    private MainWindow mainWindow;

    @FXML
    public void initialize() {
        intSourceColumn.setCellValueFactory(
                param -> new SimpleStringProperty(param.getValue().getIntSource())
        );
        vectorColumn.setCellValueFactory(param -> {
                    String vector = IntegerUtil.toString(param.getValue().getVector(), 16, 4);
                    return new SimpleStringProperty(vector.toUpperCase() + "H");
                }
        );
        modeColumn.setCellValueFactory(param -> {
                    Memory.Bit mode = param.getValue().getMode();
                    return new SimpleStringProperty(mode == null ? "" : mode.getValue() ? "1" : "0");
                }
        );
        reqColumn.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getReq().getValue() ? "1" : "0")
        );
        enaColumn.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getEna().getValue() ? "1" : "0")
        );
        priColumn.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getPri() == 1 ? "1" : "0")
        );

        tableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (oldValue != null) {
                for (CheckBox checkBox : oldValue.getCheckBoxes()) {
                    flagsGridPane.getChildren().remove(checkBox);
                }
            }

            if (newValue != null) {
                List<CheckBox> checkBoxes = newValue.getCheckBoxes();
                for (int i = 0; i < checkBoxes.size(); i++) {
                    CheckBox checkBox = checkBoxes.get(i);
                    if (checkBox != null) {
                        flagsGridPane.add(checkBox, i, 0);
                    }
                }
            }
        });

        update();
    }

    @Override
    public void update() {
        InternalData.BitMap bm = internalData.bitMap;

        ObservableList<Interrupt> list = FXCollections.observableArrayList(
                new Interrupt("P3.2/Int0", 0x03, bm.IT0, bm.IE0, bm.EX0, 0,
                        Arrays.asList(createCheckBox("IT0", bm.IT0), createCheckBox("IE0", bm.IE0), createCheckBox("EX0", bm.EX0))),
                new Interrupt("Timer 0", 0x0B, null, bm.TF0, bm.ET0, 0,
                        Arrays.asList(null, createCheckBox("TF0", bm.TF0), createCheckBox("ET0", bm.ET0))),
                new Interrupt("P3.3/Int1", 0x13, bm.IT1, bm.IE1, bm.EX1, 0,
                        Arrays.asList(createCheckBox("IT1", bm.IT1), createCheckBox("IE1", bm.IE1), createCheckBox("EX1", bm.EX1))),
                new Interrupt("Timer 1", 0x1B, null, bm.TF1, bm.ET1, 0,
                        Arrays.asList(null, createCheckBox("TF1", bm.TF1), createCheckBox("ET1", bm.ET1))),
                new Interrupt("Serial Rcv.", 0x23, null, bm.RI, bm.ES, 0,
                        Arrays.asList(null, createCheckBox("RI", bm.RI), createCheckBox("ES", bm.ES))),
                new Interrupt("Serial Xmit.", 0x23, null, bm.TI, bm.ES, 0,
                        Arrays.asList(null, createCheckBox("TI", bm.TI), createCheckBox("ES", bm.ES)))
        );

        int selectedIndex = tableView.getSelectionModel().getSelectedIndex();
        tableView.setItems(list);
        tableView.getSelectionModel().select(selectedIndex == -1 ? 0 : selectedIndex);
    }

    public void setMainWindow(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }

    private CheckBox createCheckBox(String label, Memory.Bit bit) {
        CheckBox checkBox = new CheckBox(label);
        checkBox.setSelected(bit.getValue());
        checkBox.setOnAction(event -> {
            bit.setValue(checkBox.isSelected());
            mainWindow.updateUserInterface();
        });

        return checkBox;
    }

    private class Interrupt {
        private String intSource;
        private int vector;
        private Memory.Bit mode;
        private Memory.Bit req;
        private Memory.Bit ena;
        private int pri;

        private List<CheckBox> checkBoxes;

        Interrupt(String intSource, int vector, Memory.Bit mode, Memory.Bit req, Memory.Bit ena, int pri, List<CheckBox> checkBoxes) {
            this.intSource = intSource;
            this.vector = vector;
            this.mode = mode;
            this.req = req;
            this.ena = ena;
            this.pri = pri;
            this.checkBoxes = checkBoxes;
        }

        String getIntSource() {
            return intSource;
        }

        int getVector() {
            return vector;
        }

        Memory.Bit getMode() {
            return mode;
        }

        Memory.Bit getReq() {
            return req;
        }

        Memory.Bit getEna() {
            return ena;
        }

        int getPri() {
            return pri;
        }

        List<CheckBox> getCheckBoxes() {
            return checkBoxes;
        }
    }
}
