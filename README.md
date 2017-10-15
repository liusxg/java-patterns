#Java 设计模式
##1.单例模式
###定义
>保证一个类只有一个实例，并且提供一个访问这个实例的全局访问点。
###使用场景
>一般用在一些本质上具有唯一性的系统资源，比如资源管理器这些。
###要点
>1.私有化构造器  
>2.提供一个静态方法用来获取类的实例

单例模式根据实例的生成时间又分成两类，懒汉式和饿汉式  

懒汉式：只有需要用到的时候才会去生成实例。  
饿汉式：先生成实例，不管有没有用到。

###线程安全
这里的线程安全的意思是保证在多线程运行下，保证只生成一个实例。  
饿汉式在类加载的时候就生成了实例，是天生的线程安全。
不过饿汉式的缺点也很明显，因为会先生成实例，并不管这个实例是否会被用到，会造成资源浪费的问题。所以一般采用的是懒汉式生成实例。
由于饿汉式不需要保证线程安全，所以这里的线程安全基本上说的是保证懒汉式的线程安全。

单例模式保证线程安全参考  
[1]: <http://www.cs.umd.edu/~pugh/java/memoryModel/DoubleCheckedLocking.html> "双重锁失效的可能性"

#####第一种方法：在静态方法getInstance，加上synchronized同步锁
#####特点 
>每次调用getInstance方法都要获取Singleton3.class的锁，然而只有在第一次获取实例的时候才有必要获取锁，后续的多线程访问效率会很低。

#####第二种方法：双重锁保证简称DCL。
#####特点
>在获取锁之前检查一下实例是否存在，在获取锁之后再做一次检查。如果实例已经生成，后续的线程无需再获取锁。

**注意** 双重锁对于懒加载来说是一种比较有效的实现方式，但在java里面却行不通。在Java的优化编译器或共享内存多处理器中，这个双重检查锁作用会失效。最关键的原因在于，优化编译器的处理下，对象初始化和将对象地址写到instance的顺序是不确定的。
这个意味着instance可以先赋值，然后，Singleton对象再初始化，这并不是一个原子操作。
```
public static Singleton4 getInstance() {
        if (singleton4 == null) { //1
                synchronized (Singleton4.class) { //2
                    if (singleton4 == null) {  //3
                        singleton4 = new Singleton4(); //4
                    }
                }
        }
        return singleton4; //5
    }
```
> singleton4 = new Singleton4(); //4

这个操作不是一个原子操作，这一步的原子操作有  
>分配内存空间  
 给singleton4引用赋值//在这一步的时候instance就已经不是null了  
 new Singleton4()

举个例子  
现在有两个线程，线程I和线程II。
当线程I执行代码1的时候，此时singleton4是null，线程1可以执行到代码2获得到锁，继续执行到代码4。
当singleton4引用已经被赋值，但是还没有调用Singleton4构造函数初始化的时候，线程II执行代码1获得到的是`singleton4 != null`，
然后直接执行代码5，返回的是一个尚未初始化的实例，是一个不正确的结果。

使用happen-before规则重新审视DCL  
>happen-before规则，A happen-before B，操作A在操作B发生之前要发生，操作A对内存施加对影响要被B观察到。在这，线程I初始化Singleton4对象 应该happen-before 线程II的代码1的执行。  

#####修复DCL：volatile关键字
volatile作用有两个
>1.保证不同线程对这个变量操作时对可见性，即一个线程修改了某个变量的值，这个新值对所有线程来说是立即可见的。
2.禁止指令重排。  
>
这里的禁止指令重排有两个含义  
>1.当程序执行到volatile变量的读操作或者写操作的时候，在其前面的操作必须是已经全部完成的，且结果对后面对操作也是可见的。  
2.在进行指令优化的时候，不能对volatile变量访问的语句放在其后面执行，也不能将volatile变量后面的语句放在前面执行。
>
修复代码
```
    private volatile static Singleton4 singleton4 = null;

    /**
     * 私有化构造器
     */
    private Singleton4() {}

    /**
     *
     * 提供一个公有静态方法获取实例, 不管任何对象要获取Singleton类的实例都是从这个方法获取
     *
     * 懒汉式，只有需要的时候才会去生成实例
     *
     * 特点，双重锁检查, 使用volatile关键字来保证singleton4在对象初始化完成后修改值立马可见
     *
     * @return
     */
    public static Singleton4 getInstance() {
        if (singleton4 == null) {
                synchronized (Singleton4.class) {
                    if (singleton4 == null) {
                        singleton4 = new Singleton4();
                    }
                }
        }
        return singleton4;
    }
```

###单例模式另外两种实现
####[使用ThreadLocal](src/main/java/com/liusxg/patterns/singleton/Singleton5.java)
####[通过内部类加载实现，最优雅的方式](src/main/java/com/liusxg/patterns/singleton/Singleton6.java) 







