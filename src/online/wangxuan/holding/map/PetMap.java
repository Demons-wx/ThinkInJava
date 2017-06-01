package online.wangxuan.holding.map;

import java.util.HashMap;
import java.util.Map;

import net.mindview.util.Print;

import online.wangxuan.typeinfo.pets.Cat;
import online.wangxuan.typeinfo.pets.Dog;
import online.wangxuan.typeinfo.pets.Hamster;
import online.wangxuan.typeinfo.pets.Pet;

public class PetMap {
	public static void main(String[] args) {
		Map<String, Pet> petMap = new HashMap<String, Pet>();
		petMap.put("My Cat", new Cat("Molly"));
		petMap.put("My Dog", new Dog("Ginger"));
		petMap.put("My Hamster", new Hamster("Bosco"));
		Print.print(petMap);
		Pet dog = petMap.get("My Dog");
		Print.print(dog);
		Print.print(petMap.containsKey("My Dog"));
		Print.print(petMap.containsValue(dog));
	}
}
