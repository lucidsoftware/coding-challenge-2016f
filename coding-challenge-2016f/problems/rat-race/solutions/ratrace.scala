import java.util.Scanner
import java.util.ArrayList

object RatRace {

	class VisitTracker(cols:Int, rows:Int) {
		var visited = Array.ofDim[Boolean](cols,rows);

		def visit(x:Int, y:Int):Unit = visited(x)(y) = true;
		def unvisit(x:Int, y:Int):Unit = visited(x)(y) = false;
		def isVisited(x:Int, y:Int):Boolean = visited(x)(y);
	}
	class Maze(cols:Int, rows:Int)  {
		var maze = Array.ofDim[Boolean](cols,rows);
		var curRow:Int = 0;

		class Point(x:Int, y:Int) {
			def X():Int = {return x;}
			def Y():Int = {return y;}

		}

		def AddRow(row:String) {
			for (i <- 0 to cols-1)
			{
				maze(i)(curRow) = row.charAt(i) == '.'
			}
			curRow+=1;
		}
		override def toString = cols + "x" + rows ;

		def IsSpotOpen(x:Int, y:Int):Boolean = { return maze(x)(y) }
		def CanGoUp(x:Int, y:Int):Boolean = { if (y>0 && IsSpotOpen(x,y-1)) true else false }
		def CanGoDown(x:Int, y:Int):Boolean = { if (y<rows-1 && IsSpotOpen(x,y+1)) true else false }
		def CanGoLeft(x:Int, y:Int):Boolean = { if (x>0 && IsSpotOpen(x-1,y)) true else false }
		def CanGoRight(x:Int, y:Int):Boolean = { if (x<cols-1 && IsSpotOpen(x+1,y)) true else false }
		def DirectionsAvailable(x:Int, y:Int):Int = {
			var dirsAvailable:Int = 0;
			if (!IsSpotOpen(x,y)) return 0;
			if (CanGoUp(x,y)) dirsAvailable+=1;
			if (CanGoDown(x,y)) dirsAvailable+=1;
			if (CanGoRight(x,y)) dirsAvailable+=1;
			if (CanGoLeft(x,y)) dirsAvailable+=1;
			return dirsAvailable;
		}		

		def FindEnds():ArrayList[Point] = {
			var ends:ArrayList[Point] = new ArrayList[Point];
			for (x <- 0 to cols-1) {
				for (y <- 0 to rows-1) {
					if (DirectionsAvailable(x,y) == 1)
					ends.add(new Point(x,y));
				}
			}

			return ends;
		}

		def max(a:Int, b:Int) = if (a>b) a else b

		def Traverse(v:VisitTracker, p:Point,lengthSoFar:Int):Int = {
			v.visit(p.X,p.Y)
			var longest:Int = lengthSoFar;
			if (CanGoUp(p.X,p.Y) && !v.isVisited(p.X,p.Y-1)) { longest = max(longest,Traverse(v,new Point(p.X,p.Y-1),lengthSoFar+1));}
			if (CanGoDown(p.X,p.Y) && !v.isVisited(p.X,p.Y+1)) { longest = max(longest,Traverse(v,new Point(p.X,p.Y+1),lengthSoFar+1));}
			if (CanGoLeft(p.X,p.Y) && !v.isVisited(p.X-1,p.Y)) { longest = max(longest,Traverse(v,new Point(p.X-1,p.Y),lengthSoFar+1));}
			if (CanGoRight(p.X,p.Y) && !v.isVisited(p.X+1,p.Y)) { longest = max(longest,Traverse(v,new Point(p.X+1,p.Y),lengthSoFar+1));}
			return longest
		}
		def FindLongestPath():Int = {
			val ends = FindEnds()
			var longest:Int = 0;
			for (i <- 0 to ends.size()-2) {
				val v:VisitTracker = new VisitTracker(cols,rows);
				longest = max(longest, Traverse(v,ends.get(i),0));
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