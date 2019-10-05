package com.opensim51.misc.intelhexparser;

class Record {

    private int byteCount;
    private int address;
    private RecordType type;
    private byte[] data;

    Record(int byteCount, int address, RecordType type, byte[] data) {
        this.byteCount = byteCount;
        this.address = address;
        this.type = type;
        this.data = data;
    }

    int getByteCount() {
        return byteCount;
    }

    int getAddress() {
        return address;
    }

    RecordType getType() {
        return type;
    }

    byte[] getData() {
        return data;
    }

}
