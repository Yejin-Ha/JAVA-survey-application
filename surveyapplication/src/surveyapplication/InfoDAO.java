package surveyapplication;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InfoDAO {
	private JdbcTemplate jdbcTemplate;

	public boolean signUp(String name, String pswd, int age) {
		jdbcTemplate = JdbcTemplate.getInstance();
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "insert into \"INFO\" values (\"SEQ_INFO\".nextval, ?, ?, ?)";

		try {
			conn = jdbcTemplate.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, pswd);
			pstmt.setInt(3, age);

			if (pstmt.executeUpdate() == 1)
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			jdbcTemplate.close(pstmt);
			jdbcTemplate.close(conn);
		}
		return false;
	}

	public int login(String name, String pwd) {
		jdbcTemplate = JdbcTemplate.getInstance();
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "select \"INFO_NUMBER\" from \"INFO\" where name = ? and pswd = ?";
		ResultSet rs = null;

		try {
			conn = jdbcTemplate.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, pwd);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			jdbcTemplate.close(rs);
			jdbcTemplate.close(pstmt);
			jdbcTemplate.close(conn);
		}
		return 0;
	}
}
