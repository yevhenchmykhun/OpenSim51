package com.opensim51.simulator.sys;

import com.opensim51.simulator.memory.InternalData;
import com.opensim51.simulator.memory.InternalData.Bit;
import com.opensim51.simulator.memory.datatype.UInt16;

public class InterruptSystem {

    private static final int[] VECTOR = new int[]{0x0, 0x3, 0xb, 0x13, 0x1b, 0x23};

    private final InternalData internalData;
    private final InternalData.BitField bitField;
    private boolean previousInt0;
    private boolean previousInt1;
    private int interrupLevel = -1;

    public InterruptSystem(InternalData internalData) {
        this.internalData = internalData;
        this.bitField = internalData.bitField;
    }

    public UInt16 getInterruptServiceRoutine(UInt16 pc) {
        if (isAllEnabled() && interrupLevel != 1) {
            for (int interruptSource = 0; interruptSource <= 4; interruptSource++) {
                if (isEnabled(interruptSource) && isHighPriority(interruptSource) && isFlagSet(interruptSource)) {

                    // TODO: fix
//                    if (memory.getInterruptLevel() == 0) {
//                        memory.setIsrNested(true);
//                    }

                    interrupLevel = 1;
                    clearFlag(interruptSource);
                    internalData.stack.push(pc);
                    return getVector(interruptSource);
                }
            }

            if (interrupLevel == 0) {
                return pc;
            } else {
                for (int interruptSource = 0; interruptSource <= 4; interruptSource++) {
                    if (isEnabled(interruptSource) && isFlagSet(interruptSource)) {
                        interrupLevel = 0;
                        clearFlag(interruptSource);
                        internalData.stack.push(pc);
                        return getVector(interruptSource);
                    }
                }

                return pc;
            }
        } else {
            return pc;
        }
    }

    public void pollInterrupt0() {
        previousInt0 = pollInterrupt(previousInt0, bitField.IT0, internalData.P3.getBit(2));
    }

    public void pollInterrupt1() {
        previousInt1 = pollInterrupt(previousInt1, bitField.IT1, internalData.P3.getBit(3));
    }

    private boolean pollInterrupt(boolean previousInt, Bit it, Bit p3_x) {
        boolean edgeTriggered = it.getValue();
        if (edgeTriggered) {
            if (previousInt && !p3_x.getValue()) {
                it.setBit();
            }
        } else if (!p3_x.getValue()) {
            it.setBit();
        }

        return !p3_x.getValue();
    }

    private boolean isFlagSet(int interruptSource) {
        switch (interruptSource) {
            case 0:
                return bitField.IE0.getValue();
            case 1:
                return bitField.TF0.getValue();
            case 2:
                return bitField.IE1.getValue();
            case 3:
                return bitField.TF1.getValue();
            case 4:
                return bitField.TI.getValue() || bitField.RI.getValue();

            // it will never happen
            default:
                return false;
        }
    }

    private void clearFlag(int interruptSource) {
        if (interruptSource == 1) {
            bitField.TF0.clearBit();
        } else if (interruptSource == 3) {
            bitField.TF1.clearBit();
        } else if (interruptSource == 0 && bitField.IT0.getValue()) {
            bitField.IE0.clearBit();
        } else if (interruptSource == 2 && bitField.IT1.getValue()) {
            bitField.IE1.clearBit();
        }
    }

    private boolean isHighPriority(int interruptSource) {
        return internalData.IP.getBitValue(interruptSource);
    }

    private boolean isEnabled(int interruptSource) {
        return internalData.IE.getBitValue(interruptSource);
    }

    private boolean isAllEnabled() {
        return bitField.EA.getValue();
    }

    private UInt16 getVector(int interruptSource) {
        return new UInt16(VECTOR[interruptSource + 1]);
    }

}
