package junitLecTest;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import junitLecPackage.Calculator;

public class CalculatorTest {
	private Calculator calc = null;
	
	@Before //각각의 testmethod 실행 전 실행됨
	public void beforeTest() { 
		calc = new Calculator();
		System.out.println("beforeTest()");
	}
	
	@Test
	public void  addTest() {
		assertEquals(30,calc.add(10, 20));
		System.out.println("addTest()");
	}
	
	@Test
	public void subTest() {
		assertEquals(-10,calc.sub(10, 20));	
		System.out.println("subTest()");
		
	}
	
	@Test
	public void mulTest() {
		assertEquals(200,calc.mul(10, 20));	
		System.out.println("mulTest()");
		
	}
	
	@Test //실수 결과가 나오는 경우 : 허용오차범위 arg 필요(=arg 총 3개)
	public void divTest() {
		assertEquals(0.5,calc.div(10.0, 20.0),0.00001);	
		System.out.println("divTest()");
		
	}
	
	@After
	public void afterTest() {
		System.out.println("afterTest()");
	}

}
