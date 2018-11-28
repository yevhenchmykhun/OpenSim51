package gui.controller.device;

import gui.controller.MainWindow;
import javafx.fxml.FXML;

public class DisplayController {

    @FXML
    private DigitController digit0Controller;

    @FXML
    private DigitController digit1Controller;

    @FXML
    private DigitController digit2Controller;

    @FXML
    private DigitController digit3Controller;

    @FXML
    private DigitController digit4Controller;

    @FXML
    private DigitController digit5Controller;

    @FXML
    private DigitController digit6Controller;

    @FXML
    private DigitController digit7Controller;

    @FXML
    public void initialize() {
        reset();
        digit0Controller.setValue(null);
    }

    public void reset() {
        digit0Controller.reset();
        digit1Controller.reset();
        digit2Controller.reset();
        digit3Controller.reset();
        digit4Controller.reset();
        digit5Controller.reset();
        digit6Controller.reset();
        digit7Controller.reset();
    }

    public void setMainWindow(MainWindow mainWindow) {

    }
}
