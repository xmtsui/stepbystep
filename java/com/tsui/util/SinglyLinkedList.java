package com.tsui.util;
/**
 * 单链表 带头结点 基本功能实现
 * @author xmtsui
 * @version v1.0
 */
public class SinglyLinkedList<E> extends AbstractSequentialList<E> implements List<E>{

	//元素个数
	private transient int size = 0;
	//头结点
	private transient Node<E> head;

	public SinglyLinkedList(){
		
	}

	//这样的写法不推荐，尽量避免范型数组
	public SinglyLinkedList(E[] element)
	{
		int num = element.length;
		for(int i=0; i<num; ++i)
		{
			add(element[i]);
		}
	}
	
	//Query operations
	public int size(){
		return size;
	}

	//AbstractCollection中实现
	//boolean isEmpty();
	public boolean isEmpty(){
		return head == null;
	}
	
	//boolean contains(Object o);
	public boolean contains(Object o){
		return indexOf(o) != -1;
	}
    //Iterator<E> iterator();
    //Object[] toArray();
	
    //Modification Operations

	/**
	 * 根据元素新建一个node，再插入
	 * @param  element [description]
	 * @return true if added
	 */
	public boolean add(E element){
		if(head == null)
		{
			head = new Node<E>(element, null);
			size++;
		}
		else
		{
			Node<E> tmp = head;
			while(tmp.next != null)
				tmp=tmp.next;
			Node<E> newNode = new Node<E>(element, null);
			tmp.next = newNode;//插在最后
			size++;
		}
		return true;
	}

	/**
	 * Removes the first occurrence of the specified element from this list
	 * 根据node的元素内容来删除
	 * 注意考虑首尾节点的特殊处理
	 * for循环里面注意尾节点判断n.next!=null，而不是n
	 * @param  o
	 * @return true if removed
	 */
	public boolean remove(Object o){
		if(head == null)
			return false;
		if(o==null){
			if(head.item == null)//处理头结点
			{
				head = head.next;
				size--;
				return true;
			}
			for(Node<E> n=head; n.next!=null; n=n.next){
				if(n.next.item == null){
					n.next = n.next.next;//跳过一个节点
					size--;
					return true;
				}
			}
		}
		else{
			if(o.equals(head.item))//处理头结点
			{
				head = head.next;
				size--;
				return true;
			}
			for(Node<E> n=head; n.next!=null; n=n.next){
				if(o.equals(n.next.item)){
					n.next = n.next.next;//跳过一个节点
					size--;
					return true;
				}
			}
		}
		return false;
	}

    //Bulk operations
    //boolean containsAll(Collection<?> c);
    //boolean addAll(Collection<? extends E> c);
    //boolean removeAll(Collection<?> c);
    //boolean retainAll(Collection<?> c);
	public void clear(){
		while(head!=null && head.next != null)
		{
			Node<E> tmp = head.next;
			head.item = null;
			head.next = null;
			head = tmp;
		}
		head = null;
		size = 0;
	}

    //Compare and hashing

    //boolean equals(Object o);
    //int hashCode();
	
	//implements from interface List
	//Positional Access Operations

    /**
     * 获取index位置的值
     */
    public E get(int index){
    	if(index<0 || index>=size)//可以是0到size-1，>=0 || <size可以
    	throw new IndexOutOfBoundsException("Index: "+index+", Size: "+size);
    	Node<E> n = head;
    	for(int i=0; i<index; i++)
    	{
    		n = n.next;
    	}
    	return n.item;
    }

    /**
     * 改变Index位置的值，并返回之前的值
     */
    public E set(int index, E element){
    	if(index<0 || index>=size)//可以是0到size-1，>=0 || <size可以
    	throw new IndexOutOfBoundsException("Index: "+index+", Size: "+size);
    	Node<E> n = head;
    	for(int i=0; i<index; i++)
    	{
    		n=n.next;
    	}
    	E oldValue = n.item;
    	n.item = element;
    	return oldValue;
    }

