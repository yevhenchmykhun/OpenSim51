package gui.controller;

import gui.util.IntegerUtil;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import simulator.Simulator;
import simulator.memory.InternalData;
import simulator.memory.Memory;
import simulator.memory.datatype.UnsignedInt8;

public class TimerController implements Updatable {

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

    private InternalData id = Simulator.getInstance().getInternalData();
    private InternalData.BitMap bm = id.bitMap;

    private String timerNumber;
    private MainWindow mainWindow;

    // should be called manually
    public void initializeController() {
        typeChoiceBox.setItems(FXCollections.observableArrayList("Timer", "Counter"));
        typeChoiceBox.getSelectionModel().selectFirst();

        tconTextField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                id.TCON.setValue(new UnsignedInt8(IntegerUtil.parseInt(tconTextField.getText())));
                mainWindow.updateUserInterface();
            }
        });
        tmodTextField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                id.TMOD.setValue(new UnsignedInt8(IntegerUtil.parseInt(tmodTextField.getText())));
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
                initializeView(id.TH0, id.TL0, bm.getBit(0xB4), bm.TF0, bm.TR0, bm.GATE0, bm.getBit(0xB2));
                break;
            case "1":
                modeChoiceBox.setItems(FXCollections.observableArrayList("0: 13 Bit Timer/Counter", "1: 16 Bit Timer/Counter", "2: 8 Bit auto-reload", "3: Disabled"));
                modeChoiceBox.getSelectionModel().selectFirst();
                initializeView(id.TH1, id.TL1, bm.getBit(0xB5), bm.TF1, bm.TR1, bm.GATE1, bm.getBit(0xB3));
                break;
        }

        update();
    }

    private void initializeView(Memory.Cell th, Memory.Cell tl, Memory.Bit tPin, Memory.Bit tf, Memory.Bit tr, Memory.Bit gate, Memory.Bit interrupt) {
        thTextField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                th.setValue(new UnsignedInt8(IntegerUtil.parseInt(thTextField.getText())));
                mainWindow.updateUserInterface();
            }
        });
        tlTextField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                tl.setValue(new UnsignedInt8(IntegerUtil.parseInt(tlTextField.getText())));
                mainWindow.updateUserInterface();
            }
        });

        tPinCheckBox.setOnAction(event -> {
            tPin.setValue(tPinCheckBox.isSelected());
            mainWindow.updateUserInterface();
        });
        tfCheckBox.setOnAction(event -> {
            tf.setValue(tfCheckBox.isSelected());
            mainWindow.updateUserInterface();
        });
        trCheckBox.setOnAction(event -> {
            tr.setValue(trCheckBox.isSelected());
            mainWindow.updateUserInterface();
        });
        gateCheckBox.setOnAction(event -> {
            gate.setValue(gateCheckBox.isSelected());
            mainWindow.updateUserInterface();
        });
        intCheckBox.setOnAction(event -> {
            interrupt.setValue(intCheckBox.isSelected());
            mainWindow.updateUserInterface();
        });
    }

    @Override
    public void update() {
        tconTextField.setText("0x" + IntegerUtil.toString(id.TCON.getValue().toInt(), 16, 2).toUpperCase());
        tmodTextField.setText("0x" + IntegerUtil.toString(id.TMOD.getValue().toInt(), 16, 2).toUpperCase());

        switch (timerNumber) {
            case "0":
                updateView(id.TH0, id.TL0, bm.getBit(0xB4), bm.TF0, bm.TR0, bm.GATE0, bm.getBit(0xB2));
                break;
            case "1":
                updateView(id.TH1, id.TL1, bm.getBit(0xB5), bm.TF1, bm.TR1, bm.GATE1, bm.getBit(0xB3));
                break;
        }
    }

    private void updateView(Memory.Cell th, Memory.Cell tl, Memory.Bit tPin, Memory.Bit tf, Memory.Bit tr, Memory.Bit gate, Memory.Bit interrupt) {
        thTextField.setText("0x" + IntegerUtil.toString(th.getValue().toInt(), 16, 2));
        tlTextField.setText("0x" + IntegerUtil.toString(tl.getValue().toInt(), 16, 2));
        statusTextBox.setText(tr.getValue() ? "Run" : "Stop");

        tPinCheckBox.setSelected(tPin.getValue());
        tfCheckBox.setSelected(tf.getValue());
        trCheckBox.setSelected(tr.getValue());
        gateCheckBox.setSelected(gate.getValue());
        intCheckBox.setSelected(interrupt.getValue());
    }

    public void setTimerNumber(String timerNumber) {
        this.timerNumber = timerNumber;
    }

    public void setMainWindow(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }
}
