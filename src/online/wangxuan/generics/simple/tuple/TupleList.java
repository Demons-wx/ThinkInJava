package online.wangxuan.generics.simple.tuple;

import java.util.ArrayList;

/**
 * 泛型的一个重要好处就是能够简单而安全的创建复杂的模型。
 *
 * 例如，我们可以很容器的创建list元组：
 * Created by wangxuan on 2017/8/13.
 */
public class TupleList<A, B, C, D> extends ArrayList<FourTuple<A, B, C, D>> {

    public static void main(String[] args) {
        TupleList<Vehicle, Amphibian, String, Integer> t1 =
                new TupleList<>();
        t1.add(TupleTest.h());
        t1.add(TupleTest.h());

        for (FourTuple i : t1)
            System.out.println(i);
    }
}
