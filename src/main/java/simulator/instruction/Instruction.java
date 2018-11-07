package simulator.instruction;

import simulator.Simulator;
import simulator.memory.InternalData;
import simulator.memory.Memory;
import simulator.memory.datatype.UnsignedInt16;
import simulator.memory.datatype.UnsignedInt8;

import java.util.HashMap;
import java.util.Map;

public enum Instruction {

    NOP(0x0, 1, 0, (id, xd, xc, s, i) -> s.setPC(s.getPC().inc())),

    MOV_74(0x74, 2, 0, (id, xd, xc, s, i) -> setDirectCellValue(id.ACC, xc.getCellValue(s.getPC().inc()), i.bytes, s)),
    MOV_75(0x75, 3, 0, (id, xd, xc, s, i) -> setIndirectCellValue(id, xc.getCellValue(s.getPC().inc()), xc.getCellValue(s.getPC().inc().inc()), i.bytes, s)),
    MOV_76(0x76, 2, 0, (id, xd, xc, s, i) -> setIndirectCellValue(id, id.R0.getValue(), xc.getCellValue(s.getPC().inc()), i.bytes, s)),
    MOV_77(0x77, 2, 0, (id, xd, xc, s, i) -> setIndirectCellValue(id, id.R1.getValue(), xc.getCellValue(s.getPC().inc()), i.bytes, s)),
    MOV_78(0x78, 2, 0, (id, xd, xc, s, i) -> setDirectCellValue(id.R0, xc.getCellValue(s.getPC().inc()), i.bytes, s)),
    MOV_79(0x79, 2, 0, (id, xd, xc, s, i) -> setDirectCellValue(id.R1, xc.getCellValue(s.getPC().inc()), i.bytes, s)),
    MOV_7A(0x7a, 2, 0, (id, xd, xc, s, i) -> setDirectCellValue(id.R2, xc.getCellValue(s.getPC().inc()), i.bytes, s)),
    MOV_7B(0x7b, 2, 0, (id, xd, xc, s, i) -> setDirectCellValue(id.R3, xc.getCellValue(s.getPC().inc()), i.bytes, s)),
    MOV_7C(0x7c, 2, 0, (id, xd, xc, s, i) -> setDirectCellValue(id.R4, xc.getCellValue(s.getPC().inc()), i.bytes, s)),
    MOV_7D(0x7d, 2, 0, (id, xd, xc, s, i) -> setDirectCellValue(id.R5, xc.getCellValue(s.getPC().inc()), i.bytes, s)),
    MOV_7E(0x7e, 2, 0, (id, xd, xc, s, i) -> setDirectCellValue(id.R6, xc.getCellValue(s.getPC().inc()), i.bytes, s)),
    MOV_7F(0x7f, 2, 0, (id, xd, xc, s, i) -> setDirectCellValue(id.R7, xc.getCellValue(s.getPC().inc()), i.bytes, s)),

