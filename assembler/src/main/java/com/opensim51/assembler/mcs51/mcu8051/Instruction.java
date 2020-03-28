package com.opensim51.assembler.mcs51.mcu8051;

import com.opensim51.assembler.mcs51.mcu8051.types.Operand;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum Instruction {

    NOP((locationCounter, operands) -> {
        return Collections.singletonList(0x00);
    }, null),

    AJMP((locationCounter, operands) -> {
        Operand operand = operands.get(0);
        int address = operand.getValue();
        return Arrays.asList(((address & 0x700) >> 3) | 0x01, address & 0xff);
    }, null),

    LJMP((locationCounter, operands) -> {
        Operand operand = operands.get(0);
        int address = operand.getValue();
        return Arrays.asList(0x02, (address >> 8) & 0xff, address & 0xff);
    }, null),

    RR((locationCounter, operands) -> {
        return Collections.singletonList(0x03);
    }, null),

    INC((locationCounter, operands) -> {
        Operand operand = operands.get(0);
        switch (operand.getType()) {

            case INDIRECT_REGISTER: {
                int registerNumber = operand.getValue();
                return Collections.singletonList(0x06 | registerNumber);
            }

            case ACCUMULATOR: {
                return Collections.singletonList(0x04);
            }

            case DIRECT: {
                int direct = operand.getValue();
                return Arrays.asList(0x05, direct);
            }

            case DPTR: {
                return Collections.singletonList(0xa3);
            }

            case REGISTER: {
                int registerNumber = operand.getValue();
                return Collections.singletonList(0x08 | registerNumber);
            }

        }

        return new ArrayList<>();
    }, null),

    JBC((locationCounter, operands) -> {
        Operand firstOperand = operands.get(0);
        Operand secondOperand = operands.get(1);
        return Arrays.asList(0x10, firstOperand.getValue(), secondOperand.getValue());
    }, null),

    ACALL((locationCounter, operands) -> {
        Operand operand = operands.get(0);
        int address = operand.getValue();
        return Arrays.asList(((address & 0x700) >> 3) | 0x11, address & 0xff);
    }, null),

    LCALL((locationCounter, operands) -> {
        Operand operand = operands.get(0);
        int address = operand.getValue();
        return Arrays.asList(0x12, (address >> 8) & 0xff, address & 0xff);
    }, null),

    RRC((locationCounter, operands) -> {
        return Collections.singletonList(0x13);
    }, null),

    DEC((locationCounter, operands) -> {
        Operand operand = operands.get(0);
        switch (operand.getType()) {

            case INDIRECT_REGISTER: {
                int registerNumber = operand.getValue();
                return Collections.singletonList(0x16 | registerNumber);
            }

            case ACCUMULATOR: {
                return Collections.singletonList(0x14);
            }

            case DIRECT: {
                int direct = operand.getValue();
                return Arrays.asList(0x15, direct);
            }

            case REGISTER: {
                int registerNumber = operand.getValue();
                return Collections.singletonList(0x18 | registerNumber);
            }

        }

        return new ArrayList<>();
    }, null),

    JB((locationCounter, operands) -> {
        Operand firstOperand = operands.get(0);
        Operand secondOperand = operands.get(1);
        return Arrays.asList(0x20, firstOperand.getValue(), secondOperand.getValue());
    }, null),

    RET((locationCounter, operands) -> {
        return Collections.singletonList(0x22);
    }, null),

    RL((locationCounter, operands) -> {
        return Collections.singletonList(0x23);
    }, null),

    ADD((locationCounter, operands) -> {
        Operand secondOperand = operands.get(1);
        switch (secondOperand.getType()) {

            case IMMEDIATE: {
                int immediate = secondOperand.getValue();
                return Arrays.asList(0x24, immediate);
            }

            case INDIRECT_REGISTER: {
                int registerNumber = secondOperand.getValue();
                return Collections.singletonList(0x26 | registerNumber);
            }

            case DIRECT: {
                int direct = secondOperand.getValue();
                return Arrays.asList(0x25, direct);
            }

            case REGISTER: {
                int registerNumber = secondOperand.getValue();
                return Collections.singletonList(0x28 | registerNumber);
            }

        }

        return new ArrayList<>();
    }, null),

    JNB((locationCounter, operands) -> {
        Operand firstOperand = operands.get(0);
        Operand secondOperand = operands.get(1);
        return Arrays.asList(0x30, firstOperand.getValue(), secondOperand.getValue());
    }, null),

    RETI((locationCounter, operands) -> {
        return Collections.singletonList(0x32);
    }, null),

    RLC((locationCounter, operands) -> {
        return Collections.singletonList(0x33);
    }, null),

    ADDC((locationCounter, operands) -> {
        Operand secondOperand = operands.get(1);
        switch (secondOperand.getType()) {

            case IMMEDIATE: {
                int immediate = secondOperand.getValue();
                return Arrays.asList(0x34, immediate);
            }

            case INDIRECT_REGISTER: {
                int registerNumber = secondOperand.getValue();
                return Collections.singletonList(0x36 | registerNumber);
            }

            case DIRECT: {
                int direct = secondOperand.getValue();
                return Arrays.asList(0x35, direct);
            }

            case REGISTER: {
                int registerNumber = secondOperand.getValue();
                return Collections.singletonList(0x38 | registerNumber);
            }

        }

        return new ArrayList<>();
    }, null),

    JC((locationCounter, operands) -> {
        Operand operand = operands.get(0);
        return Arrays.asList(0x40, operand.getValue());
    }, null),

    ORL((locationCounter, operands) -> {
        Operand firstOperand = operands.get(0);
        Operand secondOperand = operands.get(1);
        switch (firstOperand.getType()) {

            case ACCUMULATOR: {
                switch (secondOperand.getType()) {
                    case IMMEDIATE: {
                        int immediate = secondOperand.getValue();
                        return Arrays.asList(0x44, immediate);
                    }
                    case INDIRECT_REGISTER: {
                        int registerNumber = secondOperand.getValue();
                        return Collections.singletonList(0x46 | registerNumber);
                    }
                    case DIRECT: {
                        int direct = secondOperand.getValue();
                        return Arrays.asList(0x45, direct);
                    }
                    case REGISTER: {
                        int registerNumber = secondOperand.getValue();
                        return Collections.singletonList(0x48 | registerNumber);
                    }
                }
                break;
            }

            case CARRY: {
                switch (secondOperand.getType()) {
                    case NOT_BIT: {
                        int bit = secondOperand.getValue();
                        return Arrays.asList(0xa0, bit);
                    }

                    case BIT: {
                        int bit = secondOperand.getValue();
                        return Arrays.asList(0x72, bit);
                    }
                }
                break;
            }

            case DIRECT: {
                int direct = firstOperand.getValue();
                switch (secondOperand.getType()) {
                    case IMMEDIATE: {
                        int immediate = secondOperand.getValue();
                        return Arrays.asList(0x43, direct, immediate);
                    }

                    case ACCUMULATOR: {
                        return Arrays.asList(0x42, direct);
                    }
                }
                break;
            }

        }

        return new ArrayList<>();
    }, null),

    JNC((locationCounter, operands) -> {
        Operand operand = operands.get(0);
        return Arrays.asList(0x50, operand.getValue());
    }, null),

    ANL((locationCounter, operands) -> {
        Operand firstOperand = operands.get(0);
        Operand secondOperand = operands.get(1);
        switch (firstOperand.getType()) {

            case ACCUMULATOR: {
                switch (secondOperand.getType()) {
                    case IMMEDIATE: {
                        int immediate = secondOperand.getValue();
                        return Arrays.asList(0x54, immediate);
                    }
                    case INDIRECT_REGISTER: {
                        int registerNumber = secondOperand.getValue();
                        return Collections.singletonList(0x56 | registerNumber);
                    }
                    case DIRECT: {
                        int direct = secondOperand.getValue();
                        return Arrays.asList(0x55, direct);
                    }
                    case REGISTER: {
                        int registerNumber = secondOperand.getValue();
                        return Collections.singletonList(0x58 | registerNumber);
                    }
                }
                break;
            }

            case CARRY: {
                switch (secondOperand.getType()) {
                    case NOT_BIT: {
                        int bit = secondOperand.getValue();
                        return Arrays.asList(0xb0, bit);
                    }

                    case BIT: {
                        int bit = secondOperand.getValue();
                        return Arrays.asList(0x82, bit);
                    }
                }
                break;
            }

            case DIRECT: {
                int direct = firstOperand.getValue();
                switch (secondOperand.getType()) {
                    case IMMEDIATE: {
                        int immediate = secondOperand.getValue();
                        return Arrays.asList(0x53, direct, immediate);
                    }

                    case ACCUMULATOR: {
                        return Arrays.asList(0x52, direct);
                    }
                }
                break;
            }

        }

        return new ArrayList<>();
    }, null),

    JZ((locationCounter, operands) -> {
        Operand operand = operands.get(0);
        return Arrays.asList(0x60, operand.getValue());
    }, null),

    XRL((locationCounter, operands) -> {
        Operand firstOperand = operands.get(0);
        Operand secondOperand = operands.get(1);
        switch (firstOperand.getType()) {

            case ACCUMULATOR: {
                switch (secondOperand.getType()) {
                    case IMMEDIATE: {
                        int immediate = secondOperand.getValue();
                        return Arrays.asList(0x64, immediate);
                    }
                    case INDIRECT_REGISTER: {
                        int registerNumber = secondOperand.getValue();
                        return Collections.singletonList(0x66 | registerNumber);
                    }
                    case DIRECT: {
                        int direct = secondOperand.getValue();
                        return Arrays.asList(0x65, direct);
                    }
                    case REGISTER: {
                        int registerNumber = secondOperand.getValue();
                        return Collections.singletonList(0x68 | registerNumber);
                    }
                }
                break;
            }

            case DIRECT: {
                int direct = firstOperand.getValue();
                switch (secondOperand.getType()) {
                    case IMMEDIATE: {
                        int immediate = secondOperand.getValue();
                        return Arrays.asList(0x63, direct, immediate);
                    }

                    case ACCUMULATOR: {
                        return Arrays.asList(0x62, direct);
                    }
                }
                break;
            }

        }

        return new ArrayList<>();
    }, null),

    JNZ((locationCounter, operands) -> {
        Operand operand = operands.get(0);
        return Arrays.asList(0x70, operand.getValue());
    }, null),

    JMP((locationCounter, operands) -> {
        return Collections.singletonList(0x73);
    }, null),

    MOV((locationCounter, operands) -> {
        Operand firstOperand = operands.get(0);
        Operand secondOperand = operands.get(1);
        switch (firstOperand.getType()) {

            case INDIRECT_REGISTER: {
                int registerNumber = firstOperand.getValue();
                switch (secondOperand.getType()) {
                    case IMMEDIATE: {
                        int immediate = secondOperand.getValue();
                        return Arrays.asList(0x76 | registerNumber, immediate);
                    }
                    case ACCUMULATOR: {
                        return Collections.singletonList(0xf6 | registerNumber);
                    }
                    case DIRECT: {
                        int direct = secondOperand.getValue();
                        return Arrays.asList(0xa6 | registerNumber, direct);
                    }
                }
                break;
            }

            case ACCUMULATOR: {
                switch (secondOperand.getType()) {
                    case IMMEDIATE: {
                        int immediate = secondOperand.getValue();
                        return Arrays.asList(0x74, immediate);
                    }
                    case INDIRECT_REGISTER: {
                        int registerNumber = secondOperand.getValue();
                        return Collections.singletonList(0xe6 | registerNumber);
                    }
                    case DIRECT: {
                        int direct = secondOperand.getValue();
                        return Arrays.asList(0xe5, direct);
                    }
                    case REGISTER: {
                        int registerNumber = secondOperand.getValue();
                        return Collections.singletonList(0xe8 | registerNumber);
                    }
                }
                break;
            }

            case BIT: {
                return Arrays.asList(0x92, firstOperand.getValue());
            }

            case CARRY: {
                return Arrays.asList(0xa2, secondOperand.getValue());
            }

            case DIRECT: {
                int direct = firstOperand.getValue();
                switch (secondOperand.getType()) {
                    case DIRECT: {
                        int srcDirect = secondOperand.getValue();
                        return Arrays.asList(0x85, srcDirect, direct);
                    }
                    case IMMEDIATE: {
                        int immediate = secondOperand.getValue();
                        return Arrays.asList(0x75, direct, immediate);
                    }
                    case INDIRECT_REGISTER: {
                        int registerNumber = secondOperand.getValue();
                        return Arrays.asList(0x86 | registerNumber, direct);
                    }
                    case ACCUMULATOR: {
                        return Arrays.asList(0xf5, direct);
                    }
                    case REGISTER: {
                        int registerNumber = secondOperand.getValue();
                        return Arrays.asList(0x88 | registerNumber, direct);
                    }
                }
                break;
            }

            case DPTR: {
                int immediate = secondOperand.getValue();
                return Arrays.asList(0x90, (immediate >> 8) & 0xff, immediate & 0xff);
            }

            case REGISTER: {
                int registerNumber = firstOperand.getValue();
                switch (secondOperand.getType()) {
                    case IMMEDIATE: {
                        int immediate = secondOperand.getValue();
                        return Arrays.asList(0x78 | registerNumber, immediate);
                    }
                    case ACCUMULATOR: {
                        return Collections.singletonList(0xf8 | registerNumber);
                    }
                    case DIRECT: {
                        int direct = secondOperand.getValue();
                        return Arrays.asList(0xa8 | registerNumber, direct);
                    }
                }
                break;
            }

        }

        return new ArrayList<>();
    }, null),

    SJMP((locationCounter, operands) -> {
        Operand operand = operands.get(0);
        return Arrays.asList(0x80, operand.getValue());
    }, null),

    MOVC((locationCounter, operands) -> {
        Operand secondOperand = operands.get(1);
        switch (secondOperand.getType()) {

            case AT_A_PLUS_DPTR: {
                return Collections.singletonList(0x93);
            }

            case AT_A_PLUS_PC: {
                return Collections.singletonList(0x83);
            }
        }

        return new ArrayList<>();
    }, null),

    DIV((locationCounter, operands) -> {
        return Collections.singletonList(0x84);
    }, null),

    SUBB((locationCounter, operands) -> {
        Operand secondOperand = operands.get(1);
        switch (secondOperand.getType()) {

            case IMMEDIATE: {
                int immediate = secondOperand.getValue();
                return Arrays.asList(0x94, immediate);
            }

            case INDIRECT_REGISTER: {
                int registerNumber = secondOperand.getValue();
                return Collections.singletonList(0x96 | registerNumber);
            }

            case DIRECT: {
                int direct = secondOperand.getValue();
                return Arrays.asList(0x95, direct);
            }

            case REGISTER: {
                int registerNumber = secondOperand.getValue();
                return Collections.singletonList(0x98 | registerNumber);
            }

        }

        return new ArrayList<>();
    }, null),

    MUL((locationCounter, operands) -> {
        return Collections.singletonList(0xa4);
    }, null),

    CPL((locationCounter, operands) -> {
        Operand firstOperand = operands.get(0);
        switch (firstOperand.getType()) {

            case ACCUMULATOR: {
                return Collections.singletonList(0xf4);
            }

            case BIT: {
                int bit = firstOperand.getValue();
                return Arrays.asList(0xb2, bit);
            }

            case CARRY: {
                return Collections.singletonList(0xb3);
            }

        }

        return new ArrayList<>();
    }, null),

    CJNE((locationCounter, operands) -> {
        Operand firstOperand = operands.get(0);
        Operand secondOperand = operands.get(1);
        Operand thirdOperand = operands.get(2);
        switch (firstOperand.getType()) {

            case INDIRECT_REGISTER: {
                int registerNumber = firstOperand.getValue();
                int immediate = secondOperand.getValue();
                int address = thirdOperand.getValue();
                return Arrays.asList(0xb6 | registerNumber, immediate, address);
            }

            case ACCUMULATOR: {
                switch (secondOperand.getType()) {
                    case IMMEDIATE: {
                        int immediate = secondOperand.getValue();
                        int address = thirdOperand.getValue();
                        return Arrays.asList(0xb4, immediate, address);
                    }
                    case DIRECT: {
                        int direct = secondOperand.getValue();
                        int address = thirdOperand.getValue();
                        return Arrays.asList(0xb5, direct, address);
                    }
                }
                break;
            }

            case REGISTER: {
                int registerNumber = firstOperand.getValue();
                int immediate = secondOperand.getValue();
                int address = thirdOperand.getValue();
                return Arrays.asList(0xb8 | registerNumber, immediate, address);
            }

        }

        return new ArrayList<>();
    }, null),

    PUSH((locationCounter, operands) -> {
        Operand operand = operands.get(0);
        return Arrays.asList(0xc0, operand.getValue());
    }, null),

    CLR((locationCounter, operands) -> {
        Operand firstOperand = operands.get(0);
        switch (firstOperand.getType()) {

            case ACCUMULATOR: {
                return Collections.singletonList(0xe4);
            }

            case BIT: {
                int bit = firstOperand.getValue();
                return Arrays.asList(0xc2, bit);
            }

            case CARRY: {
                return Collections.singletonList(0xc3);
            }

        }

        return new ArrayList<>();
    }, null),

    SWAP((locationCounter, operands) -> {
        return Collections.singletonList(0xc4);
    }, null),

    XCH((locationCounter, operands) -> {
        Operand secondOperand = operands.get(1);
        switch (secondOperand.getType()) {

            case INDIRECT_REGISTER: {
                int registerNumber = secondOperand.getValue();
                return Collections.singletonList(0xc6 | registerNumber);
            }

            case DIRECT: {
                int direct = secondOperand.getValue();
                return Arrays.asList(0xc5, direct);
            }

            case REGISTER: {
                int registerNumber = secondOperand.getValue();
                return Collections.singletonList(0xc8 | registerNumber);
            }

        }

        return new ArrayList<>();
    }, null),

    POP((locationCounter, operands) -> {
        Operand operand = operands.get(0);
        return Arrays.asList(0xd0, operand.getValue());
    }, null),

    SETB((locationCounter, operands) -> {
        Operand firstOperand = operands.get(0);
        switch (firstOperand.getType()) {

            case BIT: {
                int bit = firstOperand.getValue();
                return Arrays.asList(0xd2, bit);
            }

            case CARRY: {
                return Collections.singletonList(0xd3);
            }

        }

        return new ArrayList<>();
    }, null),

    DA((locationCounter, operands) -> {
        return Collections.singletonList(0xd4);
    }, null),

    DJNZ((locationCounter, operands) -> {
        Operand firstOperand = operands.get(0);
        Operand secondOperand = operands.get(1);
        switch (firstOperand.getType()) {

            case DIRECT: {
                int direct = firstOperand.getValue();
                int offset = secondOperand.getValue() - (locationCounter + 3);
                return Arrays.asList(0xd5, direct, offset);
            }

            case REGISTER: {
                int registerNumber = firstOperand.getValue();
                int offset = secondOperand.getValue() - (locationCounter + 2);
                return Arrays.asList(0xd8 | registerNumber, offset);
            }

        }

        return new ArrayList<>();
    }, null),

    XCHD((locationCounter, operands) -> {
        Operand secondOperand = operands.get(1);
        int registerNumber = secondOperand.getValue();
        return Collections.singletonList(0xd6 | registerNumber);
    }, null),

    MOVX((locationCounter, operands) -> {
        Operand firstOperand = operands.get(0);
        Operand secondOperand = operands.get(1);
        switch (firstOperand.getType()) {

            case INDIRECT_REGISTER: {
                int registerNumber = firstOperand.getValue();
                return Collections.singletonList(0xf2 | registerNumber);
            }

            case ACCUMULATOR: {
                switch (secondOperand.getType()) {
                    case AT_DPTR: {
                        return Collections.singletonList(0xe0);
                    }
                    case INDIRECT_REGISTER: {
                        int registerNumber = secondOperand.getValue();
                        return Collections.singletonList(0xe2 | registerNumber);
                    }
                }
                break;
            }

        }

        return new ArrayList<>();
    }, null);

    private final AssemblyInstructionTranslator assemblyInstructionTranslator;
    private final MachineCodesTranslator machineCodesTranslator;

    Instruction(AssemblyInstructionTranslator assemblyInstructionTranslator, MachineCodesTranslator machineCodesTranslator) {
        this.assemblyInstructionTranslator = assemblyInstructionTranslator;
        this.machineCodesTranslator = machineCodesTranslator;
    }

    public List<Integer> toMachineCodes(Integer locationCounter, List<Operand> operands) {
        return assemblyInstructionTranslator.transform(locationCounter, operands);
    }

    public List<String> toAssemblyInstruction(List<Integer> machineCodes) {
        return machineCodesTranslator.transform(machineCodes);
    }

    private interface AssemblyInstructionTranslator {
        List<Integer> transform(Integer locationCounter, List<Operand> operands);
    }

    private interface MachineCodesTranslator {
        List<String> transform(List<Integer> machineCodes);
    }

}
