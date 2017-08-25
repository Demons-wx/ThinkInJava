package online.wangxuan.concurrency.concurrent;

import java.util.*;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 使用ScheduledExecutor模拟温室控制器，它可以控制各种设施的开关，或是对它们进行调节。
 * 这可以看作是一种并发问题，每个期望的温室事件都是一个在预定时间运行的任务。
 *
 * ScheduledThreadPoolExecutor提供了解决该问题的服务。通过使用schedule()(运行一次任务)
 * 或者scheduleAtFixedRate()(每隔规则时间重复执行任务),你可以将Runnable对象设置为在将来的
 * 某个时刻执行。
 *
 * Created by wangxuan on 2017/8/25.
 */
public class GreenhouseScheduler {

    private volatile boolean light = false;
    private volatile boolean water = false;
    private String thermostat = "Day"; // thermostat 恒温器

    public String getThermostat() {
        return thermostat;
    }

    public void setThermostat(String thermostat) {
        this.thermostat = thermostat;
    }

    ScheduledThreadPoolExecutor scheduler = new ScheduledThreadPoolExecutor(10);

    public void schedule(Runnable event, long delay) {
        scheduler.schedule(event, delay, TimeUnit.MILLISECONDS);
    }

    public void repeat(Runnable event, long initialDelay, long period) {
        scheduler.scheduleAtFixedRate(event, initialDelay, period, TimeUnit.MILLISECONDS);
    }

    class LightOn implements Runnable {
        public void run() {
            // Put hardware control code here to physically turn on the light
            System.out.println("Turning on lights");
            light = true;
        }
    }

    class LightOff implements Runnable {
        public void run() {
            // Put hardware control code here to physically turn off the light
            System.out.println("Turning off lights");
            light = false;
        }
    }

    class WaterOn implements Runnable {
        public void run() {
            // Put hardware control code here
            System.out.println("Turning greenhouse water on");
            water = true;
        }
    }

    class WaterOff implements Runnable {
        public void run() {
            // Put hardware control code here
            System.out.println("Turning greenhouse water off");
            water = false;
        }
    }

    /**
     * 夜间恒温器
     */
    class ThermostatNight implements Runnable {
        public void run() {
            // Put hardware control code here
            System.out.println("Thermostat to day setting");
            setThermostat("Day");
        }
    }

    class ThermostatDay implements Runnable {
        public void run() {
            // Put hardware control code here
            System.out.println("Thermostat to night setting");
            setThermostat("Night");
        }
    }

    class Bell implements Runnable {
        public void run() {
            System.out.println("Bing!");
        }
    }

    class Terminate implements Runnable {
        public void run() {
            System.out.println("Terminating");
            scheduler.shutdownNow();
            // must start a separate task to do this job
            // since the scheduler has been shut down
            new Thread() {
                public void run() {
                    for (DataPoint d : data) {
                        System.out.println(d);
                    }
                }
            }.start();
        }
    }

    /**
     * 收集温室内的温度和湿度读数，DataPoint可以持有并显示单个的数据段
     */
    static class DataPoint {
        final Calendar time;
        final float temperature; // 温度
        final float humidity; // 湿度

        public DataPoint(Calendar d, float temp, float hum) {
            time = d;
            temperature = temp;
            humidity = hum;
        }

        public String toString() {
            return time.getTime() + String.format(" temperature: %1$.1f humidity: %2$.2f", temperature, humidity);
        }
    }


    private Calendar lastTime = Calendar.getInstance();
    {
        // Adjust date to the harf hour
        lastTime.set(Calendar.MINUTE, 30);
        lastTime.set(Calendar.SECOND, 00);
    }

    private float lastTemp = 65.0f;
    private int tempDirection = +1;
    private float lastHumidity = 50.0f;
    private int humidityDirection = +1;
    private Random rand = new Random(47);

    // 持有DataPoint的List中所有方法都是synchronized的，这是因为在list被创建时，使用了
    // java.util.Collections实用工具synchronizedList()
    List<DataPoint> data = Collections.synchronizedList(new ArrayList<>());

    /**
     * CollectData是被调度的任务，它在每次运行时，都可以产生仿真数据，并将其添加到Greenhouse的List<DataPoint>中
     */
    class CollectData implements Runnable {
        public void run() {
            System.out.println("Collecting data");
            synchronized (GreenhouseScheduler.this) {
                // Pretend the interval is longer than it is
                lastTime.set(Calendar.MINUTE, lastTime.get(Calendar.MINUTE) + 30);
                // one in 5 chances of reversing the direction
                if (rand.nextInt(5) == 4)
                    tempDirection = -tempDirection;
                // store previous value
                lastTemp = lastTemp + tempDirection * (1.0f + rand.nextFloat());
                if (rand.nextInt(5) == 4)
                    humidityDirection = -humidityDirection;
                lastHumidity = lastHumidity + humidityDirection * rand.nextFloat();

                // calendar must be cloned, otherwise all DataPoints hold references to the
                // same lastTime. for a basic object like Calendar, clone() is ok
                data.add(new DataPoint((Calendar) lastTime.clone(), lastTemp, lastHumidity));
            }
        }
    }

    public static void main(String[] args) {
        GreenhouseScheduler gh = new GreenhouseScheduler();
        gh.schedule(gh.new Terminate(), 5000);

        gh.repeat(gh.new Bell(), 0, 1000);
        gh.repeat(gh.new ThermostatNight(), 0, 2000);
        gh.repeat(gh.new LightOn(), 0, 200);
        gh.repeat(gh.new LightOff(), 0, 400);
        gh.repeat(gh.new WaterOn(), 0, 600);
        gh.repeat(gh.new WaterOff(), 0, 800);
        gh.repeat(gh.new ThermostatDay(), 0, 1400);
        gh.repeat(gh.new CollectData(), 500, 500);

    }
}
