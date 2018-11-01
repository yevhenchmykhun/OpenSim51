package assembler;

public class Operand {
    private OperandType type;
    private int value;

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
