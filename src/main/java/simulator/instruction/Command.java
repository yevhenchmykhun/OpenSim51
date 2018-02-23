package simulator.instruction;

import simulator.Simulator;
import simulator.memory.ExternalCode;
import simulator.memory.ExternalData;
import simulator.memory.InternalData;

public interface Command {

    void execute(InternalData internalData, ExternalData externalData, ExternalCode externalCode, Simulator simulator, Instruction instruction);

}
