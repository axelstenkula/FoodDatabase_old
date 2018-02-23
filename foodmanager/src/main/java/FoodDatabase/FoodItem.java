package FoodDatabase;

import org.hibernate.dialect.MySQL5Dialect;

public class FoodItem {
	private int id;
	private String foodName;
	private String category;
	private int timeMinutes;
	private String occasion;
	private String sideDish;
	private String foodComment;
	private String link;
	private int grade;
	private int numberOfSelections;
	private String typeOfMeal;
	private byte[] image;
	private Food food;
	private boolean isDeleted;
	private boolean isVegan;
	
	public FoodItem(){}
	
    public FoodItem(int id, String foodName, String category, String typeOfMeal, Food food) {
    	this.setId(id);
        this.setFoodName(foodName);
        this.setCategory(category);
        this.setFood(food);
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFoodName() {
		return foodName;
	}

	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getTypeOfMeal() {
		return typeOfMeal;
	}

	public void setTypeOfMeal(String typeOfMeal) {
		this.typeOfMeal = typeOfMeal;
	}

	public Food getFood() {
		return food;
	}

	public void setFood(Food food) {
		this.food = food;
	}

	public int getTimeMinutes() {
		return timeMinutes;
	}

	public void setTimeMinutes(int timeMinutes) {
		this.timeMinutes = timeMinutes;
	}

	public String occasion() {
		return occasion;
	}

	public void setOccasion(String occasion) {
		this.occasion = occasion;
	}

	public String getSideDish() {
		return sideDish;
	}

	public void setSideDish(String sideDish) {
		this.sideDish = sideDish;
	}

	public String getFoodComment() {
		return foodComment;
	}

	public void setFoodComment(String foodComment) {
		this.foodComment = foodComment;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	
	public int getNumberOfSelections() {
		return numberOfSelections;
	}

	public void setNumberOfSelections(int numberOfSelections) {
		this.numberOfSelections = numberOfSelections;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public boolean isVegan() {
		return isVegan;
	}

	public void setIsVegan(boolean isVegan) {
		this.isVegan = isVegan;
	}
}
