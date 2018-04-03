package online.wangxuan.proxy.staticproxy;

import online.wangxuan.proxy.RealSubject;
import online.wangxuan.proxy.Subject;

/**
 * @author wangxuan
 * @date 2018/4/3 11:07
 */
public class SubjectProxy implements Subject {

    Subject subImpl = new RealSubject();

    @Override
    public void doSomething() {
        subImpl.doSomething();
    }
}
