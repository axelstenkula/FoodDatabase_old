package localdomain.localhost.gui_project;

import java.util.Iterator;
import java.util.List;

import FoodDatabase.Food;
import FoodDatabase.Main;

public class Test {

	public static String getString(){
		Main ME = new Main();
		Food food;
		List foods = ME.getFoodList();
		StringBuilder sb = new StringBuilder();
		for(Iterator iterator = foods.iterator(); iterator.hasNext();)
	    {
	        food = (Food)iterator.next();
	        sb.append(food.toString() + System.lineSeparator());
	    }
		
		return sb.toString();
		
	}
}
