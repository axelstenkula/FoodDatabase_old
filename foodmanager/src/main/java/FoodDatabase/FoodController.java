package FoodDatabase;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Messagebox.ClickEvent;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Vlayout;
import org.zkoss.zul.Window;
 
public class FoodController extends SelectorComposer<Component> {
    private static final long serialVersionUID = 1L;
    private static final int QUICK_TIME_DEF = 15;
 
    private FoodService foodService = new FoodServiceImpl();
    private ListModel<FoodItem> FoodItemsModel = new ListModelList<FoodItem>();
    private FoodItem RandomFoodItem;
 
    @Wire
    private Textbox txBoxSearchString;
    
    @Wire
    private Checkbox tosCheckbox;
    
    @Wire
    private Combobox cBoxCategory;

    @Wire
    private Combobox cBoxTypeOfMeal;

    @Wire
    private Combobox cBoxOccasion;
    
    @Wire
    private Checkbox chBoxQuick;
    
    @Wire
    private Combobox cBoxGrade;
    
    public FoodController() {
    }
    
    public void populateGrid() {
    	((ListModelList<FoodItem>)FoodItemsModel).clear();
        List<Food> foods = foodService.findAll();
        for(int i = 0; i < foods.size(); ++i) {
            Food food = foods.get(i);
            
            int grade = -1;
            try
            {
            	grade = Integer.parseInt(cBoxGrade.getText());
            }
            catch(NumberFormatException numberformatexception) { }
            catch(Exception exception) { }
            boolean addItem = false;
            try {
            	if((food.contains(txBoxSearchString.getText())) &&
            			(cBoxCategory.getText().equals("") || food.getCategory().equals(cBoxCategory.getText())) &&
            			(cBoxTypeOfMeal.getText().equals("") || food.getTypeOfMeal().equals(cBoxTypeOfMeal.getText())) &&
            			(!chBoxQuick.isChecked() || food.getTimeMinutes() > 0 && food.getTimeMinutes() <= QUICK_TIME_DEF) &&
            			(grade == -1 || grade <= food.getGrade()) &&
            			(cBoxOccasion.getText().equals("") || food.getOccasion().equals(cBoxOccasion.getText())))
            	{
            		addItem = true;
            	}
            }
            catch (NullPointerException ex)
            {
            	addItem = true;	
            }
            if(addItem) {
            	FoodItem foodItem = new FoodItem(food.getId(), food.getFoodName(), food.getCategory(), food.getTypeOfMeal(), food);
	            ((ListModelList<FoodItem>)FoodItemsModel).add(foodItem);
            }
        }
    }
 
    public ListModel<FoodItem> getFoodItemsModel() {
        return FoodItemsModel;
    }
 
    public String getTotalPrice() {
    	/*
        double totalPrice = 0.0;
        for(FoodItem FoodItem : ((ListModelList<FoodItem>)FoodItemsModel).getInnerList())
            totalPrice += FoodItem.getSubtotalPrice();
        return MessageFormat.format("{0,number,#.0}", totalPrice);
        */
        return "";
    }
     
    @Listen("onCreate = #win")
    public void init() {
    	/*
        Clients.showNotification("Accept terms of service before submit the order","info",tosCheckbox,"end_center",3000);
        */
    }
 
    @Listen("onClick = #randomItemBtn")
    @NotifyChange("FoodItemsModel")
    public void randomItem() {


        // TOS should be checked before accepting order
        //if(tosCheckbox.isChecked()) {
    	RandomFoodItem = foodService.getRandomItem(((ListModelList<FoodItem>)FoodItemsModel));
    	//foodService.food(((ListModelList<FoodItem>)FoodItemsModel));
    	
    	
            // show result
            Map<String, Object> arguments = new HashMap<String, Object>();
            arguments.put("RandomFoodItem", RandomFoodItem);
            //arguments.put("FoodItems", FoodItemsModel);
            String template = "food_randomItem.zul";
            Window window = (Window)Executions.createComponents(template, null, arguments);
            window.doModal();

            populateGrid();
        
    }
 
    @Listen("onClick = #uploadCsvBtn")
    @NotifyChange("FoodItemsModel")
    public void uploadCsvFile() {

            String template = "food_importCsv.zul";
            Window window = (Window)Executions.createComponents(template, null, null);
            window.doModal();
            
            populateGrid();
            
    }
    
    @Listen("onClick = #addItemBtn")
    @NotifyChange("FoodItemsModel")
    public void addItem() {

            String template = "food_addItem.zul";
            Window window = (Window)Executions.createComponents(template, null, null);
            window.doModal();
            
            populateGrid();
    }
    
	@Command
	@Listen("onClick = #listAllButton")
	@NotifyChange("FoodItemsModel")
	public void listItems() {
		populateGrid();
	}	
	
//	@Command
//	@Listen("onChange = #gradeChanged")
//	@NotifyChange("FoodItemsModel")
//	public void gradeChanged() {
//		populateGrid();
//	}
	
	@Command
	@Listen("onClick = #cleanButton")
	public void cleanFilter() {
		txBoxSearchString.setText("");
		cBoxCategory.setText("");
		cBoxTypeOfMeal.setText("");
		chBoxQuick.setChecked(false);
		cBoxGrade.setText("");
		cBoxOccasion.setText("");
	}
}