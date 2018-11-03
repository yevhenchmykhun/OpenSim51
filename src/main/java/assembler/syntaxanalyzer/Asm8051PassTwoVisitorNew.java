package assembler.syntaxanalyzer;

import assembler.Instruction;
import assembler.Operand;
import assembler.antlr.Asm8051Parser;
import org.antlr.v4.runtime.tree.ParseTree;
import simulator.memory.ExternalCode;
import simulator.memory.datatype.UnsignedInt16;
import simulator.memory.datatype.UnsignedInt8;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Asm8051PassTwoVisitorNew extends Asm8051CommonVisitor {

    private final Map<String, UnsignedInt16> symbolTable;
    private final ExternalCode externalCode;
    private UnsignedInt16 locationCounter;

    public Asm8051PassTwoVisitorNew(ExternalCode externalCode, HashMap<String, UnsignedInt16> symbolTable) {
        this.symbolTable = symbolTable;
        this.externalCode = externalCode;
        locationCounter = new UnsignedInt16(0);
    }

    @Override
    public Integer visitMnemonic(Asm8051Parser.MnemonicContext ctx) {
        ParseTree child = ctx.getChild(0);
        String mnemonic = child.getChild(0).getText();
        Instruction instruction = Instruction.getByMnemonic(mnemonic.toUpperCase());
        List<Operand> operands = processOperands(child, symbolTable);
        List<Integer> machineCodes = instruction.toMachineCodes(operands);
        for (int machineCode : machineCodes) {
            externalCode.setCellValue(locationCounter, new UnsignedInt8(machineCode));
            locationCounter = locationCounter.inc();
        }

        return 0;
    }

}
