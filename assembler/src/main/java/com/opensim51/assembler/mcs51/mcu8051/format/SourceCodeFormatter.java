package com.opensim51.assembler.mcs51.mcu8051.format;

import com.opensim51.assembler.mcs51.mcu8051.antlr.Asm8051Lexer;
import com.opensim51.assembler.mcs51.mcu8051.antlr.Asm8051Parser;
import com.opensim51.assembler.mcs51.mcu8051.syntaxanalyzer.Asm8051FormatListener;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

public class SourceCodeFormatter {

    public static String format(String source) {
        CharStream stream = CharStreams.fromString(source);
        Asm8051Lexer lexer = new Asm8051Lexer(stream);

        CommonTokenStream tokens = new CommonTokenStream(lexer);
        Asm8051Parser parser = new Asm8051Parser(tokens);

        ParseTree tree = parser.source();

        StringBuilder builder = new StringBuilder();
        Asm8051FormatListener listener = new Asm8051FormatListener(tokens, builder);
        ParseTreeWalker.DEFAULT.walk(listener, tree);

        return builder.toString();
    }

}