    MOV_85(0x85, 3, 0, (id, xd, xc, s, i) -> setIndirectCellValue(id, xc.getCellValue(s.getPC().inc().inc()), id.getCellValue(xc.getCellValue(s.getPC().inc())), i.bytes, s)),
    MOV_86(0x86, 2, 0, (id, xd, xc, s, i) -> setIndirectCellValue(id, xc.getCellValue(s.getPC().inc()), id.getCellValue(id.R0.getValue()), i.bytes, s)),
    MOV_87(0x87, 2, 0, (id, xd, xc, s, i) -> setIndirectCellValue(id, xc.getCellValue(s.getPC().inc()), id.getCellValue(id.R1.getValue()), i.bytes, s)),
    MOV_88(0x88, 2, 0, (id, xd, xc, s, i) -> setIndirectCellValue(id, xc.getCellValue(s.getPC().inc()), id.R0.getValue(), i.bytes, s)),
    MOV_89(0x89, 2, 0, (id, xd, xc, s, i) -> setIndirectCellValue(id, xc.getCellValue(s.getPC().inc()), id.R1.getValue(), i.bytes, s)),
    MOV_8A(0x8a, 2, 0, (id, xd, xc, s, i) -> setIndirectCellValue(id, xc.getCellValue(s.getPC().inc()), id.R2.getValue(), i.bytes, s)),
    MOV_8B(0x8b, 2, 0, (id, xd, xc, s, i) -> setIndirectCellValue(id, xc.getCellValue(s.getPC().inc()), id.R3.getValue(), i.bytes, s)),
    MOV_8C(0x8c, 2, 0, (id, xd, xc, s, i) -> setIndirectCellValue(id, xc.getCellValue(s.getPC().inc()), id.R4.getValue(), i.bytes, s)),
    MOV_8D(0x8d, 2, 0, (id, xd, xc, s, i) -> setIndirectCellValue(id, xc.getCellValue(s.getPC().inc()), id.R5.getValue(), i.bytes, s)),
    MOV_8E(0x8e, 2, 0, (id, xd, xc, s, i) -> setIndirectCellValue(id, xc.getCellValue(s.getPC().inc()), id.R6.getValue(), i.bytes, s)),
    MOV_8F(0x8f, 2, 0, (id, xd, xc, s, i) -> setIndirectCellValue(id, xc.getCellValue(s.getPC().inc()), id.R7.getValue(), i.bytes, s)),

    MOV_90(0x90, 3, 0, (id, xd, xc, s, i) -> {
        id.DPH.setValue(xc.getCellValue(s.getPC().inc()));
        id.DPL.setValue(xc.getCellValue(s.getPC().inc().inc()));
        s.setPC(s.getPC().add(new UnsignedInt16(i.bytes)));
    }),

    MOV_92(0x92, 2, 0, (id, xd, xc, s, i) -> {
        // TODO: impl
    }),

    MOV_94(0x94, 2, 0, (id, xd, xc, s, i) -> subtract(id, xc.getCellValue(s.getPC().inc()), i.bytes, s)),
    MOV_95(0x95, 2, 0, (id, xd, xc, s, i) -> subtract(id, id.getCellValue(xc.getCellValue(s.getPC().inc())), i.bytes, s)),
    MOV_96(0x96, 1, 0, (id, xd, xc, s, i) -> subtract(id, id.getCellValue(id.R0.getValue()), i.bytes, s)),
    MOV_97(0x97, 1, 0, (id, xd, xc, s, i) -> subtract(id, id.getCellValue(id.R1.getValue()), i.bytes, s)),
    MOV_98(0x98, 1, 0, (id, xd, xc, s, i) -> subtract(id, id.R0.getValue(), i.bytes, s)),
    MOV_99(0x99, 1, 0, (id, xd, xc, s, i) -> subtract(id, id.R1.getValue(), i.bytes, s)),
    MOV_9A(0x9a, 1, 0, (id, xd, xc, s, i) -> subtract(id, id.R2.getValue(), i.bytes, s)),
    MOV_9B(0x9b, 1, 0, (id, xd, xc, s, i) -> subtract(id, id.R3.getValue(), i.bytes, s)),
    MOV_9C(0x9c, 1, 0, (id, xd, xc, s, i) -> subtract(id, id.R4.getValue(), i.bytes, s)),
    MOV_9D(0x9d, 1, 0, (id, xd, xc, s, i) -> subtract(id, id.R5.getValue(), i.bytes, s)),
    MOV_9E(0x9e, 1, 0, (id, xd, xc, s, i) -> subtract(id, id.R6.getValue(), i.bytes, s)),
    MOV_9F(0x9f, 1, 0, (id, xd, xc, s, i) -> subtract(id, id.R7.getValue(), i.bytes, s)),

    MOV_A2(0xa2, 2, 0, (id, xd, xc, s, i) -> {
        // TODO: impl
    }),

