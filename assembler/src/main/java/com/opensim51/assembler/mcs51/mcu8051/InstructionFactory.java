package com.opensim51.assembler.mcs51.mcu8051;

import java.util.HashMap;
import java.util.Map;

import static com.opensim51.assembler.mcs51.mcu8051.Instruction.*;

public class InstructionFactory {
    private static final Map<String, Instruction> INSTRUCTION_BY_MNEMONIC;
    private static final Map<Integer, InstructionInfo> INSTRUCTION_INFO_BY_OPCODE;

    static {
        INSTRUCTION_BY_MNEMONIC = new HashMap<>();
        for (Instruction instruction : Instruction.values()) {
            InstructionFactory.INSTRUCTION_BY_MNEMONIC.put(instruction.name(), instruction);
        }

        INSTRUCTION_INFO_BY_OPCODE = new HashMap<>();
        INSTRUCTION_INFO_BY_OPCODE.put(0x00, new InstructionInfo(1, NOP));      // NOP
        INSTRUCTION_INFO_BY_OPCODE.put(0x01, new InstructionInfo(2, AJMP));     // AJMP	addr11
        INSTRUCTION_INFO_BY_OPCODE.put(0x02, new InstructionInfo(3, LJMP));     // LJMP	addr16
        INSTRUCTION_INFO_BY_OPCODE.put(0x03, new InstructionInfo(1, RR));       // RR	A
        INSTRUCTION_INFO_BY_OPCODE.put(0x04, new InstructionInfo(1, INC));      // INC	A
        INSTRUCTION_INFO_BY_OPCODE.put(0x05, new InstructionInfo(2, INC));      // INC	direct
        INSTRUCTION_INFO_BY_OPCODE.put(0x06, new InstructionInfo(1, INC));      // INC	@R0
        INSTRUCTION_INFO_BY_OPCODE.put(0x07, new InstructionInfo(1, INC));      // INC	@R1
        INSTRUCTION_INFO_BY_OPCODE.put(0x08, new InstructionInfo(1, INC));      // INC	R0
        INSTRUCTION_INFO_BY_OPCODE.put(0x09, new InstructionInfo(1, INC));      // INC	R1
        INSTRUCTION_INFO_BY_OPCODE.put(0x0A, new InstructionInfo(1, INC));      // INC	R2
        INSTRUCTION_INFO_BY_OPCODE.put(0x0B, new InstructionInfo(1, INC));      // INC	R3
        INSTRUCTION_INFO_BY_OPCODE.put(0x0C, new InstructionInfo(1, INC));      // INC	R4
        INSTRUCTION_INFO_BY_OPCODE.put(0x0D, new InstructionInfo(1, INC));      // INC	R5
        INSTRUCTION_INFO_BY_OPCODE.put(0x0E, new InstructionInfo(1, INC));      // INC	R6
        INSTRUCTION_INFO_BY_OPCODE.put(0x0F, new InstructionInfo(1, INC));      // INC	R7
        INSTRUCTION_INFO_BY_OPCODE.put(0x10, new InstructionInfo(3, JBC));      // JBC	bit, offset
        INSTRUCTION_INFO_BY_OPCODE.put(0x11, new InstructionInfo(2, ACALL));    // ACALL	addr11
        INSTRUCTION_INFO_BY_OPCODE.put(0x12, new InstructionInfo(3, LCALL));    // LCALL	addr16
        INSTRUCTION_INFO_BY_OPCODE.put(0x13, new InstructionInfo(1, RRC));      // RRC	A
        INSTRUCTION_INFO_BY_OPCODE.put(0x14, new InstructionInfo(1, DEC));      // DEC	A
        INSTRUCTION_INFO_BY_OPCODE.put(0x15, new InstructionInfo(2, DEC));      // DEC	direct
        INSTRUCTION_INFO_BY_OPCODE.put(0x16, new InstructionInfo(1, DEC));      // DEC	@R0
        INSTRUCTION_INFO_BY_OPCODE.put(0x17, new InstructionInfo(1, DEC));      // DEC	@R1
        INSTRUCTION_INFO_BY_OPCODE.put(0x18, new InstructionInfo(1, DEC));      // DEC	R0
        INSTRUCTION_INFO_BY_OPCODE.put(0x19, new InstructionInfo(1, DEC));      // DEC	R1
        INSTRUCTION_INFO_BY_OPCODE.put(0x1A, new InstructionInfo(1, DEC));      // DEC	R2
        INSTRUCTION_INFO_BY_OPCODE.put(0x1B, new InstructionInfo(1, DEC));      // DEC	R3
        INSTRUCTION_INFO_BY_OPCODE.put(0x1C, new InstructionInfo(1, DEC));      // DEC	R4
        INSTRUCTION_INFO_BY_OPCODE.put(0x1D, new InstructionInfo(1, DEC));      // DEC	R5
        INSTRUCTION_INFO_BY_OPCODE.put(0x1E, new InstructionInfo(1, DEC));      // DEC	R6
        INSTRUCTION_INFO_BY_OPCODE.put(0x1F, new InstructionInfo(1, DEC));      // DEC	R7
        INSTRUCTION_INFO_BY_OPCODE.put(0x20, new InstructionInfo(3, JB));       // JB	bit, offset
        INSTRUCTION_INFO_BY_OPCODE.put(0x21, new InstructionInfo(2, AJMP));     // AJMP	addr11
        INSTRUCTION_INFO_BY_OPCODE.put(0x22, new InstructionInfo(1, RET));      // RET
        INSTRUCTION_INFO_BY_OPCODE.put(0x23, new InstructionInfo(1, RL));       // RL	A
        INSTRUCTION_INFO_BY_OPCODE.put(0x24, new InstructionInfo(2, ADD));      // ADD	A, #immed
        INSTRUCTION_INFO_BY_OPCODE.put(0x25, new InstructionInfo(2, ADD));      // ADD	A, direct
        INSTRUCTION_INFO_BY_OPCODE.put(0x26, new InstructionInfo(1, ADD));      // ADD	A, @R0
        INSTRUCTION_INFO_BY_OPCODE.put(0x27, new InstructionInfo(1, ADD));      // ADD	A, @R1
        INSTRUCTION_INFO_BY_OPCODE.put(0x28, new InstructionInfo(1, ADD));      // ADD	A, R0
        INSTRUCTION_INFO_BY_OPCODE.put(0x29, new InstructionInfo(1, ADD));      // ADD	A, R1
        INSTRUCTION_INFO_BY_OPCODE.put(0x2A, new InstructionInfo(1, ADD));      // ADD	A, R2
        INSTRUCTION_INFO_BY_OPCODE.put(0x2B, new InstructionInfo(1, ADD));      // ADD	A, R3
        INSTRUCTION_INFO_BY_OPCODE.put(0x2C, new InstructionInfo(1, ADD));      // ADD	A, R4
        INSTRUCTION_INFO_BY_OPCODE.put(0x2D, new InstructionInfo(1, ADD));      // ADD	A, R5
        INSTRUCTION_INFO_BY_OPCODE.put(0x2E, new InstructionInfo(1, ADD));      // ADD	A, R6
        INSTRUCTION_INFO_BY_OPCODE.put(0x2F, new InstructionInfo(1, ADD));      // ADD	A, R7
        INSTRUCTION_INFO_BY_OPCODE.put(0x30, new InstructionInfo(3, JNB));      // JNB	bit, offset
        INSTRUCTION_INFO_BY_OPCODE.put(0x31, new InstructionInfo(2, ACALL));    // ACALL	addr11
        INSTRUCTION_INFO_BY_OPCODE.put(0x32, new InstructionInfo(1, RETI));     // RETI
        INSTRUCTION_INFO_BY_OPCODE.put(0x33, new InstructionInfo(1, RLC));      // RLC	A
        INSTRUCTION_INFO_BY_OPCODE.put(0x34, new InstructionInfo(2, ADDC));     // ADDC	A, #immed
        INSTRUCTION_INFO_BY_OPCODE.put(0x35, new InstructionInfo(2, ADDC));     // ADDC	A, direct
        INSTRUCTION_INFO_BY_OPCODE.put(0x36, new InstructionInfo(1, ADDC));     // ADDC	A, @R0
        INSTRUCTION_INFO_BY_OPCODE.put(0x37, new InstructionInfo(1, ADDC));     // ADDC	A, @R1
        INSTRUCTION_INFO_BY_OPCODE.put(0x38, new InstructionInfo(1, ADDC));     // ADDC	A, R0
        INSTRUCTION_INFO_BY_OPCODE.put(0x39, new InstructionInfo(1, ADDC));     // ADDC	A, R1
        INSTRUCTION_INFO_BY_OPCODE.put(0x3A, new InstructionInfo(1, ADDC));     // ADDC	A, R2
        INSTRUCTION_INFO_BY_OPCODE.put(0x3B, new InstructionInfo(1, ADDC));     // ADDC	A, R3
        INSTRUCTION_INFO_BY_OPCODE.put(0x3C, new InstructionInfo(1, ADDC));     // ADDC	A, R4
        INSTRUCTION_INFO_BY_OPCODE.put(0x3D, new InstructionInfo(1, ADDC));     // ADDC	A, R5
        INSTRUCTION_INFO_BY_OPCODE.put(0x3E, new InstructionInfo(1, ADDC));     // ADDC	A, R6
        INSTRUCTION_INFO_BY_OPCODE.put(0x3F, new InstructionInfo(1, ADDC));     // ADDC	A, R7
        INSTRUCTION_INFO_BY_OPCODE.put(0x40, new InstructionInfo(2, JC));       // JC	offset
        INSTRUCTION_INFO_BY_OPCODE.put(0x41, new InstructionInfo(2, AJMP));     // AJMP	addr11
        INSTRUCTION_INFO_BY_OPCODE.put(0x42, new InstructionInfo(2, ORL));      // ORL	direct, A
        INSTRUCTION_INFO_BY_OPCODE.put(0x43, new InstructionInfo(3, ORL));      // ORL	direct, #immed
        INSTRUCTION_INFO_BY_OPCODE.put(0x44, new InstructionInfo(2, ORL));      // ORL	A, #immed
        INSTRUCTION_INFO_BY_OPCODE.put(0x45, new InstructionInfo(2, ORL));      // ORL	A, direct
        INSTRUCTION_INFO_BY_OPCODE.put(0x46, new InstructionInfo(1, ORL));      // ORL	A, @R0
        INSTRUCTION_INFO_BY_OPCODE.put(0x47, new InstructionInfo(1, ORL));      // ORL	A, @R1
        INSTRUCTION_INFO_BY_OPCODE.put(0x48, new InstructionInfo(1, ORL));      // ORL	A, R0
        INSTRUCTION_INFO_BY_OPCODE.put(0x49, new InstructionInfo(1, ORL));      // ORL	A, R1
        INSTRUCTION_INFO_BY_OPCODE.put(0x4A, new InstructionInfo(1, ORL));      // ORL	A, R2
        INSTRUCTION_INFO_BY_OPCODE.put(0x4B, new InstructionInfo(1, ORL));      // ORL	A, R3
        INSTRUCTION_INFO_BY_OPCODE.put(0x4C, new InstructionInfo(1, ORL));      // ORL	A, R4
        INSTRUCTION_INFO_BY_OPCODE.put(0x4D, new InstructionInfo(1, ORL));      // ORL	A, R5
        INSTRUCTION_INFO_BY_OPCODE.put(0x4E, new InstructionInfo(1, ORL));      // ORL	A, R6
        INSTRUCTION_INFO_BY_OPCODE.put(0x4F, new InstructionInfo(1, ORL));      // ORL	A, R7
        INSTRUCTION_INFO_BY_OPCODE.put(0x50, new InstructionInfo(2, JNC));      // JNC	offset
        INSTRUCTION_INFO_BY_OPCODE.put(0x51, new InstructionInfo(2, ACALL));    // ACALL	addr11
        INSTRUCTION_INFO_BY_OPCODE.put(0x52, new InstructionInfo(2, ANL));      // ANL	direct, A
        INSTRUCTION_INFO_BY_OPCODE.put(0x53, new InstructionInfo(3, ANL));      // ANL	direct, #immed
        INSTRUCTION_INFO_BY_OPCODE.put(0x54, new InstructionInfo(2, ANL));      // ANL	A, #immed
        INSTRUCTION_INFO_BY_OPCODE.put(0x55, new InstructionInfo(2, ANL));      // ANL	A, direct
        INSTRUCTION_INFO_BY_OPCODE.put(0x56, new InstructionInfo(1, ANL));      // ANL	A, @R0
        INSTRUCTION_INFO_BY_OPCODE.put(0x57, new InstructionInfo(1, ANL));      // ANL	A, @R1
        INSTRUCTION_INFO_BY_OPCODE.put(0x58, new InstructionInfo(1, ANL));      // ANL	A, R0
        INSTRUCTION_INFO_BY_OPCODE.put(0x59, new InstructionInfo(1, ANL));      // ANL	A, R1
        INSTRUCTION_INFO_BY_OPCODE.put(0x5A, new InstructionInfo(1, ANL));      // ANL	A, R2
        INSTRUCTION_INFO_BY_OPCODE.put(0x5B, new InstructionInfo(1, ANL));      // ANL	A, R3
        INSTRUCTION_INFO_BY_OPCODE.put(0x5C, new InstructionInfo(1, ANL));      // ANL	A, R4
        INSTRUCTION_INFO_BY_OPCODE.put(0x5D, new InstructionInfo(1, ANL));      // ANL	A, R5
        INSTRUCTION_INFO_BY_OPCODE.put(0x5E, new InstructionInfo(1, ANL));      // ANL	A, R6
        INSTRUCTION_INFO_BY_OPCODE.put(0x5F, new InstructionInfo(1, ANL));      // ANL	A, R7
        INSTRUCTION_INFO_BY_OPCODE.put(0x60, new InstructionInfo(2, JZ));       // JZ	offset
        INSTRUCTION_INFO_BY_OPCODE.put(0x61, new InstructionInfo(2, AJMP));     // AJMP	addr11
        INSTRUCTION_INFO_BY_OPCODE.put(0x62, new InstructionInfo(2, XRL));      // XRL	direct, A
        INSTRUCTION_INFO_BY_OPCODE.put(0x63, new InstructionInfo(3, XRL));      // XRL	direct, #immed
        INSTRUCTION_INFO_BY_OPCODE.put(0x64, new InstructionInfo(2, XRL));      // XRL	A, #immed
        INSTRUCTION_INFO_BY_OPCODE.put(0x65, new InstructionInfo(2, XRL));      // XRL	A, direct
        INSTRUCTION_INFO_BY_OPCODE.put(0x66, new InstructionInfo(1, XRL));      // XRL	A, @R0
        INSTRUCTION_INFO_BY_OPCODE.put(0x67, new InstructionInfo(1, XRL));      // XRL	A, @R1
        INSTRUCTION_INFO_BY_OPCODE.put(0x68, new InstructionInfo(1, XRL));      // XRL	A, R0
        INSTRUCTION_INFO_BY_OPCODE.put(0x69, new InstructionInfo(1, XRL));      // XRL	A, R1
        INSTRUCTION_INFO_BY_OPCODE.put(0x6A, new InstructionInfo(1, XRL));      // XRL	A, R2
        INSTRUCTION_INFO_BY_OPCODE.put(0x6B, new InstructionInfo(1, XRL));      // XRL	A, R3
        INSTRUCTION_INFO_BY_OPCODE.put(0x6C, new InstructionInfo(1, XRL));      // XRL	A, R4
        INSTRUCTION_INFO_BY_OPCODE.put(0x6D, new InstructionInfo(1, XRL));      // XRL	A, R5
        INSTRUCTION_INFO_BY_OPCODE.put(0x6E, new InstructionInfo(1, XRL));      // XRL	A, R6
        INSTRUCTION_INFO_BY_OPCODE.put(0x6F, new InstructionInfo(1, XRL));      // XRL	A, R7
        INSTRUCTION_INFO_BY_OPCODE.put(0x70, new InstructionInfo(2, JNZ));      // JNZ	offset
        INSTRUCTION_INFO_BY_OPCODE.put(0x71, new InstructionInfo(2, ACALL));    // ACALL	addr11
        INSTRUCTION_INFO_BY_OPCODE.put(0x72, new InstructionInfo(2, ORL));      // ORL	C, bit
        INSTRUCTION_INFO_BY_OPCODE.put(0x73, new InstructionInfo(1, JMP));      // JMP	@A+DPTR
        INSTRUCTION_INFO_BY_OPCODE.put(0x74, new InstructionInfo(2, MOV));      // MOV	A, #immed
        INSTRUCTION_INFO_BY_OPCODE.put(0x75, new InstructionInfo(3, MOV));      // MOV	direct, #immed
        INSTRUCTION_INFO_BY_OPCODE.put(0x76, new InstructionInfo(2, MOV));      // MOV	@R0, #immed
        INSTRUCTION_INFO_BY_OPCODE.put(0x77, new InstructionInfo(2, MOV));      // MOV	@R1, #immed
        INSTRUCTION_INFO_BY_OPCODE.put(0x78, new InstructionInfo(2, MOV));      // MOV	R0, #immed
        INSTRUCTION_INFO_BY_OPCODE.put(0x79, new InstructionInfo(2, MOV));      // MOV	R1, #immed
        INSTRUCTION_INFO_BY_OPCODE.put(0x7A, new InstructionInfo(2, MOV));      // MOV	R2, #immed
        INSTRUCTION_INFO_BY_OPCODE.put(0x7B, new InstructionInfo(2, MOV));      // MOV	R3, #immed
        INSTRUCTION_INFO_BY_OPCODE.put(0x7C, new InstructionInfo(2, MOV));      // MOV	R4, #immed
        INSTRUCTION_INFO_BY_OPCODE.put(0x7D, new InstructionInfo(2, MOV));      // MOV	R5, #immed
        INSTRUCTION_INFO_BY_OPCODE.put(0x7E, new InstructionInfo(2, MOV));      // MOV	R6, #immed
        INSTRUCTION_INFO_BY_OPCODE.put(0x7F, new InstructionInfo(2, MOV));      // MOV	R7, #immed

        INSTRUCTION_INFO_BY_OPCODE.put(0x80, new InstructionInfo(2, SJMP));     // SJMP	offset
        INSTRUCTION_INFO_BY_OPCODE.put(0x81, new InstructionInfo(2, AJMP));     // AJMP	addr11
        INSTRUCTION_INFO_BY_OPCODE.put(0x82, new InstructionInfo(2, ANL));      // ANL	C, bit
        INSTRUCTION_INFO_BY_OPCODE.put(0x83, new InstructionInfo(1, MOVC));     // MOVC	A, @A+PC
        INSTRUCTION_INFO_BY_OPCODE.put(0x84, new InstructionInfo(1, DIV));      // DIV	AB
        INSTRUCTION_INFO_BY_OPCODE.put(0x85, new InstructionInfo(3, MOV));      // MOV	direct, direct
        INSTRUCTION_INFO_BY_OPCODE.put(0x86, new InstructionInfo(2, MOV));      // MOV	direct, @R0
        INSTRUCTION_INFO_BY_OPCODE.put(0x87, new InstructionInfo(2, MOV));      // MOV	direct, @R1
        INSTRUCTION_INFO_BY_OPCODE.put(0x88, new InstructionInfo(2, MOV));      // MOV	direct, R0
        INSTRUCTION_INFO_BY_OPCODE.put(0x89, new InstructionInfo(2, MOV));      // MOV	direct, R1
        INSTRUCTION_INFO_BY_OPCODE.put(0x8A, new InstructionInfo(2, MOV));      // MOV	direct, R2
        INSTRUCTION_INFO_BY_OPCODE.put(0x8B, new InstructionInfo(2, MOV));      // MOV	direct, R3
        INSTRUCTION_INFO_BY_OPCODE.put(0x8C, new InstructionInfo(2, MOV));      // MOV	direct, R4
        INSTRUCTION_INFO_BY_OPCODE.put(0x8D, new InstructionInfo(2, MOV));      // MOV	direct, R5
        INSTRUCTION_INFO_BY_OPCODE.put(0x8E, new InstructionInfo(2, MOV));      // MOV	direct, R6
        INSTRUCTION_INFO_BY_OPCODE.put(0x8F, new InstructionInfo(2, MOV));      // MOV	direct, R7
        INSTRUCTION_INFO_BY_OPCODE.put(0x90, new InstructionInfo(3, MOV));      // MOV	DPTR, #immed
        INSTRUCTION_INFO_BY_OPCODE.put(0x91, new InstructionInfo(2, ACALL));    // ACALL	addr11
        INSTRUCTION_INFO_BY_OPCODE.put(0x92, new InstructionInfo(2, MOV));      // MOV	bit, C
        INSTRUCTION_INFO_BY_OPCODE.put(0x93, new InstructionInfo(1, MOVC));     // MOVC	A, @A+DPTR
        INSTRUCTION_INFO_BY_OPCODE.put(0x94, new InstructionInfo(2, SUBB));     // SUBB	A, #immed
        INSTRUCTION_INFO_BY_OPCODE.put(0x95, new InstructionInfo(2, SUBB));     // SUBB	A, direct
        INSTRUCTION_INFO_BY_OPCODE.put(0x96, new InstructionInfo(1, SUBB));     // SUBB	A, @R0
        INSTRUCTION_INFO_BY_OPCODE.put(0x97, new InstructionInfo(1, SUBB));     // SUBB	A, @R1
        INSTRUCTION_INFO_BY_OPCODE.put(0x98, new InstructionInfo(1, SUBB));     // SUBB	A, R0
        INSTRUCTION_INFO_BY_OPCODE.put(0x99, new InstructionInfo(1, SUBB));     // SUBB	A, R1
        INSTRUCTION_INFO_BY_OPCODE.put(0x9A, new InstructionInfo(1, SUBB));     // SUBB	A, R2
        INSTRUCTION_INFO_BY_OPCODE.put(0x9B, new InstructionInfo(1, SUBB));     // SUBB	A, R3
        INSTRUCTION_INFO_BY_OPCODE.put(0x9C, new InstructionInfo(1, SUBB));     // SUBB	A, R4
        INSTRUCTION_INFO_BY_OPCODE.put(0x9D, new InstructionInfo(1, SUBB));     // SUBB	A, R5
        INSTRUCTION_INFO_BY_OPCODE.put(0x9E, new InstructionInfo(1, SUBB));     // SUBB	A, R6
        INSTRUCTION_INFO_BY_OPCODE.put(0x9F, new InstructionInfo(1, SUBB));     // SUBB	A, R7
        INSTRUCTION_INFO_BY_OPCODE.put(0xA0, new InstructionInfo(2, ORL));      // ORL	C, /bit
        INSTRUCTION_INFO_BY_OPCODE.put(0xA1, new InstructionInfo(2, AJMP));     // AJMP	addr11
        INSTRUCTION_INFO_BY_OPCODE.put(0xA2, new InstructionInfo(2, MOV));      // MOV	C, bit
        INSTRUCTION_INFO_BY_OPCODE.put(0xA3, new InstructionInfo(1, INC));      // INC	DPTR
        INSTRUCTION_INFO_BY_OPCODE.put(0xA4, new InstructionInfo(1, MUL));      // MUL	AB
        // INSTRUCTION_INFO_BY_OPCODE.put(0xA5, 0);    // reserved
        INSTRUCTION_INFO_BY_OPCODE.put(0xA6, new InstructionInfo(2, MOV));      // MOV	@R0, direct
        INSTRUCTION_INFO_BY_OPCODE.put(0xA7, new InstructionInfo(2, MOV));      // MOV	@R1, direct
        INSTRUCTION_INFO_BY_OPCODE.put(0xA8, new InstructionInfo(2, MOV));      // MOV	R0, direct
        INSTRUCTION_INFO_BY_OPCODE.put(0xA9, new InstructionInfo(2, MOV));      // MOV	R1, direct
        INSTRUCTION_INFO_BY_OPCODE.put(0xAA, new InstructionInfo(2, MOV));      // MOV	R2, direct
        INSTRUCTION_INFO_BY_OPCODE.put(0xAB, new InstructionInfo(2, MOV));      // MOV	R3, direct
        INSTRUCTION_INFO_BY_OPCODE.put(0xAC, new InstructionInfo(2, MOV));      // MOV	R4, direct
        INSTRUCTION_INFO_BY_OPCODE.put(0xAD, new InstructionInfo(2, MOV));      // MOV	R5, direct
        INSTRUCTION_INFO_BY_OPCODE.put(0xAE, new InstructionInfo(2, MOV));      // MOV	R6, direct
        INSTRUCTION_INFO_BY_OPCODE.put(0xAF, new InstructionInfo(2, MOV));      // MOV	R7, direct
        INSTRUCTION_INFO_BY_OPCODE.put(0xB0, new InstructionInfo(2, ANL));      // ANL	C, /bit
        INSTRUCTION_INFO_BY_OPCODE.put(0xB1, new InstructionInfo(2, ACALL));    // ACALL	addr11
        INSTRUCTION_INFO_BY_OPCODE.put(0xB2, new InstructionInfo(2, CPL));      // CPL	bit
        INSTRUCTION_INFO_BY_OPCODE.put(0xB3, new InstructionInfo(1, CPL));      // CPL	C
        INSTRUCTION_INFO_BY_OPCODE.put(0xB4, new InstructionInfo(3, CJNE));     // CJNE	A, #immed, offset
        INSTRUCTION_INFO_BY_OPCODE.put(0xB5, new InstructionInfo(3, CJNE));     // CJNE	A, direct, offset
        INSTRUCTION_INFO_BY_OPCODE.put(0xB6, new InstructionInfo(3, CJNE));     // CJNE	@R0, #immed, offset
        INSTRUCTION_INFO_BY_OPCODE.put(0xB7, new InstructionInfo(3, CJNE));     // CJNE	@R1, #immed, offset
        INSTRUCTION_INFO_BY_OPCODE.put(0xB8, new InstructionInfo(3, CJNE));     // CJNE	R0, #immed, offset
        INSTRUCTION_INFO_BY_OPCODE.put(0xB9, new InstructionInfo(3, CJNE));     // CJNE	R1, #immed, offset
        INSTRUCTION_INFO_BY_OPCODE.put(0xBA, new InstructionInfo(3, CJNE));     // CJNE	R2, #immed, offset
        INSTRUCTION_INFO_BY_OPCODE.put(0xBB, new InstructionInfo(3, CJNE));     // CJNE	R3, #immed, offset
        INSTRUCTION_INFO_BY_OPCODE.put(0xBC, new InstructionInfo(3, CJNE));     // CJNE	R4, #immed, offset
        INSTRUCTION_INFO_BY_OPCODE.put(0xBD, new InstructionInfo(3, CJNE));     // CJNE	R5, #immed, offset
        INSTRUCTION_INFO_BY_OPCODE.put(0xBE, new InstructionInfo(3, CJNE));     // CJNE	R6, #immed, offset
        INSTRUCTION_INFO_BY_OPCODE.put(0xBF, new InstructionInfo(3, CJNE));     // CJNE	R7, #immed, offset
        INSTRUCTION_INFO_BY_OPCODE.put(0xC0, new InstructionInfo(2, PUSH));     // PUSH	direct
        INSTRUCTION_INFO_BY_OPCODE.put(0xC1, new InstructionInfo(2, AJMP));     // AJMP	addr11
        INSTRUCTION_INFO_BY_OPCODE.put(0xC2, new InstructionInfo(2, CLR));      // CLR	bit
        INSTRUCTION_INFO_BY_OPCODE.put(0xC3, new InstructionInfo(1, CLR));      // CLR	C
        INSTRUCTION_INFO_BY_OPCODE.put(0xC4, new InstructionInfo(1, SWAP));     // SWAP	A
        INSTRUCTION_INFO_BY_OPCODE.put(0xC5, new InstructionInfo(2, XCH));      // XCH	A, direct
        INSTRUCTION_INFO_BY_OPCODE.put(0xC6, new InstructionInfo(1, XCH));      // XCH	A, @R0
        INSTRUCTION_INFO_BY_OPCODE.put(0xC7, new InstructionInfo(1, XCH));      // XCH	A, @R1
        INSTRUCTION_INFO_BY_OPCODE.put(0xC8, new InstructionInfo(1, XCH));      // XCH	A, R0
        INSTRUCTION_INFO_BY_OPCODE.put(0xC9, new InstructionInfo(1, XCH));      // XCH	A, R1
        INSTRUCTION_INFO_BY_OPCODE.put(0xCA, new InstructionInfo(1, XCH));      // XCH	A, R2
        INSTRUCTION_INFO_BY_OPCODE.put(0xCB, new InstructionInfo(1, XCH));      // XCH	A, R3
        INSTRUCTION_INFO_BY_OPCODE.put(0xCC, new InstructionInfo(1, XCH));      // XCH	A, R4
        INSTRUCTION_INFO_BY_OPCODE.put(0xCD, new InstructionInfo(1, XCH));      // XCH	A, R5
        INSTRUCTION_INFO_BY_OPCODE.put(0xCE, new InstructionInfo(1, XCH));      // XCH	A, R6
        INSTRUCTION_INFO_BY_OPCODE.put(0xCF, new InstructionInfo(1, XCH));      // XCH	A, R7
        INSTRUCTION_INFO_BY_OPCODE.put(0xD0, new InstructionInfo(2, POP));      // POP	direct
        INSTRUCTION_INFO_BY_OPCODE.put(0xD1, new InstructionInfo(2, ACALL));    // ACALL	addr11
        INSTRUCTION_INFO_BY_OPCODE.put(0xD2, new InstructionInfo(2, SETB));     // SETB	bit
        INSTRUCTION_INFO_BY_OPCODE.put(0xD3, new InstructionInfo(1, SETB));     // SETB	C
        INSTRUCTION_INFO_BY_OPCODE.put(0xD4, new InstructionInfo(1, DA));       // DA	A
        INSTRUCTION_INFO_BY_OPCODE.put(0xD5, new InstructionInfo(3, DJNZ));     // DJNZ	direct, offset
        INSTRUCTION_INFO_BY_OPCODE.put(0xD6, new InstructionInfo(1, XCHD));     // XCHD	A, @R0
        INSTRUCTION_INFO_BY_OPCODE.put(0xD7, new InstructionInfo(1, XCHD));     // XCHD	A, @R1
        INSTRUCTION_INFO_BY_OPCODE.put(0xD8, new InstructionInfo(2, DJNZ));     // DJNZ	R0, offset
        INSTRUCTION_INFO_BY_OPCODE.put(0xD9, new InstructionInfo(2, DJNZ));     // DJNZ	R1, offset
        INSTRUCTION_INFO_BY_OPCODE.put(0xDA, new InstructionInfo(2, DJNZ));     // DJNZ	R2, offset
        INSTRUCTION_INFO_BY_OPCODE.put(0xDB, new InstructionInfo(2, DJNZ));     // DJNZ	R3, offset
        INSTRUCTION_INFO_BY_OPCODE.put(0xDC, new InstructionInfo(2, DJNZ));     // DJNZ	R4, offset
        INSTRUCTION_INFO_BY_OPCODE.put(0xDD, new InstructionInfo(2, DJNZ));     // DJNZ	R5, offset
        INSTRUCTION_INFO_BY_OPCODE.put(0xDE, new InstructionInfo(2, DJNZ));     // DJNZ	R6, offset
        INSTRUCTION_INFO_BY_OPCODE.put(0xDF, new InstructionInfo(2, DJNZ));     // DJNZ	R7, offset
        INSTRUCTION_INFO_BY_OPCODE.put(0xE0, new InstructionInfo(1, MOVX));     // MOVX	A, @DPTR
        INSTRUCTION_INFO_BY_OPCODE.put(0xE1, new InstructionInfo(2, AJMP));     // AJMP	addr11
        INSTRUCTION_INFO_BY_OPCODE.put(0xE2, new InstructionInfo(1, MOVX));     // MOVX	A, @R0
        INSTRUCTION_INFO_BY_OPCODE.put(0xE3, new InstructionInfo(1, MOVX));     // MOVX	A, @R1
        INSTRUCTION_INFO_BY_OPCODE.put(0xE4, new InstructionInfo(1, CLR));      // CLR	A
        INSTRUCTION_INFO_BY_OPCODE.put(0xE5, new InstructionInfo(2, MOV));      // MOV	A, direct
        INSTRUCTION_INFO_BY_OPCODE.put(0xE6, new InstructionInfo(1, MOV));      // MOV	A, @R0
        INSTRUCTION_INFO_BY_OPCODE.put(0xE7, new InstructionInfo(1, MOV));      // MOV	A, @R1
        INSTRUCTION_INFO_BY_OPCODE.put(0xE8, new InstructionInfo(1, MOV));      // MOV	A, R0
        INSTRUCTION_INFO_BY_OPCODE.put(0xE9, new InstructionInfo(1, MOV));      // MOV	A, R1
        INSTRUCTION_INFO_BY_OPCODE.put(0xEA, new InstructionInfo(1, MOV));      // MOV	A, R2
        INSTRUCTION_INFO_BY_OPCODE.put(0xEB, new InstructionInfo(1, MOV));      // MOV	A, R3
        INSTRUCTION_INFO_BY_OPCODE.put(0xEC, new InstructionInfo(1, MOV));      // MOV	A, R4
        INSTRUCTION_INFO_BY_OPCODE.put(0xED, new InstructionInfo(1, MOV));      // MOV	A, R5
        INSTRUCTION_INFO_BY_OPCODE.put(0xEE, new InstructionInfo(1, MOV));      // MOV	A, R6
        INSTRUCTION_INFO_BY_OPCODE.put(0xEF, new InstructionInfo(1, MOV));      // MOV	A, R7
        INSTRUCTION_INFO_BY_OPCODE.put(0xF0, new InstructionInfo(1, MOVX));     // MOVX	@DPTR, A
        INSTRUCTION_INFO_BY_OPCODE.put(0xF1, new InstructionInfo(2, ACALL));    // ACALL	addr11
        INSTRUCTION_INFO_BY_OPCODE.put(0xF2, new InstructionInfo(1, MOVX));     // MOVX	@R0, A
        INSTRUCTION_INFO_BY_OPCODE.put(0xF3, new InstructionInfo(1, MOVX));     // MOVX	@R1, A
        INSTRUCTION_INFO_BY_OPCODE.put(0xF4, new InstructionInfo(1, CPL));      // CPL	A
        INSTRUCTION_INFO_BY_OPCODE.put(0xF5, new InstructionInfo(2, MOV));      // MOV	direct, A
        INSTRUCTION_INFO_BY_OPCODE.put(0xF6, new InstructionInfo(1, MOV));      // MOV	@R0, A
        INSTRUCTION_INFO_BY_OPCODE.put(0xF7, new InstructionInfo(1, MOV));      // MOV	@R1, A
        INSTRUCTION_INFO_BY_OPCODE.put(0xF8, new InstructionInfo(1, MOV));      // MOV	R0, A
        INSTRUCTION_INFO_BY_OPCODE.put(0xF9, new InstructionInfo(1, MOV));      // MOV	R1, A
        INSTRUCTION_INFO_BY_OPCODE.put(0xFA, new InstructionInfo(1, MOV));      // MOV	R2, A
        INSTRUCTION_INFO_BY_OPCODE.put(0xFB, new InstructionInfo(1, MOV));      // MOV	R3, A
        INSTRUCTION_INFO_BY_OPCODE.put(0xFC, new InstructionInfo(1, MOV));      // MOV	R4, A
        INSTRUCTION_INFO_BY_OPCODE.put(0xFD, new InstructionInfo(1, MOV));      // MOV	R5, A
        INSTRUCTION_INFO_BY_OPCODE.put(0xFE, new InstructionInfo(1, MOV));      // MOV	R6, A
        INSTRUCTION_INFO_BY_OPCODE.put(0xFF, new InstructionInfo(1, MOV));      // MOV	R7, A
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

    private static final class InstructionInfo {
        private final int bytes;
        private final Instruction instruction;

        InstructionInfo(int bytes, Instruction instruction) {
            this.bytes = bytes;
            this.instruction = instruction;
        }
    }
}
