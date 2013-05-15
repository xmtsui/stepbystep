abstract class AbstractCollection<E> implements Collection<E>{
	protected AbstractCollection(){

	}

	//Query Operations
	public abstract int size();
	
	public boolean isEmpty(){
		return size() == 0;
	}

	public abstract boolean contains(Object o);
	public abstract boolean add(E e);
	public abstract boolean remove(Object o);
	public abstract void clear();
}