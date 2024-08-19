import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.StringTokenizer;

public class OneConnection extends Thread {
	private Socket socket;
	private Dao dao;
	BufferedReader netIn;
	PrintWriter netOut;

	public OneConnection(Socket socket, Dao dao) throws IOException {
		this.socket = socket;
		this.dao = dao;
		netIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		netOut = new PrintWriter(socket.getOutputStream(), true);
	}

	public void run() {
		String res = "";
		try {
			while (true) {
				String line = netIn.readLine();
				StringTokenizer st = new StringTokenizer(line);
				String comm = st.nextToken();
				if ("QUIT".equalsIgnoreCase(comm))
					break;
				if ("ADD".equalsIgnoreCase(comm)) {
					if (st.hasMoreTokens()) {
						int id = Integer.parseInt(st.nextToken());
						String name = st.nextToken();
						int year = Integer.parseInt(st.nextToken());
						double score = Double.parseDouble(st.nextToken());
						Student student = new Student(id, name, year, score);
						int row = dao.addStudent(student);
						if (row > 0) {
							res = "Da them sinh vien " + name + " vao CSDL";
						} else {
							res = "Khong the them";
						}
					} else {
						res = "Thieu thong tin sinh vien";
					}
				} else if ("SHOW".equalsIgnoreCase(comm)) {
					String list = dao.showList();
					res = list;
				}

			}
			netOut.println(res);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