    MOV_A6(0xa6, 2, 0, (id, xd, xc, s, i) -> setIndirectCellValue(id, id.R0.getValue(), id.getCellValue(xc.getCellValue(s.getPC().inc())), i.bytes, s)),
    MOV_A7(0xa7, 2, 0, (id, xd, xc, s, i) -> setIndirectCellValue(id, id.R1.getValue(), id.getCellValue(xc.getCellValue(s.getPC().inc())), i.bytes, s)),
    MOV_A8(0xa8, 2, 0, (id, xd, xc, s, i) -> setDirectCellValue(id.R0, id.getCellValue(xc.getCellValue(s.getPC().inc())), i.bytes, s)),
    MOV_A9(0xa9, 2, 0, (id, xd, xc, s, i) -> setDirectCellValue(id.R1, id.getCellValue(xc.getCellValue(s.getPC().inc())), i.bytes, s)),
    MOV_AA(0xaa, 2, 0, (id, xd, xc, s, i) -> setDirectCellValue(id.R2, id.getCellValue(xc.getCellValue(s.getPC().inc())), i.bytes, s)),
    MOV_AB(0xab, 2, 0, (id, xd, xc, s, i) -> setDirectCellValue(id.R3, id.getCellValue(xc.getCellValue(s.getPC().inc())), i.bytes, s)),
    MOV_AC(0xac, 2, 0, (id, xd, xc, s, i) -> setDirectCellValue(id.R4, id.getCellValue(xc.getCellValue(s.getPC().inc())), i.bytes, s)),
    MOV_AD(0xad, 2, 0, (id, xd, xc, s, i) -> setDirectCellValue(id.R5, id.getCellValue(xc.getCellValue(s.getPC().inc())), i.bytes, s)),
    MOV_AE(0xae, 2, 0, (id, xd, xc, s, i) -> setDirectCellValue(id.R6, id.getCellValue(xc.getCellValue(s.getPC().inc())), i.bytes, s)),
    MOV_AF(0xaf, 2, 0, (id, xd, xc, s, i) -> setDirectCellValue(id.R7, id.getCellValue(xc.getCellValue(s.getPC().inc())), i.bytes, s)),

    MOV_E5(0xe5, 2, 0, (id, xd, xc, s, i) -> setDirectCellValue(id.ACC, id.getCellValue(xc.getCellValue(s.getPC().inc())), i.bytes, s)),
    MOV_E6(0xe6, 1, 0, (id, xd, xc, s, i) -> setDirectCellValue(id.ACC, id.getCellValue(id.R0.getValue()), i.bytes, s)),
    MOV_E7(0xe7, 1, 0, (id, xd, xc, s, i) -> setDirectCellValue(id.ACC, id.getCellValue(id.R1.getValue()), i.bytes, s)),
    MOV_E8(0xe8, 1, 0, (id, xd, xc, s, i) -> setDirectCellValue(id.ACC, id.R0.getValue(), i.bytes, s)),
    MOV_E9(0xe9, 1, 0, (id, xd, xc, s, i) -> setDirectCellValue(id.ACC, id.R1.getValue(), i.bytes, s)),
    MOV_EA(0xea, 1, 0, (id, xd, xc, s, i) -> setDirectCellValue(id.ACC, id.R2.getValue(), i.bytes, s)),
    MOV_EB(0xeb, 1, 0, (id, xd, xc, s, i) -> setDirectCellValue(id.ACC, id.R3.getValue(), i.bytes, s)),
    MOV_EC(0xec, 1, 0, (id, xd, xc, s, i) -> setDirectCellValue(id.ACC, id.R4.getValue(), i.bytes, s)),
    MOV_ED(0xed, 1, 0, (id, xd, xc, s, i) -> setDirectCellValue(id.ACC, id.R5.getValue(), i.bytes, s)),
    MOV_EE(0xee, 1, 0, (id, xd, xc, s, i) -> setDirectCellValue(id.ACC, id.R6.getValue(), i.bytes, s)),
    MOV_EF(0xef, 1, 0, (id, xd, xc, s, i) -> setDirectCellValue(id.ACC, id.R7.getValue(), i.bytes, s)),

