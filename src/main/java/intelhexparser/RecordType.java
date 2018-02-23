package intelhexparser;

public enum RecordType {

    DATA(0x00),

    EOF(0x01),

    UNKNOWN(0xff);

    int code;

    RecordType(int code) {
        this.code = code;
    }

    public static RecordType fromCode(int code) {
        for (RecordType type : RecordType.values()) {
            if (type.code == code) {
                return type;
            }
        }
        return RecordType.UNKNOWN;
    }

}
