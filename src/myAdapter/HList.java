package myAdapter;

/**
 * An ordered collection (also known as a sequence). The user of this interface has precise control over where in the list each element is inserted. The user can access elements by their integer index (position in the list), and search for elements in the list.
 *
 * Unlike sets, lists typically allow duplicate elements. More formally, lists typically allow pairs of elements e1 and e2 such that e1.equals(e2), and they typically allow multiple null elements if they allow null elements at all. It is not inconceivable that someone might wish to implement a list that prohibits duplicates, by throwing runtime exceptions when the user attempts to insert them, but we expect this usage to be rare.
 *
 * The List interface places additional stipulations, beyond those specified in the Collection interface, on the contracts of the iterator, add, remove, equals, and hashCode methods. Declarations for other inherited methods are also included here for convenience.
 *
 * The List interface provides four methods for positional (indexed) access to list elements. Lists (like Java arrays) are zero based. Note that these operations may execute in time proportional to the index value for some implementations (the LinkedList class, for example). Thus, iterating over the elements in a list is typically preferable to indexing through it if the caller does not know the implementation.
 * 
 * The List interface provides a special iterator, called a ListIterator, that allows element insertion and replacement, and bidirectional access in addition to the normal operations that the Iterator interface provides. A method is provided to obtain a list iterator that starts at a specified position in the list.
 *
 * The List interface provides two methods to search for a specified object. From a performance standpoint, these methods should be used with caution. In many implementations they will perform costly linear searches.
 *
 * The List interface provides two methods to efficiently insert and remove multiple elements at an arbitrary point in the list.
 *
 * Note: While it is permissible for lists to contain themselves as elements, extreme caution is advised: the equals and hashCode methods are no longer well defined on a such a list.
 *
 * Some list implementations have restrictions on the elements that they may contain. For example, some implementations prohibit null elements, and some have restrictions on the types of their elements. Attempting to add an ineligible element throws an unchecked exception, typically NullPointerException or ClassCastException. Attempting to query the presence of an ineligible element may throw an exception, or it may simply return false; some implementations will exhibit the former behavior and some will exhibit the latter. More generally, attempting an operation on an ineligible element whose completion would not result in the insertion of an ineligible element into the list may throw an exception or it may succeed, at the option of the implementation. Such exceptions are marked as "optional" in the specification for this interface.
**/
public interface HList extends HCollection{
    /**
     * Returns the number of elements in this list. If this list contains more than Integer.MAX_VALUE elements, returns Integer.MAX_VALUE.
     * @return the number of elements in this list.
     */
    int size();

    /**
     * Returns true if this list contains no elements.
     * @return true if this list contains no elements.
     */
    boolean isEmpty();

    /**
     * Returns true if this list contains the specified element. More formally, returns true if and only if this list contains at least one element e such that {<code> (o==null ? e==null : o.equals(e))}.
     * @param o element whose presence in this list is to be tested.
     * @return true if this list contains the specified element.
     * @throws NullPointerException if the specified element is null
     */
    boolean contains(Object o) throws NullPointerException;

    /**
     * Returns an iterator over the elements in this list in proper sequence.
     * @return an iterator over the elements in this list in proper sequence.
     */
    HIterator iterator();

    /**
     * Returns an array containing all of the elements in this list in proper sequence. Obeys the general contract of the <code>HCollection.toArray</code> method.
     * @return an array containing all of the elements in this list in proper sequence.
     */
    Object[] toArray();

    /**
     * Returns an array containing all of the elements in this list in proper sequence; the runtime type of the returned array is that of the specified array. Obeys the general contract of the <code>Collection.toArray(Object[])</code> method.
     * @param a the array into which the elements of this list are to be stored, if it is big enough; otherwise, a new array of the same runtime type is allocated for this purpose.
     * @return an array containing the elements of this list.
     * @throws NullPointerException if the specified array is null
     */
    Object[] toArray(Object[] a) throws NullPointerException;

    /**
     * Appends the specified element to the end of this list.
     * Lists that support this operation may place limitations on what elements may be added to this list. In particular, some lists will refuse to add null elements, and others will impose restrictions on the type of elements that may be added. List classes should clearly specify in their documentation any restrictions on what elements may be added.
     * @param o element to be appended to this list.
     * @return true (as per the general contract of the Collection.add method).
     * @throws NullPointerException if the specified element is null
     */
    boolean add(Object o) throws NullPointerException;

