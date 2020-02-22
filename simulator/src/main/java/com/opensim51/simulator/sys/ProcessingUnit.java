package com.opensim51.simulator.sys;

import com.opensim51.simulator.ExecutionListener;
import com.opensim51.simulator.instruction.Instruction;
import com.opensim51.simulator.memory.MemoryGroup;
import com.opensim51.simulator.memory.datatype.UInt16;
import com.opensim51.simulator.memory.datatype.UInt8;

public class ProcessingUnit {

    private final MemoryGroup memoryGroup;

    private final Timer0 timer0;
    private final InterruptSystem interruptSystem;

    private UInt16 programCounter = UInt16.ZERO;

    public ProcessingUnit(MemoryGroup memoryGroup) {
        this.memoryGroup = memoryGroup;

        this.timer0 = new Timer0(memoryGroup.getInternalData());
        this.interruptSystem = new InterruptSystem(memoryGroup.getInternalData());
    }

    public UInt16 getProgramCounter() {
        return programCounter;
    }

    public void setProgramCounter(UInt16 programCounter) {
        this.programCounter = programCounter;
    }

    public void step(ExecutionListener executionListener) {
        UInt8 opcode = memoryGroup.getExternalCode().getCellValue(programCounter);
        Instruction instruction = Instruction.getByOpcode(opcode);

        for (int cycles = 0; cycles < instruction.getCycles(); cycles++) {
            if (timer0.isRunning()) {
                timer0.step();
            }

            interruptSystem.pollInterrupt0();
            interruptSystem.pollInterrupt1();
        }

        programCounter = instruction.execute(programCounter, memoryGroup);
        programCounter = interruptSystem.getInterruptServiceRoutine(programCounter);

        executionListener.process(programCounter);
    }

}
