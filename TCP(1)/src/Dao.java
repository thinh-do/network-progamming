import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Dao {
	private Connection conn;

	public Dao(String dbFile) throws ClassNotFoundException, SQLException {
		String driverName = "net.ucanaccess.jdbc.UcanaccessDriver";
		String url = "jdbc:ucanaccess://" + dbFile;
		Class.forName(driverName);
		conn = DriverManager.getConnection(url);
	}

	public boolean checkUserName(String param) throws SQLException {
		String sqlParam = "select * from user where username=?";
		PreparedStatement pre = conn.prepareStatement(sqlParam);
		pre.setString(1, param);
		ResultSet rs = pre.executeQuery();
		return rs.next();
	}

	public boolean checkLogin(String lastUserName, String param) throws SQLException {
		String sqlParam = "select * from user where username=? and password=?";
		PreparedStatement pre = conn.prepareStatement(sqlParam);
		pre.setString(1, lastUserName);
		pre.setString(2, param);
		ResultSet rs = pre.executeQuery();
		return rs.next();
	}

	public List<Student> findByName(String param) throws SQLException {
		List<Student> res = new ArrayList<Student>();
		String sqlParam = "select * from student where stname like?";
		PreparedStatement pre = conn.prepareStatement(sqlParam);
		pre.setString(1, "%" + param + "%");
		ResultSet rs = pre.executeQuery();
		while (rs.next()) {
			int id = rs.getInt("stid");
			String name = rs.getString("stname");
			int year = rs.getInt("styear");
			double score = rs.getDouble("score");
			Student st = new Student(id, name, year, score);
			res.add(st);
		}
		return res;
	}

	public List<Student> findByScore(String param) throws SQLException {
		List<Student> res = new ArrayList<Student>();
		String sqlParam = "select * from student where score >=?";
		PreparedStatement pre = conn.prepareStatement(sqlParam);
		pre.setDouble(1, Double.parseDouble(param));
		ResultSet rs = pre.executeQuery();
		while (rs.next()) {
			int id = rs.getInt("stid");
			String name = rs.getString("stname");
			int year = rs.getInt("styear");
			double score = rs.getDouble("score");
			Student st = new Student(id, name, year, score);
			res.add(st);
		}
		return res;

	}

}
