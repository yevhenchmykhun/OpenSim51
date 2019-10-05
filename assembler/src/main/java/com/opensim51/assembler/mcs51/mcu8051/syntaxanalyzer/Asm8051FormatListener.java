package com.opensim51.assembler.mcs51.mcu8051.syntaxanalyzer;

import com.opensim51.assembler.mcs51.mcu8051.antlr.Asm8051BaseListener;
import com.opensim51.assembler.mcs51.mcu8051.antlr.Asm8051Parser;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.List;

public class Asm8051FormatListener extends Asm8051BaseListener {

    private CommonTokenStream tokens;
    private StringBuilder builder;

    public Asm8051FormatListener(CommonTokenStream tokens, StringBuilder builder) {
        this.tokens = tokens;
        this.builder = builder;
    }


    @Override
    public void exitLine(Asm8051Parser.LineContext ctx) {
        builder.append("\n");
    }

    @Override
    public void enterInstruction(Asm8051Parser.InstructionContext ctx) {
        ParseTree child = ctx.getChild(0);
        if (child instanceof Asm8051Parser.LabelContext) {
            builder.append(child.getText());
            builder.append("\t");
        } else {
            builder.append("\t");
        }
    }

    @Override
    public void enterMnemonic(Asm8051Parser.MnemonicContext ctx) {
        ParseTree child = ctx.getChild(0);
        String mnemonic = child.getChild(0).getText();

        builder.append(mnemonic);
        builder.append("\t");
        for (int i = 1; i < child.getChildCount(); i++) {
            ParseTree child1 = child.getChild(i);
            if (child1 instanceof TerminalNode && ((TerminalNode) child1).getSymbol().getType() == Asm8051Parser.COMMA) {
                builder.append(", ");
            } else {
                builder.append(child1.getText());
            }
        }

        builder.append("\t\t\t");
        builder.append(extractComment(ctx));
    }

    private String extractComment(ParserRuleContext ctx) {
        int i = ctx.getStop().getTokenIndex();
        List<Token> commentChannel = tokens.getHiddenTokensToRight(i, 1);
        if (commentChannel != null) {
            Token comment = commentChannel.get(0);
            if (comment != null) {
                return comment.getText();
            }
        }

        return "";
    }
}