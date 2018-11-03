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
        SFR_ADDRESS_BY_NAME.put("TL0", 0x8a);
        SFR_ADDRESS_BY_NAME.put("TL1", 0x8b);
        SFR_ADDRESS_BY_NAME.put("TH0", 0x8c);
        SFR_ADDRESS_BY_NAME.put("TH1", 0x8d);
        SFR_ADDRESS_BY_NAME.put("P1", 0x90);
        SFR_ADDRESS_BY_NAME.put("SCON", 0x98);
        SFR_ADDRESS_BY_NAME.put("SBUF", 0x99);
        SFR_ADDRESS_BY_NAME.put("P2", 0xa0);
        SFR_ADDRESS_BY_NAME.put("IE", 0xa8);
        SFR_ADDRESS_BY_NAME.put("P3", 0xb0);
        SFR_ADDRESS_BY_NAME.put("IP", 0xb8);
        SFR_ADDRESS_BY_NAME.put("PSW", 0xd0);
        SFR_ADDRESS_BY_NAME.put("ACC", 0xe0);
        SFR_ADDRESS_BY_NAME.put("A", 0xe0);
        SFR_ADDRESS_BY_NAME.put("B", 0xf0);


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
        BIT_ADDRESS_BY_NAME.put("IT1", 0x8a);
        BIT_ADDRESS_BY_NAME.put("IE1", 0x8b);
        BIT_ADDRESS_BY_NAME.put("TR0", 0x8c);
        BIT_ADDRESS_BY_NAME.put("TF0", 0x8d);
        BIT_ADDRESS_BY_NAME.put("TR1", 0x8e);
        BIT_ADDRESS_BY_NAME.put("TF1", 0x8f);
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
        BIT_ADDRESS_BY_NAME.put("RB8", 0x9a);
        BIT_ADDRESS_BY_NAME.put("TB8", 0x9b);
        BIT_ADDRESS_BY_NAME.put("REN", 0x9c);
        BIT_ADDRESS_BY_NAME.put("SM2", 0x9d);
        BIT_ADDRESS_BY_NAME.put("SM1", 0x9e);
        BIT_ADDRESS_BY_NAME.put("SM0", 0x9f);
        // Port 2 bits
        BIT_ADDRESS_BY_NAME.put("P2.0", 0xa0);
        BIT_ADDRESS_BY_NAME.put("P2.1", 0xa1);
        BIT_ADDRESS_BY_NAME.put("P2.2", 0xa2);
        BIT_ADDRESS_BY_NAME.put("P2.3", 0xa3);
        BIT_ADDRESS_BY_NAME.put("P2.4", 0xa4);
        BIT_ADDRESS_BY_NAME.put("P2.5", 0xa5);
        BIT_ADDRESS_BY_NAME.put("P2.6", 0xa6);
        BIT_ADDRESS_BY_NAME.put("P2.7", 0xa7);
        // Interrupt Enable bits
        BIT_ADDRESS_BY_NAME.put("EX0", 0xa8);
        BIT_ADDRESS_BY_NAME.put("ET0", 0xa9);
        BIT_ADDRESS_BY_NAME.put("EX1", 0xaa);
        BIT_ADDRESS_BY_NAME.put("ET1", 0xab);
        BIT_ADDRESS_BY_NAME.put("ES", 0xac);
        BIT_ADDRESS_BY_NAME.put("ET2", 0xad);
        BIT_ADDRESS_BY_NAME.put("EA", 0xaf);
        // Port 3 bits
        BIT_ADDRESS_BY_NAME.put("P3.0", 0xb0);
        BIT_ADDRESS_BY_NAME.put("P3.1", 0xb1);
        BIT_ADDRESS_BY_NAME.put("P3.2", 0xb2);
        BIT_ADDRESS_BY_NAME.put("P3.3", 0xb3);
        BIT_ADDRESS_BY_NAME.put("P3.4", 0xb4);
        BIT_ADDRESS_BY_NAME.put("P3.5", 0xb5);
        BIT_ADDRESS_BY_NAME.put("P3.6", 0xb6);
        BIT_ADDRESS_BY_NAME.put("P3.7", 0xb7);
        // Interrupt Priority bits
        BIT_ADDRESS_BY_NAME.put("PX0", 0xb8);
        BIT_ADDRESS_BY_NAME.put("PT0", 0xb9);
        BIT_ADDRESS_BY_NAME.put("PX1", 0xba);
        BIT_ADDRESS_BY_NAME.put("PT1", 0xbb);
        BIT_ADDRESS_BY_NAME.put("PS", 0xbc);
        BIT_ADDRESS_BY_NAME.put("PT2", 0xbd);
        // PSW bits
        BIT_ADDRESS_BY_NAME.put("P", 0xd0);
        BIT_ADDRESS_BY_NAME.put("UD", 0xd1);
        BIT_ADDRESS_BY_NAME.put("OV", 0xd2);
        BIT_ADDRESS_BY_NAME.put("RS0", 0xd3);
        BIT_ADDRESS_BY_NAME.put("RS1", 0xd4);
        BIT_ADDRESS_BY_NAME.put("F0", 0xd5);
        BIT_ADDRESS_BY_NAME.put("AC", 0xd6);
        BIT_ADDRESS_BY_NAME.put("CY", 0xd7);
        // ACC bits
        BIT_ADDRESS_BY_NAME.put("ACC.0", 0xe0);
        BIT_ADDRESS_BY_NAME.put("ACC.1", 0xe1);
        BIT_ADDRESS_BY_NAME.put("ACC.2", 0xe2);
        BIT_ADDRESS_BY_NAME.put("ACC.3", 0xe3);
        BIT_ADDRESS_BY_NAME.put("ACC.4", 0xe4);
        BIT_ADDRESS_BY_NAME.put("ACC.5", 0xe5);
        BIT_ADDRESS_BY_NAME.put("ACC.6", 0xe6);
        BIT_ADDRESS_BY_NAME.put("ACC.7", 0xe7);
        // B bits
        BIT_ADDRESS_BY_NAME.put("B.0", 0xf0);
        BIT_ADDRESS_BY_NAME.put("B.1", 0xf1);
        BIT_ADDRESS_BY_NAME.put("B.2", 0xf2);
        BIT_ADDRESS_BY_NAME.put("B.3", 0xf3);
        BIT_ADDRESS_BY_NAME.put("B.4", 0xf4);
        BIT_ADDRESS_BY_NAME.put("B.5", 0xf5);
        BIT_ADDRESS_BY_NAME.put("B.6", 0xf6);
        BIT_ADDRESS_BY_NAME.put("B.7", 0xf7);
    }

    @Override
    public Integer visitExpression(Asm8051Parser.ExpressionContext ctx) {
        if (ctx.LPAREN() != null) {
            return visitExpression(ctx.expression(0));
        } else if (ctx.prefix != null) {
            int value = visitExpression(ctx.expression(0));
            switch (ctx.prefix.getType()) {
                case Asm8051Parser.PLUS:
                    return +value;
                case Asm8051Parser.MINUS:
                    return -value;
                case Asm8051Parser.HIGH:
                    return (value >> 8) & 0xff;
                case Asm8051Parser.LOW:
                    return value & 0xff;
                case Asm8051Parser.NOT:
                    return ~value;
            }
        } else if (ctx.bop != null) {
            int left = visitExpression(ctx.expression(0));
            int right = visitExpression(ctx.expression(1));
            switch (ctx.bop.getType()) {
                case Asm8051Parser.SLASH:
                    return left / right;
                case Asm8051Parser.ASTERISK:
                    return left * right;
                case Asm8051Parser.MOD:
                    return left % right;
//                case Asm8051Parser.SHL:
//                    return value & 0xff;
//                case Asm8051Parser.SHR:
//                    return ~value;
                case Asm8051Parser.PLUS:
                    return left + right;
                case Asm8051Parser.MINUS:
                    return left - right;
                case Asm8051Parser.AND:
                    return left & right;
                case Asm8051Parser.OR:
                    return left | right;
                case Asm8051Parser.XOR:
                    return left ^ right;
            }
        } else if (ctx.comparator != null) {
            int left = visitExpression(ctx.expression(0));
            int right = visitExpression(ctx.expression(1));
            switch (ctx.comparator.getType()) {
                case Asm8051Parser.EQ:
                    return left == right ? 1 : 0;
                case Asm8051Parser.NE:
                    return left != right ? 1 : 0;
                case Asm8051Parser.LT:
                    return left < right ? 1 : 0;
                case Asm8051Parser.LE:
                    return left <= right ? 1 : 0;
                case Asm8051Parser.GT:
                    return left > right ? 1 : 0;
                case Asm8051Parser.GE:
                    return left >= right ? 1 : 0;
            }
        }

        return super.visitExpression(ctx);
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
    public Integer visitOctal(Asm8051Parser.OctalContext ctx) {
        String text = ctx.getText();
        return Integer.parseInt(text.substring(0, text.length() - 1), 8);
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

    @Override
    public Integer visitBit(Asm8051Parser.BitContext ctx) {
        ParseTree child = ctx.getChild(0);
        return child instanceof Asm8051Parser.NumberContext ? visit(child) : BIT_ADDRESS_BY_NAME.get(child.getText().toUpperCase());
    }

    protected List<Operand> processOperands(ParseTree parseTree, Map<String, UnsignedInt16> symbolTable) {
        List<Operand> operands = new ArrayList<>();

        for (int i = 1; i < parseTree.getChildCount(); i++) {
            ParseTree child = parseTree.getChild(i);
            if (isOperand(child)) {
                OperandType operandType = determineOperandType(child);
                int operandValue = determineOperandValue(child, operandType, symbolTable);
                operands.add(new Operand(operandType, operandValue));
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
                || child instanceof Asm8051Parser.NotBitContext
                || child instanceof Asm8051Parser.SymbolContext
                || child instanceof Asm8051Parser.CarryContext
                || child instanceof Asm8051Parser.DptrContext
                || child instanceof Asm8051Parser.AtDptrContext
                || child instanceof Asm8051Parser.AtAPlusDptrContext
                || child instanceof Asm8051Parser.AtAPlusPcContext;
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
        } else if (operand instanceof Asm8051Parser.NotBitContext) {
            return OperandType.NOT_BIT;
        } else if (operand instanceof Asm8051Parser.SymbolContext) {
            return OperandType.SYMBOL;
        } else if (operand instanceof Asm8051Parser.CarryContext) {
            return OperandType.CARRY;
        } else if (operand instanceof Asm8051Parser.DptrContext) {
            return OperandType.DPTR;
        } else if (operand instanceof Asm8051Parser.AtDptrContext) {
            return OperandType.AT_DPTR;
        } else if (operand instanceof Asm8051Parser.AtAPlusDptrContext) {
            return OperandType.AT_A_PLUS_DPTR;
        } else if (operand instanceof Asm8051Parser.AtAPlusPcContext) {
            return OperandType.AT_A_PLUS_PC;
        } else {
            return OperandType.UNKNOWN;
        }
    }

    protected int determineOperandValue(ParseTree operand, OperandType operandType, Map<String, UnsignedInt16> symbolTable) {
        int value = 0;
        switch (operandType) {
            case SYMBOL: {
                if (symbolTable != null) {
                    String symbol = ((Asm8051Parser.SymbolContext) operand).SYMBOL().getText();
                    if (symbolTable.containsKey(symbol)) {
                        value = symbolTable.get(symbol).toInt();
                    }
                }
                break;
            }
            case REGISTER:
            case INDIRECT_REGISTER:
            case DIRECT:
            case IMMEDIATE:
            case BIT:
            case NOT_BIT:
                value = visit(operand);
                break;
        }

        return value;
    }

}
