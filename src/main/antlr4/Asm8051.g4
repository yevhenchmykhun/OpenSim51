grammar Asm8051;

source
    : (line? EOL)+
    ;

line
    : label
    | directive
    | instruction
    | comment
    ;

directive
   :  (org | equ | set | using | ds | db | dw | end) comment?
   ;

org
    : ORG expression
    ;

equ
    : symbol EQU (expression | accumulator | register)
    ;

set
    : symbol SET (expression | accumulator | register)
    ;

using
    : USING expression
    ;

ds
    : label DS expression
    ;

db
    : label DB (expression | string) (COMMA (expression | string))*
    ;

dw
    : label DW (expression | string) (COMMA (expression | string))*
    ;

end
    : END
    ;

expression
	:	(number | character | symbol | dollar)
	|	LPAREN expression RPAREN
	|	prefix=(PLUS | MINUS) expression
	|	prefix=(HIGH | LOW) expression
	|	expression bop=(SLASH | ASTERISK | MOD | SHL | SHR) expression
	|	expression bop=(PLUS | MINUS) expression
	|	expression comparator=(EQ | NE | LT | LE | GT | GE) expression
	|   prefix=NOT expression
	|   expression bop=AND expression
	|   expression bop=(OR | XOR) expression
	;

character :
    CHARACTER
    ;

dollar :
    DOLLAR
    ;

instruction
    : label? mnemonic comment?
    ;

mnemonic
    : acall
    | add
    | addc
    | ajmp
    | anl
    | cjne
    | clr
    | cpl
    | da
    | dec
    | div
    | djnz
    | inc
    | jb
    | jbc
    | jc
    | jmp
    | jnb
    | jnc
    | jnz
    | jz
    | lcall
    | ljmp
    | mov
    | movc
    | movx
    | mul
    | nop
    | orl
    | pop
    | push
    | ret
    | reti
    | rl
    | rlc
    | rr
    | rrc
    | setb
    | sjmp
    | subb
    | swap
    | xch
    | xchd
    | xrl
    ;

acall : ACALL symbol;
add   : ADD (accumulator COMMA (immediate | indirectRegister | direct | register));
addc  : ADDC (accumulator COMMA (immediate | indirectRegister | direct | register));
ajmp  : AJMP symbol;
anl   : ANL ((accumulator COMMA (immediate | indirectRegister | direct | register))
            | (carry COMMA (bit | notBit))
            | (direct COMMA (accumulator | immediate)));
cjne  : CJNE (((indirectRegister | accumulator | register) COMMA immediate)
            | (accumulator COMMA direct)) COMMA symbol;
clr   : CLR (accumulator | carry | bit);
cpl   : CPL (accumulator | carry | bit);
da    : DA accumulator;
dec   : DEC (indirectRegister | accumulator | direct | register);
div   : DIV AB;
djnz  : DJNZ (direct | register) COMMA symbol;
inc   : INC ((indirectRegister | accumulator | direct | register)
            | dptr
            | register);
jb    : JB bit COMMA symbol;
jbc   : JBC bit COMMA symbol;
jc    : JC symbol;
jmp   : JMP atAPlusDptr;
jnb   : JNB bit COMMA symbol;
jnc   : JNC symbol;
jnz   : JNZ symbol;
jz    : JZ symbol;
lcall : LCALL symbol;
ljmp  : LJMP symbol;
mov   : MOV ((indirectRegister COMMA (immediate | accumulator | direct))
           | (accumulator COMMA (immediate | indirectRegister | direct | register))
           | (bit COMMA carry | carry COMMA bit)
           | (direct COMMA (direct | immediate | indirectRegister | accumulator | register))
           | (dptr COMMA immediate)
           | (register COMMA (immediate | accumulator | direct)));
movc  : MOVC accumulator COMMA (atAPlusDptr | atAPlusPc);
movx  : MOVX (accumulator COMMA (atDptr | indirectRegister)
           | (indirectRegister COMMA accumulator));
mul   : MUL AB;
nop   : NOP;
orl   : ORL ((accumulator COMMA (immediate | indirectRegister | direct | register))
           | (carry COMMA (bit | notBit))
           | (direct COMMA (immediate | accumulator)));
