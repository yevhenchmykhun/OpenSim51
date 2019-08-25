package assembler.syntaxanalyzer;

import org.antlr.v4.runtime.ParserRuleContext;
import simulator.memory.datatype.UInt16;

import java.util.List;

public interface TranslationListener {
    void processInstruction(ParserRuleContext ctx, UInt16 locationCounter, List<Integer> machineCodes);
}
