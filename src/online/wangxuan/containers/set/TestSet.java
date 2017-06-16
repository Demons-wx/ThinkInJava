package online.wangxuan.containers.set;

import java.util.*;

/**
 * Created by wangxuan on 2017/6/14.
 */
public class TestSet {

    public static void main(String[] args) {
        Set<Integer> set = new TreeSet<Integer>();
        for (int i = 0; i < 12; i++) {
            set.add(i);
        }
        System.out.println("Set: " + set);

        List<Integer> list = new ArrayList<>(set);
        System.out.println("List: " + list);

        set.clear();
        System.out.println("clearSet: " + set);

        set.addAll(list.subList(0, 10));
        System.out.println("subSet: " + set);
    }
}
