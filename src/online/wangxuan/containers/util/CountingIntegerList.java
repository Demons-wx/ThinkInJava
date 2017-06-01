package online.wangxuan.containers.util;

import java.util.AbstractList;

/**
 * 下面的类是一个List，它可以具有任意尺寸，并且用Integer数据进行了预初始化
 * @author wx
 *
 */
public class CountingIntegerList extends AbstractList<Integer> {
	private int size;
	/*
	 * 为了从AbstractList创建只读的List，你必须实现get()和size()。
	 * 这里再次使用了享元的解决方案，当你寻找值时，get()将产生它，因此这个List实际上并不必组装。
	 * */
	public CountingIntegerList(int size) {
		this.size = size < 0 ? 0 : size;
	}
	public Integer get(int index) {
		return Integer.valueOf(index);
	}
	public int size() {
		return size;
	}
	public static void main(String[] args) {
		System.out.println(new CountingIntegerList(30));
	}
}
