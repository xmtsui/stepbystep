package com.tsui.util;
/**
 * 可改变容量的ArrayList实现，未实现同步控制
 *
 * @author xmtsui
 * @version v1.0
 */
import java.util.Arrays;
public class Vector<E> extends AbstractList<E> implements List<E>, RandomAccess {

    /**
     * 存储结构
     *
     * @serial
     */
    protected Object[] elementData;
    
    /**
     * The number of valid components in this {@code Vector} object.
     * Components {@code elementData[0]} through
     * {@code elementData[elementCount-1]} are the actual items.
     *
     * @serial
     */
    protected int elementCount;

    /**
     * The amount by which the capacity of the vector is automatically
     * incremented when its size becomes greater than its capacity.  If
     * the capacity increment is less than or equal to zero, the capacity
     * of the vector is doubled each time it needs to grow.
     * @serial 
     */
    protected int capacityIncrement;
	
    /**
     * 使用指定的初始容量和增长值初始化一个空的Vector
     * @param  initialCapacity   初始容量
     * @param  capacityIncrement 容器满的时候的增长值
     * @throws IllegalArgumentException 初始值是负值的时候抛出
     */
    public Vector(int initialCapacity, int capacityIncrement)
    {
        super();
        if(initialCapacity<0)
            throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
        this.elementData = new Object[initialCapacity];
        this.capacityIncrement = capacityIncrement;
    }

    /**
     * 使用指定的初始容量来初始化一个空的vector，增长值为0
     * @param  initialCapacity 初始容量
     * @throws IllegalArgumentException 初始值是负值的时候抛出
     */
    public Vector(int initialCapacity)
    {
        this(initialCapacity, 0);
    }

    /**
     * 构造一个默认初始容量的空vector，增长值为0
     */
    public Vector()
    {
        this(10);
	}

    //private use
    
    /**
     * This implements the unsynchronized semantics of ensureCapacity.
     * Synchronized methods in this class can internally call this
     * method for ensuring capacity without incurring the cost of an
     * extra synchronization.
     *
     * @see #ensureCapacity(int)
     */
    private void ensureCapacityHelper(int minCapacity) {
        // overflow-conscious code
        if (minCapacity - elementData.length > 0)
            grow(minCapacity);
    }

    private void grow(int minCapacity) {
        // overflow-conscious code
        int oldCapacity = elementData.length;
        int newCapacity = oldCapacity + ((capacityIncrement > 0) ?
                                         capacityIncrement : oldCapacity);
        if (newCapacity - minCapacity < 0)
            newCapacity = minCapacity;
        if (newCapacity - MAX_ARRAY_SIZE > 0)
            newCapacity = hugeCapacity(minCapacity);
        elementData = Arrays.copyOf(elementData, newCapacity);
    }

    /**
     * The maximum size of array to allocate.
     * Some VMs reserve some header words in an array.
     * Attempts to allocate larger arrays may result in
     * OutOfMemoryError: Requested array size exceeds VM limit
     */
    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

    private static int hugeCapacity(int minCapacity) {
        if (minCapacity < 0) // overflow
            throw new OutOfMemoryError();
        return (minCapacity > MAX_ARRAY_SIZE) ?
            Integer.MAX_VALUE :
            MAX_ARRAY_SIZE;
    }

	//Query operations
    
    /**
     * Returns the number of components in this vector.
     * @return the number of components in this vector.
     */
	public synchronized int size(){
		return elementCount;
	}

    /**
     * 判断是否为空
     * @return {@code true} if and only if this vector has no components
     *         that is, its elementCount is zero.
     *         {@code false} otherwise.
     */
	public synchronized boolean isEmpty(){
		return elementCount==0;
	}

    /**
     * 判断是否包含指定的元素
     * @param  o [
     * @return   [description]
     */
	public boolean contains(Object o){
        return indexOf(o) >= 0;
	}
    
    //Iterator<E> iterator();
    //Object[] toArray();
	
    //Modification Operations
	
	/**
     * 在末尾添加
     * @param  e element to be appended to this Vector
     * @return {@code true} (as specified by {@link Collection#add})
     */
    public synchronized boolean add(E e)
	{
        ensureCapacityHelper(elementCount+1);
        elementData[elementCount++] = e;
		return true;
	}

    /**
     * [remove description]
     * @param  o [description]
     * @return   [description]
     */
	public boolean remove(Object o){
        return removeElement(o);
	}

    /**
     * Vector自己定义的函数
     * @param  o [description]
     * @return   [description]
     */
    public synchronized boolean removeElement(Object o)
    {
        int i = indexOf(o);
        if(i>=0){
            removeElementAt(i);
            return true;
        }
        return false;
    }

    /**
     * Vector自己定义的函数
     * @param  index [description]
     * @return       [description]
     */
    public synchronized void removeElementAt(int index)
    {
        if(index>=elementCount)
            throw new ArrayIndexOutOfBoundsException(index + ">=" + elementCount);
        else if(index<0)
            throw new ArrayIndexOutOfBoundsException(index);
        int j = elementCount - index - 1;
        if(j>0)
            System.arraycopy(elementData, index+1, elementData, index, j);
        elementCount--;
        elementData[elementCount]=null;
    }

    //Bulk operations
    //boolean containsAll(Collection<?> c);
    //boolean addAll(Collection<? extends E> c);
    //boolean removeAll(Collection<?> c);
    //boolean retainAll(Collection<?> c);
	public void clear(){
        removeAllElements();
	}

