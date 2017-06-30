package online.wangxuan.concurrency.concurrent;

import static net.mindview.util.Print.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * CyclicBarrier示例
 * Created by wangxuan on 2017/6/30.
 */
public class HorseRace {
    static final int FINISH_LINE = 75;
    private List<Horse> horses = new ArrayList<>();
    private ExecutorService exec = Executors.newCachedThreadPool();
    private CyclicBarrier barrier;

    public HorseRace(int nHorses, final int pause) {
        // 可以向CyclicBarrier提供一个栅栏动作，它是一个Runnable, 当计数值到达0时, 自动执行.
        // CyclicBarrier使得每匹马都要执行为了向前移动所必须的所有工作, 然后必须在栅栏处等待
        // 其他所有的马都准备完毕.
        barrier = new CyclicBarrier(nHorses, () -> {
            // 打印栅栏
            StringBuilder s = new StringBuilder();
            for (int i = 0; i < FINISH_LINE; i++) {
                s.append("-"); // 赛道上的围栏
            }
            print(s);
            // 打印马的位置
            for (Horse horse : horses) {
                print(horse.tracks());
            }
            // 判断比赛结果
            for (Horse horse : horses) {
                if (horse.getStrides() >= FINISH_LINE) {
                    print(horse + "won!");
                    exec.shutdownNow();
                    return;
                }
            }
            try {
                TimeUnit.MILLISECONDS.sleep(pause);
            } catch (InterruptedException e) {
                print("barrier-action sleep interrupted");
            }
        });
        for (int i = 0; i < nHorses; i++) {
            Horse horse = new Horse(barrier);
            horses.add(horse);
            exec.execute(horse);
        }
    }

    public static void main(String[] args) {
        int nHorses = 8;
        int pause = 200;

        if (args.length > 0) {
            int n = new Integer(args[0]);
            nHorses = n > 0 ? n : nHorses;
        }

        if (args.length > 1) {
            int p = new Integer(args[1]);
            pause = p > -1 ? p : pause;
        }

        new HorseRace(nHorses, pause);
    }
}

class Horse implements Runnable {
    private static int counter = 0;
    private final int id = counter++;
    private int strides = 0;
    private static Random rand = new Random(47);
    private static CyclicBarrier barrier;

    public Horse(CyclicBarrier b) {
        barrier = b;
    }

    public synchronized int getStrides() {
        return strides;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                synchronized (this) {
                    strides += rand.nextInt(3);
                }
                barrier.await();
            }
        } catch (InterruptedException e) {
            // A legitimate way to exit
        } catch (BrokenBarrierException e) {
            //This one we want to know about
            throw new RuntimeException(e);
        }
    }

    public String toString() {
        return "Horse " + id + " ";
    }

    public String tracks() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < getStrides(); i++)
            s.append("*");
        s.append(id);
        return s.toString();
    }
}