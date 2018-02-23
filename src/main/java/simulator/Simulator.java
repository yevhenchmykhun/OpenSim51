package simulator;

import intelhexparser.Intel8HexParser;
import simulator.instruction.Command;
import simulator.instruction.Instruction;
import simulator.memory.ExternalCode;
import simulator.memory.ExternalData;
import simulator.memory.InternalData;
import simulator.memory.datatype.UnsignedInt16;
import simulator.memory.datatype.UnsignedInt8;

import java.io.File;
import java.io.FileInputStream;

public class Simulator {

    private final InternalData internalData = new InternalData();
    private final ExternalData externalData = new ExternalData();
    private final ExternalCode externalCode = new ExternalCode();

    private UnsignedInt16 programCounter = new UnsignedInt16(0);

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

    public void run() {
        while (true) {
            UnsignedInt8 opcode = externalCode.getCellValue(programCounter);
            Instruction instruction = Instruction.findByOpcode(opcode.toInt());
            Command command = instruction.getCommand();
            command.execute(internalData, externalData, externalCode, this, instruction);
        }
    }

}
