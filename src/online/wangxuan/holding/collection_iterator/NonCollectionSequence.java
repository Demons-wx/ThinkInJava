package online.wangxuan.holding.collection_iterator;

import java.util.Iterator;

import online.wangxuan.typeinfo.pets.Pet;
import online.wangxuan.typeinfo.pets.Pets;

/**
 * 从示例CollectionSequence可以看到，如果实现Collection，就必须实现iterator()， <br>
 * 如果只拿实现iterator()与继承AbstractCollection相比，花费的代价只有略微减少，<br>
 * 但是如果你的类已经继承了其他的类，那么你就不能再继承AbstractCollection了。
 * @author wx
 *
 */
public class NonCollectionSequence extends PetSequence {
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
	public static void main(String[] args) {
		NonCollectionSequence nc = new NonCollectionSequence();
		InterfaceVsIterator.display(nc.iterator());
	}
}

class PetSequence {
	protected Pet[] pets = Pets.createArray(8);
}