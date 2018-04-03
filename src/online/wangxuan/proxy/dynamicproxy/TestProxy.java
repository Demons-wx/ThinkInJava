package online.wangxuan.proxy.dynamicproxy;

import online.wangxuan.proxy.RealSubject;
import online.wangxuan.proxy.Subject;

/**
 * @author wangxuan
 * @date 2018/4/3 11:17
 */
public class TestProxy {

    public static void main(String[] args) {
        ProxyHandler proxy = new ProxyHandler();
        Subject sub = (Subject) proxy.bind(new RealSubject());

        sub.doSomething();
    }
}
