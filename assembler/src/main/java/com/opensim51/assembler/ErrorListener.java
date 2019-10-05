package com.opensim51.assembler;

public interface ErrorListener {
    void processSyntaxError(int line, int charPositionInLine, String message);
}
