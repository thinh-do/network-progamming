package De2_Cau2_TCP;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Client {
	public static void printMenu() {
		System.out.println("1. Xem danh sách sinh viên");
		System.out.println("2. Thêm sinh viên mới");
		System.out.println("3. Xem thông tin sinh viên");
		System.out.println("4. Tìm sinh viên");

	}

	public static void main(String[] args) {
		try {
			Socket socket = new Socket("localhost", 38);
			DataInputStream dis = new DataInputStream(socket.getInputStream());
			DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

			int x;
			do {

				Scanner sc = new Scanner(System.in);
				printMenu();

				x = sc.nextInt();
				dos.writeInt(x);
				switch (x) {
				case 1:
					System.out.println("Danh sách sinh viên");
					for (int i = 0; i < dis.readInt(); i++) {
						Student st = new Student(dis.readUTF(), dis.readUTF(), dis.readInt(), dis.readUTF());
						System.out.println(st.toString());
					}
					continue;

				case 2:
					System.out.println("Nhập thông tin sinh viên");
					sc.nextLine();
					System.out.println("Tên sinh viên: ");
					String name = sc.nextLine();
					System.out.println("Ngày sinh: ");
					String dOB = sc.nextLine();
					System.out.println("MSSV: ");
					int id = sc.nextInt();
					System.out.println("Quê quán: ");
					sc.nextLine();
					String birthPlace = sc.nextLine();

					dos.writeUTF(name);
					dos.writeUTF(dOB);
					dos.writeInt(id);
					dos.writeUTF(birthPlace);

					dos.flush();
				case 3:
					System.out.println("Nhập MSSV sinh viên: ");
					int mssv = sc.nextInt();
					dos.writeInt(mssv);
					dos.flush();

					Student st = new Student(dis.readUTF(), dis.readUTF(), dis.readInt(), dis.readUTF());
					System.out.println(st.toString());
				case 4:
					System.out.println("1. Tìm theo năm sinh");
					System.out.println("2. Tìm theo quê quán");
					int y = sc.nextInt();
					dos.writeInt(y);
					dos.flush();
					switch (y) {
					case 1:
						sc.nextLine();
						System.out.println("Nhập năm sinh");
						String findYear = sc.nextLine();

						dos.writeUTF(findYear);
						dos.flush();
						
						int count=dis.readInt();
						for (int i = 0; i < count; i++) {
							Student stRes = new Student(dis.readUTF(), dis.readUTF(), dis.readInt(), dis.readUTF());
							System.out.println(stRes.toString());
						}
						
						break;

					default:
						break;
					}

				default:
					break;
				}
//				if (x == 1) {
//					System.out.println("Danh sách sinh viên");
//					for (int i = 0; i < dis.readInt(); i++) {
//						Student st = new Student(dis.readUTF(), dis.readUTF(), dis.readInt(), dis.readUTF());
//						System.out.println(st.toString());
//					}
//					printMenu();
//
//				} else if (x == 2) {
//					System.out.println("Nhập thông tin sinh viên");
//					sc.nextLine();
//					System.out.println("Tên sinh viên: ");
//					String name = sc.nextLine();
//					System.out.println("Ngày sinh: ");
//					String dOB = sc.nextLine();
//					System.out.println("MSSV: ");
//					int id = sc.nextInt();
//					System.out.println("Quê quán: ");
//					sc.nextLine();
//					String birthPlace = sc.nextLine();
//
//					dos.writeUTF(name);
//					dos.writeUTF(dOB);
//					dos.writeInt(id);
//					dos.writeUTF(birthPlace);
//
//					dos.flush();
//				}

			} while (true);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
