package online.wangxuan.containers.set;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * 演示为了成功使用特定的Set实现类而必须定义的方法
 * @author wx
 *
 */
public class TypesForSets {
	/* fill()方法可以接受任何类型的Set，以及其相同类型的Class对象，它使用Class对象来发现并接受int参数
	 * 的构造器，然后调用该构造器将元素添加到Set中。 */
	static <T> Set<T> fill(Set<T> set, Class<T> type) {
		try {
			for (int i = 0; i < 10; i++) {
				set.add(type.getConstructor(int.class).newInstance(i));
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return set;
	}
	static <T> void test(Set<T> set, Class<T> type) {
		fill(set, type);
		fill(set, type);
		fill(set, type);
		System.out.println(set);
	}
	public static void main(String[] args) {
		test(new HashSet<HashType>(), HashType.class);
		test(new LinkedHashSet<HashType>(), HashType.class);
		test(new TreeSet<TreeType>(), TreeType.class);
		// things that don't work
		/* 如果我们尝试将没有恰当的支持必须的方法的类型用于需要这些方法的Set，就会出问题。
		 * 对于没有重新定义hashCode()的SetType和TreeType，如果将它们放置到任何散列实现中都会产生重复值，这样就违反了Set的基本契约。
		 *  */
		test(new HashSet<SetType>(), SetType.class);
		test(new HashSet<TreeType>(), TreeType.class);
		test(new LinkedHashSet<SetType>(), SetType.class);
		test(new LinkedHashSet<TreeType>(), TreeType.class);
		/* 如果我们尝试在TreeSet中使用没有实现Comparable的类型，那么就会得到一个异常。 */
		try {
			test(new TreeSet<SetType>(), SetType.class);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		try {
			test(new TreeSet<HashType>(), HashType.class);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}

/**
 * 为了证明哪些方法对于某种特定的Set是必须的，并且同时还要避免代码重复，我们创建了三个类。
 * 基类SetType只存储一个int，并且通过toString()方法产生它的值。因为所有在Set中存储的类
 * 都必须具有equals()方法，因此在基类中也有该方法。其等价性是基于这个int类型的i的值来确定的。
 * @author wx
 *
 */
class SetType {
	int i;
	public SetType(int n) {
		i = n;
	}
	public boolean equals(Object o) {
		return o instanceof SetType && (i == ((SetType)o).i);
	}
	public String toString() {
		return Integer.toString(i);
	}
}

class HashType extends SetType {
	public HashType(int n) {
		super(n);
	}
	public int hashCode() {
		return i;
	}
}

class TreeType extends SetType implements Comparable<TreeType> {
	public TreeType(int n) {
		super(n);
	}
	/* 在compareTo()中，没有使用“简洁明了”的形式 return i-i2 因为这是一个常见的编程错误，它只有在i和i2都是
	 * 无符号的int时才能正确工作。对于Java的有符号int，它就会出错，因为int不够大，不足以表现两个有符号int的差。
	 * 例如i是很大的正整数，j是很大的负整数，i-j就会溢出并且返回负值了，这就不正确了。 */
	public int compareTo(TreeType o) {
		return (o.i < i ? -1 : (o.i == i ? 0 : i));
	}
}