public class IteratorDemo {
    public static void main(String[] args) {

        BookCollection library = new BookCollection(3);
        library.addBook(new Book("1984", "George Orwell"));
        library.addBook(new Book("To Kill a Mockingbird", "Harper Lee"));
        library.addBook(new Book("The Great Gatsby", "F. Scott Fitzgerald"));

        Iterator<Book> it = library.createIterator();

        while(it.hasNext()){
            System.out.println(it.next());
        }



    }
}
