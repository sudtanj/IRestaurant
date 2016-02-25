package uph;
import java.util.Random;

public class Spesial {
	public static int advancedrandom (int seed,int batas){
		return (new Random().nextInt(batas)*seed+(int)System.currentTimeMillis())%batas+1;
	}
	public static float advancedcalculator(String input){
		String[] parts=input.split("");
		String[] numbers = new String[input.length()];
		numbers[0]="";
		Integer[] index=new Integer[input.length()];
		int index1=0;
		for (int i=0;i<input.length();i++){
		if (numbers[index1]==null){
			numbers[index1]="";
		}
		if (parts[i].equals("*")){
			index1++;
			index[index1]=2;
		}
		else if (parts[i].equals("-")){
			index1++;
			index[index1]=1;
		}
		else if (parts[i].equals("+")){
			index1++;
			index[index1]=0;
		}
		else if (parts[i].equals("/")){
			index1++;
			index[index1]=3;
		}
		else if (parts[i].equals("%")){
			index1++;
			index[index1]=4;
		}
		else {
			numbers[index1]=numbers[index1]+parts[i];
		}
		}
		index1++;
		int hasil=0;
		for (int i=0;i<index1; i++){
			if (hasil==0){
				hasil=Integer.valueOf(numbers[i]);
			}
			else {
				if (index[i]==0)
					hasil=hasil+Integer.valueOf(numbers[i]);
				else if (index[i]==1)
					hasil=hasil-Integer.valueOf(numbers[i]);
				else if (index[i]==2)
					hasil=hasil*Integer.valueOf(numbers[i]);
				else if (index[i]==3)
					hasil=hasil/Integer.valueOf(numbers[i]);
				else if (index[i]==4)
					hasil=hasil%Integer.valueOf(numbers[i]);
			}
		}
		return hasil;
	}
}
