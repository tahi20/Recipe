package org.example.recipe;
import Main.DAO.RecipeDAO;
import Main.Recipe;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

public class RecipeController {
    @FXML
    private Button buttonToScene3;
    @FXML
    public Label recipeIdLabel;
    @FXML
    private TextField Setname;
    @FXML
    private TextArea instruct;
    @FXML
    private TextField categoryField;

    @FXML
    private TextField difficultyField;
    @FXML
    private TextField IngredientField;
    @FXML
    private Button AddButton;
    @FXML
    private Label errorLabel;

    private RecipeDAO recipeDAO;

    @FXML
    public void initialize() {
        try {
            this.recipeDAO = new RecipeDAO();
        } catch (SQLException e) {
            System.err.println("Error initializing DAO in controller: " + e.getMessage());

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Database Error");
            alert.setContentText("Error connecting to database. Application will be closed.");
            alert.showAndWait();
            System.exit(1);
        }
    }

    @FXML
    void onActionCreateRecipe() {
        String name = Setname.getText();
        String instructions = instruct.getText();
        String category = categoryField.getText();
        String difficulty = difficultyField.getText();

        if (name == null || name.isEmpty() || instructions == null || instructions.isEmpty() || category == null || difficulty == null) {
            errorLabel.setText("Пожалуйста, заполните все поля.");
            return;
        }

        try {
            Recipe newRecipe = new Recipe(name, instructions, category, difficulty);
            int recipeId = RecipeDAO.insert(newRecipe);
            System.out.println("Рецепт создан с ID: " + recipeId);
            clearFields();
        } catch (SQLException e) {
            System.err.println("Ошибка создания рецепта: " + e.getMessage());

            errorLabel.setText("Ошибка создания рецепта: " + e.getMessage());
        }
    }

    private void clearFields() {
        Setname.clear();
        instruct.clear();
        categoryField.clear();
        difficultyField.clear();
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Сообщение");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void switchToScene3(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Search.fxml")));
        Scene scene = new Scene(root);
        Stage window = (Stage) buttonToScene3.getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
}




