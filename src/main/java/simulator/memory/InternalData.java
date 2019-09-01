package simulator.memory;

import simulator.memory.datatype.UInt8;

public class InternalData extends Memory {

    private static final int MEMORY_SIZE = 0x100;

    // register bank 0
    public final Cell R0 = new RegisterCell(0x0);
    public final Cell R1 = new RegisterCell(0x1);
    public final Cell R2 = new RegisterCell(0x2);
    public final Cell R3 = new RegisterCell(0x3);
    public final Cell R4 = new RegisterCell(0x4);
    public final Cell R5 = new RegisterCell(0x5);
    public final Cell R6 = new RegisterCell(0x6);
    public final Cell R7 = new RegisterCell(0x7);

    // I/O port SFRs
    public final BitAddressableCell P0 = new BitAddressableCell(0x80);
    public final BitAddressableCell P1 = new BitAddressableCell(0x90);
    public final BitAddressableCell P2 = new BitAddressableCell(0xa0);
    public final BitAddressableCell P3 = new BitAddressableCell(0xb0);

    // control SFRs
    public final Cell SP = new Cell(0x81);
    public final Cell DPL = new Cell(0x82);
    public final Cell DPH = new Cell(0x83);
    public final Cell TL0 = new Cell(0x8a);
    public final Cell TL1 = new Cell(0x8b);
    public final Cell TH0 = new Cell(0x8c);
    public final Cell TH1 = new Cell(0x8d);
    public final Cell SBUF = new Cell(0x99);
    public final BitAddressableCell ACC = new BitAddressableCell(0xe0);
    public final BitAddressableCell B = new BitAddressableCell(0xf0);

    // other SFRs
    public final BitAddressableCell PCON = new BitAddressableCell(0x87);
    public final BitAddressableCell TCON = new BitAddressableCell(0x88);
    public final BitAddressableCell TMOD = new BitAddressableCell(0x89);
    public final BitAddressableCell SCON = new BitAddressableCell(0x98);
    public final BitAddressableCell IE = new BitAddressableCell(0xa8);
    public final BitAddressableCell IP = new BitAddressableCell(0xb8);
    public final BitAddressableCell PSW = new BitAddressableCell(0xd0);

    public final BitField bitField = new BitField();

    public final Stack stack = new Stack();

    public InternalData() {
        super(MEMORY_SIZE);

        P1.setValue(UInt8.MAX_VALUE);
    }

    public UInt8 getCellValue(UInt8 address) {
        return super.getCellValue(address.toInt());
    }

