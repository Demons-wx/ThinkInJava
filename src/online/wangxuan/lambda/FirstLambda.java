package online.wangxuan.lambda;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BinaryOperator;
import java.util.function.Predicate;

/**
 * Created by wangxuan on 2017/6/23.
 */
public class FirstLambda {
    public static void main(String[] args) {
        /*List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6);
        list.forEach(n -> System.out.println(n));
        list.forEach(System.out::println);
        int sum = list.stream().map(x -> x*x).reduce((x, y) -> x + y).get();
        System.out.println(sum);*/

        /*Runnable noArguments = () -> System.out.println("Hello world");
        new Thread(noArguments).start();*/

       /* BinaryOperator<Long> add = (x, y) -> x * y;
        System.out.println(add.apply(1L, 2L));

        useHahsMap(new HashMap<>());*/

        Predicate<Integer> atLeast5 = x -> x > 5;
        System.out.println(atLeast5.test(4));


    }

    private static void useHahsMap(Map<String, String> values) {

    }
}
