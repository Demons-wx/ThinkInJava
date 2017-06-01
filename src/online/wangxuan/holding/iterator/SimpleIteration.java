package online.wangxuan.holding.iterator;

import java.util.Iterator;
import java.util.List;

import online.wangxuan.typeinfo.pets.Pet;
import online.wangxuan.typeinfo.pets.Pets;

/**
 * Java的Iterator只能单向移动，这个Iterator只能用来：<br>
 * 1. 使用方法iterator()要求容器返回一个Iterator。Iterator将准备好返回序列的第一个元素。<br>
 * 2. 使用next()获得序列中的下一个元素。<br>
 * 3. 使用hasNext()检查序列中是否还有元素。<br>
 * 4. 使用remove()将迭代器新近返回的对象删除。 <br>
 * 
 * @author wx
 *
 */
public class SimpleIteration {
	public static void main(String[] args) {
		List<Pet> pets = Pets.arrayList(12);
		Iterator<Pet> it = pets.iterator();
		while(it.hasNext()) {
			Pet p = it.next();
			System.out.print(p.id() + ": " + p + " ");
		}
		System.out.println();
		
		// a simple approach when possible
		for (Pet p : pets) {
			System.out.print(p.id() + ": " + p + " ");
		}
		System.out.println();
		
		it = pets.iterator();
		for (int i = 0; i < 6; i++) {
			it.next();
			it.remove();
		}
		System.out.println(pets);
	}
}
