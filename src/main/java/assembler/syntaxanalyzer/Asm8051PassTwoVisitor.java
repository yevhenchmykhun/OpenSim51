package assembler.syntaxanalyzer;

import org.antlr.v4.runtime.tree.ParseTree;
import simulator.memory.ExternalCode;
import simulator.memory.datatype.UnsignedInt16;
import simulator.memory.datatype.UnsignedInt8;

import java.util.HashMap;
import java.util.Map;

public class Asm8051PassTwoVisitor<T> extends Asm8051BaseVisitor<Integer> {

    private final Map<String, UnsignedInt16> symbolTable;
    private final ExternalCode externalCode;
    private UnsignedInt16 locationCounter;

    public Asm8051PassTwoVisitor(ExternalCode externalCode, HashMap<String, UnsignedInt16> symbolTable) {
        this.symbolTable = symbolTable;
        this.externalCode = externalCode;
        locationCounter = new UnsignedInt16(0);
    }

    @Override
    public Integer visitMov(Asm8051Parser.MovContext ctx) {
        ParseTree firstArgument = ctx.getChild(1);
        ParseTree secondArgument = ctx.getChild(3);
        if (firstArgument instanceof Asm8051Parser.IndirectRegisterContext) {

            int registerNumber = visitIndirectRegister(((Asm8051Parser.IndirectRegisterContext) firstArgument));
            if (secondArgument instanceof Asm8051Parser.ImmediateContext) {
                int opcode = 0x76;

                int immediate = visitImmediate(((Asm8051Parser.ImmediateContext) secondArgument));
                externalCode.setCellValue(locationCounter, new UnsignedInt8(opcode | registerNumber));
                externalCode.setCellValue(locationCounter.inc(), new UnsignedInt8(immediate));

                moveLocationCounter(2);
            } else if (secondArgument instanceof Asm8051Parser.AccumulatorContext) {
                int opcode = 0xf6;

                externalCode.setCellValue(locationCounter, new UnsignedInt8(opcode | registerNumber));

                moveLocationCounter(1);
            } else if (secondArgument instanceof Asm8051Parser.DirectContext) {
                int opcode = 0xa6;

                int direct = visitDirect(((Asm8051Parser.DirectContext) secondArgument));

                externalCode.setCellValue(locationCounter, new UnsignedInt8(opcode | registerNumber));
                externalCode.setCellValue(locationCounter.inc(), new UnsignedInt8(direct));

                moveLocationCounter(2);
            }

        } else if (firstArgument instanceof Asm8051Parser.AccumulatorContext) {

            if (secondArgument instanceof Asm8051Parser.ImmediateContext) {
                int opcode = 0x74;

                int immediate = visitImmediate(((Asm8051Parser.ImmediateContext) secondArgument));
                externalCode.setCellValue(locationCounter, new UnsignedInt8(opcode));
                externalCode.setCellValue(locationCounter.inc(), new UnsignedInt8(immediate));

                moveLocationCounter(2);
            } else if (secondArgument instanceof Asm8051Parser.IndirectRegisterContext) {
                int opcode = 0xe6;

                int registerNumber = visitIndirectRegister(((Asm8051Parser.IndirectRegisterContext) secondArgument));
                externalCode.setCellValue(locationCounter, new UnsignedInt8(opcode | registerNumber));

                moveLocationCounter(1);
            } else if (secondArgument instanceof Asm8051Parser.DirectContext) {
                int opcode = 0xe5;

                int direct = visitDirect(((Asm8051Parser.DirectContext) secondArgument));

                externalCode.setCellValue(locationCounter, new UnsignedInt8(opcode));
                externalCode.setCellValue(locationCounter.inc(), new UnsignedInt8(direct));

                moveLocationCounter(2);
            } else if (secondArgument instanceof Asm8051Parser.RegisterContext) {
                int opcode = 0xe8;

                int registerNumber = visitRegister(((Asm8051Parser.RegisterContext) secondArgument));
                externalCode.setCellValue(locationCounter, new UnsignedInt8(opcode | registerNumber));

                moveLocationCounter(1);
            }

        } else if (firstArgument instanceof Asm8051Parser.DirectContext) {

            int direct = visitDirect(((Asm8051Parser.DirectContext) firstArgument));
            if (secondArgument instanceof Asm8051Parser.DirectContext) {
                int opcode = 0x85;

                int srcDirect = visitDirect(((Asm8051Parser.DirectContext) secondArgument));

                externalCode.setCellValue(locationCounter, new UnsignedInt8(opcode));
                externalCode.setCellValue(locationCounter.inc(), new UnsignedInt8(srcDirect));
                externalCode.setCellValue(locationCounter.inc().inc(), new UnsignedInt8(direct));

                moveLocationCounter(3);
            } else if (secondArgument instanceof Asm8051Parser.ImmediateContext) {
                int opcode = 0x75;

                int immediate = visitImmediate(((Asm8051Parser.ImmediateContext) secondArgument));
                externalCode.setCellValue(locationCounter, new UnsignedInt8(opcode));
                externalCode.setCellValue(locationCounter.inc(), new UnsignedInt8(direct));
                externalCode.setCellValue(locationCounter.inc().inc(), new UnsignedInt8(immediate));

                moveLocationCounter(3);
            } else if (secondArgument instanceof Asm8051Parser.IndirectRegisterContext) {
                int opcode = 0x86;

                int registerNumber = visitIndirectRegister(((Asm8051Parser.IndirectRegisterContext) secondArgument));
                externalCode.setCellValue(locationCounter, new UnsignedInt8(opcode | registerNumber));
                externalCode.setCellValue(locationCounter.inc(), new UnsignedInt8(direct));

                moveLocationCounter(2);
            } else if (secondArgument instanceof Asm8051Parser.AccumulatorContext) {
                int opcode = 0xf5;

                externalCode.setCellValue(locationCounter, new UnsignedInt8(opcode));
                externalCode.setCellValue(locationCounter.inc(), new UnsignedInt8(direct));

                moveLocationCounter(2);
            } else if (secondArgument instanceof Asm8051Parser.RegisterContext) {
                int opcode = 0x88;

                int registerNumber = visitRegister(((Asm8051Parser.RegisterContext) secondArgument));
                externalCode.setCellValue(locationCounter, new UnsignedInt8(opcode | registerNumber));
                externalCode.setCellValue(locationCounter.inc(), new UnsignedInt8(direct));

                moveLocationCounter(2);
            }

        } else if (firstArgument instanceof Asm8051Parser.RegisterContext) {

            int registerNumber = visitRegister(((Asm8051Parser.RegisterContext) firstArgument));
            if (secondArgument instanceof Asm8051Parser.ImmediateContext) {
                int opcode = 0x78;

                int immediate = visitImmediate(((Asm8051Parser.ImmediateContext) secondArgument));
                externalCode.setCellValue(locationCounter, new UnsignedInt8(opcode | registerNumber));
                externalCode.setCellValue(locationCounter.inc(), new UnsignedInt8(immediate));

                moveLocationCounter(2);
            } else if (secondArgument instanceof Asm8051Parser.AccumulatorContext) {
                int opcode = 0xf8;

                externalCode.setCellValue(locationCounter, new UnsignedInt8(opcode | registerNumber));

                moveLocationCounter(1);
            } else if (secondArgument instanceof Asm8051Parser.DirectContext) {
                int opcode = 0xa8;

                int direct = visitDirect(((Asm8051Parser.DirectContext) secondArgument));

                externalCode.setCellValue(locationCounter, new UnsignedInt8(opcode | registerNumber));
                externalCode.setCellValue(locationCounter.inc(), new UnsignedInt8(direct));

                moveLocationCounter(2);
            }

        }

        return 0;
    }

    @Override
    public Integer visitDecimal(Asm8051Parser.DecimalContext ctx) {
        String text = ctx.getText();
        return Integer.parseInt(text.substring(0, text.length() - 1), 10);
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
        return Integer.parseInt(text.substring(text.length() - 1, text.length()));
    }

    @Override
    public Integer visitRegister(Asm8051Parser.RegisterContext ctx) {
        String text = ctx.getText();
        return Integer.parseInt(text.substring(text.length() - 1, text.length()));
    }

    private void moveLocationCounter(int offset) {
        locationCounter = locationCounter.add(new UnsignedInt16(offset));
    }

}
