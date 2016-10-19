import java.util.Scanner
import java.util.ArrayList
import scala.collection.mutable.Stack

object PowerOfN {

	def test(k:Int, n:Int):Boolean = {
		if (k == 1 && n == 1) return true;
		else if (k > 1 && n == 1) return false;
		var accum = 1;
		while (accum <= k) {
			if (accum == k) {
				return true;
			}
			accum *= n;
		}
		false
	}

	def main(args: Array[String]) = {
		val scanner = new Scanner(System.in)
		val k = scanner.nextInt();
		val n = scanner.nextInt();
		val result = test(k,n);
		println( if (result) "YES" else "NO" ) 
	}
}