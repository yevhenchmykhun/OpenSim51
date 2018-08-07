package assembler.syntaxanalyzer;

import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import org.apache.commons.lang3.StringUtils;
import simulator.memory.datatype.UnsignedInt16;

import java.util.HashMap;
import java.util.Map;

public class Asm8051PassOneVisitor extends Asm8051BaseVisitor<Integer> {

    private static final Map<Class, Integer> BYTES;
    private static final Map<Class, Integer> BYTES_DIRECT;
    private static final Map<Class, Map<Class, Integer>> BYTES_BY_OPERANDS;
    private static final Map<Class, Integer> BYTES_BY_INSTRUCTION;

    static {
        BYTES = new HashMap<>();
        BYTES.put(Asm8051Parser.AccumulatorContext.class, 1);
        BYTES.put(Asm8051Parser.ImmediateContext.class, 2);
        BYTES.put(Asm8051Parser.IndirectRegisterContext.class, 1);
        BYTES.put(Asm8051Parser.DirectContext.class, 2);
        BYTES.put(Asm8051Parser.RegisterContext.class, 1);

        BYTES_DIRECT = new HashMap<>();
        BYTES_DIRECT.put(Asm8051Parser.AccumulatorContext.class, 2);
        BYTES_DIRECT.put(Asm8051Parser.ImmediateContext.class, 3);
        BYTES_DIRECT.put(Asm8051Parser.IndirectRegisterContext.class, 2);
        BYTES_DIRECT.put(Asm8051Parser.DirectContext.class, 3);
        BYTES_DIRECT.put(Asm8051Parser.RegisterContext.class, 2);

        BYTES_BY_OPERANDS = new HashMap<>();
        BYTES_BY_OPERANDS.put(Asm8051Parser.AccumulatorContext.class, BYTES);
        BYTES_BY_OPERANDS.put(Asm8051Parser.IndirectRegisterContext.class, BYTES);
        BYTES_BY_OPERANDS.put(Asm8051Parser.DirectContext.class, BYTES_DIRECT);
        BYTES_BY_OPERANDS.put(Asm8051Parser.RegisterContext.class, BYTES);

        BYTES_BY_INSTRUCTION = new HashMap<>();
        BYTES_BY_INSTRUCTION.put(Asm8051Parser.NopContext.class, 1);
        BYTES_BY_INSTRUCTION.put(Asm8051Parser.AjmpContext.class, 2);
        BYTES_BY_INSTRUCTION.put(Asm8051Parser.LjmpContext.class, 3);
        BYTES_BY_INSTRUCTION.put(Asm8051Parser.RrContext.class, 1);
        BYTES_BY_INSTRUCTION.put(Asm8051Parser.JbcContext.class, 3);
        BYTES_BY_INSTRUCTION.put(Asm8051Parser.AcallContext.class, 2);
        BYTES_BY_INSTRUCTION.put(Asm8051Parser.LcallContext.class, 3);
        BYTES_BY_INSTRUCTION.put(Asm8051Parser.RrcContext.class, 1);
        BYTES_BY_INSTRUCTION.put(Asm8051Parser.JbContext.class, 3);
        BYTES_BY_INSTRUCTION.put(Asm8051Parser.RetContext.class, 1);
        BYTES_BY_INSTRUCTION.put(Asm8051Parser.RlContext.class, 1);
        BYTES_BY_INSTRUCTION.put(Asm8051Parser.JnbContext.class, 3);
        BYTES_BY_INSTRUCTION.put(Asm8051Parser.RetiContext.class, 1);
        BYTES_BY_INSTRUCTION.put(Asm8051Parser.RlcContext.class, 1);
        BYTES_BY_INSTRUCTION.put(Asm8051Parser.JcContext.class, 2);
        BYTES_BY_INSTRUCTION.put(Asm8051Parser.JncContext.class, 2);
        BYTES_BY_INSTRUCTION.put(Asm8051Parser.JzContext.class, 2);
        BYTES_BY_INSTRUCTION.put(Asm8051Parser.JnzContext.class, 2);
        BYTES_BY_INSTRUCTION.put(Asm8051Parser.JmpContext.class, 1);
        BYTES_BY_INSTRUCTION.put(Asm8051Parser.SjmpContext.class, 2);
        BYTES_BY_INSTRUCTION.put(Asm8051Parser.MovcContext.class, 1);
        BYTES_BY_INSTRUCTION.put(Asm8051Parser.DivContext.class, 1);
        BYTES_BY_INSTRUCTION.put(Asm8051Parser.MulContext.class, 1);
        BYTES_BY_INSTRUCTION.put(Asm8051Parser.CjneContext.class, 3);
        BYTES_BY_INSTRUCTION.put(Asm8051Parser.PushContext.class, 2);
        BYTES_BY_INSTRUCTION.put(Asm8051Parser.SwapContext.class, 1);
        BYTES_BY_INSTRUCTION.put(Asm8051Parser.PopContext.class, 2);
        BYTES_BY_INSTRUCTION.put(Asm8051Parser.DaContext.class, 1);
        BYTES_BY_INSTRUCTION.put(Asm8051Parser.XchdContext.class, 1);
        BYTES_BY_INSTRUCTION.put(Asm8051Parser.MovxContext.class, 1);
    }

    private final Map<String, UnsignedInt16> symbolTable;
    private UnsignedInt16 locationCounter;

