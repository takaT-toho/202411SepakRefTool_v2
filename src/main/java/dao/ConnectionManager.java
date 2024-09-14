package dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionManager {
	private static final String FILENAME = "C:\\workspace\\demo202411\\DB.properties";
	private static ConnectionManager instance = null;
	private static Properties prop;
	
	public Connection getConnection() throws SQLException {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			String url = prop.getProperty("URL");
			String user = prop.getProperty("USER");
			String password = prop.getProperty("PASSWORD");
			con = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}
		return con;
	}
		
	public static ConnectionManager getConnectionManager() {
		if (instance == null) {
			instance = new ConnectionManager();
		}
		return instance;
	}
		
	private ConnectionManager() {
		try {
			prop = loadResource(FILENAME);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		
	public Properties loadResource(String fileName) {
		try {
			Properties sysprop = new Properties();
			FileInputStream fis = new FileInputStream(fileName);
			sysprop.load(fis);
			return sysprop;
		} catch (IOException ioex) {
			ioex.printStackTrace();
			System.out.println(fileName + ":読み込みに失敗しました");
		} catch (NullPointerException npex) {
			npex.printStackTrace();
			System.out.println(fileName + ":読み込みに失敗しました");
		}
		return null;
	}
	

}