    MOV_F5(0xF5, 2, 0, (id, xd, xc, s, i) -> setIndirectCellValue(id, xc.getCellValue(s.getPC().inc()), id.ACC.getValue(), i.bytes, s)),
    MOV_F6(0xf6, 1, 0, (id, xd, xc, s, i) -> setIndirectCellValue(id, id.R0.getValue(), id.ACC.getValue(), i.bytes, s)),
    MOV_F7(0xf7, 1, 0, (id, xd, xc, s, i) -> setIndirectCellValue(id, id.R1.getValue(), id.ACC.getValue(), i.bytes, s)),
    MOV_F8(0xf8, 1, 0, (id, xd, xc, s, i) -> setDirectCellValue(id.R0, id.ACC.getValue(), i.bytes, s)),
    MOV_F9(0xf9, 1, 0, (id, xd, xc, s, i) -> setDirectCellValue(id.R1, id.ACC.getValue(), i.bytes, s)),
    MOV_FA(0xfa, 1, 0, (id, xd, xc, s, i) -> setDirectCellValue(id.R2, id.ACC.getValue(), i.bytes, s)),
    MOV_FB(0xfb, 1, 0, (id, xd, xc, s, i) -> setDirectCellValue(id.R3, id.ACC.getValue(), i.bytes, s)),
    MOV_FC(0xfc, 1, 0, (id, xd, xc, s, i) -> setDirectCellValue(id.R4, id.ACC.getValue(), i.bytes, s)),
    MOV_FD(0xfd, 1, 0, (id, xd, xc, s, i) -> setDirectCellValue(id.R5, id.ACC.getValue(), i.bytes, s)),
    MOV_FE(0xfe, 1, 0, (id, xd, xc, s, i) -> setDirectCellValue(id.R6, id.ACC.getValue(), i.bytes, s)),
    MOV_FF(0xff, 1, 0, (id, xd, xc, s, i) -> setDirectCellValue(id.R7, id.ACC.getValue(), i.bytes, s)),

    AJMP_01(0x1, 1, 0, (id, xd, xc, s, i) -> s.setPC(determineNextAbsoluteJumpAddress(s.getPC(), xc.getCellValue(s.getPC().inc()), new UnsignedInt8(i.opcode)))),
    AJMP_21(0x21, 1, 0, (id, xd, xc, s, i) -> s.setPC(determineNextAbsoluteJumpAddress(s.getPC(), xc.getCellValue(s.getPC().inc()), new UnsignedInt8(i.opcode)))),
    AJMP_41(0x41, 1, 0, (id, xd, xc, s, i) -> s.setPC(determineNextAbsoluteJumpAddress(s.getPC(), xc.getCellValue(s.getPC().inc()), new UnsignedInt8(i.opcode)))),
    AJMP_61(0x61, 1, 0, (id, xd, xc, s, i) -> s.setPC(determineNextAbsoluteJumpAddress(s.getPC(), xc.getCellValue(s.getPC().inc()), new UnsignedInt8(i.opcode)))),
    AJMP_81(0x81, 1, 0, (id, xd, xc, s, i) -> s.setPC(determineNextAbsoluteJumpAddress(s.getPC(), xc.getCellValue(s.getPC().inc()), new UnsignedInt8(i.opcode)))),
    AJMP_A1(0xa1, 1, 0, (id, xd, xc, s, i) -> s.setPC(determineNextAbsoluteJumpAddress(s.getPC(), xc.getCellValue(s.getPC().inc()), new UnsignedInt8(i.opcode)))),
    AJMP_C1(0xc1, 1, 0, (id, xd, xc, s, i) -> s.setPC(determineNextAbsoluteJumpAddress(s.getPC(), xc.getCellValue(s.getPC().inc()), new UnsignedInt8(i.opcode)))),
    AJMP_E1(0xe1, 1, 0, (id, xd, xc, s, i) -> s.setPC(determineNextAbsoluteJumpAddress(s.getPC(), xc.getCellValue(s.getPC().inc()), new UnsignedInt8(i.opcode)))),

