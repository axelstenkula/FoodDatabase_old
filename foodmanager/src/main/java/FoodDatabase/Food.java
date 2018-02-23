package FoodDatabase;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Path;

public class Food {
	private int id;
	private String foodName;
	private int timeMinutes;
	private String occasion;
	private String category;
	private String typeOfMeal;
	private String sideDish;
	private String foodComment;
	private String link;
	private int numberOfSelections;
	private String imageString;
	private int grade;
	private byte[] image;
	private boolean isDeleted;
	private boolean isVegan;

   public Food() {}
   public Food(String foodName, int timeMinutes, String occasion, String category, String typeOfMeal, 
		   String sideDish, String foodComment, String link, String imageString, int grade, int numberOfSelections,
		   Boolean isVegan) {
      this.foodName = foodName;
      this.timeMinutes= timeMinutes;
      this.occasion = occasion;
      this.category = category;
      this.typeOfMeal=typeOfMeal;
      this.sideDish = sideDish;
      this.foodComment = foodComment;
      this.link = link;
      this.imageString = imageString;
      this.grade = grade;
      this.numberOfSelections = numberOfSelections;
      this.setIsDeleted(false);
      this.isVegan = isVegan;
      
      // Read image it is valid
      //TODO: Check imageString if it is OK
      
      if(!imageString.isEmpty()){
    	  File file = new File(imageString);
    	  byte[] bFile = new byte[(int) file.length()];

    	  try {
    		  FileInputStream fileInputStream = new FileInputStream(file);
    		  //convert file into array of bytes
    		  fileInputStream.read(bFile);
    		  fileInputStream.close();
    	  } catch (Exception e) {
    		  e.printStackTrace();
    	  }

    	  this.image = bFile;
      }
      
      
   }
   public int getId() {
      return id;
   }
   public void setId( int id ) {
      this.id = id;
   }
   public String getFoodName() {
      return foodName;
   }
   public void setFoodName( String foodName) {
      this.foodName = foodName;
   }
   public int getTimeMinutes() {
      return timeMinutes;
   }
   public void setTimeMinutes( int timeMinutes) {
      this.timeMinutes = timeMinutes;
   }
   public String getOccasion() {
      return occasion;
   }
   public void setOccasion( String occasion) {
      this.occasion = occasion;
   }
   public String getCategory() {
      return category;
   }
   public void setCategory( String category) {
      this.category = category;
   }
   public String getTypeOfMeal() {
      return typeOfMeal;
   }
   public void setTypeOfMeal( String typeOfMeal) {
      this.typeOfMeal = typeOfMeal;
   }
   public String getSideDish() {
      return sideDish;
   }
   public void setSideDish( String sideDish) {
      this.sideDish = sideDish;
   }
   public String getFoodComment() {
      return foodComment;
   }
   public void setFoodComment( String foodComment) {
      this.foodComment = foodComment;
   }
   public String getLink() {
      return link;
   }
   public void setLink( String link) {
      this.link = link;
   }
   public byte[] getImage() {
      return image;
   }
   public void setImage( byte[] image) {
      this.image = image;
   }
   public int getGrade() {
	      return grade;
	   }
   public void setGrade( int grade) {
	      this.grade = grade;
	   }
   public int getNumberOfSelections()
   {
	   return numberOfSelections;
   }
   public void setNumberOfSelections(int numberOfSelections){
	   this.numberOfSelections= numberOfSelections;
   }
   
   public String toString(){
	   //TODO: Fix general toString method, sometimes \t sometimes ,?
	   return foodName + ", " + timeMinutes + ", " + occasion + ", " + category + ", " + 
			   typeOfMeal + ", " + sideDish + ", " + foodComment + ", " + link + ", " + 
			   image + ", " + grade + ", " + numberOfSelections;
	   
   }
   public boolean contains(String searchString) {
	   return foodName.toLowerCase().toLowerCase().contains(searchString.toLowerCase()) ||
			   category.toLowerCase().contains(searchString.toLowerCase()) ||
			   typeOfMeal.toLowerCase().contains(searchString.toLowerCase()) ||
			   sideDish.toLowerCase().contains(searchString.toLowerCase()) ||
			   foodComment.toLowerCase().contains(searchString.toLowerCase());
   }
public String getImageString() {
	return imageString;
}
public void setImageString(String imageString) {
	this.imageString = imageString;
}
public boolean getIsDeleted() {
	return isDeleted;
}
public void setIsDeleted(boolean isDeleted) {
	this.isDeleted = isDeleted;
}
public boolean getIsVegan() {
	return isVegan;
}
public void setIsVegan(boolean isVegan) {
	this.isVegan = isVegan;
}   
   
}