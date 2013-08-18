#JAVA程序员面试32问  

#一，谈谈final， finally， finalize的区别。 

#二，Anonymous Inner Class （匿名内部类） 是否可以extends（继承）其它类，是否可以implements（实现）interface（接口）？ 
不能，能

#三，Static Nested Class 和 Inner Class的不同，说得越多越好（面试题有的很笼统）。 

#四，&和&&的区别。 

#五，HashMap和Hashtable的区别.  
    
    HashMap不同步，HashTable同步；HashMap key，value都能为null,  null key只有一个;get(Object key)方法返回null，即表示value null 也表示 key不存在，所以适用containsKey来判断某个键是否存在
    
#六，Collection 和 Collections的区别。 

#七，什么时候用assert. 

#八，GC是什么？ 为什么要有GC？ 

#九，String s = new String（"xyz"）；创建了几个String Object？ 

#十，Math.round（11.5）等於多少？ Math.round（-11.5）等於多少？ 

#十一，short s1 = 1； s1 = s1 + 1；有什么错？ short s1 = 1； s1 += 1；有什么错？ 
	
	JAVA规范上说     e1+=e2     实际上是     e1=(T1)(e1+e2)，其中T1是e1的数据类型。   s1+=1等效于   s1=(short)(s1+1),所以是正确的。
#十二，sleep（） 和 wait（） 有什么区别？ 
	
	sleep是线程类（Thread）的方法，导致此线程暂停执行指定时间，给执行机会给其他线程，但是监控状态依然保持，到时后会自动恢复。调用sleep不会释放对象锁。  
	
	wait是Object类的方法，对此对象调用wait方法导致本线程放弃对象锁，进入等待此对象的等待锁定池，只有针对此对象发出notify方法（或notifyAll）后本线程才进入对象锁定池准备获得对象锁进入运行状态。
	
	先说wait，wait的本质是条件等待，这里涉及到了锁的概念（也就是多线程中要保证线程安全的锁）。所谓条件等待就是已经获得了锁的线程，由于需要满足某种条件才能继续执行，而当前不满足条件，所以只能等待。所以调用wait的一个前提条件就是要先拿到锁。拿到锁的线程，wait之后，该线程就进入条件等待队列，并且释放锁，让其他线程执行。当其他线程的执行使得条件满足之后，再调用notify或者notifyAll方法，将条件等待队列中的线程唤醒，这些线程再去请求锁，拿到锁的线程接着去执行。建议你看一下ReentrantLock类，顺便用一下Condition类，看一看《Java核心技术》第二卷关于线程那一章的讲解。
	
	调用wait()/wait(long t)线程进入Waiting/TimeWaiting状态。sleep（long t）的意思是暂停当前线程t毫秒，当然，其他线程就得到了时间片，sleep与线程是否获得锁无关。调用sleep线程进入TimeWaiting状态。这里有张比较基础的图，你看一下 

#十三，Java有没有goto？ 

#十四，数组有没有length（）这个方法？ String有没有length（）这个方法？ 

#十五，Overload和Override的区别。Overloaded的方法是否可以改变返回值的类型？ 

#十六，Set里的元素是不能重复的，那么用什么方法来区分重复与否呢？ 是用==还是equals（）？ 它们有何区别？ 
	
	通过对于这两个方法的使用: equals和hashcode，在set 集合框架中体现是最突出了。1，两个对象equals相等那么hashcode 是一定相等的。2，两个对象equals不相等hashcode可能相等可以不相等。
	
	这两个方法的调用原则是： 一、先去调用hashcode方法比较，如果相等的情况下再去调用equals方法比较，这时equals相等那边认为是同一个对象，则不同对象。二、先去调用hashcode方法不相等了，这样就不会去调用equals方法做比较的。所以，我们在写程序时一般会重写这两个方法。【http://www.haoxianggo.com】

#十七，给我一个你最常见到的runtime exception. 
	
	nullpointer, indexoutofbounds, classcastexception
#十八，error和exception有什么区别？ 

#十九，List， Set， Map是否继承自Collection接口？ 

#二十，abstract class和interface有什么区别？ 

#二十一，abstract的method是否可同时是static，是否可同时是native，是否可同时是synchronized？ 

#二十二，接口是否可继承接口？ 抽象类是否可实现（implements）接口？ 抽象类是否可继承实体类（concrete class）？ 

#二十三，启动一个线程是用run（）还是start（）？ 

#二十四，构造器Constructor是否可被override？ 

#二十五，是否可以继承String类？ 

#二十六，当一个线程进入一个对象的一个synchronized方法后，其它线程是否可进入此对象的其它方法？ 

#二十七，try {}里有一个return语句，那么紧跟在这个try后的finally {}里的code会不会被执行，什么时候被执行，在return前还是后？ 

#二十八，编程题： 用最有效率的方法算出2乘以8等於几？ 

#二十九，两个对象值相同（x.equals（y） == true），但却可有不同的hash code，这句话对不对？ 

