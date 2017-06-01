package online.wangxuan.holding;

import java.util.ArrayList;

public class ApplesAndOrangesWithGenerics {
	public static void main(String[] args) {
		ArrayList<Apple> apples = new ArrayList<Apple>();
		for (int i = 0; i < 3; i++) {
			apples.add(new Apple());
		}
		// 编译错误
		//	apples.add(new Orange());
		for (int j = 0; j < apples.size(); j++) {
			System.out.println(apples.get(j).id());
		}
		// using foreach
		for (Apple apple : apples) {
			System.out.println(apple.id());
		}
	}
}
