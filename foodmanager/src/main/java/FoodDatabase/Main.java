package FoodDatabase;

import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.engine.internal.Versioning;

public class Main
{

	private static SessionFactory factory;
    private static BufferedReader br;
	private String implementationVersion;

    static 
    {
        br = new BufferedReader(new InputStreamReader(System.in));
    }
	
    public Main()
    {
//    	Init();
//    	
//    	Properties prop = new Properties();
//    	InputStream input = null;

//    	try {

    	//	input = new FileInputStream("config.properties");

//    		String a = prop.getProperty("a");


  //  	} catch (IOException io) {
    //		io.printStackTrace();
    //	} finally {
//    		if (input != null) {
//    			try {
//    				input.close();
//    			} catch (IOException e) {
//    				e.printStackTrace();
//    			}
//    		}

    	//}
    }

    public static void main(String args[])
        throws IOException
    {
        
        Main ME = new Main();
        System.out.println("Hello and welcome.");
        do
        {
            System.out.println("Select one of the options below by entering number");
            System.out.println("--------------------------------------------------");
            System.out.println("1. Add to database from input");
            System.out.println("2. Add to database from csv-file");
            System.out.println("3. Display all rows in database");
            System.out.println("4. Delete food item");
            System.out.println("5. Update food item");
            System.out.println("6. Get random item");
            System.out.println("7. List one item");
            System.out.println("8. Filter items");
            System.out.println("0. Exit");
            System.out.println("");
            System.out.print("Enter selection: ");
            int option = -1;
            try
            {
                option = Integer.parseInt(br.readLine());
            }
            catch(NumberFormatException nfe)
            {
                System.err.println("Invalid Format!");
            }
            switch(option)
            {
            case 1: // '\001'
            	
                ME.addFromInput(ME);
                break;

            case 2: // '\002'
                System.out.print("Enter filepath (leave empty if doc/test.csv): ");
                try
                {
                    String path = br.readLine();
                    path = path.equals("") ? "doc/test.csv" : path;
                    ME.importCsvFile(path);
                }
                catch(Exception e)
                {
                    System.err.println((new StringBuilder("Failed to read csv file ")).append(e).toString());
                }
                break;

            case 3: // '\003'
                ME.listFoods();
                break;

            case 4: // '\004'
                System.out.print("Enter number of food item to delete: ");
                int foodID = -1;
                try
                {
                    foodID = Integer.parseInt(br.readLine());
                }
                catch(NumberFormatException nfe)
                {
                    System.err.println("Invalid Format!");
                }
                System.out.println((new StringBuilder("Are you sure you want to delete food item \"")).append(ME.getFood(Integer.valueOf(foodID)).toString()).append("\", (yes/no)?").toString());
                String answer = br.readLine();
                if(answer.equals("yes"))
                {
                    ME.deleteFood(Integer.valueOf(foodID));
                    break;
                }
                if(!answer.equals("no"))
                    System.out.println("No valid option, delete aborted");
                break;

            case 5: // '\005'
                System.out.print("Enter number of food item to update: ");
                int foodUpdateID = -1;
                try
                {
                    foodUpdateID = Integer.parseInt(br.readLine());
                }
                catch(NumberFormatException nfe)
                {
                    System.err.println("Invalid Format!");
                }

                System.out.println("Listing all properties and values, update the one you like");
                // --
                System.out.println("Food name: " + ME.getFood(Integer.valueOf(foodUpdateID)).getFoodName());
        		System.out.println("New food name (leave empty if you want to keep the old): ");
                String newFoodName = br.readLine();
                // --
                System.out.println("Time (min): " + ME.getFood(Integer.valueOf(foodUpdateID)).getTimeMinutes());
                System.out.println("New time (min) (leave empty if you want to keep the old): ");
                int newTimeMin = -1;
                try
                {
                    newTimeMin = Integer.parseInt(br.readLine());
                }
                catch(NumberFormatException numberformatexception) { }
                // --
                System.out.println("Grade (1-5): " + ME.getFood(Integer.valueOf(foodUpdateID)).getGrade());
                System.out.println("New grade (1-5) (leave empty if you want to keep the old): ");
                int newGrade = -1;
                try
                {
                    newGrade = Integer.parseInt(br.readLine());
                }
                catch(NumberFormatException numberformatexception) { }
                // --
                System.out.println("Occasion: " + (ME.getFood(Integer.valueOf(foodUpdateID)).getOccasion()));
        		System.out.println("New occasion (True/False) (leave empty if you want to keep the old): ");
                String newOccasion = br.readLine();
                // --
                System.out.println("Food type: " + ME.getFood(Integer.valueOf(foodUpdateID)).getCategory());
        		System.out.println("New food type (leave empty if you want to keep the old): ");
                String newCategory = br.readLine();
                // --
                System.out.println("Type of meal: " + ME.getFood(Integer.valueOf(foodUpdateID)).getTypeOfMeal());
        		System.out.println("New type of meal (leave empty if you want to keep the old): ");
                String newTypeOfMeal = br.readLine();
                // --
                System.out.println("Side dish: " + ME.getFood(Integer.valueOf(foodUpdateID)).getSideDish());
        		System.out.println("New side dish (leave empty if you want to keep the old): ");
                String newSideDish = br.readLine();
                // --
                System.out.println("Food comment: " + ME.getFood(Integer.valueOf(foodUpdateID)).getFoodComment());
        		System.out.println("New food comment (leave empty if you want to keep the old): ");
                String newFoodComment = br.readLine();
                // --
                System.out.println("Link: " + ME.getFood(Integer.valueOf(foodUpdateID)).getLink());
        		System.out.println("New link (leave empty if you want to keep the old): ");
                String newLink = br.readLine();
                // --
                System.out.println("Image: " + ME.getFood(Integer.valueOf(foodUpdateID)).getImage());
        		System.out.println("New image (leave empty if you want to keep the old): ");
                String newImage = br.readLine();              

                ME.updateFood(Integer.valueOf(foodUpdateID), newFoodName, newTimeMin, newOccasion, newCategory,
                		newTypeOfMeal, newSideDish, newFoodComment, newLink, newImage, newGrade,
                		ME.getFood(foodUpdateID).getNumberOfSelections(), false);
                break;

            case 6:
            	
            	// Question if old selections should be used
            	// ...
            	
            	// Find random foodID
            	Food randomFood = ME.getRandomItem();

            	System.out.println("Random selection:");
            	System.out.println(randomFood);
            	System.out.println("Do you want to increase number of selections (yes/no)?");

                String updateAnswer = br.readLine();
                if(updateAnswer.equals("yes"))
                {
                    ME.updateFood(randomFood.getId(),
                    		randomFood.getNumberOfSelections() + 1);
                    System.out.println("Number of selections updated");
                    break;
                }
                else if (updateAnswer.equals("no"))
                    System.out.println("Number of selections NOT updated");
                else 
                	System.out.println("Unknown answer. Number of selections NOT updated");
                break;
                
            case 7: // '\007'
                System.out.print("Enter number of food item to display: ");
                int dispFoodID = -1;
                try
                {
                    dispFoodID = Integer.parseInt(br.readLine());
                }
                catch(NumberFormatException nfe)
                {
                    System.err.println("Invalid Format!");
                }
                System.out.println(ME.getFood(Integer.valueOf(dispFoodID)));
                System.out.println("Do you want to select this item and update number of selections (yes/no)?");
                answer = br.readLine();
                if(answer.equals("yes"))
                {
                	ME.updateFood(dispFoodID,
                    		ME.getFood(dispFoodID).getNumberOfSelections() + 1);
                    System.out.println("Number of selections updated");
                    break;
                }
                else if (answer.equals("no"))
                    System.out.println("Number of selections NOT updated");
                else 
                	System.out.println("Unknown answer. Number of selections NOT updated");
                break;

            case 8:
            	System.out.println("Enter the search string you want to filter on: ");
                String searchString = br.readLine();
                ME.listFilteredItems(searchString);
                break;

            case 0: // '\0'
                System.out.println("Closing, good bye");
                return;

            default:
                System.out.println((new StringBuilder("Invalid selection: ")).append(option).toString());
                break;
            }
            System.out.println("");
        } while(true);
    }

