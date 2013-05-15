abstract class AbstractList<E> extends AbstractCollection<E> implements List<E>{
	protected AbstractList(){

	}

	public boolean add(E e)
	{
		add(size(), e);
		return true;
	}

	//inherit from AbstractCollection
	public abstract boolean contains(Object o);
	public abstract boolean remove(Object o);
	public abstract void clear();

	//implements from List
	public abstract E get(int index);
	public abstract E set(int index, E element);
	public abstract void add(int index, E element);
	public abstract E remove(int index);
	public abstract int indexOf(Object o);
	public abstract int lastIndexOf(Object o);
}