    public Asm8051PassOneVisitor(Map<String, UnsignedInt16> symbolTable) {
        this.symbolTable = symbolTable;
        locationCounter = new UnsignedInt16(0);
    }

    @Override
    public Integer visitLine(Asm8051Parser.LineContext ctx) {
        ParseTree child = ctx.getChild(0);
        if (child instanceof Asm8051Parser.LblContext) {
            symbolTable.put(((Asm8051Parser.LblContext) child).label().getText(), locationCounter);
        } else if (child instanceof Asm8051Parser.InstructionContext) {
            Integer offset = visitInstruction((Asm8051Parser.InstructionContext) child);
            locationCounter = locationCounter.add(new UnsignedInt16(offset));
        }

        return 0;
    }

    @Override
    public Integer visitInstruction(Asm8051Parser.InstructionContext ctx) {
        ParseTree child = ctx.getChild(0);
        if (child instanceof Asm8051Parser.LblContext) {
            symbolTable.put(((Asm8051Parser.LblContext) child).label().getText(), locationCounter);
            return visitMnemonic(((Asm8051Parser.MnemonicContext) ctx.getChild(1)));
        } else {
            return visitMnemonic(((Asm8051Parser.MnemonicContext) child));
        }
    }

    @Override
    public Integer visitMnemonic(Asm8051Parser.MnemonicContext ctx) {
        Class<? extends ParseTree> contextClass = ctx.getChild(0).getClass();
        return BYTES_BY_INSTRUCTION.containsKey(contextClass) ? BYTES_BY_INSTRUCTION.get(contextClass) : super.visitMnemonic(ctx);
    }

    @Override
    public Integer visitInc(Asm8051Parser.IncContext ctx) {
        ParseTree firstOperand = ctx.getChild(1);
        return firstOperand instanceof Asm8051Parser.DirectContext ? 2 : 1;
    }

    @Override
    public Integer visitDec(Asm8051Parser.DecContext ctx) {
        ParseTree firstOperand = ctx.getChild(1);
        return firstOperand instanceof Asm8051Parser.DirectContext ? 2 : 1;
    }

    @Override
    public Integer visitAdd(Asm8051Parser.AddContext ctx) {
        return determineBytesByOperands(ctx);
    }

    @Override
    public Integer visitAddc(Asm8051Parser.AddcContext ctx) {
        return determineBytesByOperands(ctx);
    }

    @Override
    public Integer visitOrl(Asm8051Parser.OrlContext ctx) {
        return determineBytesForOrlAndAnl(ctx);
    }

    @Override
    public Integer visitAnl(Asm8051Parser.AnlContext ctx) {
        return determineBytesForOrlAndAnl(ctx);
    }

    @Override
    public Integer visitXrl(Asm8051Parser.XrlContext ctx) {
        return determineBytesByOperands(ctx);
    }

    @Override
    public Integer visitMov(Asm8051Parser.MovContext ctx) {
        ParseTree firstOperand = ctx.getChild(1);
        if (firstOperand instanceof Asm8051Parser.BitContext) {
            return 2;
        } else if (StringUtils.equalsIgnoreCase(firstOperand.getText(), "dptr")) {
            return 3;
        } else {
            ParseTree secondOperand = ctx.getChild(3);
            return BYTES_BY_OPERANDS.get(firstOperand.getClass()).get(secondOperand.getClass());
        }
    }

    @Override
    public Integer visitSubb(Asm8051Parser.SubbContext ctx) {
        return determineBytesByOperands(ctx);
    }

    @Override
    public Integer visitCpl(Asm8051Parser.CplContext ctx) {
        return determineBytesForCplAndClr(ctx);
    }

    @Override
    public Integer visitClr(Asm8051Parser.ClrContext ctx) {
        return determineBytesForCplAndClr(ctx);
    }

    @Override
    public Integer visitXch(Asm8051Parser.XchContext ctx) {
        return determineBytesByOperands(ctx);
    }

    @Override
    public Integer visitSetb(Asm8051Parser.SetbContext ctx) {
        ParseTree firstOperand = ctx.getChild(1);
        return ((Asm8051Parser.BitContext) firstOperand).CY() != null ? 1 : 2;
    }

    @Override
    public Integer visitDjnz(Asm8051Parser.DjnzContext ctx) {
        ParseTree firstOperand = ctx.getChild(1);
        return firstOperand instanceof Asm8051Parser.DirectContext ? 3 : 2;
    }

    private Integer determineBytesByOperands(RuleContext ctx) {
        ParseTree firstOperand = ctx.getChild(1);
        ParseTree secondOperand = ctx.getChild(3);

        return BYTES_BY_OPERANDS.get(firstOperand.getClass()).get(secondOperand.getClass());
    }

    private Integer determineBytesForOrlAndAnl(RuleContext ctx) {
        ParseTree firstOperand = ctx.getChild(1);
        if (firstOperand instanceof Asm8051Parser.BitContext) {
            return 2;
        } else {
            ParseTree secondOperand = ctx.getChild(3);
            return BYTES_BY_OPERANDS.get(firstOperand.getClass()).get(secondOperand.getClass());
        }
    }

    private Integer determineBytesForCplAndClr(RuleContext ctx) {
        ParseTree firstOperand = ctx.getChild(1);
        return firstOperand instanceof Asm8051Parser.AccumulatorContext
                || ((Asm8051Parser.BitContext) firstOperand).CY() != null ? 1 : 2;
    }

}
