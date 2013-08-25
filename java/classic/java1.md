#JAVA程序员面试42问  

#一. 谈谈final， finally， finalize的区别。 
	final—修饰符（关键字）
	1）如果一个类被声明为final，意味着它不能再派生出新的子类，不能作为父类被继承。因此一个类不能既被声明为 abstract的，又被声明为final的。  
	2）将变量或方法声明为final，可以保证它们在使用中不被改变。被声明为final的变量必须在声明时给定初值，而在以后的引用中只能读取，不可修改。被声明为final的方法也同样只能使用，不能被Override，但可以Overload
	  
	finally
	再异常处理时提供 finally 块来执行任何清除操作。如果抛出一个异常，那么相匹配的 catch 子句就会执行，然后控制就会进入 finally 块（如果有的话）。 
	
	finalize—方法名
	它是在 Object 类中定义的，因此所有的类都继承了它。子类覆盖finalize()方法以整理系统资源或者执行其他清理工作。用户可以自己调用对象的finalize方法，但是这种调用是正常的方法调用，和对象的销毁过程无关。 JVM保证在一个对象所占用的内存被回收之前，如果它实现了finalize方法，则该方法一定会被调用。Object的默认finalize什么都不做，为了效率，GC可以认为一个什么都不做的finalize不存在。 

#二. Anonymous Inner Class （匿名内部类） 是否可以extends（继承）其它类，是否可以implements（实现）interface（接口）？ 
	匿名的内部类是没有名字的内部类。不能extends（继承） 其它类，注意是其他类。
	Person queen = new Person("Mary");//Person的一个实例
	Person count = new Person("Dracula"){};//继承自Person的匿名内部类的一个实例。
	以上可以看出匿名内部类在实现时必须借助一个类或一个接口，若从这个层次上讲它是可以继承其他类也可以实现接口的。但若是通过extends或implements关键字那是不可能的

#三. Static Nested Class 和 Inner Class的不同，说得越多越好（面试题有的很笼统）。
 
##首先看下C++中嵌套的概念，嵌套是一种类之间的关系，而不是对象之间的关系。嵌套类好处：
	1）命名控制：LinkedList.Iterator不会与其他名为Iterator的类冲突.
	2）访问控制：嵌套类如果声明为private，则内部的公有域不能暴露给其他类，只有其外围类可以访问。

##嵌套类分两种
	静态嵌套类：也叫静态内部类
	内部类：除静态内部类以外的嵌套类
	内部类是一种对象之间的关系，内部类有一个隐式引用指向外围类对象。而嵌套类没有，java嵌套类更接近C++嵌套类。  