    /**
     * Removes the first occurrence in this list of the specified element. If this list does not contain the element, it is unchanged. More formally, removes the element with the lowest index i such that <code> (o==null ? get(i)==null : o.equals(get(i)))} (if such an element exists)</code>.
     * @param o element to be removed from this list, if present.
     * @return true if this list contained the specified element.
     * @throws NullPointerException if the specified element is null
     */
    boolean remove(Object o) throws NullPointerException;

    /**
     * Returns true if this list contains all of the elements of the specified collection.
     * @param c collection to be checked for containment in this list.
     * @return true if this list contains all of the elements of the specified collection.
     * @throws NullPointerException if the specified collection is null
     * @throws NullPointerException if the specified collection contains one or more null elements
     */
    boolean containsAll(HCollection c) throws NullPointerException;

    /**
     * Appends all of the elements in the specified collection to the end of this list, in the order that they are returned by the specified collection's iterator. The behavior of this operation is unspecified if the specified collection is modified while the operation is in progress. (Note that this will occur if the specified collection is this list, and it's nonempty.)
     * @param c collection whose elements are to be added to this list.
     * @return true if this list changed as a result of the call.
     * @throws NullPointerException if the specified collection is null
     * @throws NullPointerException if the specified collection contains one or more null elements
     */
    boolean addAll(HCollection c) throws NullPointerException;

    /**
     * Inserts all of the elements in the specified collection into this list at the specified position. Shifts the element currently at that position (if any) and any subsequent elements to the right (increases their indices). The new elements will appear in this list in the order that they are returned by the specified collection's iterator. The behavior of this operation is unspecified if the specified collection is modified while the operation is in progress. (Note that this will occur if the specified collection is this list, and it's nonempty.)
     * @param index index at which to insert first element from the specified collection.
     * @param c elements to be inserted into this list.
     * @return true if this list changed as a result of the call.
     * @throws NullPointerException if the specified collection is null
     * @throws NullPointerException if the specified collection contains one or more null elements
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index > size())
     */
    boolean addAll(int index, HCollection c) throws NullPointerException, IndexOutOfBoundsException;

    /**
     * Removes from this list all the elements that are contained in the specified collection.
     * @param c collection that defines which elements will be removed from this list.
     * @return true if this list changed as a result of the call.
     * @throws NullPointerException if the specified collection is null
     * @throws NullPointerException if the specified collection contains one or more null elements
     */
    boolean removeAll(HCollection c) throws NullPointerException;

    /**
     * Retains only the elements in this list that are contained in the specified collection. In other words, removes from this list all the elements that are not contained in the specified collection.
     * @param c collection that defines which elements this set will retain.
     * @return true if this list changed as a result of the call.
     * @throws NullPointerException if the specified collection is null
     * @throws NullPointerException if the specified collection contains one or more null elements
     */
    boolean retainAll(HCollection c) throws NullPointerException;

    /**
     * Removes all of the elements from this list. This list will be empty after this call returns (unless it throws an exception).
     */
    void clear();

    /**
     * Compares the specified object with this list for equality. Returns true if and only if the specified object is also a list, both lists have the same size, and all corresponding pairs of elements in the two lists are equal. (Two elements e1 and e2 are equal if (e1==null ? e2==null : e1.equals(e2)).) In other words, two lists are defined to be equal if they contain the same elements in the same order. This definition ensures that the equals method works properly across different implementations of the List interface.
     * @param o the object to be compared for equality with this list.
     * @return true if the specified object is equal to this list.
     */
    boolean equals(Object o);

    /**
     * Returns the hash code value for this list.
     * This ensures that list1.equals(list2) implies that list1.hashCode()==list2.hashCode() for any two lists, list1 and list2, as required by the general contract of Object.hashCode.
     * @return the hash code value for this list.
     */
    int hashCode();

    /**
     * Returns the element at the specified position in this list.
     * @param index index of element to return.
     * @return the element at the specified position in this list.
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index > size())
     */
    Object get(int index) throws IndexOutOfBoundsException;
    
