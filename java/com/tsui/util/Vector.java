package com.tsui.util;
/**
 * 可改变容量的ArrayList实现，未实现同步控制
 *
 * @author xmtsui
 * @version v1.0
 */
public class Vector<E> extends AbstractList<E> implements List<E>, RandomAccess {
	protected Vector(){
	}

	//Query operations
	
	public int size(){
		return -1;
	}
	public boolean isEmpty(){
		return false;
	}
	// boolean contains(Object o);
	public boolean contains(Object o){
		return false;
	}
    //Iterator<E> iterator();
    //Object[] toArray();
	
    //Modification Operations
	
	public boolean add(E e)
	{
		add(size(), e);
		return true;
	}
	public boolean remove(Object o){
		return false;
	}

    //Bulk operations
    //boolean containsAll(Collection<?> c);
    //boolean addAll(Collection<? extends E> c);
    //boolean removeAll(Collection<?> c);
    //boolean retainAll(Collection<?> c);
	public void clear(){

	}

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
    public E get(int index){
    	return null;
    }

    /**
     * Replaces the element at the specified position in this list with the
     * specified element (optional operation).
     *
     * @param index index of the element to replace
     * @param element element to be stored at the specified position
     * @return the element previously at the specified position
     */
    public E set(int index, E element){
    	return null;
    }

    /**
     * Inserts the specified element at the specified position in this list
     * (optional operation).  Shifts the element currently at that position
     * (if any) and any subsequent elements to the right (adds one to their
     * indices).
     *
     * @param index index at which the specified element is to be inserted
     * @param element element to be inserted
     */
    public void add(int index, E element){

    }

	/**
     * Removes the element at the specified position in this list (optional
     * operation).  Shifts any subsequent elements to the left (subtracts one
     * from their indices).  Returns the element that was removed from the
     * list.
     *
     * @param index the index of the element to be removed
     * @return the element previously at the specified position
     */
	public E remove(int index){
		return null;
	}

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
	public int indexOf(Object o){
		return -1;
	}

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
    public int lastIndexOf(Object o){
    	return -1;
    }
}