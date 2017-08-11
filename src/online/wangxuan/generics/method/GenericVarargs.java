package online.wangxuan.generics.method;

import java.util.ArrayList;
import java.util.List;

/**
 * 可变参数与泛型方法
 *
 * 泛型方法与可变参数列表能够很好的共享：
 * Created by wangxuan on 2017/8/11.
 */
public class GenericVarargs {

    public static <T> List<T> makeList(T... args) {
        List<T> result = new ArrayList<T>();
        for (T item : args)
            result.add(item);

        return result;
    }

    public static void main(String[] args) {
        List<String> ls = makeList("A");
        System.out.println(ls);

        ls = makeList("A", "B", "C");
        System.out.println(ls);

        ls = makeList("ABCDEFGHIJKLMNOPQRSTUVWXYZ".split(""));
        System.out.println(ls);
    }
}