	public void init() throws ExceptionInInitializerError {
		implementationVersion = getClass().getPackage().getImplementationVersion();
		
		try
        {
            if(factory == null) {
				String property = System.getProperty("catalina.base");
				if (property==null) {
					property = new java.io.File(".").getCanonicalPath();
				}
				property = property + "\\config\\properties.xml";
				
				factory = (new Configuration()).configure(
						new File(property)).buildSessionFactory();
			}
        }
        catch(Throwable ex)
        {
            System.err.println((new StringBuilder("Failed to create sessionFactory object.")).append(ex).toString());
            throw new ExceptionInInitializerError(ex);
        }
	}

    private void listFilteredItems(String searchString) {
		// TODO Search in sql instead?
    	Food food;
    	List foodList = getFoodList();
    	ArrayList<Food> filteredList = new ArrayList<Food>();
	    	
    	for(Iterator iterator = foodList.iterator(); iterator.hasNext();)
	    {
	        food = (Food)iterator.next();
	        if(food.contains(searchString)) filteredList.add(food);
	    }
    	
    	printList(filteredList);
	}

	// Only update number of selections
    public void updateFood(int foodId, int numberOfSelections) {
		Session session;
        Transaction tx;
        session = factory.openSession();
        tx = null;
        try
        {
            tx = session.beginTransaction();
            Food food = getFood(foodId);
            if(numberOfSelections != -1)
                food.setNumberOfSelections(numberOfSelections);
            session.update(food);
            tx.commit();
        }
        catch(HibernateException e)
        {
            if(tx != null)
                tx.rollback();
            e.printStackTrace();
        }
        session.close();
		
	}