##静态嵌套类与内部类的区别：
	1. 生成一个静态嵌套类对象不需要外围类实例；内部类需要
	
	class Outer{
		class Inner{
		}
	}//内部类
	
	class Outer{
		static class Inner{
		}
	}//静态嵌套类
	
	静态嵌套类
	Outer.Inner in = new Outer.Inner();
	
	内部类
	Outer outer = new Outer();
	Outer.Inner in = outer.new Inner();

	2. 内部类可以有静态常量，不能有静态成员变量；而静态嵌套类没有限制
	class Inner {
     static final int x = 3;  // OK: compile-time constant
     //static int y = 4;  // Compile-time error: an inner class
    }
	［这个是Java语言规范规定的内容，java中规定，只有在静态或顶级类型中才可以声明静态字段。其次，你可以想象一下如何去指向这个静态成员。非静态内部类是通过外部类的this指针指向的。那么对于非静态内部类的动态成员，可以通过两个this指针确定位置。但是如果允许静态成员会出现什么情况？首先，静态成员是不能通过任何this指针的，但是为了确定这个成员，必须确定这个成员所在的类实例。但是这个实例必须通过一个this指针确定。这在语法以及处理上都会造成很大的麻烦］

	3. 内部类的实现是一种语法糖，是编译器现象,与虚拟机无关。
	编译后为了让内部类引用外围类，生成了一个附加的实例域
	final Outer this$0
	Outer$Inner(Outer);//构造器指定了传入外围类的对象参数
	
	所以当Outer outer = outer.new Inner();的时候翻译成
	outer.new Inner(outer);

	4.	内部类可以访问外围类的所有实例域以及类域；静态嵌套类可以访问外围类的静态域。私有的也可以访问。
	参考这个gist:
	https://gist.github.com/xmtsui/6304728
	
	5. 嵌套类如何访问外围类的私有变量的呢？？
	如果外围类有一个私有变量，内部类或者静态嵌套类都想去访问的话。在外围类中都会如下优化编译：
	静态域的话会生成 －static int access$100();
	实例	域的话会生成 －static int access$000(Outer);

	所以，if(Outer.this.privatefield)
	当在内部类中访问的时候翻译成 if(access$000(Outer.this))
	if(Outer.privatestaticfield)
	当在静态嵌套类中访问的时候翻译成	if(Outer.access$100())
	
	5. 外围类如何实例化私有嵌套类的呢？？
	如果内部类与静态嵌套类都声明为private，则都产生一个包可见构造函数
	在内部类中会生成 －Outer$Inner(Outer, Outer$1);构造器
	在静态嵌套类中会生成 －Outer$Inner(Outer$1);构造器
	Outer$1是一个临时生成的类, 
	
	所以如果在外围类中实例化的话
	outer.new Inner()的时候会翻译成 new Outer$Inner(outer, null);
	new Inner()的时候则翻译成new Outer$Inner(null);

**JLS定义**  
	在java中，类声明的表现形式可以分为两种：  
	1）top level class  
	2）nested class  
	*nestd class* is any class whose declaration occurs within the body of another class or interface.top level class is a class that is not a nested class.  
	*inner class* is a nested class that is not explicitly or implicitly declared static. Inner classes may not declare static initializers (§8.7) or member interfaces

#四，&和&&的区别。 
	&是位运算符。&&是布尔逻辑运算符。 
#五，HashMap和Hashtable的区别.  
    
	相同
	都实现了将惟一键映射到特定的值上。 
	HashMap是Java1.2引进的Map interface的一个实现
	Hashtable继承自Dictionary类，也实现了Map接口
	都用链表法解决hash冲突
	
	1.HashTable的方法是同步的，HashMap未经同步，所以在多线程场合要手动同步HashMap这个区别就像Vector和ArrayList一样。
	
	2.HashTable不允许null值(key和value都不可以),HashMap允许null值(key和value都可以，null key只有一个)。
	
	3.HashTable有一个contains(Object value)，功能和containsValue(Object value)功能一样。
	HashMap去除了容易产生歧义的contains方法。	
	HashMap中的get(Object key)方法返回null，可能表示 key mapping不存在，也可能表示key的value是null, 使用containsKey来判断某个键是否存在。
	HashTable中的get(Object key)方法返回null,只表示key不存在，没有value null 的情况
	
	4.遍历方法不一样
	hashtable
	public synchronized Enumeration<K> keys()
	public synchronized Enumeration<V> elements()
	
	hashmap
	public Set<K> keySet() 
	public Collection<V> values()
	public Iterator<Map.Entry<K,V>> iterator()
	*Map.Entry<K,V>是比较少见的定义在接口中的内部接口*
	
	5.HashTable中hash数组默认大小是11，增加的方式是 old*2+1。HashMap中hash数组的默认大小是16，而且一定是2的指数。
	
	6.哈希值的使用不同，HashTable直接使用对象的hashCode，代码是这样的：
	int hash = key.hashCode();
	int index = (hash & 0x7FFFFFFF) % tab.length;
	
	而HashMap重新计算hash值，而且用与代替求模：
	int hash = hash(k);
	int i = indexFor(hash, table.length);
	static int hash(Object x) {
		int h = x.hashCode();
		h += ~(h << 9);
		h ^= (h >>> 14);
		h += (h << 4);
		h ^= (h >>> 10);
		return h;
	}
	static int indexFor(int h, int length) 
	{
		return h & (length-1);
	}
	
	7. 监测hash冲突的语句略有差别
	hashmap
	if (e.hash == hash && ((k = e.key) == key || key.equals(k)))
	hashtable
	if ((e.hash == hash) && e.key.equals(key))
    
