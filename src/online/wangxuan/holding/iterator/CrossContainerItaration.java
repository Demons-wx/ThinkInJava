package online.wangxuan.holding.iterator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.TreeSet;

import online.wangxuan.typeinfo.pets.Pet;
import online.wangxuan.typeinfo.pets.Pets;

public class CrossContainerItaration {
	/* display()方法不包含任何有关它所遍历的序列的类型信息，而这也展示了Iterator
	 * 真正威力：能够将遍历序列的操作与序列底层结构分离，正由于此，我们有时会说：迭代器
	 * 统一了对容器的访问方式。 */
	public static void display(Iterator<Pet> it) {
		while(it.hasNext()) {
			Pet p = it.next();
			System.out.print(p.id() + ": " + p + " ");
		}
		System.out.println();
	}
	public static void main(String[] args) {
		ArrayList<Pet> pets = Pets.arrayList(8);
		LinkedList<Pet> petsLL = new LinkedList<Pet>(pets);
		HashSet<Pet> petsHS = new HashSet<Pet>(pets);
		TreeSet<Pet> petsTS = new TreeSet<Pet>(pets);
		display(pets.iterator());
		display(petsLL.iterator());
		display(petsHS.iterator());
		display(petsTS.iterator());
	}
}
