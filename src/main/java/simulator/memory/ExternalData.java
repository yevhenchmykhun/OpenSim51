package simulator.memory;

import simulator.memory.datatype.UnsignedInt16;
import simulator.memory.datatype.UnsignedInt8;

public class ExternalData extends Memory {

    private static final int MEMORY_SIZE = 0x10000;

    public ExternalData() {
        super(MEMORY_SIZE);
    }

    public UnsignedInt8 getCellValue(UnsignedInt16 address) {
        return super.getCellValue(address.toInt());
    }

    public void setCellValue(UnsignedInt16 address, UnsignedInt8 value) {
        super.setCellValue(address.toInt(), value);
    }

}
