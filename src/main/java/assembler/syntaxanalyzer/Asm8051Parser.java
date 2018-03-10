package assembler.syntaxanalyzer;

// Generated from C:/Users/yevhen/IdeaProjects/8051Sim/src/main/antlr4\Asm8051.g4 by ANTLR 4.7
import org.antlr.v4.runtime.NoViableAltException;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.RuntimeMetaData;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.Vocabulary;
import org.antlr.v4.runtime.VocabularyImpl;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNDeserializer;
import org.antlr.v4.runtime.atn.ParserATNSimulator;
import org.antlr.v4.runtime.atn.PredictionContextCache;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.List;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class Asm8051Parser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, ACALL=4, ADD=5, ADDC=6, AJMP=7, ANL=8, CJNE=9, 
		CLR=10, CPL=11, DA=12, DEC=13, DIV=14, DJNZ=15, INC=16, JB=17, JBC=18, 
		JC=19, JMP=20, JNB=21, JNC=22, JNZ=23, JZ=24, LCALL=25, LJMP=26, MOV=27, 
		MOVC=28, MOVX=29, MUL=30, NOP=31, ORL=32, POP=33, PUSH=34, RET=35, RETI=36, 
		RL=37, RLC=38, RR=39, RRC=40, SETB=41, SJMP=42, SUBB=43, SWAP=44, XCH=45, 
		XCHD=46, XRL=47, P0=48, P1=49, P2=50, P3=51, SP=52, DPL=53, DPH=54, TL0=55, 
		TL1=56, TH0=57, TH1=58, SBUF=59, ACC=60, BCC=61, PCON=62, TCON=63, TMOD=64, 
		SCON=65, IE=66, IP=67, PSW=68, IDL=69, PD=70, GF0=71, GF1=72, SMOD=73, 
		IT0=74, IE0=75, IT1=76, IE1=77, TR0=78, TF0=79, TR1=80, TF1=81, T0M0=82, 
		T0M1=83, C_T0=84, GATE0=85, T1M0=86, T1M1=87, C_T1=88, GATE1=89, R1=90, 
		T1=91, RB8=92, TB8=93, REN=94, SM2=95, SM1=96, SM0=97, EX0=98, ET0=99, 
		EX1=100, ET1=101, ES=102, ET2=103, EA=104, PX0=105, PT0=106, PX1=107, 
		PT1=108, PS=109, PT2=110, PARITY=111, UD=112, OV=113, RS0=114, RS1=115, 
		FO=116, AC=117, CY=118, INDIRECT_REGISTER=119, REGISTER=120, NAME=121, 
		DECIMAL=122, HEXADECIMAL=123, BINARY=124, COMMENT=125, EOL=126, WS=127;
	public static final int
		RULE_listing = 0, RULE_line = 1, RULE_instruction = 2, RULE_mnemonic = 3, 
		RULE_ajmp = 4, RULE_clr = 5, RULE_jnz = 6, RULE_mov = 7, RULE_nop = 8, 
		RULE_subb = 9, RULE_lbl = 10, RULE_label = 11, RULE_name = 12, RULE_comment = 13, 
		RULE_direct = 14, RULE_immediate = 15, RULE_number = 16, RULE_indirectRegister = 17, 
		RULE_register = 18, RULE_accumulator = 19, RULE_sfr = 20, RULE_bit = 21;
	public static final String[] ruleNames = {
		"listing", "line", "instruction", "mnemonic", "ajmp", "clr", "jnz", "mov", 
		"nop", "subb", "lbl", "label", "name", "comment", "direct", "immediate", 
		"number", "indirectRegister", "register", "accumulator", "sfr", "bit"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "','", "':'", "'#'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, "ACALL", "ADD", "ADDC", "AJMP", "ANL", "CJNE", 
		"CLR", "CPL", "DA", "DEC", "DIV", "DJNZ", "INC", "JB", "JBC", "JC", "JMP", 
		"JNB", "JNC", "JNZ", "JZ", "LCALL", "LJMP", "MOV", "MOVC", "MOVX", "MUL", 
		"NOP", "ORL", "POP", "PUSH", "RET", "RETI", "RL", "RLC", "RR", "RRC", 
		"SETB", "SJMP", "SUBB", "SWAP", "XCH", "XCHD", "XRL", "P0", "P1", "P2", 
		"P3", "SP", "DPL", "DPH", "TL0", "TL1", "TH0", "TH1", "SBUF", "ACC", "BCC", 
		"PCON", "TCON", "TMOD", "SCON", "IE", "IP", "PSW", "IDL", "PD", "GF0", 
		"GF1", "SMOD", "IT0", "IE0", "IT1", "IE1", "TR0", "TF0", "TR1", "TF1", 
		"T0M0", "T0M1", "C_T0", "GATE0", "T1M0", "T1M1", "C_T1", "GATE1", "R1", 
		"T1", "RB8", "TB8", "REN", "SM2", "SM1", "SM0", "EX0", "ET0", "EX1", "ET1", 
		"ES", "ET2", "EA", "PX0", "PT0", "PX1", "PT1", "PS", "PT2", "PARITY", 
		"UD", "OV", "RS0", "RS1", "FO", "AC", "CY", "INDIRECT_REGISTER", "REGISTER", 
		"NAME", "DECIMAL", "HEXADECIMAL", "BINARY", "COMMENT", "EOL", "WS"
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
			setState(48); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(45);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << AJMP) | (1L << CLR) | (1L << JNZ) | (1L << MOV) | (1L << NOP) | (1L << SUBB))) != 0) || _la==NAME || _la==COMMENT) {
					{
					setState(44);
					line();
					}
				}

				setState(47);
				match(EOL);
				}
				}
				setState(50); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << AJMP) | (1L << CLR) | (1L << JNZ) | (1L << MOV) | (1L << NOP) | (1L << SUBB))) != 0) || ((((_la - 121)) & ~0x3f) == 0 && ((1L << (_la - 121)) & ((1L << (NAME - 121)) | (1L << (COMMENT - 121)) | (1L << (EOL - 121)))) != 0) );
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
			setState(55);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(52);
				lbl();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(53);
				instruction();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(54);
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
		public MnemonicContext mnemonic() {
			return getRuleContext(MnemonicContext.class,0);
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
			setState(58);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==NAME) {
				{
				setState(57);
				lbl();
				}
			}

			setState(60);
			mnemonic();
			setState(62);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==COMMENT) {
				{
				setState(61);
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

	public static class MnemonicContext extends ParserRuleContext {
		public AjmpContext ajmp() {
			return getRuleContext(AjmpContext.class,0);
		}
		public ClrContext clr() {
			return getRuleContext(ClrContext.class,0);
		}
		public JnzContext jnz() {
			return getRuleContext(JnzContext.class,0);
		}
		public MovContext mov() {
			return getRuleContext(MovContext.class,0);
		}
		public NopContext nop() {
			return getRuleContext(NopContext.class,0);
		}
		public SubbContext subb() {
			return getRuleContext(SubbContext.class,0);
		}
		public MnemonicContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mnemonic; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Asm8051Visitor ) return ((Asm8051Visitor<? extends T>)visitor).visitMnemonic(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MnemonicContext mnemonic() throws RecognitionException {
		MnemonicContext _localctx = new MnemonicContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_mnemonic);
		try {
			setState(70);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case AJMP:
				enterOuterAlt(_localctx, 1);
				{
				setState(64);
				ajmp();
				}
				break;
			case CLR:
				enterOuterAlt(_localctx, 2);
				{
				setState(65);
				clr();
				}
				break;
			case JNZ:
				enterOuterAlt(_localctx, 3);
				{
				setState(66);
				jnz();
				}
				break;
			case MOV:
				enterOuterAlt(_localctx, 4);
				{
				setState(67);
				mov();
				}
				break;
			case NOP:
				enterOuterAlt(_localctx, 5);
				{
				setState(68);
				nop();
				}
				break;
			case SUBB:
				enterOuterAlt(_localctx, 6);
				{
				setState(69);
				subb();
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

	public static class AjmpContext extends ParserRuleContext {
		public TerminalNode AJMP() { return getToken(Asm8051Parser.AJMP, 0); }
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
		}
		public LabelContext label() {
			return getRuleContext(LabelContext.class,0);
		}
		public AjmpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ajmp; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Asm8051Visitor ) return ((Asm8051Visitor<? extends T>)visitor).visitAjmp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AjmpContext ajmp() throws RecognitionException {
		AjmpContext _localctx = new AjmpContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_ajmp);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(72);
			match(AJMP);
			setState(75);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case DECIMAL:
			case HEXADECIMAL:
			case BINARY:
				{
				setState(73);
				number();
				}
				break;
			case NAME:
				{
				setState(74);
				label();
				}
				break;
			default:
				throw new NoViableAltException(this);
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

	public static class ClrContext extends ParserRuleContext {
		public TerminalNode CLR() { return getToken(Asm8051Parser.CLR, 0); }
		public AccumulatorContext accumulator() {
			return getRuleContext(AccumulatorContext.class,0);
		}
		public BitContext bit() {
			return getRuleContext(BitContext.class,0);
		}
		public ClrContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_clr; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Asm8051Visitor ) return ((Asm8051Visitor<? extends T>)visitor).visitClr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ClrContext clr() throws RecognitionException {
		ClrContext _localctx = new ClrContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_clr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(77);
			match(CLR);
			setState(80);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ACC:
				{
				setState(78);
				accumulator();
				}
				break;
			case IDL:
			case PD:
			case GF0:
			case GF1:
			case SMOD:
			case IT0:
			case IE0:
			case IT1:
			case IE1:
			case TR0:
			case TF0:
			case TR1:
			case TF1:
			case T0M0:
			case T0M1:
			case C_T0:
			case GATE0:
			case T1M0:
			case T1M1:
			case C_T1:
			case GATE1:
			case R1:
			case T1:
			case RB8:
			case TB8:
			case REN:
			case SM2:
			case SM1:
			case SM0:
			case EX0:
			case ET0:
			case EX1:
			case ET1:
			case ES:
			case ET2:
			case EA:
			case PX0:
			case PT0:
			case PX1:
			case PT1:
			case PS:
			case PT2:
			case PARITY:
			case UD:
			case OV:
			case RS0:
			case RS1:
			case FO:
			case AC:
			case CY:
			case DECIMAL:
			case HEXADECIMAL:
			case BINARY:
				{
				setState(79);
				bit();
				}
				break;
			default:
				throw new NoViableAltException(this);
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

	public static class JnzContext extends ParserRuleContext {
		public TerminalNode JNZ() { return getToken(Asm8051Parser.JNZ, 0); }
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
		}
		public LabelContext label() {
			return getRuleContext(LabelContext.class,0);
		}
		public JnzContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_jnz; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Asm8051Visitor ) return ((Asm8051Visitor<? extends T>)visitor).visitJnz(this);
			else return visitor.visitChildren(this);
		}
	}

	public final JnzContext jnz() throws RecognitionException {
		JnzContext _localctx = new JnzContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_jnz);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(82);
			match(JNZ);
			setState(85);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case DECIMAL:
			case HEXADECIMAL:
			case BINARY:
				{
				setState(83);
				number();
				}
				break;
			case NAME:
				{
				setState(84);
				label();
				}
				break;
			default:
				throw new NoViableAltException(this);
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
		enterRule(_localctx, 14, RULE_mov);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(87);
			match(MOV);
			setState(126);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
			case 1:
				{
				{
				setState(92);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
				case 1:
					{
					setState(88);
					accumulator();
					}
					break;
				case 2:
					{
					setState(89);
					direct();
					}
					break;
				case 3:
					{
					setState(90);
					indirectRegister();
					}
					break;
				case 4:
					{
					setState(91);
					register();
					}
					break;
				}
				setState(94);
				match(T__0);
				setState(95);
				immediate();
				}
				}
				break;
			case 2:
				{
				{
				setState(97);
				direct();
				setState(98);
				match(T__0);
				setState(102);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case P0:
				case P1:
				case P2:
				case P3:
				case SP:
				case DPL:
				case DPH:
				case TL0:
				case TL1:
				case TH0:
				case TH1:
				case SBUF:
				case ACC:
				case BCC:
				case PCON:
				case TCON:
				case TMOD:
				case SCON:
				case IE:
				case IP:
				case PSW:
				case DECIMAL:
				case HEXADECIMAL:
				case BINARY:
					{
					setState(99);
					direct();
					}
					break;
				case INDIRECT_REGISTER:
					{
					setState(100);
					indirectRegister();
					}
					break;
				case REGISTER:
					{
					setState(101);
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
				setState(106);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case INDIRECT_REGISTER:
					{
					setState(104);
					indirectRegister();
					}
					break;
				case REGISTER:
					{
					setState(105);
					register();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(108);
				match(T__0);
				setState(109);
				direct();
				}
				}
				break;
			case 4:
				{
				{
				setState(111);
				accumulator();
				setState(112);
				match(T__0);
				setState(116);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case P0:
				case P1:
				case P2:
				case P3:
				case SP:
				case DPL:
				case DPH:
				case TL0:
				case TL1:
				case TH0:
				case TH1:
				case SBUF:
				case ACC:
				case BCC:
				case PCON:
				case TCON:
				case TMOD:
				case SCON:
				case IE:
				case IP:
				case PSW:
				case DECIMAL:
				case HEXADECIMAL:
				case BINARY:
					{
					setState(113);
					direct();
					}
					break;
				case INDIRECT_REGISTER:
					{
					setState(114);
					indirectRegister();
					}
					break;
				case REGISTER:
					{
					setState(115);
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
				setState(121);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case P0:
				case P1:
				case P2:
				case P3:
				case SP:
				case DPL:
				case DPH:
				case TL0:
				case TL1:
				case TH0:
				case TH1:
				case SBUF:
				case ACC:
				case BCC:
				case PCON:
				case TCON:
				case TMOD:
				case SCON:
				case IE:
				case IP:
				case PSW:
				case DECIMAL:
				case HEXADECIMAL:
				case BINARY:
					{
					setState(118);
					direct();
					}
					break;
				case INDIRECT_REGISTER:
					{
					setState(119);
					indirectRegister();
					}
					break;
				case REGISTER:
					{
					setState(120);
					register();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(123);
				match(T__0);
				setState(124);
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

	public static class NopContext extends ParserRuleContext {
		public TerminalNode NOP() { return getToken(Asm8051Parser.NOP, 0); }
		public NopContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_nop; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Asm8051Visitor ) return ((Asm8051Visitor<? extends T>)visitor).visitNop(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NopContext nop() throws RecognitionException {
		NopContext _localctx = new NopContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_nop);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(128);
			match(NOP);
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

	public static class SubbContext extends ParserRuleContext {
		public TerminalNode SUBB() { return getToken(Asm8051Parser.SUBB, 0); }
		public AccumulatorContext accumulator() {
			return getRuleContext(AccumulatorContext.class,0);
		}
		public ImmediateContext immediate() {
			return getRuleContext(ImmediateContext.class,0);
		}
		public IndirectRegisterContext indirectRegister() {
			return getRuleContext(IndirectRegisterContext.class,0);
		}
		public DirectContext direct() {
			return getRuleContext(DirectContext.class,0);
		}
		public RegisterContext register() {
			return getRuleContext(RegisterContext.class,0);
		}
		public SubbContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_subb; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Asm8051Visitor ) return ((Asm8051Visitor<? extends T>)visitor).visitSubb(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SubbContext subb() throws RecognitionException {
		SubbContext _localctx = new SubbContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_subb);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(130);
			match(SUBB);
			{
			setState(131);
			accumulator();
			setState(132);
			match(T__0);
			setState(137);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__2:
				{
				setState(133);
				immediate();
				}
				break;
			case INDIRECT_REGISTER:
				{
				setState(134);
				indirectRegister();
				}
				break;
			case P0:
			case P1:
			case P2:
			case P3:
			case SP:
			case DPL:
			case DPH:
			case TL0:
			case TL1:
			case TH0:
			case TH1:
			case SBUF:
			case ACC:
			case BCC:
			case PCON:
			case TCON:
			case TMOD:
			case SCON:
			case IE:
			case IP:
			case PSW:
			case DECIMAL:
			case HEXADECIMAL:
			case BINARY:
				{
				setState(135);
				direct();
				}
				break;
			case REGISTER:
				{
				setState(136);
				register();
				}
				break;
			default:
				throw new NoViableAltException(this);
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
		enterRule(_localctx, 20, RULE_lbl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(139);
			label();
			setState(140);
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
		enterRule(_localctx, 22, RULE_label);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(142);
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
		enterRule(_localctx, 24, RULE_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(144);
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
		enterRule(_localctx, 26, RULE_comment);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(146);
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
		public SfrContext sfr() {
			return getRuleContext(SfrContext.class,0);
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
		enterRule(_localctx, 28, RULE_direct);
		try {
			setState(150);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case DECIMAL:
			case HEXADECIMAL:
			case BINARY:
				enterOuterAlt(_localctx, 1);
				{
				setState(148);
				number();
				}
				break;
			case P0:
			case P1:
			case P2:
			case P3:
			case SP:
			case DPL:
			case DPH:
			case TL0:
			case TL1:
			case TH0:
			case TH1:
			case SBUF:
			case ACC:
			case BCC:
			case PCON:
			case TCON:
			case TMOD:
			case SCON:
			case IE:
			case IP:
			case PSW:
				enterOuterAlt(_localctx, 2);
				{
				setState(149);
				sfr();
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
		enterRule(_localctx, 30, RULE_immediate);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(152);
			match(T__2);
			setState(153);
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
		enterRule(_localctx, 32, RULE_number);
		try {
			setState(158);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case DECIMAL:
				_localctx = new DecimalContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(155);
				match(DECIMAL);
				}
				break;
			case HEXADECIMAL:
				_localctx = new HexadecimalContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(156);
				match(HEXADECIMAL);
				}
				break;
			case BINARY:
				_localctx = new BinaryContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(157);
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
		enterRule(_localctx, 34, RULE_indirectRegister);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(160);
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
		enterRule(_localctx, 36, RULE_register);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(162);
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
		enterRule(_localctx, 38, RULE_accumulator);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(164);
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

	public static class SfrContext extends ParserRuleContext {
		public TerminalNode P0() { return getToken(Asm8051Parser.P0, 0); }
		public TerminalNode P1() { return getToken(Asm8051Parser.P1, 0); }
		public TerminalNode P2() { return getToken(Asm8051Parser.P2, 0); }
		public TerminalNode P3() { return getToken(Asm8051Parser.P3, 0); }
		public TerminalNode SP() { return getToken(Asm8051Parser.SP, 0); }
		public TerminalNode DPL() { return getToken(Asm8051Parser.DPL, 0); }
		public TerminalNode DPH() { return getToken(Asm8051Parser.DPH, 0); }
		public TerminalNode TL0() { return getToken(Asm8051Parser.TL0, 0); }
		public TerminalNode TL1() { return getToken(Asm8051Parser.TL1, 0); }
		public TerminalNode TH0() { return getToken(Asm8051Parser.TH0, 0); }
		public TerminalNode TH1() { return getToken(Asm8051Parser.TH1, 0); }
		public TerminalNode SBUF() { return getToken(Asm8051Parser.SBUF, 0); }
		public TerminalNode ACC() { return getToken(Asm8051Parser.ACC, 0); }
		public TerminalNode BCC() { return getToken(Asm8051Parser.BCC, 0); }
		public TerminalNode PCON() { return getToken(Asm8051Parser.PCON, 0); }
		public TerminalNode TCON() { return getToken(Asm8051Parser.TCON, 0); }
		public TerminalNode TMOD() { return getToken(Asm8051Parser.TMOD, 0); }
		public TerminalNode SCON() { return getToken(Asm8051Parser.SCON, 0); }
		public TerminalNode IE() { return getToken(Asm8051Parser.IE, 0); }
		public TerminalNode IP() { return getToken(Asm8051Parser.IP, 0); }
		public TerminalNode PSW() { return getToken(Asm8051Parser.PSW, 0); }
		public SfrContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sfr; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Asm8051Visitor ) return ((Asm8051Visitor<? extends T>)visitor).visitSfr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SfrContext sfr() throws RecognitionException {
		SfrContext _localctx = new SfrContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_sfr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(166);
			_la = _input.LA(1);
			if ( !(((((_la - 48)) & ~0x3f) == 0 && ((1L << (_la - 48)) & ((1L << (P0 - 48)) | (1L << (P1 - 48)) | (1L << (P2 - 48)) | (1L << (P3 - 48)) | (1L << (SP - 48)) | (1L << (DPL - 48)) | (1L << (DPH - 48)) | (1L << (TL0 - 48)) | (1L << (TL1 - 48)) | (1L << (TH0 - 48)) | (1L << (TH1 - 48)) | (1L << (SBUF - 48)) | (1L << (ACC - 48)) | (1L << (BCC - 48)) | (1L << (PCON - 48)) | (1L << (TCON - 48)) | (1L << (TMOD - 48)) | (1L << (SCON - 48)) | (1L << (IE - 48)) | (1L << (IP - 48)) | (1L << (PSW - 48)))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
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

	public static class BitContext extends ParserRuleContext {
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
		}
		public TerminalNode IDL() { return getToken(Asm8051Parser.IDL, 0); }
		public TerminalNode PD() { return getToken(Asm8051Parser.PD, 0); }
		public TerminalNode GF0() { return getToken(Asm8051Parser.GF0, 0); }
		public TerminalNode GF1() { return getToken(Asm8051Parser.GF1, 0); }
		public TerminalNode SMOD() { return getToken(Asm8051Parser.SMOD, 0); }
		public TerminalNode IT0() { return getToken(Asm8051Parser.IT0, 0); }
		public TerminalNode IE0() { return getToken(Asm8051Parser.IE0, 0); }
		public TerminalNode IT1() { return getToken(Asm8051Parser.IT1, 0); }
		public TerminalNode IE1() { return getToken(Asm8051Parser.IE1, 0); }
		public TerminalNode TR0() { return getToken(Asm8051Parser.TR0, 0); }
		public TerminalNode TF0() { return getToken(Asm8051Parser.TF0, 0); }
		public TerminalNode TR1() { return getToken(Asm8051Parser.TR1, 0); }
		public TerminalNode TF1() { return getToken(Asm8051Parser.TF1, 0); }
		public TerminalNode T0M0() { return getToken(Asm8051Parser.T0M0, 0); }
		public TerminalNode T0M1() { return getToken(Asm8051Parser.T0M1, 0); }
		public TerminalNode C_T0() { return getToken(Asm8051Parser.C_T0, 0); }
		public TerminalNode GATE0() { return getToken(Asm8051Parser.GATE0, 0); }
		public TerminalNode T1M0() { return getToken(Asm8051Parser.T1M0, 0); }
		public TerminalNode T1M1() { return getToken(Asm8051Parser.T1M1, 0); }
		public TerminalNode C_T1() { return getToken(Asm8051Parser.C_T1, 0); }
		public TerminalNode GATE1() { return getToken(Asm8051Parser.GATE1, 0); }
		public TerminalNode R1() { return getToken(Asm8051Parser.R1, 0); }
		public TerminalNode T1() { return getToken(Asm8051Parser.T1, 0); }
		public TerminalNode RB8() { return getToken(Asm8051Parser.RB8, 0); }
		public TerminalNode TB8() { return getToken(Asm8051Parser.TB8, 0); }
		public TerminalNode REN() { return getToken(Asm8051Parser.REN, 0); }
		public TerminalNode SM2() { return getToken(Asm8051Parser.SM2, 0); }
		public TerminalNode SM1() { return getToken(Asm8051Parser.SM1, 0); }
		public TerminalNode SM0() { return getToken(Asm8051Parser.SM0, 0); }
		public TerminalNode EX0() { return getToken(Asm8051Parser.EX0, 0); }
		public TerminalNode ET0() { return getToken(Asm8051Parser.ET0, 0); }
		public TerminalNode EX1() { return getToken(Asm8051Parser.EX1, 0); }
		public TerminalNode ET1() { return getToken(Asm8051Parser.ET1, 0); }
		public TerminalNode ES() { return getToken(Asm8051Parser.ES, 0); }
		public TerminalNode ET2() { return getToken(Asm8051Parser.ET2, 0); }
		public TerminalNode EA() { return getToken(Asm8051Parser.EA, 0); }
		public TerminalNode PX0() { return getToken(Asm8051Parser.PX0, 0); }
		public TerminalNode PT0() { return getToken(Asm8051Parser.PT0, 0); }
		public TerminalNode PX1() { return getToken(Asm8051Parser.PX1, 0); }
		public TerminalNode PT1() { return getToken(Asm8051Parser.PT1, 0); }
		public TerminalNode PS() { return getToken(Asm8051Parser.PS, 0); }
		public TerminalNode PT2() { return getToken(Asm8051Parser.PT2, 0); }
		public TerminalNode PARITY() { return getToken(Asm8051Parser.PARITY, 0); }
		public TerminalNode UD() { return getToken(Asm8051Parser.UD, 0); }
		public TerminalNode OV() { return getToken(Asm8051Parser.OV, 0); }
		public TerminalNode RS0() { return getToken(Asm8051Parser.RS0, 0); }
		public TerminalNode RS1() { return getToken(Asm8051Parser.RS1, 0); }
		public TerminalNode FO() { return getToken(Asm8051Parser.FO, 0); }
		public TerminalNode AC() { return getToken(Asm8051Parser.AC, 0); }
		public TerminalNode CY() { return getToken(Asm8051Parser.CY, 0); }
		public BitContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bit; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Asm8051Visitor ) return ((Asm8051Visitor<? extends T>)visitor).visitBit(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BitContext bit() throws RecognitionException {
		BitContext _localctx = new BitContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_bit);
		try {
			setState(219);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case DECIMAL:
			case HEXADECIMAL:
			case BINARY:
				enterOuterAlt(_localctx, 1);
				{
				setState(168);
				number();
				}
				break;
			case IDL:
				enterOuterAlt(_localctx, 2);
				{
				setState(169);
				match(IDL);
				}
				break;
			case PD:
				enterOuterAlt(_localctx, 3);
				{
				setState(170);
				match(PD);
				}
				break;
			case GF0:
				enterOuterAlt(_localctx, 4);
				{
				setState(171);
				match(GF0);
				}
				break;
			case GF1:
				enterOuterAlt(_localctx, 5);
				{
				setState(172);
				match(GF1);
				}
				break;
			case SMOD:
				enterOuterAlt(_localctx, 6);
				{
				setState(173);
				match(SMOD);
				}
				break;
			case IT0:
				enterOuterAlt(_localctx, 7);
				{
				setState(174);
				match(IT0);
				}
				break;
			case IE0:
				enterOuterAlt(_localctx, 8);
				{
				setState(175);
				match(IE0);
				}
				break;
			case IT1:
				enterOuterAlt(_localctx, 9);
				{
				setState(176);
				match(IT1);
				}
				break;
			case IE1:
				enterOuterAlt(_localctx, 10);
				{
				setState(177);
				match(IE1);
				}
				break;
			case TR0:
				enterOuterAlt(_localctx, 11);
				{
				setState(178);
				match(TR0);
				}
				break;
			case TF0:
				enterOuterAlt(_localctx, 12);
				{
				setState(179);
				match(TF0);
				}
				break;
			case TR1:
				enterOuterAlt(_localctx, 13);
				{
				setState(180);
				match(TR1);
				}
				break;
			case TF1:
				enterOuterAlt(_localctx, 14);
				{
				setState(181);
				match(TF1);
				}
				break;
			case T0M0:
				enterOuterAlt(_localctx, 15);
				{
				setState(182);
				match(T0M0);
				}
				break;
			case T0M1:
				enterOuterAlt(_localctx, 16);
				{
				setState(183);
				match(T0M1);
				}
				break;
			case C_T0:
				enterOuterAlt(_localctx, 17);
				{
				setState(184);
				match(C_T0);
				}
				break;
			case GATE0:
				enterOuterAlt(_localctx, 18);
				{
				setState(185);
				match(GATE0);
				}
				break;
			case T1M0:
				enterOuterAlt(_localctx, 19);
				{
				setState(186);
				match(T1M0);
				}
				break;
			case T1M1:
				enterOuterAlt(_localctx, 20);
				{
				setState(187);
				match(T1M1);
				}
				break;
			case C_T1:
				enterOuterAlt(_localctx, 21);
				{
				setState(188);
				match(C_T1);
				}
				break;
			case GATE1:
				enterOuterAlt(_localctx, 22);
				{
				setState(189);
				match(GATE1);
				}
				break;
			case R1:
				enterOuterAlt(_localctx, 23);
				{
				setState(190);
				match(R1);
				}
				break;
			case T1:
				enterOuterAlt(_localctx, 24);
				{
				setState(191);
				match(T1);
				}
				break;
			case RB8:
				enterOuterAlt(_localctx, 25);
				{
				setState(192);
				match(RB8);
				}
				break;
			case TB8:
				enterOuterAlt(_localctx, 26);
				{
				setState(193);
				match(TB8);
				}
				break;
			case REN:
				enterOuterAlt(_localctx, 27);
				{
				setState(194);
				match(REN);
				}
				break;
			case SM2:
				enterOuterAlt(_localctx, 28);
				{
				setState(195);
				match(SM2);
				}
				break;
			case SM1:
				enterOuterAlt(_localctx, 29);
				{
				setState(196);
				match(SM1);
				}
				break;
			case SM0:
				enterOuterAlt(_localctx, 30);
				{
				setState(197);
				match(SM0);
				}
				break;
			case EX0:
				enterOuterAlt(_localctx, 31);
				{
				setState(198);
				match(EX0);
				}
				break;
			case ET0:
				enterOuterAlt(_localctx, 32);
				{
				setState(199);
				match(ET0);
				}
				break;
			case EX1:
				enterOuterAlt(_localctx, 33);
				{
				setState(200);
				match(EX1);
				}
				break;
			case ET1:
				enterOuterAlt(_localctx, 34);
				{
				setState(201);
				match(ET1);
				}
				break;
			case ES:
				enterOuterAlt(_localctx, 35);
				{
				setState(202);
				match(ES);
				}
				break;
			case ET2:
				enterOuterAlt(_localctx, 36);
				{
				setState(203);
				match(ET2);
				}
				break;
			case EA:
				enterOuterAlt(_localctx, 37);
				{
				setState(204);
				match(EA);
				}
				break;
			case PX0:
				enterOuterAlt(_localctx, 38);
				{
				setState(205);
				match(PX0);
				}
				break;
			case PT0:
				enterOuterAlt(_localctx, 39);
				{
				setState(206);
				match(PT0);
				}
				break;
			case PX1:
				enterOuterAlt(_localctx, 40);
				{
				setState(207);
				match(PX1);
				}
				break;
			case PT1:
				enterOuterAlt(_localctx, 41);
				{
				setState(208);
				match(PT1);
				}
				break;
			case PS:
				enterOuterAlt(_localctx, 42);
				{
				setState(209);
				match(PS);
				}
				break;
			case PT2:
				enterOuterAlt(_localctx, 43);
				{
				setState(210);
				match(PT2);
				}
				break;
			case PARITY:
				enterOuterAlt(_localctx, 44);
				{
				setState(211);
				match(PARITY);
				}
				break;
			case UD:
				enterOuterAlt(_localctx, 45);
				{
				setState(212);
				match(UD);
				}
				break;
			case OV:
				enterOuterAlt(_localctx, 46);
				{
				setState(213);
				match(OV);
				}
				break;
			case RS0:
				enterOuterAlt(_localctx, 47);
				{
				setState(214);
				match(RS0);
				}
				break;
			case RS1:
				enterOuterAlt(_localctx, 48);
				{
				setState(215);
				match(RS1);
				}
				break;
			case FO:
				enterOuterAlt(_localctx, 49);
				{
				setState(216);
				match(FO);
				}
				break;
			case AC:
				enterOuterAlt(_localctx, 50);
				{
				setState(217);
				match(AC);
				}
				break;
			case CY:
				enterOuterAlt(_localctx, 51);
				{
				setState(218);
				match(CY);
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

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\u0081\u00e0\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\3\2\5\2\60\n\2\3\2"+
		"\6\2\63\n\2\r\2\16\2\64\3\3\3\3\3\3\5\3:\n\3\3\4\5\4=\n\4\3\4\3\4\5\4"+
		"A\n\4\3\5\3\5\3\5\3\5\3\5\3\5\5\5I\n\5\3\6\3\6\3\6\5\6N\n\6\3\7\3\7\3"+
		"\7\5\7S\n\7\3\b\3\b\3\b\5\bX\n\b\3\t\3\t\3\t\3\t\3\t\5\t_\n\t\3\t\3\t"+
		"\3\t\3\t\3\t\3\t\3\t\3\t\5\ti\n\t\3\t\3\t\5\tm\n\t\3\t\3\t\3\t\3\t\3\t"+
		"\3\t\3\t\3\t\5\tw\n\t\3\t\3\t\3\t\5\t|\n\t\3\t\3\t\3\t\5\t\u0081\n\t\3"+
		"\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\5\13\u008c\n\13\3\f\3\f\3\f"+
		"\3\r\3\r\3\16\3\16\3\17\3\17\3\20\3\20\5\20\u0099\n\20\3\21\3\21\3\21"+
		"\3\22\3\22\3\22\5\22\u00a1\n\22\3\23\3\23\3\24\3\24\3\25\3\25\3\26\3\26"+
		"\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27"+
		"\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27"+
		"\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27"+
		"\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\5\27\u00de\n\27\3\27\2\2"+
		"\30\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,\2\3\3\2\62F\2\u011d"+
		"\2\62\3\2\2\2\49\3\2\2\2\6<\3\2\2\2\bH\3\2\2\2\nJ\3\2\2\2\fO\3\2\2\2\16"+
		"T\3\2\2\2\20Y\3\2\2\2\22\u0082\3\2\2\2\24\u0084\3\2\2\2\26\u008d\3\2\2"+
		"\2\30\u0090\3\2\2\2\32\u0092\3\2\2\2\34\u0094\3\2\2\2\36\u0098\3\2\2\2"+
		" \u009a\3\2\2\2\"\u00a0\3\2\2\2$\u00a2\3\2\2\2&\u00a4\3\2\2\2(\u00a6\3"+
		"\2\2\2*\u00a8\3\2\2\2,\u00dd\3\2\2\2.\60\5\4\3\2/.\3\2\2\2/\60\3\2\2\2"+
		"\60\61\3\2\2\2\61\63\7\u0080\2\2\62/\3\2\2\2\63\64\3\2\2\2\64\62\3\2\2"+
		"\2\64\65\3\2\2\2\65\3\3\2\2\2\66:\5\26\f\2\67:\5\6\4\28:\5\34\17\29\66"+
		"\3\2\2\29\67\3\2\2\298\3\2\2\2:\5\3\2\2\2;=\5\26\f\2<;\3\2\2\2<=\3\2\2"+
		"\2=>\3\2\2\2>@\5\b\5\2?A\5\34\17\2@?\3\2\2\2@A\3\2\2\2A\7\3\2\2\2BI\5"+
		"\n\6\2CI\5\f\7\2DI\5\16\b\2EI\5\20\t\2FI\5\22\n\2GI\5\24\13\2HB\3\2\2"+
		"\2HC\3\2\2\2HD\3\2\2\2HE\3\2\2\2HF\3\2\2\2HG\3\2\2\2I\t\3\2\2\2JM\7\t"+
		"\2\2KN\5\"\22\2LN\5\30\r\2MK\3\2\2\2ML\3\2\2\2N\13\3\2\2\2OR\7\f\2\2P"+
		"S\5(\25\2QS\5,\27\2RP\3\2\2\2RQ\3\2\2\2S\r\3\2\2\2TW\7\31\2\2UX\5\"\22"+
		"\2VX\5\30\r\2WU\3\2\2\2WV\3\2\2\2X\17\3\2\2\2Y\u0080\7\35\2\2Z_\5(\25"+
		"\2[_\5\36\20\2\\_\5$\23\2]_\5&\24\2^Z\3\2\2\2^[\3\2\2\2^\\\3\2\2\2^]\3"+
		"\2\2\2_`\3\2\2\2`a\7\3\2\2ab\5 \21\2b\u0081\3\2\2\2cd\5\36\20\2dh\7\3"+
		"\2\2ei\5\36\20\2fi\5$\23\2gi\5&\24\2he\3\2\2\2hf\3\2\2\2hg\3\2\2\2i\u0081"+
		"\3\2\2\2jm\5$\23\2km\5&\24\2lj\3\2\2\2lk\3\2\2\2mn\3\2\2\2no\7\3\2\2o"+
		"p\5\36\20\2p\u0081\3\2\2\2qr\5(\25\2rv\7\3\2\2sw\5\36\20\2tw\5$\23\2u"+
		"w\5&\24\2vs\3\2\2\2vt\3\2\2\2vu\3\2\2\2w\u0081\3\2\2\2x|\5\36\20\2y|\5"+
		"$\23\2z|\5&\24\2{x\3\2\2\2{y\3\2\2\2{z\3\2\2\2|}\3\2\2\2}~\7\3\2\2~\177"+
		"\5(\25\2\177\u0081\3\2\2\2\u0080^\3\2\2\2\u0080c\3\2\2\2\u0080l\3\2\2"+
		"\2\u0080q\3\2\2\2\u0080{\3\2\2\2\u0081\21\3\2\2\2\u0082\u0083\7!\2\2\u0083"+
		"\23\3\2\2\2\u0084\u0085\7-\2\2\u0085\u0086\5(\25\2\u0086\u008b\7\3\2\2"+
		"\u0087\u008c\5 \21\2\u0088\u008c\5$\23\2\u0089\u008c\5\36\20\2\u008a\u008c"+
		"\5&\24\2\u008b\u0087\3\2\2\2\u008b\u0088\3\2\2\2\u008b\u0089\3\2\2\2\u008b"+
		"\u008a\3\2\2\2\u008c\25\3\2\2\2\u008d\u008e\5\30\r\2\u008e\u008f\7\4\2"+
		"\2\u008f\27\3\2\2\2\u0090\u0091\5\32\16\2\u0091\31\3\2\2\2\u0092\u0093"+
		"\7{\2\2\u0093\33\3\2\2\2\u0094\u0095\7\177\2\2\u0095\35\3\2\2\2\u0096"+
		"\u0099\5\"\22\2\u0097\u0099\5*\26\2\u0098\u0096\3\2\2\2\u0098\u0097\3"+
		"\2\2\2\u0099\37\3\2\2\2\u009a\u009b\7\5\2\2\u009b\u009c\5\"\22\2\u009c"+
		"!\3\2\2\2\u009d\u00a1\7|\2\2\u009e\u00a1\7}\2\2\u009f\u00a1\7~\2\2\u00a0"+
		"\u009d\3\2\2\2\u00a0\u009e\3\2\2\2\u00a0\u009f\3\2\2\2\u00a1#\3\2\2\2"+
		"\u00a2\u00a3\7y\2\2\u00a3%\3\2\2\2\u00a4\u00a5\7z\2\2\u00a5\'\3\2\2\2"+
		"\u00a6\u00a7\7>\2\2\u00a7)\3\2\2\2\u00a8\u00a9\t\2\2\2\u00a9+\3\2\2\2"+
		"\u00aa\u00de\5\"\22\2\u00ab\u00de\7G\2\2\u00ac\u00de\7H\2\2\u00ad\u00de"+
		"\7I\2\2\u00ae\u00de\7J\2\2\u00af\u00de\7K\2\2\u00b0\u00de\7L\2\2\u00b1"+
		"\u00de\7M\2\2\u00b2\u00de\7N\2\2\u00b3\u00de\7O\2\2\u00b4\u00de\7P\2\2"+
		"\u00b5\u00de\7Q\2\2\u00b6\u00de\7R\2\2\u00b7\u00de\7S\2\2\u00b8\u00de"+
		"\7T\2\2\u00b9\u00de\7U\2\2\u00ba\u00de\7V\2\2\u00bb\u00de\7W\2\2\u00bc"+
		"\u00de\7X\2\2\u00bd\u00de\7Y\2\2\u00be\u00de\7Z\2\2\u00bf\u00de\7[\2\2"+
		"\u00c0\u00de\7\\\2\2\u00c1\u00de\7]\2\2\u00c2\u00de\7^\2\2\u00c3\u00de"+
		"\7_\2\2\u00c4\u00de\7`\2\2\u00c5\u00de\7a\2\2\u00c6\u00de\7b\2\2\u00c7"+
		"\u00de\7c\2\2\u00c8\u00de\7d\2\2\u00c9\u00de\7e\2\2\u00ca\u00de\7f\2\2"+
		"\u00cb\u00de\7g\2\2\u00cc\u00de\7h\2\2\u00cd\u00de\7i\2\2\u00ce\u00de"+
		"\7j\2\2\u00cf\u00de\7k\2\2\u00d0\u00de\7l\2\2\u00d1\u00de\7m\2\2\u00d2"+
		"\u00de\7n\2\2\u00d3\u00de\7o\2\2\u00d4\u00de\7p\2\2\u00d5\u00de\7q\2\2"+
		"\u00d6\u00de\7r\2\2\u00d7\u00de\7s\2\2\u00d8\u00de\7t\2\2\u00d9\u00de"+
		"\7u\2\2\u00da\u00de\7v\2\2\u00db\u00de\7w\2\2\u00dc\u00de\7x\2\2\u00dd"+
		"\u00aa\3\2\2\2\u00dd\u00ab\3\2\2\2\u00dd\u00ac\3\2\2\2\u00dd\u00ad\3\2"+
		"\2\2\u00dd\u00ae\3\2\2\2\u00dd\u00af\3\2\2\2\u00dd\u00b0\3\2\2\2\u00dd"+
		"\u00b1\3\2\2\2\u00dd\u00b2\3\2\2\2\u00dd\u00b3\3\2\2\2\u00dd\u00b4\3\2"+
		"\2\2\u00dd\u00b5\3\2\2\2\u00dd\u00b6\3\2\2\2\u00dd\u00b7\3\2\2\2\u00dd"+
		"\u00b8\3\2\2\2\u00dd\u00b9\3\2\2\2\u00dd\u00ba\3\2\2\2\u00dd\u00bb\3\2"+
		"\2\2\u00dd\u00bc\3\2\2\2\u00dd\u00bd\3\2\2\2\u00dd\u00be\3\2\2\2\u00dd"+
		"\u00bf\3\2\2\2\u00dd\u00c0\3\2\2\2\u00dd\u00c1\3\2\2\2\u00dd\u00c2\3\2"+
		"\2\2\u00dd\u00c3\3\2\2\2\u00dd\u00c4\3\2\2\2\u00dd\u00c5\3\2\2\2\u00dd"+
		"\u00c6\3\2\2\2\u00dd\u00c7\3\2\2\2\u00dd\u00c8\3\2\2\2\u00dd\u00c9\3\2"+
		"\2\2\u00dd\u00ca\3\2\2\2\u00dd\u00cb\3\2\2\2\u00dd\u00cc\3\2\2\2\u00dd"+
		"\u00cd\3\2\2\2\u00dd\u00ce\3\2\2\2\u00dd\u00cf\3\2\2\2\u00dd\u00d0\3\2"+
		"\2\2\u00dd\u00d1\3\2\2\2\u00dd\u00d2\3\2\2\2\u00dd\u00d3\3\2\2\2\u00dd"+
		"\u00d4\3\2\2\2\u00dd\u00d5\3\2\2\2\u00dd\u00d6\3\2\2\2\u00dd\u00d7\3\2"+
		"\2\2\u00dd\u00d8\3\2\2\2\u00dd\u00d9\3\2\2\2\u00dd\u00da\3\2\2\2\u00dd"+
		"\u00db\3\2\2\2\u00dd\u00dc\3\2\2\2\u00de-\3\2\2\2\25/\649<@HMRW^hlv{\u0080"+
		"\u008b\u0098\u00a0\u00dd";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}