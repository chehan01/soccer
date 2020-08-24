package dbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtils {
	public static Connection getConnection()
	{
		//加载jdbc驱动并连接数据库
		final String DBDRIVER = "com.mysql.cj.jdbc.Driver";
		final String DBURL = "jdbc:mysql://localhost:3306/petstore?serverTimezone=UTC&useUnicode=true&characterEncoding=GBK";
		final String DBUSER = "root";
		final String DBPASS = "917350";
		Connection conn=null;
		try
		{
			Class.forName(DBDRIVER);
			conn=DriverManager.getConnection(DBURL,DBUSER,DBPASS);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
		
	}
	
	//关闭数据库连接
	public static void close(Connection conn)
	{
		if(conn!=null)
		{
			try
			{
				conn.close();
			} catch(SQLException e)
			{
				e.printStackTrace();
			}
			conn = null;
		}
	}
}