pop   : POP direct;
push  : PUSH direct;
ret   : RET;
reti  : RETI;
rl    : RL accumulator;
rlc   : RLC accumulator;
rr    : RR accumulator;
rrc   : RRC accumulator;
setb  : SETB (carry | bit);
sjmp  : SJMP symbol;
subb  : SUBB (accumulator COMMA (immediate | indirectRegister | direct | register));
swap  : SWAP accumulator;
xch   : XCH accumulator COMMA (indirectRegister | direct | register);
xchd  : XCHD accumulator COMMA indirectRegister;
xrl   : XRL ((accumulator COMMA (immediate | indirectRegister | direct | register))
           | (direct COMMA (immediate | accumulator)));

label
    : symbol COLON
    ;

symbol
    : SYMBOL
    ;

comment
    : COMMENT
    ;

direct
    : number
    | sfr
    ;

immediate
    : HASH expression
    ;

number
    : DECIMAL # decimal
    | HEXADECIMAL # hexadecimal
    | OCTAL # octal
    | BINARY # binary
    ;

string
   : STRING
   ;

indirectRegister
    : INDIRECT_REGISTER
    ;

register
    : REGISTER
    ;

accumulator
    : ACC
    ;

sfr
    : P0
    | P1
    | P2
    | P3
    | SP
    | DPL
    | DPH
    | TL0
    | TL1
    | TH0
    | TH1
    | SBUF
    | ACC
    | BCC
    | PCON
    | TCON
    | TMOD
    | SCON
    | IE
    | IP
    | PSW
    ;

bit
    : number
    | IDL
    | PD
    | GF0
    | GF1
    | SMOD
    | IT0
    | IE0
    | IT1
    | IE1
    | TR0
    | TF0
    | TR1
    | TF1
    | T0M0
    | T0M1
    | C_T0
    | GATE0
    | T1M0
    | T1M1
    | C_T1
    | GATE1
    | R1
    | T1
    | RB8
    | TB8
    | REN
    | SM2
    | SM1
    | SM0
    | EX0
    | ET0
    | EX1
    | ET1
    | ES
    | ET2
    | EA
    | PX0
    | PT0
    | PX1
    | PT1
    | PS
    | PT2
    | PARITY
    | UD
    | OV
    | RS0
    | RS1
    | FO
    | AC
    | CARRY
    | PORT_BIT
    | ACC_BIT
    | B_BIT
    ;

notBit
    : '/' bit
    ;

carry :
    CARRY
    ;

dptr :
    DPTR
    ;

atDptr :
    AT DPTR
    ;

atAPlusDptr :
    AT accumulator PLUS DPTR
    ;

atAPlusPc :
    AT accumulator PLUS PC
    ;

// tokens

// directive tokens

EQU     : E Q U;
SET     : S E T;
ORG     : O R G;
USING   : U S I N G;
DS      : D S;
DB      : D B;
DW      : D W;
END     : E N D;

// expression tokens

HIGH            : H I G H;
LOW             : L O W;
DOLLAR          : '$';
SLASH           : '/';
ASTERISK        : '*';
PLUS            : '+';
MINUS           : '-';
LPAREN          : '(';
RPAREN          : ')';
MOD             : M O D;
SHL             : S H L;
SHR             : S H R;
NOT             : N O T;
AND             : A N D;
OR              : O R;
XOR             : X O R;

EQ  : E Q | '=';
NE  : N E | '<>';
LT  : L T | '<';
LE  : L E | '<=';
GT  : G T | '>';
GE  : G E | '>=';

// mnemonics

ACALL : A C A L L;
ADD   : A D D;
ADDC  : A D D C;
AJMP  : A J M P;
ANL   : A N L;
CJNE  : C J N E;
CLR   : C L R;
CPL   : C P L;
DA    : D A;
DEC   : D E C;
DIV   : D I V;
DJNZ  : D J N Z;
INC   : I N C;
JB    : J B;
JBC   : J B C;
JC    : J C;
JMP   : J M P;
JNB   : J N B;
JNC   : J N C;
JNZ   : J N Z;
JZ    : J Z;
LCALL : L C A L L;
LJMP  : L J M P;
MOV   : M O V;
MOVC  : M O V C;
MOVX  : M O V X;
MUL   : M U L;
NOP   : N O P;
ORL   : O R L;
POP   : P O P;
PUSH  : P U S H;
RET   : R E T;
RETI  : R E T I;
RL    : R L;
RLC   : R L C;
RR    : R R;
RRC   : R R C;
SETB  : S E T B;
SJMP  : S J M P;
SUBB  : S U B B;
SWAP  : S W A P;
XCH   : X C H;
XCHD  : X C H D;
XRL   : X R L;

