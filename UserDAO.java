package Main.DAO;
import Main.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;
import Main.DBConnection; // Импортируем DatabaseConnection


public class UserDAO {
    private final Connection conn;

    public UserDAO() throws SQLException { // Обрабатываем SQLException
        conn = DBConnection.getConnection(); // Получаем соединение из Singleton
    }

    public void insertUser(User user) throws SQLException {
        String sql = "INSERT INTO users (login, password) VALUES (?, ?)";
        try (PreparedStatement preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.executeUpdate();

        }
    }

    public boolean checkUserExists(String login) throws SQLException {
        String sql = "SELECT COUNT(*) FROM users WHERE login = ?";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, login);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1) > 0;
                }
            }
        }
        return false;
    }
}
