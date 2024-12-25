package org.example.recipe;
import Main.DAO.RecipeDAO;
import Main.DAO.SearchDAO;
import Main.Recipe;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.SQLException;
import java.util.List;
public class SearchController {
    @FXML
    private Button UpdateButton;
    @FXML
    public Label recipeIdLabel;
    @FXML
    private TextField name1;
    @FXML
    private TextArea instruct;
    @FXML
    private TextField categoryField;
    @FXML
    private TextField difficultyField;
    @FXML
    private TextField IngredientField;
    @FXML
    private Button DeleteButton;

    private RecipeDAO recipeDAO;
    private Recipe recipe;
    @FXML
    private ListView<Recipe> recipeListView;
    @FXML
    private TextField searchField;
    @FXML
    private TextField searchField1;
    @FXML
    private Button searchButton;
    @FXML
    private Button searchButton1;
    @FXML
    private Label errorLabel;

    private SearchDAO searchDAO;

    @FXML
    void onActionFindRecipeByName(){
        String searchQuery = searchField.getText();
        if (searchQuery == null || searchQuery.isEmpty()) {
            errorLabel.setText("Введите имя для поиска.");
            recipeListView.setItems(FXCollections.observableArrayList());
            return;
        }

        try {
            List<Recipe> foundRecipes = SearchDAO.findByName(searchQuery);
            recipeListView.setItems(FXCollections.observableArrayList(foundRecipes));
            if (foundRecipes.isEmpty()) {
                errorLabel.setText("Рецепты не найдены.");
            } else {
                errorLabel.setText("");
            }
        } catch (SQLException e) {
            System.err.println("Ошибка поиска рецепта: " + e.getMessage());

            errorLabel.setText("Ошибка при поиске рецепта.");
        }
    }

    @FXML
    void onActionFindRecipeByCategory(){
        String searchQuery = searchField1.getText();
        if (searchQuery == null || searchQuery.isEmpty()) {
            errorLabel.setText("Введите имя для поиска.");
            recipeListView.setItems(FXCollections.observableArrayList());
            return;
        }

        try {
            List<Recipe> foundRecipes = SearchDAO.findByCategory(searchQuery);
            recipeListView.setItems(FXCollections.observableArrayList(foundRecipes));
            if (foundRecipes.isEmpty()) {
                errorLabel.setText("Рецепты не найдены.");
            } else {
                errorLabel.setText("");
            }
        } catch (SQLException e) {
            System.err.println("Ошибка поиска рецепта: " + e.getMessage());

            errorLabel.setText("Ошибка при поиске рецепта.");
        }
    }

    @FXML
    public void handleDeleteButton(ActionEvent event) {
        String recipeName = name1.getText();
        int a=9;

        try {
            searchDAO.deleteByName(recipeName);

            showAlert("Рецепт успешно удален");
        } catch (SQLException e) {
            showAlert("Ошибка при удалении рецепта: " + e.getMessage());
        }
    }

    @FXML
    public void handleUpdateButton(ActionEvent event) {
        String name = name1.getText();
        String instructions = instruct.getText();
        String category = categoryField.getText();
        String difficulty = difficultyField.getText();
        if (name == null || name.isEmpty() || instructions == null || instructions.isEmpty() || category == null || difficulty == null) {
            errorLabel.setText("Пожалуйста, заполните все поля.");
            return;
        }
        try {
            Recipe newRecipe = new Recipe(name, instructions, category, difficulty);
            int recipeId = SearchDAO.update(newRecipe);
            System.out.println("Рецепт создан с ID: " + recipeId);
            showAlert("Рецепт успешно обновлен");
        } catch (SQLException e) {
            showAlert("Ошибка при обновлении рецепта: " + e.getMessage());
        }
    }

    private void showAlert(String рецептНеНайден) {
    }
}
