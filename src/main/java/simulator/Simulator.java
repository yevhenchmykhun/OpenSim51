package simulator;

import intelhexparser.Intel8HexParser;
import simulator.instruction.Instruction;
import simulator.memory.ExternalCode;
import simulator.memory.ExternalData;
import simulator.memory.InternalData;
import simulator.memory.datatype.UnsignedInt16;
import simulator.memory.datatype.UnsignedInt8;
import simulator.peripherals.Timer0;

import java.io.File;
import java.io.FileInputStream;

public class Simulator {

    private static Simulator simulator;

    private final InternalData internalData = new InternalData();
    private final ExternalData externalData = new ExternalData();
    private final ExternalCode externalCode = new ExternalCode();

    private UnsignedInt16 programCounter = new UnsignedInt16(0);

    private Timer0 timer0 = new Timer0(internalData);

    public static Simulator getInstance() {
        if (simulator == null) {
            simulator = new Simulator();
        }

        return simulator;
    }

    public InternalData getInternalData() {
        return internalData;
    }

    public ExternalData getExternalData() {
        return externalData;
    }

    public ExternalCode getExternalCode() {
        return externalCode;
    }

    public UnsignedInt16 getPC() {
        return programCounter;
    }

    public void setPC(UnsignedInt16 programCounter) {
        this.programCounter = programCounter;
    }

    public void burnIntel8HexFile(File file) throws Exception {
        FileInputStream stream = new FileInputStream(file);
        Intel8HexParser hexParser = new Intel8HexParser(stream, (address, data) -> {
            for (byte b : data) {
                externalCode.setCellValue(address++, new UnsignedInt8(b));
            }
        });
        hexParser.parse();
    }

    public void run(ExecutionListener executionListener) {
        while (executionListener.isRunning()) {
            step(executionListener);
        }
    }

    public void step(ExecutionListener executionListener) {
        UnsignedInt8 opcode = externalCode.getCellValue(programCounter);
        Instruction instruction = Instruction.getByOpcode(opcode.toInt());

        for (int cycles = 0; cycles < instruction.getCycles(); cycles++) {
            if (timer0.isRunning()) {
                timer0.step();
            }
        }

        programCounter = instruction.execute(this);
        executionListener.process(programCounter);
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
