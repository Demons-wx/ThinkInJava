package online.wangxuan.containers.hashcode;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import online.wangxuan.holding.map.MapOfList;
import online.wangxuan.typeinfo.pets.Individual;
import online.wangxuan.typeinfo.pets.Pet;

public class IndividualTest {
	public static void main(String[] args) {
		Set<Individual> pets = new TreeSet<Individual>();
		for (List<? extends Pet> lp : MapOfList.petPeople.values()) {
			for (Pet p : lp) {
				pets.add(p);
			}
		}
		System.out.println(pets);
	}
}
