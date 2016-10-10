import java.util.Scanner
import java.util.ArrayList



object SimpleVM {

	class Line(text:String) {
		val parts = text.split(" ")
		val lineNum = Integer.parseInt(parts(0));
		val instruction = parts(1);
		val op1 = if (parts.size >2) parts(2) else "";
		val op2 = if (parts.size >3) parts(3) else "";
		//println("line:" + lineNum + " inst:" + instruction + " op1:" + op1 + " op2:" + op2)

		override def toString = text	
	}

	val lines:ArrayList[Line] = new ArrayList[Line];
	var regs = Array(0,0,0,0);
	var CR = "=";
	var IP = 0;
	var done = false;
	var output:String = "";

	def printStatus() {
		//println("r0:"+regs(0) + " r1:"+regs(1) + " r2:" + regs(2) + " r3:" + regs(3) + " CR:" + CR + " IP:" + IP + " output: " + output);
	}
	def ParseConstant(c:String):Int = {
		if (c.charAt(0) == '\'') c.charAt(1).toInt
		else Integer.parseInt(c)
	}
	def ParseRegister(r:String):Int = {
		if (r.charAt(0) != 'R') throw new IllegalArgumentException;
		(r.charAt(2).toInt - 0x30);
	}
	def ExecuteLine(lineNum:Int) : Int = {
		if (lineNum >= lines.size()) {
			throw new IllegalArgumentException;
		}
		val line = lines.get(lineNum);
		//print(line + " "+line.instruction + " " + line.op1 + " " + line.op2 + " >>> ");

		line.instruction match {
			case "END" =>  {done=true; return -1}
			case "SET" => {
				val regIndex = ParseRegister(line.op1)
				val value = ParseConstant(line.op2);
				regs(regIndex) = value;
			}
			case "ADD" => {
				val r1Index = ParseRegister(line.op1)
				val r2Index = ParseRegister(line.op2)
				regs(r1Index) = (regs(r1Index) + regs(r2Index)) & 0xFF;
			}
			case "CMP" => {
				val r1Index = ParseRegister(line.op1)
				val r2Index = ParseRegister(line.op2)
				if (regs(r1Index) > regs(r2Index)) CR = ">"
				else if (regs(r1Index) < regs(r2Index)) CR = "<"
				else CR = "="
			}
			case "JMP" => {
				return ParseConstant(line.op1);
			}
			case "JIF" => {
				var shouldJump = false;
				line.op1 match {
					case "GRE" => shouldJump = CR == ">";
					case "LES" => shouldJump = CR == "<";
					case "EQU" => shouldJump = CR == "=";
					case "GRQ" => shouldJump = CR != "<";
					case "LEQ" => shouldJump = CR != ">";
					case "NEQ" => shouldJump = CR != "=";
					case ooops => throw new IllegalArgumentException;
				}
				if (shouldJump)
				{
					return ParseConstant(line.op2);
				}
			}
			case "OUT" => {
				val r1Index = ParseRegister(line.op1);
				output = output + regs(r1Index).toChar;
			}
			case ooops => throw new IllegalArgumentException;
		}
		return lineNum + 1;
	}
	def main(args: Array[String]) = {
		val scanner = new Scanner(System.in)
		while (scanner.hasNext()) {
			val next = scanner.nextLine();
			lines.add(new Line(next));
		}
		
		while (!done)
		{
			IP = ExecuteLine(IP);
			printStatus();
		}
		println(output);
		
	}
}