#六，Collection 和 Collections的区别。 
	Collections是个java.util下的类，它包含有各种有关集合操作的静态方法。 
	Collection是个java.util下的接口，它是各种集合结构的父接口。 

#七，什么时候用assert. 
	断言是一个包含布尔表达式的语句，在执行这个语句时假定该表达式为 true.如果表达式计算为 false，那么系统会报告一个 AssertionError.它用于调试目的：assert（a > 0）； // throws an AssertionError if a <= 0断言可以有两种形式：assert Expression1 ；assert Expression1 ： Expression2 ；Expression1 应该总是产生一个布尔值。 
	Expression2 可以是得出一个值的任意表达式。这个值用于生成显示更多调试信息的 String 消息。 
	
	断言在默认情况下是禁用的。要在编译时启用断言，需要使用 source 1.4 标记：javac -source 1.4 Test.java要在运行时启用断言，可使用 -enableassertions 或者 -ea 标记。 
	要在运行时选择禁用断言，可使用 -da 或者 -disableassertions 标记。 
	要系统类中启用断言，可使用 -esa 或者 -dsa 标记。还可以在包的基础上启用或者禁用断言。 
	可以在预计正常情况下不会到达的任何位置上放置断言。断言可以用于验证传递给私有方法的参数。不过，断言不应该用于验证传递给公有方法的参数，因为不管是否启用了断言，公有方法都必须检查其参数。不过，既可以在公有方法中，也可以在非公有方法中利用断言测试后置条件。另外，断言不应该以任何方式改变程序的状态。 

#八，GC是什么？ 为什么要有GC？ 

	GC是垃圾收集器。Java 程序员不用担心内存管理，因为垃圾收集器会自动进行管理。要请求垃圾收集，可以调用下面的方法之一：
	System.gc（） 
	Runtime.getRuntime().gc()

#九，String s = new String（"xyz"）；创建了几个String Object？ 
	两个对象，一个是“xyx”，一个是指向“xyx”的引用对象s. 

#十，Math.round（11.5）等於多少？Math.round（-11.5）等於多少？
	Math.round（11.5）返回（long）12，Math.round（-11.5）返回（long）-11； 

#十一，short s1 = 1； s1 = s1 + 1；有什么错？ short s1 = 1； s1 += 1；有什么错？ 
	short s1 = 1； s1 = s1 + 1；有错，s1是short型，s1+1是int型，不能显式转化为short型。可修改为s1 =（short）（s1 + 1） .short s1 = 1； s1 += 1正确。 JAVA规范上说     e1+=e2     实际上是     e1=(T1)(e1+e2)，其中T1是e1的数据类型。   s1+=1等效于   s1=(short)(s1+1),所以是正确的。
	
