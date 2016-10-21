# Rat race

<img align="right" src="maze_main.jpg" alt="Rat Maze" height="160">

## Description

Rat Master Inc, market leader in rat maze fabrication, has enlisted you to write a program to rate the difficulty of various maze configurations.  One of the measures of difficulty they want you to report is the longest path in each maze.

Given a series of maze layouts, compute the longest path that a rat can walk without back tracking.

Rat Master always designs their mazes such that that there is exactly one path between any two free blocks.

## Input
    
The first line is two integers *C* and *R* (3 ≤ *C*, *R* ≤ 100) indicating the number of columns and rows. Then exactly *R* lines follow, each containing *C* characters. These characters specify the labyrinth. An octothorp `#` marks represent impassible walls; a period `.` marks a free space where a rat can walk.

## Output

Output `Maximum path length is L` where *L* is the length of the longest path within the respective maze.

## Examples

<table>
    <tr>
        <th>Input</th>
        <th>Input</th>
        <th>Input</th>
        <th>Input</th>
    </tr>
    <tr>
        <td valign="top">
<pre>4 4
####
#..#
##.#
####</pre>
        </td>
        <td valign="top">
<pre>3 3
###
#.#
###</pre>
        </td>
        <td valign="top">
<pre>6 6
######
#....#
#.##.#
####.#
#....#
######</pre>
        </td>
        <td valign="top">
<pre>6 5
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
        <th>Output</th>
    </tr>
    <tr>
        <td valign="top">
<pre>Maximum path length is 2</pre>
        </td>
        <td valign="top">
<pre>Maximum path length is 0</pre>
        </td>
        <td valign="top">
<pre>Maximum path length is 10</pre>
        </td>
        <td valign="top">
<pre>Maximum path length is 10</pre>
        </td>
    </tr>
</table>
