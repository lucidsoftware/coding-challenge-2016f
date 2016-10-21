# Word search

```
T Q J V I C T O R Y Q Q Q
Q P Q C Q Q C Q N E R D S
W R Q Z O Q O P K F A E E
E O C X N M M Q Q Q Q Q Q
R G V W N P P R O G R I M
T R Q Q M Q E E Q Q Q Q Q
Y A Q Q P Q T Q T Q V Q E
U M Q Q O T L Q N E R O S

COMPETE    NERDS
PROGRAM    VICTORY
```

## Description

Word searches are fun, but these aren't your run-of-the-mill puzzle book word searches. They're a lot bigger, with a lot more words. So let's write a program to solve them.

Words may be found in four directions: left-to-right, top-to-bottom, bottom-left-to-top-right, or top-left-to-bottom-right. 

## Input

The first line consists of two space-separated integers *r* and *c* (1 ≤ r, c ≤ 200). The next *r* lines each have *c* space-separated capital letters (A - Z), representing the word search grid.

The next line is a positive integer *n*. The next *n* lines are the words to find. Words are not repeated in the list, and each word occurs exactly once in the puzzle.

## Output

Output the word search grid as it was given, but replace any letters not part of a match with a period.

## Examples

<table>
    <tr>
        <th>Input</th>
        <th>Input</th>
        <th>Input</th>
    </tr>
    <tr>
        <td valign="top">
<pre>5 5
Q X Q X Q
Q A C M Q
Q R A R Q
Q Q R Q Q
T E S T P
3
ACM
CARS
TEST</pre>
        </td>
        <td valign="top">
<pre>3 9
A Z S D F Q Y R W
N C P B N E T W U
G E M E H Z X R T
3
ACM
HEY
WUT</pre>
        </td>
        <td valign="top">
<pre>5 8
A A A A A A B Z
A A A A A A C Z
A A A A A A D Z
A A A A A A E Z
A A A A A A F Z
10
AAAAAAB
AAAAAAC
AAAAAAD
AAAAAAE
AAAAAAF
AAAAABZ
AAAAACZ
AAAAADZ
AAAAAEZ
AAAAAFZ</pre>
        </td>
    </tr>
    <tr>
        <th>Output</th>
        <th>Output</th>
        <th>Output</th>
    </tr>
    <tr>
        <td valign="top">
<pre>. . . . .
. A C M .
. . A . .
. . R . .
T E S T .</pre>
        </td>
        <td valign="top">
<pre>A . . . . . Y . W
. C . . . E . . U
. . M . H . . . T</pre>
        </td>
        <td valign="top">
<pre>A A A A A A B Z
A A A A A A C Z
A A A A A A D Z
A A A A A A E Z
A A A A A A F Z</pre>
        </td>
    </tr>
</table>
