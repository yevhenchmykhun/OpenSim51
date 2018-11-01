package assembler.syntaxanalyzer;

import assembler.Operand;
import assembler.OperandType;
import assembler.antlr.Asm8051BaseVisitor;
import assembler.antlr.Asm8051Parser;
import org.antlr.v4.runtime.tree.ParseTree;
import simulator.memory.datatype.UnsignedInt16;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Asm8051CommonVisitor extends Asm8051BaseVisitor<Integer> {

    private static final Map<String, Integer> SFR_ADDRESS_BY_NAME;
    private static final Map<String, Integer> BIT_ADDRESS_BY_NAME;

    static {
        SFR_ADDRESS_BY_NAME = new HashMap<>();
        SFR_ADDRESS_BY_NAME.put("P0", 0x80);
        SFR_ADDRESS_BY_NAME.put("SP", 0x81);
        SFR_ADDRESS_BY_NAME.put("DPL", 0x82);
        SFR_ADDRESS_BY_NAME.put("DPH", 0x83);
        SFR_ADDRESS_BY_NAME.put("PCON", 0x87);
        SFR_ADDRESS_BY_NAME.put("TCON", 0x88);
        SFR_ADDRESS_BY_NAME.put("TMOD", 0x89);
        SFR_ADDRESS_BY_NAME.put("TL0", 0x8A);
        SFR_ADDRESS_BY_NAME.put("TL1", 0x8B);
        SFR_ADDRESS_BY_NAME.put("TH0", 0x8C);
        SFR_ADDRESS_BY_NAME.put("TH1", 0x8D);
        SFR_ADDRESS_BY_NAME.put("P1", 0x90);
        SFR_ADDRESS_BY_NAME.put("SCON", 0x98);
        SFR_ADDRESS_BY_NAME.put("SBUF", 0x99);
        SFR_ADDRESS_BY_NAME.put("P2", 0xA0);
        SFR_ADDRESS_BY_NAME.put("IE", 0xA8);
        SFR_ADDRESS_BY_NAME.put("P3", 0xB0);
        SFR_ADDRESS_BY_NAME.put("IP", 0xB8);
        SFR_ADDRESS_BY_NAME.put("PSW", 0xD0);
        SFR_ADDRESS_BY_NAME.put("ACC", 0xE0);
        SFR_ADDRESS_BY_NAME.put("A", 0xE0);
        SFR_ADDRESS_BY_NAME.put("B", 0xF0);


        BIT_ADDRESS_BY_NAME = new HashMap<>();
        // Port 0 bits
        BIT_ADDRESS_BY_NAME.put("P0.0", 0x80);
        BIT_ADDRESS_BY_NAME.put("P0.1", 0x81);
        BIT_ADDRESS_BY_NAME.put("P0.2", 0x82);
        BIT_ADDRESS_BY_NAME.put("P0.3", 0x83);
        BIT_ADDRESS_BY_NAME.put("P0.4", 0x84);
        BIT_ADDRESS_BY_NAME.put("P0.5", 0x85);
        BIT_ADDRESS_BY_NAME.put("P0.6", 0x86);
        BIT_ADDRESS_BY_NAME.put("P0.7", 0x87);
        // Timer Control bits
        BIT_ADDRESS_BY_NAME.put("IT0", 0x88);
        BIT_ADDRESS_BY_NAME.put("IE0", 0x89);
        BIT_ADDRESS_BY_NAME.put("IT1", 0x8A);
        BIT_ADDRESS_BY_NAME.put("IE1", 0x8B);
        BIT_ADDRESS_BY_NAME.put("TR0", 0x8C);
        BIT_ADDRESS_BY_NAME.put("TF0", 0x8D);
        BIT_ADDRESS_BY_NAME.put("TR1", 0x8E);
        BIT_ADDRESS_BY_NAME.put("TF1", 0x8F);
        // Port 1 bits
        BIT_ADDRESS_BY_NAME.put("P1.0", 0x90);
        BIT_ADDRESS_BY_NAME.put("P1.1", 0x91);
        BIT_ADDRESS_BY_NAME.put("P1.2", 0x92);
        BIT_ADDRESS_BY_NAME.put("P1.3", 0x93);
        BIT_ADDRESS_BY_NAME.put("P1.4", 0x94);
        BIT_ADDRESS_BY_NAME.put("P1.5", 0x95);
        BIT_ADDRESS_BY_NAME.put("P1.6", 0x96);
        BIT_ADDRESS_BY_NAME.put("P1.7", 0x97);
        // Serial Control bits
        BIT_ADDRESS_BY_NAME.put("RI", 0x98);
        BIT_ADDRESS_BY_NAME.put("TI", 0x99);
        BIT_ADDRESS_BY_NAME.put("RB8", 0x9A);
        BIT_ADDRESS_BY_NAME.put("TB8", 0x9B);
        BIT_ADDRESS_BY_NAME.put("REN", 0x9C);
        BIT_ADDRESS_BY_NAME.put("SM2", 0x9D);
        BIT_ADDRESS_BY_NAME.put("SM1", 0x9E);
        BIT_ADDRESS_BY_NAME.put("SM0", 0x9F);
        // Port 2 bits
        BIT_ADDRESS_BY_NAME.put("P2.0", 0xA0);
        BIT_ADDRESS_BY_NAME.put("P2.1", 0xA1);
        BIT_ADDRESS_BY_NAME.put("P2.2", 0xA2);
        BIT_ADDRESS_BY_NAME.put("P2.3", 0xA3);
        BIT_ADDRESS_BY_NAME.put("P2.4", 0xA4);
        BIT_ADDRESS_BY_NAME.put("P2.5", 0xA5);
        BIT_ADDRESS_BY_NAME.put("P2.6", 0xA6);
        BIT_ADDRESS_BY_NAME.put("P2.7", 0xA7);
        // Interrupt Enable bits
        BIT_ADDRESS_BY_NAME.put("EX0", 0xA8);
        BIT_ADDRESS_BY_NAME.put("ET0", 0xA9);
        BIT_ADDRESS_BY_NAME.put("EX1", 0xAA);
        BIT_ADDRESS_BY_NAME.put("ET1", 0xAB);
        BIT_ADDRESS_BY_NAME.put("ES", 0xAC);
        BIT_ADDRESS_BY_NAME.put("ET2", 0xAD);
        BIT_ADDRESS_BY_NAME.put("EA", 0xAF);
        // Port 3 bits
        BIT_ADDRESS_BY_NAME.put("P3.0", 0xB0);
        BIT_ADDRESS_BY_NAME.put("P3.1", 0xB1);
        BIT_ADDRESS_BY_NAME.put("P3.2", 0xB2);
        BIT_ADDRESS_BY_NAME.put("P3.3", 0xB3);
        BIT_ADDRESS_BY_NAME.put("P3.4", 0xB4);
        BIT_ADDRESS_BY_NAME.put("P3.5", 0xB5);
        BIT_ADDRESS_BY_NAME.put("P3.6", 0xB6);
        BIT_ADDRESS_BY_NAME.put("P3.7", 0xB7);
        // Interrupt Priority bits
        BIT_ADDRESS_BY_NAME.put("PX0", 0xB8);
        BIT_ADDRESS_BY_NAME.put("PT0", 0xB9);
        BIT_ADDRESS_BY_NAME.put("PX1", 0xBA);
        BIT_ADDRESS_BY_NAME.put("PT1", 0xBB);
        BIT_ADDRESS_BY_NAME.put("PS", 0xBC);
        BIT_ADDRESS_BY_NAME.put("PT2", 0xBD);
        // PSW bits
        BIT_ADDRESS_BY_NAME.put("P", 0xD0);
        BIT_ADDRESS_BY_NAME.put("UD", 0xD1);
        BIT_ADDRESS_BY_NAME.put("OV", 0xD2);
        BIT_ADDRESS_BY_NAME.put("RS0", 0xD3);
        BIT_ADDRESS_BY_NAME.put("RS1", 0xD4);
        BIT_ADDRESS_BY_NAME.put("F0", 0xD5);
        BIT_ADDRESS_BY_NAME.put("AC", 0xD6);
        BIT_ADDRESS_BY_NAME.put("CY", 0xD7);
        // ACC bits
        BIT_ADDRESS_BY_NAME.put("ACC.0", 0xE0);
        BIT_ADDRESS_BY_NAME.put("ACC.1", 0xE1);
        BIT_ADDRESS_BY_NAME.put("ACC.2", 0xE2);
        BIT_ADDRESS_BY_NAME.put("ACC.3", 0xE3);
        BIT_ADDRESS_BY_NAME.put("ACC.4", 0xE4);
        BIT_ADDRESS_BY_NAME.put("ACC.5", 0xE5);
        BIT_ADDRESS_BY_NAME.put("ACC.6", 0xE6);
        BIT_ADDRESS_BY_NAME.put("ACC.7", 0xE7);
        // B bits
        BIT_ADDRESS_BY_NAME.put("B.0", 0xF0);
        BIT_ADDRESS_BY_NAME.put("B.1", 0xF1);
        BIT_ADDRESS_BY_NAME.put("B.2", 0xF2);
        BIT_ADDRESS_BY_NAME.put("B.3", 0xF3);
        BIT_ADDRESS_BY_NAME.put("B.4", 0xF4);
        BIT_ADDRESS_BY_NAME.put("B.5", 0xF5);
        BIT_ADDRESS_BY_NAME.put("B.6", 0xF6);
        BIT_ADDRESS_BY_NAME.put("B.7", 0xF7);
    }

    @Override
    public Integer visitDecimal(Asm8051Parser.DecimalContext ctx) {
        String text = ctx.getText();
        int endIndex = text.endsWith("D") || text.endsWith("d") ? text.length() - 1 : text.length();
        return Integer.parseInt(text.substring(0, endIndex), 10);
    }

    @Override
    public Integer visitHexadecimal(Asm8051Parser.HexadecimalContext ctx) {
        String text = ctx.getText();
        return Integer.parseInt(text.substring(0, text.length() - 1), 16);
    }

    @Override
    public Integer visitBinary(Asm8051Parser.BinaryContext ctx) {
        String text = ctx.getText();
        return Integer.parseInt(text.substring(0, text.length() - 1), 2);
    }

    @Override
    public Integer visitIndirectRegister(Asm8051Parser.IndirectRegisterContext ctx) {
        String text = ctx.getText();
        return Integer.parseInt(text.substring(text.length() - 1));
    }

    @Override
    public Integer visitRegister(Asm8051Parser.RegisterContext ctx) {
        String text = ctx.getText();
        return Integer.parseInt(text.substring(text.length() - 1));
    }

    @Override
    public Integer visitSfr(Asm8051Parser.SfrContext ctx) {
        return SFR_ADDRESS_BY_NAME.get(ctx.getText().toUpperCase());
    }

    protected List<Operand> processOperands(ParseTree tree, Map<String, UnsignedInt16> symbolTable) {
        List<Operand> operands = new ArrayList<>();

        for (int i = 1; i < tree.getChildCount(); i++) {
            ParseTree child = tree.getChild(i);
            if (isOperand(child)) {
                OperandType operandType = determineOperandType(child);
                operands.add(new Operand(operandType, determineOperandValue(child, operandType, symbolTable)));
            }
        }

        return operands;
    }

    protected boolean isOperand(ParseTree child) {
        return child instanceof Asm8051Parser.RegisterContext
                || child instanceof Asm8051Parser.IndirectRegisterContext
                || child instanceof Asm8051Parser.DirectContext
                || child instanceof Asm8051Parser.ImmediateContext
                || child instanceof Asm8051Parser.AccumulatorContext
                || child instanceof Asm8051Parser.BitContext
                || child instanceof Asm8051Parser.SymbolContext
                || child instanceof Asm8051Parser.CarryContext
                || child instanceof Asm8051Parser.DptrContext;
    }

    protected OperandType determineOperandType(ParseTree operand) {
        if (operand instanceof Asm8051Parser.RegisterContext) {
            return OperandType.REGISTER;
        } else if (operand instanceof Asm8051Parser.IndirectRegisterContext) {
            return OperandType.INDIRECT_REGISTER;
        } else if (operand instanceof Asm8051Parser.DirectContext) {
            return OperandType.DIRECT;
        } else if (operand instanceof Asm8051Parser.ImmediateContext) {
            return OperandType.IMMEDIATE;
        } else if (operand instanceof Asm8051Parser.AccumulatorContext) {
            return OperandType.ACCUMULATOR;
        } else if (operand instanceof Asm8051Parser.BitContext) {
            return OperandType.BIT;
        } else if (operand instanceof Asm8051Parser.SymbolContext) {
            return OperandType.SYMBOL;
        } else if (operand instanceof Asm8051Parser.CarryContext) {
            return OperandType.CARRY;
        } else if (operand instanceof Asm8051Parser.DptrContext) {
            return OperandType.DPTR;
        } else {
            return OperandType.UNKNOWN;
        }
    }

    protected int determineOperandValue(ParseTree operand, OperandType operandType, Map<String, UnsignedInt16> symbolTable) {
        int value = 0;
        switch (operandType) {
            case REGISTER:
                value = visitRegister(((Asm8051Parser.RegisterContext) operand));
                break;
            case INDIRECT_REGISTER:
                value = visitIndirectRegister(((Asm8051Parser.IndirectRegisterContext) operand));
                break;
            case DIRECT:
                value = visitDirect(((Asm8051Parser.DirectContext) operand));
                break;
            case IMMEDIATE:
                value = visitImmediate(((Asm8051Parser.ImmediateContext) operand));
                break;
            case SYMBOL:
                if (symbolTable != null) {
                    String symbol = ((Asm8051Parser.LabelContext) operand).symbol().getText();
                    value = symbolTable.get(symbol).toInt();
                }
                break;
            case BIT: {
                ParseTree child = operand.getChild(0);
                value = child instanceof Asm8051Parser.NumberContext ? visit(child) : BIT_ADDRESS_BY_NAME.get(child.getText().toUpperCase());
                break;
            }
        }

        return value;
    }

}