	public Food getRandomItem() {
    	Food food;
    	List foodList = getFoodList();
    	Random r = new Random(System.currentTimeMillis());

    	if(true)
    	{	
    		// Don't care about previous selections
    		food = (Food) foodList.get(r.nextInt(foodList.size()));
    	}
    	else
    	{
    		// TODO: If number of selections should be handled
	    	
	    	for(Iterator iterator = foodList.iterator(); iterator.hasNext();)
		    {
		        food = (Food)iterator.next();
		    }
    	}
    	
    	return food;
	}

	public void addFromInput(Main mE)
        throws IOException
    {
        System.out.print("Enter Food name: ");
        String foodName = br.readLine();
        //System.out.println((new StringBuilder("String ")).append(firstname).append(" read").toString());
        
        System.out.println("Enter time (min): ");
        int timeMinutes = -1;
        try
        {
        	timeMinutes = Integer.parseInt(br.readLine());
        }
        catch(NumberFormatException numberformatexception) {
            System.err.println("Invalid Format!");
        }
        // --
        System.out.println("Enter Occasion (true/false): ");
		String occasion = br.readLine();
        System.out.print("Enter Food type: ");
        String category = br.readLine();
        System.out.print("Enter type of meal: ");
        String typeOfMeal = br.readLine();
        System.out.print("Enter side dish: ");
        String sideDish = br.readLine();
        System.out.print("Enter Food comment: ");
        String foodComment = br.readLine();
        System.out.print("Enter link: ");
        String link = br.readLine();
        System.out.print("Enter image: ");
        String imageString = br.readLine();
        
        System.out.println("Enter grade (1-5): ");
        int grade = -1;
        try
        {
        	grade = Integer.parseInt(br.readLine());
        }
        catch(NumberFormatException numberformatexception) {
            System.err.println("Invalid Format!");
        }
        // --
        
        Integer empID1 = null;
        empID1 = mE.addFood(foodName, timeMinutes, occasion, category, typeOfMeal, 
      		   sideDish, foodComment, link, imageString, grade, false);
    }

