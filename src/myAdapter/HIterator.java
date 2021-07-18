package myAdapter;

import java.util.NoSuchElementException;

/**
 * An iterator over a collection. Iterator takes the place of Enumeration in the Java collections framework. Iterators differ from enumerations in two ways:
 *
 * <ul>
 * <li>Iterators allow the caller to remove elements from the underlying collection during the iteration with well-defined semantics.</li>
 * <li>Method names have been improved.</li>
 * </ul>
**/
public interface HIterator {
    /** 
    * Returns true if the iteration has more elements. (In other words, returns true if next would return an element rather than throwing an exception.)
    @return true if the iterator has more elements
    */
    boolean hasNext();

    /** 
    * Returns the next element in the iteration.
    * @return the next element in the iteration
    * @throws NoSuchElementException iteration has no more elements.
    */
    Object next() throws NoSuchElementException;

    /** 
    * Removes from the underlying collection the last element returned by the iterator (optional operation). This method can be called only once per call to next. The behavior of an iterator is unspecified if the underlying collection is modified while the iteration is in progress in any way other than by calling this method.
    * @throws UnsupportedOperationException remove operation is not supported
    */
    void remove() throws IllegalStateException;
}