    public synchronized void removeAllElements(){
        for(int i=0; i<elementCount; ++i)
            elementData[i] = null;
        elementCount = 0;
    }

    //Compare and hashing

    //boolean equals(Object o);
    //int hashCode();
	
	//implements from List
	//Positional Access Operations

    @SuppressWarnings("unchecked")
    E elementData(int index) {
        return (E) elementData[index];
    }

    /**
     * Returns the element at the specified position in this list.
     *
     * @param index index of the element to return
     * @return the element at the specified position in this list
     * @throws IndexOutOfBoundsException if the index is out of range
     *         (<tt>index &lt; 0 || index &gt;= size()</tt>)
     */
    public synchronized E get(int index){
    	if(index>=elementCount)
            throw new ArrayIndexOutOfBoundsException(index + ">=" + elementCount);
        else if(index<0)
            throw new ArrayIndexOutOfBoundsException(index);

        return elementData(index);
    }

    /**
     * Replaces the element at the specified position in this list with the
     * specified element (optional operation).
     *
     * @param index index of the element to replace
     * @param element element to be stored at the specified position
     * @return the element previously at the specified position
     */
    public synchronized E set(int index, E element){
        if(index>=elementCount)
            throw new ArrayIndexOutOfBoundsException(index + ">=" + elementCount);
        else if(index<0)
            throw new ArrayIndexOutOfBoundsException(index);
        E oldValue = elementData(index);
        elementData[index] = element;
        return oldValue;
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
        if(index>=elementCount)
            throw new ArrayIndexOutOfBoundsException(index + ">=" + elementCount);
        else if(index<0)
            throw new ArrayIndexOutOfBoundsException(index);
        insertElementAt(element, index);
    }

    /**
     * Vector 自己定义
     * Inserts the specified object as a component in this vector at the
     * specified {@code index}. Each component in this vector with
     * an index greater or equal to the specified {@code index} is
     * shifted upward to have an index one greater than the value it had
     * previously.
     *
     * <p>The index must be a value greater than or equal to {@code 0}
     * and less than or equal to the current size of the vector. (If the
     * index is equal to the current size of the vector, the new element
     * is appended to the Vector.)
     *
     * <p>This method is identical in functionality to the
     * {@link #add(int, Object) add(int, E)}
     * method (which is part of the {@link List} interface).  Note that the
     * {@code add} method reverses the order of the parameters, to more closely
     * match array usage.
     *
     * @param      obj     the component to insert
     * @param      index   where to insert the new component
     * @throws ArrayIndexOutOfBoundsException if the index is out of range
     *         ({@code index < 0 || index > size()})
     */
    public synchronized void insertElementAt(E obj, int index) {
        if (index > elementCount) {
            throw new ArrayIndexOutOfBoundsException(index
                                                     + " > " + elementCount);
        }
        ensureCapacityHelper(elementCount + 1);
        System.arraycopy(elementData, index, elementData, index + 1, elementCount - index);
        elementData[index] = obj;
        elementCount++;
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
	public synchronized E remove(int index){
        if (index >= elementCount)
            throw new ArrayIndexOutOfBoundsException(index);
        E oldValue = elementData(index);

        int numMoved = elementCount - index - 1;
        if (numMoved > 0)
            System.arraycopy(elementData, index+1, elementData, index,
                             numMoved);
        elementData[--elementCount] = null; // Let gc do its work

        return oldValue;
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
		return indexOf(o, 0);
	}

    /**
     * Vector自己定义，不是继承来的。函数重载，从指定的index开始寻找
     * 
     * @param  o     [description]
     * @param  index [description]
     * @return       [description]
     */
    public synchronized int indexOf(Object o, int index)
    {
        if(o == null)
        {
            for(int i=0; i<elementCount; ++i)
                if(elementData[i] == null)
                    return i;
        }
        else
        {
            for(int i=0; i<elementCount; ++i)
                if(o.equals(elementData[i]))
                    return i;
        }
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
    public synchronized int lastIndexOf(Object o){
    	return lastIndexOf(o, elementCount-1);
    }

    /**
     * Returns the index of the last occurrence of the specified element in
     * this vector, searching backwards from {@code index}, or returns -1 if
     * the element is not found.
     * More formally, returns the highest index {@code i} such that
     * <tt>(i&nbsp;&lt;=&nbsp;index&nbsp;&amp;&amp;&nbsp;(o==null&nbsp;?&nbsp;get(i)==null&nbsp;:&nbsp;o.equals(get(i))))</tt>,
     * or -1 if there is no such index.
     *
     * @param o element to search for
     * @param index index to start searching backwards from
     * @return the index of the last occurrence of the element at position
     *         less than or equal to {@code index} in this vector;
     *         -1 if the element is not found.
     * @throws IndexOutOfBoundsException if the specified index is greater
     *         than or equal to the current size of this vector
     */
    public synchronized int lastIndexOf(Object o, int index) {
        if (index >= elementCount)
            throw new IndexOutOfBoundsException(index + " >= "+ elementCount);

        if (o == null) {
            for (int i = index; i >= 0; i--)
                if (elementData[i]==null)
                    return i;
        } else {
            for (int i = index; i >= 0; i--)
                if (o.equals(elementData[i]))
                    return i;
        }
        return -1;
    }
}