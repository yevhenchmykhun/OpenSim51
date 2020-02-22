package com.opensim51.gui.controller;

import com.opensim51.gui.util.IntegerUtil;
import com.opensim51.simulator.Simulator;
import com.opensim51.simulator.memory.InternalData;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;

import java.util.Arrays;
import java.util.List;

public class InterruptController implements Updatable, MainWindowDependant {

    @FXML
    private TableView<Interrupt> tableView;

    @FXML
    private TableColumn<Interrupt, String> intSourceColumn;

    @FXML
    private TableColumn<Interrupt, String> vectorColumn;

    @FXML
    private TableColumn<Interrupt, String> modeColumn;

    @FXML
    private TableColumn<Interrupt, String> reqColumn;

    @FXML
    private TableColumn<Interrupt, String> enaColumn;

    @FXML
    private TableColumn<Interrupt, String> priColumn;

    @FXML
    private GridPane flagsGridPane;

    private final InternalData internalData = Simulator.getInstance().getInternalData();

    private MainWindow mainWindow;

    @FXML
    public void initialize() {
        intSourceColumn.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getIntSource()));
        vectorColumn.setCellValueFactory(param -> {
            String vector = IntegerUtil.toString(param.getValue().getVector(), 16, 4);
            return new SimpleStringProperty("0x" + vector.toUpperCase() + "H");
        });
        modeColumn.setCellValueFactory(param -> {
            InternalData.Bit mode = param.getValue().getMode();
            return new SimpleStringProperty(mode == null ? "" : mode.getValue() ? "1" : "0");
        });
        reqColumn.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getReq().getValue() ? "1" : "0"));
        enaColumn.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getEna().getValue() ? "1" : "0"));
        priColumn.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getPri() == 1 ? "1" : "0"));

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
        InternalData.BitField bf = internalData.bitField;

        ObservableList<Interrupt> list = FXCollections.observableArrayList(
                new Interrupt("P3.2/Int0", 0x03, bf.IT0, bf.IE0, bf.EX0,
                        Arrays.asList(createCheckBox("IT0", bf.IT0), createCheckBox("IE0", bf.IE0), createCheckBox("EX0", bf.EX0))),
                new Interrupt("Timer 0", 0x0b, null, bf.TF0, bf.ET0,
                        Arrays.asList(null, createCheckBox("TF0", bf.TF0), createCheckBox("ET0", bf.ET0))),
                new Interrupt("P3.3/Int1", 0x13, bf.IT1, bf.IE1, bf.EX1,
                        Arrays.asList(createCheckBox("IT1", bf.IT1), createCheckBox("IE1", bf.IE1), createCheckBox("EX1", bf.EX1))),
                new Interrupt("Timer 1", 0x1b, null, bf.TF1, bf.ET1,
                        Arrays.asList(null, createCheckBox("TF1", bf.TF1), createCheckBox("ET1", bf.ET1))),
                new Interrupt("Serial Rcv.", 0x23, null, bf.RI, bf.ES,
                        Arrays.asList(null, createCheckBox("RI", bf.RI), createCheckBox("ES", bf.ES))),
                new Interrupt("Serial Xmit.", 0x23, null, bf.TI, bf.ES,
                        Arrays.asList(null, createCheckBox("TI", bf.TI), createCheckBox("ES", bf.ES)))
        );

        int selectedIndex = tableView.getSelectionModel().getSelectedIndex();
        tableView.setItems(list);
        tableView.getSelectionModel().select(selectedIndex == -1 ? 0 : selectedIndex);
    }

    public void setMainWindow(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }

    private CheckBox createCheckBox(String label, InternalData.Bit bit) {
        CheckBox checkBox = new CheckBox(label);
        checkBox.setSelected(bit.getValue());
        checkBox.setOnAction(event -> {
            bit.setValue(checkBox.isSelected());
            mainWindow.updateUserInterface();
        });

        return checkBox;
    }

    private class Interrupt {
        private final String intSource;
        private final int vector;
        private final InternalData.Bit mode;
        private final InternalData.Bit req;
        private final InternalData.Bit ena;
        private final int pri;

        private final List<CheckBox> checkBoxes;

        Interrupt(String intSource, int vector, InternalData.Bit mode, InternalData.Bit req, InternalData.Bit ena, List<CheckBox> checkBoxes) {
            this.intSource = intSource;
            this.vector = vector;
            this.mode = mode;
            this.req = req;
            this.ena = ena;
            this.pri = 0;
            this.checkBoxes = checkBoxes;
        }

        String getIntSource() {
            return intSource;
        }

        int getVector() {
            return vector;
        }

        InternalData.Bit getMode() {
            return mode;
        }

        InternalData.Bit getReq() {
            return req;
        }

        InternalData.Bit getEna() {
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
