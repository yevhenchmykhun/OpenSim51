package com.opensim51.simulator.instruction;

import com.opensim51.simulator.memory.MemoryUnit;
import com.opensim51.simulator.memory.datatype.UInt16;

public final class Instruction {
    private final int bytes;
    private final int cycles;
    private final InstructionType instructionType;

    Instruction(int bytes, int cycles, InstructionType instructionType) {
        this.bytes = bytes;
        this.cycles = cycles;
        this.instructionType = instructionType;
    }

    public UInt16 execute(UInt16 programCounter, MemoryUnit memoryUnit) {
        return instructionType.execute(programCounter, memoryUnit, bytes);
    }

    public int getCycles() {
        return cycles;
    }

}
