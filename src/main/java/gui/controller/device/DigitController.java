package gui.controller.device;

import javafx.fxml.FXML;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import simulator.memory.datatype.UInt8;

public class DigitController {

    @FXML
    private Polygon aLed;

    @FXML
    private Polygon bLed;

    @FXML
    private Polygon cLed;

    @FXML
    private Polygon dLed;

    @FXML
    private Polygon eLed;

    @FXML
    private Polygon fLed;

    @FXML
    private Polygon gLed;

    @FXML
    private Circle dpLed;

    public void setValue(UInt8 value) {
        bLed.setFill(Color.RED);
        cLed.setFill(Color.RED);
        eLed.setFill(Color.RED);
        fLed.setFill(Color.RED);
        gLed.setFill(Color.RED);
    }

    public void reset() {
        aLed.setFill(Color.DARKSLATEGRAY);
        bLed.setFill(Color.DARKSLATEGRAY);
        cLed.setFill(Color.DARKSLATEGRAY);
        dLed.setFill(Color.DARKSLATEGRAY);
        eLed.setFill(Color.DARKSLATEGRAY);
        fLed.setFill(Color.DARKSLATEGRAY);
        gLed.setFill(Color.DARKSLATEGRAY);
        dpLed.setFill(Color.DARKSLATEGRAY);
    }

}
