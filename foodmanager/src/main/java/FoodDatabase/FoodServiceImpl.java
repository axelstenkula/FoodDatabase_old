package FoodDatabase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale.Category;
import java.util.Map;
import java.util.Random;

public class FoodServiceImpl implements FoodService {

		private List<Food> foods = new ArrayList<Food>();
		private Map<String, List<Food>> queryResults;
		private Category rootCategory;
		private static List<String> categories;
		private static List<String> typeOfMeals;
		private static List<String> occasions;
		private static List<Integer> grades;

		static Main ME = new Main();
		
		public FoodServiceImpl() {
			ME.init();
			
			foods = ME.getFoodList();
			categories = new ArrayList<String>();
			typeOfMeals = new ArrayList<String>();
			occasions = new ArrayList<String>();
			grades = new ArrayList<Integer>();
		}

		public List<Food> findAll() {
			return ME.getFoodList();
		}

		public void store(Food car) {
			// process data
			// ...
		}

		/*public void store(InventoryItem inventoryItem) {
			// process data
			// ...
		}*/

		public FoodItem getRandomItem(List<FoodItem> foodItems) {
			FoodItem foodItem = new FoodItem();
			foodItem.setFood(ME.getRandomItem());
			return foodItem;
		}
		
		public FoodItem getItem(Integer id) {
			FoodItem foodItem = new FoodItem();
			foodItem.setFood(ME.getFood(id));
			return foodItem;
		}
		
		public void food(List<FoodItem> foodItems) {
			//return foodItems.get(0);
		}

//		public Category getFoodCategoriesRoot() {
//			return rootCategory;
//		}

		
		public void increaseNumberOfSelections(Integer id){
			ME.updateFood(id, ME.getFood(id).getNumberOfSelections() + 1 );
		}
		
		
		public List<String> getCategories()
		{
			List<Food> foodList = ME.getFoodList();
			
			for (Food food : foodList) {
				if(!categories.contains(food.getCategory())) categories.add(food.getCategory());
			}
			return categories;
		}
		
		public List<String> getTypeOfMeals()
		{
			List<Food> foodList = ME.getFoodList();
			
			for (Food food : foodList) {
				if(!typeOfMeals.contains(food.getTypeOfMeal())) typeOfMeals.add(food.getTypeOfMeal());
			}
			return typeOfMeals;
		}
		
		public List<String> getOccasions()
		{
			List<Food> foodList = ME.getFoodList();
			
			for (Food food : foodList) {
				if(!occasions.contains(food.getOccasion())) occasions.add(food.getOccasion());
			}
			return occasions;
		}
		
		public List<Integer> getGrades()
		{
			int[] array = {1,2,3,4,5};
			for (int i=0;i<array.length; i++) {
				if(!grades.contains(array[i])) grades.add(array[i]);
			}
			return grades;
			
		}
	}