#三十，当一个对象被当作参数传递到一个方法后，此方法可改变这个对象的属性，并可返回变化后的结果，那么这里到底是值传递还是引用传递？ 

#三十一，swtich是否能作用在byte上，是否能作用在long上，是否能作用在String上？ 

#三十二，编程题： 写一个Singleton出来。 

#以下是答案
##第一，谈谈final， finally， finalize的区别。 

　　final—修饰符（关键字）如果一个类被声明为final，意味着它不能再派生出新的子类，不能作为父类被继承。因此一个类不能既被声明为 abstract的，又被声明为final的。将变量或方法声明为final，可以保证它们在使用中不被改变。被声明为final的变量必须在声明时给定初值，而在以后的引用中只能读取，不可修改。被声明为final的方法也同样只能使用，不能重载finally—再异常处理时提供 finally 块来执行任何清除操作。如果抛出一个异常，那么相匹配的 catch 子句就会执行，然后控制就会进入 finally 块（如果有的话）。 

　　finalize—方法名。Java 技术允许使用 finalize（） 方法在垃圾收集器将对象从内存中清除出去之前做必要的清理工作。这个方法是由垃圾收集器在确定这个对象没有被引用时对这个对象调用的。它是在 Object 类中定义的，因此所有的类都继承了它。子类覆盖 finalize（） 方法以整理系统资源或者执行其他清理工作。finalize（） 方法是在垃圾收集器删除对象之前对这个对象调用的。 

#第二，Anonymous Inner Class （匿名内部类） 是否可以extends（继承）其它类，是否可以implements（实现）interface（接口）？ 

　　匿名的内部类是没有名字的内部类。不能extends（继承） 其它类，但一个内部类可以作为一个接口，由另一个内部类实现。 

#第三，Static Nested Class 和 Inner Class的不同，说得越多越好（面试题有的很笼统）。 

　　Nested Class （一般是C++的说法），Inner Class （一般是JAVA的说法）。Java内部类与C++嵌套类最大的不同就在于是否有指向外部的引用上。具体可见http： //www.frontfree.net/articles/services/view.asp？id=704&page=1注： 静态内部类（Inner Class）意味着1创建一个static内部类的对象，不需要一个外部类对象，2不能从一个static内部类的一个对象访问一个外部类对象 

#第四，&和&&的区别。 

　　&是位运算符。&&是布尔逻辑运算符。 

#五，HashMap和Hashtable的区别。 

　　都属于Map接口的类，实现了将惟一键映射到特定的值上。 

　　HashMap 类没有分类或者排序。它允许一个 null 键和多个 null 值。 

　　Hashtable 类似于 HashMap，但是不允许 null 键和 null 值。它也比 HashMap 慢，因为它是同步的。 

#第六，Collection 和 Collections的区别。 

　　Collections是个java.util下的类，它包含有各种有关集合操作的静态方法。 

　　Collection是个java.util下的接口，它是各种集合结构的父接口。 

#第七，什么时候用assert.

断言是一个包含布尔表达式的语句，在执行这个语句时假定该表达式为 true.如果表达式计算为 false，那么系统会报告一个 AssertionError.它用于调试目的：assert（a > 0）； // throws an AssertionError if a <= 0断言可以有两种形式：assert Expression1 ；assert Expression1 ： Expression2 ；Expression1 应该总是产生一个布尔值。 

　　Expression2 可以是得出一个值的任意表达式。这个值用于生成显示更多调试信息的 String 消息。 

　　断言在默认情况下是禁用的。要在编译时启用断言，需要使用 source 1.4 标记：javac -source 1.4 Test.java要在运行时启用断言，可使用 -enableassertions 或者 -ea 标记。 

　　要在运行时选择禁用断言，可使用 -da 或者 -disableassertions 标记。 

　　要系统类中启用断言，可使用 -esa 或者 -dsa 标记。还可以在包的基础上启用或者禁用断言。 

　　可以在预计正常情况下不会到达的任何位置上放置断言。断言可以用于验证传递给私有方法的参数。不过，断言不应该用于验证传递给公有方法的参数，因为不管是否启用了断言，公有方法都必须检查其参数。不过，既可以在公有方法中，也可以在非公有方法中利用断言测试后置条件。另外，断言不应该以任何方式改变程序的状态。 

#第八，GC是什么？ 为什么要有GC？ （基础）。 

　　GC是垃圾收集器。Java 程序员不用担心内存管理，因为垃圾收集器会自动进行管理。要请求垃圾收集，可以调用下面的方法之一：System.gc（） 

　　Runtime.getRuntime（）。gc（） 

#第九，String s = new String（"xyz"）；创建了几个String Object？ 

　　两个对象，一个是“xyx”，一个是指向“xyx”的引用对象s. 

#第十，Math.round（11.5）等於多少？ Math.round（-11.5）等於多少？ 

　　Math.round（11.5）返回（long）12，Math.round（-11.5）返回（long）-11； 

#第十一，short s1 = 1； s1 = s1 + 1；有什么错？ short s1 = 1； s1 += 1；有什么错？ 

