package online.wangxuan.jvm;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by wangxuan on 2017/6/28.
 */
public class TestJConsole {

    /**
     * 内存占位符对象，一个OOMObject大约占64Kb
     */
    static class OOMObject {
        public byte[] placeholder = new byte[64 * 1024];
    }

    public static void fillHeap(int num) throws InterruptedException {
        List<OOMObject> list = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            // 稍作时延，令监视曲线变化更加明显
            TimeUnit.MILLISECONDS.sleep(50);
            list.add(new OOMObject());
        }
        System.gc();
        System.out.println("GC完成！");
    }

    public static void main(String[] args) throws Exception {
        fillHeap(1000);
        TimeUnit.SECONDS.sleep(10);
    }
}
