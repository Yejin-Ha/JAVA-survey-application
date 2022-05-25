package survey;

import java.sql.*;
import java.util.ArrayList;

import javax.naming.*;
import javax.sql.DataSource;

public class BrandDao {
	private ConnectionPool pool = null;

	public BrandDao() {
		try {
			pool = ConnectionPool.getInstance();
		} catch (Exception e) {
			System.out.println("Error: Connection load failed");
		}
	}

	public ArrayList<BrandVo> selectAll() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<BrandVo> result = new ArrayList<BrandVo>();
		try {
			conn = pool.getConnection();
			pstmt = conn.prepareStatement("select * from \"BRAND\"");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				result.add(new BrandVo(rs.getInt("BRAND_NUMBER"), rs.getString("TITLE")));
			}

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

	public String insertBrand(String brand) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = pool.getConnection();
			pstmt = conn.prepareStatement("insert into \"BRAND\" values (\"SEQ_BRAND\".nextval, ?)");
			pstmt.setString(1, brand);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
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
		return brandNum(brand);
	}

	public String brandNum(String brand) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String result = "";
		try {
			conn = pool.getConnection();
			pstmt = conn.prepareStatement("select \"BRAND_NUMBER\" from \"BRAND\" where \"TITLE\" = ?");
			pstmt.setString(1, brand);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = rs.getString("BRAND_NUMBER");
			}
		} catch (SQLException e) {
			e.printStackTrace();
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
		return result;
	}
}
