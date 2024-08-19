package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

import Server.IStudent;
import Server.Student;

public class Client {
	public static void main(String[] args) throws NotBoundException, IOException {
		Registry reg = LocateRegistry.getRegistry(1234);
		IStudent server = (IStudent) reg.lookup("STUDENT");

		BufferedReader userIn = new BufferedReader(new InputStreamReader(System.in));
		String line;
		String comm, param;
		while (true) {
			System.out.println("Welcome...");
			line = userIn.readLine();
			StringTokenizer st = new StringTokenizer(line);
			if (st.hasMoreTokens()) {
				comm = st.nextToken();
				if ("QUIT".equalsIgnoreCase(comm))
					break;
				if ("ADD".equalsIgnoreCase(comm)) {
					if (st.countTokens() == 4) {
						try {
							int id = Integer.parseInt(st.nextToken());
							String name = st.nextToken();
							int year = Integer.parseInt(st.nextToken());
							double score = Double.parseDouble(st.nextToken());
							Student student = new Student(id, name, year, score);
							int changed = server.addStudent(student);
							if (changed > 0)
								System.out.println("Da them sinh vien " + id + " vao CSDL");
							else {
								System.out.println("KO THE");
							}
						} catch (NoSuchElementException e) {
							throw new RemoteException("thieu phan tu");
						} catch (NumberFormatException e) {
							throw new RemoteException("Sai du lieu");
						}
					} else {
						System.out.println("Lenh khong hop le. Phai co 4 tham so");
					}
				}
				if ("DELETE".equalsIgnoreCase(comm)) {
					param = st.nextToken();
					int id = Integer.parseInt(param);
					int check = server.deleteStudent(id);
					if (check > 0) {
						System.out.println("Da xoa");
					} else {
						System.out.println("Khong co sinh vien ");
					}
				}
				if ("LIST".equalsIgnoreCase(comm)) {
					System.out.println(server.showList());
				}

				else {
					System.out.println("Lenh khong hop le");
				}
			} else {
				System.out.println("Khong co lenh nao duoc nhap");
			}
		}
	}

}
