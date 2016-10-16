import java.util.Scanner

object Conveyor {
	
	class bitfield2d(w:Int, h:Int) {
		val bits = Array.ofDim[Int](((w*h)/32) + 1)
		def set(x:Int, y:Int):Unit ={
			val bit = x + (y*w);
			val bitSubIndex = bit & 0x1F;
			val intIndex = bit >> 5;
			bits(intIndex) = bits(intIndex) | (1<<bitSubIndex);
		}
		def clear(x:Int, y:Int):Unit ={
			val bit = x + (y*w);
			val bitSubIndex = bit & 0x1F;
			val intIndex = bit >> 5;
			bits(intIndex) = bits(intIndex) & ~(1<<bitSubIndex);

		}
		def check(x:Int, y:Int):Boolean ={
			val bit = x + (y*w);
			val bitSubIndex:Int = bit & 0x1F;
			val intIndex:Int = bit >> 5;
			var bitsAtLoc:Int = bits(intIndex);
			if ((bitsAtLoc & (1<<bitSubIndex)) != 0) true else false
		}
	}

	def positionIndex(x:Int, y:Int, w:Int):Int = x + y*w;
	/**
	 * findLoopLength given a starting x,y position, follow the layout until we run into either
	 *  - a dead end, meaning the starting point did not result in a loop
	 *  - or a spot we may or may not have visited already on this traversal
	 */
	def findLoopLength(layout:Array[Array[Char]], visited:bitfield2d, startX:Int, startY:Int):Int = {
		if (visited.check(startX,startY)) return 0 // if we've already been here, no need to check again
		var traverseList = scala.collection.mutable.Map[Int,Int]();
		var curX = startX;
		var curY = startY;
		var curIndex = 1;
		var width = layout.length;
		while (true) {
			visited.set(curX,curY);
			traverseList += (positionIndex(curX,curY,width) -> curIndex);
			val curSpot = layout(curX)(curY);
			val dir = 	curSpot match {
				case 'V' =>  Array(0,1)
				case '^' =>  Array(0,-1)
				case '<' =>  Array(-1,0)
				case '>' =>  Array(1,0)
				case _ =>    null
			}
			if (dir == null) {
				return 0
			}
			else {
				var nextX = curX + dir(0)
				var nextY = curY + dir(1)
				curIndex+=1;

				// did we go off the edge?  If so, no loop here!
				if (nextX < 0 || nextY < 0 || nextX >= layout.length || nextY >= layout(0).length) {
					return 0
				}

				
				if (visited.check(nextX,nextY)) { // did we find a spot we've already traversed?
					val nextIndex = positionIndex(nextX, nextY,width)
					if (traverseList contains nextIndex) { // did we traverse over this position on this iteration?
						// we've looped to a spot that we visited during this iteration
					    // calculate the length of the loop based on the index of where we 
					    // intersected our self
						var headPosition:Int = traverseList(nextIndex)
						return  (curIndex - headPosition);
					}
					else { // we visitied the next spot before, but not on this iteration
						   // so this traversal is not part of a loop
						return 0;
					}
				}
				else {
					curX = nextX
					curY = nextY
				}
			}
		}
		return 0
	}

	def main(args: Array[String]) = {
		//read input
		val scanner = new Scanner(System.in)
		val r = scanner.nextInt();
		val c = scanner.nextInt();
		val layout = Array.ofDim[Char](c,r)
		for (y <- 0 until r) {
			for (x <- 0 until c) {
				layout(x)(y) = scanner.next().charAt(0);
			}
		}
		// process
		var visited = new bitfield2d(c,r);
		var longest = 0;
		for (y <- 0 until r) {
			for (x <- 0 until c) {
				val length = findLoopLength(layout,visited,x,y)
				if (length > longest) 
					longest = length
			}
		}
		println(longest)
	}
}