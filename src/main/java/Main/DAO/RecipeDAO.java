package Main.DAO;
import Main.Recipe;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;
import Main.DBConnection;



public class RecipeDAO {
    private static Connection conn;

    public RecipeDAO() throws SQLException {
        conn = DBConnection.getConnection();
    }


    public static int insert(Recipe newRecipe) throws SQLException {
        String sql = "INSERT INTO recipes (name, instructions, difficulty, category) VALUES (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, newRecipe.getName());
            preparedStatement.setString(2, newRecipe.getInstructions());
            preparedStatement.setString(4, newRecipe.getDifficulty());
            preparedStatement.setString(3, newRecipe.getCategory());

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating recipe failed, no rows affected.");
            }

            try (var generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Creating recipe failed, no ID obtained.");
                }
            }
        }
    }
}