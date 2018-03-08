grammar Asm8051;

listing
    : (line? EOL)+
    ;

line
    : lbl
    | instruction
    | comment
    ;

instruction
    : lbl? opcode comment?
    ;

opcode
    : mov
    ;

mov
    : MOV (((accumulator | direct | indirectRegister | register) ',' immediate)
         | (direct ',' (direct | indirectRegister | register))
         | ((indirectRegister | register) ',' direct)
         | (accumulator ',' (direct | indirectRegister | register))
         | ((direct | indirectRegister | register) ',' accumulator))
    ;

lbl
    : label ':'
    ;

label
    : name
    ;

name
    : NAME
    ;

comment
    : COMMENT
    ;

direct
    : number
    ;

immediate
    : '#' number
    ;

number
    : DECIMAL # decimal
    | HEXADECIMAL # hexadecimal
    | BINARY # binary
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

/*
* opcodes
*/

MOV
    : M O V
    ;


ACC
    : A
    ;
INDIRECT_REGISTER
    : '@' REGISTER
    ;
REGISTER
    : R [0-7]
    ;
NAME
    : [a-zA-Z] [a-zA-Z0-9]*
    ;
DECIMAL
    : [0-9]+
    ;
HEXADECIMAL
    : [0-9a-fA-F]+ [Hh]
    ;
BINARY
    : [01]+ [Bb]
    ;
COMMENT
    : ';' ~[\r\n]* -> skip
    ;
EOL
    : '\r'? '\n'
    ;
WS
    : [ \t] -> skip
    ;

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
