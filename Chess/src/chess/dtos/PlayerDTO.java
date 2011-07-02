package chess.dtos;

public class PlayerDTO {

    public PlayerDTO() {
    }
    private int id;
    private char color;
    private String name;

    public char getColor() {
        return color;
    }

    public void setColor(char color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;

    }

    public int getId() {
        return this.id;

    }
}
