;        0064    6
                        HUNDRED     EQU     100         ;100 x 10000 us = 1 sec
;        2710    7
                        COUNT       EQU     10000
;8100            8
                                    ORG     8100H
;8100    758901  9
                                    MOV     TMOD,#01H   ;use timer 0 in mode 1
;8103    3096FD  10
                        LOOP:       JNB     P1.6,LOOP   ;wait for 1 input
;8106    2096FD  11
                        WAIT:       JB      P1.6,WAIT   ;wait for 0 input
;8109    D297    12
                                    SETB    P1.7        ;turn buzzer on
;810B    128112  13
                                    CALL    DELAY       ;wait 1 second
;810E    C297    14
                                    CLR     P1.7        ;turn buzzer off
;8110    80F1    15
                                    SJMP    LOOP

;8112    7F64    16
                        DELAY:      MOV     R7,#HUNDRED
;8114    758C27  17
                        AGAIN:      MOV     TH0,#HIGH(COUNT)
;8117    758A10  18
                                    MOV     TL0,#LOW(COUNT)
;811A    D28C    19
                                    SETB    TR0
;811C    308DFD  20
                        WAIT2:      JNB     TF0,WAIT2
;811F    C28D    21
                                    CLR     TF0
;8121    C28C    22
                                    CLR     TR0
;8123    DFEF    23
                                    DJNZ    R7,AGAIN
;8125    22      24
                                    RET
;                25
                                    END