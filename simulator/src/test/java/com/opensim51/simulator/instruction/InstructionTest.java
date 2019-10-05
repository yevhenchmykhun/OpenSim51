package com.opensim51.simulator.instruction;

import org.junit.Before;
import org.junit.Test;
import com.opensim51.simulator.Simulator;
import com.opensim51.simulator.memory.ExternalCode;
import com.opensim51.simulator.memory.ExternalData;
import com.opensim51.simulator.memory.InternalData;

public class InstructionTest {

    private Simulator s;

    private InternalData id;
    private ExternalData xd;
    private ExternalCode xc;

    @Before
    public void setUp() throws Exception {
        s = new Simulator();
        id = s.getInternalData();
        xd = s.getExternalData();
        xc = s.getExternalCode();
    }

    @Test
    public void NOP() throws Exception {
//        Instruction instruction = Instruction.getByOpcode(0x0);
//
//        assertEquals(instruction.getBytes(), 1);
//
//        instruction.execute(s);
//        assertEquals(s.getPC(), new UInt16(1));
    }

    @Test
    public void MOV_74() throws Exception {
//        xc.setCellValue(s.getPC(), new UInt8(0x74));
//        Instruction instruction = Instruction.getByOpcode(0x74);
//
//        assertEquals(instruction.getBytes(), 2);
//
//        UInt8 zero = new UInt8(0x0);
//        id.ACC.setValue(zero);
//        assertEquals(id.ACC.getValue(), zero);
//
//        UInt8 seven = new UInt8(0x7);
//        xc.setCellValue(s.getPC().inc(), seven);
//        assertEquals(xc.getCellValue(s.getPC().inc()), seven);
//
//        instruction.execute(s);
//
//        assertEquals(id.ACC.getValue(), seven);
    }

}