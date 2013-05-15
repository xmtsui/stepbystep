import java.util.Arrays;
class ArrayList<E> extends AbstractList<E> implements List<E>{
	private transient Object[] elementData;
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

	public int size(){
		return size;
	}

	//inherit from AbstractCollection
	public boolean contains(Object o){
		return indexOf(o) >= 0;
	}

	E elementData(int index){
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

    private static int hugeCapacity(int minCapacity) {
        if (minCapacity < 0) // overflow
            throw new OutOfMemoryError();
        return (minCapacity > MAX_ARRAY_SIZE) ?
            Integer.MAX_VALUE :
            MAX_ARRAY_SIZE;
    }

    //collection oper
	// public boolean add(E e){
	// 	ensureCapacityInternal(size+1);
	// 	elementData[size++] = e;
	// 	return true;
	// }

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

	public void fastRemove(int index){
		System.arraycopy(elementData, index+1, elementData, index, size-index-1);
		elementData[--size] = null;//let gc works
	}

	public void clear(){
		for(int i=0; i<size; i++)
		{
			elementData[i] = null;
		}
		size = 0;
	}

	//List oper
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
		System.arraycopy(elementData, index+1, elementData, index, size-index-1);
		elementData[--size] = null;
		return oldValue;
	}

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