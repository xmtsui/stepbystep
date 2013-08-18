package com.tsui.util;
/**
 * 集合抽象类
 * @author xmtsui
 * @version v1.0
 */
abstract class AbstractCollection<E> implements Collection<E>{
	protected AbstractCollection(){
	}

	//Query operations
	public abstract int size();

	public boolean isEmpty(){
		return size() == 0;
	}

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
}