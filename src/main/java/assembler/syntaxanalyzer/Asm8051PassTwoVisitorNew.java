package assembler.syntaxanalyzer;

import assembler.Instruction;
import assembler.Operand;
import assembler.antlr.Asm8051Parser;
import org.antlr.v4.runtime.tree.ParseTree;
import simulator.memory.datatype.UnsignedInt16;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Asm8051PassTwoVisitorNew extends Asm8051CommonVisitor {

    private final Map<String, UnsignedInt16> symbolTable;
    private final TranslationListener translationListener;
    private UnsignedInt16 locationCounter;

    public Asm8051PassTwoVisitorNew(HashMap<String, UnsignedInt16> symbolTable, TranslationListener translationListener) {
        this.symbolTable = symbolTable;
        this.translationListener = translationListener;
        locationCounter = UnsignedInt16.ZERO;
    }

    @Override
    public Integer visitMnemonic(Asm8051Parser.MnemonicContext ctx) {
        ParseTree child = ctx.getChild(0);
        String mnemonic = child.getChild(0).getText();

        Instruction instruction = Instruction.getByMnemonic(mnemonic.toUpperCase());
        List<Operand> operands = processOperands(child, symbolTable);
        List<Integer> machineCodes = instruction.toMachineCodes(operands);
        translationListener.processInstruction(ctx, locationCounter, machineCodes);

        locationCounter = locationCounter.add(new UnsignedInt16(machineCodes.size()));

        return 0;
    }

}
