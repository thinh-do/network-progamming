import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;

public class Server {
	public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
		ServerSocket server = new ServerSocket(1234);
		String dbFile = "D:\\WEB\\NoPainNoGain\\TCP\\data.accdb";
		while (true) {
			Socket socket = server.accept();
			Dao dao = new Dao(dbFile);
			OneConnection one = new OneConnection(socket, dao);
			one.start();
		}
	}

}
