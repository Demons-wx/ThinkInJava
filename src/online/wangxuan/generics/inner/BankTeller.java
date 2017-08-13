package online.wangxuan.generics.inner;

import online.wangxuan.generics.generator.Generator;
import online.wangxuan.generics.method.Generators;

import java.util.*;

/**
 * 泛型还可以应用于内部类以及匿名内部类。下面是示例使用匿名内部类来实现Generator接口：
 * Created by wangxuan on 2017/8/13.
 */
public class BankTeller {

    public static void serve(Teller t, Customer c) {
        System.out.println(t + " serves " + c);
    }

    public static void main(String[] args) {
        Random rand = new Random(47);
        Queue<Customer> line = new LinkedList<Customer>();
        Generators.fill(line, Customer.generator(), 15);
        List<Teller> tellers = new ArrayList<Teller>();
        Generators.fill(tellers, Teller.generator, 4);
        for (Customer c : line)
            serve(tellers.get(rand.nextInt(tellers.size())), c);
    }
}

class Customer {
    private static long counter = 1;
    private final long id = counter++;
    private Customer() {}
    public String toString() {
        return "Customer " + id;
    }

    // A method to produce Generator objects
    public static Generator<Customer> generator() {
        return Customer::new;
    }
}

class Teller {
    private static long counter = 1;
    private final long id = counter++;
    private Teller() {}
    public String toString() {
        return "Teller " + id;
    }

    // A single Generator object:
    public static Generator<Teller> generator = Teller::new;
}

