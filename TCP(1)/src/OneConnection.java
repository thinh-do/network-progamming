import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;
import java.util.StringTokenizer;

public class OneConnection extends Thread {
	private Socket socket;
	private Dao dao;
	PrintWriter netOut;
	BufferedReader netIn;

	public OneConnection(Socket socket, Dao dao) throws IOException {
		this.socket = socket;
		this.dao = dao;
		netIn = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
		netOut = new PrintWriter(socket.getOutputStream(), true);
		
	}

	public void run() {
		String comm, param;
		String lastUserName = "";
		boolean isLogin = false;
		boolean check;
		String line;
		String res;
		try {
			while (!isLogin) {
				line = netIn.readLine();
				StringTokenizer st = new StringTokenizer(line);
				comm = st.nextToken();
				if ("QUIT".equalsIgnoreCase(comm))
					break;
				param = st.nextToken();
				if ("USER".equalsIgnoreCase(comm)) {
					check = dao.checkUserName(param);
					if (check) {
						lastUserName = param;
						res = "OK";
					} else {
						res = "User " + param + " khong ton tai";
					}
				} else if ("PASS".equalsIgnoreCase(comm)) {
					check = dao.checkLogin(lastUserName, param);
					if (check) {
						res = "LOGIN thanh cong";
						isLogin = true;
					} else {
						res = "Sai mat khau";
					}
				} else {
					res = "Sai cu phap";
				}
				netOut.println(res);
				netOut.println(isLogin);

			}

			while (isLogin) {
				res = "";
				line = netIn.readLine();
				StringTokenizer st = new StringTokenizer(line);
				comm = st.nextToken();
				if ("QUIT".equalsIgnoreCase(comm))
					break;
				param = st.nextToken();
				if ("NAME".equalsIgnoreCase(comm)) {
					List<Student> listFound = dao.findByName(param);
					if (listFound.isEmpty()) {
						res = "Khong tim thay";
					} else {
						for (Student student : listFound) {
							res += student.toString() + "|";
						}

					}
				} else if ("SCORE".equalsIgnoreCase(comm)) {
					List<Student> listFound = dao.findByScore(param);
					if (listFound.isEmpty()) {
						res = "Khong tim thay";
					} else {
						for (Student student : listFound) {
							res += student.toString() + "|";
						}

					}
				} else {
					res = "Sai cu phap";
				}
				netOut.println(res);

			}

			socket.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
