package De2_Cau2_TCP;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class Server {
	static List<Student> list = (new ListStudent().importList());

	public static void main(String[] args) {
		try {
			ServerSocket server = new ServerSocket(38);
			System.out.println("Server is running...");
			Socket socket = server.accept();
			System.out.println("Connected");

			DataInputStream dis = new DataInputStream(socket.getInputStream());
			DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

			do {
				int x = dis.readInt();
				switch (x) {
				case 1:
					for (Student st : list) {
						dos.writeInt(list.size());
						dos.writeUTF(st.getName());
						dos.writeUTF(st.getdOB());
						dos.writeInt(st.getId());
						dos.writeUTF(st.getBirthPlace());
						dos.flush();
					}
					dos.writeUTF("null");
				case 2:
					Student st = new Student(dis.readUTF(), dis.readUTF(), dis.readInt(), dis.readUTF());
					list.add(st);
				case 3:
					int mssv = dis.readInt();
					for (Student student : list) {
						if (student.getId() == mssv) {
							dos.writeUTF(student.getName());
							dos.writeUTF(student.getdOB());
							dos.writeInt(student.getId());
							dos.writeUTF(student.getBirthPlace());
							dos.flush();
						}

					}
				case 4:
					int y = dis.readInt();
					switch (y) {
					case 1:
						String year = dis.readUTF();
						List<Student> stRes = new ListStudent().findByYear(year);
						dos.writeInt(stRes.size());

						for (int i = 0; i < stRes.size(); i++) {
							for (Student student : stRes) {

								dos.writeUTF(student.getName());
								dos.writeUTF(student.getdOB());
								dos.writeInt(student.getId());
								dos.writeUTF(student.getBirthPlace());
								dos.flush();
							}
						}
						break;

					default:
						break;
					}

				default:
					break;
				}
//				if(x==1) {
//					for(Student st:list) {
//						dos.writeInt(list.size());
//						dos.writeUTF(st.getName());
//						dos.writeUTF(st.getdOB());
//						dos.writeInt(st.getId());
//						dos.writeUTF(st.getBirthPlace());
//						dos.flush();
//					}
//				}else if(x==2) {
//					Student st=new Student(dis.readUTF(), dis.readUTF(), dis.readInt(), dis.readUTF());
//					System.out.println(st.toString());
//					list.add(st);
//					
//				}

			} while (true);

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
