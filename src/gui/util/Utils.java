package gui.util;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

public class Utils {
	
	//By event, identify what stage object is occurring
	public static Stage currentStage(ActionEvent event)
	{
		return (Stage) ((Node) event.getSource()).getScene().getWindow();
	}

}
