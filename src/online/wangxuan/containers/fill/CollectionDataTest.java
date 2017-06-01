package online.wangxuan.containers.fill;

import java.util.LinkedHashSet;
import java.util.Set;

import net.mindview.util.Generator;

public class CollectionDataTest {
	public static void main(String[] args) {
		Set<String> set = new LinkedHashSet<String>(
				new CollectionData<String>(new Government(), 15));
		// Using the convenience method;	
	//	set.addAll(CollectionData.list(new Government(), 15));
		System.out.println(set);
	}
}

class Government implements Generator<String> {
	String[] foundation = ("strange women lying in ponds distributing swords " +
			"is no basis for a system of government").split(" ");
	private int index;
	@Override
	public String next() {
		return foundation[index++];
	}
}