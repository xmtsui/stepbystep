/**
 * 集合接口
 * @author xmtsui
 * @version v1.0
 */
interface Collection<E>{
	//Query operations
	int size();
	boolean isEmpty();
	boolean contains(Object o);
	//Iterator<E> iterator();
	//Object[] toArray();
	
	//Modification Operations
	boolean add(E e);
	boolean remove(Object o);

	//Bulk operations
	//boolean containsAll(Collection<?> c);
	//boolean addAll(Collection<? extends E> c);
	//boolean removeAll(Collection<?> c);
	//boolean retainAll(Collection<?> c);
	void clear();

	//Compare and hashing
	//boolean equals(Object o);
	//int hashCode();
}