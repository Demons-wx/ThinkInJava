package online.wangxuan.holding.set;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import net.mindview.util.Print;

public class SetOperations {
	public static void main(String[] args) {
		Set<String> set1 = new HashSet<String>();
		Collections.addAll(set1, "A B C D E F G H I J K L".split(" "));
		set1.add("M");
		Print.print("H: " + set1.contains("H"));
		Print.print("N: " + set1.contains("N"));
		Set<String> set2 = new HashSet<String>();
		Collections.addAll(set2, "H I J K L".split(" "));
		Print.print("set2 in set1: " + set1.containsAll(set2));
		set1.remove("H");
		Print.print("set1: " + set1);
		Print.print("set2 in set1: " + set1.containsAll(set2));
		set1.removeAll(set2);
		Print.print("set2 removed from set1: " + set1);
		Collections.addAll(set1, "X Y Z".split(" "));
		Print.print("'X Y Z' added to set1: " + set1);
	}
}
