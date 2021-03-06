package gui;


import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import gui.listeners.DataChangeListener;
import gui.util.Alerts;
import gui.util.Utils;
import javafx.beans.property.ReadOnlyBooleanWrapper;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.entities.Department;
import model.services.DepartmentService;

public class DepartmentListController implements Initializable, DataChangeListener {
	
	
	private DepartmentService service;
	
	@FXML
	private TableView<Department> tableViewDepartmentList;
	@FXML
	private TableColumn<Department, Integer> tableCollumId;
	@FXML
	private TableColumn<Department, String> tableCollumName;
	@FXML
	private TableColumn<Department, Department> tableColumnEDIT;	
	@FXML
	private Button btnNew;
	
	
	//A list that allows listeners to track changes when they occur.
	private ObservableList<Department> obsList;
	
	
	/*Events*/
	
	@FXML
	public void onButtonNewAction(ActionEvent event)
	{
		System.out.println("onButtonNewAction");
		Stage parentStage = Utils.currentStage(event);
		
		Department obj = new Department();
		createDialogForm(obj,"/gui/DepartmentForm.fxml", parentStage);
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
	
	private void initEditButtons() 
	{  	
		tableColumnEDIT.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue())); 
		tableColumnEDIT.setCellFactory(param -> new TableCell<Department, Department>() { 
			private final Button button = new Button("edit"); 
	 
			@Override   protected void updateItem(Department obj, boolean empty) {  
				super.updateItem(obj, empty); 
	 
				if (obj == null) {   
					setGraphic(null);  
					return;
					} 
	 
				setGraphic(button);  
				button.setOnAction(  
						event -> createDialogForm( 
								obj, "/gui/DepartmentForm.fxml",Utils.currentStage(event)));
				}  });
		} 
	 
	 
	
	public void updateTableView()
	{
		if(service == null)
		{
			throw new IllegalStateException("Sevice is null");
		}
		
		List<Department> list = service.findAll();
		obsList = FXCollections.observableArrayList(list);
		
		tableViewDepartmentList.setItems(obsList);	
		
		initEditButtons();
		
	}
	
	public void createDialogForm(Department obj, String absoluteName,Stage parentStage){
	
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			Pane pane = loader.load();
			
			DepartmentFormController controller = loader.getController();
			controller.setDepartment(obj);
			controller.setDepartmentService(service);
			controller.subscribeDataChangeListener(this);
			controller.updateFormData();
			
			
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Enter Department data");
			dialogStage.setScene(new Scene(pane));
			dialogStage.setResizable(false);
			dialogStage.initOwner(parentStage);
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.showAndWait();
			
			
		}
		catch (IOException e) {		 
			Alerts.showAlert("IOException", "Error loading view", e.getMessage(), AlertType.ERROR);
		}
		
	}

	@Override
	public void onDataChanged() {
		// TODO Auto-generated method stub
		updateTableView();
	}

}
