package online.wangxuan.generics.generator;

/**
 * 泛型也可以应用于接口。例如生成器，这是一种专门负责创建对象的类。
 * 这是工厂方法设计模式的一种应用。不过，当使用生成器创建新的对象时，
 * 它不需要任何参数，而工厂方法一般需要参数。
 * Created by wangxuan on 2017/8/10.
 */
public interface Generator<T> {
    T next();
}