    public void setCellValue(UInt8 address, UInt8 value) {
        setCellValue(address.toInt(), value);
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

    public class BitField {

        // Power Control bits
        public final Bit IDL = new NamedBit(PCON, 0);
        public final Bit PD = new NamedBit(PCON, 1);
        public final Bit GF0 = new NamedBit(PCON, 2);
        public final Bit GF1 = new NamedBit(PCON, 3);
        public final Bit SMOD = new NamedBit(PCON, 7);
        // Timer Control bits
        public final Bit IT0 = new NamedBit(TCON, 0);
        public final Bit IE0 = new NamedBit(TCON, 1);
        public final Bit IT1 = new NamedBit(TCON, 2);
        public final Bit IE1 = new NamedBit(TCON, 3);
        public final Bit TR0 = new NamedBit(TCON, 4);
        public final Bit TF0 = new NamedBit(TCON, 5);
        public final Bit TR1 = new NamedBit(TCON, 6);
        public final Bit TF1 = new NamedBit(TCON, 7);
        // Timer Mode bits
        public final Bit T0M0 = new NamedBit(TMOD, 0);
        public final Bit T0M1 = new NamedBit(TMOD, 1);
        public final Bit C_T0 = new NamedBit(TMOD, 2);
        public final Bit GATE0 = new NamedBit(TMOD, 3);
        public final Bit T1M0 = new NamedBit(TMOD, 4);
        public final Bit T1M1 = new NamedBit(TMOD, 5);
        public final Bit C_T1 = new NamedBit(TMOD, 6);
        public final Bit GATE1 = new NamedBit(TMOD, 7);
        // Serial Control bits
        public final Bit RI = new NamedBit(SCON, 0);
        public final Bit TI = new NamedBit(SCON, 1);
        public final Bit RB8 = new NamedBit(SCON, 2);
        public final Bit TB8 = new NamedBit(SCON, 3);
        public final Bit REN = new NamedBit(SCON, 4);
        public final Bit SM2 = new NamedBit(SCON, 5);
        public final Bit SM1 = new NamedBit(SCON, 6);
        public final Bit SM0 = new NamedBit(SCON, 7);
        // Interrupt Enable bits
        public final Bit EX0 = new NamedBit(IE, 0);
        public final Bit ET0 = new NamedBit(IE, 1);
        public final Bit EX1 = new NamedBit(IE, 2);
        public final Bit ET1 = new NamedBit(IE, 3);
        public final Bit ES = new NamedBit(IE, 4);
        public final Bit ET2 = new NamedBit(IE, 5);
        public final Bit EA = new NamedBit(IE, 7);
        // Interrupt Priority bits
        public final Bit PX0 = new NamedBit(IP, 0);
        public final Bit PT0 = new NamedBit(IP, 1);
        public final Bit PX1 = new NamedBit(IP, 2);
        public final Bit PT1 = new NamedBit(IP, 3);
        public final Bit PS = new NamedBit(IP, 4);
        public final Bit PT2 = new NamedBit(IP, 5);
        // PSW bits
        public final Bit P = new NamedBit(PSW, 0);
        public final Bit UD = new NamedBit(PSW, 1);
        public final Bit OV = new NamedBit(PSW, 2);
        public final Bit RS0 = new NamedBit(PSW, 3);
        public final Bit RS1 = new NamedBit(PSW, 4);
        public final Bit F0 = new NamedBit(PSW, 5);
        public final Bit AC = new NamedBit(PSW, 6);
        public final Bit CY = new NamedBit(PSW, 7);

        public Bit getBit(int bitAddress) {
            return new UnnamedBit(bitAddress);
        }

        public void setBitValue(int bitAddress, boolean value) {
            new UnnamedBit(bitAddress).setValue(value);
        }

        public boolean getBitValue(int bitAddress) {
            return new UnnamedBit(bitAddress).getValue();
        }
    }

    public class BitAddressableCell extends Cell {

        BitAddressableCell(int address) {
            super(address);
        }

        public boolean getBitValue(int position) {
            checkRange(7, position);

            UInt8 one = new UInt8(1);
            return getValue().shiftRight(position).and(one).equals(one);
        }

        public void setBitValue(int position, boolean value) {
            checkRange(7, position);

            if (value) {
                setValue(getValue().setBit(position));
            } else {
                setValue(getValue().clearBit(position));
            }
        }

    }

    public class Stack {

        public void push(UInt8 value) {
            UInt8 sp = SP.getValue().inc();
            SP.setValue(sp);
            setCellValue(sp, value);
        }

        public UInt8 pop() {
            return null;
        }

    }

    private class RegisterCell extends Cell {

        RegisterCell(int address) {
            super(address);
        }

        @Override
        public UInt8 getValue() {
            determineRegisterBank();
            return super.getValue();
        }

        @Override
        public void setValue(UInt8 value) {
            determineRegisterBank();
            super.setValue(value);
        }

        private void determineRegisterBank() {
            boolean rs1 = bitField.RS1.getValue();
            boolean rs0 = bitField.RS0.getValue();
            if (!rs1 && !rs0) {
                address = scale(address, 0x0);
            } else if (!rs1) {
                address = scale(address, 0x8);
            } else if (!rs0) {
                address = scale(address, 0x10);
            } else {
                address = scale(address, 0x18);
            }
        }

        private int scale(int address, int offset) {
            return (address % 8) + offset;
        }

    }

    private class NamedBit implements Bit {

        private final BitAddressableCell cell;
        private final int position;

        NamedBit(BitAddressableCell cell, int position) {
            this.cell = cell;
            this.position = position;
        }

        @Override
        public boolean getValue() {
            return cell.getBitValue(position);
        }

        @Override
        public void setValue(boolean value) {
            cell.setBitValue(position, value);
        }

    }

    private class UnnamedBit implements Bit {

        private final int bitAddress;

        UnnamedBit(int address) {
            if (!isBitAddress(address)) {
                throw new IllegalArgumentException("Value is out of range");
            }

            this.bitAddress = address;
        }

        @Override
        public boolean getValue() {
            int cellAddress = determineCellAddress();

            BitAddressableCell cell = new BitAddressableCell(cellAddress);
            return cell.getBitValue(bitAddress % 0x8);
        }

        @Override
        public void setValue(boolean value) {
            int cellAddress = determineCellAddress();

            BitAddressableCell cell = new BitAddressableCell(cellAddress);
            cell.setBitValue(bitAddress % 0x8, value);
        }

        private int determineCellAddress() {
            int cellAddress = (bitAddress - (bitAddress % 0x8));
            return bitAddress <= 0x7f ? cellAddress + 0x20 : cellAddress;
        }

        private boolean isBitAddress(int address) {
            return address >= 0x0 && address <= 0x7f || (address - (address % 0x8)) % 0x8 == 0;
        }

    }

}
