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
		BufferedReader netIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		PrintWriter netOut = new PrintWriter(socket.getOutputStream(), true);
		BufferedReader userIn = new BufferedReader(new InputStreamReader(System.in));

		while (true) {
			String line = userIn.readLine();
			netOut.println(line);
			StringTokenizer st = new StringTokenizer(line);
			String comm = st.nextToken();
			if ("QUIT".equalsIgnoreCase(comm))
				break;
			System.out.println(netIn.readLine());
			

		}

	}

}
