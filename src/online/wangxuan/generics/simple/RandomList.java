package online.wangxuan.generics.simple;

import java.util.ArrayList;
import java.util.Random;

/**
 * 假设我们需要一个持有特定类型对象的列表，每次调用其上的select()方法时，
 * 他就可以随机的选取一个元素。如果我们希望以此构建一个可以应用于各种类型
 * 的对象的工具，就需要使用泛型：
 * Created by wangxuan on 2017/8/10.
 */
public class RandomList<T> {

    private ArrayList<T> storage = new ArrayList<T>();

    private Random rand = new Random(47);

    public void add(T item) {
        storage.add(item);
    }

    public T select() {
        return storage.get(rand.nextInt(storage.size()));
    }

    public static void main(String[] args) {
        RandomList<String> rs = new RandomList<>();
        for (String s : ("The quick brown fox jumped over the lazy brown dog").split(" "))
            rs.add(s);

        for (int i = 0; i < 11; i++) {
            System.out.print(rs.select() + " ");
        }
    }
}
