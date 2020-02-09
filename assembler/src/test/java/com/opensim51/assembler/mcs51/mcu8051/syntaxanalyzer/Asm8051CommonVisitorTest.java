package com.opensim51.assembler.mcs51.mcu8051.syntaxanalyzer;

import com.opensim51.assembler.mcs51.mcu8051.Asm8051Lexer;
import com.opensim51.assembler.mcs51.mcu8051.Asm8051Parser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Asm8051CommonVisitorTest {

    private Asm8051CommonVisitor visitor;

    @Before
    public void setUp() throws Exception {
        visitor = new Asm8051CommonVisitor();
    }

    @Test
    public void visitExpression() {
        Asm8051Parser parser = buildParser("P1.((87+3)/10 AND -1 SHR 0DH)");
        assertEquals(0, (int) visitor.visit(parser.expression()));
    }

    @Test
    public void visitNumber() {
        Asm8051Parser parser = buildParser("10");
        assertEquals(10, (int) visitor.visit(parser.number()));

        parser = buildParser("10b");
        assertEquals(2, (int) visitor.visit(parser.number()));
    }

    @Test
    public void visitHexadecimal() {
    }

    @Test
    public void visitOctal() {
    }

    @Test
    public void visitBinary() {
    }

    @Test
    public void visitIndirectRegister() {
    }

    @Test
    public void visitRegister() {
    }

    @Test
    public void visitSfr() {
    }

    @Test
    public void visitBit() {
    }

    private Asm8051Parser buildParser(String source) {
        CharStream stream = CharStreams.fromString(source);
        Asm8051Lexer lexer = new Asm8051Lexer(stream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        return new Asm8051Parser(tokens);
    }
}