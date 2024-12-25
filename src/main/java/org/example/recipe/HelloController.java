package org.example.recipe;
import Main.DAO.UserDAO;
import Main.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

public class HelloController {
    @FXML
    private Button buttonToScene2;

    @FXML
    private Button loginButton;

    @FXML
    private TextField password;

    @FXML
    private TextField login;

    private UserDAO userDAO;

    public void initialize() {
        try {
            userDAO = new UserDAO();
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Ошибка подключения к базе данных");
        }
    }

    @FXML
    void onActionLogin() {
        String enteredLogin = login.getText();
        String enteredPassword = password.getText();

        try {
            boolean bbb = userDAO.checkUserExists(enteredLogin, enteredPassword);

            if (bbb) {
                System.out.println("Вы успешно зарегистрировались");
                showAlert("Вы успешно зарегистрировались");

            } else {
                showAlert("Неверный пароль");
            }
        }
        catch (Exception e){
            showAlert("Couldnt connect to database");
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Сообщение");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void switchToScene2(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Recipes.fxml")));
        Scene scene = new Scene(root);
        Stage window = (Stage) buttonToScene2.getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
}