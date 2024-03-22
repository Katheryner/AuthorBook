package entity;

public class Book {
    private int ID;
    private String title;
    private int year;
    private double price;
    private int IdAuthor;

    private Author objAuthor;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getIdAuthor() {
        return IdAuthor;
    }

    public void setIdAuthor(int idAuthor) {
        IdAuthor = idAuthor;
    }

    public Author getObjAuthor() {
        return objAuthor;
    }

    public void setObjAuthor(Author objAuthor) {
        this.objAuthor = objAuthor;
    }

    @Override
    public String toString() {
        return "Book{" +
                "ID=" + ID +
                ", title='" + title + '\'' +
                ", year=" + year +
                ", price=" + price +
                ", IdAuthor=" + IdAuthor +
                ", Author=" + objAuthor +
                '}';
    }
}
