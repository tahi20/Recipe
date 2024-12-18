package org.example.recipe;
import Main.DAO.RecipeDAO;
import Main.Recipe;
import Main.Category;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.List;

public class HelloController {
    @FXML
    private Button loginButton;

    @FXML
    private TextField password;
    @FXML
    private TextField login;

    @FXML
    void initialize() {
        loginButton.setOnAction(actionEvent -> {
            System.out.println("Вы усрешно зарегестрировались");
        });
    }
}