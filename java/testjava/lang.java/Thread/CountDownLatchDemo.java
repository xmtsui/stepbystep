/**
 * 关于java的CountDownLatch。Latch：门闩之意。
 * CountDownLatch经常用来在多线程环境下，主线程协调多个子线程的步调。
 * 生活中最相似的场景就是运动员比赛，裁判员（主线程）控制比赛的开始和结束，
 * 运动员（子线程）完成自己的比赛，当且仅当所有运动员都完成比赛时，
 * 裁判员就可以下令整场比赛结束。
 * 下面转载网友针对上述场景的模拟代码，以便更好地理解CountDownLatch的使用。
 *
 * @author xmtsui
 * @version v1.0
 */
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchDemo {
    private static final int PLAYER_AMOUNT =5;
    private static Object obj = new Object();
    public CountDownLatchDemo() {
    }
    /**
     * @param args
     */
    public static void main(String[] args) {
        //对于每位运动员，CountDownLatch减1后即结束比赛
        CountDownLatch begin =new CountDownLatch(1);
        //对于整个比赛，所有运动员结束后才算结束
        CountDownLatch end =new CountDownLatch(PLAYER_AMOUNT);
        Player[] plays =new Player[PLAYER_AMOUNT];

        for(int i=0;i<PLAYER_AMOUNT;i++)
            plays[i] =new Player(i+1,begin,end);

        //设置特定的线程池，大小为5
        ExecutorService exe = Executors.newFixedThreadPool(PLAYER_AMOUNT);
        for(Player p:plays)
            exe.execute(p);            //分配线程
        System.out.println("Race begins!");
        begin.countDown();//相当于裁判员下令比赛开始
        
        // synchronized(obj){
            try{
                //注意wait ,await区别
                end.await();            //等待end状态变为0，即为比赛结束
                // end.wait();            //等待end状态变为0，即为比赛结束
            }catch (InterruptedException e) {
                e.printStackTrace();
            }finally{
                System.out.println("Race ends!");
            }
            exe.shutdown();
        // }
    }
}
class Player implements Runnable {
    private int id;
    private CountDownLatch begin;
    private CountDownLatch end;
    public Player(int i, CountDownLatch begin, CountDownLatch end) {
        super();
        this.id = i;
        this.begin = begin;
        this.end = end;
    }

    @Override
    public void run() {
        try{
            begin.await();        //等待begin的状态为0，相当于运动员已做好准备，等待裁判员宣布比赛开始。
            Thread.sleep((long)(Math.random()*100));    //随机分配时间，即运动员完成时间
            System.out.println("Play"+id+" arrived.");
        }catch (InterruptedException e) {
            e.printStackTrace();
        }finally{
            end.countDown();    //使end状态减1，最终减至0
        }
    }
}