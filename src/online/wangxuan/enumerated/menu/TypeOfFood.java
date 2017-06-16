package online.wangxuan.enumerated.menu;

import online.wangxuan.enumerated.menu.Food;
import online.wangxuan.enumerated.menu.Food.Appetizer;
import online.wangxuan.enumerated.menu.Food.Coffee;
import online.wangxuan.enumerated.menu.Food.Dessert;
import online.wangxuan.enumerated.menu.Food.MainCourse;

public class TypeOfFood {

	public static void main(String[] args) {
		Food food = Appetizer.SALAD;
		food = MainCourse.HUMMOUS;
		food = Dessert.BLACK_FOREST_CAKE;
		food = Coffee.CAPPUCCINO;
		
	}
	
	
}
