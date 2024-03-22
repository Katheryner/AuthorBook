package entity;

public class Author {
    private int ID;
    private String name;
    private String nationality;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    @Override
    public String toString() {
        return "Author{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", nacionalidad='" + nationality + '\'' +
                '}';
    }
}
