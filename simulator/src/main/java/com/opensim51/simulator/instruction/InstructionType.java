package com.opensim51.simulator.instruction;

import com.opensim51.simulator.memory.ExternalCode;
import com.opensim51.simulator.memory.InternalData;
import com.opensim51.simulator.memory.Memory;
import com.opensim51.simulator.memory.MemoryUnit;
import com.opensim51.simulator.memory.datatype.UInt16;
import com.opensim51.simulator.memory.datatype.UInt8;

import static com.opensim51.simulator.instruction.InstructionUtils.*;

public enum InstructionType {

    NOP((pc, memoryUnit, bytes) -> pc.inc()),

    AJMP((pc, memoryUnit, bytes) -> {
        ExternalCode code = memoryUnit.getExternalCode();

        UInt8 opcode = code.getCellValue(pc);
        UInt16 highOrderBits = opcode.and(new UInt8(0xe0)).x16().shl(8);
        UInt16 lowOrderBits = code.getCellValue(pc.inc()).x16();
        UInt16 addr11 = highOrderBits.or(lowOrderBits);
        return pc.and(new UInt16(0xf800)).or(addr11);
    }),

    LJMP((pc, memoryUnit, bytes) -> {
        ExternalCode code = memoryUnit.getExternalCode();

        UInt8 highOrderByte = code.getCellValue(pc.inc());
        UInt8 lowOrderByte = code.getCellValue(pc.inc().inc());
        return highOrderByte.x16().shl(8).and(lowOrderByte.x16());
    }),

    RR((pc, memoryUnit, bytes) -> {
        return null;
    }),

    INC((pc, memoryUnit, bytes) -> {
        return null;
    }),

    JBC((pc, memoryUnit, bytes) -> {
        return null;
    }),

    ACALL((pc, memoryUnit, bytes) -> {
        InternalData data = memoryUnit.getInternalData();
        ExternalCode code = memoryUnit.getExternalCode();

        UInt16 next = pc.inc().inc();

        data.stack.push(next);

        UInt8 opcode = code.getCellValue(pc);
        UInt16 highOrderBits = opcode.and(new UInt8(0xe0)).x16().shl(8);
        UInt16 lowOrderBits = code.getCellValue(pc.inc()).x16();
        UInt16 addr11 = highOrderBits.or(lowOrderBits);

        return pc.and(new UInt16(0xf800)).or(addr11);
    }),

    LCALL((pc, memoryUnit, bytes) -> {
        InternalData data = memoryUnit.getInternalData();
        ExternalCode code = memoryUnit.getExternalCode();

        UInt16 next = pc.add(new UInt16(bytes));

        data.stack.push(next);

        UInt16 highByte = code.getCellValue(pc.inc()).x16();
        UInt16 lowByte = code.getCellValue(pc.inc().inc()).x16();

        return highByte.shl(0x8).add(lowByte);
    }),

    RRC((pc, memoryUnit, bytes) -> {
        return null;
    }),

    DEC((pc, memoryUnit, bytes) -> {
        InternalData data = memoryUnit.getInternalData();
        ExternalCode code = memoryUnit.getExternalCode();

        int opcode = code.getCellValue(pc).toInt();
        if ((opcode & 0xfe) == 0x16) {

            Memory.Cell register = getRegister(data, opcode & 0x01);
            UInt8 value = getIndirect(data, register).inc();
            setIndirect(register, value, data);

        } else if (opcode == 0x14) {

            UInt8 value = data.ACC.getValue().inc();
            data.ACC.setValue(value);

        } else if (opcode == 0x15) {

            UInt8 value = data.getCellValue(code.getCellValue(pc.inc())).sub(UInt8.ONE);
            data.setCellValue(code.getCellValue(pc.inc()), value);

        } else if ((opcode & 0xf8) == 0x18) {

            Memory.Cell register = getRegister(data, opcode & 0x07);
            register.setValue(register.getValue().inc());

        }

        return pc.add(new UInt16(bytes));
    }),

    JB((pc, memoryUnit, bytes) -> {
        return null;
    }),

    RET((pc, memoryUnit, bytes) -> {
        InternalData data = memoryUnit.getInternalData();

        UInt8 highByte = data.stack.pop();
        UInt8 lowByte = data.stack.pop();

        return highByte.x16().shl(8).or(lowByte.x16());
    }),

    RL((pc, memoryUnit, bytes) -> {
        return null;
    }),

    ADD((pc, memoryUnit, bytes) -> {
        return null;
    }),

    JNB((pc, memoryUnit, bytes) -> {
        return null;
    }),

    RETI((pc, memoryUnit, bytes) -> {
        return null;
    }),

    RLC((pc, memoryUnit, bytes) -> {
        return null;
    }),

    ADDC((pc, memoryUnit, bytes) -> {
        return null;
    }),

    JC((pc, memoryUnit, bytes) -> {
        return null;
    }),

