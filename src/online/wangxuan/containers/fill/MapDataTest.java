package online.wangxuan.containers.fill;

import java.util.Iterator;

import online.wangxuan.containers.util.Pair;
import net.mindview.util.CountingGenerator;
import net.mindview.util.Generator;
import net.mindview.util.Print;
import net.mindview.util.RandomGenerator;

public class MapDataTest {
	public static void main(String[] args) {
		Print.print(MapData.map(new Letters(), 11));
		Print.print(MapData.map(new CountingGenerator.Character(), new RandomGenerator.String(3), 8));
		Print.print(MapData.map(new CountingGenerator.Character(), "Value", 6));
		Print.print(MapData.map(new Letters(), new RandomGenerator.String(3)));
		Print.print(MapData.map(new Letters(), "value"));
	}
}

class Letters implements Generator<Pair<Integer, String>>, Iterable<Integer> {
	private int size = 9;
	private int number = 1;
	private char letter = 'A';
	public Iterator<Integer> iterator() {
		return new Iterator<Integer>() {
			public boolean hasNext() {
				return number < size;
			}
			public Integer next() {
				return number++;
			}
			public void remove() {
				throw new UnsupportedOperationException();
			}
			
		};
	}
	public Pair<Integer, String> next() {
		return new Pair<Integer, String>(number++, "" + letter++);
	}
}