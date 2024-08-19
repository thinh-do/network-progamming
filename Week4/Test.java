package Week4;

import java.util.NoSuchElementException;
import java.util.StringTokenizer;

public class Test {
	private static void requestAnal(String line) throws MyException {
		String temp = line + 123;
		String st1, st2;
		StringTokenizer st = new StringTokenizer(temp, "+-*/");
		try {
			String s = st.nextToken();
			String s2 = st.nextToken();
		} catch (NoSuchElementException e) {
			throw new MyException("Thieu toan tu");
		}
		st = new StringTokenizer(line, "+-*/");
		try {
			st1 = st.nextToken();
			st2 = st.nextToken();
		} catch (NoSuchElementException e) {
			throw new MyException("Thieu toan hang");
		}
		double num1 = Double.parseDouble(st1);
		double num2 = Double.parseDouble(st2);
		char operator = line.charAt(st1.length());

	}

	public static void main(String[] args) throws MyException {
		requestAnal("1111+");
	}
}
