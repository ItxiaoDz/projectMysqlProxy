package net.taodianzhang.mysqlproxy.client;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MysqlConnection {

	/**
	 * @param args
	 * @author CZX
	 * @date 2015-6-11
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String driver = "com.mysql.jdbc.Driver";

		// URL指向要访问的数据库名scutcs

		String url = "jdbc:mysql://192.168.1.9:8066/trade";

		// MySQL配置时的用户名

		String user = "root";

		// Java连接MySQL配置时的密码

		String password = "123456";

		try {

		// 加载驱动程序

		Class.forName(driver);

		// 连续数据库

		//DriverManager.setLoginTimeout(100);
		Connection conn = DriverManager.getConnection(url, user, password);
		if(!conn.isClosed())

		System.out.println("Succeeded connecting to the Database!");
		
		String sqlStr = "select count(1) from t_traderatebad";
		 Statement stmt = conn.createStatement() ;  
		 ResultSet rs = stmt.executeQuery(sqlStr) ; 
		
		conn.close();
		}catch(ClassNotFoundException e) {   
			System.out.println("Sorry,can`t find the Driver!");   
			e.printStackTrace();   
			} catch(SQLException e) {   
			e.printStackTrace();   
			} catch(Exception e) {   
			e.printStackTrace();   
			}
	}

}
