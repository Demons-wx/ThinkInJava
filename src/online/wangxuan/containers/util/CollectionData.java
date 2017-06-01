package online.wangxuan.containers.util;

import java.util.ArrayList;

import net.mindview.util.Generator;

/**
 * 事实上，所有的Collection子类型都有一个接收另一个Collection对象的构造器，
 * 用所接收的Collection对象中的元素来填充新的容器。
 * @author wx
 *
 */
public class CollectionData<T> extends ArrayList<T> {
	public CollectionData(Generator<T> gen, int quantity) {
		for (int i = 0; i < quantity; i++) {
			add(gen.next());
		}
	}
	public static <T> CollectionData<T> list(Generator<T> gen, int quantity) {
		return new CollectionData<>(gen, quantity);
	}
}
