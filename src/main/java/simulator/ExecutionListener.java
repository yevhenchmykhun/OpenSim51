package simulator;

import simulator.memory.datatype.UnsignedInt16;

public interface ExecutionListener {

    void process(UnsignedInt16 programCounter);

    void reset();

    boolean isRunning();

}
