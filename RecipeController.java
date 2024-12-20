package org.example.recipe;
import Main.DAO.CategoryDAO;
import Main.DAO.RecipeDAO;
import Main.Recipe;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.SQLException;
import java.util.List;

public class RecipeController {

    @FXML
    private TextField nameF;
    private ObservableList<Category> categories;
    @FXML
    private TextArea instruct;
    @FXML
    private ComboBox<Category> categoryComboBox;
    @FXML
    private ComboBox<String> difficultyComboBox;
    @FXML
    private TextField IngredientField;
    @FXML
    private TextField SearchField;
    @FXML
    private TextField AddField;
    @FXML
    private Label recipeIdLabel;
    @FXML
    private TextField newCategoryTextField;
    @FXML
    private Label errorLabel;

    private RecipeDAO recipeDAO;
    private CategoryDAO СategoryDAO;

    @FXML
    public void initialize() {
        try {
            this.recipeDAO = new RecipeDAO();
            this.СategoryDAO = new CategoryDAO();
            List<Category> categories = CategoryDAO.findAll();
            categoryComboBox.setItems(FXCollections.observableArrayList(categories));
        } catch (SQLException e) {
            System.err.println("Error initializing DAO in controller: " + e.getMessage());
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Database Error");
            alert.setContentText("Error connecting to database. Application will be closed.");
            alert.showAndWait();
            System.exit(1);
        }
    }

    @FXML
    private ListView<Recipe> recipeListView;

    @FXML
    private void findRecipeByName() {
        String nameToFind = nameF.getText();
        if (nameToFind == null || nameToFind.isEmpty()) {
            errorLabel.setText("Введите имя для поиска.");
            recipeListView.setItems(FXCollections.observableArrayList());
            return;
        }

        try {
            List<Recipe> foundRecipes = recipeDAO.findByName(nameToFind);
            recipeListView.setItems(FXCollections.observableArrayList(foundRecipes));
            if (foundRecipes.isEmpty()) {
                errorLabel.setText("Рецепты не найдены.");
            } else {
                errorLabel.setText("");
            }
        } catch (SQLException e) {
            System.err.println("Ошибка поиска рецепта: " + e.getMessage());
            e.printStackTrace();
            errorLabel.setText("Ошибка при поиске рецепта.");
        }
    }


    @FXML
    private void CreateRecipe() {
        errorLabel.setText("");
        String name = nameF.getText();
        String instructions = instruct.getText();
        Category category = categoryComboBox.getValue();
        String difficulty = difficultyComboBox.getValue();

        if (name == null || name.isEmpty() || instructions == null || instructions.isEmpty() || category == null || difficulty == null) {
            errorLabel.setText("Пожалуйста, заполните все поля.");
            return;
        }

        try {
            Recipe newRecipe = new Recipe(name, instructions, category, difficulty);
            int recipeId = recipeDAO.insert(newRecipe);
            System.out.println("Рецепт создан с ID: " + recipeId);
            clearFields();
        } catch (SQLException e) {
            System.err.println("Ошибка создания рецепта: " + e.getMessage());
            e.printStackTrace();
            errorLabel.setText("Ошибка создания рецепта: " + e.getMessage());
        }
    }

    private void clearFields() {
        nameF.clear();
        instruct.clear();
        difficultyComboBox.setValue(null);
        categoryComboBox.setValue(null);
    }
}




