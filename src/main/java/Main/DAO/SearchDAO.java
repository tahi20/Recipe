package Main.DAO;

import Main.DBConnection;
import Main.Recipe;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SearchDAO {

    private static Connection conn;

    public static List<Recipe> findByName(String name) throws SQLException {
        List<Recipe> foundRecipes = new ArrayList<>();
        String sql = "SELECT * FROM recipes WHERE name = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Recipe recipe = new Recipe(
                        rs.getString("name"),
                        rs.getString("instructions"),
                        rs.getString("difficulty"),
                        rs.getString("category")
                );
                foundRecipes.add(recipe);
            }
        }
        return foundRecipes;
    }

    public static List<Recipe> findByCategory(String name) throws SQLException {
        List<Recipe> foundRecipes = new ArrayList<>();
        String sql = "SELECT * FROM recipes WHERE category = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Recipe recipe = new Recipe(
                        rs.getString("name"),
                        rs.getString("instructions"),
                        rs.getString("difficulty"),
                        rs.getString("category")
                );
                foundRecipes.add(recipe);
            }
        }
        return foundRecipes;
    }
    public void deleteByName(String recipeName) throws SQLException {
        String sql = "DELETE FROM recipes WHERE name = ?";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, recipeName);
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Рецепт с указанным именем не найден");
            }
        }
    }

    public static int update(Recipe updatedRecipe) throws SQLException {
        String sql = "UPDATE recipes SET name = ?, instructions = ?, difficulty = ?, category = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setString(1, updatedRecipe.getName());
            preparedStatement.setString(2, updatedRecipe.getInstructions());
            preparedStatement.setString(3, updatedRecipe.getDifficulty());
            preparedStatement.setString(4, updatedRecipe.getCategory());
            preparedStatement.setLong(5, updatedRecipe.getId()); // ID рецепта для обновления

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Обновление рецепта не удалось, строки не были изменены.");
            }
        }
        return 0;
    }
}
