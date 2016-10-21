# Knight's journey

## Description

Imagine a chess board wrapped around a torus as shown in the figure below.

<div align="middle">
    <img src="Toroidal-Poloidal.png" height="250">
</div>

This wrapping causes the top edge to connect to the bottom edge of the chess board, as well as left edge to connect to the right.

A knight is a chess piece that moves one space along one axis and two spaces along the other axis.

Calculate the minimum number of moves required for a knight to travel from a given starting position to a given destination on a toroidal board of arbitrary size.

## Input

The first line is two integers *R* and *C* (1 ≤ *R*, *C* ≤ 10,000), which are the dimensions of the board.

The second line is the starting location, and the third line is the ending location.

## Output

Output a single integer for each test case indicating the minimum number of moves that are required for the knight to move from the origin to the destination.

## Examples

<table>
    <tr>
        <th>Input</th>
        <th>Input</th>
    </tr>
    <tr>
        <td valign="top">
<pre>8 8
0 0
7 7</pre>
        </td>
        <td valign="top">
<pre>2 100
0 0
1 50
</pre>
        </td>
    </tr>
    <tr>
        <th>Output</th>
        <th>Output</th>
    </tr>
    <tr>
        <td valign="top">
<pre>2</pre>
        </td>
        <td valign="top">
<pre>25</pre>
        </td>
    </tr>
</table>