    /**
     * Replaces the element at the specified position in this list with the specified element.
     * @param index index of element to replace.
     * @param element element to be stored at the specified position.
     * @return the element previously at the specified position.
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index > size())
     * @throws NullPointerException if the specified element is null
     */
    Object set(int index, Object element) throws IndexOutOfBoundsException, NullPointerException;

    /**
     * Inserts the specified element at the specified position in this list. Shifts the element currently at that position (if any) and any subsequent elements to the right (adds one to their indices).
     * @param index index at which the specified element is to be inserted.
     * @param element element to be inserted.
     * @throws <code>IndexOutOfBoundsException</code> if the index is out of range (index < 0 || index > size())
     * @throws NullPointerException if the specified element is null
     */
    void add(int index, Object element) throws IndexOutOfBoundsException, NullPointerException;

    /**
     * Removes the element at the specified position in this list. Shifts any subsequent elements to the left (subtracts one from their indices). Returns the element that was removed from the list.
     * @param index the index of the element to removed.
     * @return the element previously at the specified position.
     * @throws <code>IndexOutOfBoundsException</code> if the index is out of range (index < 0 || index > size())
     */
    Object remove(int index) throws IndexOutOfBoundsException;

    /**
     * Returns the index in this list of the first occurrence of the specified element, or -1 if this list does not contain this element. More formally, returns the lowest index i such that <code>(o==null ? get(i)==null : o.equals(get(i)))</code>, or -1 if there is no such index.
     * @param o element to search for.
     * @return the index in this list of the first occurrence of the specified element, or -1 if this list does not contain this element.
     * @throws NullPointerException if the specified element is null
     */
    int indexOf(Object o) throws NullPointerException;

    /**
     * Returns the index in this list of the last occurrence of the specified element, or -1 if this list does not contain this element. More formally, returns the highest index i such that <code>(o==null ? get(i)==null : o.equals(get(i)))}</code>, or -1 if there is no such index.
     * @param o element to search for.
     * @return the index in this list of the last occurrence of the specified element, or -1 if this list does not contain this element.
     * @throws NullPointerException if the specified element is null
     */
    int lastIndexOf(Object o) throws NullPointerException;

    /**
     * Returns a list iterator of the elements in this list (in proper sequence).
     * @return a list iterator of the elements in this list (in proper sequence).
     */
    HListIterator listIterator();

    /**
     * Returns a list iterator of the elements in this list (in proper sequence), starting at the specified position in this list. The specified index indicates the first element that would be returned by an initial call to the next method. An initial call to the previous method would return the element with the specified index minus one.
     * @param index index of first element to be returned from the list iterator (by a call to the next method).
     * @return a list iterator of the elements in this list (in proper sequence), starting at the specified position in this list.
    * @throws <code>IndexOutOfBoundsException</code> if the index is out of range (index < 0 || index > size())
     */
    HListIterator listIterator(int index) throws IndexOutOfBoundsException;

    /**
     * Returns a view of the portion of this list between the specified fromIndex, inclusive, and toIndex, exclusive. (If fromIndex and toIndex are equal, the returned list is empty.) The returned list is backed by this list, so non-structural changes in the returned list are reflected in this list, and vice-versa. The returned list supports all of the optional list operations supported by this list.
     * This method eliminates the need for explicit range operations (of the sort that commonly exist for arrays). Any operation that expects a list can be used as a range operation by passing a subList view instead of a whole list. For example, the following idiom removes a range of elements from a list:
     * list.subList(from, to).clear();
     * Similar idioms may be constructed for indexOf and lastIndexOf, and all of the algorithms in the Collections class can be applied to a subList.
     * The semantics of the list returned by this method become undefined if the backing list (i.e., this list) is structurally modified in any way other than via the returned list. (Structural modifications are those that change the size of this list, or otherwise perturb it in such a fashion that iterations in progress may yield incorrect results.)
     * @param fromIndex low endpoint (inclusive) of the subList.
     * @param toIndex high endpoint (exclusive) of the subList.
     * @return a view of the specified range within this list.
     * @throws <code> IndexOutOfBoundsException</code> for an illegal endpoint index value (fromIndex < 0 || toIndex > size || fromIndex > toIndex)
     */
    HList subList(int fromIndex, int toIndex) throws IndexOutOfBoundsException;
}
