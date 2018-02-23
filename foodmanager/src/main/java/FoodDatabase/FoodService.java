package FoodDatabase;

import java.util.List;
import java.util.Locale.Category;
 
public interface FoodService {
 
    /**
     * Retrieve all Foods in the Food store.
     * @return all Foods.
     */
    public List<Food> findAll();
 
    /**
     * Store or modify a Food in Food store.
     */
    void store(Food Food);
 
    /**
     * Store or modify a inventory item in Food store.
     * @return 
     */
    void food(List<FoodItem> foodItems);
    
    FoodItem getRandomItem(List<FoodItem> foodItems);
    
	FoodItem getItem(Integer id);
    
    /**
     * Retrieve the root of Food categories.
     */
    //Category getFoodCategoriesRoot();
 

	void increaseNumberOfSelections(Integer id);
	
	List<String> getCategories();
	
	List<String> getTypeOfMeals();
	
	List<String> getOccasions();
	
	List getGrades();
}