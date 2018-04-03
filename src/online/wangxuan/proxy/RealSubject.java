package online.wangxuan.proxy;

import online.wangxuan.proxy.Subject;

/**
 * @author wangxuan
 * @date 2018/4/3 11:06
 */
public class RealSubject implements Subject {

    @Override
    public void doSomething() {
        System.out.println("call doSomething()");
    }
}
