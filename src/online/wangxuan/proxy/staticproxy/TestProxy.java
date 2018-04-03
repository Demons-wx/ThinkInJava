package online.wangxuan.proxy.staticproxy;

import online.wangxuan.proxy.Subject;

/**
 * @author wangxuan
 * @date 2018/4/3 11:08
 */
public class TestProxy {

    public static void main(String[] args) {
        Subject sub = new SubjectProxy();
        sub.doSomething();
    }
}
