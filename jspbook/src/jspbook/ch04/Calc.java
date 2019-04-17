package jspbook.ch04;

public class Calc {
	
	int calculate(int num1, int num2, String op) {
		
		int result = 0;
		
//		if(op.equals("+")) {
//			result = num1+num2;
//		} else if(op.equals("-")){
//			result = num1-num2;
//		} else if(op.equals("*")){
//			result = num1*num2;
//		} else if(op.equals("/")){
//			result = num1/num2;
//		}
//		return result;
		
		switch(op){
		case "+":
			result = num1 + num2; break;
		case "-":
			result = num1 - num2; break;
		case "*":
			result = num1 * num2; break;
		case "/":
			result = num1 / num2; break;
		}
		return result;
	}
}
