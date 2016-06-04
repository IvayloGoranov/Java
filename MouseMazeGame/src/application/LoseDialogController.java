package application;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class LoseDialogController {

	public Button buttonNo;
	public Button buttonYes;
	
	public void onYes(ActionEvent actionEvent) {
		
		Stage stage = (Stage)buttonYes.getScene().getWindow();
		stage.close();
	}
	
	public void onNo(ActionEvent actionEvent) {
	
		Platform.exit();
	}
}