#十二，sleep（） 和 wait（） 有什么区别？ 
	
	sleep是线程类（Thread）的方法，导致此线程暂停执行指定时间，给执行机会给其他线程，但是监控状态依然保持，到时后会自动恢复。调用sleep不会释放对象锁。  
	
	wait是Object类的方法，对此对象调用wait方法导致本线程放弃对象锁，进入等待此对象的等待锁定池，只有针对此对象发出notify方法（或notifyAll）后本线程才进入对象锁定池准备获得对象锁进入运行状态。
	
	先说wait，wait的本质是条件等待，这里涉及到了锁的概念（也就是多线程中要保证线程安全的锁）。所谓条件等待就是已经获得了锁的线程，由于需要满足某种条件才能继续执行，而当前不满足条件，所以只能等待。所以调用wait的一个前提条件就是要先拿到锁。拿到锁的线程，wait之后，该线程就进入条件等待队列，并且释放锁，让其他线程执行。当其他线程的执行使得条件满足之后，再调用notify或者notifyAll方法，将条件等待队列中的线程唤醒，这些线程再去请求锁，拿到锁的线程接着去执行。建议你看一下ReentrantLock类，顺便用一下Condition类，看一看《Java核心技术》第二卷关于线程那一章的讲解。
	
	调用wait()/wait(long t)线程进入Waiting/TimeWaiting状态。sleep（long t）的意思是暂停当前线程t毫秒，当然，其他线程就得到了时间片，sleep与线程是否获得锁无关。调用sleep线程进入TimeWaiting状态。这里有张比较基础的图，你看一下 
	-----	-----	-----	-----	-----	-----
	 搞线程的最爱sleep（）方法是使线程停止一段时间的方法。在sleep 时间间隔期满后，线程不一定立即恢复执行。这是因为在那个时刻，其它线程可能正在运行而且没有被调度为放弃执行，除非（a）“醒来”的线程具有更高的优先级（b）正在运行的线程因为其它原因而阻塞。 
	 wait（）是线程交互时，如果线程对一个同步对象x 发出一个wait（）调用，该线程会暂停执行，被调对象进入等待状态，直到被唤醒或等待时间到。 

#十三，Java有没有goto？ 
	Goto—java中的保留字，现在没有在java中使用。 

#十四，数组有没有length（）这个方法？ String有没有length（）这个方法？ 

	数组没有length（）这个方法，有length的属性。 
	String有length（）这个方法。 
	List size()

#十五，Overload和Override的区别。Overloaded的方法是否可以改变返回值的类型？ 
	方法的重写Overriding和重载Overloading是Java多态性的不同表现。重写Overriding是父类与子类之间多态性的一种表现，重载Overloading是一个类中多态性的一种表现。如果在子类中定义某方法与其父类有相同的名称和参数，我们说该方法被重写 （Overriding）。子类的对象使用这个方法时，将调用子类中的定义，对它而言，父类中的定义如同被“屏蔽”了。如果在一个类中定义了多个同名的方法，它们或有不同的参数个数或有不同的参数类型，则称为方法的重载（Overloading）。Overloaded的方法是可以改变返回值的类型。 

#十六，Set里的元素是不能重复的，那么用什么方法来区分重复与否呢？ 是用==还是equals（）？ 它们有何区别？ 
	
	通过对于这两个方法的使用: equals和hashcode，在set 集合框架中体现是最突出了。1，两个对象equals相等那么hashcode 是一定相等的。2，两个对象equals不相等hashcode可能相等可以不相等。
	
	这两个方法的调用原则是： 一、先去调用hashcode方法比较，如果相等的情况下再去调用equals方法比较，这时equals相等那边认为是同一个对象，则不同对象。二、先去调用hashcode方法不相等了，这样就不会去调用equals方法做比较的。所以，我们在写程序时一般会重写这两个方法。【http://www.haoxianggo.com】
	------------
	Set里的元素是不能重复的，那么用iterator（）方法来区分重复与否。equals（）是判读两个Set是否相等。 
	equals（）和==方法决定引用值是否指向同一对象equals（）在类中被覆盖，为的是当两个分离的对象的内容和类型相配的话，返回真值。 
#十七，给我一个你最常见到的runtime exception. 
	
	UncheckedException: nullpointer, indexoutofbounds, classcastexception
	checked exception: ClassNotFoundException, IOException
	Error: OOME, StackOverFlowError
	
	===========
	ArithmeticException， ArrayStoreException， BufferOverflowException， BufferUnderflowException， CannotRedoException， CannotUndoException， ClassCastException， CMMException， ConcurrentModificationException， DOMException， EmptyStackException， IllegalArgumentException， IllegalMonitorStateException， IllegalPathStateException， IllegalStateException，ImagingOpException， IndexOutOfBoundsException， MissingResourceException， NegativeArraySizeException， NoSuchElementException， NullPointerException， ProfileDataException， ProviderException， RasterFormatException， SecurityException， SystemException， UndeclaredThrowableException， UnmodifiableSetException， UnsupportedOperationException 

