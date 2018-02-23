package intelhexparser;

public interface Intel8HexProcessingListener {

    void processDataRecord(int address, byte[] data);

}
