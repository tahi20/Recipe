package Main.DAO;
import org.example.recipe.Category;
import Main.DBConnection;
import java.sql.*;
import java.util.List;


public class CategoryDAO {
    public void categoryDAO() throws SQLException {
        Connection conn = DBConnection.getConnection();
    }


    public static List<Category> findAll() {
        return List.of();
    };
}
