package assembler;

public enum OperandType {
    REGISTER,
    INDIRECT_REGISTER,
    DIRECT,
    IMMEDIATE,
    ACCUMULATOR,
    BIT,
    NOT_BIT,
    SYMBOL,
    CARRY,
    DPTR,
    AT_DPTR,
    AT_A_PLUS_DPTR,
    AT_A_PLUS_PC,
    UNKNOWN;
}
