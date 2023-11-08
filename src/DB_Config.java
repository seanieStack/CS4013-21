// DB Config and URL here , plz do this Seanie 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB_Config {
    private static final String JDBC_URL = "jdbc:yourdatabasetype://yourdatabaseurl";
    private static final String USERNAME = "yourdbusername";
    private static final String PASSWORD = "yourdbpassword";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
    }

}