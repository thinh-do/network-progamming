package Server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Dao {
	private Connection conn;

	public Dao(String dbFile) throws SQLException, ClassNotFoundException {
		String driverName = "net.ucanaccess.jdbc.UcanaccessDriver";
		String url = "jdbc:ucanaccess://" + dbFile;
		Class.forName(driverName);
		conn = DriverManager.getConnection(url);
	}

	public boolean checkUserName(String userName) throws SQLException {
		String sqlParam = "select * from user where username=?";
		PreparedStatement pre = conn.prepareStatement(sqlParam);
		pre.setString(1, userName);
		ResultSet rs = pre.executeQuery();
		return rs.next();
	}

	public int addStudent(Student student) throws SQLException {
		String sqlParam = "INSERT INTO student(stid, stname, styear, score) VALUES (?,?,?,?)";
		PreparedStatement pre = conn.prepareStatement(sqlParam);
		pre.setInt(1, student.getId());
		pre.setString(2, student.getName());
		pre.setInt(3, student.getYear());
		pre.setDouble(4, student.getScore());
		int res = pre.executeUpdate();
		return res;
	}

	public int deleteStudent(int id) throws SQLException {
		String sqlParam = "DELETE FROM student WHERE stid=?";
		PreparedStatement pre = conn.prepareStatement(sqlParam);
		pre.setInt(1, id);
		int res = pre.executeUpdate();
		return res;
	}

	public String showList() throws SQLException {
		String res = "";
		String sql = "select * from student";
		PreparedStatement pre = conn.prepareStatement(sql);
		ResultSet rs = pre.executeQuery();
		while (rs.next()) {
			int id = rs.getInt("stid");
			String name = rs.getString("stname");
			int year = rs.getInt("styear");
			double score = rs.getDouble("score");
			res += id + " " + name + " " + year + " " + score + "\n";
		}
		return res;
	}

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Dao dao = new Dao("D:\\WEB\\NoPainNoGain\\RMI\\data.accdb");
		dao.showList();
	}

}
