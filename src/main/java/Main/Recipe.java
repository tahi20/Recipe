package Main;
public class Recipe {
    private int id;
    private String name;
    private String instructions;
    private String category;
    private String difficulty;

    public Recipe(String name, String instructions, String difficulty, String category) {
        this.name = name;
        this.instructions = instructions;
        this.difficulty = difficulty;
        this.category = category;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getInstructions() {
        return instructions;
    }
    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public String getDifficulty() {
        return difficulty;
    }
    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }
    public Recipe() {

    }
    @Override
    public String toString() {
        return
                 ' ' + id + ' ' + ' ' + name + ' ' +  instructions + ' '+ difficulty + ' ' + category + ' ';
    }
}


