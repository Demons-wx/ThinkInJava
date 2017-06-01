package online.wangxuan.holding.collection_iterator;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import online.wangxuan.typeinfo.pets.Pet;
import online.wangxuan.typeinfo.pets.Pets;

/**
 * 在java中，遵循C++的方式，即用迭代器而不是Collection来表示容器之间的共性。<br>
 * 在java中，实现Collection就意味着需要提供iterator()方法。
 * @author wx
 *
 */
public class InterfaceVsIterator {
	public static void display(Iterator<Pet> it) {
		while(it.hasNext()) {
			Pet p = it.next();
			System.out.print(p.id() + ":" + p + " ");
		}
		System.out.println();
	}
	public static void display(Collection<Pet> pets) {
		for (Pet pet : pets) {
			System.out.print(pet.id() + ":" + pet + " ");
		}
		System.out.println();
	}
	public static void main(String[] args) {
		List<Pet> petList = Pets.arrayList(8);
		Set<Pet> petSet = new HashSet<Pet>(petList);
		Map<String, Pet> petMap = new LinkedHashMap<String, Pet>();
		String[] names = ("Ralph, Eric, Lacey, " + "Briteny, Sam, Spot, Fluffy").split(", ");
		for (int i = 0; i < names.length; i++) {
			petMap.put(names[i], petList.get(i));
		}
		display(petList);
		display(petSet);
		display(petList.iterator());
		display(petSet.iterator());
		System.out.println(petMap.keySet());
		display(petMap.values());
		display(petMap.values().iterator());
	}
}
