    ORL((pc, memoryUnit, bytes) -> {
        return null;
    }),

    JNC((pc, memoryUnit, bytes) -> {
        return null;
    }),

    ANL((pc, memoryUnit, bytes) -> {
        return null;
    }),

    JZ((pc, memoryUnit, bytes) -> {
        return null;
    }),

    XRL((pc, memoryUnit, bytes) -> {
        return null;
    }),

    JNZ((pc, memoryUnit, bytes) -> {
        InternalData data = memoryUnit.getInternalData();
        ExternalCode code = memoryUnit.getExternalCode();

        pc = pc.add(new UInt16(bytes));
        if (!data.ACC.getValue().equals(UInt8.ZERO)) {
            UInt16 offset = code.getCellValue(pc.inc()).not().inc().x16();
            pc = pc.add(offset);
        }

        return pc;
    }),

    JMP((pc, memoryUnit, bytes) -> {
        return null;
    }),

    MOV((pc, memoryUnit, bytes) -> {
        InternalData data = memoryUnit.getInternalData();
        ExternalCode code = memoryUnit.getExternalCode();

        int opcode = code.getCellValue(pc).toInt();
        if ((opcode & 0xfe) == 0x76) {

            Memory.Cell register = getRegister(data, opcode & 0x01);
            UInt8 value = code.getCellValue(pc.inc());
            setIndirect(register, value, data);

        } else if ((opcode & 0xfe) == 0xf6) {

            Memory.Cell register = getRegister(data, opcode & 0x01);
            UInt8 value = data.ACC.getValue();
            setIndirect(register, value, data);

        } else if ((opcode & 0xfe) == 0xa6) {

            Memory.Cell register = getRegister(data, opcode & 0x01);
            UInt8 value = data.getCellValue(code.getCellValue(pc.inc()));
            setIndirect(register, value, data);

        } else if (opcode == 0x74) {

            Memory.Cell destination = data.ACC;
            UInt8 value = code.getCellValue(pc.inc());
            destination.setValue(value);

        } else if ((opcode & 0xfe) == 0xe6) {

            Memory.Cell destination = data.ACC;
            Memory.Cell register = getRegister(data, opcode & 0x01);
            setIndirect(destination, register, data);

        } else if (opcode == 0xe5) {

            Memory.Cell destination = data.ACC;
            UInt8 value = data.getCellValue(code.getCellValue(pc.inc()));
            destination.setValue(value);

        } else if ((opcode & 0xf8) == 0xe8) {

            Memory.Cell destination = data.ACC;
            UInt8 value = getRegister(data, opcode & 0x07).getValue();
            destination.setValue(value);

        } else if (opcode == 0x92) {

            InternalData.Bit destination = data.bitField.getBit(code.getCellValue(pc.inc()).toInt());
            boolean value = data.bitField.CY.getValue();
            destination.setValue(value);

        } else if (opcode == 0xa2) {

            InternalData.Bit destination = data.bitField.CY;
            boolean value = data.bitField.getBit(code.getCellValue(pc.inc()).toInt()).getValue();
            destination.setValue(value);

        } else if (opcode == 0x85) {

            Memory.Cell destination = data.getCell(code.getCellValue(pc.inc().inc()).toInt());
            UInt8 value = data.getCell(code.getCellValue(pc.inc()).toInt()).getValue();
            destination.setValue(value);

        } else if (opcode == 0x75) {

            Memory.Cell destination = data.getCell(code.getCellValue(pc.inc()).toInt());
            UInt8 value = code.getCellValue(pc.inc().inc());
            destination.setValue(value);

        } else if ((opcode & 0xfe) == 0x86) {

            Memory.Cell destination = data.getCell(code.getCellValue(pc.inc()).toInt());
            Memory.Cell register = getRegister(data, opcode & 0x01);
            setIndirect(destination, register, data);

        } else if (opcode == 0xf5) {

            Memory.Cell destination = data.getCell(code.getCellValue(pc.inc()).toInt());
            UInt8 value = data.ACC.getValue();
            destination.setValue(value);

        } else if ((opcode & 0xfe) == 0x88) {

            Memory.Cell destination = data.getCell(code.getCellValue(pc.inc()).toInt());
            UInt8 value = getRegister(data, opcode & 0x01).getValue();
            destination.setValue(value);

        } else if (opcode == 0x90) {

            data.DPH.setValue(code.getCellValue(pc.inc()));
            data.DPL.setValue(code.getCellValue(pc.inc().inc()));

        } else if ((opcode & 0xf8) == 0x78) {

            Memory.Cell destination = getRegister(data, opcode & 0x07);
            UInt8 value = code.getCellValue(pc.inc());
            destination.setValue(value);

        } else if ((opcode & 0xf8) == 0xf8) {

            Memory.Cell destination = getRegister(data, opcode & 0x07);
            UInt8 value = data.ACC.getValue();
            destination.setValue(value);

        } else if ((opcode & 0xf8) == 0xa8) {

            Memory.Cell destination = getRegister(data, opcode & 0x07);
            UInt8 value = data.getCellValue(code.getCellValue(pc.inc()));
            destination.setValue(value);

        }

        return pc.add(new UInt16(bytes));
    }),

