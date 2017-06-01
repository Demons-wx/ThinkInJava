package online.wangxuan.holding.map;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.mindview.util.Print;

import online.wangxuan.typeinfo.pets.Cat;
import online.wangxuan.typeinfo.pets.Cymric;
import online.wangxuan.typeinfo.pets.Dog;
import online.wangxuan.typeinfo.pets.Mutt;
import online.wangxuan.typeinfo.pets.Person;
import online.wangxuan.typeinfo.pets.Pet;
import online.wangxuan.typeinfo.pets.Pug;
import online.wangxuan.typeinfo.pets.Rat;

/**
 * Map与数组和其他的Collection一样，可以很容易地扩展到多维，而我们只需要将其值 <br>
 * 设置为Map(这些Map的值可以是其他容器，甚至是其他Map)。因此，我们能够很容易地将容器 <br>
 * 组合起来从而快速的生成强大的数据结构。<br><br>
 * 
 * 例如，假设你正在跟踪拥有多个宠物的人，你只需要一个Map<Person, List<Pet>>
 * @author wx
 *
 */
public class MapOfList {
	public static Map<Person, List<? extends Pet>> 
		petPeople = new HashMap<Person, List<? extends Pet>>();
	static {
		petPeople.put(new Person("Dawn"), 
				Arrays.asList(new Cymric("Molly"), new Mutt("Spot")));
		petPeople.put(new Person("Kate"), 
				Arrays.asList(new Cat("Shackleton"), 
						new Cat("Elsie May"), new Dog("Margrett")));
		petPeople.put(new Person("Marilyn"),
				Arrays.asList(new Pug("Louie aka Louis Snorkelstein Dupree"),
					new Cat("Stanford aka Stinky el Negro"), 
					new Cat("Pinkola")));
		petPeople.put(new Person("Luke"), 
				Arrays.asList(new Rat("Fuzzy"), new Rat("Fizzy")));
		petPeople.put(new Person("Isaac"), 
				Arrays.asList(new Rat("Freckly")));
	}
	public static void main(String[] args) {
		Print.print("People: " + petPeople.keySet());
		Print.print("Pets: " + petPeople.values());
		for (Person person : petPeople.keySet()) {
			Print.print(person + "has:");
			for (Pet pet : petPeople.get(person)) {
				Print.print("	" + pet);
			}
		}
	}
}

























