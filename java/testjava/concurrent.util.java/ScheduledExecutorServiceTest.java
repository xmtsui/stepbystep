import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.ScheduledExecutorService;
/**
 * test execute multi task using timer and scheduledExecutorService
 * 多线程并行处理定时任务时，Timer运行多个TimeTask时，只要其中之一没有捕获抛出的异常，其它任务便会自动终止运行，使用ScheduledExecutorService则没有这个问题。
 */
public class ScheduledExecutorServiceTest {
 public static void main(String[] args) {
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor(new ThreadFactory() {
            @Override
            public Thread newThread(Runnable runnable) {
                Thread thread = new Thread(runnable, "flushProducerStatusDiskThread");
                return thread;
            }
        });

        Timer timer = new Timer();
        final AtomicInteger i1 = new AtomicInteger();
        // add a timer task
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                System.out.println("timer haha1");
                if(i1.getAndIncrement() == 2) {
                    System.out.println("timer not haha1");
                    throw new RuntimeException("timer exception haha1");
                }
            }
        }, 0L, 1000L);

        // add a timer task
        final AtomicInteger i2 = new AtomicInteger();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                System.out.println("timer haha2");
                if(i2.getAndIncrement() == 2) {
                    System.out.println("timer not haha2");
                    // throw new RuntimeException("timer exception haha2");
                }
            }
        }, 0L, 1000L);

        // add a scheduler task
        final AtomicInteger i3 = new AtomicInteger();
        scheduler.scheduleAtFixedRate(new Thread(){
           public void run() {
               System.out.println("scheduler hehe1");
               if(i3.getAndIncrement() == 2) {
                   System.out.println("scheduler not hehe1");
                   throw new RuntimeException("scheduler exception hehe1");
               }
           }
       }, 0, 1000, TimeUnit.MILLISECONDS);

        // add a scheduler task
        final AtomicInteger i4 = new AtomicInteger();
        scheduler.scheduleAtFixedRate(new Thread(){
           public void run() {
               System.out.println("scheduler hehe2");
               if(i4.getAndIncrement() == 2) {
                   System.out.println("scheduler not hehe2");
                   // throw new RuntimeException("exception hehe2");
               }
           }
       }, 0, 1000, TimeUnit.MILLISECONDS);
    }
}