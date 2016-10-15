import java.util.Scanner
import java.util.ArrayList

object Conveyor {

	
	/**
	 * findLoopLength given a starting x,y position, follow the layout until we run into either
	 *  - a dead end, meaning the starting point did not result in a loop
	 *  - or a spot we have visited already on this traversal
	 */
	def findLoopLength(layout:Array[Array[Char]], 
		visitIndex:Array[Array[Int]], 
		traversalIndex:Array[Array[Int]],
		startX:Int, 
		startY:Int):Int = {
		val traversal = Array.ofDim[Int](layout.length,layout(0).length);
		if (visitIndex(startX)(startY) != 0) return 0

		var done = false;
		var curX = startX;
		var curY = startY;
		var curIndex = 1;
		var result = 0;
		var thisVisitIndex = startY*layout.length + startX + 1;
		while (!done) {
			visitIndex(curX)(curY) = thisVisitIndex;
			traversalIndex(curX)(curY) = curIndex;
			val curSpot = layout(curX)(curY);
			println(curX + "," + curY + " " + curSpot)
			val dir = 	curSpot match {
				case 'V' =>  Array(0,1)
				case '^' =>  Array(0,-1)
				case '<' =>  Array(-1,0)
				case '>' =>  Array(1,0)
				case _ =>    null
			}
			var nextX = 0
			var nextY = 0
			if (dir == null) {
				done = true
				result = 0
			}
			else {
				nextX = curX + dir(0)
				nextY = curY + dir(1)

				// did we go off the edge?  If so, no loop here!
				if (nextX < 0 || nextY < 0 || nextX >= layout.length || nextY >= layout(0).length) {
					return 0
				}

				// did we find a spot we've already traversed on this iteration?
				if (visitIndex(nextX)(nextY) != 0)
				{
					if (visitIndex(nextX)(nextY) == thisVisitIndex)
					{   // we've looped to a spot that we visited during this iteration
					    // calculate the length of the loop based on the index of where we 
					    // intersected our self
						result = (curIndex - traversal(nextX)(nextY));
						done = true
					}
					else
					{
						// we visitied the next spot before, but not on this iteration
						// so this traversal is not part of a loop
						result = 0;
						done = true;
					}
				}
				else
				{

					curIndex+=1;
					curX = nextX
					curY = nextY
				}
			}
			0
		}
		return result
	}
	def main(args: Array[String]) = {
		
		// read input
		val scanner = new Scanner(System.in)
		val r = scanner.nextInt();
		val c = scanner.nextInt();
		val layout = Array.ofDim[Char](c,r)
		for (y <- 0 until r) {
			for (x <- 0 until c) {
				layout(x)(y) = scanner.next().charAt(0);
			}
		}

		// we'll be using this array to track which locations we've already traversed
		val visitIndex = Array.ofDim[Int](c,r)
		val traversalIndex = Array.ofDim[Int](c,r)

		var longest = 0;
		for (y <- 0 until r) {
			for (x <- 0 until c) {
				val length = findLoopLength(layout,visitIndex,traversalIndex,x,y)
				if (length > longest) 
					longest = length
			}
		}

		println(longest)
	
	}
}