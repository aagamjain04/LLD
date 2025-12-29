public class BookCollection implements Container<Book>{

    private Book[] books;
    private int size;

    public BookCollection(int capacity){
        this.size = 0;
        books = new Book[capacity];
    }
    public void addBook(Book book){
        if(size < books.length){
            books[size++] = book;
        }
    }


    @Override
    public Iterator<Book> createIterator() {
        return new BookIterator(books);
    }
}
