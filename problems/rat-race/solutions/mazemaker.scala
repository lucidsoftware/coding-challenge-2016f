import scala.collection.mutable.Stack

object MaxeMaker {
	val rand = scala.util.Random
	
	class bitfield2d(w:Int, h:Int) {
		val bits = Array.ofDim[Int](((w*h)/32) + 1)
		def set(x:Int, y:Int):Unit = {
			val bit = x + (y*w);
			val bitSubIndex = bit & 0x1F;
			val intIndex = bit >> 5;
			bits(intIndex) = bits(intIndex) | (1<<bitSubIndex);
		}

		def clear(x:Int, y:Int):Unit = {
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
	def canGo(visited:bitfield2d, x:Int, y:Int, w:Int, h:Int, allowZero:Boolean):Boolean = {
		val dirs = List[(Int,Int)]((1,0),(-1,0),(0,1),(0,-1))
		val allowed = if(allowZero) Set(0,1) else Set(1)
		isInBounds(x, y, w, h) && !visited.check(x,y) && allowed.contains(dirs.count(d => {
			val nx = x + d._1
			val ny = y + d._2
			isInBounds(nx, ny, w, h) && visited.check(nx,ny)
		}))
	}
	def crawl(maze:bitfield2d, startX:Int, startY:Int, w:Int, h:Int):Unit = {
		val stack = Stack[(Int,Int)]()
		var x = startX;
		var y = startY;
		var first = true
		stack.push((x,y))
		do {
			val prev = stack.pop();
			x = prev._1;
			y = prev._2;
			if(canGo(maze,x,y,w,h,first)) {
				maze.set(x,y)
				val dirs = List[(Int,Int)]((1,0),(-1,0),(0,1),(0,-1))
				scala.util.Random.shuffle(dirs).foreach {dir =>
					var nx = x+dir._1
					var ny = y+dir._2
					if(canGo(maze,nx,ny,w,h,false)) {
						stack.push((nx,ny))
					}
				}
			}
			first = false
		} while (stack.length > 0)
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
		val cases = 1
		println(cases)

		0 until cases foreach {_ =>
			val w = 100//rand.nextInt(98)+3
			val h = 100//rand.nextInt(98)+3

			println(w + " " + h)

			val maze= new bitfield2d(w,h);

			crawl(maze,w/2,h/2,w,h)

			printMaze(maze,w,h)
			
		}


	}
}
