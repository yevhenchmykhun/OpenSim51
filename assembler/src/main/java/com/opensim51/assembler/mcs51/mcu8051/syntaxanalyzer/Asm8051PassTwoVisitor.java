package com.opensim51.assembler.mcs51.mcu8051.syntaxanalyzer;

import com.opensim51.assembler.TranslationListener;
import com.opensim51.assembler.mcs51.mcu8051.Asm8051Parser;
import com.opensim51.assembler.mcs51.mcu8051.Instruction;
import com.opensim51.assembler.mcs51.mcu8051.InstructionFactory;
import com.opensim51.assembler.mcs51.mcu8051.types.Operand;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.List;
import java.util.Map;

public class Asm8051PassTwoVisitor extends Asm8051CommonVisitor {

    private final Map<String, Integer> symbolTable;
    private final TranslationListener translationListener;
    private Integer locationCounter;

    public Asm8051PassTwoVisitor(Map<String, Integer> symbolTable, TranslationListener translationListener) {
        this.symbolTable = symbolTable;
        this.translationListener = translationListener;
        locationCounter = 0;
    }

    @Override
    public Integer visitMnemonic(Asm8051Parser.MnemonicContext ctx) {
        ParseTree child = ctx.getChild(0);
        String mnemonic = child.getChild(0).getText();

        Instruction instruction = InstructionFactory.getByMnemonic(mnemonic.toUpperCase());
        List<Operand> operands = processOperands(child, symbolTable);
        List<Integer> machineCodes = instruction.toMachineCodes(locationCounter, operands);
        translationListener.processInstruction(ctx.getStart().getLine(), locationCounter, machineCodes);

        locationCounter = locationCounter + machineCodes.size();

        return 0;
    }

}