#第十八，error和exception有什么区别？ 
	error 表示恢复不是不可能但很困难的情况下的一种严重问题。比如说内存溢出。不可能指望程序能处理这样的情况。 
	exception 表示一种设计或实现问题。也就是说，它表示如果程序运行正常，从不会发生的情况。 

#第十九，List， Set， Map是否继承自Collection接口？ 
	List，Set是 
	Map不是 

#第二十，abstract class和interface有什么区别？ 
	声明方法的存在而不去实现它的类被叫做抽象类（abstract class），它用于要创建一个体现某些基本行为的类，并为该类声明方法，但不能在该类中实现该类的情况。不能创建abstract 类的实例。然而可以创建一个变量，其类型是一个抽象类，并让它指向具体子类的一个实例。不能有抽象构造函数或抽象静态方法。Abstract 类的子类为它们父类中的所有抽象方法提供实现，否则它们也是抽象类为。取而代之，在子类中实现该方法。知道其行为的其它类可以在类中实现这些方法。 
	接口（interface）是抽象类的变体。在接口中，所有方法都是抽象的。多继承性可通过实现这样的接口而获得。接口中的所有方法都是抽象的，没有一个有程序体。接口只可以定义static final成员变量。接口的实现与子类相似，除了该实现类不能从接口定义中继承行为。当类实现特殊接口时，它定义（即将程序体给予）所有这种接口的方法。然后，它可以在实现了该接口的类的任何对象上调用接口的方法。由于有抽象类，它允许使用接口名作为引用变量的类型。通常的动态联编将生效。引用可以转换到接口类型或从接口类型转换，instanceof 运算符可以用来决定某对象的类是否实现了接口。 

#第二十一，abstract的method是否可同时是static，是否可同时是native，是否可同时是synchronized？ 
	都不能 

#第二十二，接口是否可继承接口？ 抽象类是否可实现（implements）接口？ 抽象类是否可继承实体类（concrete class）？ 
	接口可以继承接口。抽象类可以实现（implements）接口，抽象类是否可继承实体类，但前提是实体类必须有明确的构造函数。 

#第二十三，启动一个线程是用run（）还是start（）？ 
	启动一个线程是调用start（）方法，使线程所代表的虚拟处理机处于可运行状态，这意味着它可以由JVM调度并执行。这并不意味着线程就会立即运行。run（）方法可以产生必须退出的标志来停止一个线程。 

#第二十四，构造器Constructor是否可被override？ 
	构造器Constructor不能被继承，因此不能重写Overriding，但可以被重载Overloading. 

#第二十五，是否可以继承String类？ 
	String类是final类故不可以继承。 

#第二十六，当一个线程进入一个对象的一个synchronized方法后，其它线程是否可进入此对象的其它方法？ 
	不能，一个对象的一个synchronized方法只能由一个线程访问。 

#第二十七，try {}里有一个return语句，那么紧跟在这个try后的finally {}里的code会不会被执行，什么时候被执行，在return前还是后？ 
	会执行，在return前执行

#第二十八，编程题： 用最有效率的方法算出2乘以8等於几？ 
	有C背景的程序员特别喜欢问这种问题。 
	2 << 3 

#第二十九，两个对象值相同（x.equals（y） == true），但却可有不同的hash code，这句话对不对？ 
	不对，有相同的hash code. 
	因此重新equal步骤： 
	1） ＝＝比较 
	2）instanceof (此句包含的null的比较，因为null不是任何object的实例) 
	3）attribute比较 
	hashcode的作用：为了便于对象的检索，Set内部对象是用哈希表来存储，同时已对象的hashcode做key.因此比较重复对象的时候，首先首先通过hashcode检索对应的位置，如果有多个元素，则通过equal方法判断。因此两个对象equal，但没有相同的hashcode,就会造成重复的存储，破坏了set的原则。也可以这样理解，[b]相等的对象(equal)一定要存储在同一个位置(hashcode)。[/b] 
	JDK中对于hashcode的限定： 
	1）一致性，多次调用同一对象，返回的hashcode应该是一样的 
	2）两个对象equal,一定要有相同的hashcode 
	3) 两个不equal的对象，不需要一定不同的hashcode 
	总结来说，就是对象equal,必须有相同的hashcode;有相同的hashcode,并不一定equal. 
	因此重写equal的时候一定要重写hashcode. 

