package myAdapter;

import java.util.NoSuchElementException;

/**
 * An iterator for lists that allows the programmer to traverse the list in either direction, modify the list during iteration, and obtain the iterator's current position in the list. A ListIterator has no current element; its cursor position always lies between the element that would be returned by a call to previous() and the element that would be returned by a call to next(). In a list of length n, there are n+1 valid index values, from 0 to n, inclusive.
 */
public interface HListIterator extends HIterator {
    /**
     * Returns true if this list iterator has more elements when traversing the list in the forward direction. (In other words, returns true if next would return an element rather than throwing an exception.)
     * @return true if the list iterator has more elements when traversing the list in the forward direction.
     */
    boolean hasNext();

    /**
     * Returns the next element in the list. This method may be called repeatedly to iterate through the list, or intermixed with calls to previous to go back and forth. (Note that alternating calls to next and previous will return the same element repeatedly.)
     * @return the next element in the list.
     * @throws NoSuchElementException if the iteration has no next element
     */ 
    Object next() throws NoSuchElementException;

    /**
     * Returns true if this list iterator has more elements when traversing the list in the reverse direction. (In other words, returns true if previous would return an element rather than throwing an exception.)
     * @return true if the list iterator has more elements when traversing the list in the reverse direction.
     */
    boolean hasPrevious();

    /**
     * Returns the previous element in the list. This method may be called repeatedly to iterate through the list backwards, or intermixed with calls to next to go back and forth. (Note that alternating calls to next and previous will return the same element repeatedly.)
     * @return the previous element in the list.
     * @throws NoSuchElementException if the iteration has no previous element
     */
    Object previous() throws NoSuchElementException;

    /**
     * Returns the index of the element that would be returned by a subsequent call to next. (Returns list size if the list iterator is at the end of the list.)
     * @return the index of the element that would be returned by a subsequent call to next, or list size if list iterator is at end of list.
     */
    int nextIndex();

    /**
     * Returns the index of the element that would be returned by a subsequent call to previous. (Returns -1 if the list iterator is at the beginning of the list.)
     * @return the index of the element that would be returned by a subsequent call to previous, or -1 if list iterator is at beginning of list.
     */
    int previousIndex();

    /**
     * Removes from the list the last element that was returned by next or previous. This call can only be made once per call to next or previous. It can be made only if ListIterator.add has not been called after the last call to next or previous.
     * @throws UnSupportedElementException remove operation is not supported
     */
    void remove() throws IllegalStateException;

    /**
     * Replaces the last element returned by next or previous with the specified element. This call can be made only if neither ListIterator.remove nor ListIterator.add have been called after the last call to next or previous.
     * @param o the element with which to replace the last element returned by next or previous.
     * @throws UnSupportedElementException set operation is not supported
     */
    void set(Object o) throws IllegalArgumentException, IllegalStateException;

    /**
     * Inserts the specified element into the list. The element is inserted immediately before the next element that would be returned by next, if any, and after the next element that would be returned by previous, if any. (If the list contains no elements, the new element becomes the sole element on the list.) The new element is inserted before the implicit cursor: a subsequent call to next would be unaffected, and a subsequent call to previous would return the new element. (This call increases by one the value that would be returned by a call to nextIndex or previousIndex.)
     * @param o the element to insert.
     * @throws UnSupportedElementException add operation is not supported
     */
    void add(Object o) throws IllegalArgumentException;
}
