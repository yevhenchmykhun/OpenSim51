package gui.editorstyles;

import org.apache.commons.lang3.StringUtils;
import org.fxmisc.richtext.model.StyleSpans;
import org.fxmisc.richtext.model.StyleSpansBuilder;

import java.util.Collection;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TokensHighlighting {

    private static final String[] MNEMONICS = new String[]{
            "NOP", "AJMP", "LJMP", "RR", "INC", "JBC", "ACALL", "LCALL",
            "RRC", "DEC", "JB", "RET", "RL", "ADD", "JNB", "RETI", "RLC",
            "ADDC", "JC", "ORL", "JNC", "ANL", "JZ", "XRL", "JNZ", "JMP",
            "MOV", "SJMP", "MOVC", "DIV", "SUBB", "MUL", "CPL", "CJNE",
            "PUSH", "CLR", "SWAP", "XCH", "POP", "SETB", "DA", "DJNZ",
            "XCHD", "MOVX"
    };

    private static final String[] BITS = new String[]{
            "(P[0-3]|ACC|B)\\.[0-7]",
            "IT0", "IE0", "IT1", "IE1", "TR0", "TF0", "TR1", "TF1",
            "RI", "TI", "RB8", "TB8", "REN", "SM2", "SM1", "SM0",
            "EX0", "ET0", "EX1", "ET1", "ES", "ET2", "EA",
            "PX0", "PT0", "PX1", "PT1", "PS", "PT2",
            "P", "UD", "OV", "RS0", "RS1", "F0", "AC", "C",
    };

    private static final String[] REGISTERS = new String[]{
            "P0", "SP", "DPL", "DPH", "PCON", "TCON", "TMOD", "TL0",
            "TL1", "TH0", "TH1", "P1", "SCON", "SBUF", "P2", "IE", "P3",
            "IP", "PSW", "ACC", "A", "B", "R[0-7]"
    };

    private static final String MNEMONIC_PATTERN = "(?i)\\b(" + StringUtils.join(MNEMONICS, "|") + ")\\b";
    private static final String BIT_PATTERN = "(?i)\\b(" + StringUtils.join(BITS, "|") + ")\\b";
    private static final String REGISTER_PATTERN = "(?i)\\b(" + StringUtils.join(REGISTERS, "|") + ")\\b";
    private static final String PAREN_PATTERN = "[()]";
    private static final String STRING_PATTERN = "'([^'\\\\]|\\\\.)*'";
    private static final String COMMENT_PATTERN = ";[^\n]*";

    private static final Pattern PATTERN = Pattern.compile(
            "(?<MNEMONIC>" + MNEMONIC_PATTERN + ")"
                    + "|(?<BIT>" + BIT_PATTERN + ")"
                    + "|(?<REGISTER>" + REGISTER_PATTERN + ")"
                    + "|(?<PAREN>" + PAREN_PATTERN + ")"
                    + "|(?<STRING>" + STRING_PATTERN + ")"
                    + "|(?<COMMENT>" + COMMENT_PATTERN + ")"
    );

    public static StyleSpans<Collection<String>> computeHighlighting(String text) {
        Matcher matcher = PATTERN.matcher(text);
        int lastKwEnd = 0;
        StyleSpansBuilder<Collection<String>> spansBuilder
                = new StyleSpansBuilder<>();
        while (matcher.find()) {
            String styleClass;
            if (matcher.group("MNEMONIC") != null) {
                styleClass = "mnemonic";
            } else if (matcher.group("REGISTER") != null) {
                styleClass = "register";
            } else if (matcher.group("BIT") != null) {
                styleClass = "bit";
            } else if (matcher.group("PAREN") != null) {
                styleClass = "paren";
            } else if (matcher.group("STRING") != null) {
                styleClass = "string";
            } else if (matcher.group("COMMENT") != null) {
                styleClass = "comment";
            } else {
                styleClass = null;
            }

            assert styleClass != null;
            spansBuilder.add(Collections.emptyList(), matcher.start() - lastKwEnd);
            spansBuilder.add(Collections.singleton(styleClass), matcher.end() - matcher.start());
            lastKwEnd = matcher.end();
        }
        spansBuilder.add(Collections.emptyList(), text.length() - lastKwEnd);
        return spansBuilder.create();
    }

}
