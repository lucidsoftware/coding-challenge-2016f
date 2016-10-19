import java.util.Scanner
import java.util.ArrayList
import scala.collection.mutable.Stack

object RatRace {

	class Maze(cols:Int, rows:Int)  {
		var maze = Array.ofDim[Boolean](cols,rows);
		var curRow:Int = 0;

		class Point(x:Int, y:Int) {
			def X():Int = {return x;}
			def Y():Int = {return y;}
			def add(p:Point):Point = { new Point(x+p.X,y+p.Y) }
		}

		val up:Point = new Point(0,-1);
		val down:Point = new Point(0,1);
		val left:Point = new Point(-1,0);
		val right:Point = new Point(1,0);

		class Progress(_p:Point, _d:Int) {
			def point():Point = _p
			def distance():Int = _d
		}

		class VisitTracker(cols:Int, rows:Int) {
			var visited = Array.ofDim[Boolean](cols,rows);

			def visit(x:Int, y:Int):Unit = visited(x)(y) = true;
			def unvisit(x:Int, y:Int):Unit = visited(x)(y) = false;
			def isVisited(p:Point):Boolean = visited(p.X)(p.Y);
		}

		def AddRow(row:String) {
			for (i <- 0 to cols-1)
			{
				maze(i)(curRow) = row.charAt(i) == '.'
			}
			curRow+=1;
		}

		def IsSpotOpen(p:Point):Boolean = { return maze(p.X)(p.Y) }
		
		def CanMove(next:Point):Boolean = { 
			if (next.X < 0 || next.X >= rows || next.Y < 0 || next.Y >= cols || !IsSpotOpen(next)) false else true
		}

		def DirectionsAvailable(p:Point):Int = {
			var dirsAvailable:Int = 0;
			if (!IsSpotOpen(p)) return 0;
			if (CanMove(p.add(up))) dirsAvailable+=1;
			if (CanMove(p.add(down))) dirsAvailable+=1;
			if (CanMove(p.add(right))) dirsAvailable+=1;
			if (CanMove(p.add(left))) dirsAvailable+=1;
			return dirsAvailable;
		}		

		def FindEnds():ArrayList[Point] = {
			var ends:ArrayList[Point] = new ArrayList[Point];
			for (x <- 0 to cols-1) {
				for (y <- 0 to rows-1) {
					val p = new Point(x,y)
					if (DirectionsAvailable(p) == 1)
					ends.add(p);
				}
			}

			return ends;
		}

		def max(a:Int, b:Int) = if (a>b) a else b

		def Traverse(v:VisitTracker, start:Point):Int = {
			val stack = Stack[Progress]()
			var done:Boolean = false
			var p:Progress = new Progress(start, 0)
			var longest = 0
			while(!done) {
				v.visit(p.point.X,p.point.Y)
				val nextUp = new Progress(p.point.add(up),p.distance+1)
				if (CanMove(nextUp.point) && !v.isVisited(nextUp.point)) { longest = max(longest,nextUp.distance); stack.push(nextUp); }
				val nextDown = new Progress(p.point.add(down),p.distance+1)
				if (CanMove(nextDown.point) && !v.isVisited(nextDown.point)) { longest = max(longest,nextDown.distance); stack.push(nextDown); }
				val nextLeft = new Progress(p.point.add(left),p.distance+1)
				if (CanMove(nextLeft.point) && !v.isVisited(nextLeft.point)) { longest = max(longest,nextLeft.distance); stack.push(nextLeft); }
				val nextRight = new Progress(p.point.add(right),p.distance+1)
				if (CanMove(nextRight.point) && !v.isVisited(nextRight.point)) { longest = max(longest,nextRight.distance); stack.push(nextRight); }
				if (stack.length > 0) { p = stack.pop() } else {done = true}
			}
			return longest
		}
		def FindLongestPath():Int = {
			val ends = FindEnds()
			var longest:Int = 0;
			for (i <- 0 to ends.size()-2) {
				val v:VisitTracker = new VisitTracker(cols,rows);
				longest = max(longest, Traverse(v,ends.get(i)));
			}
			return longest;
		}
	}
	
	def main(args: Array[String]) = {
		val scanner = new Scanner(System.in)
		val numMazes = scanner.nextInt();
		for (maze <- 1 to numMazes) {
			val cols = scanner.nextInt();
			val rows = scanner.nextInt();
			scanner.nextLine();
			val maze:Maze = new Maze(cols,rows);
			for (row <- 1 to rows) {
				maze.AddRow(scanner.nextLine());
			}
			val longestPath:Int = maze.FindLongestPath();
			println("Maximum path length is " + longestPath);
		}
		
	}
}