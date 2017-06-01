package online.wangxuan.holding.iterator;

import java.util.List;

import online.wangxuan.typeinfo.pets.Pet;
import online.wangxuan.typeinfo.pets.Pets;

/**
 * ListIterator是一个更强大的Iterator的子类型，它只能用于各种List类的访问。 <br>
 * ListIterator可以双向移动，还可以产生相对于迭代器在列表中指向的当前位置的前一个和 <bt>
 * 后一个元素的索引，并且可以使用set()方法替换它访问过的最后一个元素。<br>
 * <br>
 * 你可以通过调用listIterator()方法产生一个指向List开始处的ListIterator <br>
 * 还可以通过调用listIterator(n)方法创建一个一开始就指向列表索引为n的元素处的ListIterator
 * 
 * @author wx
 *
 */
public class ListIterator {
	public static void main(String[] args) {
		List<Pet> pets = Pets.arrayList(8);
		java.util.ListIterator<Pet> it = pets.listIterator();
		while(it.hasNext()) {
			System.out.print(it.next() + ", " + it.nextIndex()
					+ ", " + it.previousIndex() + "; ");
		}
		System.out.println();
		// Backwards
		while(it.hasPrevious()) {
			System.out.print(it.previous().id() + " ");
		}
		System.out.println();
		System.out.println(pets);
		it = pets.listIterator(3);
		/* Pets.randomPet()用来替换在列表中从位置3开始向后的所有Pet对象 */
		while(it.hasNext()) {
			it.next();
			it.set(Pets.randomPet());
		}
		System.out.println(pets);
	}
}
