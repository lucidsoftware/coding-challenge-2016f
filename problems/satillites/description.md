# Near miss

<img align="right" src="https://imgs.xkcd.com/comics/six_words.png" title="Ahem. We are STRICTLY an Orbiter shop." alt="Six Words" height="300">

## Description

We are ready for the Orberth Kuiper Maneuver, except that we have just detected a comet near the beginning of the planned route.

Find the shortest distance between the rocket and the comet after launch. For the purposes of this calculation, we'll approximate the trajectories of the rocket and the comet as straight lines with constant velocities.

Don't forget the Cartesian distance formula: √(Δx<sup>2</sup> + Δy<sup>2</sup> + Δz<sup>2</sup>)

## Input

The first line is the starting 3D position of the rocket, given in meters as three integers between -10<sup>6</sup> and 10<sup>6</sup> inclusive.
The second line is the 3D velocity of the rocket, given in meters per second as three integers between -10<sup>3</sup> and 10<sup>3</sup> inclusive.
The next two lines are the same as the first two, except they describe the position and velocity of the comet.

## Output

Output the shortest distance in meters that the rocket will be from the comet, beginning now. You may output any precision, as long as the value is accurate to within 0.001.

<table>
    <tr>
        <th>Input</th>
        <th>Input</th>
    </tr>
    <tr>
        <td>
<pre>0 0 0
0 1 0
1000 1000 1000
1 0 0</pre>
        </td>
        <td>
<pre>10 10 10
-1 -1 -1
100 100 100
1 1 1
</pre>
        </td>
    </tr>
    <tr>
        <th>Output</th>
        <th>Output</th>
    </tr>
    <tr>
        <td>
<pre>500</pre>
        </td>
        <td>
<pre>155.885</pre>
        </td>
    </tr>
</table>
