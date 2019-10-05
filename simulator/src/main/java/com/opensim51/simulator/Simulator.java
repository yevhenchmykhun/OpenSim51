package com.opensim51.simulator;

import com.opensim51.simulator.memory.ExternalCode;
import com.opensim51.simulator.memory.ExternalData;
import com.opensim51.simulator.memory.InternalData;
import com.opensim51.simulator.memory.MemoryGroup;
import com.opensim51.simulator.memory.datatype.UInt16;
import com.opensim51.simulator.sys.ProcessingUnit;

public class Simulator {

    private static Simulator simulator;

    private MemoryGroup memoryGroup;
    private ProcessingUnit processingUnit;

    public Simulator() {
        memoryGroup = new MemoryGroup();
        processingUnit = new ProcessingUnit(memoryGroup);
    }

    public static Simulator getInstance() {
        if (simulator == null) {
            simulator = new Simulator();
        }

        return simulator;
    }

    public InternalData getInternalData() {
        return memoryGroup.getInternalData();
    }

    public ExternalData getExternalData() {
        return memoryGroup.getExternalData();
    }

    public ExternalCode getExternalCode() {
        return memoryGroup.getExternalCode();
    }

    public UInt16 getPC() {
        return processingUnit.getProgramCounter();
    }

    public void setPC(UInt16 programCounter) {
        processingUnit.setProgramCounter(programCounter);
    }

//    public void burnIntel8HexFile(File file) throws Exception {
//        FileInputStream stream = new FileInputStream(file);
//        Intel8HexParser hexParser = new Intel8HexParser(stream, (address, data) -> {
//            for (byte b : data) {
//                memoryGroup.getExternalCode().setCellValue(address++, new UInt8(b));
//            }
//        });
//        hexParser.parse();
//    }

    public void run(ExecutionListener executionListener) {
        processingUnit.run(executionListener);
    }

    public void step(ExecutionListener executionListener) {
        processingUnit.step(executionListener);
    }

    //    private synchronized void updateParity() {
//        int psw = this.memory[208];
//        if (this.evenNumberOfOnesInAcc()) {
//            if (psw % 2 != 0) {
//                this.memory[208] = psw - 1;
//            }
//        } else if (psw % 2 != 1) {
//            this.memory[208] = psw + 1;
//        }
//
//    }

}
