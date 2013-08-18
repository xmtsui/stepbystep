package com.tsui.util;
/**
 * 链式的栈实现
 * @author xmtsui
 * @version v1.0
 */
import java.util.NoSuchElementException;
public class LinkedStack<E> {
	
	private Node<E> first;

	private int size;

	/**
	 * constructor
	 */
	public LinkedStack()
	{

	}

	/**
	 * 在头部压入元素，重新指定头指针
	 * 在这里该实现，允许空，没有堆栈空间限制
	 * @param  element [description]
	 * @return         [description]
	 */
	public boolean push(E element)
	{
		Node<E> tmp = new Node<E>(element, first);
		first = tmp;
		size++;
		return true;
	}

	/**
	 * 弹出头部节点内容，头指针指向下一个
	 * @return [description]
	 * @throws NoSuchElementException 空的话抛出异常
	 */
	public E pop()
	{
		if(first == null)
			throw new NoSuchElementException();
		Node<E> tmp = first;
		first=first.next;
		E oldValue = tmp.element;
		
		tmp.element = null;
		tmp.next = null;
		tmp = null;
		size--;
		return oldValue;
	}

	/**
	 * 获取头部节点内容
	 * @return 空的话返回null
	 */
	public E peek()
	{
		final Node<E> f = first;
		return (f==null) ? null : f.element;
	}

	/**
	 * 判断是否为空
	 * @return [description]
	 */
	public boolean isEmpty()
	{
		return size==0;
	}

	/**
	 * 获取栈大小
	 * @return [description]
	 */
	public int size()
	{
		return size;
	}

	/**
	 * 
	 */
	public String toString()
	{
		Node<E> tmp = first;
		StringBuilder sb = new StringBuilder();
		while(tmp!=null)
		{	
			sb.append(tmp.element);
			tmp = tmp.next;
		}
		return new String(sb);
	}

	private static class Node<E> {
		E element;
		Node<E> next;

		Node(E element, Node<E> next){
			this.element=element;
			this.next=next;
		}
	}
}