package org.example.recipe;

import Main.DAO.UserDAO;
import Main.User;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import java.sql.SQLException;

public class HelloController {

    @FXML
    private Button loginButton;

    @FXML
    private TextField password;

    @FXML
    private TextField login;
    private final String correctPassword = "12345";

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

        if (enteredPassword.equals(correctPassword)) {
            System.out.println("Вы успешно зарегистрировались");
            showAlert("Вы успешно зарегистрировались");

            try {
                if (!userDAO.checkUserExists(enteredLogin)) {
                    User newUser = new User(enteredLogin, enteredPassword);
                    userDAO.insertUser(newUser);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                showAlert("Ошибка записи в базу данных");
            }


        } else {
            showAlert("Неверный пароль");
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Сообщение");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}