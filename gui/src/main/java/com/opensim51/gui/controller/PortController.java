package com.opensim51.gui.controller;

import com.opensim51.gui.util.IntegerUtil;
import com.opensim51.simulator.memory.InternalData;
import com.opensim51.simulator.memory.datatype.UInt8;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import org.jetbrains.annotations.NotNull;

public class PortController implements Updatable, MainWindowDependant {

    @FXML
    private Label portNameLabel;

    @FXML
    private TextField valueTextField;

    @FXML
    private CheckBox pin0;

    @FXML
    private CheckBox pin1;

    @FXML
    private CheckBox pin2;

    @FXML
    private CheckBox pin3;

    @FXML
    private CheckBox pin4;

    @FXML
    private CheckBox pin5;

    @FXML
    private CheckBox pin6;

    @FXML
    private CheckBox pin7;

    private InternalData.BitAddressableCell port;

    private MainWindow mainWindow;

    @FXML
    public void initialize() {
        valueTextField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                String text = valueTextField.getText();
                if (IntegerUtil.isValid(text)) {
                    port.setValue(UInt8.valueOf(IntegerUtil.parseInt(text)));
                    mainWindow.updateUserInterface();
                }
            }
        });

        pin0.selectedProperty().addListener(createChangeListener(0));
        pin1.selectedProperty().addListener(createChangeListener(1));
        pin2.selectedProperty().addListener(createChangeListener(2));
        pin3.selectedProperty().addListener(createChangeListener(3));
        pin4.selectedProperty().addListener(createChangeListener(4));
        pin5.selectedProperty().addListener(createChangeListener(5));
        pin6.selectedProperty().addListener(createChangeListener(6));
        pin7.selectedProperty().addListener(createChangeListener(7));
    }

    @Override
    public void update() {
        valueTextField.setText(IntegerUtil.toStringWithPrefix(port.getValue().toInt(), 16, 2));
        pin0.setSelected(port.getBitValue(0));
        pin1.setSelected(port.getBitValue(1));
        pin2.setSelected(port.getBitValue(2));
        pin3.setSelected(port.getBitValue(3));
        pin4.setSelected(port.getBitValue(4));
        pin5.setSelected(port.getBitValue(5));
        pin6.setSelected(port.getBitValue(6));
        pin7.setSelected(port.getBitValue(7));
    }

    public void setMainWindow(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }

    void setPortNumber(String portNumber) {
        portNameLabel.setText("P" + portNumber + ":");
    }

    void setPort(InternalData.BitAddressableCell port) {
        this.port = port;
    }

    @NotNull
    private ChangeListener<Boolean> createChangeListener(int position) {
        return (observable, oldValue, newValue) -> {
            port.setBitValue(position, newValue);
            valueTextField.setText(IntegerUtil.toStringWithPrefix(port.getValue().toInt(), 16, 2));
            mainWindow.updateUserInterface();
        };
    }

}