// SFRs

P0   : P '0';
P1   : P '1';
P2   : P '2';
P3   : P '3';
SP   : S P;
DPL  : D P L;
DPH  : D P H;
TL0  : T L '0';
TL1  : T L '1';
TH0  : T H '0';
TH1  : T H '1';
SBUF : S B U F;
ACC  : A;
BCC  : B;
PCON : P C O N;
TCON : T C O N;
TMOD : T M O D;
SCON : S C O N;
IE   : I E;
IP   : I P;
PSW  : P S W;

// bits

IDL    : I D L;
PD     : P D;
GF0    : G F '0';
GF1    : G F '1';
SMOD   : S M O D;
IT0    : I T '0';
IE0    : I E '0';
IT1    : I T '1';
IE1    : I E '1';
TR0    : T R '0';
TF0    : T F '0';
TR1    : T R '1';
TF1    : T F '1';
T0M0   : T '0' M '0';
T0M1   : T '0' M '1';
C_T0   : C '/' T '0';
GATE0  : G A T E '0';
T1M0   : T '1' M '0';
T1M1   : T '1' M '1';
C_T1   : C '/' T '1';
GATE1  : G A T E '1';
R1     : R I;
T1     : T I;
RB8    : R B '8';
TB8    : T B '8';
REN    : R E N;
SM2    : S M '2';
SM1    : S M '1';
SM0    : S M '0';
EX0    : E X '0';
ET0    : E T '0';
EX1    : E X '1';
ET1    : E T '1';
ES     : E S;
ET2    : E T '2';
EA     : E A;
PX0    : P X '0';
PT0    : P T '0';
PX1    : P X '1';
PT1    : P T '1';
PS     : P S;
PT2    : P T '2';
PARITY : P;
UD     : U D;
OV     : O V;
RS0    : R S '0';
RS1    : R S '1';
FO     : F O;
AC     : A C;
CARRY  : C;
PORT_BIT: (P0 | P1 | P2 | P3) DOT [0-7];
ACC_BIT: ACC DOT [0-7];
B_BIT: BCC DOT [0-7];

PC : P C;
DPTR : D P T R;
AB : A B;
REGISTER : R [0-7];
INDIRECT_REGISTER : AT R [01];

HASH    : '#';
COLON   : ':';
COMMA   : ',';
DOT     : '.';
AT      : '@';

DECIMAL : [0-9]+ [Dd]?;
HEXADECIMAL : [0-9a-fA-F]+ [Hh];
OCTAL : [0-7]+ [OoQq];
BINARY : [01]+ [Bb];

SYMBOL : [a-zA-Z?_] [a-zA-Z0-9?_]*;
CHARACTER : '\'' [a-zA-Z0-9][a-zA-Z0-9]? '\'';
STRING : '\'' ~ [']* '\'';
COMMENT : ';' ~[\r\n]* -> skip;
EOL : '\r'? '\n';
WS : [ \t] -> skip;

// fragments

fragment A : [aA];
fragment B : [bB];
fragment C : [cC];
fragment D : [dD];
fragment E : [eE];
fragment F : [fF];
fragment G : [gG];
fragment H : [hH];
fragment I : [iI];
fragment J : [jJ];
fragment K : [kK];
fragment L : [lL];
fragment M : [mM];
fragment N : [nN];
fragment O : [oO];
fragment P : [pP];
fragment Q : [qQ];
fragment R : [rR];
fragment S : [sS];
fragment T : [tT];
fragment U : [uU];
fragment V : [vV];
fragment W : [wW];
fragment X : [xX];
fragment Y : [yY];
fragment Z : [zZ];