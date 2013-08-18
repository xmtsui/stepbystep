package com.tsui.util;
/**
 * 链式的队列实现
 *
 * 注意：toString若没有覆盖重写，则输出对象名， 如com.tsui.util.LinkedQueue@c3bb2b8
 * 
 * buglist:
 * enqueue,dequeue实现从尾节点开始遍历，对应toString1()
 * offer,poll实现从头节点开始遍历，对应toString2()
 * 
 * @author xmtsui
 * @version v1.0
 */
import java.util.NoSuchElementException;
public class LinkedQueue<E>{

	/*头指针*/
	private Node<E> first;
	/*尾指针*/
	private Node<E> last;
	/*队列长度*/
	private int size;

	public LinkedQueue()
	{
	}
	
	/**
	 * 从队尾入队
	 * 此实现没有容量限制，且允许空
	 * @param  element [description]
	 * @return         [description]
	 */
	public boolean enqueue(E element)
	{
		Node<E> newNode = new Node<E>(element, last);
		last = newNode;
		//若入队时栈空，特殊处理头指针
		if(size==0)
			first = newNode;
		size++;
		return true;
	}

	/**
	 * 从队首出队
	 * @return [description]
	 * @throws NoSuchElementException 空的话抛出异常
	 */
	public E dequeue()
	{
		if(first==null)
			throw new NoSuchElementException();
		Node<E> tmp = last;
		
		while(tmp.next.next != null)
			tmp = tmp.next;
		
		Node<E> oldFirst = tmp.next;

		first = tmp;
		size--;

		//若出队后栈空，则特殊处理尾指针
		if(size==0)
			last = first = null;
		E oldValue = oldFirst.element;
		
		oldFirst.element = null;
		oldFirst.next = null;
		oldFirst = null;//所有的引用都要清除，内存才会被回收
		tmp.next = null;//所有的引用都要清除，内存才会被回收
		return oldValue;
	}

	/**
	 * enqueue,dequeue的toString
	 * 
	 * @return [description]
	 */
	public String toString1()
	{
		Node<E> tmp = last;
		StringBuilder sb = new StringBuilder();
		while(tmp != null)
		{
			sb.append(tmp.element);
			tmp = tmp.next;
		}
		return new String(sb);
	}

	/**
	 * 入队
	 * 若队列为空，特殊处理头节点
	 * 
	 * @param  element [description]
	 * @return         入队成功返回 true
	 * 
	 */
	public boolean offer(E element)
	{
		Node<E> oldLast = last;
		Node<E> newNode = new Node<E>(element, null);
		last = newNode;
		if(oldLast == null)
			first = newNode;
		else
			oldLast.next = newNode;
		size++;
		return true;
	}

	/**
	 * 出队
	 * 考虑队首是否为空
	 * 若删除后为空，特殊处理头尾节点
	 * 删除已删除节点的引用，调用内存回收
	 * @return 若为空返回null，不为空返回旧节点的值
	 */
	public E poll()
	{
		if(first == null)
			return null;

		Node<E> oldFirst = first;
		first = first.next;
		E oldValue = oldFirst.element;

		size--;
		if(size == 0)
			first = last = null;

		oldFirst.element = null;
		oldFirst.next = null;
		oldFirst = null;

		return oldValue;
	}

	/**
	 * offer,poll的toString
	 * 
	 * @return [description]
	 */
	public String toString2()
	{
		Node<E> tmp = first;
		StringBuilder sb = new StringBuilder();
		while(tmp != null)
		{
			sb.append(tmp.element);
			tmp = tmp.next;
		}
		return new String(sb);
	}

	/**
	 * 获取队头
	 * 还可以实现一个element()函数，空的话抛出异常
	 * @return 空的话返回null
	 */
	private E peek()
	{
		final Node<E> tmp = first;
		return (tmp==null) ? null : tmp.element;
	}

	/**
	 * [peekFirst description]
	 * @return 空的话返回null
	 */
	public E peekFirst()
	{
		return peek();
	}

	/**
	 * [peekLast description]
	 * @return 空的话返回null
	 */
	public E peekLast()
	{
		final Node<E> tmp = last;
		return (last==null) ? null : tmp.element;
	}

	/**
	 * [size description]
	 * @return [description]
	 */
	public int size()
	{
		return size;
	}

	/**
	 * [isEmpty description]
	 * @return [description]
	 */
	public boolean isEmpty()
	{
		return size==0;
	}

	/**
	 * Node class
	 */
	private static class Node<E> {
		E element;
		Node<E> next;

		Node(E element, Node<E> next)
		{
			this.element = element;
			this.next = next;
		}
	}
}