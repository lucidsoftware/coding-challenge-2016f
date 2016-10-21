# No squares

<img align="right" src="http://imgs.xkcd.com/comics/factoring_the_time.png" title="I occasionally do this with mile markers on the highway." alt="Factoring the Time" height="300">

## Description

**Factoring** a positive integer is decomposing it into a product of one or more integers greater than 1. For example, 24 can be factored as 24, 2×12, 3×8, 4×6, 2×2×6, 2×3×4, or 2×2×2×3.

A **square** is an integer that can be factored as two equal integers. For example, 4 is square because it can be factored as 2×2.

If a factoring has any factor that is divisible by a square greater than 1, let's call it **squarish**. For example, since 4 is a square, five of the factorings of 24 are squarish: 24, 2×12, 3×8, 4×6, and 2×3×4. That leaves two non-squarish factorings: 2×2×6 and 2×2×2×3.

Given a postive integer, find the shortest possible length of a non-squarish factoring. For example, for 24, the shortest possible length is three (2×2×6).

## Input

An integer between 2 and 2,000,000 inclusive.

## Output

The shortest length of the integer's non-squarish factorings.

## Examples

<table>
    <tr>
        <th>Input</th>
        <th>Input</th>
        <th>Input</th>
    </tr>
    <tr>
        <td><pre>24</pre></td>
        <td><pre>4</pre></td>
        <td><pre>1000000</pre></td>
    </tr>
    <tr>
        <th>Output</th>
        <th>Output</th>
        <th>Output</th>
    </tr>
    <tr>
        <td><pre>3</pre></td>
        <td><pre>2</pre></td>
        <td><pre>6</pre></td>
    </tr>
</table>
