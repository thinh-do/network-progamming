package Week4;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static void main(String[] args) throws IOException {
		ServerSocket server=new ServerSocket(1022);
		while (true) {
			Socket socket=server.accept();
			OneConnection one=new OneConnection(socket);
			one.start();
		}
	}

}
