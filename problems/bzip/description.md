# Binary zip

<img align="right" src="http://imgs.xkcd.com/comics/1_to_10.png" alt="If you get an 11/100 on a CS test, but you claim it should be counted as a &#39;C&#39;, they&#39;ll probably decide you deserve the upgrade." title="If you get an 11/100 on a CS test, but you claim it should be counted as a &#39;C&#39;, they&#39;ll probably decide you deserve the upgrade." height="250">

## Description

Two numbers "zip" together if their binary representations line up such that the 0's and the 1's are all opposite.

The unsigned 8 bit integer 170 is represented as 10101010. the 8 bit unsigned integer that will "zip" with 170 is 85, which in binary is 01010101.

```
10101010
01010101
```

## Input

The input will consist of two lines. The first line will have a number *N* (1 <= *N* <= 32), the number of bits in each number. The second line will contain two *N*-bit unsigned integers separated by a space.

## Output

The two integers make a binary zip, print `YES`. Otherwise, print `NO`.

## Examples

<table>
    <tr>
        <th>Input</th>
        <th>Input</th>
        <th>Input</th>
        <th>Input</th>
        <th>Input</th>
    </tr>
    <tr>
        <td>
<pre>1
0 1</pre>
        </td>
        <td>
<pre>8
170 85</pre>
        </td>
        <td>
<pre>5
13 18</pre>
        </td>
        <td>
<pre>5
5 27</pre>
        </td>
        <td>
<pre>8
13 18</pre>
        </td>
    </tr>
    <tr>
        <th>Output</th>
        <th>Output</th>
        <th>Output</th>
        <th>Output</th>
        <th>Output</th>
    </tr>
    <tr>
        <td>
<pre>YES</pre>
        </td>
        <td>
<pre>YES</pre>
        </td>
        <td>
<pre>YES</pre>
        </td>
        <td>
        <pre>NO</pre>
        </td>
        <td>
        <pre>NO</pre>
        </td>
    </tr>
</table>
