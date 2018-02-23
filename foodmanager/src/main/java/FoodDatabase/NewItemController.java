package FoodDatabase;

import java.io.IOException;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Label;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

public class NewItemController extends SelectorComposer<Component> {
	private static final long serialVersionUID = 1L;

	Main ME = new Main();
	
	@Wire
	private Window addItemWin;
	@Wire
	private Textbox txBoxFoodName;
	@Wire
    private Combobox cBoxCategory;
	@Wire
	private Checkbox chBoxVegan;
	@Wire
    private Textbox txBoxTimeMinutes;
	@Wire
    private Combobox cBoxOccasion;
	@Wire
    private Combobox cBoxTypeOfMeal;
	@Wire
    private Textbox txBoxSideDish;
	@Wire
    private Textbox txBoxFoodComment;
	@Wire
    private Textbox txBoxImage;
	@Wire
    private Textbox txBoxLink;
	@Wire
    private Textbox txBoxGrade;
	@Wire
	private Label lblResult;

	@Listen("onClick = #cancelButton")
	public void cancel() {
		addItemWin.detach();
	}
	
	@Listen("onClick = #addItemButton")
	public void addItem() {
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
        
		
		ME.addFood(txBoxFoodName.getText(), 
					timeMinutes, 
					cBoxOccasion.getText(), 
					cBoxCategory.getText(),
					cBoxTypeOfMeal.getText(),
					 txBoxSideDish.getText(), 
					 txBoxFoodComment.getText(),
					 txBoxLink.getText(),
					 txBoxImage.getText(), 
					 grade, chBoxVegan.isChecked());
		lblResult.setValue("Adding succeeded");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		addItemWin.detach();
		
		//populateGrid();
		
	}
	
}