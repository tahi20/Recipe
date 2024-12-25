module org.example.recipe {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens org.example.recipe to javafx.fxml;
    exports org.example.recipe;
    exports Main;
    opens Main to javafx.fxml;
    exports Main.DAO;
    opens Main.DAO to javafx.fxml;
}