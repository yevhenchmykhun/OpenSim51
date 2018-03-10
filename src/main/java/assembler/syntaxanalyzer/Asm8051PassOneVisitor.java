package assembler.syntaxanalyzer;

import org.antlr.v4.runtime.tree.ParseTree;
import simulator.memory.datatype.UnsignedInt16;

import java.util.Map;

public class Asm8051PassOneVisitor<T> extends Asm8051BaseVisitor<Integer> {

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
        ParseTree firstArgument = ctx.getChild(1);
        if (firstArgument instanceof Asm8051Parser.AccumulatorContext) {
            return 1;
        } else if (firstArgument instanceof Asm8051Parser.BitContext) {
            if (((Asm8051Parser.BitContext) firstArgument).CY() != null) {
                return 1;
            } else {
                return 2;
            }
        }

        return 0;
    }

    @Override
    public Integer visitSubb(Asm8051Parser.SubbContext ctx) {
        ParseTree secondArgument = ctx.getChild(3);
        if (secondArgument instanceof Asm8051Parser.ImmediateContext) {
            return 2;
        } else if (secondArgument instanceof Asm8051Parser.IndirectRegisterContext) {
            return 1;
        } else if (secondArgument instanceof Asm8051Parser.DirectContext) {
            return 2;
        } else if (secondArgument instanceof Asm8051Parser.RegisterContext) {
            return 1;
        }

        return 0;
    }

    @Override
    public Integer visitMov(Asm8051Parser.MovContext ctx) {
        ParseTree firstArgument = ctx.getChild(1);
        ParseTree secondArgument = ctx.getChild(3);
        if (firstArgument instanceof Asm8051Parser.IndirectRegisterContext) {

            if (secondArgument instanceof Asm8051Parser.ImmediateContext) {
                return 2;
            } else if (secondArgument instanceof Asm8051Parser.AccumulatorContext) {
                return 1;
            } else if (secondArgument instanceof Asm8051Parser.DirectContext) {
                return 2;
            }

        } else if (firstArgument instanceof Asm8051Parser.AccumulatorContext) {

            if (secondArgument instanceof Asm8051Parser.ImmediateContext) {
                return 2;
            } else if (secondArgument instanceof Asm8051Parser.IndirectRegisterContext) {
                return 1;
            } else if (secondArgument instanceof Asm8051Parser.DirectContext) {
                return 2;
            } else if (secondArgument instanceof Asm8051Parser.RegisterContext) {
                return 1;
            }

        } else if (firstArgument instanceof Asm8051Parser.DirectContext) {

            if (secondArgument instanceof Asm8051Parser.DirectContext) {
                return 3;
            } else if (secondArgument instanceof Asm8051Parser.ImmediateContext) {
                return 3;
            } else if (secondArgument instanceof Asm8051Parser.IndirectRegisterContext) {
                return 2;
            } else if (secondArgument instanceof Asm8051Parser.AccumulatorContext) {
                return 2;
            } else if (secondArgument instanceof Asm8051Parser.RegisterContext) {
                return 2;
            }

        } else if (firstArgument instanceof Asm8051Parser.RegisterContext) {

            if (secondArgument instanceof Asm8051Parser.ImmediateContext) {
                return 2;
            } else if (secondArgument instanceof Asm8051Parser.AccumulatorContext) {
                return 1;
            } else if (secondArgument instanceof Asm8051Parser.DirectContext) {
                return 2;
            }

        }

        return 0;
    }

}
