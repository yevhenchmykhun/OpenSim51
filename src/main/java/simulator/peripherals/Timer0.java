package simulator.peripherals;

import simulator.memory.InternalData;
import simulator.memory.datatype.UnsignedInt8;

public class Timer0 {

    private InternalData internalData;
    private InternalData.BitMap bitMap;
    private boolean t0;

    public Timer0(InternalData internalData) {
        this.internalData = internalData;
        this.bitMap = internalData.bitMap;
    }

    public int getMode() {
        return internalData.TMOD.getValue().toInt() & 3;
    }

    public boolean isRunning() {
        return bitMap.TR0.getValue();
    }

    private boolean isCounter() {
        return internalData.TMOD.getBitValue(2);
    }

    private void updateT0() {
        t0 = internalData.P3.getBitValue(4);
    }

    public void step() {
        boolean tr0 = bitMap.TR0.getValue();
        boolean gate = bitMap.GATE0.getValue();
        boolean int0 = internalData.P3.getBitValue(2);

        if (tr0 && (!gate || int0)) {
            boolean previousT0 = t0;
            updateT0();

            UnsignedInt8 th0 = internalData.TH0.getValue();
            UnsignedInt8 tl0 = internalData.TL0.getValue();

            UnsignedInt8 newTh0 = th0;
            UnsignedInt8 newTl0 = tl0;

            boolean tf0 = false;
            boolean tf1 = false;

            if (getMode() == 3) {
                newTh0 = th0.inc();
                if (th0.isOverflowOccurred()) {
                    tf1 = true;
                }
            }

            if (!isCounter() || previousT0 && !t0) {
                if (getMode() == 3) {
                    newTl0 = tl0.inc();
                    if (tl0.isOverflowOccurred()) {
                        tf0 = true;
                    }
                } else {
                    short lowByteOverflowValue;
                    if (getMode() == 0) {
                        lowByteOverflowValue = 0x1f;
                    } else {
                        lowByteOverflowValue = 0xff;
                    }

                    newTl0 = tl0.inc();
                    if (tl0.toInt() == lowByteOverflowValue) {
                        if (getMode() == 2) {
                            newTl0 = th0;
                            tf0 = true;
                        } else {
                            newTl0 = UnsignedInt8.ZERO;
                            newTh0 = th0.inc();
                            if (th0.isOverflowOccurred()) {
                                tf1 = true;
                            }
                        }
                    }
                }
            }

            internalData.TH0.setValue(newTh0);
            internalData.TL0.setValue(newTl0);

            if (tf0) {
                bitMap.TF0.setValue(tf0);
            }

            if (tf1) {
                bitMap.TF1.setValue(tf1);
            }
        }
    }

}
