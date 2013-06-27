package com.tsui.util;
/**
 * 线性数组List的实现
 *
 * @author xmtsui
 * @version v1.0
 */
import java.util.Arrays;
class ArrayList<E> extends AbstractList<E> implements RandomAccess, List<E>{
	
	//List元素存储缓存，大小为其容量
	private transient Object[] elementData;
	
	//List元素个数
	private int size;

    /**
     * The maximum size of array to allocate.
     * Some VMs reserve some header words in an array.
     * Attempts to allocate larger arrays may result in
     * OutOfMemoryError: Requested array size exceeds VM limit
     */
    // private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;
    private static final int MAX_ARRAY_SIZE = 100;

	/**
     * Constructs an empty list with the specified initial capacity.
     *
     * @param  initialCapacity  the initial capacity of the list
     * @throws IllegalArgumentException if the specified initial capacity
     *         is negative
     */
	public ArrayList(int initialCapacity)
	{
		super();
		if(initialCapacity<0)
			throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
		this.elementData = new Object[initialCapacity];
	}

	/**
     * Constructs an empty list with an initial capacity of ten.
     */
	public ArrayList() {
		this(10);
	}


	//Query operations
	
	public int size(){
		return size;
	}
	
	//AbstractCollection中实现
	//boolean isEmpty();
	
	public boolean contains(Object o){
		return indexOf(o) >= 0;
	}
    //Iterator<E> iterator();
    //Object[] toArray();


	//Modification Operations
	
	//AbstractList中实现
	//public boolean add(E e)
	public boolean remove(Object o){
		if(o == null){
			for(int index=0; index<size; index++)
				if(elementData[index] == null){
					fastRemove(index);
					return true;
				}
			}
			else {
				for(int index=0; index<size; index++)
					if(o.equals(elementData[index])){
						fastRemove(index);
						return true;
					}
				}
				return false;
			}

	private void fastRemove(int index){
		int numMoved = size - index - 1;
        if (numMoved > 0)
			System.arraycopy(elementData, index+1, elementData, index, size-index-1);
		elementData[--size] = null;//let gc works
	}

 	//Bulk operations
    //boolean containsAll(Collection<?> c);
    //boolean addAll(Collection<? extends E> c);
    //boolean removeAll(Collection<?> c);
    //boolean retainAll(Collection<?> c);
	public void clear(){
		for(int i=0; i<size; i++)
		{
			elementData[i] = null;
		}
		size = 0;
	}

	//Compare and hashing

    //boolean equals(Object o);
    //int hashCode();


	//implements from List

	//private use
	@SuppressWarnings("unchecked")
	private E elementData(int index){
		return (E) elementData[index];
	}

	private void rangeCheck(int index) {
		if (index > size || index < 0)
			throw new IndexOutOfBoundsException("Index: "+index+", Size: "+size);
	}

	private void ensureCapacityInternal(int minCapacity){
		if(minCapacity - elementData.length > 0)
			elementData = Arrays.copyOf(elementData, minCapacity);
	}

	private void grow(int minCapacity){
		int oldCapacity = elementData.length;
		int newCapacity = oldCapacity + (oldCapacity>>1);
		if(newCapacity - minCapacity < 0)
			newCapacity = minCapacity;
		if(newCapacity - MAX_ARRAY_SIZE > 0)
			newCapacity = hugeCapacity(minCapacity);
    	// minCapacity is usually close to size, so this is a win:
		elementData = Arrays.copyOf(elementData, newCapacity);
	}

    //why static ?? 
    //private static int hugeCapacity(int minCapacity)
	private int hugeCapacity(int minCapacity) {
        if (minCapacity < 0) // overflow
        throw new OutOfMemoryError();
        return (minCapacity > MAX_ARRAY_SIZE) ?
        Integer.MAX_VALUE :
        MAX_ARRAY_SIZE;
    }

	//Positional Access Operations
	public E get(int index){
    	rangeCheck(index);
    	return elementData(index);
    }
    
    public E set(int index, E element){
    	rangeCheck(index);
    	E oldValue = elementData(index);
    	elementData[index] = element;
    	return oldValue;
    }
    
    public void add(int index, E element){
    	rangeCheck(index);
    	ensureCapacityInternal(size+1);
    	System.arraycopy(elementData, index, elementData, index+1, size-index);
    	elementData[index] = element;
    	size++;
    }

    public E remove(int index){
    	rangeCheck(index);
    	E oldValue = elementData(index);
    	
    	int numMoved = size - index - 1;
        if (numMoved > 0)
    		System.arraycopy(elementData, index+1, elementData, index, size-index-1);
    	elementData[--size] = null;
    	return oldValue;
    }
 
 	//Search Operations
    public int indexOf(Object o){
    	if(o==null){
    		for(int i=0; i<size; i++){
    			if(elementData[i] == null)
    				return i;
    		}
    	}
    	else{
    		for(int i=0; i<size; i++){
    			if(o.equals(elementData[i]))
    				return i;
    		}
    	}
    	return -1;
    }
    public int lastIndexOf(Object o){
    	if(o==null){
    		for(int i=size-1; i>=0; i--){
    			if(elementData[i] == null)
    				return i;
    		}
    	}
    	else{
    		for(int i=size-1; i>=0; i--){
    			if(o.equals(elementData[i]))
    				return i;
    		}
    	}
    	return -1;
    }
}