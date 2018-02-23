package FoodDatabase;

import java.util.HashMap;
import java.util.Map;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Vlayout;
import org.zkoss.zul.Window;

public class ResultController extends SelectorComposer<Component> {
	private static final long serialVersionUID = 1L;


	Main ME = new Main();
	
	@Wire 
	private FoodItem foodItem;
	
	@Wire
	private Window resultWin;
	
	@Wire
	private Window updateItemWin;
	
	@Wire
	private Vlayout popup;
	
	@Wire
	private Textbox txBoxId;
	@Wire
	private Textbox txBoxFoodName;
	@Wire
    private Combobox cBoxCategory;
	@Wire
    private Textbox txBoxTimeMinutes;
	@Wire
    private Combobox cBoxOccasion;
	@Wire
    private Combobox cBoxTypeOfMeal;
	@Wire
	private Checkbox chBoxVegan;
	@Wire
    private Textbox txBoxSideDish;
	@Wire
    private Textbox txBoxFoodComment;
	@Wire
    private Textbox txBoxImage;
	@Wire
    private Textbox txBoxLink;
	@Wire
	private Textbox txBoxNumberOfSelections;
	@Wire
	private Textbox txBoxGrade;

	private FoodService foodService = new FoodServiceImpl();
    	
	@Listen("onClick = #closeButton")
	public void close() {
		if(resultWin!=null) resultWin.detach();
		if(popup!=null) popup.detach();
	}
	
	@Listen("onClick = #cancelBtn")
	public void cancel() {
		if(updateItemWin!=null) updateItemWin.detach();
	}
	
	
	@Listen("onClick = #generateNew")
	public void generateNew() {

		// TODO: Fixa
	}
	
	@Listen("onClick = #updateBtn")
    public void update() {
		Integer id = -1;
        try
        {
        	id = Integer.parseInt(txBoxId.getText());
        }
        catch(NumberFormatException numberformatexception) { }

		foodItem = foodService.getItem(id);
    	
        // show result
        Map<String, Object> arguments = new HashMap<String, Object>();
        arguments.put("FoodItem", foodItem);
        String template = "food_updateItem.zul";
        Window window = (Window)Executions.createComponents(template, null, arguments);
        window.doModal();
            
    }
	
	
	@Listen("onClick = #selectButton")
    public void selectAndUpdateNumberOfTimes() {
		
		Integer id = -1;
        try
        {
        	id = Integer.parseInt(txBoxId.getText());
        }
        catch(NumberFormatException numberformatexception) { }

		foodService.increaseNumberOfSelections(id);
    	
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(resultWin!=null) resultWin.detach();	

		//populateGrid();
    }
	

	@Listen("onClick = #updateItemBtn")
    public void updateItem() {

		Integer id = -1;
        try
        {
        	id = Integer.parseInt(txBoxId.getText());
        }
        catch(NumberFormatException numberformatexception) { }
        
		int timeMinutes = -1;
        try
        {
        	timeMinutes = Integer.parseInt(txBoxTimeMinutes.getText());
        }
        catch(NumberFormatException numberformatexception) { }
        
        int grade = -1;
        try
        {
        	grade = Integer.parseInt(txBoxGrade.getText());
        }
        catch(NumberFormatException numberformatexception) { }

		int numberOfSelections = 0;
        try
        {
        	numberOfSelections = Integer.parseInt(txBoxNumberOfSelections.getText());
        }
        catch(NumberFormatException numberformatexception) { }
		
		ME.updateFood(id,
				txBoxFoodName.getText(), 
					timeMinutes, 
					cBoxOccasion.getText(), 
					cBoxCategory.getText(),
					cBoxTypeOfMeal.getText(),
					 txBoxSideDish.getText(), 
					 txBoxFoodComment.getText(),
					 txBoxLink.getText(),
					 txBoxImage.getText(),
					 grade,
					 numberOfSelections,
					 chBoxVegan.isChecked());
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		updateItemWin.detach();
		

        //populateGrid();

    }
	
	@Listen("onClick = #deleteItemBtn")
    public void deleteItem() {
		
		// TODO: Fixa verifieringsfråga för delete, kanske även update?
		
		Integer id = -1;
        try
        {
        	id = Integer.parseInt(txBoxId.getText());
        }
        catch(NumberFormatException numberformatexception) { }
        
		ME.deleteFood(id);

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		updateItemWin.detach();
            

        //populateGrid();
	}
	
	
}

