package assembler.syntaxanalyzer;

// Generated from C:/Users/yevhen/IdeaProjects/8051Sim/src/main/antlr4\Asm8051.g4 by ANTLR 4.7
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link Asm8051Parser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface Asm8051Visitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link Asm8051Parser#source}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSource(Asm8051Parser.SourceContext ctx);
	/**
	 * Visit a parse tree produced by {@link Asm8051Parser#line}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLine(Asm8051Parser.LineContext ctx);
	/**
	 * Visit a parse tree produced by {@link Asm8051Parser#instruction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInstruction(Asm8051Parser.InstructionContext ctx);
	/**
	 * Visit a parse tree produced by {@link Asm8051Parser#mnemonic}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMnemonic(Asm8051Parser.MnemonicContext ctx);
	/**
	 * Visit a parse tree produced by {@link Asm8051Parser#acall}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAcall(Asm8051Parser.AcallContext ctx);
	/**
	 * Visit a parse tree produced by {@link Asm8051Parser#add}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAdd(Asm8051Parser.AddContext ctx);
	/**
	 * Visit a parse tree produced by {@link Asm8051Parser#addc}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddc(Asm8051Parser.AddcContext ctx);
	/**
	 * Visit a parse tree produced by {@link Asm8051Parser#ajmp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAjmp(Asm8051Parser.AjmpContext ctx);
	/**
	 * Visit a parse tree produced by {@link Asm8051Parser#anl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnl(Asm8051Parser.AnlContext ctx);
	/**
	 * Visit a parse tree produced by {@link Asm8051Parser#cjne}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCjne(Asm8051Parser.CjneContext ctx);
	/**
	 * Visit a parse tree produced by {@link Asm8051Parser#clr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClr(Asm8051Parser.ClrContext ctx);
	/**
	 * Visit a parse tree produced by {@link Asm8051Parser#cpl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCpl(Asm8051Parser.CplContext ctx);
	/**
	 * Visit a parse tree produced by {@link Asm8051Parser#da}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDa(Asm8051Parser.DaContext ctx);
	/**
	 * Visit a parse tree produced by {@link Asm8051Parser#dec}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDec(Asm8051Parser.DecContext ctx);
	/**
	 * Visit a parse tree produced by {@link Asm8051Parser#div}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDiv(Asm8051Parser.DivContext ctx);
	/**
	 * Visit a parse tree produced by {@link Asm8051Parser#djnz}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDjnz(Asm8051Parser.DjnzContext ctx);
	/**
	 * Visit a parse tree produced by {@link Asm8051Parser#inc}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInc(Asm8051Parser.IncContext ctx);
	/**
	 * Visit a parse tree produced by {@link Asm8051Parser#jb}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJb(Asm8051Parser.JbContext ctx);
	/**
	 * Visit a parse tree produced by {@link Asm8051Parser#jbc}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJbc(Asm8051Parser.JbcContext ctx);
	/**
	 * Visit a parse tree produced by {@link Asm8051Parser#jc}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJc(Asm8051Parser.JcContext ctx);
	/**
	 * Visit a parse tree produced by {@link Asm8051Parser#jmp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJmp(Asm8051Parser.JmpContext ctx);
	/**
	 * Visit a parse tree produced by {@link Asm8051Parser#jnb}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJnb(Asm8051Parser.JnbContext ctx);
	/**
	 * Visit a parse tree produced by {@link Asm8051Parser#jnc}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJnc(Asm8051Parser.JncContext ctx);
	/**
	 * Visit a parse tree produced by {@link Asm8051Parser#jnz}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJnz(Asm8051Parser.JnzContext ctx);
	/**
	 * Visit a parse tree produced by {@link Asm8051Parser#jz}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJz(Asm8051Parser.JzContext ctx);
	/**
	 * Visit a parse tree produced by {@link Asm8051Parser#lcall}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLcall(Asm8051Parser.LcallContext ctx);
	/**
	 * Visit a parse tree produced by {@link Asm8051Parser#ljmp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLjmp(Asm8051Parser.LjmpContext ctx);
	/**
	 * Visit a parse tree produced by {@link Asm8051Parser#mov}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMov(Asm8051Parser.MovContext ctx);
	/**
	 * Visit a parse tree produced by {@link Asm8051Parser#movc}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMovc(Asm8051Parser.MovcContext ctx);
	/**
	 * Visit a parse tree produced by {@link Asm8051Parser#movx}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMovx(Asm8051Parser.MovxContext ctx);
	/**
	 * Visit a parse tree produced by {@link Asm8051Parser#mul}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMul(Asm8051Parser.MulContext ctx);
	/**
	 * Visit a parse tree produced by {@link Asm8051Parser#nop}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNop(Asm8051Parser.NopContext ctx);
	/**
	 * Visit a parse tree produced by {@link Asm8051Parser#orl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOrl(Asm8051Parser.OrlContext ctx);
	/**
	 * Visit a parse tree produced by {@link Asm8051Parser#pop}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPop(Asm8051Parser.PopContext ctx);
	/**
	 * Visit a parse tree produced by {@link Asm8051Parser#push}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPush(Asm8051Parser.PushContext ctx);
	/**
	 * Visit a parse tree produced by {@link Asm8051Parser#ret}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRet(Asm8051Parser.RetContext ctx);
	/**
	 * Visit a parse tree produced by {@link Asm8051Parser#reti}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReti(Asm8051Parser.RetiContext ctx);
	/**
	 * Visit a parse tree produced by {@link Asm8051Parser#rl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRl(Asm8051Parser.RlContext ctx);
	/**
	 * Visit a parse tree produced by {@link Asm8051Parser#rlc}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRlc(Asm8051Parser.RlcContext ctx);
	/**
	 * Visit a parse tree produced by {@link Asm8051Parser#rr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRr(Asm8051Parser.RrContext ctx);
	/**
	 * Visit a parse tree produced by {@link Asm8051Parser#rrc}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRrc(Asm8051Parser.RrcContext ctx);
	/**
	 * Visit a parse tree produced by {@link Asm8051Parser#setb}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSetb(Asm8051Parser.SetbContext ctx);
	/**
	 * Visit a parse tree produced by {@link Asm8051Parser#sjmp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSjmp(Asm8051Parser.SjmpContext ctx);
	/**
	 * Visit a parse tree produced by {@link Asm8051Parser#subb}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubb(Asm8051Parser.SubbContext ctx);
	/**
	 * Visit a parse tree produced by {@link Asm8051Parser#swap}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSwap(Asm8051Parser.SwapContext ctx);
	/**
	 * Visit a parse tree produced by {@link Asm8051Parser#xch}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitXch(Asm8051Parser.XchContext ctx);
	/**
	 * Visit a parse tree produced by {@link Asm8051Parser#xchd}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitXchd(Asm8051Parser.XchdContext ctx);
	/**
	 * Visit a parse tree produced by {@link Asm8051Parser#xrl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitXrl(Asm8051Parser.XrlContext ctx);
	/**
	 * Visit a parse tree produced by {@link Asm8051Parser#lbl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLbl(Asm8051Parser.LblContext ctx);
	/**
	 * Visit a parse tree produced by {@link Asm8051Parser#label}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLabel(Asm8051Parser.LabelContext ctx);
	/**
	 * Visit a parse tree produced by {@link Asm8051Parser#name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitName(Asm8051Parser.NameContext ctx);
	/**
	 * Visit a parse tree produced by {@link Asm8051Parser#comment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComment(Asm8051Parser.CommentContext ctx);
	/**
	 * Visit a parse tree produced by {@link Asm8051Parser#direct}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDirect(Asm8051Parser.DirectContext ctx);
	/**
	 * Visit a parse tree produced by {@link Asm8051Parser#immediate}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitImmediate(Asm8051Parser.ImmediateContext ctx);
	/**
	 * Visit a parse tree produced by the {@code decimal}
	 * labeled alternative in {@link Asm8051Parser#number}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDecimal(Asm8051Parser.DecimalContext ctx);
	/**
	 * Visit a parse tree produced by the {@code hexadecimal}
	 * labeled alternative in {@link Asm8051Parser#number}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHexadecimal(Asm8051Parser.HexadecimalContext ctx);
	/**
	 * Visit a parse tree produced by the {@code binary}
	 * labeled alternative in {@link Asm8051Parser#number}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinary(Asm8051Parser.BinaryContext ctx);
	/**
	 * Visit a parse tree produced by {@link Asm8051Parser#indirectRegister}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIndirectRegister(Asm8051Parser.IndirectRegisterContext ctx);
	/**
	 * Visit a parse tree produced by {@link Asm8051Parser#register}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRegister(Asm8051Parser.RegisterContext ctx);
	/**
	 * Visit a parse tree produced by {@link Asm8051Parser#accumulator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAccumulator(Asm8051Parser.AccumulatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link Asm8051Parser#sfr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSfr(Asm8051Parser.SfrContext ctx);
	/**
	 * Visit a parse tree produced by {@link Asm8051Parser#bit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBit(Asm8051Parser.BitContext ctx);
}