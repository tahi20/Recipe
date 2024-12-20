package Main;
import org.example.recipe.Category;

public class Recipe {
    private int id;
    private String name;
    private String instructions;
    private Category category;
    private String difficulty; 

    public Recipe() {
    }

    public Recipe(String name, String instructions, Category category, String difficulty) {
        this.name = name;
        this.instructions = instructions;
        this.category = category;
        this.difficulty = difficulty;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getInstructions() { return instructions; }
    public void setInstructions(String instructions) {this.instructions = instructions;}
    public Category getCategory() { return category; }
    public void setCategory(Category category) { this.category = category; }
    public String getDifficulty() { return difficulty; }
    public void setDifficulty(String difficulty) { this.difficulty = difficulty; }

    @Override
    public String toString() {
        return "Recipe{" +
                "id=" + id +
                ", recipename='" + name + '\'' +
                ", instructions='" + instructions + '\'' +
                ", category=" + category + '\''+
                '}';
    }
}


