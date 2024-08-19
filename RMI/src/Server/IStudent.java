package Server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IStudent extends Remote {
	public boolean checkUser(String userName) throws RemoteException;

	public boolean checkLogin(String userName, String pass) throws RemoteException;
	
	public int addStudent(Student student) throws RemoteException;
	
	public int deleteStudent(int id) throws RemoteException ;

	public List<Student> findByName(String name) throws RemoteException;

	public List<Student> findByScore(double score) throws RemoteException;

	public String showList() throws RemoteException;

}
