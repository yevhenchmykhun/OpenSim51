package com.opensim51.assembler.mcs51.mcu8051.types;

public final class Operand {
    private final OperandType type;
    private final int value;

    public Operand(OperandType type, int value) {
        this.type = type;
        this.value = value;
    }

    public OperandType getType() {
        return type;
    }

    public int getValue() {
        return value;
    }
}
