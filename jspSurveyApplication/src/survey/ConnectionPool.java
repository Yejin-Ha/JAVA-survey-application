package survey;

import java.sql.*;
import java.util.ArrayList;

import org.apache.tomcat.dbcp.dbcp2.SQLExceptionList;

public class ConnectionPool {
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	// 사용 전 connection
	private ArrayList<Connection> freed;
	// 사용 중인 connection
	private ArrayList<Connection> used;
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String id = "c##tester";
	private String pwd = "1234";
	// 초기 connection 수
	private int initCons = 10;
	// 최대 connection 수
	private int maxCons = 20;
	// 전체 connection 수
	private int numCons = 0;

	private static ConnectionPool cp;

	private ConnectionPool() throws SQLException {
		/*
		 * 초기 connection 개수를 각각의 arraylist에 저장할 수 있도록 초기 connection 수 만큼 생성
		 */
		freed = new ArrayList<Connection>(initCons);
		used = new ArrayList<Connection>(initCons);

		// initCons 수 만큼 connection 생성
		while (numCons < initCons) {
			addConnection();
		}
	}

	public static ConnectionPool getInstance() {
		try {
			if (cp == null) {
				synchronized (ConnectionPool.class) {
					cp = new ConnectionPool();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cp;
	}

	private void addConnection() throws SQLException {
		freed.add(getNewConnection());
	}

	private Connection getNewConnection() throws SQLException {
		Connection conn = null;

		try {
			conn = DriverManager.getConnection(url, id, pwd);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("About to connect to " + conn);
		numCons++;
		return conn;
	}

	// 요청이 왔을 때 freed에 있는 connection을 used로 옮기는 기능
	public synchronized Connection getConnection() throws SQLException {
		if (freed.isEmpty()) {
			while (numCons < maxCons) {
				addConnection();
			}
		}
		// freed에 마지막 connection을 반환
		Connection _conn = freed.get(freed.size() - 1);
		freed.remove(_conn);
		used.add(_conn);
		return _conn;
	}

	// 요청 처리가 끝나면 used의 connection객체를 freed로 옮기는 기능
	public synchronized void releaseConnection(Connection _conn) throws SQLException {
		boolean flag = false;
		if (used.contains(_conn)) {
			used.remove(_conn);
			numCons--;
			flag = true;
		} else {
			throw new SQLException("ConnectionPool에 없습니다.");
		}

		try {
			if (flag) {
				freed.add(_conn);
				numCons++;
			} else {
				_conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 모든 connection 삭제
	public void closeAll() {
		for (int i = 0; i < used.size(); i++) {
			Connection _conn = used.get(i);
			used.remove(i--);
			try {
				_conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		// 사용 전인 모든 Connection 객체 삭제
		for (int i = 0; i < freed.size(); i++) {
			Connection _conn = freed.get(i);
			freed.remove(i--);
			try {
				_conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public int getNumCons() {
		return numCons;
	}

	public void close(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void close(Statement stmt) {
		if (stmt != null) {
			try {
				stmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}