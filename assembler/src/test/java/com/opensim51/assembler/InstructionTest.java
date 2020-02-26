package com.opensim51.assembler;

import com.opensim51.assembler.mcs51.mcu8051.Instruction;
import com.opensim51.assembler.mcs51.mcu8051.InstructionFactory;
import com.opensim51.assembler.mcs51.mcu8051.types.Operand;
import com.opensim51.assembler.mcs51.mcu8051.types.OperandType;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class InstructionTest {

    @Test
    public void nop() {
        Instruction nop = InstructionFactory.getByMnemonic("NOP");

        List<Integer> machineCodes = nop.toMachineCodes(null, null);
        assertEquals(1, machineCodes.size());
        assertEquals(0x00, (int) machineCodes.get(0));
    }

    @Test
    public void ajmp() {
        Instruction ajmp = InstructionFactory.getByMnemonic("AJMP");

        Operand operand = new Operand(OperandType.SYMBOL, 0x00);
        List<Integer> machineCodes = ajmp.toMachineCodes(null, Arrays.asList(operand));
        assertEquals(2, machineCodes.size());
        assertEquals(0x01, (int) machineCodes.get(0));
        assertEquals(0x00, (int) machineCodes.get(1));

        operand = new Operand(OperandType.SYMBOL, 0x07);
        machineCodes = ajmp.toMachineCodes(null, Arrays.asList(operand));
        assertEquals(2, machineCodes.size());
        assertEquals(0x01, (int) machineCodes.get(0));
        assertEquals(0x07, (int) machineCodes.get(1));

        operand = new Operand(OperandType.SYMBOL, 0x7ff);
        machineCodes = ajmp.toMachineCodes(null, Arrays.asList(operand));
        assertEquals(2, machineCodes.size());
        assertEquals(0xe1, (int) machineCodes.get(0));
        assertEquals(0xff, (int) machineCodes.get(1));

        operand = new Operand(OperandType.SYMBOL, 0x800);
        machineCodes = ajmp.toMachineCodes(null, Arrays.asList(operand));
        assertEquals(2, machineCodes.size());
        assertEquals(0x01, (int) machineCodes.get(0));
        assertEquals(0x00, (int) machineCodes.get(1));

        operand = new Operand(OperandType.SYMBOL, 0xfff);
        machineCodes = ajmp.toMachineCodes(null, Arrays.asList(operand));
        assertEquals(2, machineCodes.size());
        assertEquals(0xe1, (int) machineCodes.get(0));
        assertEquals(0xff, (int) machineCodes.get(1));
    }

    @Test
    public void ljmp() {
        Instruction ljmp = InstructionFactory.getByMnemonic("LJMP");

        Operand operand = new Operand(OperandType.SYMBOL, 0x00);
        List<Integer> machineCodes = ljmp.toMachineCodes(null, Arrays.asList(operand));
        assertEquals(3, machineCodes.size());
        assertEquals(0x02, (int) machineCodes.get(0));
        assertEquals(0x00, (int) machineCodes.get(1));
        assertEquals(0x00, (int) machineCodes.get(2));

        operand = new Operand(OperandType.SYMBOL, 0x07);
        machineCodes = ljmp.toMachineCodes(null, Arrays.asList(operand));
        assertEquals(3, machineCodes.size());
        assertEquals(0x02, (int) machineCodes.get(0));
        assertEquals(0x00, (int) machineCodes.get(1));
        assertEquals(0x07, (int) machineCodes.get(2));

        operand = new Operand(OperandType.SYMBOL, 0x7ff);
        machineCodes = ljmp.toMachineCodes(null, Arrays.asList(operand));
        assertEquals(3, machineCodes.size());
        assertEquals(0x02, (int) machineCodes.get(0));
        assertEquals(0x07, (int) machineCodes.get(1));
        assertEquals(0xff, (int) machineCodes.get(2));

        operand = new Operand(OperandType.SYMBOL, 0xffff);
        machineCodes = ljmp.toMachineCodes(null, Arrays.asList(operand));
        assertEquals(3, machineCodes.size());
        assertEquals(0x02, (int) machineCodes.get(0));
        assertEquals(0xff, (int) machineCodes.get(1));
        assertEquals(0xff, (int) machineCodes.get(2));
    }

    @Test
    public void rr() {
        Instruction rr = InstructionFactory.getByMnemonic("RR");

        List<Integer> machineCodes = rr.toMachineCodes(null, null);
        assertEquals(1, machineCodes.size());
        assertEquals(0x03, (int) machineCodes.get(0));
    }

    @Test
    public void inc() {
        Instruction inc = InstructionFactory.getByMnemonic("INC");

        Operand operand = new Operand(OperandType.INDIRECT_REGISTER, 0x01);
        List<Integer> machineCodes = inc.toMachineCodes(null, Arrays.asList(operand));
        assertEquals(1, machineCodes.size());
        assertEquals(0x07, (int) machineCodes.get(0));

        operand = new Operand(OperandType.ACCUMULATOR, 0x00);
        machineCodes = inc.toMachineCodes(null, Arrays.asList(operand));
        assertEquals(1, machineCodes.size());
        assertEquals(0x04, (int) machineCodes.get(0));

        operand = new Operand(OperandType.DIRECT, 0x34);
        machineCodes = inc.toMachineCodes(null, Arrays.asList(operand));
        assertEquals(2, machineCodes.size());
        assertEquals(0x05, (int) machineCodes.get(0));
        assertEquals(0x34, (int) machineCodes.get(1));

        operand = new Operand(OperandType.DPTR, 0x00);
        machineCodes = inc.toMachineCodes(null, Arrays.asList(operand));
        assertEquals(1, machineCodes.size());
        assertEquals(0xa3, (int) machineCodes.get(0));

        operand = new Operand(OperandType.REGISTER, 0x07);
        machineCodes = inc.toMachineCodes(null, Arrays.asList(operand));
        assertEquals(1, machineCodes.size());
        assertEquals(0x0f, (int) machineCodes.get(0));
    }

    @Test
    public void jbc() {
        Instruction jbc = InstructionFactory.getByMnemonic("JBC");

        Operand firstOperand = new Operand(OperandType.BIT, 0x44);
        Operand osecondOperand = new Operand(OperandType.SYMBOL, 0x10);
        List<Integer> machineCodes = jbc.toMachineCodes(null, Arrays.asList(firstOperand, osecondOperand));
        assertEquals(3, machineCodes.size());
        assertEquals(0x10, (int) machineCodes.get(0));
        assertEquals(0x44, (int) machineCodes.get(1));
        assertEquals(0x10, (int) machineCodes.get(2));
    }

    @Test
    public void acall() {
        Instruction acall = InstructionFactory.getByMnemonic("ACALL");

        Operand operand = new Operand(OperandType.SYMBOL, 0x00);
        List<Integer> machineCodes = acall.toMachineCodes(null, Arrays.asList(operand));
        assertEquals(2, machineCodes.size());
        assertEquals(0x11, (int) machineCodes.get(0));
        assertEquals(0x00, (int) machineCodes.get(1));

        operand = new Operand(OperandType.SYMBOL, 0x07);
        machineCodes = acall.toMachineCodes(null, Arrays.asList(operand));
        assertEquals(2, machineCodes.size());
        assertEquals(0x11, (int) machineCodes.get(0));
        assertEquals(0x07, (int) machineCodes.get(1));

        operand = new Operand(OperandType.SYMBOL, 0x7ff);
        machineCodes = acall.toMachineCodes(null, Arrays.asList(operand));
        assertEquals(2, machineCodes.size());
        assertEquals(0xf1, (int) machineCodes.get(0));
        assertEquals(0xff, (int) machineCodes.get(1));

        operand = new Operand(OperandType.SYMBOL, 0x800);
        machineCodes = acall.toMachineCodes(null, Arrays.asList(operand));
        assertEquals(2, machineCodes.size());
        assertEquals(0x11, (int) machineCodes.get(0));
        assertEquals(0x00, (int) machineCodes.get(1));

        operand = new Operand(OperandType.SYMBOL, 0xfff);
        machineCodes = acall.toMachineCodes(null, Arrays.asList(operand));
        assertEquals(2, machineCodes.size());
        assertEquals(0xf1, (int) machineCodes.get(0));
        assertEquals(0xff, (int) machineCodes.get(1));
    }

    @Test
    public void lcall() {
        Instruction lcall = InstructionFactory.getByMnemonic("LCALL");

        Operand operand = new Operand(OperandType.SYMBOL, 0x00);
        List<Integer> machineCodes = lcall.toMachineCodes(null, Arrays.asList(operand));
        assertEquals(3, machineCodes.size());
        assertEquals(0x12, (int) machineCodes.get(0));
        assertEquals(0x00, (int) machineCodes.get(1));
        assertEquals(0x00, (int) machineCodes.get(2));

        operand = new Operand(OperandType.SYMBOL, 0x07);
        machineCodes = lcall.toMachineCodes(null, Arrays.asList(operand));
        assertEquals(3, machineCodes.size());
        assertEquals(0x12, (int) machineCodes.get(0));
        assertEquals(0x00, (int) machineCodes.get(1));
        assertEquals(0x07, (int) machineCodes.get(2));

        operand = new Operand(OperandType.SYMBOL, 0x7ff);
        machineCodes = lcall.toMachineCodes(null, Arrays.asList(operand));
        assertEquals(3, machineCodes.size());
        assertEquals(0x12, (int) machineCodes.get(0));
        assertEquals(0x07, (int) machineCodes.get(1));
        assertEquals(0xff, (int) machineCodes.get(2));

        operand = new Operand(OperandType.SYMBOL, 0xffff);
        machineCodes = lcall.toMachineCodes(null, Arrays.asList(operand));
        assertEquals(3, machineCodes.size());
        assertEquals(0x12, (int) machineCodes.get(0));
        assertEquals(0xff, (int) machineCodes.get(1));
        assertEquals(0xff, (int) machineCodes.get(2));
    }

    @Test
    public void rrc() {
        Instruction rrc = InstructionFactory.getByMnemonic("RRC");

        List<Integer> machineCodes = rrc.toMachineCodes(null, null);
        assertEquals(1, machineCodes.size());
        assertEquals(0x13, (int) machineCodes.get(0));
    }

    @Test
    public void dec() {
        Instruction dec = InstructionFactory.getByMnemonic("DEC");

        Operand operand = new Operand(OperandType.INDIRECT_REGISTER, 0x01);
        List<Integer> machineCodes = dec.toMachineCodes(null, Arrays.asList(operand));
        assertEquals(1, machineCodes.size());
        assertEquals(0x17, (int) machineCodes.get(0));

        operand = new Operand(OperandType.ACCUMULATOR, 0x00);
        machineCodes = dec.toMachineCodes(null, Arrays.asList(operand));
        assertEquals(1, machineCodes.size());
        assertEquals(0x14, (int) machineCodes.get(0));

        operand = new Operand(OperandType.DIRECT, 0x34);
        machineCodes = dec.toMachineCodes(null, Arrays.asList(operand));
        assertEquals(2, machineCodes.size());
        assertEquals(0x15, (int) machineCodes.get(0));
        assertEquals(0x34, (int) machineCodes.get(1));

        operand = new Operand(OperandType.REGISTER, 0x07);
        machineCodes = dec.toMachineCodes(null, Arrays.asList(operand));
        assertEquals(1, machineCodes.size());
        assertEquals(0x1f, (int) machineCodes.get(0));
    }

    @Test
    public void jb() {
        Instruction jb = InstructionFactory.getByMnemonic("JB");

        Operand firstOperand = new Operand(OperandType.BIT, 0x44);
        Operand secondOperand = new Operand(OperandType.SYMBOL, 0x10);
        List<Integer> machineCodes = jb.toMachineCodes(null, Arrays.asList(firstOperand, secondOperand));
        assertEquals(3, machineCodes.size());
        assertEquals(0x20, (int) machineCodes.get(0));
        assertEquals(0x44, (int) machineCodes.get(1));
        assertEquals(0x10, (int) machineCodes.get(2));
    }

    @Test
    public void ret() {
        Instruction ret = InstructionFactory.getByMnemonic("RET");

        List<Integer> machineCodes = ret.toMachineCodes(null, null);
        assertEquals(1, machineCodes.size());
        assertEquals(0x22, (int) machineCodes.get(0));
    }

    @Test
    public void rl() {
        Instruction rl = InstructionFactory.getByMnemonic("RL");

        List<Integer> machineCodes = rl.toMachineCodes(null, null);
        assertEquals(1, machineCodes.size());
        assertEquals(0x23, (int) machineCodes.get(0));
    }

    @Test
    public void add() {
        Instruction add = InstructionFactory.getByMnemonic("ADD");

        Operand firstOperand = new Operand(OperandType.ACCUMULATOR, 0x00);
        Operand secondOperand = new Operand(OperandType.IMMEDIATE, 0x03);
        List<Integer> machineCodes = add.toMachineCodes(null, Arrays.asList(firstOperand, secondOperand));
        assertEquals(2, machineCodes.size());
        assertEquals(0x24, (int) machineCodes.get(0));
        assertEquals(0x03, (int) machineCodes.get(1));

        secondOperand = new Operand(OperandType.INDIRECT_REGISTER, 0x01);
        machineCodes = add.toMachineCodes(null, Arrays.asList(firstOperand, secondOperand));
        assertEquals(1, machineCodes.size());
        assertEquals(0x27, (int) machineCodes.get(0));

        secondOperand = new Operand(OperandType.DIRECT, 0x34);
        machineCodes = add.toMachineCodes(null, Arrays.asList(firstOperand, secondOperand));
        assertEquals(2, machineCodes.size());
        assertEquals(0x25, (int) machineCodes.get(0));
        assertEquals(0x34, (int) machineCodes.get(1));

        secondOperand = new Operand(OperandType.REGISTER, 0x07);
        machineCodes = add.toMachineCodes(null, Arrays.asList(firstOperand, secondOperand));
        assertEquals(1, machineCodes.size());
        assertEquals(0x2f, (int) machineCodes.get(0));
    }

    @Test
    public void jnb() {
        Instruction jnb = InstructionFactory.getByMnemonic("JNB");

        Operand firstOperand = new Operand(OperandType.BIT, 0x44);
        Operand osecondOperand = new Operand(OperandType.SYMBOL, 0x10);
        List<Integer> machineCodes = jnb.toMachineCodes(null, Arrays.asList(firstOperand, osecondOperand));
        assertEquals(3, machineCodes.size());
        assertEquals(0x30, (int) machineCodes.get(0));
        assertEquals(0x44, (int) machineCodes.get(1));
        assertEquals(0x10, (int) machineCodes.get(2));
    }

    @Test
    public void reti() {
        Instruction reti = InstructionFactory.getByMnemonic("RETI");

        List<Integer> machineCodes = reti.toMachineCodes(null, null);
        assertEquals(1, machineCodes.size());
        assertEquals(0x32, (int) machineCodes.get(0));
    }

    @Test
    public void rlc() {
        Instruction rlc = InstructionFactory.getByMnemonic("RLC");

        List<Integer> machineCodes = rlc.toMachineCodes(null, null);
        assertEquals(1, machineCodes.size());
        assertEquals(0x33, (int) machineCodes.get(0));
    }

    @Test
    public void addc() {
        Instruction addc = InstructionFactory.getByMnemonic("ADDC");

        Operand firstOperand = new Operand(OperandType.ACCUMULATOR, 0x00);
        Operand secondOperand = new Operand(OperandType.IMMEDIATE, 0x03);
        List<Integer> machineCodes = addc.toMachineCodes(null, Arrays.asList(firstOperand, secondOperand));
        assertEquals(2, machineCodes.size());
        assertEquals(0x34, (int) machineCodes.get(0));
        assertEquals(0x03, (int) machineCodes.get(1));

        secondOperand = new Operand(OperandType.INDIRECT_REGISTER, 0x01);
        machineCodes = addc.toMachineCodes(null, Arrays.asList(firstOperand, secondOperand));
        assertEquals(1, machineCodes.size());
        assertEquals(0x37, (int) machineCodes.get(0));

        secondOperand = new Operand(OperandType.DIRECT, 0x34);
        machineCodes = addc.toMachineCodes(null, Arrays.asList(firstOperand, secondOperand));
        assertEquals(2, machineCodes.size());
        assertEquals(0x35, (int) machineCodes.get(0));
        assertEquals(0x34, (int) machineCodes.get(1));

        secondOperand = new Operand(OperandType.REGISTER, 0x07);
        machineCodes = addc.toMachineCodes(null, Arrays.asList(firstOperand, secondOperand));
        assertEquals(1, machineCodes.size());
        assertEquals(0x3f, (int) machineCodes.get(0));
    }

    @Test
    public void jc() {
        Instruction jc = InstructionFactory.getByMnemonic("JC");

        Operand operand = new Operand(OperandType.SYMBOL, 0x10);
        List<Integer> machineCodes = jc.toMachineCodes(null, Arrays.asList(operand));
        assertEquals(2, machineCodes.size());
        assertEquals(0x40, (int) machineCodes.get(0));
        assertEquals(0x10, (int) machineCodes.get(1));
    }

    @Test
    public void orl() {
        Instruction orl = InstructionFactory.getByMnemonic("ORL");

        Operand firstOperand = new Operand(OperandType.ACCUMULATOR, 0x00);
        Operand secondOperand = new Operand(OperandType.IMMEDIATE, 0x03);
        List<Integer> machineCodes = orl.toMachineCodes(null, Arrays.asList(firstOperand, secondOperand));
        assertEquals(2, machineCodes.size());
        assertEquals(0x44, (int) machineCodes.get(0));
        assertEquals(0x03, (int) machineCodes.get(1));

        secondOperand = new Operand(OperandType.INDIRECT_REGISTER, 0x01);
        machineCodes = orl.toMachineCodes(null, Arrays.asList(firstOperand, secondOperand));
        assertEquals(1, machineCodes.size());
        assertEquals(0x47, (int) machineCodes.get(0));

        secondOperand = new Operand(OperandType.DIRECT, 0x34);
        machineCodes = orl.toMachineCodes(null, Arrays.asList(firstOperand, secondOperand));
        assertEquals(2, machineCodes.size());
        assertEquals(0x45, (int) machineCodes.get(0));
        assertEquals(0x34, (int) machineCodes.get(1));

        secondOperand = new Operand(OperandType.REGISTER, 0x07);
        machineCodes = orl.toMachineCodes(null, Arrays.asList(firstOperand, secondOperand));
        assertEquals(1, machineCodes.size());
        assertEquals(0x4f, (int) machineCodes.get(0));


        firstOperand = new Operand(OperandType.CARRY, 0x00);
        secondOperand = new Operand(OperandType.NOT_BIT, 0x22);
        machineCodes = orl.toMachineCodes(null, Arrays.asList(firstOperand, secondOperand));
        assertEquals(2, machineCodes.size());
        assertEquals(0xa0, (int) machineCodes.get(0));
        assertEquals(0x22, (int) machineCodes.get(1));

        secondOperand = new Operand(OperandType.BIT, 0x22);
        machineCodes = orl.toMachineCodes(null, Arrays.asList(firstOperand, secondOperand));
        assertEquals(2, machineCodes.size());
        assertEquals(0x72, (int) machineCodes.get(0));
        assertEquals(0x22, (int) machineCodes.get(1));


        firstOperand = new Operand(OperandType.DIRECT, 0xe0);
        secondOperand = new Operand(OperandType.IMMEDIATE, 0x11);
        machineCodes = orl.toMachineCodes(null, Arrays.asList(firstOperand, secondOperand));
        assertEquals(3, machineCodes.size());
        assertEquals(0x43, (int) machineCodes.get(0));
        assertEquals(0xe0, (int) machineCodes.get(1));
        assertEquals(0x11, (int) machineCodes.get(2));

        secondOperand = new Operand(OperandType.ACCUMULATOR, 0x00);
        machineCodes = orl.toMachineCodes(null, Arrays.asList(firstOperand, secondOperand));
        assertEquals(2, machineCodes.size());
        assertEquals(0x42, (int) machineCodes.get(0));
        assertEquals(0xe0, (int) machineCodes.get(1));
    }

    @Test
    public void jnc() {
        Instruction jnc = InstructionFactory.getByMnemonic("JNC");

        Operand operand = new Operand(OperandType.SYMBOL, 0x10);
        List<Integer> machineCodes = jnc.toMachineCodes(null, Arrays.asList(operand));
        assertEquals(2, machineCodes.size());
        assertEquals(0x50, (int) machineCodes.get(0));
        assertEquals(0x10, (int) machineCodes.get(1));
    }

    @Test
    public void anl() {
        Instruction anl = InstructionFactory.getByMnemonic("ANL");

        Operand firstOperand = new Operand(OperandType.ACCUMULATOR, 0x00);
        Operand secondOperand = new Operand(OperandType.IMMEDIATE, 0x03);
        List<Integer> machineCodes = anl.toMachineCodes(null, Arrays.asList(firstOperand, secondOperand));
        assertEquals(2, machineCodes.size());
        assertEquals(0x54, (int) machineCodes.get(0));
        assertEquals(0x03, (int) machineCodes.get(1));

        secondOperand = new Operand(OperandType.INDIRECT_REGISTER, 0x01);
        machineCodes = anl.toMachineCodes(null, Arrays.asList(firstOperand, secondOperand));
        assertEquals(1, machineCodes.size());
        assertEquals(0x57, (int) machineCodes.get(0));

        secondOperand = new Operand(OperandType.DIRECT, 0x34);
        machineCodes = anl.toMachineCodes(null, Arrays.asList(firstOperand, secondOperand));
        assertEquals(2, machineCodes.size());
        assertEquals(0x55, (int) machineCodes.get(0));
        assertEquals(0x34, (int) machineCodes.get(1));

        secondOperand = new Operand(OperandType.REGISTER, 0x07);
        machineCodes = anl.toMachineCodes(null, Arrays.asList(firstOperand, secondOperand));
        assertEquals(1, machineCodes.size());
        assertEquals(0x5f, (int) machineCodes.get(0));


        firstOperand = new Operand(OperandType.CARRY, 0x00);
        secondOperand = new Operand(OperandType.NOT_BIT, 0x22);
        machineCodes = anl.toMachineCodes(null, Arrays.asList(firstOperand, secondOperand));
        assertEquals(2, machineCodes.size());
        assertEquals(0xb0, (int) machineCodes.get(0));
        assertEquals(0x22, (int) machineCodes.get(1));

        secondOperand = new Operand(OperandType.BIT, 0x22);
        machineCodes = anl.toMachineCodes(null, Arrays.asList(firstOperand, secondOperand));
        assertEquals(2, machineCodes.size());
        assertEquals(0x82, (int) machineCodes.get(0));
        assertEquals(0x22, (int) machineCodes.get(1));


        firstOperand = new Operand(OperandType.DIRECT, 0xe0);
        secondOperand = new Operand(OperandType.IMMEDIATE, 0x11);
        machineCodes = anl.toMachineCodes(null, Arrays.asList(firstOperand, secondOperand));
        assertEquals(3, machineCodes.size());
        assertEquals(0x53, (int) machineCodes.get(0));
        assertEquals(0xe0, (int) machineCodes.get(1));
        assertEquals(0x11, (int) machineCodes.get(2));

        secondOperand = new Operand(OperandType.ACCUMULATOR, 0x00);
        machineCodes = anl.toMachineCodes(null, Arrays.asList(firstOperand, secondOperand));
        assertEquals(2, machineCodes.size());
        assertEquals(0x52, (int) machineCodes.get(0));
        assertEquals(0xe0, (int) machineCodes.get(1));
    }

    @Test
    public void jz() {
        Instruction jz = InstructionFactory.getByMnemonic("JZ");

        Operand operand = new Operand(OperandType.SYMBOL, 0x10);
        List<Integer> machineCodes = jz.toMachineCodes(null, Arrays.asList(operand));
        assertEquals(2, machineCodes.size());
        assertEquals(0x60, (int) machineCodes.get(0));
        assertEquals(0x10, (int) machineCodes.get(1));
    }

    @Test
    public void xrl() {
        Instruction xrl = InstructionFactory.getByMnemonic("XRL");

        Operand firstOperand = new Operand(OperandType.ACCUMULATOR, 0x00);
        Operand secondOperand = new Operand(OperandType.IMMEDIATE, 0x03);
        List<Integer> machineCodes = xrl.toMachineCodes(null, Arrays.asList(firstOperand, secondOperand));
        assertEquals(2, machineCodes.size());
        assertEquals(0x64, (int) machineCodes.get(0));
        assertEquals(0x03, (int) machineCodes.get(1));

        secondOperand = new Operand(OperandType.INDIRECT_REGISTER, 0x01);
        machineCodes = xrl.toMachineCodes(null, Arrays.asList(firstOperand, secondOperand));
        assertEquals(1, machineCodes.size());
        assertEquals(0x67, (int) machineCodes.get(0));

        secondOperand = new Operand(OperandType.DIRECT, 0x34);
        machineCodes = xrl.toMachineCodes(null, Arrays.asList(firstOperand, secondOperand));
        assertEquals(2, machineCodes.size());
        assertEquals(0x65, (int) machineCodes.get(0));
        assertEquals(0x34, (int) machineCodes.get(1));

        secondOperand = new Operand(OperandType.REGISTER, 0x07);
        machineCodes = xrl.toMachineCodes(null, Arrays.asList(firstOperand, secondOperand));
        assertEquals(1, machineCodes.size());
        assertEquals(0x6f, (int) machineCodes.get(0));


        firstOperand = new Operand(OperandType.DIRECT, 0xe0);
        secondOperand = new Operand(OperandType.IMMEDIATE, 0x11);
        machineCodes = xrl.toMachineCodes(null, Arrays.asList(firstOperand, secondOperand));
        assertEquals(3, machineCodes.size());
        assertEquals(0x63, (int) machineCodes.get(0));
        assertEquals(0xe0, (int) machineCodes.get(1));
        assertEquals(0x11, (int) machineCodes.get(2));

        secondOperand = new Operand(OperandType.ACCUMULATOR, 0x00);
        machineCodes = xrl.toMachineCodes(null, Arrays.asList(firstOperand, secondOperand));
        assertEquals(2, machineCodes.size());
        assertEquals(0x62, (int) machineCodes.get(0));
        assertEquals(0xe0, (int) machineCodes.get(1));
    }

    @Test
    public void jnz() {
        Instruction jnz = InstructionFactory.getByMnemonic("JNZ");

        Operand operand = new Operand(OperandType.SYMBOL, 0x10);
        List<Integer> machineCodes = jnz.toMachineCodes(null, Arrays.asList(operand));
        assertEquals(2, machineCodes.size());
        assertEquals(0x70, (int) machineCodes.get(0));
        assertEquals(0x10, (int) machineCodes.get(1));
    }

    @Test
    public void jmp() {
        Instruction jmp = InstructionFactory.getByMnemonic("JMP");

        List<Integer> machineCodes = jmp.toMachineCodes(null, null);
        assertEquals(1, machineCodes.size());
        assertEquals(0x73, (int) machineCodes.get(0));
    }

    @Test
    public void mov() {
        Instruction mov = InstructionFactory.getByMnemonic("MOV");

        Operand firstOperand = new Operand(OperandType.INDIRECT_REGISTER, 0x00);
        Operand secondOperand = new Operand(OperandType.IMMEDIATE, 0x03);
        List<Integer> machineCodes = mov.toMachineCodes(null, Arrays.asList(firstOperand, secondOperand));
        assertEquals(2, machineCodes.size());
        assertEquals(0x76, (int) machineCodes.get(0));
        assertEquals(0x03, (int) machineCodes.get(1));

        secondOperand = new Operand(OperandType.ACCUMULATOR, 0x00);
        machineCodes = mov.toMachineCodes(null, Arrays.asList(firstOperand, secondOperand));
        assertEquals(1, machineCodes.size());
        assertEquals(0xf6, (int) machineCodes.get(0));

        secondOperand = new Operand(OperandType.DIRECT, 0x34);
        machineCodes = mov.toMachineCodes(null, Arrays.asList(firstOperand, secondOperand));
        assertEquals(2, machineCodes.size());
        assertEquals(0xa6, (int) machineCodes.get(0));
        assertEquals(0x34, (int) machineCodes.get(1));


        firstOperand = new Operand(OperandType.ACCUMULATOR, 0x00);
        secondOperand = new Operand(OperandType.IMMEDIATE, 0x03);
        machineCodes = mov.toMachineCodes(null, Arrays.asList(firstOperand, secondOperand));
        assertEquals(2, machineCodes.size());
        assertEquals(0x74, (int) machineCodes.get(0));
        assertEquals(0x03, (int) machineCodes.get(1));

        secondOperand = new Operand(OperandType.INDIRECT_REGISTER, 0x00);
        machineCodes = mov.toMachineCodes(null, Arrays.asList(firstOperand, secondOperand));
        assertEquals(1, machineCodes.size());
        assertEquals(0xe6, (int) machineCodes.get(0));

        secondOperand = new Operand(OperandType.DIRECT, 0x34);
        machineCodes = mov.toMachineCodes(null, Arrays.asList(firstOperand, secondOperand));
        assertEquals(2, machineCodes.size());
        assertEquals(0xe5, (int) machineCodes.get(0));
        assertEquals(0x34, (int) machineCodes.get(1));

        secondOperand = new Operand(OperandType.REGISTER, 0x02);
        machineCodes = mov.toMachineCodes(null, Arrays.asList(firstOperand, secondOperand));
        assertEquals(1, machineCodes.size());
        assertEquals(0xea, (int) machineCodes.get(0));


        firstOperand = new Operand(OperandType.BIT, 0x22);
        secondOperand = new Operand(OperandType.CARRY, 0x00);
        machineCodes = mov.toMachineCodes(null, Arrays.asList(firstOperand, secondOperand));
        assertEquals(2, machineCodes.size());
        assertEquals(0x92, (int) machineCodes.get(0));
        assertEquals(0x22, (int) machineCodes.get(1));


        firstOperand = new Operand(OperandType.CARRY, 0x00);
        secondOperand = new Operand(OperandType.BIT, 0x22);
        machineCodes = mov.toMachineCodes(null, Arrays.asList(firstOperand, secondOperand));
        assertEquals(2, machineCodes.size());
        assertEquals(0xa2, (int) machineCodes.get(0));
        assertEquals(0x22, (int) machineCodes.get(1));


        firstOperand = new Operand(OperandType.DIRECT, 0xe0);
        secondOperand = new Operand(OperandType.DIRECT, 0x00);
        machineCodes = mov.toMachineCodes(null, Arrays.asList(firstOperand, secondOperand));
        assertEquals(3, machineCodes.size());
        assertEquals(0x85, (int) machineCodes.get(0));
        assertEquals(0x00, (int) machineCodes.get(1));
        assertEquals(0xe0, (int) machineCodes.get(2));

        secondOperand = new Operand(OperandType.IMMEDIATE, 0x11);
        machineCodes = mov.toMachineCodes(null, Arrays.asList(firstOperand, secondOperand));
        assertEquals(3, machineCodes.size());
        assertEquals(0x75, (int) machineCodes.get(0));
        assertEquals(0xe0, (int) machineCodes.get(1));
        assertEquals(0x11, (int) machineCodes.get(2));

        secondOperand = new Operand(OperandType.INDIRECT_REGISTER, 0x00);
        machineCodes = mov.toMachineCodes(null, Arrays.asList(firstOperand, secondOperand));
        assertEquals(2, machineCodes.size());
        assertEquals(0x86, (int) machineCodes.get(0));
        assertEquals(0xe0, (int) machineCodes.get(1));

        secondOperand = new Operand(OperandType.ACCUMULATOR, 0x00);
        machineCodes = mov.toMachineCodes(null, Arrays.asList(firstOperand, secondOperand));
        assertEquals(2, machineCodes.size());
        assertEquals(0xf5, (int) machineCodes.get(0));
        assertEquals(0xe0, (int) machineCodes.get(1));

        secondOperand = new Operand(OperandType.REGISTER, 0x00);
        machineCodes = mov.toMachineCodes(null, Arrays.asList(firstOperand, secondOperand));
        assertEquals(2, machineCodes.size());
        assertEquals(0x88, (int) machineCodes.get(0));
        assertEquals(0xe0, (int) machineCodes.get(1));


        firstOperand = new Operand(OperandType.DPTR, 0x00);
        secondOperand = new Operand(OperandType.IMMEDIATE, 0x0103);
        machineCodes = mov.toMachineCodes(null, Arrays.asList(firstOperand, secondOperand));
        assertEquals(3, machineCodes.size());
        assertEquals(0x90, (int) machineCodes.get(0));
        assertEquals(0x01, (int) machineCodes.get(1));
        assertEquals(0x03, (int) machineCodes.get(2));


        firstOperand = new Operand(OperandType.REGISTER, 0x00);
        secondOperand = new Operand(OperandType.IMMEDIATE, 0x03);
        machineCodes = mov.toMachineCodes(null, Arrays.asList(firstOperand, secondOperand));
        assertEquals(2, machineCodes.size());
        assertEquals(0x78, (int) machineCodes.get(0));
        assertEquals(0x03, (int) machineCodes.get(1));

        secondOperand = new Operand(OperandType.ACCUMULATOR, 0x00);
        machineCodes = mov.toMachineCodes(null, Arrays.asList(firstOperand, secondOperand));
        assertEquals(1, machineCodes.size());
        assertEquals(0xf8, (int) machineCodes.get(0));

        secondOperand = new Operand(OperandType.DIRECT, 0x34);
        machineCodes = mov.toMachineCodes(null, Arrays.asList(firstOperand, secondOperand));
        assertEquals(2, machineCodes.size());
        assertEquals(0xa8, (int) machineCodes.get(0));
        assertEquals(0x34, (int) machineCodes.get(1));
    }

    @Test
    public void sjmp() {
        Instruction sjmp = InstructionFactory.getByMnemonic("SJMP");

        Operand operand = new Operand(OperandType.SYMBOL, 0x10);
        List<Integer> machineCodes = sjmp.toMachineCodes(null, Arrays.asList(operand));
        assertEquals(2, machineCodes.size());
        assertEquals(0x80, (int) machineCodes.get(0));
        assertEquals(0x10, (int) machineCodes.get(1));
    }

    @Test
    public void movc() {
        Instruction movc = InstructionFactory.getByMnemonic("MOVC");

        Operand firstOperand = new Operand(OperandType.ACCUMULATOR, 0x00);
        Operand secondOperand = new Operand(OperandType.AT_A_PLUS_DPTR, 0x00);
        List<Integer> machineCodes = movc.toMachineCodes(null, Arrays.asList(firstOperand, secondOperand));
        assertEquals(1, machineCodes.size());
        assertEquals(0x93, (int) machineCodes.get(0));

        secondOperand = new Operand(OperandType.AT_A_PLUS_PC, 0x00);
        machineCodes = movc.toMachineCodes(null, Arrays.asList(firstOperand, secondOperand));
        assertEquals(1, machineCodes.size());
        assertEquals(0x83, (int) machineCodes.get(0));
    }

    @Test
    public void div() {
        Instruction div = InstructionFactory.getByMnemonic("DIV");

        List<Integer> machineCodes = div.toMachineCodes(null, null);
        assertEquals(1, machineCodes.size());
        assertEquals(0x84, (int) machineCodes.get(0));
    }

    @Test
    public void subb() {
        Instruction subb = InstructionFactory.getByMnemonic("SUBB");

        Operand firstOperand = new Operand(OperandType.ACCUMULATOR, 0x00);
        Operand secondOperand = new Operand(OperandType.IMMEDIATE, 0x03);
        List<Integer> machineCodes = subb.toMachineCodes(null, Arrays.asList(firstOperand, secondOperand));
        assertEquals(2, machineCodes.size());
        assertEquals(0x94, (int) machineCodes.get(0));
        assertEquals(0x03, (int) machineCodes.get(1));

        secondOperand = new Operand(OperandType.INDIRECT_REGISTER, 0x01);
        machineCodes = subb.toMachineCodes(null, Arrays.asList(firstOperand, secondOperand));
        assertEquals(1, machineCodes.size());
        assertEquals(0x97, (int) machineCodes.get(0));

        secondOperand = new Operand(OperandType.DIRECT, 0x34);
        machineCodes = subb.toMachineCodes(null, Arrays.asList(firstOperand, secondOperand));
        assertEquals(2, machineCodes.size());
        assertEquals(0x95, (int) machineCodes.get(0));
        assertEquals(0x34, (int) machineCodes.get(1));

        secondOperand = new Operand(OperandType.REGISTER, 0x07);
        machineCodes = subb.toMachineCodes(null, Arrays.asList(firstOperand, secondOperand));
        assertEquals(1, machineCodes.size());
        assertEquals(0x9f, (int) machineCodes.get(0));
    }

    @Test
    public void mul() {
        Instruction mul = InstructionFactory.getByMnemonic("MUL");

        List<Integer> machineCodes = mul.toMachineCodes(null, null);
        assertEquals(1, machineCodes.size());
        assertEquals(0xa4, (int) machineCodes.get(0));
    }

    @Test
    public void cpl() {
        Instruction cpl = InstructionFactory.getByMnemonic("CPL");

        Operand firstOperand = new Operand(OperandType.ACCUMULATOR, 0x00);
        List<Integer> machineCodes = cpl.toMachineCodes(null, Arrays.asList(firstOperand));
        assertEquals(1, machineCodes.size());
        assertEquals(0xf4, (int) machineCodes.get(0));

        firstOperand = new Operand(OperandType.BIT, 0x01);
        machineCodes = cpl.toMachineCodes(null, Arrays.asList(firstOperand));
        assertEquals(2, machineCodes.size());
        assertEquals(0xb2, (int) machineCodes.get(0));
        assertEquals(0x01, (int) machineCodes.get(1));

        firstOperand = new Operand(OperandType.CARRY, 0x00);
        machineCodes = cpl.toMachineCodes(null, Arrays.asList(firstOperand));
        assertEquals(1, machineCodes.size());
        assertEquals(0xb3, (int) machineCodes.get(0));
    }

    @Test
    public void cjne() {
        Instruction cjne = InstructionFactory.getByMnemonic("CJNE");

        Operand firstOperand = new Operand(OperandType.INDIRECT_REGISTER, 0x01);
        Operand secondOperand = new Operand(OperandType.IMMEDIATE, 0x24);
        Operand thirdOperand = new Operand(OperandType.SYMBOL, 0xff);
        List<Integer> machineCodes = cjne.toMachineCodes(null, Arrays.asList(firstOperand, secondOperand, thirdOperand));
        assertEquals(3, machineCodes.size());
        assertEquals(0xb7, (int) machineCodes.get(0));
        assertEquals(0x24, (int) machineCodes.get(1));
        assertEquals(0xff, (int) machineCodes.get(2));


        firstOperand = new Operand(OperandType.ACCUMULATOR, 0x00);
        secondOperand = new Operand(OperandType.IMMEDIATE, 0x24);
        thirdOperand = new Operand(OperandType.SYMBOL, 0xff);
        machineCodes = cjne.toMachineCodes(null, Arrays.asList(firstOperand, secondOperand, thirdOperand));
        assertEquals(3, machineCodes.size());
        assertEquals(0xb4, (int) machineCodes.get(0));
        assertEquals(0x24, (int) machineCodes.get(1));
        assertEquals(0xff, (int) machineCodes.get(2));

        secondOperand = new Operand(OperandType.DIRECT, 0x60);
        thirdOperand = new Operand(OperandType.SYMBOL, 0xff);
        machineCodes = cjne.toMachineCodes(null, Arrays.asList(firstOperand, secondOperand, thirdOperand));
        assertEquals(3, machineCodes.size());
        assertEquals(0xb5, (int) machineCodes.get(0));
        assertEquals(0x60, (int) machineCodes.get(1));
        assertEquals(0xff, (int) machineCodes.get(2));


        firstOperand = new Operand(OperandType.REGISTER, 0x02);
        secondOperand = new Operand(OperandType.IMMEDIATE, 0x24);
        thirdOperand = new Operand(OperandType.SYMBOL, 0xff);
        machineCodes = cjne.toMachineCodes(null, Arrays.asList(firstOperand, secondOperand, thirdOperand));
        assertEquals(3, machineCodes.size());
        assertEquals(0xba, (int) machineCodes.get(0));
        assertEquals(0x24, (int) machineCodes.get(1));
        assertEquals(0xff, (int) machineCodes.get(2));
    }

    @Test
    public void push() {
        Instruction push = InstructionFactory.getByMnemonic("PUSH");

        Operand operand = new Operand(OperandType.DIRECT, 0xe0);
        List<Integer> machineCodes = push.toMachineCodes(null, Arrays.asList(operand));
        assertEquals(2, machineCodes.size());
        assertEquals(0xc0, (int) machineCodes.get(0));
        assertEquals(0xe0, (int) machineCodes.get(1));
    }

    @Test
    public void clr() {
        Instruction clr = InstructionFactory.getByMnemonic("CLR");

        Operand firstOperand = new Operand(OperandType.ACCUMULATOR, 0x00);
        List<Integer> machineCodes = clr.toMachineCodes(null, Arrays.asList(firstOperand));
        assertEquals(1, machineCodes.size());
        assertEquals(0xe4, (int) machineCodes.get(0));

        firstOperand = new Operand(OperandType.BIT, 0x01);
        machineCodes = clr.toMachineCodes(null, Arrays.asList(firstOperand));
        assertEquals(2, machineCodes.size());
        assertEquals(0xc2, (int) machineCodes.get(0));
        assertEquals(0x01, (int) machineCodes.get(1));

        firstOperand = new Operand(OperandType.CARRY, 0x00);
        machineCodes = clr.toMachineCodes(null, Arrays.asList(firstOperand));
        assertEquals(1, machineCodes.size());
        assertEquals(0xc3, (int) machineCodes.get(0));
    }

    @Test
    public void swap() {
        Instruction swap = InstructionFactory.getByMnemonic("SWAP");

        List<Integer> machineCodes = swap.toMachineCodes(null, null);
        assertEquals(1, machineCodes.size());
        assertEquals(0xc4, (int) machineCodes.get(0));
    }

    @Test
    public void xch() {
        Instruction xch = InstructionFactory.getByMnemonic("XCH");

        Operand firstOperand = new Operand(OperandType.ACCUMULATOR, 0x00);
        Operand secondOperand = new Operand(OperandType.INDIRECT_REGISTER, 0x01);
        List<Integer> machineCodes = xch.toMachineCodes(null, Arrays.asList(firstOperand, secondOperand));
        assertEquals(1, machineCodes.size());
        assertEquals(0xc7, (int) machineCodes.get(0));

        secondOperand = new Operand(OperandType.DIRECT, 0x34);
        machineCodes = xch.toMachineCodes(null, Arrays.asList(firstOperand, secondOperand));
        assertEquals(2, machineCodes.size());
        assertEquals(0xc5, (int) machineCodes.get(0));
        assertEquals(0x34, (int) machineCodes.get(1));

        secondOperand = new Operand(OperandType.REGISTER, 0x07);
        machineCodes = xch.toMachineCodes(null, Arrays.asList(firstOperand, secondOperand));
        assertEquals(1, machineCodes.size());
        assertEquals(0xcf, (int) machineCodes.get(0));
    }

    @Test
    public void pop() {
        Instruction pop = InstructionFactory.getByMnemonic("POP");

        Operand operand = new Operand(OperandType.DIRECT, 0xe0);
        List<Integer> machineCodes = pop.toMachineCodes(null, Arrays.asList(operand));
        assertEquals(2, machineCodes.size());
        assertEquals(0xd0, (int) machineCodes.get(0));
        assertEquals(0xe0, (int) machineCodes.get(1));
    }

    @Test
    public void setb() {
        Instruction setb = InstructionFactory.getByMnemonic("SETB");

        Operand firstOperand = new Operand(OperandType.BIT, 0x01);
        List<Integer> machineCodes = setb.toMachineCodes(null, Arrays.asList(firstOperand));
        assertEquals(2, machineCodes.size());
        assertEquals(0xd2, (int) machineCodes.get(0));
        assertEquals(0x01, (int) machineCodes.get(1));

        firstOperand = new Operand(OperandType.CARRY, 0x00);
        machineCodes = setb.toMachineCodes(null, Arrays.asList(firstOperand));
        assertEquals(1, machineCodes.size());
        assertEquals(0xd3, (int) machineCodes.get(0));
    }

    @Test
    public void da() {
        Instruction da = InstructionFactory.getByMnemonic("DA");

        List<Integer> machineCodes = da.toMachineCodes(null, null);
        assertEquals(1, machineCodes.size());
        assertEquals(0xd4, (int) machineCodes.get(0));
    }

    @Test
    public void djnz() {
        Instruction djnz = InstructionFactory.getByMnemonic("DJNZ");

        Operand firstOperand = new Operand(OperandType.DIRECT, 0x00);
        Operand secondOperand = new Operand(OperandType.SYMBOL, 0x34);
        List<Integer> machineCodes = djnz.toMachineCodes(0, Arrays.asList(firstOperand, secondOperand));
        assertEquals(3, machineCodes.size());
        assertEquals(0xd5, (int) machineCodes.get(0));
        assertEquals(0x00, (int) machineCodes.get(1));
        assertEquals(0x31, (int) machineCodes.get(2));

        firstOperand = new Operand(OperandType.REGISTER, 0x07);
        secondOperand = new Operand(OperandType.SYMBOL, 0x34);
        machineCodes = djnz.toMachineCodes(0, Arrays.asList(firstOperand, secondOperand));
        assertEquals(2, machineCodes.size());
        assertEquals(0xdf, (int) machineCodes.get(0));
        assertEquals(0x32, (int) machineCodes.get(1));
    }

    @Test
    public void xchd() {
        Instruction xchd = InstructionFactory.getByMnemonic("XCHD");

        Operand firstOperand = new Operand(OperandType.ACCUMULATOR, 0x00);
        Operand secondOperand = new Operand(OperandType.INDIRECT_REGISTER, 0x01);
        List<Integer> machineCodes = xchd.toMachineCodes(null, Arrays.asList(firstOperand, secondOperand));
        assertEquals(1, machineCodes.size());
        assertEquals(0xd7, (int) machineCodes.get(0));
    }

    @Test
    public void movx() {
        Instruction movx = InstructionFactory.getByMnemonic("MOVX");

        Operand firstOperand = new Operand(OperandType.INDIRECT_REGISTER, 0x01);
        Operand secondOperand = new Operand(OperandType.ACCUMULATOR, 0x00);
        List<Integer> machineCodes = movx.toMachineCodes(null, Arrays.asList(firstOperand, secondOperand));
        assertEquals(1, machineCodes.size());
        assertEquals(0xf3, (int) machineCodes.get(0));


        firstOperand = new Operand(OperandType.ACCUMULATOR, 0x00);
        secondOperand = new Operand(OperandType.AT_DPTR, 0x00);
        machineCodes = movx.toMachineCodes(null, Arrays.asList(firstOperand, secondOperand));
        assertEquals(1, machineCodes.size());
        assertEquals(0xe0, (int) machineCodes.get(0));

        secondOperand = new Operand(OperandType.INDIRECT_REGISTER, 0x01);
        machineCodes = movx.toMachineCodes(null, Arrays.asList(firstOperand, secondOperand));
        assertEquals(1, machineCodes.size());
        assertEquals(0xe3, (int) machineCodes.get(0));
    }

}