package online.wangxuan.containers.fill;

import java.util.ArrayList;
import java.util.HashSet;

import net.mindview.util.RandomGenerator;

public class CollectionDataGeneration {
	public static void main(String[] args) {
		System.out.println(new ArrayList<String>(CollectionData.list(new RandomGenerator.String(9), 10)));
		System.out.println(new HashSet<Integer>(CollectionData.list(new RandomGenerator.Integer(), 10)));
	}
}
