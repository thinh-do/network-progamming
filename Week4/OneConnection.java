package Week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

public class OneConnection extends Thread {
	private Socket socket;
	PrintWriter netOut;
	BufferedReader netIn;
	double num1, num2;
	char operator;

	public OneConnection(Socket socket) throws IOException {
		this.socket = socket;
		this.netOut = new PrintWriter(socket.getOutputStream(), true);
		this.netIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	}

	public void run() {
		String line;
		try {
			netOut.println("Welcome....");
			while (true) {
				line = netIn.readLine();
				if ("QUIT".equalsIgnoreCase(line))
					break;
				requestAnal(line);
				line += " = " + request();
				netOut.println(line);

			}
			socket.close();

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	private double request() {
		double res = 0;
		switch (this.operator) {
		case '+':
			res = this.num1 + this.num2;
			break;
		case '-':
			res = this.num1 - this.num2;
			break;
		case '*':
			res = this.num1 * this.num2;
			break;
		case '/':
			res = this.num1 / this.num2;
			break;

		default:
			break;
		}

		return res;
	}

	private void requestAnal(String line) throws MyException {
		String temp = line + 123;
		String st1, st2;
		StringTokenizer st = new StringTokenizer(temp, "+-*/");
		try {
			st.nextElement();
			st.nextToken();
		} catch (NoSuchElementException e) {
			throw new MyException("Thieu toan hang");
		}
		st = new StringTokenizer(line, "+-*/");
		try {
			st1 = st.nextToken();
			st2 = st.nextToken();
		} catch (NoSuchElementException e) {
			throw new MyException("Lenh khong hop le");
		}
		this.num1 = Double.parseDouble(st1);
		this.num2 = Double.parseDouble(st2);
		this.operator = line.charAt(st1.length());

	}

	public static void main(String[] args) throws IOException, MyException {
	}

}
