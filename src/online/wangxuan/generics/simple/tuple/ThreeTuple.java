package online.wangxuan.generics.simple.tuple;

/**
 * Created by wangxuan on 2017/8/10.
 */
public class ThreeTuple<A, B, C> extends TwoTuple<A, B> {

    public final C third;
    public ThreeTuple(A a, B b, C c) {
        super(a, b);
        third = c;
    }

    public String toString() {
        return "(" + first + ", " + second + ", " + third + ")";
    }

}
