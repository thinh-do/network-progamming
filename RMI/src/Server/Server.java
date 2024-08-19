package Server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.SQLException;

public class Server {
	public static void main(String[] args) throws RemoteException, ClassNotFoundException, SQLException {
		Registry reg = LocateRegistry.createRegistry(1234);
		String dbFile = "D:\\WEB\\NoPainNoGain\\RMI\\data.accdb";
		StudentImpl student = new StudentImpl(dbFile);
		reg.rebind("STUDENT", student);
	}

}
