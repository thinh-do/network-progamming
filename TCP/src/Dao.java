import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Dao {
	private Connection conn;

	public Dao(String dbFile) throws ClassNotFoundException, SQLException {
		String driverName = "net.ucanaccess.jdbc.UcanaccessDriver";
		String url = "jdbc:ucanaccess://" + dbFile;
		Class.forName(driverName);
		conn = DriverManager.getConnection(url);
	}

	public int addStudent(Student student) throws SQLException {
		String sql = "insert into student(stid, stname, styear, score) values (?,?,?,?)";
		PreparedStatement pre = conn.prepareStatement(sql);
		pre.setInt(1, student.getId());
		pre.setString(2, student.getName());
		pre.setInt(3, student.getYear());
		pre.setDouble(4, student.getScore());
		int rs = pre.executeUpdate();
		return rs;

	}

	public String showList() throws SQLException {
		String res = "";
		String sqlparam = "select * from student";
		PreparedStatement pre = conn.prepareStatement(sqlparam);
		ResultSet rs = pre.executeQuery();
		while (rs.next()) {
			int id = rs.getInt("stid");
			String name = rs.getString("stname");
			int year = rs.getInt("styear");
			double score = rs.getDouble("score");
			res += id + " " + name + " " + year + " " + score + "|";
		}
		return res;

	}

}
