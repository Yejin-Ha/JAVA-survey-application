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
}
