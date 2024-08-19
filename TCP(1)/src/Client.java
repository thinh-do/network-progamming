import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.StringTokenizer;

public class Client {
	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket socket = new Socket("127.0.0.1", 1234);
		PrintWriter netOut = new PrintWriter(socket.getOutputStream(), true);
		BufferedReader netIn = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
		BufferedReader userIn = new BufferedReader(new InputStreamReader(System.in));
		String comm, param;
		String lastUserName = "";
		boolean isLogin = false;
		boolean check;
		String line;
		while (!isLogin) {
			line = userIn.readLine();
			netOut.println(line);
			StringTokenizer st = new StringTokenizer(line);
			comm = st.nextToken();
			if ("QUIT".equalsIgnoreCase(comm))
				break;
			System.out.println(netIn.readLine());
			String status = netIn.readLine();
			if (status.equalsIgnoreCase("FALSE"))
				continue;
			else {
				isLogin = true;
			}

		}
		while (isLogin) {
			line = userIn.readLine();
			netOut.println(line);
			StringTokenizer st = new StringTokenizer(line);
			comm = st.nextToken();
			if ("QUIT".equalsIgnoreCase(comm))
				break;
			System.out.println(netIn.readLine());

		}
		socket.close();

	}

}
