package com.opensim51.gui.controller;

import com.opensim51.gui.util.IntegerUtil;
import com.opensim51.simulator.Simulator;
import com.opensim51.simulator.memory.InternalData;
import com.opensim51.simulator.memory.Memory;
import com.opensim51.simulator.memory.datatype.UInt8;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.jetbrains.annotations.NotNull;

public class TimerController implements Updatable, MainWindowDependant {

    @FXML
    private Label thLabel;

    @FXML
    private Label tlLabel;

    @FXML
    private ChoiceBox<String> modeChoiceBox;

    @FXML
    private ChoiceBox<String> typeChoiceBox;

    @FXML
    private TextField tconTextField;

    @FXML
    private TextField thTextField;

    @FXML
    private TextField tmodTextField;

    @FXML
    private TextField tlTextField;

    @FXML
    private CheckBox tPinCheckBox;

    @FXML
    private CheckBox tfCheckBox;

    @FXML
    private TextField statusTextBox;

    @FXML
    private CheckBox trCheckBox;

    @FXML
    private CheckBox gateCheckBox;

    @FXML
    private CheckBox intCheckBox;

    private final InternalData id = Simulator.getInstance().getInternalData();

    private final InternalData.BitField bf = id.bitField;

    private String timerNumber;

    private MainWindow mainWindow;

    // should be called manually
    void initializeController() {
        typeChoiceBox.setItems(FXCollections.observableArrayList("Timer", "Counter"));
        typeChoiceBox.getSelectionModel().selectFirst();

        tconTextField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                id.TCON.setValue(new UInt8(IntegerUtil.parseInt(tconTextField.getText())));
                mainWindow.updateUserInterface();
            }
        });
        tmodTextField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                id.TMOD.setValue(new UInt8(IntegerUtil.parseInt(tmodTextField.getText())));
                mainWindow.updateUserInterface();
            }
        });

        thLabel.setText("TH" + timerNumber + ":");
        tlLabel.setText("TL" + timerNumber + ":");
        tPinCheckBox.setText("T" + timerNumber + " Pin");
        tfCheckBox.setText("TF" + timerNumber);
        trCheckBox.setText("TR" + timerNumber);
        intCheckBox.setText("INT" + timerNumber + "#");

        switch (timerNumber) {
            case "0":
                modeChoiceBox.setItems(FXCollections.observableArrayList("0: 13 Bit Timer/Counter", "1: 16 Bit Timer/Counter", "2: 8 Bit auto-reload", "3: Two 8 Bit Timer/Cnt"));
                modeChoiceBox.getSelectionModel().selectFirst();
                initializeView(id.TH0, id.TL0, bf.getBit(0xB4), bf.TF0, bf.TR0, bf.GATE0, bf.getBit(0xB2));
                break;
            case "1":
                modeChoiceBox.setItems(FXCollections.observableArrayList("0: 13 Bit Timer/Counter", "1: 16 Bit Timer/Counter", "2: 8 Bit auto-reload", "3: Disabled"));
                modeChoiceBox.getSelectionModel().selectFirst();
                initializeView(id.TH1, id.TL1, bf.getBit(0xB5), bf.TF1, bf.TR1, bf.GATE1, bf.getBit(0xB3));
                break;
        }

        update();
    }

    @Override
    public void update() {
        tconTextField.setText(IntegerUtil.toStringWithPrefix(id.TCON.getValue().toInt(), 16, 2));
        tmodTextField.setText(IntegerUtil.toStringWithPrefix(id.TMOD.getValue().toInt(), 16, 2));

        switch (timerNumber) {
            case "0":
                updateView(id.TH0, id.TL0, bf.getBit(0xB4), bf.TF0, bf.TR0, bf.GATE0, bf.getBit(0xB2));
                break;
            case "1":
                updateView(id.TH1, id.TL1, bf.getBit(0xB5), bf.TF1, bf.TR1, bf.GATE1, bf.getBit(0xB3));
                break;
        }
    }

    private void initializeView(InternalData.Cell th, InternalData.Cell tl, InternalData.Bit tPin, InternalData.Bit tf, InternalData.Bit tr, InternalData.Bit gate, InternalData.Bit interrupt) {
        thTextField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                th.setValue(new UInt8(IntegerUtil.parseInt(thTextField.getText())));
                mainWindow.updateUserInterface();
            }
        });
        tlTextField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                tl.setValue(new UInt8(IntegerUtil.parseInt(tlTextField.getText())));
                mainWindow.updateUserInterface();
            }
        });

        tPinCheckBox.setOnAction(getActionEventEventHandler(tPin, tPinCheckBox));
        tfCheckBox.setOnAction(getActionEventEventHandler(tf, tfCheckBox));
        trCheckBox.setOnAction(getActionEventEventHandler(tr, trCheckBox));
        gateCheckBox.setOnAction(getActionEventEventHandler(gate, gateCheckBox));
        intCheckBox.setOnAction(getActionEventEventHandler(interrupt, intCheckBox));
    }

    @NotNull
    private EventHandler<ActionEvent> getActionEventEventHandler(InternalData.Bit tPin, CheckBox tPinCheckBox) {
        return event -> {
            tPin.setValue(tPinCheckBox.isSelected());
            mainWindow.updateUserInterface();
        };
    }

    private void updateView(Memory.Cell th, Memory.Cell tl, InternalData.Bit tPin, InternalData.Bit tf, InternalData.Bit tr, InternalData.Bit gate, InternalData.Bit interrupt) {
        thTextField.setText(IntegerUtil.toStringWithPrefix(th.getValue().toInt(), 16, 2));
        tlTextField.setText(IntegerUtil.toStringWithPrefix(tl.getValue().toInt(), 16, 2));
        statusTextBox.setText(tr.getValue() ? "Run" : "Stop");

        tPinCheckBox.setSelected(tPin.getValue());
        tfCheckBox.setSelected(tf.getValue());
        trCheckBox.setSelected(tr.getValue());
        gateCheckBox.setSelected(gate.getValue());
        intCheckBox.setSelected(interrupt.getValue());
    }

    void setTimerNumber(String timerNumber) {
        this.timerNumber = timerNumber;
    }

    public void setMainWindow(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }

}
