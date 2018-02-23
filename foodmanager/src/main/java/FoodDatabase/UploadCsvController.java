package FoodDatabase;

import java.io.IOException;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Label;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

public class UploadCsvController extends SelectorComposer<Component> {
	private static final long serialVersionUID = 1L;

	Main ME = new Main();
	
	@Wire
	private Window uploadCsvWin;
	@Wire
	private Textbox txtBox;
	@Wire
	private Label lblResult;

	@Listen("onClick = #cancelButton")
	public void cancel() {
		uploadCsvWin.detach();
	}
	
	@Listen("onClick = #uploadButton")
	public void uploadCsvFile() {
		try {
			ME.importCsvFile(txtBox.getText());
			lblResult.setValue("File imported succesfully");
		} catch (WrongValueException e) {
			lblResult.setValue("File could not be imported");
			e.printStackTrace();
		} catch (IOException e) {
			lblResult.setValue("File could not be imported");
			e.printStackTrace();
		}

		//populateGrid();
	}
	
}