    SJMP((pc, memoryUnit, bytes) -> {
        return null;
    }),

    MOVC((pc, memoryUnit, bytes) -> {
        return null;
    }),

    DIV((pc, memoryUnit, bytes) -> {
        return null;
    }),

    SUBB((pc, memoryUnit, bytes) -> {
        InternalData data = memoryUnit.getInternalData();
        ExternalCode code = memoryUnit.getExternalCode();

        int opcode = code.getCellValue(pc).toInt();
        if (opcode == 0x94) {

            UInt8 value = code.getCellValue(pc.inc());
            subtract(data, value);

        } else if ((opcode & 0xfe) == 0x96) {

            UInt8 value = data.getCellValue(getRegister(data, opcode & 0x01).getValue());
            subtract(data, value);

        } else if (opcode == 0x95) {

            UInt8 value = data.getCellValue(code.getCellValue(pc.inc()));
            subtract(data, value);

        } else if ((opcode & 0xf8) == 0x98) {

            UInt8 value = getRegister(data, opcode & 0x07).getValue();
            subtract(data, value);

        }

        return pc.add(new UInt16(bytes));
    }),

    MUL((pc, memoryUnit, bytes) -> {
        return null;
    }),

    CPL((pc, memoryUnit, bytes) -> {
        return null;
    }),

    CJNE((pc, memoryUnit, bytes) -> {
        return null;
    }),

    PUSH((pc, memoryUnit, bytes) -> {
        return null;
    }),

    CLR((pc, memoryUnit, bytes) -> {
        InternalData data = memoryUnit.getInternalData();
        ExternalCode code = memoryUnit.getExternalCode();

        int opcode = code.getCellValue(pc).toInt();
        if (opcode == 0xe4) {

            data.ACC.setValue(UInt8.ZERO);

        } else if (opcode == 0xc2) {

            UInt8 bitAddress = code.getCellValue(pc.inc());
            data.bitField.setBitValue(bitAddress.toInt(), false);

        } else if (opcode == 0xc3) {

            data.bitField.CY.setValue(false);

        }

        return pc.add(new UInt16(bytes));
    }),

    SWAP((pc, memoryUnit, bytes) -> {
        return null;
    }),

    XCH((pc, memoryUnit, bytes) -> {
        return null;
    }),

    POP((pc, memoryUnit, bytes) -> {
        return null;
    }),

    SETB((pc, memoryUnit, bytes) -> {
        InternalData data = memoryUnit.getInternalData();
        ExternalCode code = memoryUnit.getExternalCode();

        int opcode = code.getCellValue(pc).toInt();
        if (opcode == 0xd2) {

            UInt8 bitValue = code.getCellValue(pc.inc());
            data.bitField.setBitValue(bitValue.toInt(), true);

        } else if (opcode == 0xd3) {

            data.bitField.CY.setValue(true);

        }

        return pc.add(new UInt16(bytes));
    }),

    DA((pc, memoryUnit, bytes) -> {
        return null;
    }),

    DJNZ((pc, memoryUnit, bytes) -> {
        InternalData data = memoryUnit.getInternalData();
        ExternalCode code = memoryUnit.getExternalCode();

        UInt16 next = pc.add(new UInt16(bytes));

        int opcode = code.getCellValue(pc).toInt();
        if (opcode == 0xd5) {

            UInt8 direct = code.getCellValue(pc.inc());
            UInt8 value = data.getCellValue(direct);
            UInt8 newValue = value.sub(UInt8.ONE);
            data.setCellValue(direct, newValue);

            if (!newValue.equals(UInt8.ZERO)) {
                UInt8 offset = code.getCellValue(pc.inc().inc());
                next = next.add(offset.x16());
            }

        } else if ((opcode & 0xf8) == 0xd8) {

            Memory.Cell register = getRegister(data, opcode & 0x07);
            UInt8 newValue = register.getValue().sub(UInt8.ONE);
            register.setValue(newValue);

            if (!newValue.equals(UInt8.ZERO)) {
                UInt8 offset = code.getCellValue(pc.inc());
                next = next.add(offset.sx16());
            }

        }

        return next;
    }),

    XCHD((pc, memoryUnit, bytes) -> {
        return null;
    }),

    MOVX((pc, memoryUnit, bytes) -> {
        return null;
    });

    private final Operation operation;

    InstructionType(Operation operation) {
        this.operation = operation;
    }

    public UInt16 execute(UInt16 programCounter, MemoryUnit memoryUnit, int bytes) {
        return operation.execute(programCounter, memoryUnit, bytes);
    }

    private interface Operation {
        UInt16 execute(UInt16 programCounter, MemoryUnit memoryUnit, int bytes);
    }

}
