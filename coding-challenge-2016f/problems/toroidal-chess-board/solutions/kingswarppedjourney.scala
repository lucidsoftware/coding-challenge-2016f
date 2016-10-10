import java.util.Scanner


object KingsWarppedJourney {

	def abs(v:Int): Int = {
		if (v<0) -v else v;
	}
	def min(a:Int, b:Int): Int = {
		if (a<b) a else b
	}

	def minDistance(i1: Int, i2: Int): Int = {
		val d1 = abs(i1-i2) 
		val d2 = abs(i1 - (i2-8))
		val d3 = abs(i1 - (i2+8))
		min(d1, min (d2, d3))
	}

	def main(args: Array[String]) = {
		val scanner = new Scanner(System.in)
		val count = scanner.nextInt()
		for (i <- 1 to count) {
			val x1 = scanner.nextInt()
			val y1 = scanner.nextInt()
			val x2 = scanner.nextInt()
			val y2 = scanner.nextInt()

			val minX = minDistance(x1,x2)
			val minY = minDistance(y1,y2)

			val result = minX max minY;
			println(result);
		}
	}
}