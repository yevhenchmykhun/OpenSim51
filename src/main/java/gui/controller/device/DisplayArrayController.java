package gui.controller.device;

import gui.controller.Updatable;
import javafx.fxml.FXML;
import simulator.Simulator;
import simulator.memory.InternalData;
import simulator.memory.datatype.UInt8;

public class DisplayArrayController implements Updatable {

    private InternalData internalData = Simulator.getInstance().getInternalData();

    @FXML
    private DisplayController display0Controller;

    @FXML
    private DisplayController display1Controller;

    @FXML
    private DisplayController display2Controller;

    @FXML
    private DisplayController display3Controller;

    @FXML
    private DisplayController display4Controller;

    @FXML
    private DisplayController display5Controller;

    @FXML
    private DisplayController display6Controller;

    @FXML
    private DisplayController display7Controller;

    @FXML
    public void initialize() {
        display0Controller.setValue(UInt8.ZERO);
        display1Controller.setValue(UInt8.ZERO);
        display2Controller.setValue(UInt8.ZERO);
        display3Controller.setValue(UInt8.ZERO);
        display4Controller.setValue(UInt8.ZERO);
        display5Controller.setValue(UInt8.ZERO);
        display6Controller.setValue(UInt8.ZERO);
        display7Controller.setValue(UInt8.ZERO);
    }

    @Override
    public void update() {
        DisplayController[] displays = new DisplayController[]{
                display0Controller,
                display1Controller,
                display2Controller,
                display3Controller,
                display4Controller,
                display5Controller,
                display6Controller,
                display7Controller
        };

        // bits P3.5, P3.4, and P3.3 contain the number of the active display
        UInt8 p3 = internalData.P3.getValue();
        UInt8 mask = new UInt8(0x7).shiftLeft(0x3);
        int displayIndex = p3.and(mask).shiftRight(0x3).toInt();

        UInt8 value = internalData.P1.getValue();
        for (int i = 0; i < displays.length; i++) {
            displays[i].setValue(i == displayIndex ? value : UInt8.ZERO);
        }
    }

}
