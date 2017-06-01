package online.wangxuan.enumerated;

import online.wangxuan.enumerated.Food.Appetizer;
import online.wangxuan.enumerated.Food.Coffee;
import online.wangxuan.enumerated.Food.Dessert;
import online.wangxuan.enumerated.Food.MainCourse;

public class TypeOfFood {

	public static void main(String[] args) {
		Food food = Appetizer.SALAD;
		food = MainCourse.HUMMOUS;
		food = Dessert.BLACK_FOREST_CAKE;
		food = Coffee.CAPPUCCINO;
		
	}
	
	
}
