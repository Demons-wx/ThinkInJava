package online.wangxuan.generics.method;

import online.wangxuan.generics.generator.Generator;

/**
 * <p><b>一个通用的Generator</b></p>
 *
 * 下面的程序可以为任何类构造一个Generator，只要该类具有默认的构造器。
 * 为了减少类型声明，它提供了一个泛型方法，用于生成BasicGenerator：
 * Created by wangxuan on 2017/8/11.
 */
public class BasicGenerator<T> implements Generator<T> {

    private Class<T> types;

    public BasicGenerator(Class<T> types) {
        this.types = types;
    }

    @Override
    public T next() {
        try {
            return types.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> Generator<T> create(Class<T> type) {
        return new BasicGenerator<T>(type);
    }
}
