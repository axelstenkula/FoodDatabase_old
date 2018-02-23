package localdomain.localhost.gui_project;

import java.util.Iterator;
import java.util.List;

import FoodDatabase.*;

/**
 * Hello world!
 *
 */
public class App 
{
	static Main ME = new Main();
	
	
    public static void main( String[] args )
    {
    	List foods = ME.getFoodList();
        System.out.println( "Hello World!" );

        Food food;
	    for(Iterator iterator = foods.iterator(); iterator.hasNext();)
	    {
	        food = (Food)iterator.next();
	        System.out.println( food.toString());
	    }
    }
}
