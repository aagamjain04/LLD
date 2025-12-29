// Aggregate interface - Declares a method to create am iterator
public interface Container<T> {

    Iterator<T> createIterator();
}
