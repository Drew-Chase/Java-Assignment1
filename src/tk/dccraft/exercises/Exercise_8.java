package tk.dccraft.exercises;

import tk.dccraft.init.Main;

@SuppressWarnings("all")
public class Exercise_8  extends Main{
	String part1 = 
	"colors[0] = \"Red\"\n"+
	"colors[2] = \"Blue\"\n"+
	"colors[\"Black\"] = ERROR TypeMismatchException\n"+
	"colors.length = 5\n"+
	"colors[4] = \"Gold\" | \"Orange\" --> \"Gold\"\n"+
	"colors[5] = ArrayIndexOutOfBoundsException\n"+
	"colors[1] = 35 --> TypeMismatchException\n";
	
	public Exercise_8() {
		print(part1, 2);
		int[] values = new int[]{ 9, 16, 10, 8, 2, 3, 17, 4, 5, 20 }; 
		for(int i = values.length - 1; i >= 0; i--){
			print("Value: " + values[i]);
		}
		double sum = 0;
		for(int e : values){
			sum += (double)e;
		}
		print("and The Sum is "+ sum);
		
	}

}