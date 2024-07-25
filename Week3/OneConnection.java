package Week3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class OneConnection extends Thread {
	private Socket socket;

	public OneConnection(Socket socket) {
		this.socket = socket;
	}

	public void run() {
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			out.println("Welcome.....");
			String line;
			while (true) {
				line = in.readLine();
				if ("QUIT".equalsIgnoreCase(line)) {
					out.println("Have a good day");
					socket.close();
				}
				line = "Echo: " + line;
				out.println(line);

			}

		} catch (Exception e) {
			System.err.println(e);
		}

	}

}
