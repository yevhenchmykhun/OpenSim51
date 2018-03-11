package assembler.syntaxanalyzer;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import simulator.memory.datatype.UnsignedInt16;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("SuspiciousMethodCalls")
public class Asm8051PassOneVisitor<T> extends Asm8051BaseVisitor<Integer> {

    private static final Map<Class<? extends ParserRuleContext>, Map<Class<? extends ParserRuleContext>, Integer>> SECOND_OPERAND;
    private static final Map<Class<? extends ParserRuleContext>, Integer> BYTES;
    private static final Map<Class<? extends ParserRuleContext>, Integer> BYTES_DIRECT;

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

        SECOND_OPERAND = new HashMap<>();
        SECOND_OPERAND.put(Asm8051Parser.AccumulatorContext.class, BYTES);
        SECOND_OPERAND.put(Asm8051Parser.IndirectRegisterContext.class, BYTES);
        SECOND_OPERAND.put(Asm8051Parser.DirectContext.class, BYTES_DIRECT);
        SECOND_OPERAND.put(Asm8051Parser.RegisterContext.class, BYTES);
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
        } else if (child instanceof Asm8051Parser.MnemonicContext) {
            return visitChildren(ctx);
        }

        return 0;
    }

    @Override
    public Integer visitAjmp(Asm8051Parser.AjmpContext ctx) {
        return 2;
    }

    @Override
    public Integer visitJnz(Asm8051Parser.JnzContext ctx) {
        return 2;
    }

    @Override
    public Integer visitNop(Asm8051Parser.NopContext ctx) {
        return 1;
    }

    @Override
    public Integer visitClr(Asm8051Parser.ClrContext ctx) {
        ParseTree child = ctx.getChild(1);
        if (child instanceof Asm8051Parser.AccumulatorContext) {
            return 1;
        } else if (child instanceof Asm8051Parser.BitContext) {
            if (((Asm8051Parser.BitContext) child).CY() != null) {
                return 1;
            } else {
                return 2;
            }
        }

        return 0;
    }

    @Override
    public Integer visitSubb(Asm8051Parser.SubbContext ctx) {
        ParseTree firstOperand = ctx.getChild(1);
        ParseTree secondOperand = ctx.getChild(3);

        return SECOND_OPERAND.get(firstOperand.getClass()).get(secondOperand.getClass());
    }

    @Override
    public Integer visitMov(Asm8051Parser.MovContext ctx) {
        ParseTree firstOperand = ctx.getChild(1);
        ParseTree secondOperand = ctx.getChild(3);

        return SECOND_OPERAND.get(firstOperand.getClass()).get(secondOperand.getClass());
    }

}
