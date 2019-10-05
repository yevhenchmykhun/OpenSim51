package com.opensim51.simulator.memory;

import com.opensim51.simulator.memory.datatype.UInt8;

import java.util.HashMap;
import java.util.Map;

public abstract class Memory {

    private int size;
    private Map<Integer, UInt8> rawMemory;

    protected Memory(int size) {
        this(size, new HashMap<>());
    }

    private Memory(int size, Map<Integer, UInt8> rawMemory) {
        this.size = size;
        this.rawMemory = rawMemory;
    }

    public UInt8 getCellValue(int address) {
        checkRange(size - 1, address);

        return rawMemory.getOrDefault(address, UInt8.ZERO);
    }

    public void setCellValue(int address, UInt8 value) {
        checkRange(size - 1, address);
        rawMemory.put(address, value);
    }

    public Memory.Cell getCell(int address) {
        return new Cell(address);
    }

    public int getSize() {
        return size;
    }

    void checkRange(int upperBound, int value) {
        if (value < 0 || value > upperBound) {
            throw new IllegalArgumentException("Value is out of range");
        }
    }

    public class Cell {

        protected int address;

        Cell(int address) {
            this.address = address;
        }

        public UInt8 getValue() {
            return getCellValue(address);
        }

        public void setValue(UInt8 value) {
            setCellValue(address, value);
        }

    }

}
