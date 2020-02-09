package com.opensim51.assembler;

public interface Assembler {

    void assemble(String source, TranslationListener translationListener, ErrorListener errorListener);

}
