package com.opensim51.misc.intelhexparser;

public interface Intel8HexProcessingListener {

    void processDataRecord(int address, byte[] data);

}
