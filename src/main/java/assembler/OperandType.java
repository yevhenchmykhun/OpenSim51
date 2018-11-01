package assembler;

public enum OperandType {
    REGISTER,
    INDIRECT_REGISTER,
    DIRECT,
    IMMEDIATE,
    ACCUMULATOR,
    BIT,
    SYMBOL,
    CARRY,
    DPTR,
    UNKNOWN;
}
