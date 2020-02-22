package com.opensim51.gui.controller.device;

import com.opensim51.gui.controller.Updatable;
import com.opensim51.simulator.Simulator;
import com.opensim51.simulator.memory.InternalData;
import com.opensim51.simulator.memory.datatype.UInt8;
import javafx.fxml.FXML;

public class DisplayArrayController implements Updatable {

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

    private final InternalData internalData = Simulator.getInstance().getInternalData();

    @FXML
    public void initialize() {
        display0Controller.setValue(UInt8.MAX_VALUE);
        display1Controller.setValue(UInt8.MAX_VALUE);
        display2Controller.setValue(UInt8.MAX_VALUE);
        display3Controller.setValue(UInt8.MAX_VALUE);
        display4Controller.setValue(UInt8.MAX_VALUE);
        display5Controller.setValue(UInt8.MAX_VALUE);
        display6Controller.setValue(UInt8.MAX_VALUE);
        display7Controller.setValue(UInt8.MAX_VALUE);
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
        UInt8 mask = new UInt8(0x07).shl(3);
        int displayIndex = p3.and(mask).shr(3).toInt();

        UInt8 value = internalData.P1.getValue();
        for (int i = 0; i < displays.length; i++) {
            displays[i].setValue(i == displayIndex ? value : UInt8.MAX_VALUE);
        }
    }

}
