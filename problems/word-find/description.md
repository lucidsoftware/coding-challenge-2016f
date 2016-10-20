# Word find

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

Solve a word search puzzle. Words may be found in four ways
* left to right: every character appears one column to the right of the preceding character.
* top to bottom: every character appears one row below the preceding character.
* up diagonal: every character appears one row above and one character to the right of the preceding character
* down diagonal: every character appears one row below and one character to the right of the preceding character

## Input

The first line consists of two space-separated integers *r* and *c*.
The next *r* lines each have *c* space-separated characters, representing the word search grid.
The next line is *n*.
The next *n* lines are the words to find.

## Output

Output the word search grid as it was given, but replace any letters not part of a match with a period.

## Examples

<table>
    <tr>
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
    </tr>
    <tr>
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
    </tr>
</table>
