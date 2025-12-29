public class BookIterator implements Iterator<Book>{

    private int currentIndex = 0;
    private final Book[] book;

    public BookIterator(Book[] book){
        this.book = book;
    }


    @Override
    public boolean hasNext() {
        return currentIndex < book.length;
    }

    @Override
    public Book next() {
        return book[currentIndex++];
    }
}
