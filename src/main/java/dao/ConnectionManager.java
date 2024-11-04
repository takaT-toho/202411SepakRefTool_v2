package dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import jakarta.servlet.ServletContext;

public class ConnectionManager {
	private static String FILENAME = "/WEB-INF/config/DB.properties";
	private static ConnectionManager instance = null;
	private static Properties prop;

	private static void init(ServletContext context) {
        try {
            System.out.println("Loading properties from: " + FILENAME);
            prop = loadResource(context, FILENAME);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

	public static ConnectionManager getConnectionManager() {
        return instance;
    }
		
	private ConnectionManager(ServletContext context) {
        init(context);
	}

	private static Properties loadResource(ServletContext context, String fileName) {
        Properties sysprop = new Properties();

        try (InputStream input = context.getResourceAsStream(fileName)) {
            if (input == null) {
                throw new IllegalStateException("プロパティファイルが見つかりません: " + fileName);
            }
            sysprop.load(input);
        } catch (IOException ioex) {
            ioex.printStackTrace();
            System.out.println(fileName + ": 読み込みに失敗しました");
        }

        return sysprop;
    }
	
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

	public static void initialize(ServletContext context) {
        if (instance == null) {
            instance = new ConnectionManager(context);
        }
    }
}