    public Integer addFood(String foodName, int timeMinutes, String occasion, String category, String typeOfMeal, 
 		   String sideDish, String foodComment, String link, String imageString, int grade, boolean isVegan)
    {
        Session session;
        Transaction tx;
        Integer foodID;
        session = factory.openSession();
        tx = null;
        foodID = null;
        int numberOfSelections = 0;
        try
        {
            tx = session.beginTransaction();
            Food food = new Food(foodName, timeMinutes, occasion, category, typeOfMeal, 
         		   sideDish, foodComment, link, imageString, grade, numberOfSelections, isVegan);
            foodID = (Integer)session.save(food);
            tx.commit();
        }
        catch(HibernateException e)
        {
            if(tx != null)
                tx.rollback();
            e.printStackTrace();
        }
        session.close();
        return foodID;
    }

    public void listFoods()
    {
	    List<Food> foods = getFoodList();
	    printList(foods);
    }
    
	    
    private void printList(List<Food> foods)
	    {
	    int shortLength = 5;
	    int mediumLength = 10;
	    int longLength = 20;

	    Food food;
	
	    if(foods != null)
	    {
		    // Heading
	    	StringBuilder sb = new StringBuilder();
	    	sb.append(getCompactString("ID", shortLength) + "\t");
	    	sb.append(getCompactString("Food name",longLength) + "\t");
	    	sb.append(getCompactString("Time (min)",mediumLength) + "\t");
	    	sb.append(getCompactString("Occasion",shortLength) + "\t");
	    	sb.append(getCompactString("Food type",longLength) + "\t");
	    	sb.append(getCompactString("Type of meal",longLength) + "\t");
	    	sb.append(getCompactString("Side dish",longLength) + "\t");
	    	sb.append(getCompactString("Food comment",longLength) + "\t");
	    	sb.append(getCompactString("Link",longLength) + "\t");
	    	sb.append(getCompactString("Image",longLength) + "\t");
	    	System.out.println(sb.toString());
		    
		    for(Iterator<Food> iterator = foods.iterator(); iterator.hasNext();)
		    {
		        food = iterator.next();
		    
		        sb = new StringBuilder();
		        sb.append(getCompactString(food.getId() + "", shortLength) + "\t");
		    	sb.append(getCompactString(food.getFoodName(),longLength) + "\t");
		    	sb.append(getCompactString(food.getTimeMinutes() + "",mediumLength) + "\t");
		    	sb.append(getCompactString(food.getOccasion() + "",shortLength) + "\t");
		    	sb.append(getCompactString(food.getCategory(),longLength) + "\t");
		    	sb.append(getCompactString(food.getTypeOfMeal(),longLength) + "\t");
		    	sb.append(getCompactString(food.getSideDish(),longLength) + "\t");
		    	sb.append(getCompactString(food.getFoodComment(),longLength) + "\t");
		    	sb.append(getCompactString(food.getLink(),longLength) + "\t");
		    	//sb.append(getCompactString(food.getImage(),longLength) + "\t");
		    	sb.append(getCompactString(food.getGrade() + "",mediumLength) + "\t");
		    	System.out.println(sb.toString());
    	   }
	    }
    
    }
    
    
	private String getCompactString(String string, int i) {
		// Add spaces
		// Cut and add ... if to long
		// Return substring of i
		StringBuilder sb = new StringBuilder();
		for(int j=0;j<i;j++){sb.append(" ");}
		
		if(string.length()>i&& i >=3)
			string = string.substring(0, i-2) + "..";
		return (string + sb.toString()).substring(0, i);
	}

	public List<Food> getFoodList()
	{
        List<Food> foods = null;
        List<Food> resultFoods = new ArrayList<Food>();
    	Session session;
        Transaction tx;
        session = factory.openSession();
        tx = null;
        try
        {
            tx = session.beginTransaction();
            foods = session.createQuery("FROM Food").list();
           
            tx.commit();
        }
        catch(HibernateException e)
        {
            if(tx != null)
                tx.rollback();
            e.printStackTrace();
        }
        session.close();
        
        Collections.sort(foods, new FoodComparator());
        
        for (Food food : foods) {
			if(!food.getIsDeleted())
        		resultFoods.add(food);
        }
        	
//        for (Food food : foods) {
//           	if(food.getIsDeleted()) 
//        		foods.remove(food);
//       	}
	    return resultFoods;
        
    }          

