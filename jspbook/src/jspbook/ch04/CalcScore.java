package jspbook.ch04;

public class CalcScore {
	int[] calculate(int lang, int eng, int math) {
		int[] result = new int[2];
		
		result[0] = lang+eng+math;
		result[1] = result[0]/3;
		
		return result;
		
		
	}
}
