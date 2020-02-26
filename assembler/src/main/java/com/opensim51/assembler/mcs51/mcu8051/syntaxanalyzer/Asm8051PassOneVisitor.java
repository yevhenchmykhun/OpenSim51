package com.opensim51.assembler.mcs51.mcu8051.syntaxanalyzer;

import com.opensim51.assembler.mcs51.mcu8051.Asm8051Parser;
import com.opensim51.assembler.mcs51.mcu8051.Instruction;
import com.opensim51.assembler.mcs51.mcu8051.InstructionFactory;
import com.opensim51.assembler.mcs51.mcu8051.types.Operand;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.List;
import java.util.Map;

public class Asm8051PassOneVisitor extends Asm8051CommonVisitor {

    private final Map<String, Integer> symbolTable;
    private Integer locationCounter;

    public Asm8051PassOneVisitor(Map<String, Integer> symbolTable) {
        this.symbolTable = symbolTable;
        locationCounter = 0;
    }

    @Override
    public Integer visitLine(Asm8051Parser.LineContext ctx) {
        ParseTree child = ctx.getChild(0);
        if (child instanceof Asm8051Parser.LabelContext) {
            String symbol = ((Asm8051Parser.LabelContext) child).symbol().getText();
            symbolTable.put(symbol, locationCounter);
        } else if (child instanceof Asm8051Parser.InstructionContext) {
            Integer offset = visitInstruction((Asm8051Parser.InstructionContext) child);
            locationCounter = locationCounter + offset;
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
        Instruction instruction = InstructionFactory.getByMnemonic(mnemonic.toUpperCase());
        List<Operand> operands = processOperands(child, null);

        return instruction.toMachineCodes(locationCounter, operands).size();
    }

}