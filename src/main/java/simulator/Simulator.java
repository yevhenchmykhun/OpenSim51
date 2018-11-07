package simulator;

import assembler.antlr.Asm8051Lexer;
import assembler.antlr.Asm8051Parser;
import assembler.syntaxanalyzer.Asm8051PassOneVisitorNew;
import assembler.syntaxanalyzer.Asm8051PassTwoVisitorNew;
import intelhexparser.Intel8HexParser;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.antlr.v4.runtime.tree.ParseTree;
import simulator.instruction.Command;
import simulator.instruction.Instruction;
import simulator.memory.ExternalCode;
import simulator.memory.ExternalData;
import simulator.memory.InternalData;
import simulator.memory.datatype.UnsignedInt16;
import simulator.memory.datatype.UnsignedInt8;
import simulator.peripherals.Timer0;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;

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

    public void translate(String sourceCode, BaseErrorListener errorListener) throws ParseCancellationException {
        CharStream stream = CharStreams.fromString(sourceCode);

        Asm8051Lexer lexer = new Asm8051Lexer(stream);
        lexer.removeErrorListeners();
        lexer.addErrorListener(errorListener);

        CommonTokenStream tokens = new CommonTokenStream(lexer);
        Asm8051Parser parser = new Asm8051Parser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(errorListener);

        ParseTree tree = parser.source();

        HashMap<String, UnsignedInt16> symbolTable = new HashMap<>();
        Asm8051PassOneVisitorNew passOneVisitor = new Asm8051PassOneVisitorNew(symbolTable);
        passOneVisitor.visit(tree);

        Asm8051PassTwoVisitorNew passTwoVisitor = new Asm8051PassTwoVisitorNew(externalCode, symbolTable);
        passTwoVisitor.visit(tree);
    }

    public void run() {
        while (true) {
            step();
        }
    }

    public void step() {
        UnsignedInt8 opcode = externalCode.getCellValue(programCounter);
        Instruction instruction = Instruction.findByOpcode(opcode.toInt());

        for (int cycles = 0; cycles < instruction.getCycles(); cycles++) {
            if (timer0.isRunning()) {
                timer0.step();
            }
        }

        Command command = instruction.getCommand();
        command.execute(internalData, externalData, externalCode, this, instruction);
    }

}
