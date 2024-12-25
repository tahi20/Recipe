package Main.DAO;

import Main.User;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
class UserDAOTest {

    @Test
    void insertUser() {
        try {
            UserDAO userDAO = new UserDAO();

            User user = new User("Tahi", "123455");
            userDAO.insertUser(user);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }


    }

    @Test
    void checkUserExists() {
        try {
            UserDAO userDAO = new UserDAO();

            boolean bbb = userDAO.checkUserExists("Tahi", "1234");
            System.out.println(bbb);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
}