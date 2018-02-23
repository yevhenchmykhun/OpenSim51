package simulator.instruction;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import simulator.Simulator;
import simulator.memory.ExternalCode;
import simulator.memory.ExternalData;
import simulator.memory.InternalData;
import simulator.memory.datatype.UnsignedInt16;
import simulator.memory.datatype.UnsignedInt8;

public class InstructionTest {

    private Simulator s;

    private InternalData id;
    private ExternalData xd;
    private ExternalCode xc;

    @Before
    public void setUp() throws Exception {
        s = new Simulator();
        id = new InternalData();
        xd = new ExternalData();
        xc = new ExternalCode();
    }

    @Test
    public void NOP() throws Exception {
        Instruction instruction = Instruction.findByOpcode(0x0);
        Command command = instruction.getCommand();

        Assert.assertEquals(instruction.getBytes(), 1);

        command.execute(id, xd, xc, s, instruction);
        Assert.assertEquals(s.getPC(), new UnsignedInt16(1));
    }

    @Test
    public void MOV_74() throws Exception {
        Instruction instruction = Instruction.findByOpcode(0x74);
        Command command = instruction.getCommand();

        Assert.assertEquals(instruction.getBytes(), 2);

        UnsignedInt8 zero = new UnsignedInt8(0x0);
        id.ACC.setValue(zero);
        Assert.assertEquals(id.ACC.getValue(), zero);

        UnsignedInt8 seven = new UnsignedInt8(0x7);
        xc.setCellValue(s.getPC().inc(), seven);
        Assert.assertEquals(xc.getCellValue(s.getPC().inc()), seven);

        command.execute(id, xd, xc, s, instruction);

        Assert.assertEquals(id.ACC.getValue(), seven);
    }

}