package com.opensim51.assembler.mcs51.mcu8051;

import com.opensim51.assembler.Assembler;
import com.opensim51.assembler.ErrorListener;
import com.opensim51.assembler.TranslationListener;
import com.opensim51.assembler.mcs51.mcu8051.syntaxanalyzer.Asm8051PassOneVisitor;
import com.opensim51.assembler.mcs51.mcu8051.syntaxanalyzer.Asm8051PassTwoVisitor;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.HashMap;
import java.util.Map;

public final class Mcu8051Assembler implements Assembler {

    @Override
    public void assemble(String source, TranslationListener translationListener, ErrorListener errorListener) {
        BaseErrorListener wrappedErrorListener = wrapErrorListener(errorListener);

        CharStream stream = CharStreams.fromString(source);
        com.opensim51.assembler.mcs51.mcu8051.Asm8051Lexer lexer = new com.opensim51.assembler.mcs51.mcu8051.Asm8051Lexer(stream);
        lexer.removeErrorListeners();
        lexer.addErrorListener(wrappedErrorListener);

        CommonTokenStream tokens = new CommonTokenStream(lexer);
        com.opensim51.assembler.mcs51.mcu8051.Asm8051Parser parser = new com.opensim51.assembler.mcs51.mcu8051.Asm8051Parser(tokens);
        parser.removeErrorListeners();
        parser.addErrorListener(wrappedErrorListener);

        ParseTree tree = parser.source();

        Map<String, Integer> symbolTable = new HashMap<>();
        Asm8051PassOneVisitor passOneVisitor = new Asm8051PassOneVisitor(symbolTable);
        passOneVisitor.visit(tree);

        Asm8051PassTwoVisitor passTwoVisitor = new Asm8051PassTwoVisitor(symbolTable, translationListener);
        passTwoVisitor.visit(tree);
    }

    private BaseErrorListener wrapErrorListener(ErrorListener errorListener) {
        return new BaseErrorListener() {
            @Override
            public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
                errorListener.processSyntaxError(line, charPositionInLine, msg);
            }
        };
    }

}