#三十，当一个对象被当作参数传递到一个方法后，此方法可改变这个对象的属性，并可返回变化后的结果，那么这里到底是值传递还是引用传递？ 
	是值传递。Java 编程语言只由值传递参数。当一个对象实例作为一个参数被传递到方法中时，参数的值就是对该对象的引用。对象的内容可以在被调用的方法中改变，但对象的引用是永远不会改变的。 

#三十一，swtich是否能作用在byte上，是否能作用在long上，是否能作用在String上？ 
	switch（expr1）中，expr1是一个整数表达式。因此传递给 switch 和 case 语句的参数应该是 int、 short、 char 或者 byte。long，string 都不能作用于swtich. 

#三十二，编程题： 写一个Singleton出来。 

#三十三  STRING与STRINGBUFFER的区别
	STRING的长度是不可变的，一旦一个String对象被创建，包含在这个实例中的内容(“字符串”)不可以被更改，直至这个对象被销毁，因此， 指向一个 String对象的变量实质上是一个常量，String对象也被称为常量对象;STRINGBUFFER的长度是可变的，可以通过 StringBuffer的append()、insert()、reverse()、setCharAt()、setLength()等方法，可以对这 个字符串中的内容修改。如果你对字符串中的内容经常进行操作，特别是内容要修改时，那么使用StringBuffer，如果最后需要String，那么使 用StringBuffer的toString()方法
	
#三十四	JAVA类实现序例化的方法是?
	JAVA类要实现序列化，对应的类必须先实现Serializable和Externalizable这两种接口，然后就可以调用 ObjectOutputStream的witeObject()方法来保存对象以及通过ObjeatInputStream的 readObjeact()方法来读取保存的对象。
	
#三十五 Collection框架中实现比较要实现什么接口?
	要实现Comparable接口，并实现这个接口的唯一方法cpmparaTo()，接受一个Object对象，在这个方法中可以定义对象的排序规则。
	
#三十六 介绍JAVA中的Collection 框架结构 , 并画出来
<>Collection 
　　<>Set 
　　<>List 
　　HashSet 
　　Tree set 
　　ArrayList 
　　Vector

#三十七 多线程有几种实现方法,都是什么?同步有几种实现方法,都是什么?
	多线程有三种实现方法，分别为：
	① 实现Runnable接口，覆盖Run()方法。
	② 继承Thread，覆盖Run()方法。
	③ 继承TimerTask，覆盖Run()方法。
	同步的实现是在方法前加synchronized，在调用wait()和notify()。
#三十八 请说出你所知道的线程同步的方法
	1. synchronized 方法：通过在方法声明中加入 synchronized关键字来声明 synchronized 方法。
	2. synchronized 块：通过 synchronized关键字来声明synchronized 块。
#三十九 当一个线程进入一个对象的一个synchronized方法后，其它线程是否可进入此对象的其它方法?
	不可以。synchronized 方法都必须获得调用该方法的类实例的锁方能执行，否则所属线程阻塞，方法一旦执行，就独占该锁，直到从该方法返回时才将锁释放，此后被阻塞的线程才能获得该锁，重新进入可执行状态
#四十 编程 用JAVA SOCKET编程，实现简单的Echo功能

#四十一 编程题：列出某文件夹下的所有文件 (文件夹从命令行输入)

#四十二 编程：编写一个截取字符串的函数，输入为一个字符串和字节数，输出为按字节截取的字符串。 但是要保证汉字不被截半个，如“我ABC”4，应该截为“我AB”，输入“我ABC汉DEF”，6，应该输出为“我ABC”而不是“我ABC+汉的半个”





