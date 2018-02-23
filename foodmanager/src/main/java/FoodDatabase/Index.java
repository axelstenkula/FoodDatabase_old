package FoodDatabase;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Vlayout;
import org.zkoss.zul.Window;


public class Index extends SelectorComposer<Component> {
	private static final long serialVersionUID = 1L;

	@Wire
	private Window win;
	
	@Wire
	private Vlayout listAll;

	@Listen("onClick = #listAllButton")
	public void listAll() {
		System.out.println("eee");
		listAll.setVisible(true);
	}
	

}