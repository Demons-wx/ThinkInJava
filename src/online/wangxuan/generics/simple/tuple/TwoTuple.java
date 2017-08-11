package online.wangxuan.generics.simple.tuple;

/**
 * 元组：它是将一组对象直接打包存储于其中的一个单一对象。这个容器对象允许读取其中元素，
 * 但是不允许向其中存放新的对象。
 * Created by wangxuan on 2017/8/10.
 */
public class TwoTuple<A, B> {

    public final A first;
    public final B second;

    public TwoTuple(A a, B b) {
        first = a;
        second = b;
    }

    public String toString() {
        return "(" + first + ", " + second + ")";
    }

}


