package entity;

import java.sql.*;

public class BaseDao {
	/* 加载驱动包 */
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/* 获得连接的方法 */
	public static Connection getconn() {
		//数据库服务器路径，bookSys为数据库名
		String url = "jdbc:mysql://127.0.0.1:3306/medicalsystem1?useUnicode=yes&characterEncoding=UTF-8&serverTimezone=GMT%2B8";
		String user = "root";
		String password = "root";
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// 返回连接对象
		return conn;
	}

	/* 关闭连接的方法 */
	public static void closeAll(Connection conn, PreparedStatement ps,
			ResultSet rs) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (ps != null) {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		Connection conn = getconn();
		System.out.println(conn);
	}
}
