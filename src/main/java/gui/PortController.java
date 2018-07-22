package gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import simulator.memory.Memory;

public class PortController implements Updatable {

    @FXML
    private Label portNameLabel;

    private Memory.BitAddressableCell port;

    public void setPortNumber(String portNumber) {
        portNameLabel.setText("P" + portNumber + ":");
    }

    public void setPort(Memory.BitAddressableCell port) {
        this.port = port;
    }

    @Override
    public void update() {

    }

}
