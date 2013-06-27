package com.tsui.util;
/**
 * 继承自vector，线性结构实现的Stack
 * 
 * @author xmtsui
 * @version v1.0
 */
import java.util.EmptyStackException;
public class Stack<E> extends Vector<E>{
	public Stack()
	{

	}

	/**
	 * 在vector末尾添加，相当于堆栈栈顶
	 * @param  element [description]
	 * @return         [description]
	 */
	public E push(E element)
	{
		add(element);
		return element;
	}

	/**
	 * 从vector末尾弹出，相当于堆栈栈顶
	 * @return [description]
	 */
	public synchronized E pop()
	{
		E obj=null;
		int len = size();
		obj=peek();
		removeElementAt(len-1);
		return obj;
	}

	/**
	 * 获取堆栈栈顶元素
	 * @return [description]
	 */
	public synchronized E peek()
	{
		int len = size();
		if(len == 0)
			throw new EmptyStackException();
		return elementData(len-1);
	}

	/**
	 * 判断栈是否为空
	 * @return [description]
	 */
	public boolean isEmpty()
	{
		return elementCount == 0;
	}

	/**
	 * 获取当前栈大小
	 * @return [description]
	 */
	public int size()
	{
		return elementCount;
	}

	/**
	 * 打印栈，方便测试
	 */
	public void printString()
	{
		System.out.println("Stack: ");
		for(int i=size()-1; i>=0; --i)
		{
			if(i!=0)
				System.out.print(elementData[i]+"<-");
			else
				System.out.print(elementData[i]);
		}
		System.out.println();
	}
	
	/*
	 *  泛型类无法继承 java.lang.Throwable
	private class EmptyStackException extends RuntimeException{
		 public EmptyStackException() {}
	}
	*/
}
