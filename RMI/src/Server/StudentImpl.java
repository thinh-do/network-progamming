package Server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class StudentImpl extends UnicastRemoteObject implements IStudent {
	private Dao dao;

	public StudentImpl(String dbFile) throws RemoteException, ClassNotFoundException, SQLException {
		super();
		dao = new Dao(dbFile);

	}

	@Override
	public boolean checkUser(String userName) throws RemoteException {
		try {
			return dao.checkUserName(userName);
		} catch (SQLException e) {
			throw new RemoteException("SQL exception");
		}
	}

	@Override
	public boolean checkLogin(String userName, String pass) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Student> findByName(String name) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Student> findByScore(double score) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int addStudent(Student student) throws RemoteException {
		try {
			return dao.addStudent(student);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RemoteException("SQL error");
		}
	}

	@Override
	public int deleteStudent(int id) throws RemoteException {
		try {
			return dao.deleteStudent(id);
		} catch (SQLException e) {
			throw new RemoteException("SQL error");
		}
	}

	@Override
	public String showList() throws RemoteException {
		try {
			return dao.showList();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RemoteException("SQL error");
		}

	}

}
