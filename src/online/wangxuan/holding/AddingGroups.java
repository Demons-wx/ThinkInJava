package online.wangxuan.holding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * adding groups of elements to Collection objects
 * @author wx
 *
 */
public class AddingGroups {
	public static void main(String[] args) {
		Collection<Integer> collection = 
				new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5));
		Integer[] moreInts = {6, 7, 8, 9, 10};
		// 比上面的构造形式快的多
		collection.addAll(Arrays.asList(moreInts));
		/**
		 * 将所有指定的元素添加到指定的集合。 要添加的元素可以单独指定或作为数组指定。 
		 * 此方便方法的行为与c.addAll(Arrays.asList(elements))的行为相同，
		 * 但此方法在大多数实现中可能运行速度明显更快。
		 */
		Collections.addAll(collection, 11, 12, 13, 14, 15);
		Collections.addAll(collection, moreInts);
		
		// Produces a list backed by an array
		// 可以直接使用Arrays.asList()的输出，将其当作List,但这种情况，其
		// 底层表示是数组，所以不能调整尺寸。所以不支持add()或delete()等方法。
		List<Integer> list = Arrays.asList(16, 17, 18, 19, 20);
		list.set(1, 99);
//		list.add(21); // Unsupported Operation
	}
}
