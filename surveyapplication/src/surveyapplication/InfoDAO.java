package surveyapplication;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InfoDAO {
	private JdbcTemplate jdbcTemplate;

	public InfoDAO() {
		System.out.println("ȸ���� ������ �����Դϴ�. �α��� / ȸ�������� �������ּ���.");
		System.out.println("1. �α���");
		System.out.println("2. ȸ������");
	}

	public boolean signUp(String name, String pswd, int age) {
		if (isExist(name, pswd))
			return false;
		jdbcTemplate = JdbcTemplate.getInstance();
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "insert into \"INFO\" values (\"SEQ_INFO\".nextval, ?, ?, \"AGE_RANGE\"(?))";
		boolean result = false;
		try {
			conn = jdbcTemplate.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, pswd);
			pstmt.setInt(3, age);

			if (pstmt.executeUpdate() == 1)
				result = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			jdbcTemplate.close(pstmt);
			jdbcTemplate.close(conn);
		}
		return result;
	}

	// ȸ������ �� �̸�, ��й�ȣ�� ������ ȸ���� �����ϸ� false ��ȯ
	public boolean isExist(String name, String pwd) {
		jdbcTemplate = JdbcTemplate.getInstance();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean exist = false;
		String sql = "select * from \"INFO\" where \"NAME\" = ? and \"PSWD\" = ?";

		try {
			conn = jdbcTemplate.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, pwd);
			rs = pstmt.executeQuery();

			if (rs.next())
				exist = true;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			jdbcTemplate.close(pstmt);
			jdbcTemplate.close(conn);
		}
		return exist;
	}

	//
	public int login(String name, String pwd) {
		jdbcTemplate = JdbcTemplate.getInstance();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select \"INFO_NUMBER\" from \"INFO\" where name = ? and pswd = ?";
		int result = 0;

		try {
			conn = jdbcTemplate.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, pwd);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			jdbcTemplate.close(rs);
			jdbcTemplate.close(pstmt);
			jdbcTemplate.close(conn);
		}
		return result;
	}
}
