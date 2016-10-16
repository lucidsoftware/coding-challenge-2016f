import scala.collection.mutable.Stack

object MaxeMaker {
	val rand = scala.util.Random
	
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

	def pickDirection():(Int,Int) = {
		rand.nextInt(4) match {
			case 0 => (0,1)
			case 1 => (1,0)
			case 2 => (0,-1)
			case _ => (-1,0)
		}
	}
	def isInBounds(x:Int, y:Int, w:Int, h:Int):Boolean = {
		if (x<0 || y<0 || x>=w || y >= h)
			false
		else 
			true
	}
	def canGo(visited:bitfield2d, dir:(Int,Int),x:Int, y:Int, w:Int, h:Int):Boolean = {
		val nextX = x + dir._1 * 2;
		var nextY = y + dir._2 * 2;
		if (isInBounds(nextX,nextY,w,h)) {
			if (!visited.check(nextX,nextY))
			{
				return true
			}
		}
		false
	}
	def crawl(maze:bitfield2d, startX:Int, startY:Int, w:Int, h:Int):Unit = {
		val stack = Stack[(Int,Int)]()
		var x = startX;
		var y = startY;
		stack.push((x,y))
		while (stack.length > 0) {
			var dir = pickDirection();
			if (!canGo(maze,dir,x,y,w,h)) dir = pickDirection();	
			if (!canGo(maze,dir,x,y,w,h)) dir = pickDirection();	
			if (!canGo(maze,dir,x,y,w,h)) dir = pickDirection();	
			if (canGo(maze,dir,x,y,w,h)) {
				val nx = x+dir._1;
				val ny = y+dir._2;
				maze.set(nx,ny);
				val nx2 = x+(2*dir._1)
				val ny2 = y+(2*dir._2)
				stack.push((nx2,ny2));
				maze.set(nx2,ny2);
				x = nx2;
				y = ny2;
			}
			else
			{
				val prev = stack.pop();
				x = prev._1;
				y = prev._2;
			}
		}
	}

	def printMaze(maze:bitfield2d, w:Int, h:Int):Unit = {
		for (y <- 0 until h) {
			var line:String = ""
			for (x <- 0 until w ) {
				if (maze.check(x,y)) line +="." else line += "#"
			}
			println(line)
		}
	}

	def main(args: Array[String]) = {
		val w = 51
		val h = 51

		val maze= new bitfield2d(w,h);

		crawl(maze,w/2,h/2,w,h)

		printMaze(maze,w,h)

	}
}
