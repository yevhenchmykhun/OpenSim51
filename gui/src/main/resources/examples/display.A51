; This program multiplexes the number 1234
; on the four 7-segment displays.

; Note: a logic 0 lights a display segment.

start:
	SETB P3.3		; |
	SETB P3.4		; | enable display 3
	MOV P1, #11111001B	; put pattern for 1 on display
	ACALL delay
	CLR P3.3		; enable display 2
	MOV P1, #10100100B	; put pattern for 2 on display
	ACALL delay
	CLR P3.4		; |
	SETB P3.3		; | enable display 1
	MOV P1, #10110000B	; put pattern for 3 on display
	ACALL delay
	CLR P3.3		; enable display 0
	MOV P1, #10011001B	; put pattern for 4 on display
	ACALL delay
	AJMP start		; jump back to start

; a crude delay
delay:
	MOV R0, #200
t:	DJNZ R0, t
	RET
