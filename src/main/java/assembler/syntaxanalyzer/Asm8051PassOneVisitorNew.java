package assembler.syntaxanalyzer;

import assembler.Instruction;
import assembler.Operand;
import assembler.antlr.Asm8051Parser;
import org.antlr.v4.runtime.tree.ParseTree;
import simulator.memory.datatype.UnsignedInt16;

import java.util.List;
import java.util.Map;

public class Asm8051PassOneVisitorNew extends Asm8051CommonVisitor {

    private final Map<String, UnsignedInt16> symbolTable;
    private UnsignedInt16 locationCounter;

    public Asm8051PassOneVisitorNew(Map<String, UnsignedInt16> symbolTable) {
        this.symbolTable = symbolTable;
        locationCounter = UnsignedInt16.ZERO;
    }

    @Override
    public Integer visitLine(Asm8051Parser.LineContext ctx) {
        ParseTree child = ctx.getChild(0);
        if (child instanceof Asm8051Parser.LabelContext) {
            String symbol = ((Asm8051Parser.LabelContext) child).symbol().getText();
            symbolTable.put(symbol, locationCounter);
        } else if (child instanceof Asm8051Parser.InstructionContext) {
            Integer offset = visitInstruction((Asm8051Parser.InstructionContext) child);
            locationCounter = locationCounter.add(new UnsignedInt16(offset));
        }

        return 0;
    }

    @Override
    public Integer visitInstruction(Asm8051Parser.InstructionContext ctx) {
        ParseTree child = ctx.getChild(0);
        if (child instanceof Asm8051Parser.LabelContext) {
            String symbol = ((Asm8051Parser.LabelContext) child).symbol().getText();
            symbolTable.put(symbol, locationCounter);
            return visitMnemonic(((Asm8051Parser.MnemonicContext) ctx.getChild(1)));
        } else {
            return visitMnemonic(((Asm8051Parser.MnemonicContext) child));
        }
    }

    @Override
    public Integer visitMnemonic(Asm8051Parser.MnemonicContext ctx) {
        ParseTree child = ctx.getChild(0);
        String mnemonic = child.getChild(0).getText();
        Instruction instruction = Instruction.getByMnemonic(mnemonic.toUpperCase());
        List<Operand> operands = processOperands(child, null);

        return instruction.toMachineCodes(operands).size();
    }

}