// Generated from C:/Users/yevhen/IdeaProjects/8051Sim/src/main/antlr4\Asm8051.g4 by ANTLR 4.7
package assembler.syntaxanalyzer;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class Asm8051Parser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, MOV=4, ACC=5, ASSEMBLER_INSTRUCTION=6, INDIRECT_REGISTER=7, 
		REGISTER=8, NAME=9, DECIMAL=10, HEXADECIMAL=11, BINARY=12, COMMENT=13, 
		EOL=14, WS=15;
	public static final int
		RULE_listing = 0, RULE_line = 1, RULE_instruction = 2, RULE_opcode = 3, 
		RULE_mov = 4, RULE_lbl = 5, RULE_label = 6, RULE_name = 7, RULE_comment = 8, 
		RULE_direct = 9, RULE_immediate = 10, RULE_number = 11, RULE_indirectRegister = 12, 
		RULE_register = 13, RULE_accumulator = 14;
	public static final String[] ruleNames = {
		"listing", "line", "instruction", "opcode", "mov", "lbl", "label", "name", 
		"comment", "direct", "immediate", "number", "indirectRegister", "register", 
		"accumulator"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "','", "':'", "'#'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, "MOV", "ACC", "ASSEMBLER_INSTRUCTION", "INDIRECT_REGISTER", 
		"REGISTER", "NAME", "DECIMAL", "HEXADECIMAL", "BINARY", "COMMENT", "EOL", 
		"WS"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "Asm8051.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public Asm8051Parser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ListingContext extends ParserRuleContext {
		public List<TerminalNode> EOL() { return getTokens(Asm8051Parser.EOL); }
		public TerminalNode EOL(int i) {
			return getToken(Asm8051Parser.EOL, i);
		}
		public List<LineContext> line() {
			return getRuleContexts(LineContext.class);
		}
		public LineContext line(int i) {
			return getRuleContext(LineContext.class,i);
		}
		public ListingContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_listing; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Asm8051Visitor ) return ((Asm8051Visitor<? extends T>)visitor).visitListing(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ListingContext listing() throws RecognitionException {
		ListingContext _localctx = new ListingContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_listing);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(34); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(31);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MOV) | (1L << NAME) | (1L << COMMENT))) != 0)) {
					{
					setState(30);
					line();
					}
				}

				setState(33);
				match(EOL);
				}
				}
				setState(36); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MOV) | (1L << NAME) | (1L << COMMENT) | (1L << EOL))) != 0) );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LineContext extends ParserRuleContext {
		public LblContext lbl() {
			return getRuleContext(LblContext.class,0);
		}
		public InstructionContext instruction() {
			return getRuleContext(InstructionContext.class,0);
		}
		public CommentContext comment() {
			return getRuleContext(CommentContext.class,0);
		}
		public LineContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_line; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Asm8051Visitor ) return ((Asm8051Visitor<? extends T>)visitor).visitLine(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LineContext line() throws RecognitionException {
		LineContext _localctx = new LineContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_line);
		try {
			setState(41);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(38);
				lbl();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(39);
				instruction();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(40);
				comment();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class InstructionContext extends ParserRuleContext {
		public OpcodeContext opcode() {
			return getRuleContext(OpcodeContext.class,0);
		}
		public LblContext lbl() {
			return getRuleContext(LblContext.class,0);
		}
		public CommentContext comment() {
			return getRuleContext(CommentContext.class,0);
		}
		public InstructionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_instruction; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Asm8051Visitor ) return ((Asm8051Visitor<? extends T>)visitor).visitInstruction(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InstructionContext instruction() throws RecognitionException {
		InstructionContext _localctx = new InstructionContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_instruction);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(44);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==NAME) {
				{
				setState(43);
				lbl();
				}
			}

			setState(46);
			opcode();
			setState(48);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==COMMENT) {
				{
				setState(47);
				comment();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OpcodeContext extends ParserRuleContext {
		public MovContext mov() {
			return getRuleContext(MovContext.class,0);
		}
		public OpcodeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_opcode; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Asm8051Visitor ) return ((Asm8051Visitor<? extends T>)visitor).visitOpcode(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OpcodeContext opcode() throws RecognitionException {
		OpcodeContext _localctx = new OpcodeContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_opcode);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(50);
			mov();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MovContext extends ParserRuleContext {
		public TerminalNode MOV() { return getToken(Asm8051Parser.MOV, 0); }
		public ImmediateContext immediate() {
			return getRuleContext(ImmediateContext.class,0);
		}
		public List<DirectContext> direct() {
			return getRuleContexts(DirectContext.class);
		}
		public DirectContext direct(int i) {
			return getRuleContext(DirectContext.class,i);
		}
		public AccumulatorContext accumulator() {
			return getRuleContext(AccumulatorContext.class,0);
		}
		public IndirectRegisterContext indirectRegister() {
			return getRuleContext(IndirectRegisterContext.class,0);
		}
		public RegisterContext register() {
			return getRuleContext(RegisterContext.class,0);
		}
		public MovContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mov; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Asm8051Visitor ) return ((Asm8051Visitor<? extends T>)visitor).visitMov(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MovContext mov() throws RecognitionException {
		MovContext _localctx = new MovContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_mov);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(52);
			match(MOV);
			setState(91);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				{
				{
				setState(57);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case ACC:
					{
					setState(53);
					accumulator();
					}
					break;
				case DECIMAL:
				case HEXADECIMAL:
				case BINARY:
					{
					setState(54);
					direct();
					}
					break;
				case INDIRECT_REGISTER:
					{
					setState(55);
					indirectRegister();
					}
					break;
				case REGISTER:
					{
					setState(56);
					register();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(59);
				match(T__0);
				setState(60);
				immediate();
				}
				}
				break;
			case 2:
				{
				{
				setState(62);
				direct();
				setState(63);
				match(T__0);
				setState(67);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case DECIMAL:
				case HEXADECIMAL:
				case BINARY:
					{
					setState(64);
					direct();
					}
					break;
				case INDIRECT_REGISTER:
					{
					setState(65);
					indirectRegister();
					}
					break;
				case REGISTER:
					{
					setState(66);
					register();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				}
				break;
			case 3:
				{
				{
				setState(71);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case INDIRECT_REGISTER:
					{
					setState(69);
					indirectRegister();
					}
					break;
				case REGISTER:
					{
					setState(70);
					register();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(73);
				match(T__0);
				setState(74);
				direct();
				}
				}
				break;
			case 4:
				{
				{
				setState(76);
				accumulator();
				setState(77);
				match(T__0);
				setState(81);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case DECIMAL:
				case HEXADECIMAL:
				case BINARY:
					{
					setState(78);
					direct();
					}
					break;
				case INDIRECT_REGISTER:
					{
					setState(79);
					indirectRegister();
					}
					break;
				case REGISTER:
					{
					setState(80);
					register();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				}
				break;
			case 5:
				{
				{
				setState(86);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case DECIMAL:
				case HEXADECIMAL:
				case BINARY:
					{
					setState(83);
					direct();
					}
					break;
				case INDIRECT_REGISTER:
					{
					setState(84);
					indirectRegister();
					}
					break;
				case REGISTER:
					{
					setState(85);
					register();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(88);
				match(T__0);
				setState(89);
				accumulator();
				}
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LblContext extends ParserRuleContext {
		public LabelContext label() {
			return getRuleContext(LabelContext.class,0);
		}
		public LblContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lbl; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Asm8051Visitor ) return ((Asm8051Visitor<? extends T>)visitor).visitLbl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LblContext lbl() throws RecognitionException {
		LblContext _localctx = new LblContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_lbl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(93);
			label();
			setState(94);
			match(T__1);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LabelContext extends ParserRuleContext {
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
		public LabelContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_label; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Asm8051Visitor ) return ((Asm8051Visitor<? extends T>)visitor).visitLabel(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LabelContext label() throws RecognitionException {
		LabelContext _localctx = new LabelContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_label);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(96);
			name();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NameContext extends ParserRuleContext {
		public TerminalNode NAME() { return getToken(Asm8051Parser.NAME, 0); }
		public NameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_name; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Asm8051Visitor ) return ((Asm8051Visitor<? extends T>)visitor).visitName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NameContext name() throws RecognitionException {
		NameContext _localctx = new NameContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(98);
			match(NAME);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CommentContext extends ParserRuleContext {
		public TerminalNode COMMENT() { return getToken(Asm8051Parser.COMMENT, 0); }
		public CommentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comment; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Asm8051Visitor ) return ((Asm8051Visitor<? extends T>)visitor).visitComment(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CommentContext comment() throws RecognitionException {
		CommentContext _localctx = new CommentContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_comment);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(100);
			match(COMMENT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DirectContext extends ParserRuleContext {
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
		}
		public DirectContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_direct; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Asm8051Visitor ) return ((Asm8051Visitor<? extends T>)visitor).visitDirect(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DirectContext direct() throws RecognitionException {
		DirectContext _localctx = new DirectContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_direct);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(102);
			number();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ImmediateContext extends ParserRuleContext {
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
		}
		public ImmediateContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_immediate; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Asm8051Visitor ) return ((Asm8051Visitor<? extends T>)visitor).visitImmediate(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ImmediateContext immediate() throws RecognitionException {
		ImmediateContext _localctx = new ImmediateContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_immediate);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(104);
			match(T__2);
			setState(105);
			number();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NumberContext extends ParserRuleContext {
		public NumberContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_number; }
	 
		public NumberContext() { }
		public void copyFrom(NumberContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class BinaryContext extends NumberContext {
		public TerminalNode BINARY() { return getToken(Asm8051Parser.BINARY, 0); }
		public BinaryContext(NumberContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Asm8051Visitor ) return ((Asm8051Visitor<? extends T>)visitor).visitBinary(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class DecimalContext extends NumberContext {
		public TerminalNode DECIMAL() { return getToken(Asm8051Parser.DECIMAL, 0); }
		public DecimalContext(NumberContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Asm8051Visitor ) return ((Asm8051Visitor<? extends T>)visitor).visitDecimal(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class HexadecimalContext extends NumberContext {
		public TerminalNode HEXADECIMAL() { return getToken(Asm8051Parser.HEXADECIMAL, 0); }
		public HexadecimalContext(NumberContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Asm8051Visitor ) return ((Asm8051Visitor<? extends T>)visitor).visitHexadecimal(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NumberContext number() throws RecognitionException {
		NumberContext _localctx = new NumberContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_number);
		try {
			setState(110);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case DECIMAL:
				_localctx = new DecimalContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(107);
				match(DECIMAL);
				}
				break;
			case HEXADECIMAL:
				_localctx = new HexadecimalContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(108);
				match(HEXADECIMAL);
				}
				break;
			case BINARY:
				_localctx = new BinaryContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(109);
				match(BINARY);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IndirectRegisterContext extends ParserRuleContext {
		public TerminalNode INDIRECT_REGISTER() { return getToken(Asm8051Parser.INDIRECT_REGISTER, 0); }
		public IndirectRegisterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_indirectRegister; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Asm8051Visitor ) return ((Asm8051Visitor<? extends T>)visitor).visitIndirectRegister(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IndirectRegisterContext indirectRegister() throws RecognitionException {
		IndirectRegisterContext _localctx = new IndirectRegisterContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_indirectRegister);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(112);
			match(INDIRECT_REGISTER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RegisterContext extends ParserRuleContext {
		public TerminalNode REGISTER() { return getToken(Asm8051Parser.REGISTER, 0); }
		public RegisterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_register; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Asm8051Visitor ) return ((Asm8051Visitor<? extends T>)visitor).visitRegister(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RegisterContext register() throws RecognitionException {
		RegisterContext _localctx = new RegisterContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_register);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(114);
			match(REGISTER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AccumulatorContext extends ParserRuleContext {
		public TerminalNode ACC() { return getToken(Asm8051Parser.ACC, 0); }
		public AccumulatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_accumulator; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Asm8051Visitor ) return ((Asm8051Visitor<? extends T>)visitor).visitAccumulator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AccumulatorContext accumulator() throws RecognitionException {
		AccumulatorContext _localctx = new AccumulatorContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_accumulator);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(116);
			match(ACC);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\21y\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t\13\4"+
		"\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\3\2\5\2\"\n\2\3\2\6\2%\n"+
		"\2\r\2\16\2&\3\3\3\3\3\3\5\3,\n\3\3\4\5\4/\n\4\3\4\3\4\5\4\63\n\4\3\5"+
		"\3\5\3\6\3\6\3\6\3\6\3\6\5\6<\n\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\5\6"+
		"F\n\6\3\6\3\6\5\6J\n\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\5\6T\n\6\3\6\3"+
		"\6\3\6\5\6Y\n\6\3\6\3\6\3\6\5\6^\n\6\3\7\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3"+
		"\n\3\13\3\13\3\f\3\f\3\f\3\r\3\r\3\r\5\rq\n\r\3\16\3\16\3\17\3\17\3\20"+
		"\3\20\3\20\2\2\21\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36\2\2\2\177\2$"+
		"\3\2\2\2\4+\3\2\2\2\6.\3\2\2\2\b\64\3\2\2\2\n\66\3\2\2\2\f_\3\2\2\2\16"+
		"b\3\2\2\2\20d\3\2\2\2\22f\3\2\2\2\24h\3\2\2\2\26j\3\2\2\2\30p\3\2\2\2"+
		"\32r\3\2\2\2\34t\3\2\2\2\36v\3\2\2\2 \"\5\4\3\2! \3\2\2\2!\"\3\2\2\2\""+
		"#\3\2\2\2#%\7\20\2\2$!\3\2\2\2%&\3\2\2\2&$\3\2\2\2&\'\3\2\2\2\'\3\3\2"+
		"\2\2(,\5\f\7\2),\5\6\4\2*,\5\22\n\2+(\3\2\2\2+)\3\2\2\2+*\3\2\2\2,\5\3"+
		"\2\2\2-/\5\f\7\2.-\3\2\2\2./\3\2\2\2/\60\3\2\2\2\60\62\5\b\5\2\61\63\5"+
		"\22\n\2\62\61\3\2\2\2\62\63\3\2\2\2\63\7\3\2\2\2\64\65\5\n\6\2\65\t\3"+
		"\2\2\2\66]\7\6\2\2\67<\5\36\20\28<\5\24\13\29<\5\32\16\2:<\5\34\17\2;"+
		"\67\3\2\2\2;8\3\2\2\2;9\3\2\2\2;:\3\2\2\2<=\3\2\2\2=>\7\3\2\2>?\5\26\f"+
		"\2?^\3\2\2\2@A\5\24\13\2AE\7\3\2\2BF\5\24\13\2CF\5\32\16\2DF\5\34\17\2"+
		"EB\3\2\2\2EC\3\2\2\2ED\3\2\2\2F^\3\2\2\2GJ\5\32\16\2HJ\5\34\17\2IG\3\2"+
		"\2\2IH\3\2\2\2JK\3\2\2\2KL\7\3\2\2LM\5\24\13\2M^\3\2\2\2NO\5\36\20\2O"+
		"S\7\3\2\2PT\5\24\13\2QT\5\32\16\2RT\5\34\17\2SP\3\2\2\2SQ\3\2\2\2SR\3"+
		"\2\2\2T^\3\2\2\2UY\5\24\13\2VY\5\32\16\2WY\5\34\17\2XU\3\2\2\2XV\3\2\2"+
		"\2XW\3\2\2\2YZ\3\2\2\2Z[\7\3\2\2[\\\5\36\20\2\\^\3\2\2\2];\3\2\2\2]@\3"+
		"\2\2\2]I\3\2\2\2]N\3\2\2\2]X\3\2\2\2^\13\3\2\2\2_`\5\16\b\2`a\7\4\2\2"+
		"a\r\3\2\2\2bc\5\20\t\2c\17\3\2\2\2de\7\13\2\2e\21\3\2\2\2fg\7\17\2\2g"+
		"\23\3\2\2\2hi\5\30\r\2i\25\3\2\2\2jk\7\5\2\2kl\5\30\r\2l\27\3\2\2\2mq"+
		"\7\f\2\2nq\7\r\2\2oq\7\16\2\2pm\3\2\2\2pn\3\2\2\2po\3\2\2\2q\31\3\2\2"+
		"\2rs\7\t\2\2s\33\3\2\2\2tu\7\n\2\2u\35\3\2\2\2vw\7\7\2\2w\37\3\2\2\2\16"+
		"!&+.\62;EISX]p";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}