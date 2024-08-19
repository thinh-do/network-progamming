package Server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentImpl extends UnicastRemoteObject implements IStudent {
	Dao dao;

	public StudentImpl(String dbFile) throws RemoteException, ClassNotFoundException, SQLException {
		super();
		dao = new Dao(dbFile);
	}

	@Override
	public boolean checkUserName(String userName) throws RemoteException {
		try {
			String sqlParam = "select * from user where username=?";
			PreparedStatement pre = dao.conn.prepareStatement(sqlParam);
			pre.setString(1, userName);
			ResultSet rs = pre.executeQuery();
			return rs.next();
		} catch (SQLException e) {
			throw new RemoteException("SQL error");
		}
	}

	@Override
	public boolean checkLogin(String userName, String pass) throws RemoteException {
		try {
			String sqlParam = "select * from user where username=? and password=?";
			PreparedStatement pre = dao.conn.prepareStatement(sqlParam);
			pre.setString(1, userName);
			pre.setString(2, pass);
			ResultSet rs = pre.executeQuery();
			return rs.next();
		} catch (SQLException e) {
			throw new RemoteException("SQL error");
		}
	}

	@Override
	public List<Student> findByName(String name) throws RemoteException {
		try {
			List<Student> res = new ArrayList<Student>();
			String sql = "select * from student where stname like?";
			PreparedStatement pre = dao.conn.prepareStatement(sql);
			pre.setString(1, "%" + name + "%");
			ResultSet rs = pre.executeQuery();
			while (rs.next()) {
				Student st;
				int id = rs.getInt("stid");
				String namest = rs.getString("stname");
				int year = rs.getInt("styear");
				double score = rs.getDouble("score");
				st = new Student(id, namest, year, score);
				res.add(st);
			}

			return res;
		} catch (SQLException e) {
			throw new RemoteException();
		}
	}

	@Override
	public List<Student> findByScore(double score) throws RemoteException {
		try {
			List<Student> res = new ArrayList<>();
			String sql = "select * from student where score >= ?";
			PreparedStatement pre = dao.conn.prepareStatement(sql);
			pre.setDouble(1, score);
			ResultSet rs = pre.executeQuery();
			while (rs.next()) {
				Student st;
				int id = rs.getInt("stid");
				String name = rs.getString("stname");
				int year = rs.getInt("styear");
				double scorest = rs.getDouble("score");
				st = new Student(id, name, year, scorest);
				res.add(st);
			}
			return res;
		} catch (SQLException e) {
			throw new RemoteException("SQL error");
		}
	}

}
