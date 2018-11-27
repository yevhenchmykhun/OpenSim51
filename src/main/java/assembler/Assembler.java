package assembler;

import assembler.antlr.Asm8051Lexer;
import assembler.antlr.Asm8051Parser;
import assembler.syntaxanalyzer.Asm8051PassOneVisitorNew;
import assembler.syntaxanalyzer.Asm8051PassTwoVisitorNew;
import assembler.syntaxanalyzer.TranslationListener;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ConsoleErrorListener;
import org.antlr.v4.runtime.tree.ParseTree;
import simulator.memory.datatype.UnsignedInt16;

import java.util.HashMap;

public final class Assembler {

    private Asm8051Lexer lexer;
    private Asm8051Parser parser;
    private ParseTree tree;

    public Assembler(String source) {
        CharStream stream = CharStreams.fromString(source);
        lexer = new Asm8051Lexer(stream);

        CommonTokenStream tokens = new CommonTokenStream(lexer);
        parser = new Asm8051Parser(tokens);

        tree = parser.source();
    }

    public void assemble(TranslationListener translationListener) {
        assemble(translationListener, new ConsoleErrorListener());
    }

    public void assemble(TranslationListener translationListener, BaseErrorListener errorListener) {
        lexer.removeErrorListeners();
        lexer.addErrorListener(errorListener);

        parser.removeErrorListeners();
        parser.addErrorListener(errorListener);

        HashMap<String, UnsignedInt16> symbolTable = new HashMap<>();
        Asm8051PassOneVisitorNew passOneVisitor = new Asm8051PassOneVisitorNew(symbolTable);
        passOneVisitor.visit(tree);

        Asm8051PassTwoVisitorNew passTwoVisitor = new Asm8051PassTwoVisitorNew(symbolTable, translationListener);
        passTwoVisitor.visit(tree);
    }

}
