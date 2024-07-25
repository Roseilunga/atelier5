import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DatabaseSingleton {
    private static final String URL = "jdbc:mysql://sql.freedb.tech:3306/freedb_STUDENT";
    private static final String USER = "freedb_PHNUSER"; // Remplacez par votre nomd'utilisateur
    private static final String PASSWORD = "GeqAprS7MD!B!&r"; // Remplacez par votremot de passe
    private static DatabaseSingleton instance;
    private static Connection connection;

    private DatabaseSingleton() throws SQLException {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new SQLException("Failed to create the database connection.", e);
        }
    }
    //ce code est la suite du premier, ne changez pas declasse

    public static DatabaseSingleton getInstance() throws
            SQLException {
        if (instance == null) {
            synchronized (DatabaseSingleton.class) {
                if (instance == null) {
                    instance = new DatabaseSingleton();
                }
            }
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}