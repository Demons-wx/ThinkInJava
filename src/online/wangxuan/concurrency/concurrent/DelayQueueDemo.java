package online.wangxuan.concurrency.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

import static java.util.concurrent.TimeUnit.*;
import static net.mindview.util.Print.*;

/**
 * DelayQueue是一个无界的BlockingQueue, 用于放置实现了Delayed接口的对象, 其中的对象
 * 只能在到期时才能从队列中取走. 这种队列是有序的, 即队头对象的延迟到期时间最长.
 *
 * 如果没有任何延迟到期, 那么就不会有任何头元素, 并且poll()将返回null.
 *
 * 下面是一个示例, 其中的Delayed对象自身就是任务, 而DelayedTaskConsumer将最紧急的任务(到期时间最长的任务)
 * 从队列中取出, 然后运行它. 注意, 这样DelayQueue就成为了有限级队列的一种变体:
 * Created by wangxuan on 2017/8/24.
 */
public class DelayQueueDemo {

    public static void main(String[] args) {
        Random rand = new Random(47);
        ExecutorService exec = Executors.newCachedThreadPool();
        DelayQueue<DelayedTask> queue = new DelayQueue<>();

        // Fill with tasks that have random delays
        for (int i = 0; i < 20; i++)
            queue.put(new DelayedTask(rand.nextInt(5000)));

        // Set the stopping point
        queue.add(new DelayedTask.EndSentinel(5000, exec));
        exec.execute(new DelayTaskConsumer(queue));

    }
}

class DelayedTask implements Runnable, Delayed {

    private static int counter = 0;
    private final int id = counter++;
    private final int delta;
    private final long trigger;
    // DelayedTask包含了一个称为sequence的List<DelayedTask>, 它保存了任务被创建的顺序，
    // 因此我们可以看到排序是按照实际发生的顺序执行的
    protected static List<DelayedTask> sequence = new ArrayList<>();

    public DelayedTask(int delayInMilliseconds) {
        delta = delayInMilliseconds;
        trigger = System.nanoTime() + NANOSECONDS.convert(delta, MILLISECONDS);
        sequence.add(this);
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(trigger - System.nanoTime(), NANOSECONDS);
    }

    @Override
    public int compareTo(Delayed arg) {
        DelayedTask that = (DelayedTask) arg;
        if (trigger < that.trigger)
            return -1;
        if (trigger > that.trigger)
            return 1;
        return 0;
    }

    @Override
    public void run() {
        printnb(this + " ");
    }

    public String toString() {
        return String.format("[%1$-4d]", delta) + " Task " + id;
    }

    public String summary() {
        return "(" + id + ":" + delta + ")";
    }

    // EndSentinel提供了一种关闭所有事物的途径，将其放置在队列的最后一个元素
    public static class EndSentinel extends DelayedTask {
        private ExecutorService exec;

        public EndSentinel(int delay, ExecutorService e) {
            super(delay);
            exec = e;
        }

        // 当线程运行到最后一个EndSebtinel时，先循环输出每个DelayedTask的summary，最后终止执行
        public void run() {
            for (DelayedTask pt : sequence) {
                printnb(pt.summary() + " ");
            }
            print();
            print(this + "Calling shutdownNow()");
            exec.shutdownNow();
        }
    }
}

/**
 * DelayTaskConsumer自身是一个任务，所有它有自己的thread，它可以使用这个线程来运行从
 * 队列中获取的所有任务。由于任务是按照队列优先级的顺序执行的，因此在本例中不需要启动任何
 * 单独的线程来运行DelayedTask
 */
class DelayTaskConsumer implements Runnable {
    private DelayQueue<DelayedTask> q;

    public DelayTaskConsumer(DelayQueue<DelayedTask> q) {
        this.q = q;
    }

    public void run() {
        try {
            while (!Thread.interrupted())
                // 在队列头部取出元素并执行
                q.take().run();
        } catch (InterruptedException e) {

        }
        print("Finished DelayedTaskConsumer");
    }
}

/**
 * 从输出中可以看到，任务创建的顺序对执行顺序没有任何影响，任务是按照所期望的延迟顺序执行的。
 * [128 ] Task 11 [200 ] Task 7 [429 ] Task 5 [520 ] Task 18 [555 ] Task 1 [961 ] Task 4 [998 ] Task 16 [1207] Task 9 [1693] Task 2 [1809] Task 14 [1861] Task 3 [2278] Task 15 [3288] Task 10 [3551] Task 12 [4258] Task 0 [4258] Task 19 [4522] Task 8 [4589] Task 13 [4861] Task 17 [4868] Task 6
 * (0:4258) (1:555) (2:1693) (3:1861) (4:961) (5:429) (6:4868) (7:200) (8:4522) (9:1207) (10:3288) (11:128) (12:3551) (13:4589) (14:1809) (15:2278) (16:998) (17:4861) (18:520) (19:4258) (20:5000)
 * [5000] Task 20Calling shutdownNow()
 * Finished DelayedTaskConsumer
 */
