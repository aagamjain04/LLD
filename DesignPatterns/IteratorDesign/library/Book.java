// Book class representing individual books in a library
public class Book {

    private final String title;
    private final String author;
    private int price;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;

    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
