package survey;

import java.sql.*;
import javax.naming.*;
import javax.sql.DataSource;

public class InfoDao {
	private ConnectionPool pool = null;

	public InfoDao() {
		try {
			pool = ConnectionPool.getInstance();
		} catch (Exception e) {
			System.out.println("Error: Connection load failed");
		}
	}

	public boolean idCheck(String id) {
		boolean result = true;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = pool.getConnection();
			pstmt = conn.prepareStatement("select * from \"INFO\" where \"ID\" = ?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (!rs.next())
				result = false;
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			pool.close(rs);
			pool.close(pstmt);
			if (conn != null) {
				try {
					pool.releaseConnection(conn);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}

	public boolean insertInfo(InfoDto dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean flag = false;

		try {
			conn = pool.getConnection();
			String query = "insert into \"INFO\" values (\"SEQ_INFO\".nextval, ?, ?, \"AGE_RANGE\"(?))";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPwd());
			pstmt.setInt(3, dto.getAge());
			int count = pstmt.executeUpdate();

			if (count > 0)
				flag = true;
		} catch (Exception e) {
			System.out.println("Exception " + e);
		} finally {
			pool.close(pstmt);
			if (conn != null) {
				try {
					pool.releaseConnection(conn);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return flag;
	}

	// 로그인 성공 ->
	public int login(String id, String pwd) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = -1;

		try {
			conn = pool.getConnection();
			String query = "select \"INFO_NUMBER\" from \"INFO\" where \"ID\" = ? and \"PWD\" = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = rs.getInt("INFO_NUMBER");
			}
		} catch (Exception e) {
			System.out.println("Exception " + e);
		} finally {
			pool.close(rs);
			pool.close(pstmt);
			if (conn != null) {
				try {
					pool.releaseConnection(conn);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}
}