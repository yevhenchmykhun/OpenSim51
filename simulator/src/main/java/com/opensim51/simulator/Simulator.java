package com.opensim51.simulator;

import com.opensim51.misc.intelhexparser.Intel8HexParser;
import com.opensim51.simulator.memory.ExternalCode;
import com.opensim51.simulator.memory.ExternalData;
import com.opensim51.simulator.memory.InternalData;
import com.opensim51.simulator.memory.MemoryUnit;
import com.opensim51.simulator.memory.datatype.UInt16;
import com.opensim51.simulator.memory.datatype.UInt8;
import com.opensim51.simulator.sys.ProcessingUnit;

import java.io.File;
import java.io.FileInputStream;

public class Simulator {

    private static Simulator simulator = new Simulator();

    private MemoryUnit memoryUnit;
    private ProcessingUnit processingUnit;

    private Simulator() {
        memoryUnit = new MemoryUnit();
        processingUnit = new ProcessingUnit(memoryUnit);
    }

    public static Simulator getInstance() {
        return simulator;
    }

    public InternalData getInternalData() {
        return memoryUnit.getInternalData();
    }

    public ExternalData getExternalData() {
        return memoryUnit.getExternalData();
    }

    public ExternalCode getExternalCode() {
        return memoryUnit.getExternalCode();
    }

    public UInt16 getProgramCounter() {
        return processingUnit.getProgramCounter();
    }

    public void setProgramCounter(UInt16 programCounter) {
        processingUnit.setProgramCounter(programCounter);
    }

    public void burnIntel8HexFile(File file) throws Exception {
        FileInputStream stream = new FileInputStream(file);
        Intel8HexParser hexParser = new Intel8HexParser(stream, (address, data) -> {
            for (byte b : data) {
                memoryUnit.getExternalCode().setCellValue(address++, UInt8.valueOf(b));
            }
        });
        hexParser.parse();
    }

    public void run(ExecutionListener executionListener) {
        processingUnit.run(executionListener);
    }

    public void step(ExecutionListener executionListener) {
        processingUnit.step(executionListener);
    }

}
