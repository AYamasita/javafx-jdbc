package gui;

import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.entities.Department;

public class DepartmentListController implements Initializable{
	
	@FXML
	private TableView<Department> tableViewDepartmentList;
	@FXML
	private TableColumn<Department, Integer> tableCollumId;
	@FXML
	private TableColumn<Department, String> tableCollumName;
	@FXML
	private Button btnNew;
	
	@FXML
	public void onButtonNewAction()
	{
		System.out.println("onButtonNewAction");
	}
	

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO Auto-generated method stub
		
		initializeNodes();
		
	}


	private void initializeNodes() {
		// TODO Auto-generated method stub
		
		//inicializar as colunas na TableView
		tableCollumId.setCellValueFactory(new PropertyValueFactory<Department, Integer>("id"));
		tableCollumName.setCellValueFactory(new PropertyValueFactory<Department, String>("name"));
		
		//align table view with height of the main window.
		Stage stage = (Stage) Main.getMainScene().getWindow();
		tableViewDepartmentList.prefHeightProperty().bind(stage.heightProperty());
	}

}
