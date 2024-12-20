package Main.DAO;

import Main.DAOInterface;
import Main.DBConnection;
import Main.Recipe;
import org.example.recipe.Category;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement; // Правильный пакет

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class RecipeDAO {
        private final Connection connection;

        public RecipeDAO() throws SQLException {
            this.connection = DBConnection.getConnection();
        }

        public List<Recipe> findAll() throws SQLException {
            List<Recipe> recipeList = new ArrayList<>();
            String sql = "SELECT * FROM recipes"; // Исправленный SQL-запрос
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) { // try-with-resources для ResultSet
                while (resultSet.next()) {
                    Recipe recipe = new Recipe();
                    recipe.setId(resultSet.getInt("id"));
                    recipe.setName(resultSet.getString("name"));
                    recipe.setInstructions(resultSet.getString("instructions"));

                    int categoryId = resultSet.getInt("category_id");
                    Category category = new Category();
                    category.setId(categoryId);
                    recipe.setCategory(category);

                    recipe.setDifficulty(resultSet.getString("difficulty"));
                    recipeList.add(recipe);
                }
            }
            return recipeList;
        }
    public int insert(Recipe newRecipe) throws SQLException {
        String sql = "INSERT INTO recipes (name, instructions, category_id, difficulty) VALUES (?, ?, ?, ?)"; // Убедитесь, что имена столбцов соответствуют вашей базе данных
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, newRecipe.getName());
            preparedStatement.setString(2, newRecipe.getInstructions());
            preparedStatement.setInt(3, newRecipe.getCategory().getId()); // Предполагается, что у Category есть метод getId()
            preparedStatement.setString(4, newRecipe.getDifficulty());

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


    public List<Recipe> findByName(String name) throws SQLException {
        List<Recipe> recipes = new ArrayList<>();
        String sql = "SELECT * FROM recipes WHERE name LIKE ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, "%" + name + "%");
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Recipe recipe = new Recipe();
                    recipe.setId(resultSet.getInt("id"));
                    recipe.setName(resultSet.getString("name"));
                    recipe.setInstructions(resultSet.getString("instructions"));
                    int categoryId = resultSet.getInt("category_id");
                    Category category = new Category();
                    category.setId(categoryId);
                    recipe.setCategory(category);
                    recipe.setDifficulty(resultSet.getString("difficulty"));
                    recipes.add(recipe);
                }
            }
        }
        return recipes;
    }


    public Recipe read(int id) {
        return null;
    }


    public void update(Recipe entity) {

    }


    public void delete(Recipe entity) {

    }


}