    public void updateFood(Integer FoodID, String foodName, int timeMinutes, String occasion, String category, 
    		String typeOfMeal, String sideDish, String foodComment, String link, String image, int grade, int numberOfSelections, boolean isVegan)
    {
        Session session;
        Transaction tx;
        session = factory.openSession();
        tx = null;
        try
        {
            tx = session.beginTransaction();
            Food food = getFood(FoodID);
            if(!foodName.equals(""))
                food.setFoodName(foodName);
            if(timeMinutes != -1)
                food.setTimeMinutes(timeMinutes);
            if(!foodName.equals(""))
                food.setFoodName(foodName);
            food.setOccasion(occasion);
            if(!category.equals(""))
                food.setCategory(category);
            if(!typeOfMeal.equals(""))
                food.setTypeOfMeal(typeOfMeal);
            if(!sideDish.equals(""))
                food.setSideDish(sideDish);
            if(!foodComment.equals(""))
                food.setFoodComment(foodComment);
            if(!link.equals(""))
                food.setLink(link);
          //  if(!image.equals(""))
         //       food.setImage(image);
            if(grade != -1)
                food.setGrade(grade);
            food.setIsVegan(isVegan);

            if(numberOfSelections != -1)
                food.setNumberOfSelections(numberOfSelections);
            session.update(food);
            tx.commit();
        }
        catch(HibernateException e)
        {
            if(tx != null)
                tx.rollback();
            e.printStackTrace();
        }
        session.close();
    }

    public Food getFood(Integer foodID)
    {
        Session session;
        Transaction tx;
        Food food;
        session = factory.openSession();
        tx = null;
        food = null;
        try
        {
            tx = session.beginTransaction();
            food = session.get(Food.class, foodID);
        }
        catch(HibernateException e)
        {
            if(tx != null)
                tx.rollback();
            e.printStackTrace();
        }
        session.close();
        return food;
    }

    public void deleteFood(Integer FoodID)
    {	
        Session session;
        Transaction tx;
        session = factory.openSession();
        tx = null;
        try
        {
            tx = session.beginTransaction();
            Food food = session.get(Food.class, FoodID);
            food.setIsDeleted(true);
            session.update(food);
            tx.commit();
        }
        catch(HibernateException e)
        {
            if(tx != null)
                tx.rollback();
            e.printStackTrace();
        }
        session.close();        
    }

    public void importCsvFile(String filepath)
        throws IOException
    {
        Exception exception;
        exception = null;
        Object obj = null;
        BufferedReader br = new BufferedReader(new FileReader(filepath.replaceAll("\"", "")));
        StringBuilder sb = new StringBuilder();
        //Skip first row for headings
        String line = br.readLine();
        line = br.readLine();
        for(Integer rowNr = Integer.valueOf(1); line != null; rowNr = Integer.valueOf(rowNr.intValue() + 1))
        {
        	line+=";";
        	line = line.replace(";;","; ;");
            String row[] = line.split(";");

            int grade = -1;
            
            boolean isVegan = false;
            
            if(row.length >= 11)
            {

                try
                {
                	if(row[10]!=null){
                		isVegan = Boolean.parseBoolean(row[10]);
                	}
                }
                catch(Exception e) { }
            }
            
            if(row.length >= 10)
            {

                try
                {
                	if(row[9]!=null) grade = Integer.parseInt(row[9]);
                }
                catch(NumberFormatException numberformatexception) { }
            }
            if(row.length >= 9)
            {
            	int timeMinutes = -1;
                try
                {
                	timeMinutes = Integer.parseInt(row[1]);
                }
                catch(NumberFormatException numberformatexception) { }
            	
                Integer id = addFood(row[0], timeMinutes, row[2], row[3], 
                		row[4], row[5], row[6], row[7], row[8], grade, isVegan);
                if(id == null)
                    sb.append((new StringBuilder("Row number ")).append(rowNr).append(" could not be read: line").append(System.lineSeparator()).toString());
            }
            line = br.readLine();
        }

        if(br != null)
            br.close();
    }
}