package com.opensim51.simulator;

import com.opensim51.simulator.memory.datatype.UInt16;

public interface ExecutionListener {

    void process(UInt16 programCounter);

    boolean isRunning();

    void cancel();

    void reset();

}
