package online.wangxuan.holding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import net.mindview.util.Print;

import online.wangxuan.typeinfo.pets.Cymric;
import online.wangxuan.typeinfo.pets.Hamster;
import online.wangxuan.typeinfo.pets.Mouse;
import online.wangxuan.typeinfo.pets.Pet;
import online.wangxuan.typeinfo.pets.Pets;

/**
 * 目前对于Pet类，只需要知道两点：<br>
 * 1. 有一个Pet类，以及各种子类型；
 * 2. 静态的Pets.arrayList()方法将返回一个填充了随机选取的Pet对象的ArrayList
 * @author wx
 *
 */
public class ListFeatures {
	public static void main(String[] args) {
		Random rand = new Random(47);
		List<Pet> pets = Pets.arrayList(7);
		Print.print("1: " + pets);
		Hamster h = new Hamster();
		pets.add(h); // Automatically resizes
		Print.print("2: " + pets);
		Print.print("3: " + pets.contains(h));
		pets.remove(h); // Remove by object
		Pet p = pets.get(2);
		Print.print("4: " + p + " " + pets.indexOf(p));
		Pet cymric = new Cymric();
		Print.print("5: " + pets.indexOf(cymric));
		Print.print("6: " + pets.remove(cymric));
		// must be the exact object; 必须是精确对象
		Print.print("7: " + pets.remove(p));
		Print.print("8: " + pets);
		pets.add(3, new Mouse());	// Insert at an index
		Print.print("9: " + pets);
		List<Pet> sub = pets.subList(1, 4);
		Print.print("subList: " + sub);
		Print.print("10: " + pets.contains(sub));
		Collections.sort(sub); // In-place sort
		Print.print("sorted subList: " + sub);
		// Order is not important in containsAll()
		Print.print("11: " + pets.containsAll(sub));
		Collections.shuffle(sub, rand); // Mix it up
		Print.print("shuffled subList: " + sub);
		Print.print("12: " + pets.containsAll(sub));
		List<Pet> copy = new ArrayList<Pet>(pets);
		sub = Arrays.asList(pets.get(1), pets.get(4));
		Print.print("sub: " + sub);
		/* 从此列表中删除未包含在指定集合中的所有元素。 */
		copy.retainAll(sub);
		Print.print("13: " + copy);
		copy = new ArrayList<Pet>(pets); // get a fresh copy
		copy.remove(2); // remove by index
		Print.print("14: " + copy);
		copy.removeAll(sub); // Only removes exact objects
		Print.print("15: " + copy);
		copy.set(1, new Mouse()); // Replace an element
		Print.print("16: " + copy);
		copy.addAll(2, sub); // Insert a list in the middle
		Print.print("17: " + copy);
		Print.print("18: " + pets.isEmpty());
		pets.clear(); // Remove all element
		Print.print("19: " + pets);
		Print.print("20: " + pets.isEmpty());
		pets.addAll(Pets.arrayList(4));
		Print.print("21: " + pets);
		Object[] o = pets.toArray();
		Print.print("22: " + o[3]);
		Pet[] pa = pets.toArray(new Pet[0]);
		Print.print("23: " + pa[3].id());
	}
}

























