package com.opensim51.assembler.mcs51.mcu8051.antlr;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link Asm8051Parser}.
 */
public interface Asm8051Listener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link Asm8051Parser#source}.
	 * @param ctx the parse tree
	 */
	void enterSource(Asm8051Parser.SourceContext ctx);
	/**
	 * Exit a parse tree produced by {@link Asm8051Parser#source}.
	 * @param ctx the parse tree
	 */
	void exitSource(Asm8051Parser.SourceContext ctx);
	/**
	 * Enter a parse tree produced by {@link Asm8051Parser#line}.
	 * @param ctx the parse tree
	 */
	void enterLine(Asm8051Parser.LineContext ctx);
	/**
	 * Exit a parse tree produced by {@link Asm8051Parser#line}.
	 * @param ctx the parse tree
	 */
	void exitLine(Asm8051Parser.LineContext ctx);
	/**
	 * Enter a parse tree produced by {@link Asm8051Parser#directive}.
	 * @param ctx the parse tree
	 */
	void enterDirective(Asm8051Parser.DirectiveContext ctx);
	/**
	 * Exit a parse tree produced by {@link Asm8051Parser#directive}.
	 * @param ctx the parse tree
	 */
	void exitDirective(Asm8051Parser.DirectiveContext ctx);
	/**
	 * Enter a parse tree produced by {@link Asm8051Parser#org}.
	 * @param ctx the parse tree
	 */
	void enterOrg(Asm8051Parser.OrgContext ctx);
	/**
	 * Exit a parse tree produced by {@link Asm8051Parser#org}.
	 * @param ctx the parse tree
	 */
	void exitOrg(Asm8051Parser.OrgContext ctx);
	/**
	 * Enter a parse tree produced by {@link Asm8051Parser#equ}.
	 * @param ctx the parse tree
	 */
	void enterEqu(Asm8051Parser.EquContext ctx);
	/**
	 * Exit a parse tree produced by {@link Asm8051Parser#equ}.
	 * @param ctx the parse tree
	 */
	void exitEqu(Asm8051Parser.EquContext ctx);
	/**
	 * Enter a parse tree produced by {@link Asm8051Parser#set}.
	 * @param ctx the parse tree
	 */
	void enterSet(Asm8051Parser.SetContext ctx);
	/**
	 * Exit a parse tree produced by {@link Asm8051Parser#set}.
	 * @param ctx the parse tree
	 */
	void exitSet(Asm8051Parser.SetContext ctx);
	/**
	 * Enter a parse tree produced by {@link Asm8051Parser#using}.
	 * @param ctx the parse tree
	 */
	void enterUsing(Asm8051Parser.UsingContext ctx);
	/**
	 * Exit a parse tree produced by {@link Asm8051Parser#using}.
	 * @param ctx the parse tree
	 */
	void exitUsing(Asm8051Parser.UsingContext ctx);
	/**
	 * Enter a parse tree produced by {@link Asm8051Parser#ds}.
	 * @param ctx the parse tree
	 */
	void enterDs(Asm8051Parser.DsContext ctx);
	/**
	 * Exit a parse tree produced by {@link Asm8051Parser#ds}.
	 * @param ctx the parse tree
	 */
	void exitDs(Asm8051Parser.DsContext ctx);
	/**
	 * Enter a parse tree produced by {@link Asm8051Parser#db}.
	 * @param ctx the parse tree
	 */
	void enterDb(Asm8051Parser.DbContext ctx);
	/**
	 * Exit a parse tree produced by {@link Asm8051Parser#db}.
	 * @param ctx the parse tree
	 */
	void exitDb(Asm8051Parser.DbContext ctx);
	/**
	 * Enter a parse tree produced by {@link Asm8051Parser#dw}.
	 * @param ctx the parse tree
	 */
	void enterDw(Asm8051Parser.DwContext ctx);
	/**
	 * Exit a parse tree produced by {@link Asm8051Parser#dw}.
	 * @param ctx the parse tree
	 */
	void exitDw(Asm8051Parser.DwContext ctx);
	/**
	 * Enter a parse tree produced by {@link Asm8051Parser#end}.
	 * @param ctx the parse tree
	 */
	void enterEnd(Asm8051Parser.EndContext ctx);
	/**
	 * Exit a parse tree produced by {@link Asm8051Parser#end}.
	 * @param ctx the parse tree
	 */
	void exitEnd(Asm8051Parser.EndContext ctx);
	/**
	 * Enter a parse tree produced by {@link Asm8051Parser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(Asm8051Parser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link Asm8051Parser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(Asm8051Parser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link Asm8051Parser#character}.
	 * @param ctx the parse tree
	 */
	void enterCharacter(Asm8051Parser.CharacterContext ctx);
	/**
	 * Exit a parse tree produced by {@link Asm8051Parser#character}.
	 * @param ctx the parse tree
	 */
	void exitCharacter(Asm8051Parser.CharacterContext ctx);
	/**
	 * Enter a parse tree produced by {@link Asm8051Parser#dollar}.
	 * @param ctx the parse tree
	 */
	void enterDollar(Asm8051Parser.DollarContext ctx);
	/**
	 * Exit a parse tree produced by {@link Asm8051Parser#dollar}.
	 * @param ctx the parse tree
	 */
	void exitDollar(Asm8051Parser.DollarContext ctx);
	/**
	 * Enter a parse tree produced by {@link Asm8051Parser#instruction}.
	 * @param ctx the parse tree
	 */
	void enterInstruction(Asm8051Parser.InstructionContext ctx);
	/**
	 * Exit a parse tree produced by {@link Asm8051Parser#instruction}.
	 * @param ctx the parse tree
	 */
	void exitInstruction(Asm8051Parser.InstructionContext ctx);
	/**
	 * Enter a parse tree produced by {@link Asm8051Parser#mnemonic}.
	 * @param ctx the parse tree
	 */
	void enterMnemonic(Asm8051Parser.MnemonicContext ctx);
	/**
	 * Exit a parse tree produced by {@link Asm8051Parser#mnemonic}.
	 * @param ctx the parse tree
	 */
	void exitMnemonic(Asm8051Parser.MnemonicContext ctx);
	/**
	 * Enter a parse tree produced by {@link Asm8051Parser#acall}.
	 * @param ctx the parse tree
	 */
	void enterAcall(Asm8051Parser.AcallContext ctx);
	/**
	 * Exit a parse tree produced by {@link Asm8051Parser#acall}.
	 * @param ctx the parse tree
	 */
	void exitAcall(Asm8051Parser.AcallContext ctx);
	/**
	 * Enter a parse tree produced by {@link Asm8051Parser#add}.
	 * @param ctx the parse tree
	 */
	void enterAdd(Asm8051Parser.AddContext ctx);
	/**
	 * Exit a parse tree produced by {@link Asm8051Parser#add}.
	 * @param ctx the parse tree
	 */
	void exitAdd(Asm8051Parser.AddContext ctx);
	/**
	 * Enter a parse tree produced by {@link Asm8051Parser#addc}.
	 * @param ctx the parse tree
	 */
	void enterAddc(Asm8051Parser.AddcContext ctx);
	/**
	 * Exit a parse tree produced by {@link Asm8051Parser#addc}.
	 * @param ctx the parse tree
	 */
	void exitAddc(Asm8051Parser.AddcContext ctx);
	/**
	 * Enter a parse tree produced by {@link Asm8051Parser#ajmp}.
	 * @param ctx the parse tree
	 */
	void enterAjmp(Asm8051Parser.AjmpContext ctx);
	/**
	 * Exit a parse tree produced by {@link Asm8051Parser#ajmp}.
	 * @param ctx the parse tree
	 */
	void exitAjmp(Asm8051Parser.AjmpContext ctx);
	/**
	 * Enter a parse tree produced by {@link Asm8051Parser#anl}.
	 * @param ctx the parse tree
	 */
	void enterAnl(Asm8051Parser.AnlContext ctx);
	/**
	 * Exit a parse tree produced by {@link Asm8051Parser#anl}.
	 * @param ctx the parse tree
	 */
	void exitAnl(Asm8051Parser.AnlContext ctx);
	/**
	 * Enter a parse tree produced by {@link Asm8051Parser#cjne}.
	 * @param ctx the parse tree
	 */
	void enterCjne(Asm8051Parser.CjneContext ctx);
	/**
	 * Exit a parse tree produced by {@link Asm8051Parser#cjne}.
	 * @param ctx the parse tree
	 */
	void exitCjne(Asm8051Parser.CjneContext ctx);
	/**
	 * Enter a parse tree produced by {@link Asm8051Parser#clr}.
	 * @param ctx the parse tree
	 */
	void enterClr(Asm8051Parser.ClrContext ctx);
	/**
	 * Exit a parse tree produced by {@link Asm8051Parser#clr}.
	 * @param ctx the parse tree
	 */
	void exitClr(Asm8051Parser.ClrContext ctx);
	/**
	 * Enter a parse tree produced by {@link Asm8051Parser#cpl}.
	 * @param ctx the parse tree
	 */
	void enterCpl(Asm8051Parser.CplContext ctx);
	/**
	 * Exit a parse tree produced by {@link Asm8051Parser#cpl}.
	 * @param ctx the parse tree
	 */
	void exitCpl(Asm8051Parser.CplContext ctx);
	/**
	 * Enter a parse tree produced by {@link Asm8051Parser#da}.
	 * @param ctx the parse tree
	 */
	void enterDa(Asm8051Parser.DaContext ctx);
	/**
	 * Exit a parse tree produced by {@link Asm8051Parser#da}.
	 * @param ctx the parse tree
	 */
	void exitDa(Asm8051Parser.DaContext ctx);
	/**
	 * Enter a parse tree produced by {@link Asm8051Parser#dec}.
	 * @param ctx the parse tree
	 */
	void enterDec(Asm8051Parser.DecContext ctx);
	/**
	 * Exit a parse tree produced by {@link Asm8051Parser#dec}.
	 * @param ctx the parse tree
	 */
	void exitDec(Asm8051Parser.DecContext ctx);
	/**
	 * Enter a parse tree produced by {@link Asm8051Parser#div}.
	 * @param ctx the parse tree
	 */
	void enterDiv(Asm8051Parser.DivContext ctx);
	/**
	 * Exit a parse tree produced by {@link Asm8051Parser#div}.
	 * @param ctx the parse tree
	 */
	void exitDiv(Asm8051Parser.DivContext ctx);
	/**
	 * Enter a parse tree produced by {@link Asm8051Parser#djnz}.
	 * @param ctx the parse tree
	 */
	void enterDjnz(Asm8051Parser.DjnzContext ctx);
	/**
	 * Exit a parse tree produced by {@link Asm8051Parser#djnz}.
	 * @param ctx the parse tree
	 */
	void exitDjnz(Asm8051Parser.DjnzContext ctx);
	/**
	 * Enter a parse tree produced by {@link Asm8051Parser#inc}.
	 * @param ctx the parse tree
	 */
	void enterInc(Asm8051Parser.IncContext ctx);
	/**
	 * Exit a parse tree produced by {@link Asm8051Parser#inc}.
	 * @param ctx the parse tree
	 */
	void exitInc(Asm8051Parser.IncContext ctx);
	/**
	 * Enter a parse tree produced by {@link Asm8051Parser#jb}.
	 * @param ctx the parse tree
	 */
	void enterJb(Asm8051Parser.JbContext ctx);
	/**
	 * Exit a parse tree produced by {@link Asm8051Parser#jb}.
	 * @param ctx the parse tree
	 */
	void exitJb(Asm8051Parser.JbContext ctx);
	/**
	 * Enter a parse tree produced by {@link Asm8051Parser#jbc}.
	 * @param ctx the parse tree
	 */
	void enterJbc(Asm8051Parser.JbcContext ctx);
	/**
	 * Exit a parse tree produced by {@link Asm8051Parser#jbc}.
	 * @param ctx the parse tree
	 */
	void exitJbc(Asm8051Parser.JbcContext ctx);
	/**
	 * Enter a parse tree produced by {@link Asm8051Parser#jc}.
	 * @param ctx the parse tree
	 */
	void enterJc(Asm8051Parser.JcContext ctx);
	/**
	 * Exit a parse tree produced by {@link Asm8051Parser#jc}.
	 * @param ctx the parse tree
	 */
	void exitJc(Asm8051Parser.JcContext ctx);
	/**
	 * Enter a parse tree produced by {@link Asm8051Parser#jmp}.
	 * @param ctx the parse tree
	 */
	void enterJmp(Asm8051Parser.JmpContext ctx);
	/**
	 * Exit a parse tree produced by {@link Asm8051Parser#jmp}.
	 * @param ctx the parse tree
	 */
	void exitJmp(Asm8051Parser.JmpContext ctx);
	/**
	 * Enter a parse tree produced by {@link Asm8051Parser#jnb}.
	 * @param ctx the parse tree
	 */
	void enterJnb(Asm8051Parser.JnbContext ctx);
	/**
	 * Exit a parse tree produced by {@link Asm8051Parser#jnb}.
	 * @param ctx the parse tree
	 */
	void exitJnb(Asm8051Parser.JnbContext ctx);
	/**
	 * Enter a parse tree produced by {@link Asm8051Parser#jnc}.
	 * @param ctx the parse tree
	 */
	void enterJnc(Asm8051Parser.JncContext ctx);
	/**
	 * Exit a parse tree produced by {@link Asm8051Parser#jnc}.
	 * @param ctx the parse tree
	 */
	void exitJnc(Asm8051Parser.JncContext ctx);
	/**
	 * Enter a parse tree produced by {@link Asm8051Parser#jnz}.
	 * @param ctx the parse tree
	 */
	void enterJnz(Asm8051Parser.JnzContext ctx);
	/**
	 * Exit a parse tree produced by {@link Asm8051Parser#jnz}.
	 * @param ctx the parse tree
	 */
	void exitJnz(Asm8051Parser.JnzContext ctx);
	/**
	 * Enter a parse tree produced by {@link Asm8051Parser#jz}.
	 * @param ctx the parse tree
	 */
	void enterJz(Asm8051Parser.JzContext ctx);
	/**
	 * Exit a parse tree produced by {@link Asm8051Parser#jz}.
	 * @param ctx the parse tree
	 */
	void exitJz(Asm8051Parser.JzContext ctx);
	/**
	 * Enter a parse tree produced by {@link Asm8051Parser#lcall}.
	 * @param ctx the parse tree
	 */
	void enterLcall(Asm8051Parser.LcallContext ctx);
	/**
	 * Exit a parse tree produced by {@link Asm8051Parser#lcall}.
	 * @param ctx the parse tree
	 */
	void exitLcall(Asm8051Parser.LcallContext ctx);
	/**
	 * Enter a parse tree produced by {@link Asm8051Parser#ljmp}.
	 * @param ctx the parse tree
	 */
	void enterLjmp(Asm8051Parser.LjmpContext ctx);
	/**
	 * Exit a parse tree produced by {@link Asm8051Parser#ljmp}.
	 * @param ctx the parse tree
	 */
	void exitLjmp(Asm8051Parser.LjmpContext ctx);
	/**
	 * Enter a parse tree produced by {@link Asm8051Parser#mov}.
	 * @param ctx the parse tree
	 */
	void enterMov(Asm8051Parser.MovContext ctx);
	/**
	 * Exit a parse tree produced by {@link Asm8051Parser#mov}.
	 * @param ctx the parse tree
	 */
	void exitMov(Asm8051Parser.MovContext ctx);
	/**
	 * Enter a parse tree produced by {@link Asm8051Parser#movc}.
	 * @param ctx the parse tree
	 */
	void enterMovc(Asm8051Parser.MovcContext ctx);
	/**
	 * Exit a parse tree produced by {@link Asm8051Parser#movc}.
	 * @param ctx the parse tree
	 */
	void exitMovc(Asm8051Parser.MovcContext ctx);
	/**
	 * Enter a parse tree produced by {@link Asm8051Parser#movx}.
	 * @param ctx the parse tree
	 */
	void enterMovx(Asm8051Parser.MovxContext ctx);
	/**
	 * Exit a parse tree produced by {@link Asm8051Parser#movx}.
	 * @param ctx the parse tree
	 */
	void exitMovx(Asm8051Parser.MovxContext ctx);
	/**
	 * Enter a parse tree produced by {@link Asm8051Parser#mul}.
	 * @param ctx the parse tree
	 */
	void enterMul(Asm8051Parser.MulContext ctx);
	/**
	 * Exit a parse tree produced by {@link Asm8051Parser#mul}.
	 * @param ctx the parse tree
	 */
	void exitMul(Asm8051Parser.MulContext ctx);
	/**
	 * Enter a parse tree produced by {@link Asm8051Parser#nop}.
	 * @param ctx the parse tree
	 */
	void enterNop(Asm8051Parser.NopContext ctx);
	/**
	 * Exit a parse tree produced by {@link Asm8051Parser#nop}.
	 * @param ctx the parse tree
	 */
	void exitNop(Asm8051Parser.NopContext ctx);
	/**
	 * Enter a parse tree produced by {@link Asm8051Parser#orl}.
	 * @param ctx the parse tree
	 */
	void enterOrl(Asm8051Parser.OrlContext ctx);
	/**
	 * Exit a parse tree produced by {@link Asm8051Parser#orl}.
	 * @param ctx the parse tree
	 */
	void exitOrl(Asm8051Parser.OrlContext ctx);
	/**
	 * Enter a parse tree produced by {@link Asm8051Parser#pop}.
	 * @param ctx the parse tree
	 */
	void enterPop(Asm8051Parser.PopContext ctx);
	/**
	 * Exit a parse tree produced by {@link Asm8051Parser#pop}.
	 * @param ctx the parse tree
	 */
	void exitPop(Asm8051Parser.PopContext ctx);
	/**
	 * Enter a parse tree produced by {@link Asm8051Parser#push}.
	 * @param ctx the parse tree
	 */
	void enterPush(Asm8051Parser.PushContext ctx);
	/**
	 * Exit a parse tree produced by {@link Asm8051Parser#push}.
	 * @param ctx the parse tree
	 */
	void exitPush(Asm8051Parser.PushContext ctx);
	/**
	 * Enter a parse tree produced by {@link Asm8051Parser#ret}.
	 * @param ctx the parse tree
	 */
	void enterRet(Asm8051Parser.RetContext ctx);
	/**
	 * Exit a parse tree produced by {@link Asm8051Parser#ret}.
	 * @param ctx the parse tree
	 */
	void exitRet(Asm8051Parser.RetContext ctx);
	/**
	 * Enter a parse tree produced by {@link Asm8051Parser#reti}.
	 * @param ctx the parse tree
	 */
	void enterReti(Asm8051Parser.RetiContext ctx);
	/**
	 * Exit a parse tree produced by {@link Asm8051Parser#reti}.
	 * @param ctx the parse tree
	 */
	void exitReti(Asm8051Parser.RetiContext ctx);
	/**
	 * Enter a parse tree produced by {@link Asm8051Parser#rl}.
	 * @param ctx the parse tree
	 */
	void enterRl(Asm8051Parser.RlContext ctx);
	/**
	 * Exit a parse tree produced by {@link Asm8051Parser#rl}.
	 * @param ctx the parse tree
	 */
	void exitRl(Asm8051Parser.RlContext ctx);
	/**
	 * Enter a parse tree produced by {@link Asm8051Parser#rlc}.
	 * @param ctx the parse tree
	 */
	void enterRlc(Asm8051Parser.RlcContext ctx);
	/**
	 * Exit a parse tree produced by {@link Asm8051Parser#rlc}.
	 * @param ctx the parse tree
	 */
	void exitRlc(Asm8051Parser.RlcContext ctx);
	/**
	 * Enter a parse tree produced by {@link Asm8051Parser#rr}.
	 * @param ctx the parse tree
	 */
	void enterRr(Asm8051Parser.RrContext ctx);
	/**
	 * Exit a parse tree produced by {@link Asm8051Parser#rr}.
	 * @param ctx the parse tree
	 */
	void exitRr(Asm8051Parser.RrContext ctx);
	/**
	 * Enter a parse tree produced by {@link Asm8051Parser#rrc}.
	 * @param ctx the parse tree
	 */
	void enterRrc(Asm8051Parser.RrcContext ctx);
	/**
	 * Exit a parse tree produced by {@link Asm8051Parser#rrc}.
	 * @param ctx the parse tree
	 */
	void exitRrc(Asm8051Parser.RrcContext ctx);
	/**
	 * Enter a parse tree produced by {@link Asm8051Parser#setb}.
	 * @param ctx the parse tree
	 */
	void enterSetb(Asm8051Parser.SetbContext ctx);
	/**
	 * Exit a parse tree produced by {@link Asm8051Parser#setb}.
	 * @param ctx the parse tree
	 */
	void exitSetb(Asm8051Parser.SetbContext ctx);
	/**
	 * Enter a parse tree produced by {@link Asm8051Parser#sjmp}.
	 * @param ctx the parse tree
	 */
	void enterSjmp(Asm8051Parser.SjmpContext ctx);
	/**
	 * Exit a parse tree produced by {@link Asm8051Parser#sjmp}.
	 * @param ctx the parse tree
	 */
	void exitSjmp(Asm8051Parser.SjmpContext ctx);
	/**
	 * Enter a parse tree produced by {@link Asm8051Parser#subb}.
	 * @param ctx the parse tree
	 */
	void enterSubb(Asm8051Parser.SubbContext ctx);
	/**
	 * Exit a parse tree produced by {@link Asm8051Parser#subb}.
	 * @param ctx the parse tree
	 */
	void exitSubb(Asm8051Parser.SubbContext ctx);
	/**
	 * Enter a parse tree produced by {@link Asm8051Parser#swap}.
	 * @param ctx the parse tree
	 */
	void enterSwap(Asm8051Parser.SwapContext ctx);
	/**
	 * Exit a parse tree produced by {@link Asm8051Parser#swap}.
	 * @param ctx the parse tree
	 */
	void exitSwap(Asm8051Parser.SwapContext ctx);
	/**
	 * Enter a parse tree produced by {@link Asm8051Parser#xch}.
	 * @param ctx the parse tree
	 */
	void enterXch(Asm8051Parser.XchContext ctx);
	/**
	 * Exit a parse tree produced by {@link Asm8051Parser#xch}.
	 * @param ctx the parse tree
	 */
	void exitXch(Asm8051Parser.XchContext ctx);
	/**
	 * Enter a parse tree produced by {@link Asm8051Parser#xchd}.
	 * @param ctx the parse tree
	 */
	void enterXchd(Asm8051Parser.XchdContext ctx);
	/**
	 * Exit a parse tree produced by {@link Asm8051Parser#xchd}.
	 * @param ctx the parse tree
	 */
	void exitXchd(Asm8051Parser.XchdContext ctx);
	/**
	 * Enter a parse tree produced by {@link Asm8051Parser#xrl}.
	 * @param ctx the parse tree
	 */
	void enterXrl(Asm8051Parser.XrlContext ctx);
	/**
	 * Exit a parse tree produced by {@link Asm8051Parser#xrl}.
	 * @param ctx the parse tree
	 */
	void exitXrl(Asm8051Parser.XrlContext ctx);
	/**
	 * Enter a parse tree produced by {@link Asm8051Parser#label}.
	 * @param ctx the parse tree
	 */
	void enterLabel(Asm8051Parser.LabelContext ctx);
	/**
	 * Exit a parse tree produced by {@link Asm8051Parser#label}.
	 * @param ctx the parse tree
	 */
	void exitLabel(Asm8051Parser.LabelContext ctx);
	/**
	 * Enter a parse tree produced by {@link Asm8051Parser#symbol}.
	 * @param ctx the parse tree
	 */
	void enterSymbol(Asm8051Parser.SymbolContext ctx);
	/**
	 * Exit a parse tree produced by {@link Asm8051Parser#symbol}.
	 * @param ctx the parse tree
	 */
	void exitSymbol(Asm8051Parser.SymbolContext ctx);
	/**
	 * Enter a parse tree produced by {@link Asm8051Parser#comment}.
	 * @param ctx the parse tree
	 */
	void enterComment(Asm8051Parser.CommentContext ctx);
	/**
	 * Exit a parse tree produced by {@link Asm8051Parser#comment}.
	 * @param ctx the parse tree
	 */
	void exitComment(Asm8051Parser.CommentContext ctx);
	/**
	 * Enter a parse tree produced by {@link Asm8051Parser#direct}.
	 * @param ctx the parse tree
	 */
	void enterDirect(Asm8051Parser.DirectContext ctx);
	/**
	 * Exit a parse tree produced by {@link Asm8051Parser#direct}.
	 * @param ctx the parse tree
	 */
	void exitDirect(Asm8051Parser.DirectContext ctx);
	/**
	 * Enter a parse tree produced by {@link Asm8051Parser#immediate}.
	 * @param ctx the parse tree
	 */
	void enterImmediate(Asm8051Parser.ImmediateContext ctx);
	/**
	 * Exit a parse tree produced by {@link Asm8051Parser#immediate}.
	 * @param ctx the parse tree
	 */
	void exitImmediate(Asm8051Parser.ImmediateContext ctx);
	/**
	 * Enter a parse tree produced by the {@code decimal}
	 * labeled alternative in {@link Asm8051Parser#number}.
	 * @param ctx the parse tree
	 */
	void enterDecimal(Asm8051Parser.DecimalContext ctx);
	/**
	 * Exit a parse tree produced by the {@code decimal}
	 * labeled alternative in {@link Asm8051Parser#number}.
	 * @param ctx the parse tree
	 */
	void exitDecimal(Asm8051Parser.DecimalContext ctx);
	/**
	 * Enter a parse tree produced by the {@code hexadecimal}
	 * labeled alternative in {@link Asm8051Parser#number}.
	 * @param ctx the parse tree
	 */
	void enterHexadecimal(Asm8051Parser.HexadecimalContext ctx);
	/**
	 * Exit a parse tree produced by the {@code hexadecimal}
	 * labeled alternative in {@link Asm8051Parser#number}.
	 * @param ctx the parse tree
	 */
	void exitHexadecimal(Asm8051Parser.HexadecimalContext ctx);
	/**
	 * Enter a parse tree produced by the {@code octal}
	 * labeled alternative in {@link Asm8051Parser#number}.
	 * @param ctx the parse tree
	 */
	void enterOctal(Asm8051Parser.OctalContext ctx);
	/**
	 * Exit a parse tree produced by the {@code octal}
	 * labeled alternative in {@link Asm8051Parser#number}.
	 * @param ctx the parse tree
	 */
	void exitOctal(Asm8051Parser.OctalContext ctx);
	/**
	 * Enter a parse tree produced by the {@code binary}
	 * labeled alternative in {@link Asm8051Parser#number}.
	 * @param ctx the parse tree
	 */
	void enterBinary(Asm8051Parser.BinaryContext ctx);
	/**
	 * Exit a parse tree produced by the {@code binary}
	 * labeled alternative in {@link Asm8051Parser#number}.
	 * @param ctx the parse tree
	 */
	void exitBinary(Asm8051Parser.BinaryContext ctx);
	/**
	 * Enter a parse tree produced by {@link Asm8051Parser#string}.
	 * @param ctx the parse tree
	 */
	void enterString(Asm8051Parser.StringContext ctx);
	/**
	 * Exit a parse tree produced by {@link Asm8051Parser#string}.
	 * @param ctx the parse tree
	 */
	void exitString(Asm8051Parser.StringContext ctx);
	/**
	 * Enter a parse tree produced by {@link Asm8051Parser#indirectRegister}.
	 * @param ctx the parse tree
	 */
	void enterIndirectRegister(Asm8051Parser.IndirectRegisterContext ctx);
	/**
	 * Exit a parse tree produced by {@link Asm8051Parser#indirectRegister}.
	 * @param ctx the parse tree
	 */
	void exitIndirectRegister(Asm8051Parser.IndirectRegisterContext ctx);
	/**
	 * Enter a parse tree produced by {@link Asm8051Parser#register}.
	 * @param ctx the parse tree
	 */
	void enterRegister(Asm8051Parser.RegisterContext ctx);
	/**
	 * Exit a parse tree produced by {@link Asm8051Parser#register}.
	 * @param ctx the parse tree
	 */
	void exitRegister(Asm8051Parser.RegisterContext ctx);
	/**
	 * Enter a parse tree produced by {@link Asm8051Parser#accumulator}.
	 * @param ctx the parse tree
	 */
	void enterAccumulator(Asm8051Parser.AccumulatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link Asm8051Parser#accumulator}.
	 * @param ctx the parse tree
	 */
	void exitAccumulator(Asm8051Parser.AccumulatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link Asm8051Parser#sfr}.
	 * @param ctx the parse tree
	 */
	void enterSfr(Asm8051Parser.SfrContext ctx);
	/**
	 * Exit a parse tree produced by {@link Asm8051Parser#sfr}.
	 * @param ctx the parse tree
	 */
	void exitSfr(Asm8051Parser.SfrContext ctx);
	/**
	 * Enter a parse tree produced by {@link Asm8051Parser#bit}.
	 * @param ctx the parse tree
	 */
	void enterBit(Asm8051Parser.BitContext ctx);
	/**
	 * Exit a parse tree produced by {@link Asm8051Parser#bit}.
	 * @param ctx the parse tree
	 */
	void exitBit(Asm8051Parser.BitContext ctx);
	/**
	 * Enter a parse tree produced by {@link Asm8051Parser#notBit}.
	 * @param ctx the parse tree
	 */
	void enterNotBit(Asm8051Parser.NotBitContext ctx);
	/**
	 * Exit a parse tree produced by {@link Asm8051Parser#notBit}.
	 * @param ctx the parse tree
	 */
	void exitNotBit(Asm8051Parser.NotBitContext ctx);
	/**
	 * Enter a parse tree produced by {@link Asm8051Parser#carry}.
	 * @param ctx the parse tree
	 */
	void enterCarry(Asm8051Parser.CarryContext ctx);
	/**
	 * Exit a parse tree produced by {@link Asm8051Parser#carry}.
	 * @param ctx the parse tree
	 */
	void exitCarry(Asm8051Parser.CarryContext ctx);
	/**
	 * Enter a parse tree produced by {@link Asm8051Parser#dptr}.
	 * @param ctx the parse tree
	 */
	void enterDptr(Asm8051Parser.DptrContext ctx);
	/**
	 * Exit a parse tree produced by {@link Asm8051Parser#dptr}.
	 * @param ctx the parse tree
	 */
	void exitDptr(Asm8051Parser.DptrContext ctx);
	/**
	 * Enter a parse tree produced by {@link Asm8051Parser#atDptr}.
	 * @param ctx the parse tree
	 */
	void enterAtDptr(Asm8051Parser.AtDptrContext ctx);
	/**
	 * Exit a parse tree produced by {@link Asm8051Parser#atDptr}.
	 * @param ctx the parse tree
	 */
	void exitAtDptr(Asm8051Parser.AtDptrContext ctx);
	/**
	 * Enter a parse tree produced by {@link Asm8051Parser#atAPlusDptr}.
	 * @param ctx the parse tree
	 */
	void enterAtAPlusDptr(Asm8051Parser.AtAPlusDptrContext ctx);
	/**
	 * Exit a parse tree produced by {@link Asm8051Parser#atAPlusDptr}.
	 * @param ctx the parse tree
	 */
	void exitAtAPlusDptr(Asm8051Parser.AtAPlusDptrContext ctx);
	/**
	 * Enter a parse tree produced by {@link Asm8051Parser#atAPlusPc}.
	 * @param ctx the parse tree
	 */
	void enterAtAPlusPc(Asm8051Parser.AtAPlusPcContext ctx);
	/**
	 * Exit a parse tree produced by {@link Asm8051Parser#atAPlusPc}.
	 * @param ctx the parse tree
	 */
	void exitAtAPlusPc(Asm8051Parser.AtAPlusPcContext ctx);
}