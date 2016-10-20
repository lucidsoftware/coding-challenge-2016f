# Swiss Bank Account

## Description

Excentric multi-millionaire Thurston Howell III died in 1973. His vast fortune was held in a swiss bank account and his only heir, his son Gerald was never able to access the fortune because he could not find the account number amidst his father records. Gerald recently passed away and with his passing several teams of fortune hunters have begun searching for the secret account number hoping to be the first to claim Thurston's fortune. You have been contacted by a group who claims to hold the secret to finding the account number and are asking for your help. They have sent you a picture of a single page that appears to contain the specifications for a primitive (by modern standards) assembly language. This group claims to have possesion of a program that, when run, would output the bank account number.  Your job is to implement a VM to run specified language since no existing processor exists that can run this assembly language.

### Specification

```
REGISTERS: 
    IP - Instruction Pointer, points to the next instruction to be executed, 000 at start
    R00, R01, R02, ..., R08 - 8 bit registers, initialized to zero at start
    CR - stored result of last CMP operation, can be >, =, <, initialized to = at start

INSTRUCTION FORMAT:
Each line of code will be formatted like:
NNN III OP1 OP2
Where NNN is the instruction number, starting at 000 and rising incrementally for each line
      III is a three letter instruction code, see below for instruction codes
      OP1 is the first parameter to the instruction, some instructions do not require this
      OP2 is the second parameter to the instruction, some instructions do not require this

INSTRUCTIONS:
   SET OP1 OP2 - load constant value specified by OP2 into register specified by OP1
   ADD OP1 OP2 - add value in register specified by OP2 to register specified by OP1, 
                 store result in register OP1
   CMP OP1 OP2 - compare value in register OP1 with value in register OP2, stores result
                 in CR
   JMP OP1     - set IP to instruction number OP1 which must be a constant value
   JIF OP1 OP2 - conditional jump.  OP1 must be one of GRE, LES, EQU, GRQ, LEQ NEQ where
                 GRE is >,  LES is <. EQU is =, GRQ is >=, LEQ is <= and NEQ is !=.  
                 Compares CR to specified condition and executes a jump to line 
                 OP2 if condition is met
   OUT OP1     - outputs a single character
   END         - end of the program, all programs must end with this instruction

Constant values can be in one of two forms:
   *  DDD where D is a numeral from 0-9.  0 -> 000, 37 -> 037
   *  'c' where c is any ascii character
```


## Input

Your input will be several lines of assembly code as described in the specification.

## Output

You will output what the assembly code would output via its OUT command.

## Examples

<table>
    <tr>
        <th>Input</th>
        <th>Input</th>
        <th>Input</th>
    </tr>
    <tr>
        <td valign="top">
<pre>000 SET R00 'H'
001 OUT R00
002 SET R00 'e'
003 OUT R00
004 SET R00 'l'
005 OUT R00
006 OUT R00
007 SET R00 'o'
008 OUT R00 
009 END
</pre>
        </td>
        <td valign="top">
<pre>000 SET R00 '0'
001 SET R01 001
002 SET R02 '9'
003 OUT R00
004 ADD R00 R01
005 CMP R00 R02
006 JIF LEQ 003
007 END
</pre>
        </td>
        <td valign="top">
<pre>000 SET R00 13
001 SET R01 '0'
002 SET R02 '9'
003 SET R03 101
004 ADD R00 R03
005 CMP R00 R01
006 JIF LES 004
007 CMP R00 R02
008 JIF GRE 004
009 OUT R00
010 CMP R00 R01
011 JIF NEQ 004
012 END
</pre>
        </td>
    </tr>
    <tr>
        <th>Output</th>
        <th>Output</th>
        <th>Output</th>
    </tr>
    <tr>
        <td>
<pre>Hello</pre>
        </td>
        <td>
<pre>0123456789
</pre>
        </td>
        <td>
        <pre>5381649270</pre>
        </td>
    </tr>
</table>
