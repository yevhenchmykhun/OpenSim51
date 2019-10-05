package com.opensim51.misc.intelhexparser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Intel8HexParser {

    private static final int HEX = 16;

    private BufferedReader reader;
    private Intel8HexProcessingListener listener;
    private boolean eof;

    public Intel8HexParser(InputStream stream, Intel8HexProcessingListener listener) {
        this.reader = new BufferedReader(new InputStreamReader(stream));
        this.listener = listener;
        eof = false;
    }

    public void parse() throws Intel8HexParserException {
        try {
            String line = reader.readLine();
            while (line != null || !eof) {
                processRecord(processLine(line));
                line = reader.readLine();
            }
        } catch (IOException e) {
            throw new Intel8HexParserException(e);
        }

        if (!eof) {
            throw new Intel8HexParserException("File does not contain EOF line");
        }
    }

    private Record processLine(String line) throws Intel8HexParserException {

        if (!line.startsWith(":")) {
            throw new Intel8HexParserException("Colon is missing");
        }

        byte[] hexRecord = toByteArray(line);

        if (!isChecksumValid(hexRecord)) {
            throw new Intel8HexParserException("Invalid checksum for line -> " + line);
        }

        int byteCount = hexRecord[0];
        if ((byteCount + 5) != hexRecord.length) {
            throw new Intel8HexParserException("Invalid record byteCount for line -> " + line);
        }

        byte[] data = new byte[byteCount];
        System.arraycopy(hexRecord, 4, data, 0, byteCount);

        int address = ((hexRecord[1] & 0xff) << 8) + (hexRecord[2] & 0xff);

        RecordType type = RecordType.fromCode(hexRecord[3] & 0xff);
        if (type == RecordType.UNKNOWN) {
            throw new Intel8HexParserException("Unsupported record type for line -> " + line);
        }

        return new Record(byteCount, address, type, data);
    }

    private byte[] toByteArray(String line) {
        byte[] hexRecord = new byte[line.length() / 2];
        for (int i = 0; i < hexRecord.length; i++) {
            String num = line.substring(2 * i + 1, 2 * i + 3);
            hexRecord[i] = (byte) Integer.parseInt(num, HEX);
        }
        return hexRecord;
    }

    private boolean isChecksumValid(byte[] hexRecord) {
        // sum of all bytes modulo 256, including checksum, should be 0
        int sum = 0;
        for (byte hex : hexRecord) {
            sum += hex & 0xff;
        }
        return (sum & 0xff) == 0;
    }

    private void processRecord(Record record) throws Intel8HexParserException {
        switch (record.getType()) {
            case DATA:
                listener.processDataRecord(record.getAddress(), record.getData());
                break;
            case EOF:
                eof = true;
                break;
        }
    }

}
