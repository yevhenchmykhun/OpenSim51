package simulator.memory;

import simulator.memory.datatype.UInt16;
import simulator.memory.datatype.UInt8;

public class ExternalCode extends Memory {

    private static final int MEMORY_SIZE = 0x10000;

    public ExternalCode() {
        super(MEMORY_SIZE);
    }

    public UInt8 getCellValue(UInt16 address) {
        return super.getCellValue(address.toInt());
    }

    public void setCellValue(UInt16 address, UInt8 value) {
        super.setCellValue(address.toInt(), value);
    }

}