　　short s1 = 1； s1 = s1 + 1；有错，s1是short型，s1+1是int型，不能显式转化为short型。可修改为s1 =（short）（s1 + 1） .short s1 = 1； s1 += 1正确。 

#第十二，sleep（） 和 wait（） 有什么区别？
 搞线程的最爱sleep（）方法是使线程停止一段时间的方法。在sleep 时间间隔期满后，线程不一定立即恢复执行。这是因为在那个时刻，其它线程可能正在运行而且没有被调度为放弃执行，除非（a）“醒来”的线程具有更高的优先级（b）正在运行的线程因为其它原因而阻塞。 

　　wait（）是线程交互时，如果线程对一个同步对象x 发出一个wait（）调用，该线程会暂停执行，被调对象进入等待状态，直到被唤醒或等待时间到。 

#第十三，Java有没有goto？ 

　　Goto—java中的保留字，现在没有在java中使用。 

#第十四，数组有没有length（）这个方法？ String有没有length（）这个方法？ 

　　数组没有length（）这个方法，有length的属性。 

　　String有有length（）这个方法。 

#第十五，Overload和Override的区别。Overloaded的方法是否可以改变返回值的类型？ 

　　方法的重写Overriding和重载Overloading是Java多态性的不同表现。重写Overriding是父类与子类之间多态性的一种表现，重载Overloading是一个类中多态性的一种表现。如果在子类中定义某方法与其父类有相同的名称和参数，我们说该方法被重写 （Overriding）。子类的对象使用这个方法时，将调用子类中的定义，对它而言，父类中的定义如同被“屏蔽”了。如果在一个类中定义了多个同名的方法，它们或有不同的参数个数或有不同的参数类型，则称为方法的重载（Overloading）。Overloaded的方法是可以改变返回值的类型。 

#第十六，Set里的元素是不能重复的，那么用什么方法来区分重复与否呢？ 是用==还是equals（）？ 它们有何区别？ 

Set里的元素是不能重复的，那么用iterator（）方法来区分重复与否。equals（）是判读两个Set是否相等。 

　　equals（）和==方法决定引用值是否指向同一对象equals（）在类中被覆盖，为的是当两个分离的对象的内容和类型相配的话，返回真值。 

#第十七，给我一个你最常见到的runtime exception. 
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

　　`会执行，在return前执行`

#第二十八，编程题： 用最有效率的方法算出2乘以8等於几？ 

　　有C背景的程序员特别喜欢问这种问题。 

　　2 << 3 

#第二十九，两个对象值相同（x.equals（y） == true），但却可有不同的hash code，这句话对不对？ 

　　不对，有相同的hash code. 
　　因此重新equal步骤： 
1） ＝＝比较 
2）instanceof (此句包含的null的比较，因为null不是任何object的实例) 
3）attribute比较 

hashcode的作用： 
为了便于对象的检索，Set内部对象是用哈希表来存储，同时已对象的hashcode做key.因此比较重复对象的时候，首先首先通过hashcode检索对应的位置，如果有多个元素，则通过equal方法判断。因此两个对象equal，但没有相同的hashcode,就会造成重复的存储，破坏了set的原则。也可以这样理解，[b]相等的对象(equal)一定要存储在同一个位置(hashcode)。[/b] 
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

　　Singleton模式主要作用是保证在Java应用程序中，一个类Class只有一个实例存在。 

　　一般Singleton模式通常有几种种形式：第一种形式： 定义一个类，它的构造函数为private的，它有一个static的private的该类变量，在类初始化时实例话，通过一个public的getInstance方法获取对它的引用，继而调用其中的方法。 

　　public class Singleton { private Singleton（）{} //在自己内部定义自己一个实例，是不是很奇怪？ 

　　//注意这是private 只供内部调用private static Singleton instance = new Singleton（）；//这里提供了一个供外部访问本class的静态方法，可以直接访问public static Singleton getInstance（） { return instance；}第二种形式：public class Singleton { private static Singleton instance = null；public static synchronized Singleton getInstance（） { //这个方法比上面有所改进，不用每次都进行生成对象，只是第一次//使用时生成实例，提高了效率！ 

　　if （instance==null） 

　　instance＝new Singleton（）；return instance； 　　} }其他形式：定义一个类，它的构造函数为private的，所有方法为static的。 

　　一般认为第一种形式要更加安全些
#第三十三 Hashtable和HashMap Hashtable继承自Dictionary类，而HashMap是Java1.2引进的Map interface的一个实现 

　　HashMap允许将null作为一个entry的key或者value，而Hashtable不允许 

　　还有就是，HashMap把Hashtable的contains方法去掉了，改成containsvalue和containsKey.因为contains方法容易让人引起误解。 

　　最大的不同是，Hashtable的方法是Synchronize的，而HashMap不是，在多个线程访问Hashtable时，不需要自己为它的方法实现同步，而HashMap就必须为之提供外同步。 

　　Hashtable和HashMap采用的hash/rehash算法都大概一样，所以性能不会有很大的差异。 


