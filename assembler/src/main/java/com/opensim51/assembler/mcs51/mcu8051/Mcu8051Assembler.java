package com.opensim51.assembler.mcs51.mcu8051;

import com.opensim51.assembler.Assembler;
import com.opensim51.assembler.ErrorListener;
import com.opensim51.assembler.TranslationListener;
import com.opensim51.assembler.mcs51.mcu8051.antlr.Asm8051Lexer;
import com.opensim51.assembler.mcs51.mcu8051.antlr.Asm8051Parser;
import com.opensim51.assembler.mcs51.mcu8051.syntaxanalyzer.Asm8051PassOneVisitorNew;
import com.opensim51.assembler.mcs51.mcu8051.syntaxanalyzer.Asm8051PassTwoVisitorNew;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.HashMap;

public final class Mcu8051Assembler implements Assembler {

    @Override
    public void assemble(String source, TranslationListener translationListener, ErrorListener errorListener) {

        CharStream stream = CharStreams.fromString(source);
        Asm8051Lexer lexer = new Asm8051Lexer(stream);

        CommonTokenStream tokens = new CommonTokenStream(lexer);
        Asm8051Parser parser = new Asm8051Parser(tokens);

        ParseTree tree = parser.source();

        BaseErrorListener wrappedErrorListener = wrapErrorListener(errorListener);

        lexer.removeErrorListeners();
        lexer.addErrorListener(wrappedErrorListener);

        parser.removeErrorListeners();
        parser.addErrorListener(wrappedErrorListener);

        HashMap<String, Integer> symbolTable = new HashMap<>();
        Asm8051PassOneVisitorNew passOneVisitor = new Asm8051PassOneVisitorNew(symbolTable);
        passOneVisitor.visit(tree);

        Asm8051PassTwoVisitorNew passTwoVisitor = new Asm8051PassTwoVisitorNew(symbolTable, translationListener);
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
