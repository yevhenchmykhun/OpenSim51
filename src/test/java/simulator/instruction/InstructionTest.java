package simulator.instruction;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import simulator.Simulator;
import simulator.memory.ExternalCode;
import simulator.memory.ExternalData;
import simulator.memory.InternalData;
import simulator.memory.datatype.UInt16;
import simulator.memory.datatype.UInt8;

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
        Instruction instruction = Instruction.getByOpcode(0x0);

        Assert.assertEquals(instruction.getBytes(), 1);

        instruction.execute(s);
        Assert.assertEquals(s.getPC(), new UInt16(1));
    }

    @Test
    public void MOV_74() throws Exception {
        xc.setCellValue(s.getPC(), new UInt8(0x74));
        Instruction instruction = Instruction.getByOpcode(0x74);

        Assert.assertEquals(instruction.getBytes(), 2);

        UInt8 zero = new UInt8(0x0);
        id.ACC.setValue(zero);
        Assert.assertEquals(id.ACC.getValue(), zero);

        UInt8 seven = new UInt8(0x7);
        xc.setCellValue(s.getPC().inc(), seven);
        Assert.assertEquals(xc.getCellValue(s.getPC().inc()), seven);

        instruction.execute(s);

        Assert.assertEquals(id.ACC.getValue(), seven);
    }

}