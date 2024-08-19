package Server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IStudent extends Remote {
	public boolean checkUserName(String userName) throws RemoteException;

	public boolean checkLogin(String userName, String pass) throws RemoteException;

	public List<Student> findByName(String name) throws RemoteException;

	public List<Student> findByScore(double score) throws RemoteException;

}
