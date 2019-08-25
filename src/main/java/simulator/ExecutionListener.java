package simulator;

import simulator.memory.datatype.UInt16;

public interface ExecutionListener {

    void process(UInt16 programCounter);

    void reset();

    boolean isRunning();

}
