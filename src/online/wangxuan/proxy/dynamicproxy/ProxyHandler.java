package online.wangxuan.proxy.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author wangxuan
 * @date 2018/4/3 11:14
 */
public class ProxyHandler implements InvocationHandler {

    private Object tar;

    public Object bind(Object tar) {
        this.tar = tar;
        // 绑定该类实现的所有接口，取得代理类
        return Proxy.newProxyInstance(tar.getClass().getClassLoader(),
                tar.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        Object result = null;
        // 这里可以进行AOP编程，在调用具体函数方法前，执行功能处理
        result = method.invoke(tar, args);
        return result;
    }
}
