package online.wangxuan.holding;

import java.util.ArrayList;
import java.util.LinkedList;

import net.mindview.util.Print;

import online.wangxuan.typeinfo.pets.Hamster;
import online.wangxuan.typeinfo.pets.Pet;
import online.wangxuan.typeinfo.pets.Pets;
import online.wangxuan.typeinfo.pets.Rat;

/**
 * LinkedList添加了可以使其用作栈、队列或双端队列的方法。 <br><br>
 * 
 * getFirst()和element()完全一样，它们都返回列表的头，而并不移除它，如果list为空，则抛出
 * NoSuchElementException，peek()方法与这两个方法略有不同，它在列表为空时返回null。<br><br>
 * 
 * removeFirst()和remove()完全一样，它们移除并返回列表的头，如果list为空，则抛出
 * NoSuchElementException，poll()方法略有不同，它在列表为空时返回null。<br><br>
 * 
 * @author wx
 *
 */
public class LinkedListFeatures {
	public static void main(String[] args) {
		LinkedList<Pet> pets = new LinkedList<Pet>(Pets.arrayList(5));
		Print.print(pets);
		// Identical:
		Print.print("pets.getFirst(): " + pets.getFirst());
		Print.print("pets.element(): " + pets.element());
		// Only differs in empty-list behavior
		Print.print("pets.peek(): " + pets.peek());
		// Identical: remove and return the first element
		Print.print("pets.remove(): " + pets.remove());
		Print.print("pets.removeFirst(): " + pets.removeFirst());
		//  Only differs in empty-list behavior
		Print.print("pets.poll(): " + pets.poll());
		Print.print(pets);
		pets.addFirst(new Rat());
		Print.print("after addFirst(): " + pets);
		pets.offer(Pets.randomPet());
		Print.print("After offer(): " + pets);
		pets.add(Pets.randomPet());
		Print.print("After add(): " + pets);
		pets.addLast(new Hamster());
		Print.print("After addLast(): " + pets);
		Print.print("pets.removeLast(): " + pets.removeLast());
	}
}
