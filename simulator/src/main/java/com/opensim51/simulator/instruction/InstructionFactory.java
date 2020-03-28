package com.opensim51.simulator.instruction;

import com.opensim51.simulator.memory.datatype.UInt8;

import java.util.HashMap;
import java.util.Map;

import static com.opensim51.simulator.instruction.InstructionType.*;

public class InstructionFactory {
    private static final Map<Integer, Instruction> INSTRUCTION_INFO_BY_OPCODE;

    static {
        INSTRUCTION_INFO_BY_OPCODE = new HashMap<>(256);
        INSTRUCTION_INFO_BY_OPCODE.put(0x00, new Instruction(1, 0, NOP));      // NOP
        INSTRUCTION_INFO_BY_OPCODE.put(0x01, new Instruction(2, 0, AJMP));     // AJMP	addr11
        INSTRUCTION_INFO_BY_OPCODE.put(0x02, new Instruction(3, 0, LJMP));     // LJMP	addr16
        INSTRUCTION_INFO_BY_OPCODE.put(0x03, new Instruction(1, 0, RR));       // RR	A
        INSTRUCTION_INFO_BY_OPCODE.put(0x04, new Instruction(1, 0, INC));      // INC	A
        INSTRUCTION_INFO_BY_OPCODE.put(0x05, new Instruction(2, 0, INC));      // INC	direct
        INSTRUCTION_INFO_BY_OPCODE.put(0x06, new Instruction(1, 0, INC));      // INC	@R0
        INSTRUCTION_INFO_BY_OPCODE.put(0x07, new Instruction(1, 0, INC));      // INC	@R1
        INSTRUCTION_INFO_BY_OPCODE.put(0x08, new Instruction(1, 0, INC));      // INC	R0
        INSTRUCTION_INFO_BY_OPCODE.put(0x09, new Instruction(1, 0, INC));      // INC	R1
        INSTRUCTION_INFO_BY_OPCODE.put(0x0A, new Instruction(1, 0, INC));      // INC	R2
        INSTRUCTION_INFO_BY_OPCODE.put(0x0B, new Instruction(1, 0, INC));      // INC	R3
        INSTRUCTION_INFO_BY_OPCODE.put(0x0C, new Instruction(1, 0, INC));      // INC	R4
        INSTRUCTION_INFO_BY_OPCODE.put(0x0D, new Instruction(1, 0, INC));      // INC	R5
        INSTRUCTION_INFO_BY_OPCODE.put(0x0E, new Instruction(1, 0, INC));      // INC	R6
        INSTRUCTION_INFO_BY_OPCODE.put(0x0F, new Instruction(1, 0, INC));      // INC	R7
        INSTRUCTION_INFO_BY_OPCODE.put(0x10, new Instruction(3, 0, JBC));      // JBC	bit, offset
        INSTRUCTION_INFO_BY_OPCODE.put(0x11, new Instruction(2, 0, ACALL));    // ACALL	addr11
        INSTRUCTION_INFO_BY_OPCODE.put(0x12, new Instruction(3, 0, LCALL));    // LCALL	addr16
        INSTRUCTION_INFO_BY_OPCODE.put(0x13, new Instruction(1, 0, RRC));      // RRC	A
        INSTRUCTION_INFO_BY_OPCODE.put(0x14, new Instruction(1, 0, DEC));      // DEC	A
        INSTRUCTION_INFO_BY_OPCODE.put(0x15, new Instruction(2, 0, DEC));      // DEC	direct
        INSTRUCTION_INFO_BY_OPCODE.put(0x16, new Instruction(1, 0, DEC));      // DEC	@R0
        INSTRUCTION_INFO_BY_OPCODE.put(0x17, new Instruction(1, 0, DEC));      // DEC	@R1
        INSTRUCTION_INFO_BY_OPCODE.put(0x18, new Instruction(1, 0, DEC));      // DEC	R0
        INSTRUCTION_INFO_BY_OPCODE.put(0x19, new Instruction(1, 0, DEC));      // DEC	R1
        INSTRUCTION_INFO_BY_OPCODE.put(0x1A, new Instruction(1, 0, DEC));      // DEC	R2
        INSTRUCTION_INFO_BY_OPCODE.put(0x1B, new Instruction(1, 0, DEC));      // DEC	R3
        INSTRUCTION_INFO_BY_OPCODE.put(0x1C, new Instruction(1, 0, DEC));      // DEC	R4
        INSTRUCTION_INFO_BY_OPCODE.put(0x1D, new Instruction(1, 0, DEC));      // DEC	R5
        INSTRUCTION_INFO_BY_OPCODE.put(0x1E, new Instruction(1, 0, DEC));      // DEC	R6
        INSTRUCTION_INFO_BY_OPCODE.put(0x1F, new Instruction(1, 0, DEC));      // DEC	R7
        INSTRUCTION_INFO_BY_OPCODE.put(0x20, new Instruction(3, 0, JB));       // JB	bit, offset
        INSTRUCTION_INFO_BY_OPCODE.put(0x21, new Instruction(2, 0, AJMP));     // AJMP	addr11
        INSTRUCTION_INFO_BY_OPCODE.put(0x22, new Instruction(1, 0, RET));      // RET
        INSTRUCTION_INFO_BY_OPCODE.put(0x23, new Instruction(1, 0, RL));       // RL	A
        INSTRUCTION_INFO_BY_OPCODE.put(0x24, new Instruction(2, 0, ADD));      // ADD	A, #immed
        INSTRUCTION_INFO_BY_OPCODE.put(0x25, new Instruction(2, 0, ADD));      // ADD	A, direct
        INSTRUCTION_INFO_BY_OPCODE.put(0x26, new Instruction(1, 0, ADD));      // ADD	A, @R0
        INSTRUCTION_INFO_BY_OPCODE.put(0x27, new Instruction(1, 0, ADD));      // ADD	A, @R1
        INSTRUCTION_INFO_BY_OPCODE.put(0x28, new Instruction(1, 0, ADD));      // ADD	A, R0
        INSTRUCTION_INFO_BY_OPCODE.put(0x29, new Instruction(1, 0, ADD));      // ADD	A, R1
        INSTRUCTION_INFO_BY_OPCODE.put(0x2A, new Instruction(1, 0, ADD));      // ADD	A, R2
        INSTRUCTION_INFO_BY_OPCODE.put(0x2B, new Instruction(1, 0, ADD));      // ADD	A, R3
        INSTRUCTION_INFO_BY_OPCODE.put(0x2C, new Instruction(1, 0, ADD));      // ADD	A, R4
        INSTRUCTION_INFO_BY_OPCODE.put(0x2D, new Instruction(1, 0, ADD));      // ADD	A, R5
        INSTRUCTION_INFO_BY_OPCODE.put(0x2E, new Instruction(1, 0, ADD));      // ADD	A, R6
        INSTRUCTION_INFO_BY_OPCODE.put(0x2F, new Instruction(1, 0, ADD));      // ADD	A, R7
        INSTRUCTION_INFO_BY_OPCODE.put(0x30, new Instruction(3, 0, JNB));      // JNB	bit, offset
        INSTRUCTION_INFO_BY_OPCODE.put(0x31, new Instruction(2, 0, ACALL));    // ACALL	addr11
        INSTRUCTION_INFO_BY_OPCODE.put(0x32, new Instruction(1, 0, RETI));     // RETI
        INSTRUCTION_INFO_BY_OPCODE.put(0x33, new Instruction(1, 0, RLC));      // RLC	A
        INSTRUCTION_INFO_BY_OPCODE.put(0x34, new Instruction(2, 0, ADDC));     // ADDC	A, #immed
        INSTRUCTION_INFO_BY_OPCODE.put(0x35, new Instruction(2, 0, ADDC));     // ADDC	A, direct
        INSTRUCTION_INFO_BY_OPCODE.put(0x36, new Instruction(1, 0, ADDC));     // ADDC	A, @R0
        INSTRUCTION_INFO_BY_OPCODE.put(0x37, new Instruction(1, 0, ADDC));     // ADDC	A, @R1
        INSTRUCTION_INFO_BY_OPCODE.put(0x38, new Instruction(1, 0, ADDC));     // ADDC	A, R0
        INSTRUCTION_INFO_BY_OPCODE.put(0x39, new Instruction(1, 0, ADDC));     // ADDC	A, R1
        INSTRUCTION_INFO_BY_OPCODE.put(0x3A, new Instruction(1, 0, ADDC));     // ADDC	A, R2
        INSTRUCTION_INFO_BY_OPCODE.put(0x3B, new Instruction(1, 0, ADDC));     // ADDC	A, R3
        INSTRUCTION_INFO_BY_OPCODE.put(0x3C, new Instruction(1, 0, ADDC));     // ADDC	A, R4
        INSTRUCTION_INFO_BY_OPCODE.put(0x3D, new Instruction(1, 0, ADDC));     // ADDC	A, R5
        INSTRUCTION_INFO_BY_OPCODE.put(0x3E, new Instruction(1, 0, ADDC));     // ADDC	A, R6
        INSTRUCTION_INFO_BY_OPCODE.put(0x3F, new Instruction(1, 0, ADDC));     // ADDC	A, R7
        INSTRUCTION_INFO_BY_OPCODE.put(0x40, new Instruction(2, 0, JC));       // JC	offset
        INSTRUCTION_INFO_BY_OPCODE.put(0x41, new Instruction(2, 0, AJMP));     // AJMP	addr11
        INSTRUCTION_INFO_BY_OPCODE.put(0x42, new Instruction(2, 0, ORL));      // ORL	direct, A
        INSTRUCTION_INFO_BY_OPCODE.put(0x43, new Instruction(3, 0, ORL));      // ORL	direct, #immed
        INSTRUCTION_INFO_BY_OPCODE.put(0x44, new Instruction(2, 0, ORL));      // ORL	A, #immed
        INSTRUCTION_INFO_BY_OPCODE.put(0x45, new Instruction(2, 0, ORL));      // ORL	A, direct
        INSTRUCTION_INFO_BY_OPCODE.put(0x46, new Instruction(1, 0, ORL));      // ORL	A, @R0
        INSTRUCTION_INFO_BY_OPCODE.put(0x47, new Instruction(1, 0, ORL));      // ORL	A, @R1
        INSTRUCTION_INFO_BY_OPCODE.put(0x48, new Instruction(1, 0, ORL));      // ORL	A, R0
        INSTRUCTION_INFO_BY_OPCODE.put(0x49, new Instruction(1, 0, ORL));      // ORL	A, R1
        INSTRUCTION_INFO_BY_OPCODE.put(0x4A, new Instruction(1, 0, ORL));      // ORL	A, R2
        INSTRUCTION_INFO_BY_OPCODE.put(0x4B, new Instruction(1, 0, ORL));      // ORL	A, R3
        INSTRUCTION_INFO_BY_OPCODE.put(0x4C, new Instruction(1, 0, ORL));      // ORL	A, R4
        INSTRUCTION_INFO_BY_OPCODE.put(0x4D, new Instruction(1, 0, ORL));      // ORL	A, R5
        INSTRUCTION_INFO_BY_OPCODE.put(0x4E, new Instruction(1, 0, ORL));      // ORL	A, R6
        INSTRUCTION_INFO_BY_OPCODE.put(0x4F, new Instruction(1, 0, ORL));      // ORL	A, R7
        INSTRUCTION_INFO_BY_OPCODE.put(0x50, new Instruction(2, 0, JNC));      // JNC	offset
        INSTRUCTION_INFO_BY_OPCODE.put(0x51, new Instruction(2, 0, ACALL));    // ACALL	addr11
        INSTRUCTION_INFO_BY_OPCODE.put(0x52, new Instruction(2, 0, ANL));      // ANL	direct, A
        INSTRUCTION_INFO_BY_OPCODE.put(0x53, new Instruction(3, 0, ANL));      // ANL	direct, #immed
        INSTRUCTION_INFO_BY_OPCODE.put(0x54, new Instruction(2, 0, ANL));      // ANL	A, #immed
        INSTRUCTION_INFO_BY_OPCODE.put(0x55, new Instruction(2, 0, ANL));      // ANL	A, direct
        INSTRUCTION_INFO_BY_OPCODE.put(0x56, new Instruction(1, 0, ANL));      // ANL	A, @R0
        INSTRUCTION_INFO_BY_OPCODE.put(0x57, new Instruction(1, 0, ANL));      // ANL	A, @R1
        INSTRUCTION_INFO_BY_OPCODE.put(0x58, new Instruction(1, 0, ANL));      // ANL	A, R0
        INSTRUCTION_INFO_BY_OPCODE.put(0x59, new Instruction(1, 0, ANL));      // ANL	A, R1
        INSTRUCTION_INFO_BY_OPCODE.put(0x5A, new Instruction(1, 0, ANL));      // ANL	A, R2
        INSTRUCTION_INFO_BY_OPCODE.put(0x5B, new Instruction(1, 0, ANL));      // ANL	A, R3
        INSTRUCTION_INFO_BY_OPCODE.put(0x5C, new Instruction(1, 0, ANL));      // ANL	A, R4
        INSTRUCTION_INFO_BY_OPCODE.put(0x5D, new Instruction(1, 0, ANL));      // ANL	A, R5
        INSTRUCTION_INFO_BY_OPCODE.put(0x5E, new Instruction(1, 0, ANL));      // ANL	A, R6
        INSTRUCTION_INFO_BY_OPCODE.put(0x5F, new Instruction(1, 0, ANL));      // ANL	A, R7
        INSTRUCTION_INFO_BY_OPCODE.put(0x60, new Instruction(2, 0, JZ));       // JZ	offset
        INSTRUCTION_INFO_BY_OPCODE.put(0x61, new Instruction(2, 0, AJMP));     // AJMP	addr11
        INSTRUCTION_INFO_BY_OPCODE.put(0x62, new Instruction(2, 0, XRL));      // XRL	direct, A
        INSTRUCTION_INFO_BY_OPCODE.put(0x63, new Instruction(3, 0, XRL));      // XRL	direct, #immed
        INSTRUCTION_INFO_BY_OPCODE.put(0x64, new Instruction(2, 0, XRL));      // XRL	A, #immed
        INSTRUCTION_INFO_BY_OPCODE.put(0x65, new Instruction(2, 0, XRL));      // XRL	A, direct
        INSTRUCTION_INFO_BY_OPCODE.put(0x66, new Instruction(1, 0, XRL));      // XRL	A, @R0
        INSTRUCTION_INFO_BY_OPCODE.put(0x67, new Instruction(1, 0, XRL));      // XRL	A, @R1
        INSTRUCTION_INFO_BY_OPCODE.put(0x68, new Instruction(1, 0, XRL));      // XRL	A, R0
        INSTRUCTION_INFO_BY_OPCODE.put(0x69, new Instruction(1, 0, XRL));      // XRL	A, R1
        INSTRUCTION_INFO_BY_OPCODE.put(0x6A, new Instruction(1, 0, XRL));      // XRL	A, R2
        INSTRUCTION_INFO_BY_OPCODE.put(0x6B, new Instruction(1, 0, XRL));      // XRL	A, R3
        INSTRUCTION_INFO_BY_OPCODE.put(0x6C, new Instruction(1, 0, XRL));      // XRL	A, R4
        INSTRUCTION_INFO_BY_OPCODE.put(0x6D, new Instruction(1, 0, XRL));      // XRL	A, R5
        INSTRUCTION_INFO_BY_OPCODE.put(0x6E, new Instruction(1, 0, XRL));      // XRL	A, R6
        INSTRUCTION_INFO_BY_OPCODE.put(0x6F, new Instruction(1, 0, XRL));      // XRL	A, R7
        INSTRUCTION_INFO_BY_OPCODE.put(0x70, new Instruction(2, 0, JNZ));      // JNZ	offset
        INSTRUCTION_INFO_BY_OPCODE.put(0x71, new Instruction(2, 0, ACALL));    // ACALL	addr11
        INSTRUCTION_INFO_BY_OPCODE.put(0x72, new Instruction(2, 0, ORL));      // ORL	C, bit
        INSTRUCTION_INFO_BY_OPCODE.put(0x73, new Instruction(1, 0, JMP));      // JMP	@A+DPTR
        INSTRUCTION_INFO_BY_OPCODE.put(0x74, new Instruction(2, 0, MOV));      // MOV	A, #immed
        INSTRUCTION_INFO_BY_OPCODE.put(0x75, new Instruction(3, 0, MOV));      // MOV	direct, #immed
        INSTRUCTION_INFO_BY_OPCODE.put(0x76, new Instruction(2, 0, MOV));      // MOV	@R0, #immed
        INSTRUCTION_INFO_BY_OPCODE.put(0x77, new Instruction(2, 0, MOV));      // MOV	@R1, #immed
        INSTRUCTION_INFO_BY_OPCODE.put(0x78, new Instruction(2, 0, MOV));      // MOV	R0, #immed
        INSTRUCTION_INFO_BY_OPCODE.put(0x79, new Instruction(2, 0, MOV));      // MOV	R1, #immed
        INSTRUCTION_INFO_BY_OPCODE.put(0x7A, new Instruction(2, 0, MOV));      // MOV	R2, #immed
        INSTRUCTION_INFO_BY_OPCODE.put(0x7B, new Instruction(2, 0, MOV));      // MOV	R3, #immed
        INSTRUCTION_INFO_BY_OPCODE.put(0x7C, new Instruction(2, 0, MOV));      // MOV	R4, #immed
        INSTRUCTION_INFO_BY_OPCODE.put(0x7D, new Instruction(2, 0, MOV));      // MOV	R5, #immed
        INSTRUCTION_INFO_BY_OPCODE.put(0x7E, new Instruction(2, 0, MOV));      // MOV	R6, #immed
        INSTRUCTION_INFO_BY_OPCODE.put(0x7F, new Instruction(2, 0, MOV));      // MOV	R7, #immed

        INSTRUCTION_INFO_BY_OPCODE.put(0x80, new Instruction(2, 0, SJMP));     // SJMP	offset
        INSTRUCTION_INFO_BY_OPCODE.put(0x81, new Instruction(2, 0, AJMP));     // AJMP	addr11
        INSTRUCTION_INFO_BY_OPCODE.put(0x82, new Instruction(2, 0, ANL));      // ANL	C, bit
        INSTRUCTION_INFO_BY_OPCODE.put(0x83, new Instruction(1, 0, MOVC));     // MOVC	A, @A+PC
        INSTRUCTION_INFO_BY_OPCODE.put(0x84, new Instruction(1, 0, DIV));      // DIV	AB
        INSTRUCTION_INFO_BY_OPCODE.put(0x85, new Instruction(3, 0, MOV));      // MOV	direct, direct
        INSTRUCTION_INFO_BY_OPCODE.put(0x86, new Instruction(2, 0, MOV));      // MOV	direct, @R0
        INSTRUCTION_INFO_BY_OPCODE.put(0x87, new Instruction(2, 0, MOV));      // MOV	direct, @R1
        INSTRUCTION_INFO_BY_OPCODE.put(0x88, new Instruction(2, 0, MOV));      // MOV	direct, R0
        INSTRUCTION_INFO_BY_OPCODE.put(0x89, new Instruction(2, 0, MOV));      // MOV	direct, R1
        INSTRUCTION_INFO_BY_OPCODE.put(0x8A, new Instruction(2, 0, MOV));      // MOV	direct, R2
        INSTRUCTION_INFO_BY_OPCODE.put(0x8B, new Instruction(2, 0, MOV));      // MOV	direct, R3
        INSTRUCTION_INFO_BY_OPCODE.put(0x8C, new Instruction(2, 0, MOV));      // MOV	direct, R4
        INSTRUCTION_INFO_BY_OPCODE.put(0x8D, new Instruction(2, 0, MOV));      // MOV	direct, R5
        INSTRUCTION_INFO_BY_OPCODE.put(0x8E, new Instruction(2, 0, MOV));      // MOV	direct, R6
        INSTRUCTION_INFO_BY_OPCODE.put(0x8F, new Instruction(2, 0, MOV));      // MOV	direct, R7
        INSTRUCTION_INFO_BY_OPCODE.put(0x90, new Instruction(3, 0, MOV));      // MOV	DPTR, #immed
        INSTRUCTION_INFO_BY_OPCODE.put(0x91, new Instruction(2, 0, ACALL));    // ACALL	addr11
        INSTRUCTION_INFO_BY_OPCODE.put(0x92, new Instruction(2, 0, MOV));      // MOV	bit, C
        INSTRUCTION_INFO_BY_OPCODE.put(0x93, new Instruction(1, 0, MOVC));     // MOVC	A, @A+DPTR
        INSTRUCTION_INFO_BY_OPCODE.put(0x94, new Instruction(2, 0, SUBB));     // SUBB	A, #immed
        INSTRUCTION_INFO_BY_OPCODE.put(0x95, new Instruction(2, 0, SUBB));     // SUBB	A, direct
        INSTRUCTION_INFO_BY_OPCODE.put(0x96, new Instruction(1, 0, SUBB));     // SUBB	A, @R0
        INSTRUCTION_INFO_BY_OPCODE.put(0x97, new Instruction(1, 0, SUBB));     // SUBB	A, @R1
        INSTRUCTION_INFO_BY_OPCODE.put(0x98, new Instruction(1, 0, SUBB));     // SUBB	A, R0
        INSTRUCTION_INFO_BY_OPCODE.put(0x99, new Instruction(1, 0, SUBB));     // SUBB	A, R1
        INSTRUCTION_INFO_BY_OPCODE.put(0x9A, new Instruction(1, 0, SUBB));     // SUBB	A, R2
        INSTRUCTION_INFO_BY_OPCODE.put(0x9B, new Instruction(1, 0, SUBB));     // SUBB	A, R3
        INSTRUCTION_INFO_BY_OPCODE.put(0x9C, new Instruction(1, 0, SUBB));     // SUBB	A, R4
        INSTRUCTION_INFO_BY_OPCODE.put(0x9D, new Instruction(1, 0, SUBB));     // SUBB	A, R5
        INSTRUCTION_INFO_BY_OPCODE.put(0x9E, new Instruction(1, 0, SUBB));     // SUBB	A, R6
        INSTRUCTION_INFO_BY_OPCODE.put(0x9F, new Instruction(1, 0, SUBB));     // SUBB	A, R7
        INSTRUCTION_INFO_BY_OPCODE.put(0xA0, new Instruction(2, 0, ORL));      // ORL	C, /bit
        INSTRUCTION_INFO_BY_OPCODE.put(0xA1, new Instruction(2, 0, AJMP));     // AJMP	addr11
        INSTRUCTION_INFO_BY_OPCODE.put(0xA2, new Instruction(2, 0, MOV));      // MOV	C, bit
        INSTRUCTION_INFO_BY_OPCODE.put(0xA3, new Instruction(1, 0, INC));      // INC	DPTR
        INSTRUCTION_INFO_BY_OPCODE.put(0xA4, new Instruction(1, 0, MUL));      // MUL	AB
        // INSTRUCTION_INFO_BY_OPCODE.put(0xA5, 0);    // reserved
        INSTRUCTION_INFO_BY_OPCODE.put(0xA6, new Instruction(2, 0, MOV));      // MOV	@R0, direct
        INSTRUCTION_INFO_BY_OPCODE.put(0xA7, new Instruction(2, 0, MOV));      // MOV	@R1, direct
        INSTRUCTION_INFO_BY_OPCODE.put(0xA8, new Instruction(2, 0, MOV));      // MOV	R0, direct
        INSTRUCTION_INFO_BY_OPCODE.put(0xA9, new Instruction(2, 0, MOV));      // MOV	R1, direct
        INSTRUCTION_INFO_BY_OPCODE.put(0xAA, new Instruction(2, 0, MOV));      // MOV	R2, direct
        INSTRUCTION_INFO_BY_OPCODE.put(0xAB, new Instruction(2, 0, MOV));      // MOV	R3, direct
        INSTRUCTION_INFO_BY_OPCODE.put(0xAC, new Instruction(2, 0, MOV));      // MOV	R4, direct
        INSTRUCTION_INFO_BY_OPCODE.put(0xAD, new Instruction(2, 0, MOV));      // MOV	R5, direct
        INSTRUCTION_INFO_BY_OPCODE.put(0xAE, new Instruction(2, 0, MOV));      // MOV	R6, direct
        INSTRUCTION_INFO_BY_OPCODE.put(0xAF, new Instruction(2, 0, MOV));      // MOV	R7, direct
        INSTRUCTION_INFO_BY_OPCODE.put(0xB0, new Instruction(2, 0, ANL));      // ANL	C, /bit
        INSTRUCTION_INFO_BY_OPCODE.put(0xB1, new Instruction(2, 0, ACALL));    // ACALL	addr11
        INSTRUCTION_INFO_BY_OPCODE.put(0xB2, new Instruction(2, 0, CPL));      // CPL	bit
        INSTRUCTION_INFO_BY_OPCODE.put(0xB3, new Instruction(1, 0, CPL));      // CPL	C
        INSTRUCTION_INFO_BY_OPCODE.put(0xB4, new Instruction(3, 0, CJNE));     // CJNE	A, #immed, offset
        INSTRUCTION_INFO_BY_OPCODE.put(0xB5, new Instruction(3, 0, CJNE));     // CJNE	A, direct, offset
        INSTRUCTION_INFO_BY_OPCODE.put(0xB6, new Instruction(3, 0, CJNE));     // CJNE	@R0, #immed, offset
        INSTRUCTION_INFO_BY_OPCODE.put(0xB7, new Instruction(3, 0, CJNE));     // CJNE	@R1, #immed, offset
        INSTRUCTION_INFO_BY_OPCODE.put(0xB8, new Instruction(3, 0, CJNE));     // CJNE	R0, #immed, offset
        INSTRUCTION_INFO_BY_OPCODE.put(0xB9, new Instruction(3, 0, CJNE));     // CJNE	R1, #immed, offset
        INSTRUCTION_INFO_BY_OPCODE.put(0xBA, new Instruction(3, 0, CJNE));     // CJNE	R2, #immed, offset
        INSTRUCTION_INFO_BY_OPCODE.put(0xBB, new Instruction(3, 0, CJNE));     // CJNE	R3, #immed, offset
        INSTRUCTION_INFO_BY_OPCODE.put(0xBC, new Instruction(3, 0, CJNE));     // CJNE	R4, #immed, offset
        INSTRUCTION_INFO_BY_OPCODE.put(0xBD, new Instruction(3, 0, CJNE));     // CJNE	R5, #immed, offset
        INSTRUCTION_INFO_BY_OPCODE.put(0xBE, new Instruction(3, 0, CJNE));     // CJNE	R6, #immed, offset
        INSTRUCTION_INFO_BY_OPCODE.put(0xBF, new Instruction(3, 0, CJNE));     // CJNE	R7, #immed, offset
        INSTRUCTION_INFO_BY_OPCODE.put(0xC0, new Instruction(2, 0, PUSH));     // PUSH	direct
        INSTRUCTION_INFO_BY_OPCODE.put(0xC1, new Instruction(2, 0, AJMP));     // AJMP	addr11
        INSTRUCTION_INFO_BY_OPCODE.put(0xC2, new Instruction(2, 0, CLR));      // CLR	bit
        INSTRUCTION_INFO_BY_OPCODE.put(0xC3, new Instruction(1, 0, CLR));      // CLR	C
        INSTRUCTION_INFO_BY_OPCODE.put(0xC4, new Instruction(1, 0, SWAP));     // SWAP	A
        INSTRUCTION_INFO_BY_OPCODE.put(0xC5, new Instruction(2, 0, XCH));      // XCH	A, direct
        INSTRUCTION_INFO_BY_OPCODE.put(0xC6, new Instruction(1, 0, XCH));      // XCH	A, @R0
        INSTRUCTION_INFO_BY_OPCODE.put(0xC7, new Instruction(1, 0, XCH));      // XCH	A, @R1
        INSTRUCTION_INFO_BY_OPCODE.put(0xC8, new Instruction(1, 0, XCH));      // XCH	A, R0
        INSTRUCTION_INFO_BY_OPCODE.put(0xC9, new Instruction(1, 0, XCH));      // XCH	A, R1
        INSTRUCTION_INFO_BY_OPCODE.put(0xCA, new Instruction(1, 0, XCH));      // XCH	A, R2
        INSTRUCTION_INFO_BY_OPCODE.put(0xCB, new Instruction(1, 0, XCH));      // XCH	A, R3
        INSTRUCTION_INFO_BY_OPCODE.put(0xCC, new Instruction(1, 0, XCH));      // XCH	A, R4
        INSTRUCTION_INFO_BY_OPCODE.put(0xCD, new Instruction(1, 0, XCH));      // XCH	A, R5
        INSTRUCTION_INFO_BY_OPCODE.put(0xCE, new Instruction(1, 0, XCH));      // XCH	A, R6
        INSTRUCTION_INFO_BY_OPCODE.put(0xCF, new Instruction(1, 0, XCH));      // XCH	A, R7
        INSTRUCTION_INFO_BY_OPCODE.put(0xD0, new Instruction(2, 0, POP));      // POP	direct
        INSTRUCTION_INFO_BY_OPCODE.put(0xD1, new Instruction(2, 0, ACALL));    // ACALL	addr11
        INSTRUCTION_INFO_BY_OPCODE.put(0xD2, new Instruction(2, 0, SETB));     // SETB	bit
        INSTRUCTION_INFO_BY_OPCODE.put(0xD3, new Instruction(1, 0, SETB));     // SETB	C
        INSTRUCTION_INFO_BY_OPCODE.put(0xD4, new Instruction(1, 0, DA));       // DA	A
        INSTRUCTION_INFO_BY_OPCODE.put(0xD5, new Instruction(3, 0, DJNZ));     // DJNZ	direct, offset
        INSTRUCTION_INFO_BY_OPCODE.put(0xD6, new Instruction(1, 0, XCHD));     // XCHD	A, @R0
        INSTRUCTION_INFO_BY_OPCODE.put(0xD7, new Instruction(1, 0, XCHD));     // XCHD	A, @R1
        INSTRUCTION_INFO_BY_OPCODE.put(0xD8, new Instruction(2, 0, DJNZ));     // DJNZ	R0, offset
        INSTRUCTION_INFO_BY_OPCODE.put(0xD9, new Instruction(2, 0, DJNZ));     // DJNZ	R1, offset
        INSTRUCTION_INFO_BY_OPCODE.put(0xDA, new Instruction(2, 0, DJNZ));     // DJNZ	R2, offset
        INSTRUCTION_INFO_BY_OPCODE.put(0xDB, new Instruction(2, 0, DJNZ));     // DJNZ	R3, offset
        INSTRUCTION_INFO_BY_OPCODE.put(0xDC, new Instruction(2, 0, DJNZ));     // DJNZ	R4, offset
        INSTRUCTION_INFO_BY_OPCODE.put(0xDD, new Instruction(2, 0, DJNZ));     // DJNZ	R5, offset
        INSTRUCTION_INFO_BY_OPCODE.put(0xDE, new Instruction(2, 0, DJNZ));     // DJNZ	R6, offset
        INSTRUCTION_INFO_BY_OPCODE.put(0xDF, new Instruction(2, 0, DJNZ));     // DJNZ	R7, offset
        INSTRUCTION_INFO_BY_OPCODE.put(0xE0, new Instruction(1, 0, MOVX));     // MOVX	A, @DPTR
        INSTRUCTION_INFO_BY_OPCODE.put(0xE1, new Instruction(2, 0, AJMP));     // AJMP	addr11
        INSTRUCTION_INFO_BY_OPCODE.put(0xE2, new Instruction(1, 0, MOVX));     // MOVX	A, @R0
        INSTRUCTION_INFO_BY_OPCODE.put(0xE3, new Instruction(1, 0, MOVX));     // MOVX	A, @R1
        INSTRUCTION_INFO_BY_OPCODE.put(0xE4, new Instruction(1, 0, CLR));      // CLR	A
        INSTRUCTION_INFO_BY_OPCODE.put(0xE5, new Instruction(2, 0, MOV));      // MOV	A, direct
        INSTRUCTION_INFO_BY_OPCODE.put(0xE6, new Instruction(1, 0, MOV));      // MOV	A, @R0
        INSTRUCTION_INFO_BY_OPCODE.put(0xE7, new Instruction(1, 0, MOV));      // MOV	A, @R1
        INSTRUCTION_INFO_BY_OPCODE.put(0xE8, new Instruction(1, 0, MOV));      // MOV	A, R0
        INSTRUCTION_INFO_BY_OPCODE.put(0xE9, new Instruction(1, 0, MOV));      // MOV	A, R1
        INSTRUCTION_INFO_BY_OPCODE.put(0xEA, new Instruction(1, 0, MOV));      // MOV	A, R2
        INSTRUCTION_INFO_BY_OPCODE.put(0xEB, new Instruction(1, 0, MOV));      // MOV	A, R3
        INSTRUCTION_INFO_BY_OPCODE.put(0xEC, new Instruction(1, 0, MOV));      // MOV	A, R4
        INSTRUCTION_INFO_BY_OPCODE.put(0xED, new Instruction(1, 0, MOV));      // MOV	A, R5
        INSTRUCTION_INFO_BY_OPCODE.put(0xEE, new Instruction(1, 0, MOV));      // MOV	A, R6
        INSTRUCTION_INFO_BY_OPCODE.put(0xEF, new Instruction(1, 0, MOV));      // MOV	A, R7
        INSTRUCTION_INFO_BY_OPCODE.put(0xF0, new Instruction(1, 0, MOVX));     // MOVX	@DPTR, A
        INSTRUCTION_INFO_BY_OPCODE.put(0xF1, new Instruction(2, 0, ACALL));    // ACALL	addr11
        INSTRUCTION_INFO_BY_OPCODE.put(0xF2, new Instruction(1, 0, MOVX));     // MOVX	@R0, A
        INSTRUCTION_INFO_BY_OPCODE.put(0xF3, new Instruction(1, 0, MOVX));     // MOVX	@R1, A
        INSTRUCTION_INFO_BY_OPCODE.put(0xF4, new Instruction(1, 0, CPL));      // CPL	A
        INSTRUCTION_INFO_BY_OPCODE.put(0xF5, new Instruction(2, 0, MOV));      // MOV	direct, A
        INSTRUCTION_INFO_BY_OPCODE.put(0xF6, new Instruction(1, 0, MOV));      // MOV	@R0, A
        INSTRUCTION_INFO_BY_OPCODE.put(0xF7, new Instruction(1, 0, MOV));      // MOV	@R1, A
        INSTRUCTION_INFO_BY_OPCODE.put(0xF8, new Instruction(1, 0, MOV));      // MOV	R0, A
        INSTRUCTION_INFO_BY_OPCODE.put(0xF9, new Instruction(1, 0, MOV));      // MOV	R1, A
        INSTRUCTION_INFO_BY_OPCODE.put(0xFA, new Instruction(1, 0, MOV));      // MOV	R2, A
        INSTRUCTION_INFO_BY_OPCODE.put(0xFB, new Instruction(1, 0, MOV));      // MOV	R3, A
        INSTRUCTION_INFO_BY_OPCODE.put(0xFC, new Instruction(1, 0, MOV));      // MOV	R4, A
        INSTRUCTION_INFO_BY_OPCODE.put(0xFD, new Instruction(1, 0, MOV));      // MOV	R5, A
        INSTRUCTION_INFO_BY_OPCODE.put(0xFE, new Instruction(1, 0, MOV));      // MOV	R6, A
        INSTRUCTION_INFO_BY_OPCODE.put(0xFF, new Instruction(1, 0, MOV));      // MOV	R7, A
    }

    public static Instruction getByOpcode(UInt8 opcode) {
        return getByOpcode(opcode.toInt());
    }

    public static Instruction getByOpcode(int opcode) {
        return INSTRUCTION_INFO_BY_OPCODE.get(opcode);
    }

}
