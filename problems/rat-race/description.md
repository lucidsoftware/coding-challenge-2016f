# Rat race

<img align="right" src="maze_main.jpg" alt="Rat Maze" height="160">

## Description

Your company, RatMaster Inc, market leader in rat maze fabrication has enlisted you to write a program to rate the difficulty of various maze configurations.  One of the measures of difficulty they want you to report is the longest path in each maze. You must write a program which, given a series of maze layouts, analyzes each and computes the longest path that a rat can walk without back tracking.
        
## Input
    
The input consists of T test cases. The quantity of them (T) is given on the first line of the input file. Each test case begins with a line containing two integers C and R (3 ≤ C,R ≤ 100) indicating the number of columns and rows. Then exactly R lines follow, each containing C characters. These characters specify the labyrinth. Each of them is either a hash mark (#) or a period (.). Hash marks represent impassible walls, periods are free spaces where a rat can walk. Eat space has a unit length and width of 1.

The labyrinths are designed in such a way that there is exactly one path between any two free blocks.

## Output

Your program must print exactly one line of output for each test case. The line must contain the sentence "Maximum path length is X." where X is the integer length of the longest path within the respective maze.

## Examples

<table>
    <tr>
        <th>Input</th>
        <th>Input</th>
        <th>Input</th>
    </tr>
    <tr>
        <td valign="top">
<pre>1
4 4
####
#..#
##.#
####</pre>
        </td>
        <td valign="top">
<pre>2
3 3
###
#.#
###
6 6
######
#....#
#.##.#
####.#
#....#
######
</pre>
        </td>
        <td valign="top">
<pre>1
6 5
#.###.
#..#..
##.##.
......
#####.</pre>
        </td>
    </tr>
    <tr>
        <th>Output</th>
        <th>Output</th>
        <th>Output</th>
    </tr>
    <tr>
        <td valign="top">
<pre>Maximum path length is 2</pre>
        </td>
        <td valign="top">
<pre>Maximum path length is 0
Maximum path length is 10
</pre>
        </td>
        <td valign="top">
        <pre>Maximum path length is 10</pre>
        </td>
    </tr>
</table>
