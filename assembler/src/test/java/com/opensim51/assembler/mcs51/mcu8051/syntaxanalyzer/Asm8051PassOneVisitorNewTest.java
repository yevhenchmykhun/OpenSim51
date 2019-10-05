package com.opensim51.assembler.mcs51.mcu8051.syntaxanalyzer;

import com.opensim51.assembler.mcs51.mcu8051.types.Operand;
import com.opensim51.assembler.mcs51.mcu8051.antlr.Asm8051Lexer;
import com.opensim51.assembler.mcs51.mcu8051.antlr.Asm8051Parser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.apache.commons.lang3.StringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class Asm8051PassOneVisitorNewTest {

    private PrintWriter printWriter;

    @Before
    public void setUp() throws Exception {
        printWriter = new PrintWriter("src/test/resources/pass_one_visitor_output.txt");
    }

    @After
    public void tearDown() throws Exception {
        printWriter.flush();
        printWriter.close();
    }

    private class Asm8051PassOneVisitorNewMock extends Asm8051PassOneVisitorNew {

        Asm8051PassOneVisitorNewMock(Map<String, Integer> symbolTable) {
            super(symbolTable);
        }

        @Override
        protected List<Operand> processOperands(ParseTree parseTree, Map<String, Integer> symbolTable) {
            List<Operand> operands = super.processOperands(parseTree, symbolTable);

            List<String> list = new ArrayList<>();
            for (Operand operand : operands) {
                list.add(operand.getType().toString() + ":" + Integer.toHexString(operand.getValue()));
            }
            printWriter.println(parseTree.getChild(0).getText() + " [" + StringUtils.join(list, ", ") + "]");
            printWriter.println();

            return operands;
        }
    }

    @Test
    public void visitMnemonic() {
        URL resource = getClass().getClassLoader().getResource("instructions.txt");
        assert resource != null;

        try (Stream<String> lines = Files.lines(Paths.get(resource.toURI()))) {
            lines.forEach(line -> {
                CharStream stream = CharStreams.fromString(line + "\n");
                Asm8051Lexer lexer = new Asm8051Lexer(stream);
                CommonTokenStream tokens = new CommonTokenStream(lexer);
                Asm8051Parser parser = new Asm8051Parser(tokens);
                ParseTree tree = parser.source();

                printWriter.println(line);

                HashMap<String, Integer> symbolTable = new HashMap<>();
                Asm8051PassOneVisitorNewMock passOneVisitor = new Asm8051PassOneVisitorNewMock(symbolTable);
                passOneVisitor.visit(tree);
            });
        } catch (URISyntaxException | IOException e) {
            // NOP
        }
    }
}