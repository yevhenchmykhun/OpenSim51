package com.opensim51.simulator.memory;

public class MemoryUnit {

    private final InternalData internalData;
    private final ExternalData externalData;
    private final ExternalCode externalCode;

    public MemoryUnit() {
        this.internalData = new InternalData();
        this.externalData = new ExternalData();
        this.externalCode = new ExternalCode();
    }

    public InternalData getInternalData() {
        return internalData;
    }

    public ExternalData getExternalData() {
        return externalData;
    }

    public ExternalCode getExternalCode() {
        return externalCode;
    }

    public void reset() {
    }

}
