package online.wangxuan.generics.method;

import online.wangxuan.generics.generator.CoffeeGenerator;
import online.wangxuan.generics.generator.Fibonacci;
import online.wangxuan.generics.generator.Generator;
import online.wangxuan.generics.generator.coffee.Coffee;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 利用生成器，我们可以很方便的填充一个Collection，而泛型化这种操作是具有实际意义的：
 * Created by wangxuan on 2017/8/11.
 */
public class Generators {

    public static <T> Collection<T> fill(Collection<T> coll, Generator<T> gen, int n) {
        for (int i = 0; i < n; i++) {
            coll.add(gen.next());
        }
        return coll;
    }

    public static void main(String[] args) {
        Collection<Coffee> coffee = fill(new ArrayList<Coffee>(), new CoffeeGenerator(), 4);

        for (Coffee c : coffee)
            System.out.println(c);
        Collection<Integer> fnumbers = fill(new ArrayList<Integer>(), new Fibonacci(), 12);
        for (int i : fnumbers)
            System.out.print(i + ", ");
    }
}
