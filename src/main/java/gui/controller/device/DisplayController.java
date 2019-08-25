package gui.controller.device;

import javafx.fxml.FXML;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import org.jetbrains.annotations.NotNull;
import simulator.memory.datatype.UInt8;

public class DisplayController {

    private static final Color LIT = Color.RED;

    private static final Color NOT_LIT = Color.DARKSLATEGRAY;

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

    void setValue(@NotNull UInt8 value) {
        UInt8 not = value.not();
        aLed.setFill(not.getBitValue(0) ? LIT : NOT_LIT);
        bLed.setFill(not.getBitValue(1) ? LIT : NOT_LIT);
        cLed.setFill(not.getBitValue(2) ? LIT : NOT_LIT);
        dLed.setFill(not.getBitValue(3) ? LIT : NOT_LIT);
        eLed.setFill(not.getBitValue(4) ? LIT : NOT_LIT);
        fLed.setFill(not.getBitValue(5) ? LIT : NOT_LIT);
        gLed.setFill(not.getBitValue(6) ? LIT : NOT_LIT);
        dpLed.setFill(not.getBitValue(7) ? LIT : NOT_LIT);
    }

}
