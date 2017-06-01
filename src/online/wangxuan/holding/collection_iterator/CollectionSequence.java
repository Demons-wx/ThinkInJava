package online.wangxuan.holding.collection_iterator;

import java.util.AbstractCollection;
import java.util.Iterator;

import online.wangxuan.typeinfo.pets.Pet;
import online.wangxuan.typeinfo.pets.Pets;

/**
 * 通过继承一个持有Pet对象的类来创建一个Collection的实现。<br>
 * 需要实现iterator()和size()方法。
 * @author wx
 *
 */
public class CollectionSequence extends AbstractCollection<Pet> {
	private Pet[] pets = Pets.createArray(8);
	@Override
	public Iterator<Pet> iterator() {
		return new Iterator<Pet>() {
			private int index = 0;
			public boolean hasNext() {
				return index < pets.length;
			}
			public Pet next() {
				return pets[index++];
			}
			public void remove() {
				throw new UnsupportedOperationException();
			}
		};
	}
	@Override
	public int size() {
		return pets.length;
	}
	public static void main(String[] args) {
		CollectionSequence c = new CollectionSequence();
		InterfaceVsIterator.display(c);
		InterfaceVsIterator.display(c.iterator());
	}
}
