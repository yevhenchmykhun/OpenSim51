package com.opensim51.assembler;

import java.util.List;

public interface TranslationListener {

    void processInstruction(int line, int locationCounter, List<Integer> machineCodes);

}
