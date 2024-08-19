package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.StringTokenizer;

import Server.IStudent;
import Server.Student;

public class Client {
	public static void main(String[] args) throws NotBoundException, IOException {
		Registry reg = LocateRegistry.getRegistry(1234);
		IStudent server = (IStudent) reg.lookup("STUDENT");
		BufferedReader userIn = new BufferedReader(new InputStreamReader(System.in));
		boolean isLogin = false;
		String comm, param;
		boolean check;
		String lastUserName = "";
		String line;
		while (!isLogin) {
			line = userIn.readLine();
			StringTokenizer st = new StringTokenizer(line);
			comm = st.nextToken();
			if ("QUIT".equalsIgnoreCase(comm))
				break;
			param = st.nextToken();
			if ("USER".equalsIgnoreCase(comm)) {
				check = server.checkUserName(param);
				if (check) {
					lastUserName = param;
					System.out.println("OK");
				} else {
					System.out.println("USER " + param + " NOT EXIST");
				}
			} else if ("PASS".equalsIgnoreCase(comm)) {
				if (lastUserName == null) {
					System.out.println("ENTER USERNAME FIRST");
				} else {
					check = server.checkLogin(lastUserName, param);
					if (check) {
						System.out.println("LOGIN SUCCESS");
						isLogin = true;
					} else {
						System.out.println("WRONG PASSWORD");
					}
				}
			} else {
				System.out.println("WRONG SYNTAX, PLEASE DO AGAIN");
			}
		}
		while (isLogin) {
			line = userIn.readLine();
			StringTokenizer st = new StringTokenizer(line);
			comm = st.nextToken();
			if ("QUIT".equalsIgnoreCase(comm))
				break;

			param = st.nextToken();
			if ("NAME".equalsIgnoreCase(comm)) {
				List<Student> listFound = server.findByName(param);
				if (listFound.isEmpty())
					System.out.println("Khong Tim thay sinh vien");
				else {
					for (Student student : listFound) {
						System.out.println(student.toString() + "\n");
					}
				}
			} else if ("SCORE".equalsIgnoreCase(comm)) {
				List<Student> listFound = server.findByScore(Double.parseDouble(param));
				if (listFound.isEmpty())
					System.out.println("Khong co sinh vien nao");
				else {
					for (Student student : listFound) {
						System.out.println(student.toString() + "\n");
					}
				}
			}
		}

	}

}
