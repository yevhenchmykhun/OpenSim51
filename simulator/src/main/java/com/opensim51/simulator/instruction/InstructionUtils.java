package com.opensim51.simulator.instruction;

import com.opensim51.simulator.memory.InternalData;
import com.opensim51.simulator.memory.Memory;
import com.opensim51.simulator.memory.datatype.UInt8;

final class InstructionUtils {

    static void subtract(InternalData data, UInt8 value) {
        value = data.bitField.CY.getValue() ? value.inc() : value;

        UInt8 acc = data.ACC.getValue();
        data.bitField.CY.setValue(acc.isNegativeOverflowable(value));
        data.ACC.setValue(acc.sub(value));

        // set C flag

        // set OV flag
//        int signedResult = ram.toSignedNumber(a) - ram.toSignedNumber(data);
//        if (signedResult <= 127 && signedResult >= -128) {
//            data.bitMap.OV.stb();
//        } else {
//            ram.stb(210);
//        }

        // set AC flag
        UInt8 accumulatorLowNibble = data.ACC.getValue().and(UInt8.MASK_LOW_NIBBLE);
        UInt8 valueLowNibble = value.and(UInt8.MASK_LOW_NIBBLE);
        data.bitField.AC.setValue(accumulatorLowNibble.compareTo(valueLowNibble) < 0);
    }

    static void setIndirect(Memory.Cell destination, Memory.Cell register, InternalData data) {
        destination.setValue(data.getCellValue(register.getValue()));
    }

    static void setIndirect(Memory.Cell register, UInt8 value, InternalData data) {
        data.setCellValue(register.getValue(), value);
    }

    static UInt8 getIndirect(InternalData data, Memory.Cell register) {
        return data.getCellValue(register.getValue());
    }

    static Memory.Cell getRegister(InternalData data, int number) {
        switch (number) {
            case 0:
                return data.R0;
            case 1:
                return data.R1;
            case 2:
                return data.R2;
            case 3:
                return data.R3;
            case 4:
                return data.R4;
            case 5:
                return data.R5;
            case 6:
                return data.R6;
            case 7:
                return data.R7;

            // it will never happen
            default:
                return data.getCell(-1);
        }
    }

}
