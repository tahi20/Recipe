package Main.DAO;

import Main.Recipe;
import org.junit.jupiter.api.Test;
import java.sql.SQLException;

class RecipeDAOTest {

    @Test
    void insert() {
        try {
            RecipeDAO recipeDAO= new RecipeDAO();
            Recipe newRecipe = new Recipe("Пирог","Инструкциигнп г","Десерт","Средний");
            RecipeDAO.insert(newRecipe);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}