    /**
     * 在index前插入新结点
     * 如果是0,需要对head单独处理
     */
    public void add(int index, E element){
    	if(index<0 || index>size)//可以是0到size,唯一一个可以以size作为index的。>=0 || <=size可以
    	throw new IndexOutOfBoundsException("Index: "+index+", Size: "+size);
    	Node<E> n = head;
    	if(head == null)//第一个结点
    	{
    		head = new Node<E>(element, null);
    		size++;
    	}
    	else
    	{
    		if(index == 0)//头节点单独处理
    		{
    			Node<E> newNode = new Node<E>(element, n);
    			head = newNode;
    			size++;
    		}
    		else
    		{
    			for(int i=0; i<index-1; i++)
    			{
    				n = n.next;
    			}
    			Node<E> newNode = new Node<E>(element, n.next);
    			n.next = newNode;
    			size++;
    		}
    	}
    }

	/**
     * 删除index处的结点
     */
	public E remove(int index){
		if(index<0 || index>=size)//可以是0到size-1，>=0 || <size可以
		throw new IndexOutOfBoundsException("Index: "+index+", Size: "+size);
		if(head == null)
			return null;
		//定位到index,所有的index都从0开始
		Node<E> pre = head;
		Node<E> current = head;

		//后来添加的，注意remove头节点的单独处理
		if(index == 0)
		{
			head = head.next;
			E oldValue = pre.item;
			pre.item = null;
			pre.next = null;
			size--;
			return oldValue;
		}
		else
		{
			for(int i=0; i<index; i++)
			{
				pre = current;
				current = current.next;
			}
			E oldValue = current.item;
			pre.next = current.next;
			current.item=null;
			current.next=null;
			size--;
			return oldValue;
		}
	}

	//Search Operations

	/**
     * 查找第一次出现某个元素的index，没有返回－1
     */
	public int indexOf(Object o){
		int index=0;
		if(head == null)
			return -1;
		if(o==null){
			for(Node<E> n=head; n!=null; n=n.next)
			{
				if(n.item == null)
					return index;
				index++;
			}
		}
		else{
			for(Node<E> n=head; n!=null; n=n.next)
			{
				if(o.equals(n.item))
					return index;
				index++;
			}
		}
		return -1;
	}

    /**
     * 查找倒数第一次出现某个元素的index，没有返回－1
     * 全部遍历完
     * 注意判断对象内容相等的方法，曾犯过错误
     * 不是o == n.item
     * 而是o.equals(n.item)
     */
    public int lastIndexOf(Object o){
    	int index=0;
    	int result=-1;
    	if(head == null)
    		return -1;
    	if(o == null)
    	{
    		for(Node<E> n=head; n!=null; n=n.next)
    		{
    			if(n.item == null)
    				result = index;
    			index++;
    		}
    	}
    	else
    	{
    		for(Node<E> n=head; n!=null; n=n.next)
    		{
    			if(o.equals(n.item))
    				result = index;
    			index++;
    		}
    	}
    	return result;
    }

	//结点定义,注意分析static的意义，区分内部类，嵌套类
	//private static class Node<E>
    public static class Node<E> {
    	public E item;
    	public Node<E> next;

    	public Node(E element, Node<E> next) {
    		this.item = element;
    		this.next = next;
    	}
    }
    
    /**
     * 临时代替iterator来遍历
     */
    public void doTraverse(){
    	int len = size();
    	System.out.print("The list is: { ");
    	for(int i=0; i<len; ++i)
    	{
    		System.out.print(+ i + "[" + get(i) + "] ");
    	}
    	System.out.print("}\n");
    }

    //自己新加的方法
    //
	/**
	 * 获取某个index的节点
	 */
	public Node<E> getNode(int index)
	{
		if(index<0 || index>size)
			throw new IndexOutOfBoundsException("Index: "+index+", Size: "+size);
		Node<E> runner = head;
		for(int i=0; i<index; ++i)
		{
			runner = runner.next;
		}
		return runner;
	}

	/**
	 * 删除节点后，size-1
	 */
	public void decreaseSize()
	{
		if(size>0)
			size--;
	}

	public Node<E> getHead()
	{
		return head;
	}
}