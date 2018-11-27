package simulator.memory;

import simulator.memory.datatype.UnsignedInt8;

import java.util.HashMap;
import java.util.Map;

public abstract class Memory {

    private int memorySize;
    private Map<Integer, UnsignedInt8> rawMemory;

    protected Memory(int memorySize) {
        this(memorySize, new HashMap<>());
    }

    private Memory(int memorySize, Map<Integer, UnsignedInt8> rawMemory) {
        this.memorySize = memorySize;
        this.rawMemory = rawMemory;
    }

    public Memory.Cell getCell(int address) {
        return new Cell(address);
    }

    public UnsignedInt8 getCellValue(int address) {
        checkRange(0, memorySize - 1, address);

        if (rawMemory.containsKey(address)) {
            return rawMemory.get(address);
        }

        rawMemory.put(address, new UnsignedInt8(0));
        return rawMemory.get(address);
    }

    public void setCellValue(int address, UnsignedInt8 value) {
        checkRange(0, memorySize - 1, address);

        rawMemory.put(address, value);
    }

    protected void checkRange(int lowerBound, int upperBound, int value) {
        if (value < lowerBound || value > upperBound) {
            throw new IllegalArgumentException("Value is out of range");
        }
    }

    public int getMemorySize() {
        return memorySize;
    }

    public interface Bit {

        boolean getValue();

        void setValue(boolean value);

        default void setBit() {
            setValue(true);
        }

        default void clearBit() {
            setValue(false);
        }

    }

    public class Cell {

        protected int address;

        protected Cell(int address) {
            this.address = address;
        }

        public UnsignedInt8 getValue() {
            return getCellValue(address);
        }

        public void setValue(UnsignedInt8 value) {
            setCellValue(address, value);
        }

    }

    public class BitAddressableCell extends Cell {

        protected BitAddressableCell(int address) {
            super(address);
        }

        public boolean getBitValue(int position) {
            checkRange(0, 7, position);

            UnsignedInt8 one = new UnsignedInt8(1);
            return getValue().shiftRight(position).and(one).equals(one);
        }

        public void setBitValue(int position, boolean value) {
            checkRange(0, 7, position);

            if (value) {
                setValue(getValue().setBit(position));
            } else {
                setValue(getValue().clearBit(position));
            }
        }

    }

}
