package assembler;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class InstructionTest {

    @Test
    public void nop() {
        Instruction nop = Instruction.getByMnemonic("NOP");

        List<Integer> machineCodes = nop.toMachineCodes(null);
        assertEquals(machineCodes.size(), 1);
        assertEquals((int) machineCodes.get(0), 0x00);
    }

    @Test
    public void ajmp() {
        Instruction ajmp = Instruction.getByMnemonic("AJMP");

        Operand operand = new Operand(OperandType.SYMBOL, 0x00);
        List<Integer> machineCodes = ajmp.toMachineCodes(Arrays.asList(operand));
        assertEquals(machineCodes.size(), 2);
        assertEquals((int) machineCodes.get(0), 0x01);
        assertEquals((int) machineCodes.get(1), 0x00);

        operand = new Operand(OperandType.SYMBOL, 0x07);
        machineCodes = ajmp.toMachineCodes(Arrays.asList(operand));
        assertEquals(machineCodes.size(), 2);
        assertEquals((int) machineCodes.get(0), 0x01);
        assertEquals((int) machineCodes.get(1), 0x07);

        operand = new Operand(OperandType.SYMBOL, 0x7ff);
        machineCodes = ajmp.toMachineCodes(Arrays.asList(operand));
        assertEquals(machineCodes.size(), 2);
        assertEquals((int) machineCodes.get(0), 0xe1);
        assertEquals((int) machineCodes.get(1), 0xff);

        operand = new Operand(OperandType.SYMBOL, 0x800);
        machineCodes = ajmp.toMachineCodes(Arrays.asList(operand));
        assertEquals(machineCodes.size(), 2);
        assertEquals((int) machineCodes.get(0), 0x01);
        assertEquals((int) machineCodes.get(1), 0x00);

        operand = new Operand(OperandType.SYMBOL, 0xfff);
        machineCodes = ajmp.toMachineCodes(Arrays.asList(operand));
        assertEquals(machineCodes.size(), 2);
        assertEquals((int) machineCodes.get(0), 0xe1);
        assertEquals((int) machineCodes.get(1), 0xff);
    }

    @Test
    public void ljmp() {
        Instruction ljmp = Instruction.getByMnemonic("LJMP");

        Operand operand = new Operand(OperandType.SYMBOL, 0x00);
        List<Integer> machineCodes = ljmp.toMachineCodes(Arrays.asList(operand));
        assertEquals(machineCodes.size(), 3);
        assertEquals((int) machineCodes.get(0), 0x02);
        assertEquals((int) machineCodes.get(1), 0x00);
        assertEquals((int) machineCodes.get(2), 0x00);

        operand = new Operand(OperandType.SYMBOL, 0x07);
        machineCodes = ljmp.toMachineCodes(Arrays.asList(operand));
        assertEquals(machineCodes.size(), 3);
        assertEquals((int) machineCodes.get(0), 0x02);
        assertEquals((int) machineCodes.get(1), 0x00);
        assertEquals((int) machineCodes.get(2), 0x07);

        operand = new Operand(OperandType.SYMBOL, 0x7ff);
        machineCodes = ljmp.toMachineCodes(Arrays.asList(operand));
        assertEquals(machineCodes.size(), 3);
        assertEquals((int) machineCodes.get(0), 0x02);
        assertEquals((int) machineCodes.get(1), 0x07);
        assertEquals((int) machineCodes.get(2), 0xff);

        operand = new Operand(OperandType.SYMBOL, 0xffff);
        machineCodes = ljmp.toMachineCodes(Arrays.asList(operand));
        assertEquals(machineCodes.size(), 3);
        assertEquals((int) machineCodes.get(0), 0x02);
        assertEquals((int) machineCodes.get(1), 0xff);
        assertEquals((int) machineCodes.get(2), 0xff);
    }

    @Test
    public void rr() {
        Instruction rr = Instruction.getByMnemonic("RR");

        List<Integer> machineCodes = rr.toMachineCodes(null);
        assertEquals(machineCodes.size(), 1);
        assertEquals((int) machineCodes.get(0), 0x03);
    }

    @Test
    public void inc() {
        Instruction inc = Instruction.getByMnemonic("INC");

        Operand operand = new Operand(OperandType.INDIRECT_REGISTER, 0x01);
        List<Integer> machineCodes = inc.toMachineCodes(Arrays.asList(operand));
        assertEquals(machineCodes.size(), 1);
        assertEquals((int) machineCodes.get(0), 0x07);

        operand = new Operand(OperandType.ACCUMULATOR, 0x00);
        machineCodes = inc.toMachineCodes(Arrays.asList(operand));
        assertEquals(machineCodes.size(), 1);
        assertEquals((int) machineCodes.get(0), 0x04);

        operand = new Operand(OperandType.DIRECT, 0x34);
        machineCodes = inc.toMachineCodes(Arrays.asList(operand));
        assertEquals(machineCodes.size(), 2);
        assertEquals((int) machineCodes.get(0), 0x05);
        assertEquals((int) machineCodes.get(1), 0x34);

        operand = new Operand(OperandType.DPTR, 0x00);
        machineCodes = inc.toMachineCodes(Arrays.asList(operand));
        assertEquals(machineCodes.size(), 1);
        assertEquals((int) machineCodes.get(0), 0xa3);

        operand = new Operand(OperandType.REGISTER, 0x07);
        machineCodes = inc.toMachineCodes(Arrays.asList(operand));
        assertEquals(machineCodes.size(), 1);
        assertEquals((int) machineCodes.get(0), 0x0f);
    }

    @Test
    public void jbc() {
        Instruction jbc = Instruction.getByMnemonic("JBC");

        Operand firstOperand = new Operand(OperandType.BIT, 0x44);
        Operand osecondOperand = new Operand(OperandType.SYMBOL, 0x10);
        List<Integer> machineCodes = jbc.toMachineCodes(Arrays.asList(firstOperand, osecondOperand));
        assertEquals(machineCodes.size(), 3);
        assertEquals((int) machineCodes.get(0), 0x10);
        assertEquals((int) machineCodes.get(1), 0x44);
        assertEquals((int) machineCodes.get(2), 0x10);
    }

    @Test
    public void acall() {
        Instruction acall = Instruction.getByMnemonic("ACALL");

        Operand operand = new Operand(OperandType.SYMBOL, 0x00);
        List<Integer> machineCodes = acall.toMachineCodes(Arrays.asList(operand));
        assertEquals(machineCodes.size(), 2);
        assertEquals((int) machineCodes.get(0), 0x11);
        assertEquals((int) machineCodes.get(1), 0x00);

        operand = new Operand(OperandType.SYMBOL, 0x07);
        machineCodes = acall.toMachineCodes(Arrays.asList(operand));
        assertEquals(machineCodes.size(), 2);
        assertEquals((int) machineCodes.get(0), 0x11);
        assertEquals((int) machineCodes.get(1), 0x07);

        operand = new Operand(OperandType.SYMBOL, 0x7ff);
        machineCodes = acall.toMachineCodes(Arrays.asList(operand));
        assertEquals(machineCodes.size(), 2);
        assertEquals((int) machineCodes.get(0), 0xf1);
        assertEquals((int) machineCodes.get(1), 0xff);

        operand = new Operand(OperandType.SYMBOL, 0x800);
        machineCodes = acall.toMachineCodes(Arrays.asList(operand));
        assertEquals(machineCodes.size(), 2);
        assertEquals((int) machineCodes.get(0), 0x11);
        assertEquals((int) machineCodes.get(1), 0x00);

        operand = new Operand(OperandType.SYMBOL, 0xfff);
        machineCodes = acall.toMachineCodes(Arrays.asList(operand));
        assertEquals(machineCodes.size(), 2);
        assertEquals((int) machineCodes.get(0), 0xf1);
        assertEquals((int) machineCodes.get(1), 0xff);
    }

    @Test
    public void lcall() {
        Instruction lcall = Instruction.getByMnemonic("LCALL");

        Operand operand = new Operand(OperandType.SYMBOL, 0x00);
        List<Integer> machineCodes = lcall.toMachineCodes(Arrays.asList(operand));
        assertEquals(machineCodes.size(), 3);
        assertEquals((int) machineCodes.get(0), 0x12);
        assertEquals((int) machineCodes.get(1), 0x00);
        assertEquals((int) machineCodes.get(2), 0x00);

        operand = new Operand(OperandType.SYMBOL, 0x07);
        machineCodes = lcall.toMachineCodes(Arrays.asList(operand));
        assertEquals(machineCodes.size(), 3);
        assertEquals((int) machineCodes.get(0), 0x12);
        assertEquals((int) machineCodes.get(1), 0x00);
        assertEquals((int) machineCodes.get(2), 0x07);

        operand = new Operand(OperandType.SYMBOL, 0x7ff);
        machineCodes = lcall.toMachineCodes(Arrays.asList(operand));
        assertEquals(machineCodes.size(), 3);
        assertEquals((int) machineCodes.get(0), 0x12);
        assertEquals((int) machineCodes.get(1), 0x07);
        assertEquals((int) machineCodes.get(2), 0xff);

        operand = new Operand(OperandType.SYMBOL, 0xffff);
        machineCodes = lcall.toMachineCodes(Arrays.asList(operand));
        assertEquals(machineCodes.size(), 3);
        assertEquals((int) machineCodes.get(0), 0x12);
        assertEquals((int) machineCodes.get(1), 0xff);
        assertEquals((int) machineCodes.get(2), 0xff);
    }

    @Test
    public void rrc() {
        Instruction rrc = Instruction.getByMnemonic("RRC");

        List<Integer> machineCodes = rrc.toMachineCodes(null);
        assertEquals(machineCodes.size(), 1);
        assertEquals((int) machineCodes.get(0), 0x13);
    }

    @Test
    public void dec() {
        Instruction dec = Instruction.getByMnemonic("DEC");

        Operand operand = new Operand(OperandType.INDIRECT_REGISTER, 0x01);
        List<Integer> machineCodes = dec.toMachineCodes(Arrays.asList(operand));
        assertEquals(machineCodes.size(), 1);
        assertEquals((int) machineCodes.get(0), 0x17);

        operand = new Operand(OperandType.ACCUMULATOR, 0x00);
        machineCodes = dec.toMachineCodes(Arrays.asList(operand));
        assertEquals(machineCodes.size(), 1);
        assertEquals((int) machineCodes.get(0), 0x14);

        operand = new Operand(OperandType.DIRECT, 0x34);
        machineCodes = dec.toMachineCodes(Arrays.asList(operand));
        assertEquals(machineCodes.size(), 2);
        assertEquals((int) machineCodes.get(0), 0x15);
        assertEquals((int) machineCodes.get(1), 0x34);

        operand = new Operand(OperandType.REGISTER, 0x07);
        machineCodes = dec.toMachineCodes(Arrays.asList(operand));
        assertEquals(machineCodes.size(), 1);
        assertEquals((int) machineCodes.get(0), 0x1f);
    }

    @Test
    public void jb() {
        Instruction jb = Instruction.getByMnemonic("JB");

        Operand firstOperand = new Operand(OperandType.BIT, 0x44);
        Operand secondOperand = new Operand(OperandType.SYMBOL, 0x10);
        List<Integer> machineCodes = jb.toMachineCodes(Arrays.asList(firstOperand, secondOperand));
        assertEquals(machineCodes.size(), 3);
        assertEquals((int) machineCodes.get(0), 0x20);
        assertEquals((int) machineCodes.get(1), 0x44);
        assertEquals((int) machineCodes.get(2), 0x10);
    }

    @Test
    public void ret() {
        Instruction ret = Instruction.getByMnemonic("RET");

        List<Integer> machineCodes = ret.toMachineCodes(null);
        assertEquals(machineCodes.size(), 1);
        assertEquals((int) machineCodes.get(0), 0x22);
    }

    @Test
    public void rl() {
        Instruction rl = Instruction.getByMnemonic("RL");

        List<Integer> machineCodes = rl.toMachineCodes(null);
        assertEquals(machineCodes.size(), 1);
        assertEquals((int) machineCodes.get(0), 0x23);
    }

    @Test
    public void add() {
        Instruction add = Instruction.getByMnemonic("ADD");

        Operand firstOperand = new Operand(OperandType.ACCUMULATOR, 0x00);
        Operand secondOperand = new Operand(OperandType.IMMEDIATE, 0x03);
        List<Integer> machineCodes = add.toMachineCodes(Arrays.asList(firstOperand, secondOperand));
        assertEquals(machineCodes.size(), 2);
        assertEquals((int) machineCodes.get(0), 0x24);
        assertEquals((int) machineCodes.get(1), 0x03);

        secondOperand = new Operand(OperandType.INDIRECT_REGISTER, 0x01);
        machineCodes = add.toMachineCodes(Arrays.asList(firstOperand, secondOperand));
        assertEquals(machineCodes.size(), 1);
        assertEquals((int) machineCodes.get(0), 0x27);

        secondOperand = new Operand(OperandType.DIRECT, 0x34);
        machineCodes = add.toMachineCodes(Arrays.asList(firstOperand, secondOperand));
        assertEquals(machineCodes.size(), 2);
        assertEquals((int) machineCodes.get(0), 0x25);
        assertEquals((int) machineCodes.get(1), 0x34);

        secondOperand = new Operand(OperandType.REGISTER, 0x07);
        machineCodes = add.toMachineCodes(Arrays.asList(firstOperand, secondOperand));
        assertEquals(machineCodes.size(), 1);
        assertEquals((int) machineCodes.get(0), 0x2f);
    }

    @Test
    public void jnb() {
        Instruction jnb = Instruction.getByMnemonic("JNB");

        Operand firstOperand = new Operand(OperandType.BIT, 0x44);
        Operand osecondOperand = new Operand(OperandType.SYMBOL, 0x10);
        List<Integer> machineCodes = jnb.toMachineCodes(Arrays.asList(firstOperand, osecondOperand));
        assertEquals(machineCodes.size(), 3);
        assertEquals((int) machineCodes.get(0), 0x30);
        assertEquals((int) machineCodes.get(1), 0x44);
        assertEquals((int) machineCodes.get(2), 0x10);
    }

    @Test
    public void reti() {
        Instruction reti = Instruction.getByMnemonic("RETI");

        List<Integer> machineCodes = reti.toMachineCodes(null);
        assertEquals(machineCodes.size(), 1);
        assertEquals((int) machineCodes.get(0), 0x32);
    }

    @Test
    public void rlc() {
        Instruction rlc = Instruction.getByMnemonic("RLC");

        List<Integer> machineCodes = rlc.toMachineCodes(null);
        assertEquals(machineCodes.size(), 1);
        assertEquals((int) machineCodes.get(0), 0x33);
    }

    @Test
    public void addc() {
        Instruction addc = Instruction.getByMnemonic("ADDC");

        Operand firstOperand = new Operand(OperandType.ACCUMULATOR, 0x00);
        Operand secondOperand = new Operand(OperandType.IMMEDIATE, 0x03);
        List<Integer> machineCodes = addc.toMachineCodes(Arrays.asList(firstOperand, secondOperand));
        assertEquals(machineCodes.size(), 2);
        assertEquals((int) machineCodes.get(0), 0x34);
        assertEquals((int) machineCodes.get(1), 0x03);

        secondOperand = new Operand(OperandType.INDIRECT_REGISTER, 0x01);
        machineCodes = addc.toMachineCodes(Arrays.asList(firstOperand, secondOperand));
        assertEquals(machineCodes.size(), 1);
        assertEquals((int) machineCodes.get(0), 0x37);

        secondOperand = new Operand(OperandType.DIRECT, 0x34);
        machineCodes = addc.toMachineCodes(Arrays.asList(firstOperand, secondOperand));
        assertEquals(machineCodes.size(), 2);
        assertEquals((int) machineCodes.get(0), 0x35);
        assertEquals((int) machineCodes.get(1), 0x34);

        secondOperand = new Operand(OperandType.REGISTER, 0x07);
        machineCodes = addc.toMachineCodes(Arrays.asList(firstOperand, secondOperand));
        assertEquals(machineCodes.size(), 1);
        assertEquals((int) machineCodes.get(0), 0x3f);
    }

    @Test
    public void jc() {
        Instruction jc = Instruction.getByMnemonic("JC");

        Operand operand = new Operand(OperandType.SYMBOL, 0x10);
        List<Integer> machineCodes = jc.toMachineCodes(Arrays.asList(operand));
        assertEquals(machineCodes.size(), 2);
        assertEquals((int) machineCodes.get(0), 0x40);
        assertEquals((int) machineCodes.get(1), 0x10);
    }

    @Test
    public void orl() {
        Instruction orl = Instruction.getByMnemonic("ORL");

        Operand firstOperand = new Operand(OperandType.ACCUMULATOR, 0x00);
        Operand secondOperand = new Operand(OperandType.IMMEDIATE, 0x03);
        List<Integer> machineCodes = orl.toMachineCodes(Arrays.asList(firstOperand, secondOperand));
        assertEquals(machineCodes.size(), 2);
        assertEquals((int) machineCodes.get(0), 0x44);
        assertEquals((int) machineCodes.get(1), 0x03);

        secondOperand = new Operand(OperandType.INDIRECT_REGISTER, 0x01);
        machineCodes = orl.toMachineCodes(Arrays.asList(firstOperand, secondOperand));
        assertEquals(machineCodes.size(), 1);
        assertEquals((int) machineCodes.get(0), 0x47);

        secondOperand = new Operand(OperandType.DIRECT, 0x34);
        machineCodes = orl.toMachineCodes(Arrays.asList(firstOperand, secondOperand));
        assertEquals(machineCodes.size(), 2);
        assertEquals((int) machineCodes.get(0), 0x45);
        assertEquals((int) machineCodes.get(1), 0x34);

        secondOperand = new Operand(OperandType.REGISTER, 0x07);
        machineCodes = orl.toMachineCodes(Arrays.asList(firstOperand, secondOperand));
        assertEquals(machineCodes.size(), 1);
        assertEquals((int) machineCodes.get(0), 0x4f);


        firstOperand = new Operand(OperandType.CARRY, 0x00);
        secondOperand = new Operand(OperandType.NOT_BIT, 0x22);
        machineCodes = orl.toMachineCodes(Arrays.asList(firstOperand, secondOperand));
        assertEquals(machineCodes.size(), 2);
        assertEquals((int) machineCodes.get(0), 0xa0);
        assertEquals((int) machineCodes.get(1), 0x22);

        secondOperand = new Operand(OperandType.BIT, 0x22);
        machineCodes = orl.toMachineCodes(Arrays.asList(firstOperand, secondOperand));
        assertEquals(machineCodes.size(), 2);
        assertEquals((int) machineCodes.get(0), 0x72);
        assertEquals((int) machineCodes.get(1), 0x22);


        firstOperand = new Operand(OperandType.DIRECT, 0xe0);
        secondOperand = new Operand(OperandType.IMMEDIATE, 0x11);
        machineCodes = orl.toMachineCodes(Arrays.asList(firstOperand, secondOperand));
        assertEquals(machineCodes.size(), 3);
        assertEquals((int) machineCodes.get(0), 0x43);
        assertEquals((int) machineCodes.get(1), 0xe0);
        assertEquals((int) machineCodes.get(2), 0x11);

        secondOperand = new Operand(OperandType.ACCUMULATOR, 0x00);
        machineCodes = orl.toMachineCodes(Arrays.asList(firstOperand, secondOperand));
        assertEquals(machineCodes.size(), 2);
        assertEquals((int) machineCodes.get(0), 0x42);
        assertEquals((int) machineCodes.get(1), 0xe0);
    }

    @Test
    public void jnc() {
        Instruction jnc = Instruction.getByMnemonic("JNC");

        Operand operand = new Operand(OperandType.SYMBOL, 0x10);
        List<Integer> machineCodes = jnc.toMachineCodes(Arrays.asList(operand));
        assertEquals(machineCodes.size(), 2);
        assertEquals((int) machineCodes.get(0), 0x50);
        assertEquals((int) machineCodes.get(1), 0x10);
    }

    @Test
    public void anl() {
        Instruction anl = Instruction.getByMnemonic("ANL");

        Operand firstOperand = new Operand(OperandType.ACCUMULATOR, 0x00);
        Operand secondOperand = new Operand(OperandType.IMMEDIATE, 0x03);
        List<Integer> machineCodes = anl.toMachineCodes(Arrays.asList(firstOperand, secondOperand));
        assertEquals(machineCodes.size(), 2);
        assertEquals((int) machineCodes.get(0), 0x54);
        assertEquals((int) machineCodes.get(1), 0x03);

        secondOperand = new Operand(OperandType.INDIRECT_REGISTER, 0x01);
        machineCodes = anl.toMachineCodes(Arrays.asList(firstOperand, secondOperand));
        assertEquals(machineCodes.size(), 1);
        assertEquals((int) machineCodes.get(0), 0x57);

        secondOperand = new Operand(OperandType.DIRECT, 0x34);
        machineCodes = anl.toMachineCodes(Arrays.asList(firstOperand, secondOperand));
        assertEquals(machineCodes.size(), 2);
        assertEquals((int) machineCodes.get(0), 0x55);
        assertEquals((int) machineCodes.get(1), 0x34);

        secondOperand = new Operand(OperandType.REGISTER, 0x07);
        machineCodes = anl.toMachineCodes(Arrays.asList(firstOperand, secondOperand));
        assertEquals(machineCodes.size(), 1);
        assertEquals((int) machineCodes.get(0), 0x5f);


        firstOperand = new Operand(OperandType.CARRY, 0x00);
        secondOperand = new Operand(OperandType.NOT_BIT, 0x22);
        machineCodes = anl.toMachineCodes(Arrays.asList(firstOperand, secondOperand));
        assertEquals(machineCodes.size(), 2);
        assertEquals((int) machineCodes.get(0), 0xb0);
        assertEquals((int) machineCodes.get(1), 0x22);

        secondOperand = new Operand(OperandType.BIT, 0x22);
        machineCodes = anl.toMachineCodes(Arrays.asList(firstOperand, secondOperand));
        assertEquals(machineCodes.size(), 2);
        assertEquals((int) machineCodes.get(0), 0x82);
        assertEquals((int) machineCodes.get(1), 0x22);


        firstOperand = new Operand(OperandType.DIRECT, 0xe0);
        secondOperand = new Operand(OperandType.IMMEDIATE, 0x11);
        machineCodes = anl.toMachineCodes(Arrays.asList(firstOperand, secondOperand));
        assertEquals(machineCodes.size(), 3);
        assertEquals((int) machineCodes.get(0), 0x53);
        assertEquals((int) machineCodes.get(1), 0xe0);
        assertEquals((int) machineCodes.get(2), 0x11);

        secondOperand = new Operand(OperandType.ACCUMULATOR, 0x00);
        machineCodes = anl.toMachineCodes(Arrays.asList(firstOperand, secondOperand));
        assertEquals(machineCodes.size(), 2);
        assertEquals((int) machineCodes.get(0), 0x52);
        assertEquals((int) machineCodes.get(1), 0xe0);
    }

    @Test
    public void jz() {
        Instruction jz = Instruction.getByMnemonic("JZ");

        Operand operand = new Operand(OperandType.SYMBOL, 0x10);
        List<Integer> machineCodes = jz.toMachineCodes(Arrays.asList(operand));
        assertEquals(machineCodes.size(), 2);
        assertEquals((int) machineCodes.get(0), 0x60);
        assertEquals((int) machineCodes.get(1), 0x10);
    }

    @Test
    public void xrl() {
        Instruction xrl = Instruction.getByMnemonic("XRL");

        Operand firstOperand = new Operand(OperandType.ACCUMULATOR, 0x00);
        Operand secondOperand = new Operand(OperandType.IMMEDIATE, 0x03);
        List<Integer> machineCodes = xrl.toMachineCodes(Arrays.asList(firstOperand, secondOperand));
        assertEquals(machineCodes.size(), 2);
        assertEquals((int) machineCodes.get(0), 0x64);
        assertEquals((int) machineCodes.get(1), 0x03);

        secondOperand = new Operand(OperandType.INDIRECT_REGISTER, 0x01);
        machineCodes = xrl.toMachineCodes(Arrays.asList(firstOperand, secondOperand));
        assertEquals(machineCodes.size(), 1);
        assertEquals((int) machineCodes.get(0), 0x67);

        secondOperand = new Operand(OperandType.DIRECT, 0x34);
        machineCodes = xrl.toMachineCodes(Arrays.asList(firstOperand, secondOperand));
        assertEquals(machineCodes.size(), 2);
        assertEquals((int) machineCodes.get(0), 0x65);
        assertEquals((int) machineCodes.get(1), 0x34);

        secondOperand = new Operand(OperandType.REGISTER, 0x07);
        machineCodes = xrl.toMachineCodes(Arrays.asList(firstOperand, secondOperand));
        assertEquals(machineCodes.size(), 1);
        assertEquals((int) machineCodes.get(0), 0x6f);


        firstOperand = new Operand(OperandType.DIRECT, 0xe0);
        secondOperand = new Operand(OperandType.IMMEDIATE, 0x11);
        machineCodes = xrl.toMachineCodes(Arrays.asList(firstOperand, secondOperand));
        assertEquals(machineCodes.size(), 3);
        assertEquals((int) machineCodes.get(0), 0x63);
        assertEquals((int) machineCodes.get(1), 0xe0);
        assertEquals((int) machineCodes.get(2), 0x11);

        secondOperand = new Operand(OperandType.ACCUMULATOR, 0x00);
        machineCodes = xrl.toMachineCodes(Arrays.asList(firstOperand, secondOperand));
        assertEquals(machineCodes.size(), 2);
        assertEquals((int) machineCodes.get(0), 0x62);
        assertEquals((int) machineCodes.get(1), 0xe0);
    }

    @Test
    public void jnz() {
        Instruction jnz = Instruction.getByMnemonic("JNZ");

        Operand operand = new Operand(OperandType.SYMBOL, 0x10);
        List<Integer> machineCodes = jnz.toMachineCodes(Arrays.asList(operand));
        assertEquals(machineCodes.size(), 2);
        assertEquals((int) machineCodes.get(0), 0x70);
        assertEquals((int) machineCodes.get(1), 0x10);
    }

    @Test
    public void jmp() {
        Instruction jmp = Instruction.getByMnemonic("JMP");

        List<Integer> machineCodes = jmp.toMachineCodes(null);
        assertEquals(machineCodes.size(), 1);
        assertEquals((int) machineCodes.get(0), 0x73);
    }

    @Test
    public void mov() {
        Instruction mov = Instruction.getByMnemonic("MOV");

        Operand firstOperand = new Operand(OperandType.INDIRECT_REGISTER, 0x00);
        Operand secondOperand = new Operand(OperandType.IMMEDIATE, 0x03);
        List<Integer> machineCodes = mov.toMachineCodes(Arrays.asList(firstOperand, secondOperand));
        assertEquals(machineCodes.size(), 2);
        assertEquals((int) machineCodes.get(0), 0x76);
        assertEquals((int) machineCodes.get(1), 0x03);

        secondOperand = new Operand(OperandType.ACCUMULATOR, 0x00);
        machineCodes = mov.toMachineCodes(Arrays.asList(firstOperand, secondOperand));
        assertEquals(machineCodes.size(), 1);
        assertEquals((int) machineCodes.get(0), 0xf6);

        secondOperand = new Operand(OperandType.DIRECT, 0x34);
        machineCodes = mov.toMachineCodes(Arrays.asList(firstOperand, secondOperand));
        assertEquals(machineCodes.size(), 2);
        assertEquals((int) machineCodes.get(0), 0xa6);
        assertEquals((int) machineCodes.get(1), 0x34);


        firstOperand = new Operand(OperandType.ACCUMULATOR, 0x00);
        secondOperand = new Operand(OperandType.IMMEDIATE, 0x03);
        machineCodes = mov.toMachineCodes(Arrays.asList(firstOperand, secondOperand));
        assertEquals(machineCodes.size(), 2);
        assertEquals((int) machineCodes.get(0), 0x74);
        assertEquals((int) machineCodes.get(1), 0x03);

        secondOperand = new Operand(OperandType.INDIRECT_REGISTER, 0x00);
        machineCodes = mov.toMachineCodes(Arrays.asList(firstOperand, secondOperand));
        assertEquals(machineCodes.size(), 1);
        assertEquals((int) machineCodes.get(0), 0xe6);

        secondOperand = new Operand(OperandType.DIRECT, 0x34);
        machineCodes = mov.toMachineCodes(Arrays.asList(firstOperand, secondOperand));
        assertEquals(machineCodes.size(), 2);
        assertEquals((int) machineCodes.get(0), 0xe5);
        assertEquals((int) machineCodes.get(1), 0x34);

        secondOperand = new Operand(OperandType.REGISTER, 0x02);
        machineCodes = mov.toMachineCodes(Arrays.asList(firstOperand, secondOperand));
        assertEquals(machineCodes.size(), 1);
        assertEquals((int) machineCodes.get(0), 0xea);


        firstOperand = new Operand(OperandType.BIT, 0x22);
        secondOperand = new Operand(OperandType.CARRY, 0x00);
        machineCodes = mov.toMachineCodes(Arrays.asList(firstOperand, secondOperand));
        assertEquals(machineCodes.size(), 2);
        assertEquals((int) machineCodes.get(0), 0x92);
        assertEquals((int) machineCodes.get(1), 0x22);


        firstOperand = new Operand(OperandType.CARRY, 0x00);
        secondOperand = new Operand(OperandType.BIT, 0x22);
        machineCodes = mov.toMachineCodes(Arrays.asList(firstOperand, secondOperand));
        assertEquals(machineCodes.size(), 2);
        assertEquals((int) machineCodes.get(0), 0xa2);
        assertEquals((int) machineCodes.get(1), 0x22);


        firstOperand = new Operand(OperandType.DIRECT, 0xe0);
        secondOperand = new Operand(OperandType.DIRECT, 0x00);
        machineCodes = mov.toMachineCodes(Arrays.asList(firstOperand, secondOperand));
        assertEquals(machineCodes.size(), 3);
        assertEquals((int) machineCodes.get(0), 0x85);
        assertEquals((int) machineCodes.get(1), 0x00);
        assertEquals((int) machineCodes.get(2), 0xe0);

        secondOperand = new Operand(OperandType.IMMEDIATE, 0x11);
        machineCodes = mov.toMachineCodes(Arrays.asList(firstOperand, secondOperand));
        assertEquals(machineCodes.size(), 3);
        assertEquals((int) machineCodes.get(0), 0x75);
        assertEquals((int) machineCodes.get(1), 0xe0);
        assertEquals((int) machineCodes.get(2), 0x11);

        secondOperand = new Operand(OperandType.INDIRECT_REGISTER, 0x00);
        machineCodes = mov.toMachineCodes(Arrays.asList(firstOperand, secondOperand));
        assertEquals(machineCodes.size(), 2);
        assertEquals((int) machineCodes.get(0), 0x86);
        assertEquals((int) machineCodes.get(1), 0xe0);

        secondOperand = new Operand(OperandType.ACCUMULATOR, 0x00);
        machineCodes = mov.toMachineCodes(Arrays.asList(firstOperand, secondOperand));
        assertEquals(machineCodes.size(), 2);
        assertEquals((int) machineCodes.get(0), 0xf5);
        assertEquals((int) machineCodes.get(1), 0xe0);

        secondOperand = new Operand(OperandType.REGISTER, 0x00);
        machineCodes = mov.toMachineCodes(Arrays.asList(firstOperand, secondOperand));
        assertEquals(machineCodes.size(), 2);
        assertEquals((int) machineCodes.get(0), 0x88);
        assertEquals((int) machineCodes.get(1), 0xe0);


        firstOperand = new Operand(OperandType.DPTR, 0x00);
        secondOperand = new Operand(OperandType.IMMEDIATE, 0x0103);
        machineCodes = mov.toMachineCodes(Arrays.asList(firstOperand, secondOperand));
        assertEquals(machineCodes.size(), 3);
        assertEquals((int) machineCodes.get(0), 0x90);
        assertEquals((int) machineCodes.get(1), 0x01);
        assertEquals((int) machineCodes.get(2), 0x03);


        firstOperand = new Operand(OperandType.REGISTER, 0x00);
        secondOperand = new Operand(OperandType.IMMEDIATE, 0x03);
        machineCodes = mov.toMachineCodes(Arrays.asList(firstOperand, secondOperand));
        assertEquals(machineCodes.size(), 2);
        assertEquals((int) machineCodes.get(0), 0x78);
        assertEquals((int) machineCodes.get(1), 0x03);

        secondOperand = new Operand(OperandType.ACCUMULATOR, 0x00);
        machineCodes = mov.toMachineCodes(Arrays.asList(firstOperand, secondOperand));
        assertEquals(machineCodes.size(), 1);
        assertEquals((int) machineCodes.get(0), 0xf8);

        secondOperand = new Operand(OperandType.DIRECT, 0x34);
        machineCodes = mov.toMachineCodes(Arrays.asList(firstOperand, secondOperand));
        assertEquals(machineCodes.size(), 2);
        assertEquals((int) machineCodes.get(0), 0xa8);
        assertEquals((int) machineCodes.get(1), 0x34);
    }

    @Test
    public void sjmp() {
        Instruction sjmp = Instruction.getByMnemonic("SJMP");

        Operand operand = new Operand(OperandType.SYMBOL, 0x10);
        List<Integer> machineCodes = sjmp.toMachineCodes(Arrays.asList(operand));
        assertEquals(machineCodes.size(), 2);
        assertEquals((int) machineCodes.get(0), 0x80);
        assertEquals((int) machineCodes.get(1), 0x10);
    }

    @Test
    public void movc() {
        Instruction movc = Instruction.getByMnemonic("MOVC");

        Operand firstOperand = new Operand(OperandType.ACCUMULATOR, 0x00);
        Operand secondOperand = new Operand(OperandType.AT_A_PLUS_DPTR, 0x00);
        List<Integer> machineCodes = movc.toMachineCodes(Arrays.asList(firstOperand, secondOperand));
        assertEquals(machineCodes.size(), 1);
        assertEquals((int) machineCodes.get(0), 0x93);

        secondOperand = new Operand(OperandType.AT_A_PLUS_PC, 0x00);
        machineCodes = movc.toMachineCodes(Arrays.asList(firstOperand, secondOperand));
        assertEquals(machineCodes.size(), 1);
        assertEquals((int) machineCodes.get(0), 0x83);
    }

    @Test
    public void div() {
        Instruction div = Instruction.getByMnemonic("DIV");

        List<Integer> machineCodes = div.toMachineCodes(null);
        assertEquals(machineCodes.size(), 1);
        assertEquals((int) machineCodes.get(0), 0x84);
    }

    @Test
    public void subb() {
        Instruction subb = Instruction.getByMnemonic("SUBB");

        Operand firstOperand = new Operand(OperandType.ACCUMULATOR, 0x00);
        Operand secondOperand = new Operand(OperandType.IMMEDIATE, 0x03);
        List<Integer> machineCodes = subb.toMachineCodes(Arrays.asList(firstOperand, secondOperand));
        assertEquals(machineCodes.size(), 2);
        assertEquals((int) machineCodes.get(0), 0x94);
        assertEquals((int) machineCodes.get(1), 0x03);

        secondOperand = new Operand(OperandType.INDIRECT_REGISTER, 0x01);
        machineCodes = subb.toMachineCodes(Arrays.asList(firstOperand, secondOperand));
        assertEquals(machineCodes.size(), 1);
        assertEquals((int) machineCodes.get(0), 0x97);

        secondOperand = new Operand(OperandType.DIRECT, 0x34);
        machineCodes = subb.toMachineCodes(Arrays.asList(firstOperand, secondOperand));
        assertEquals(machineCodes.size(), 2);
        assertEquals((int) machineCodes.get(0), 0x95);
        assertEquals((int) machineCodes.get(1), 0x34);

        secondOperand = new Operand(OperandType.REGISTER, 0x07);
        machineCodes = subb.toMachineCodes(Arrays.asList(firstOperand, secondOperand));
        assertEquals(machineCodes.size(), 1);
        assertEquals((int) machineCodes.get(0), 0x9f);
    }

    @Test
    public void mul() {
        Instruction mul = Instruction.getByMnemonic("MUL");

        List<Integer> machineCodes = mul.toMachineCodes(null);
        assertEquals(machineCodes.size(), 1);
        assertEquals((int) machineCodes.get(0), 0xa4);
    }

    @Test
    public void cpl() {
        Instruction cpl = Instruction.getByMnemonic("CPL");

        Operand firstOperand = new Operand(OperandType.ACCUMULATOR, 0x00);
        List<Integer> machineCodes = cpl.toMachineCodes(Arrays.asList(firstOperand));
        assertEquals(machineCodes.size(), 1);
        assertEquals((int) machineCodes.get(0), 0xf4);

        firstOperand = new Operand(OperandType.BIT, 0x01);
        machineCodes = cpl.toMachineCodes(Arrays.asList(firstOperand));
        assertEquals(machineCodes.size(), 2);
        assertEquals((int) machineCodes.get(0), 0xb2);
        assertEquals((int) machineCodes.get(1), 0x01);

        firstOperand = new Operand(OperandType.CARRY, 0x00);
        machineCodes = cpl.toMachineCodes(Arrays.asList(firstOperand));
        assertEquals(machineCodes.size(), 1);
        assertEquals((int) machineCodes.get(0), 0xb3);
    }

    @Test
    public void cjne() {
        Instruction cjne = Instruction.getByMnemonic("CJNE");

        Operand firstOperand = new Operand(OperandType.INDIRECT_REGISTER, 0x01);
        Operand secondOperand = new Operand(OperandType.IMMEDIATE, 0x24);
        Operand thirdOperand = new Operand(OperandType.SYMBOL, 0xff);
        List<Integer> machineCodes = cjne.toMachineCodes(Arrays.asList(firstOperand, secondOperand, thirdOperand));
        assertEquals(machineCodes.size(), 3);
        assertEquals((int) machineCodes.get(0), 0xb7);
        assertEquals((int) machineCodes.get(1), 0x24);
        assertEquals((int) machineCodes.get(2), 0xff);


        firstOperand = new Operand(OperandType.ACCUMULATOR, 0x00);
        secondOperand = new Operand(OperandType.IMMEDIATE, 0x24);
        thirdOperand = new Operand(OperandType.SYMBOL, 0xff);
        machineCodes = cjne.toMachineCodes(Arrays.asList(firstOperand, secondOperand, thirdOperand));
        assertEquals(machineCodes.size(), 3);
        assertEquals((int) machineCodes.get(0), 0xb4);
        assertEquals((int) machineCodes.get(1), 0x24);
        assertEquals((int) machineCodes.get(2), 0xff);

        secondOperand = new Operand(OperandType.DIRECT, 0x60);
        thirdOperand = new Operand(OperandType.SYMBOL, 0xff);
        machineCodes = cjne.toMachineCodes(Arrays.asList(firstOperand, secondOperand, thirdOperand));
        assertEquals(machineCodes.size(), 3);
        assertEquals((int) machineCodes.get(0), 0xb5);
        assertEquals((int) machineCodes.get(1), 0x60);
        assertEquals((int) machineCodes.get(2), 0xff);


        firstOperand = new Operand(OperandType.REGISTER, 0x02);
        secondOperand = new Operand(OperandType.IMMEDIATE, 0x24);
        thirdOperand = new Operand(OperandType.SYMBOL, 0xff);
        machineCodes = cjne.toMachineCodes(Arrays.asList(firstOperand, secondOperand, thirdOperand));
        assertEquals(machineCodes.size(), 3);
        assertEquals((int) machineCodes.get(0), 0xba);
        assertEquals((int) machineCodes.get(1), 0x24);
        assertEquals((int) machineCodes.get(2), 0xff);
    }

    @Test
    public void push() {
        Instruction push = Instruction.getByMnemonic("PUSH");

        Operand operand = new Operand(OperandType.DIRECT, 0xe0);
        List<Integer> machineCodes = push.toMachineCodes(Arrays.asList(operand));
        assertEquals(machineCodes.size(), 2);
        assertEquals((int) machineCodes.get(0), 0xc0);
        assertEquals((int) machineCodes.get(1), 0xe0);
    }

    @Test
    public void clr() {
        Instruction clr = Instruction.getByMnemonic("CLR");

        Operand firstOperand = new Operand(OperandType.ACCUMULATOR, 0x00);
        List<Integer> machineCodes = clr.toMachineCodes(Arrays.asList(firstOperand));
        assertEquals(machineCodes.size(), 1);
        assertEquals((int) machineCodes.get(0), 0xe4);

        firstOperand = new Operand(OperandType.BIT, 0x01);
        machineCodes = clr.toMachineCodes(Arrays.asList(firstOperand));
        assertEquals(machineCodes.size(), 2);
        assertEquals((int) machineCodes.get(0), 0xc2);
        assertEquals((int) machineCodes.get(1), 0x01);

        firstOperand = new Operand(OperandType.CARRY, 0x00);
        machineCodes = clr.toMachineCodes(Arrays.asList(firstOperand));
        assertEquals(machineCodes.size(), 1);
        assertEquals((int) machineCodes.get(0), 0xc3);
    }

    @Test
    public void swap() {
        Instruction swap = Instruction.getByMnemonic("SWAP");

        List<Integer> machineCodes = swap.toMachineCodes(null);
        assertEquals(machineCodes.size(), 1);
        assertEquals((int) machineCodes.get(0), 0xc4);
    }

    @Test
    public void xch() {
        Instruction xch = Instruction.getByMnemonic("XCH");

        Operand firstOperand = new Operand(OperandType.ACCUMULATOR, 0x00);
        Operand secondOperand = new Operand(OperandType.INDIRECT_REGISTER, 0x01);
        List<Integer> machineCodes = xch.toMachineCodes(Arrays.asList(firstOperand, secondOperand));
        assertEquals(machineCodes.size(), 1);
        assertEquals((int) machineCodes.get(0), 0xc7);

        secondOperand = new Operand(OperandType.DIRECT, 0x34);
        machineCodes = xch.toMachineCodes(Arrays.asList(firstOperand, secondOperand));
        assertEquals(machineCodes.size(), 2);
        assertEquals((int) machineCodes.get(0), 0xc5);
        assertEquals((int) machineCodes.get(1), 0x34);

        secondOperand = new Operand(OperandType.REGISTER, 0x07);
        machineCodes = xch.toMachineCodes(Arrays.asList(firstOperand, secondOperand));
        assertEquals(machineCodes.size(), 1);
        assertEquals((int) machineCodes.get(0), 0xcf);
    }

    @Test
    public void pop() {
        Instruction pop = Instruction.getByMnemonic("POP");

        Operand operand = new Operand(OperandType.DIRECT, 0xe0);
        List<Integer> machineCodes = pop.toMachineCodes(Arrays.asList(operand));
        assertEquals(machineCodes.size(), 2);
        assertEquals((int) machineCodes.get(0), 0xd0);
        assertEquals((int) machineCodes.get(1), 0xe0);
    }

    @Test
    public void setb() {
        Instruction setb = Instruction.getByMnemonic("SETB");

        Operand firstOperand = new Operand(OperandType.BIT, 0x01);
        List<Integer> machineCodes = setb.toMachineCodes(Arrays.asList(firstOperand));
        assertEquals(machineCodes.size(), 2);
        assertEquals((int) machineCodes.get(0), 0xd2);
        assertEquals((int) machineCodes.get(1), 0x01);

        firstOperand = new Operand(OperandType.CARRY, 0x00);
        machineCodes = setb.toMachineCodes(Arrays.asList(firstOperand));
        assertEquals(machineCodes.size(), 1);
        assertEquals((int) machineCodes.get(0), 0xd3);
    }

    @Test
    public void da() {
        Instruction da = Instruction.getByMnemonic("DA");

        List<Integer> machineCodes = da.toMachineCodes(null);
        assertEquals(machineCodes.size(), 1);
        assertEquals((int) machineCodes.get(0), 0xd4);
    }

    @Test
    public void djnz() {
        Instruction djnz = Instruction.getByMnemonic("DJNZ");

        Operand firstOperand = new Operand(OperandType.DIRECT, 0x00);
        Operand secondOperand = new Operand(OperandType.SYMBOL, 0x34);
        List<Integer> machineCodes = djnz.toMachineCodes(Arrays.asList(firstOperand, secondOperand));
        assertEquals(machineCodes.size(), 3);
        assertEquals((int) machineCodes.get(0), 0xd5);
        assertEquals((int) machineCodes.get(1), 0x00);
        assertEquals((int) machineCodes.get(2), 0x34);

        firstOperand = new Operand(OperandType.REGISTER, 0x07);
        secondOperand = new Operand(OperandType.SYMBOL, 0x34);
        machineCodes = djnz.toMachineCodes(Arrays.asList(firstOperand, secondOperand));
        assertEquals(machineCodes.size(), 2);
        assertEquals((int) machineCodes.get(0), 0xdf);
        assertEquals((int) machineCodes.get(1), 0x34);
    }

    @Test
    public void xchd() {
        Instruction xchd = Instruction.getByMnemonic("XCHD");

        Operand firstOperand = new Operand(OperandType.ACCUMULATOR, 0x00);
        Operand secondOperand = new Operand(OperandType.INDIRECT_REGISTER, 0x01);
        List<Integer> machineCodes = xchd.toMachineCodes(Arrays.asList(firstOperand, secondOperand));
        assertEquals(machineCodes.size(), 1);
        assertEquals((int) machineCodes.get(0), 0xd7);
    }

    @Test
    public void movx() {
        Instruction movx = Instruction.getByMnemonic("MOVX");

        Operand firstOperand = new Operand(OperandType.INDIRECT_REGISTER, 0x01);
        Operand secondOperand = new Operand(OperandType.ACCUMULATOR, 0x00);
        List<Integer> machineCodes = movx.toMachineCodes(Arrays.asList(firstOperand, secondOperand));
        assertEquals(machineCodes.size(), 1);
        assertEquals((int) machineCodes.get(0), 0xf3);


        firstOperand = new Operand(OperandType.ACCUMULATOR, 0x00);
        secondOperand = new Operand(OperandType.AT_DPTR, 0x00);
        machineCodes = movx.toMachineCodes(Arrays.asList(firstOperand, secondOperand));
        assertEquals(machineCodes.size(), 1);
        assertEquals((int) machineCodes.get(0), 0xe0);

        secondOperand = new Operand(OperandType.INDIRECT_REGISTER, 0x01);
        machineCodes = movx.toMachineCodes(Arrays.asList(firstOperand, secondOperand));
        assertEquals(machineCodes.size(), 1);
        assertEquals((int) machineCodes.get(0), 0xe3);
    }

}