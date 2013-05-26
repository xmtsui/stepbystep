package com.tsui.util;
/**
 *
 * @author xmtsui
 * @version v1.0
 */
abstract class AbstractSequentialList<E> extends AbstractList<E> implements List<E>{
	protected AbstractSequentialList(){
	}

	//Query operations
	
	//int size();
	//boolean isEmpty();
	//boolean contains(Object o);
	public abstract boolean contains(Object o);
    //Iterator<E> iterator();
    //Object[] toArray();
	
    //Modification Operations
	
	public abstract boolean add(E e);
	public abstract boolean remove(Object o);

    //Bulk operations
    //boolean containsAll(Collection<?> c);
    //boolean addAll(Collection<? extends E> c);
    //boolean removeAll(Collection<?> c);
    //boolean retainAll(Collection<?> c);
	public abstract void clear();

    //Compare and hashing

    //boolean equals(Object o);
    //int hashCode();
	
	//implements from List
	//Positional Access Operations

    /**
     * Returns the element at the specified position in this list.
     *
     * @param index index of the element to return
     * @return the element at the specified position in this list
     * @throws IndexOutOfBoundsException if the index is out of range
     *         (<tt>index &lt; 0 || index &gt;= size()</tt>)
     */
    public abstract E get(int index);

    /**
     * Replaces the element at the specified position in this list with the
     * specified element (optional operation).
     *
     * @param index index of the element to replace
     * @param element element to be stored at the specified position
     * @return the element previously at the specified position
     */
    public abstract E set(int index, E element);

    /**
     * Inserts the specified element at the specified position in this list
     * (optional operation).  Shifts the element currently at that position
     * (if any) and any subsequent elements to the right (adds one to their
     * indices).
     *
     * @param index index at which the specified element is to be inserted
     * @param element element to be inserted
     */
    public abstract void add(int index, E element);

	/**
     * Removes the element at the specified position in this list (optional
     * operation).  Shifts any subsequent elements to the left (subtracts one
     * from their indices).  Returns the element that was removed from the
     * list.
     *
     * @param index the index of the element to be removed
     * @return the element previously at the specified position
     */
	public abstract E remove(int index);

	//Search Operations

	/**
     * Returns the index of the first occurrence of the specified element
     * in this list, or -1 if this list does not contain the element.
     * More formally, returns the lowest index <tt>i</tt> such that
     * <tt>(o==null&nbsp;?&nbsp;get(i)==null&nbsp;:&nbsp;o.equals(get(i)))</tt>,
     * or -1 if there is no such index.
     *
     * @param o element to search for
     * @return the index of the first occurrence of the specified element in
     *         this list, or -1 if this list does not contain the element
     */
	public abstract int indexOf(Object o);

    /**
     * Returns the index of the last occurrence of the specified element
     * in this list, or -1 if this list does not contain the element.
     * More formally, returns the highest index <tt>i</tt> such that
     * <tt>(o==null&nbsp;?&nbsp;get(i)==null&nbsp;:&nbsp;o.equals(get(i)))</tt>,
     * or -1 if there is no such index.
     *
     * @param o element to search for
     * @return the index of the last occurrence of the specified element in
     *         this list, or -1 if this list does not contain the element
     */
    public abstract int lastIndexOf(Object o);

}