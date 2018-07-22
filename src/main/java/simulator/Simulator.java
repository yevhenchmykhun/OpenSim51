package simulator;

import assembler.syntaxanalyzer.Asm8051Lexer;
import assembler.syntaxanalyzer.Asm8051Parser;
import assembler.syntaxanalyzer.Asm8051PassOneVisitor;
import assembler.syntaxanalyzer.Asm8051PassTwoVisitor;
import intelhexparser.Intel8HexParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import simulator.instruction.Command;
import simulator.instruction.Instruction;
import simulator.memory.ExternalCode;
import simulator.memory.ExternalData;
import simulator.memory.InternalData;
import simulator.memory.datatype.UnsignedInt16;
import simulator.memory.datatype.UnsignedInt8;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;

public class Simulator {

    private final InternalData internalData = new InternalData();
    private final ExternalData externalData = new ExternalData();
    private final ExternalCode externalCode = new ExternalCode();

    public InternalData getInternalData() {
        return internalData;
    }

    public ExternalData getExternalData() {
        return externalData;
    }

    public ExternalCode getExternalCode() {
        return externalCode;
    }

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

    public void translate(String sourceCode) {
        CharStream stream = CharStreams.fromString(sourceCode);
        Asm8051Lexer lexer = new Asm8051Lexer(stream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        Asm8051Parser parser = new Asm8051Parser(tokens);
        ParseTree tree = parser.source();

        HashMap<String, UnsignedInt16> symbolTable = new HashMap<>();
        Asm8051PassOneVisitor passOneVisitor = new Asm8051PassOneVisitor(symbolTable);
        passOneVisitor.visit(tree);

        Asm8051PassTwoVisitor passTwoVisitor = new Asm8051PassTwoVisitor(externalCode, symbolTable);
        passTwoVisitor.visit(tree);
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
