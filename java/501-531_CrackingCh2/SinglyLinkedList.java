/**
 * 单链表 带头结点 基本功能实现
 * @author xmtsui
 * @version v1.0
 */
class SinglyLinkedList<E> extends AbstractSequentialList<E> implements List<E>{

	//元素个数
	transient int size = 0;
	
	//头结点
	transient Node<E> head;

	public SinglyLinkedList(){
	}

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

	//insert behind last
	public boolean add(E e){
		if(head == null)
		{
			head = new Node<E>(e, null);
			size++;
		}
		else
		{
			Node<E> tmp = head;
			while(tmp.next != null)
				tmp=tmp.next;
			Node<E> newNode = new Node<E>(e, null);
			tmp.next = newNode;//插在最后
			size++;
		}
		return true;
	}

	/**
	 * Removes the first occurrence of the specified element from this list
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
			for(Node<E> n=head; n!=null; n=n.next){
				if(n.next.item == null){
					n.next = n.next.next;
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
			for(Node<E> n=head; n!=null; n=n.next){
				if(o.equals(n.next.item)){
					n.next = n.next.next;
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
	
	//implements from List
	//Positional Access Operations

    /**
     * 获取index位置的值
     */
    public E get(int index){
    	if(index<0 || index>=size)//可以是0到size-1
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
    	if(index<0 || index>=size)//可以是0到size-1
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
     */
    public void add(int index, E element){
    	if(index<0 || index>size)
    		throw new IndexOutOfBoundsException("Index: "+index+", Size: "+size);
    	Node<E> n = head;
    	if(head == null)//第一个结点
    	{
    		head = new Node<E>(element, null);
    		size++;
    	}
    	else
    	{
    		if(index == 0)
    		{
    			Node<E> newNode = new Node<E>(element, n);
    			head = newNode;
    			size++;
    		}
    		else
    		{
    			for(int i=0; i<index; i++)
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
		if(index<0 || index>size)
			throw new IndexOutOfBoundsException("Index: "+index+", Size: "+size);
		if(head == null)
			return null;
		//定位到index-1
		Node<E> tmp = head;
		for(int i=0; i<index-1; i++)
		{
			tmp = tmp.next;
		}
		E oldValue = tmp.item;
		tmp.next = tmp.next.next;
		size--;
		return oldValue;
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
     * 无尾巴节点的情况下无法实现
     */
    public int lastIndexOf(Object o){
    	return -2;
    }

	//结点定义,注意分析static的意义，区分内部类，嵌套类
    static class Node<E> {
    	E item;
    	Node<E> next;

    	Node(E element, Node<E> next) {
    		this.item = element;
    		this.next = next;
    	}
    }
}