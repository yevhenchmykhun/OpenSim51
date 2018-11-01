package assembler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum Instruction {

    NOP(operands -> {
        ArrayList<Integer> machineCodes = new ArrayList<>();
        setValues(machineCodes, 0x00);
        return machineCodes;
    }, null),

    AJMP(operands -> {
        ArrayList<Integer> machineCodes = new ArrayList<>();

        Operand operand = operands.get(0);

        int address = operand.getValue();
        setValues(machineCodes, ((address & 0x700) >> 3) | 0x01, address & 0xff);

        return machineCodes;
    }, null),

    LJMP(operands -> {
        ArrayList<Integer> machineCodes = new ArrayList<>();

        Operand operand = operands.get(0);

        int address = operand.getValue();
        setValues(machineCodes, 0x02, (address >> 8) & 0xff, address & 0xff);

        return machineCodes;
    }, null),

    RR(operands -> {
        ArrayList<Integer> machineCodes = new ArrayList<>();
        setValues(machineCodes, 0x03);
        return machineCodes;
    }, null),

    INC(operands -> {
        ArrayList<Integer> machineCodes = new ArrayList<>();

        Operand operand = operands.get(0);
        switch (operand.getType()) {

            case INDIRECT_REGISTER: {
                int registerNumber = operand.getValue();
                setValues(machineCodes, 0x06 | registerNumber);
                break;
            }

            case ACCUMULATOR: {
                setValues(machineCodes, 0x04);
                break;
            }

            case DIRECT: {
                int direct = operand.getValue();
                setValues(machineCodes, 0x05, direct);
                break;
            }

            case DPTR: {
                setValues(machineCodes, 0xa3);
                break;
            }

            case REGISTER: {
                int registerNumber = operand.getValue();
                setValues(machineCodes, 0x08 | registerNumber);
                break;
            }

        }

        return machineCodes;
    }, null),

    MOV(operands -> {
        List<Integer> machineCodes = new ArrayList<>();

        Operand firstOperand = operands.get(0);
        Operand secondOperand = operands.get(1);

        switch (firstOperand.getType()) {

            case INDIRECT_REGISTER: {
                int registerNumber = firstOperand.getValue();
                switch (secondOperand.getType()) {
                    case IMMEDIATE: {
                        int immediate = secondOperand.getValue();
                        setValues(machineCodes, 0x76 | registerNumber, immediate);
                        break;
                    }
                    case ACCUMULATOR: {
                        setValues(machineCodes, 0xf6 | registerNumber);
                        break;
                    }
                    case DIRECT: {
                        int direct = secondOperand.getValue();
                        setValues(machineCodes, 0xa6 | registerNumber, direct);
                        break;
                    }
                }
                break;
            }

            case ACCUMULATOR: {
                switch (secondOperand.getType()) {
                    case IMMEDIATE: {
                        int immediate = secondOperand.getValue();
                        setValues(machineCodes, 0x74, immediate);
                        break;
                    }
                    case INDIRECT_REGISTER: {
                        int registerNumber = secondOperand.getValue();
                        setValues(machineCodes, 0xe6 | registerNumber);
                        break;
                    }
                    case DIRECT: {
                        int direct = secondOperand.getValue();
                        setValues(machineCodes, 0xe5, direct);
                        break;
                    }
                    case REGISTER: {
                        int registerNumber = secondOperand.getValue();
                        setValues(machineCodes, 0xe8 | registerNumber);
                        break;
                    }
                }
                break;
            }

            case BIT: {
                setValues(machineCodes, 0x92, secondOperand.getValue());
                break;
            }

            case CARRY: {
                setValues(machineCodes, 0xa2, secondOperand.getValue());
                break;
            }

            case DIRECT: {
                int direct = firstOperand.getValue();
                switch (secondOperand.getType()) {
                    case DIRECT: {
                        int srcDirect = secondOperand.getValue();
                        setValues(machineCodes, 0x85, srcDirect, direct);
                        break;
                    }
                    case IMMEDIATE: {
                        int immediate = secondOperand.getValue();
                        setValues(machineCodes, 0x75, direct, immediate);
                        break;
                    }
                    case INDIRECT_REGISTER: {
                        int registerNumber = secondOperand.getValue();
                        setValues(machineCodes, 0x86 | registerNumber, direct);
                        break;
                    }
                    case ACCUMULATOR: {
                        setValues(machineCodes, 0xf5, direct);
                        break;
                    }
                    case REGISTER: {
                        int registerNumber = secondOperand.getValue();
                        setValues(machineCodes, 0x88 | registerNumber, direct);
                        break;
                    }
                }
                break;
            }

            case DPTR: {
                int immediate = secondOperand.getValue();
                setValues(machineCodes, 0x90, (immediate >> 8) & 0xff, immediate & 0xff);
                break;
            }

            case REGISTER: {
                int registerNumber = firstOperand.getValue();
                switch (secondOperand.getType()) {
                    case IMMEDIATE: {
                        int immediate = secondOperand.getValue();
                        setValues(machineCodes, 0x78 | registerNumber, immediate);
                        break;
                    }
                    case ACCUMULATOR: {
                        setValues(machineCodes, 0xf8 | registerNumber);
                        break;
                    }
                    case DIRECT: {
                        int direct = secondOperand.getValue();
                        setValues(machineCodes, 0xa8 | registerNumber, direct);
                        break;
                    }
                }
                break;
            }

        }

        return machineCodes;
    }, null);

    private static final Map<String, Instruction> INSTRUCTION_BY_MNEMONIC;
    private static final Map<Integer, InstructionInfo> INSTRUCTION_INFO_BY_OPCODE;

    static {
        INSTRUCTION_BY_MNEMONIC = new HashMap<>();
        for (Instruction instruction : Instruction.values()) {
            INSTRUCTION_BY_MNEMONIC.put(instruction.name(), instruction);
        }

        INSTRUCTION_INFO_BY_OPCODE = new HashMap<>();
        INSTRUCTION_INFO_BY_OPCODE.put(0x00, new InstructionInfo(1, NOP));    // NOP
        INSTRUCTION_INFO_BY_OPCODE.put(0x01, new InstructionInfo(2, AJMP));    // AJMP	addr11
        INSTRUCTION_INFO_BY_OPCODE.put(0x02, new InstructionInfo(3, LJMP));    // LJMP	addr16
        INSTRUCTION_INFO_BY_OPCODE.put(0x03, new InstructionInfo(1, RR));    // RR	A
        INSTRUCTION_INFO_BY_OPCODE.put(0x04, new InstructionInfo(1, INC));    // INC	A
        INSTRUCTION_INFO_BY_OPCODE.put(0x05, new InstructionInfo(2, INC));    // INC	direct
        INSTRUCTION_INFO_BY_OPCODE.put(0x06, new InstructionInfo(1, INC));    // INC	@R0
        INSTRUCTION_INFO_BY_OPCODE.put(0x07, new InstructionInfo(1, INC));    // INC	@R1
        INSTRUCTION_INFO_BY_OPCODE.put(0x08, new InstructionInfo(1, INC));    // INC	R0
        INSTRUCTION_INFO_BY_OPCODE.put(0x09, new InstructionInfo(1, INC));    // INC	R1
        INSTRUCTION_INFO_BY_OPCODE.put(0x0A, new InstructionInfo(1, INC));    // INC	R2
        INSTRUCTION_INFO_BY_OPCODE.put(0x0B, new InstructionInfo(1, INC));    // INC	R3
        INSTRUCTION_INFO_BY_OPCODE.put(0x0C, new InstructionInfo(1, INC));    // INC	R4
        INSTRUCTION_INFO_BY_OPCODE.put(0x0D, new InstructionInfo(1, INC));    // INC	R5
        INSTRUCTION_INFO_BY_OPCODE.put(0x0E, new InstructionInfo(1, INC));    // INC	R6
        INSTRUCTION_INFO_BY_OPCODE.put(0x0F, new InstructionInfo(1, INC));    // INC	R7
//        INSTRUCTION_INFO_BY_OPCODE.put(0x10, 3);    // JBC	bit, offset
//        INSTRUCTION_INFO_BY_OPCODE.put(0x11, 2);    // ACALL	addr11
//        INSTRUCTION_INFO_BY_OPCODE.put(0x12, 3);    // LCALL	addr16
//        INSTRUCTION_INFO_BY_OPCODE.put(0x13, 1);    // RRC	A
//        INSTRUCTION_INFO_BY_OPCODE.put(0x14, 1);    // DEC	A
//        INSTRUCTION_INFO_BY_OPCODE.put(0x15, 2);    // DEC	direct
//        INSTRUCTION_INFO_BY_OPCODE.put(0x16, 1);    // DEC	@R0
//        INSTRUCTION_INFO_BY_OPCODE.put(0x17, 1);    // DEC	@R1
//        INSTRUCTION_INFO_BY_OPCODE.put(0x18, 1);    // DEC	R0
//        INSTRUCTION_INFO_BY_OPCODE.put(0x19, 1);    // DEC	R1
//        INSTRUCTION_INFO_BY_OPCODE.put(0x1A, 1);    // DEC	R2
//        INSTRUCTION_INFO_BY_OPCODE.put(0x1B, 1);    // DEC	R3
//        INSTRUCTION_INFO_BY_OPCODE.put(0x1C, 1);    // DEC	R4
//        INSTRUCTION_INFO_BY_OPCODE.put(0x1D, 1);    // DEC	R5
//        INSTRUCTION_INFO_BY_OPCODE.put(0x1E, 1);    // DEC	R6
//        INSTRUCTION_INFO_BY_OPCODE.put(0x1F, 1);    // DEC	R7
//        INSTRUCTION_INFO_BY_OPCODE.put(0x20, 3);    // JB	bit, offset
        INSTRUCTION_INFO_BY_OPCODE.put(0x21, new InstructionInfo(2, AJMP));    // AJMP	addr11
//        INSTRUCTION_INFO_BY_OPCODE.put(0x22, 1);    // RET
//        INSTRUCTION_INFO_BY_OPCODE.put(0x23, 1);    // RL	A
//        INSTRUCTION_INFO_BY_OPCODE.put(0x24, 2);    // ADD	A, #immed
//        INSTRUCTION_INFO_BY_OPCODE.put(0x25, 2);    // ADD	A, direct
//        INSTRUCTION_INFO_BY_OPCODE.put(0x26, 1);    // ADD	A, @R0
//        INSTRUCTION_INFO_BY_OPCODE.put(0x27, 1);    // ADD	A, @R1
//        INSTRUCTION_INFO_BY_OPCODE.put(0x28, 1);    // ADD	A, R0
//        INSTRUCTION_INFO_BY_OPCODE.put(0x29, 1);    // ADD	A, R1
//        INSTRUCTION_INFO_BY_OPCODE.put(0x2A, 1);    // ADD	A, R2
//        INSTRUCTION_INFO_BY_OPCODE.put(0x2B, 1);    // ADD	A, R3
//        INSTRUCTION_INFO_BY_OPCODE.put(0x2C, 1);    // ADD	A, R4
//        INSTRUCTION_INFO_BY_OPCODE.put(0x2D, 1);    // ADD	A, R5
//        INSTRUCTION_INFO_BY_OPCODE.put(0x2E, 1);    // ADD	A, R6
//        INSTRUCTION_INFO_BY_OPCODE.put(0x2F, 1);    // ADD	A, R7
//        INSTRUCTION_INFO_BY_OPCODE.put(0x30, 3);    // JNB	bit, offset
//        INSTRUCTION_INFO_BY_OPCODE.put(0x31, 2);    // ACALL	addr11
//        INSTRUCTION_INFO_BY_OPCODE.put(0x32, 1);    // RETI
//        INSTRUCTION_INFO_BY_OPCODE.put(0x33, 1);    // RLC	A
//        INSTRUCTION_INFO_BY_OPCODE.put(0x34, 2);    // ADDC	A, #immed
//        INSTRUCTION_INFO_BY_OPCODE.put(0x35, 2);    // ADDC	A, direct
//        INSTRUCTION_INFO_BY_OPCODE.put(0x36, 1);    // ADDC	A, @R0
//        INSTRUCTION_INFO_BY_OPCODE.put(0x37, 1);    // ADDC	A, @R1
//        INSTRUCTION_INFO_BY_OPCODE.put(0x38, 1);    // ADDC	A, R0
//        INSTRUCTION_INFO_BY_OPCODE.put(0x39, 1);    // ADDC	A, R1
//        INSTRUCTION_INFO_BY_OPCODE.put(0x3A, 1);    // ADDC	A, R2
//        INSTRUCTION_INFO_BY_OPCODE.put(0x3B, 1);    // ADDC	A, R3
//        INSTRUCTION_INFO_BY_OPCODE.put(0x3C, 1);    // ADDC	A, R4
//        INSTRUCTION_INFO_BY_OPCODE.put(0x3D, 1);    // ADDC	A, R5
//        INSTRUCTION_INFO_BY_OPCODE.put(0x3E, 1);    // ADDC	A, R6
//        INSTRUCTION_INFO_BY_OPCODE.put(0x3F, 1);    // ADDC	A, R7
//        INSTRUCTION_INFO_BY_OPCODE.put(0x40, 2);    // JC	offset
        INSTRUCTION_INFO_BY_OPCODE.put(0x41, new InstructionInfo(2, AJMP));    // AJMP	addr11
//        INSTRUCTION_INFO_BY_OPCODE.put(0x42, 2);    // ORL	direct, A
//        INSTRUCTION_INFO_BY_OPCODE.put(0x43, 3);    // ORL	direct, #immed
//        INSTRUCTION_INFO_BY_OPCODE.put(0x44, 2);    // ORL	A, #immed
//        INSTRUCTION_INFO_BY_OPCODE.put(0x45, 2);    // ORL	A, direct
//        INSTRUCTION_INFO_BY_OPCODE.put(0x46, 1);    // ORL	A, @R0
//        INSTRUCTION_INFO_BY_OPCODE.put(0x47, 1);    // ORL	A, @R1
//        INSTRUCTION_INFO_BY_OPCODE.put(0x48, 1);    // ORL	A, R0
//        INSTRUCTION_INFO_BY_OPCODE.put(0x49, 1);    // ORL	A, R1
//        INSTRUCTION_INFO_BY_OPCODE.put(0x4A, 1);    // ORL	A, R2
//        INSTRUCTION_INFO_BY_OPCODE.put(0x4B, 1);    // ORL	A, R3
//        INSTRUCTION_INFO_BY_OPCODE.put(0x4C, 1);    // ORL	A, R4
//        INSTRUCTION_INFO_BY_OPCODE.put(0x4D, 1);    // ORL	A, R5
//        INSTRUCTION_INFO_BY_OPCODE.put(0x4E, 1);    // ORL	A, R6
//        INSTRUCTION_INFO_BY_OPCODE.put(0x4F, 1);    // ORL	A, R7
//        INSTRUCTION_INFO_BY_OPCODE.put(0x50, 2);    // JNC	offset
//        INSTRUCTION_INFO_BY_OPCODE.put(0x51, 2);    // ACALL	addr11
//        INSTRUCTION_INFO_BY_OPCODE.put(0x52, 2);    // ANL	direct, A
//        INSTRUCTION_INFO_BY_OPCODE.put(0x53, 3);    // ANL	direct, #immed
//        INSTRUCTION_INFO_BY_OPCODE.put(0x54, 2);    // ANL	A, #immed
//        INSTRUCTION_INFO_BY_OPCODE.put(0x55, 2);    // ANL	A, direct
//        INSTRUCTION_INFO_BY_OPCODE.put(0x56, 1);    // ANL	A, @R0
//        INSTRUCTION_INFO_BY_OPCODE.put(0x57, 1);    // ANL	A, @R1
//        INSTRUCTION_INFO_BY_OPCODE.put(0x58, 1);    // ANL	A, R0
//        INSTRUCTION_INFO_BY_OPCODE.put(0x59, 1);    // ANL	A, R1
//        INSTRUCTION_INFO_BY_OPCODE.put(0x5A, 1);    // ANL	A, R2
//        INSTRUCTION_INFO_BY_OPCODE.put(0x5B, 1);    // ANL	A, R3
//        INSTRUCTION_INFO_BY_OPCODE.put(0x5C, 1);    // ANL	A, R4
//        INSTRUCTION_INFO_BY_OPCODE.put(0x5D, 1);    // ANL	A, R5
//        INSTRUCTION_INFO_BY_OPCODE.put(0x5E, 1);    // ANL	A, R6
//        INSTRUCTION_INFO_BY_OPCODE.put(0x5F, 1);    // ANL	A, R7
//        INSTRUCTION_INFO_BY_OPCODE.put(0x60, 2);    // JZ	offset
        INSTRUCTION_INFO_BY_OPCODE.put(0x61, new InstructionInfo(2, AJMP));    // AJMP	addr11
//        INSTRUCTION_INFO_BY_OPCODE.put(0x62, 2);    // XRL	direct, A
//        INSTRUCTION_INFO_BY_OPCODE.put(0x63, 3);    // XRL	direct, #immed
//        INSTRUCTION_INFO_BY_OPCODE.put(0x64, 2);    // XRL	A, #immed
//        INSTRUCTION_INFO_BY_OPCODE.put(0x65, 2);    // XRL	A, direct
//        INSTRUCTION_INFO_BY_OPCODE.put(0x66, 1);    // XRL	A, @R0
//        INSTRUCTION_INFO_BY_OPCODE.put(0x67, 1);    // XRL	A, @R1
//        INSTRUCTION_INFO_BY_OPCODE.put(0x68, 1);    // XRL	A, R0
//        INSTRUCTION_INFO_BY_OPCODE.put(0x69, 1);    // XRL	A, R1
//        INSTRUCTION_INFO_BY_OPCODE.put(0x6A, 1);    // XRL	A, R2
//        INSTRUCTION_INFO_BY_OPCODE.put(0x6B, 1);    // XRL	A, R3
//        INSTRUCTION_INFO_BY_OPCODE.put(0x6C, 1);    // XRL	A, R4
//        INSTRUCTION_INFO_BY_OPCODE.put(0x6D, 1);    // XRL	A, R5
//        INSTRUCTION_INFO_BY_OPCODE.put(0x6E, 1);    // XRL	A, R6
//        INSTRUCTION_INFO_BY_OPCODE.put(0x6F, 1);    // XRL	A, R7
//        INSTRUCTION_INFO_BY_OPCODE.put(0x70, 2);    // JNZ	offset
//        INSTRUCTION_INFO_BY_OPCODE.put(0x71, 2);    // ACALL	addr11
//        INSTRUCTION_INFO_BY_OPCODE.put(0x72, 2);    // ORL	C, bit
//        INSTRUCTION_INFO_BY_OPCODE.put(0x73, 1);    // JMP	@A+DPTR
        INSTRUCTION_INFO_BY_OPCODE.put(0x74, new InstructionInfo(2, MOV));    // MOV	A, #immed
        INSTRUCTION_INFO_BY_OPCODE.put(0x75, new InstructionInfo(3, MOV));    // MOV	direct, #immed
        INSTRUCTION_INFO_BY_OPCODE.put(0x76, new InstructionInfo(2, MOV));    // MOV	@R0, #immed
        INSTRUCTION_INFO_BY_OPCODE.put(0x77, new InstructionInfo(2, MOV));    // MOV	@R1, #immed
        INSTRUCTION_INFO_BY_OPCODE.put(0x78, new InstructionInfo(2, MOV));    // MOV	R0, #immed
        INSTRUCTION_INFO_BY_OPCODE.put(0x79, new InstructionInfo(2, MOV));    // MOV	R1, #immed
        INSTRUCTION_INFO_BY_OPCODE.put(0x7A, new InstructionInfo(2, MOV));    // MOV	R2, #immed
        INSTRUCTION_INFO_BY_OPCODE.put(0x7B, new InstructionInfo(2, MOV));    // MOV	R3, #immed
        INSTRUCTION_INFO_BY_OPCODE.put(0x7C, new InstructionInfo(2, MOV));    // MOV	R4, #immed
        INSTRUCTION_INFO_BY_OPCODE.put(0x7D, new InstructionInfo(2, MOV));    // MOV	R5, #immed
        INSTRUCTION_INFO_BY_OPCODE.put(0x7E, new InstructionInfo(2, MOV));    // MOV	R6, #immed
        INSTRUCTION_INFO_BY_OPCODE.put(0x7F, new InstructionInfo(2, MOV));    // MOV	R7, #immed

//        INSTRUCTION_INFO_BY_OPCODE.put(0x80, 2);    // SJMP	offset
        INSTRUCTION_INFO_BY_OPCODE.put(0x81, new InstructionInfo(2, AJMP));    // AJMP	addr11
//        INSTRUCTION_INFO_BY_OPCODE.put(0x82, 2);    // ANL	C, bit
//        INSTRUCTION_INFO_BY_OPCODE.put(0x83, 1);    // MOVC	A, @A+PC
//        INSTRUCTION_INFO_BY_OPCODE.put(0x84, 1);    // DIV	AB
        INSTRUCTION_INFO_BY_OPCODE.put(0x85, new InstructionInfo(3, MOV));    // MOV	direct, direct
        INSTRUCTION_INFO_BY_OPCODE.put(0x86, new InstructionInfo(2, MOV));    // MOV	direct, @R0
        INSTRUCTION_INFO_BY_OPCODE.put(0x87, new InstructionInfo(2, MOV));    // MOV	direct, @R1
        INSTRUCTION_INFO_BY_OPCODE.put(0x88, new InstructionInfo(2, MOV));    // MOV	direct, R0
        INSTRUCTION_INFO_BY_OPCODE.put(0x89, new InstructionInfo(2, MOV));    // MOV	direct, R1
        INSTRUCTION_INFO_BY_OPCODE.put(0x8A, new InstructionInfo(2, MOV));    // MOV	direct, R2
        INSTRUCTION_INFO_BY_OPCODE.put(0x8B, new InstructionInfo(2, MOV));    // MOV	direct, R3
        INSTRUCTION_INFO_BY_OPCODE.put(0x8C, new InstructionInfo(2, MOV));    // MOV	direct, R4
        INSTRUCTION_INFO_BY_OPCODE.put(0x8D, new InstructionInfo(2, MOV));    // MOV	direct, R5
        INSTRUCTION_INFO_BY_OPCODE.put(0x8E, new InstructionInfo(2, MOV));    // MOV	direct, R6
        INSTRUCTION_INFO_BY_OPCODE.put(0x8F, new InstructionInfo(2, MOV));    // MOV	direct, R7
        INSTRUCTION_INFO_BY_OPCODE.put(0x90, new InstructionInfo(3, MOV));    // MOV	DPTR, #immed
//        INSTRUCTION_INFO_BY_OPCODE.put(0x91, 2);    // ACALL	addr11
        INSTRUCTION_INFO_BY_OPCODE.put(0x92, new InstructionInfo(2, MOV));    // MOV	bit, C
//        INSTRUCTION_INFO_BY_OPCODE.put(0x93, 1);    // MOVC	A, @A+DPTR
//        INSTRUCTION_INFO_BY_OPCODE.put(0x94, 2);    // SUBB	A, #immed
//        INSTRUCTION_INFO_BY_OPCODE.put(0x95, 2);    // SUBB	A, direct
//        INSTRUCTION_INFO_BY_OPCODE.put(0x96, 1);    // SUBB	A, @R0
//        INSTRUCTION_INFO_BY_OPCODE.put(0x97, 1);    // SUBB	A, @R1
//        INSTRUCTION_INFO_BY_OPCODE.put(0x98, 1);    // SUBB	A, R0
//        INSTRUCTION_INFO_BY_OPCODE.put(0x99, 1);    // SUBB	A, R1
//        INSTRUCTION_INFO_BY_OPCODE.put(0x9A, 1);    // SUBB	A, R2
//        INSTRUCTION_INFO_BY_OPCODE.put(0x9B, 1);    // SUBB	A, R3
//        INSTRUCTION_INFO_BY_OPCODE.put(0x9C, 1);    // SUBB	A, R4
//        INSTRUCTION_INFO_BY_OPCODE.put(0x9D, 1);    // SUBB	A, R5
//        INSTRUCTION_INFO_BY_OPCODE.put(0x9E, 1);    // SUBB	A, R6
//        INSTRUCTION_INFO_BY_OPCODE.put(0x9F, 1);    // SUBB	A, R7
//        INSTRUCTION_INFO_BY_OPCODE.put(0xA0, 2);    // ORL	C, /bit
        INSTRUCTION_INFO_BY_OPCODE.put(0xA1, new InstructionInfo(2, AJMP));    // AJMP	addr11
        INSTRUCTION_INFO_BY_OPCODE.put(0xA2, new InstructionInfo(2, MOV));    // MOV	C, bit
        INSTRUCTION_INFO_BY_OPCODE.put(0xA3, new InstructionInfo(1, INC));    // INC	DPTR
//        INSTRUCTION_INFO_BY_OPCODE.put(0xA4, 1);    // MUL	AB
//        INSTRUCTION_INFO_BY_OPCODE.put(0xA5, 0);    // reserved
        INSTRUCTION_INFO_BY_OPCODE.put(0xA6, new InstructionInfo(2, MOV));    // MOV	@R0, direct
        INSTRUCTION_INFO_BY_OPCODE.put(0xA7, new InstructionInfo(2, MOV));    // MOV	@R1, direct
        INSTRUCTION_INFO_BY_OPCODE.put(0xA8, new InstructionInfo(2, MOV));    // MOV	R0, direct
        INSTRUCTION_INFO_BY_OPCODE.put(0xA9, new InstructionInfo(2, MOV));    // MOV	R1, direct
        INSTRUCTION_INFO_BY_OPCODE.put(0xAA, new InstructionInfo(2, MOV));    // MOV	R2, direct
        INSTRUCTION_INFO_BY_OPCODE.put(0xAB, new InstructionInfo(2, MOV));    // MOV	R3, direct
        INSTRUCTION_INFO_BY_OPCODE.put(0xAC, new InstructionInfo(2, MOV));    // MOV	R4, direct
        INSTRUCTION_INFO_BY_OPCODE.put(0xAD, new InstructionInfo(2, MOV));    // MOV	R5, direct
        INSTRUCTION_INFO_BY_OPCODE.put(0xAE, new InstructionInfo(2, MOV));    // MOV	R6, direct
        INSTRUCTION_INFO_BY_OPCODE.put(0xAF, new InstructionInfo(2, MOV));    // MOV	R7, direct
//        INSTRUCTION_INFO_BY_OPCODE.put(0xB0, 2);    // ANL	C, /bit
//        INSTRUCTION_INFO_BY_OPCODE.put(0xB1, 2);    // ACALL	addr11
//        INSTRUCTION_INFO_BY_OPCODE.put(0xB2, 2);    // CPL	bit
//        INSTRUCTION_INFO_BY_OPCODE.put(0xB3, 1);    // CPL	C
//        INSTRUCTION_INFO_BY_OPCODE.put(0xB4, 3);    // CJNE	A, #immed, offset
//        INSTRUCTION_INFO_BY_OPCODE.put(0xB5, 3);    // CJNE	A, direct, offset
//        INSTRUCTION_INFO_BY_OPCODE.put(0xB6, 3);    // CJNE	@R0, #immed, offset
//        INSTRUCTION_INFO_BY_OPCODE.put(0xB7, 3);    // CJNE	@R1, #immed, offset
//        INSTRUCTION_INFO_BY_OPCODE.put(0xB8, 3);    // CJNE	R0, #immed, offset
//        INSTRUCTION_INFO_BY_OPCODE.put(0xB9, 3);    // CJNE	R1, #immed, offset
//        INSTRUCTION_INFO_BY_OPCODE.put(0xBA, 3);    // CJNE	R2, #immed, offset
//        INSTRUCTION_INFO_BY_OPCODE.put(0xBB, 3);    // CJNE	R3, #immed, offset
//        INSTRUCTION_INFO_BY_OPCODE.put(0xBC, 3);    // CJNE	R4, #immed, offset
//        INSTRUCTION_INFO_BY_OPCODE.put(0xBD, 3);    // CJNE	R5, #immed, offset
//        INSTRUCTION_INFO_BY_OPCODE.put(0xBE, 3);    // CJNE	R6, #immed, offset
//        INSTRUCTION_INFO_BY_OPCODE.put(0xBF, 3);    // CJNE	R7, #immed, offset
//        INSTRUCTION_INFO_BY_OPCODE.put(0xC0, 2);    // PUSH	direct
        INSTRUCTION_INFO_BY_OPCODE.put(0xC1, new InstructionInfo(2, AJMP));    // AJMP	addr11
//        INSTRUCTION_INFO_BY_OPCODE.put(0xC2, 2);    // CLR	bit
//        INSTRUCTION_INFO_BY_OPCODE.put(0xC3, 1);    // CLR	C
//        INSTRUCTION_INFO_BY_OPCODE.put(0xC4, 1);    // SWAP	A
//        INSTRUCTION_INFO_BY_OPCODE.put(0xC5, 2);    // XCH	A, direct
//        INSTRUCTION_INFO_BY_OPCODE.put(0xC6, 1);    // XCH	A, @R0
//        INSTRUCTION_INFO_BY_OPCODE.put(0xC7, 1);    // XCH	A, @R1
//        INSTRUCTION_INFO_BY_OPCODE.put(0xC8, 1);    // XCH	A, R0
//        INSTRUCTION_INFO_BY_OPCODE.put(0xC9, 1);    // XCH	A, R1
//        INSTRUCTION_INFO_BY_OPCODE.put(0xCA, 1);    // XCH	A, R2
//        INSTRUCTION_INFO_BY_OPCODE.put(0xCB, 1);    // XCH	A, R3
//        INSTRUCTION_INFO_BY_OPCODE.put(0xCC, 1);    // XCH	A, R4
//        INSTRUCTION_INFO_BY_OPCODE.put(0xCD, 1);    // XCH	A, R5
//        INSTRUCTION_INFO_BY_OPCODE.put(0xCE, 1);    // XCH	A, R6
//        INSTRUCTION_INFO_BY_OPCODE.put(0xCF, 1);    // XCH	A, R7
//        INSTRUCTION_INFO_BY_OPCODE.put(0xD0, 2);    // POP	direct
//        INSTRUCTION_INFO_BY_OPCODE.put(0xD1, 2);    // ACALL	addr11
//        INSTRUCTION_INFO_BY_OPCODE.put(0xD2, 2);    // SETB	bit
//        INSTRUCTION_INFO_BY_OPCODE.put(0xD3, 1);    // SETB	C
//        INSTRUCTION_INFO_BY_OPCODE.put(0xD4, 1);    // DA	A
//        INSTRUCTION_INFO_BY_OPCODE.put(0xD5, 3);    // DJNZ	direct, offset
//        INSTRUCTION_INFO_BY_OPCODE.put(0xD6, 1);    // XCHD	A, @R0
//        INSTRUCTION_INFO_BY_OPCODE.put(0xD7, 1);    // XCHD	A, @R1
//        INSTRUCTION_INFO_BY_OPCODE.put(0xD8, 2);    // DJNZ	R0, offset
//        INSTRUCTION_INFO_BY_OPCODE.put(0xD9, 2);    // DJNZ	R1, offset
//        INSTRUCTION_INFO_BY_OPCODE.put(0xDA, 2);    // DJNZ	R2, offset
//        INSTRUCTION_INFO_BY_OPCODE.put(0xDB, 2);    // DJNZ	R3, offset
//        INSTRUCTION_INFO_BY_OPCODE.put(0xDC, 2);    // DJNZ	R4, offset
//        INSTRUCTION_INFO_BY_OPCODE.put(0xDD, 2);    // DJNZ	R5, offset
//        INSTRUCTION_INFO_BY_OPCODE.put(0xDE, 2);    // DJNZ	R6, offset
//        INSTRUCTION_INFO_BY_OPCODE.put(0xDF, 2);    // DJNZ	R7, offset
//        INSTRUCTION_INFO_BY_OPCODE.put(0xE0, 1);    // MOVX	A, @DPTR
        INSTRUCTION_INFO_BY_OPCODE.put(0xE1, new InstructionInfo(2, AJMP));    // AJMP	addr11
//        INSTRUCTION_INFO_BY_OPCODE.put(0xE2, 1);    // MOVX	A, @R0
//        INSTRUCTION_INFO_BY_OPCODE.put(0xE3, 1);    // MOVX	A, @R1
//        INSTRUCTION_INFO_BY_OPCODE.put(0xE4, 1);    // CLR	A
        INSTRUCTION_INFO_BY_OPCODE.put(0xE5, new InstructionInfo(2, MOV));    // MOV	A, direct
        INSTRUCTION_INFO_BY_OPCODE.put(0xE6, new InstructionInfo(1, MOV));    // MOV	A, @R0
        INSTRUCTION_INFO_BY_OPCODE.put(0xE7, new InstructionInfo(1, MOV));    // MOV	A, @R1
        INSTRUCTION_INFO_BY_OPCODE.put(0xE8, new InstructionInfo(1, MOV));    // MOV	A, R0
        INSTRUCTION_INFO_BY_OPCODE.put(0xE9, new InstructionInfo(1, MOV));    // MOV	A, R1
        INSTRUCTION_INFO_BY_OPCODE.put(0xEA, new InstructionInfo(1, MOV));    // MOV	A, R2
        INSTRUCTION_INFO_BY_OPCODE.put(0xEB, new InstructionInfo(1, MOV));    // MOV	A, R3
        INSTRUCTION_INFO_BY_OPCODE.put(0xEC, new InstructionInfo(1, MOV));    // MOV	A, R4
        INSTRUCTION_INFO_BY_OPCODE.put(0xED, new InstructionInfo(1, MOV));    // MOV	A, R5
        INSTRUCTION_INFO_BY_OPCODE.put(0xEE, new InstructionInfo(1, MOV));    // MOV	A, R6
        INSTRUCTION_INFO_BY_OPCODE.put(0xEF, new InstructionInfo(1, MOV));    // MOV	A, R7
//        INSTRUCTION_INFO_BY_OPCODE.put(0xF0, 1);    // MOVX	@DPTR, A
//        INSTRUCTION_INFO_BY_OPCODE.put(0xF1, 2);    // ACALL	addr11
//        INSTRUCTION_INFO_BY_OPCODE.put(0xF2, 1);    // MOVX	@R0, A
//        INSTRUCTION_INFO_BY_OPCODE.put(0xF3, 1);    // MOVX	@R1, A
//        INSTRUCTION_INFO_BY_OPCODE.put(0xF4, 1);    // CPL	A
        INSTRUCTION_INFO_BY_OPCODE.put(0xF5, new InstructionInfo(2, MOV));    // MOV	direct, A
        INSTRUCTION_INFO_BY_OPCODE.put(0xF6, new InstructionInfo(1, MOV));    // MOV	@R0, A
        INSTRUCTION_INFO_BY_OPCODE.put(0xF7, new InstructionInfo(1, MOV));    // MOV	@R1, A
        INSTRUCTION_INFO_BY_OPCODE.put(0xF8, new InstructionInfo(1, MOV));    // MOV	R0, A
        INSTRUCTION_INFO_BY_OPCODE.put(0xF9, new InstructionInfo(1, MOV));    // MOV	R1, A
        INSTRUCTION_INFO_BY_OPCODE.put(0xFA, new InstructionInfo(1, MOV));    // MOV	R2, A
        INSTRUCTION_INFO_BY_OPCODE.put(0xFB, new InstructionInfo(1, MOV));    // MOV	R3, A
        INSTRUCTION_INFO_BY_OPCODE.put(0xFC, new InstructionInfo(1, MOV));    // MOV	R4, A
        INSTRUCTION_INFO_BY_OPCODE.put(0xFD, new InstructionInfo(1, MOV));    // MOV	R5, A
        INSTRUCTION_INFO_BY_OPCODE.put(0xFE, new InstructionInfo(1, MOV));    // MOV	R6, A
        INSTRUCTION_INFO_BY_OPCODE.put(0xFF, new InstructionInfo(1, MOV));    // MOV	R7, A
    }

    private AssemblyInstructionTransformer assemblyInstructionTransformer;
    private MachineCodesTransformer machineCodesTransformer;

    Instruction(AssemblyInstructionTransformer assemblyInstructionTransformer, MachineCodesTransformer machineCodesTransformer) {
        this.assemblyInstructionTransformer = assemblyInstructionTransformer;
        this.machineCodesTransformer = machineCodesTransformer;
    }

    public static Instruction getByMnemonic(String mnemonic) {
        return INSTRUCTION_BY_MNEMONIC.get(mnemonic);
    }

    public static Instruction getByOpcode(int opcode) {
        return INSTRUCTION_INFO_BY_OPCODE.get(opcode).instruction;
    }

    public static int getInstructionLength(int opcode) {
        return INSTRUCTION_INFO_BY_OPCODE.get(opcode).bytes;
    }

    public List<Integer> toMachineCodes(List<Operand> operands) {
        return assemblyInstructionTransformer.transform(operands);
    }

    public List<String> toAssemblyInstruction(List<Integer> machineCodes) {
        return machineCodesTransformer.transform(machineCodes);
    }

    private static void setValues(List<Integer> machineCodes, int... values) {
        for (int value : values) {
            machineCodes.add(value);
        }
    }

    private interface AssemblyInstructionTransformer {
        List<Integer> transform(List<Operand> operands);
    }

    private interface MachineCodesTransformer {
        List<String> transform(List<Integer> machineCodes);
    }

    private static class InstructionInfo {
        private int bytes;
        private Instruction instruction;

        InstructionInfo(int bytes, Instruction instruction) {
            this.bytes = bytes;
            this.instruction = instruction;
        }
    }

}