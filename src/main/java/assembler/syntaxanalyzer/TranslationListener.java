package assembler.syntaxanalyzer;

import org.antlr.v4.runtime.ParserRuleContext;
import simulator.memory.datatype.UnsignedInt16;

import java.util.List;

public interface TranslationListener {
    void processInstruction(ParserRuleContext ctx, UnsignedInt16 locationCounter, List<Integer> machineCodes);
}
