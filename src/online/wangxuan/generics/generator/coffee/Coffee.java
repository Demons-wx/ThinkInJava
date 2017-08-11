package online.wangxuan.generics.generator.coffee;

/**
 * Created by wangxuan on 2017/8/10.
 */
public class Coffee {
    private static long counter = 0;
    private final long id = counter++;
    public String toString() {
        return getClass().getSimpleName() + " " + id;
    }
}