    CLR_C2(0xc2, 2, 0, (id, xd, xc, s, i) -> {
        // TODO: impl
        s.setPC(s.getPC().inc().inc());
    }),

    CLR_C3(0xc3, 1, 0, (id, xd, xc, s, i) -> {
        id.bitMap.CY.setValue(false);
        s.setPC(s.getPC().inc());
    }),

    CLR_E4(0xe4, 1, 0, (id, xd, xc, s, i) -> {
        id.ACC.setValue(new UnsignedInt8(0x0));
        s.setPC(s.getPC().inc());
    }),

    JNZ(0x70, 2, 0, (id, xd, xc, s, i) -> {
        UnsignedInt16 programCounter = s.getPC();

        UnsignedInt16 nextAddress;
        if (id.ACC.getValue().equals(new UnsignedInt8(0x0))) {
            nextAddress = programCounter.inc().inc();
        } else {
            nextAddress = programCounter.add(xc.getCellValue(programCounter.inc()).toUnsignedInt16());
        }
        s.setPC(nextAddress);
    }),

    LJMP(2, 3, 0, (id, xd, xc, s, i) -> {
        UnsignedInt8 highOrderByte = xc.getCellValue(s.getPC().inc());
        UnsignedInt8 lowOrderByte = xc.getCellValue(s.getPC().inc().inc());
        UnsignedInt16 nextAddress = highOrderByte.toUnsignedInt16().shiftLeft(0x8).and(lowOrderByte.toUnsignedInt16());
        s.setPC(nextAddress);
    });

    private static final Map<Integer, Instruction> INSTRUCTIONS;

    static {
        INSTRUCTIONS = new HashMap<>(256);
        for (Instruction instruction : Instruction.values()) {
            INSTRUCTIONS.put(instruction.opcode, instruction);
        }
    }

    private final int opcode;

    private final int bytes;

    private int cycles;

    private final Command command;

    Instruction(int code, int bytes, int cycles, Command command) {
        this.opcode = code;
        this.bytes = bytes;
        this.cycles = cycles;
        this.command = command;
    }

    public static Instruction findByOpcode(int opcode) {
        return INSTRUCTIONS.get(opcode);
    }

    private static void setDirectCellValue(Memory.Cell cell, UnsignedInt8 value, int increment, Simulator simulator) {
        cell.setValue(value);
        simulator.setPC(simulator.getPC().add(new UnsignedInt16(increment)));
    }

    private static void subtract(InternalData internalData, UnsignedInt8 value, int increment, Simulator simulator) {
        value = internalData.bitMap.CY.getValue() ? value.inc() : value;
        internalData.bitMap.CY.setValue(internalData.ACC.getValue().compareTo(value) < 0);

        UnsignedInt8 mask = new UnsignedInt8(0x0f);
        internalData.bitMap.AC.setValue(internalData.ACC.getValue().and(mask).compareTo(value.and(mask)) < 0);

        // TODO: handle OV flag
//        internalData.OV.setValue(true);

        internalData.ACC.setValue(internalData.ACC.getValue().subtract(value));
        simulator.setPC(simulator.getPC().add(new UnsignedInt16(increment)));
    }

    private static void setIndirectCellValue(InternalData internalData, UnsignedInt8 address, UnsignedInt8 value, int increment, Simulator simulator) {
        internalData.setCellValue(address, value);
        simulator.setPC(simulator.getPC().add(new UnsignedInt16(increment)));
    }

    private static UnsignedInt16 determineNextAbsoluteJumpAddress(UnsignedInt16 pc, UnsignedInt8 operand, UnsignedInt8 opcode) {
        UnsignedInt8 upperAddress = opcode.shiftRight(0x5).and(new UnsignedInt8(0x7));
        return pc.and(new UnsignedInt16(0xf800)).or(upperAddress.shiftLeft(0x8).or(operand).toUnsignedInt16());
    }

    public int getBytes() {
        return bytes;
    }

    public int getCycles() {
        return cycles;
    }

    public Command getCommand() {
        return command;
    }
}
