package gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.entities.Department;
import model.services.DepartmentService;

public class DepartmentListController implements Initializable{
	
	private DepartmentService service;
	
	@FXML
	private TableView<Department> tableViewDepartmentList;
	@FXML
	private TableColumn<Department, Integer> tableCollumId;
	@FXML
	private TableColumn<Department, String> tableCollumName;
	@FXML
	private Button btnNew;
	
	
	//A list that allows listeners to track changes when they occur.
	private ObservableList<Department> obsList;
	
	
	/*Events*/
	
	@FXML
	public void onButtonNewAction()
	{
		System.out.println("onButtonNewAction");
	}
	
	/*Settings*/
	
	
	public void setDepartmentService(DepartmentService service)
	{
		this.service = service;
	}
	

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO Auto-generated method stub
		
		initializeNodes();
		
	}

	private void initializeNodes() {
		// TODO Auto-generated method stub
		
		//initialize columns on the TableView
		tableCollumId.setCellValueFactory(new PropertyValueFactory<Department, Integer>("id"));
		tableCollumName.setCellValueFactory(new PropertyValueFactory<Department, String>("name"));
		
		//align table view with height of the main window.
		Stage stage = (Stage) Main.getMainScene().getWindow();
		tableViewDepartmentList.prefHeightProperty().bind(stage.heightProperty());
	}
	
	/*Methods*/
	
	public void updateTableView()
	{
		if(service == null)
		{
			throw new IllegalStateException("Sevice is null");
		}
		
		List<Department> list = service.findAll();
		obsList = FXCollections.observableArrayList(list);
		
		tableViewDepartmentList.setItems(obsList);		
		
	}

}
