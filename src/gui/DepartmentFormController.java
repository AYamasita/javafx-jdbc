package gui;


import java.net.URL;
import java.util.ResourceBundle;

import db.DbException;
import gui.util.Alerts;
import gui.util.Constraints;
import gui.util.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import model.entities.Department;
import model.services.DepartmentService;

public class DepartmentFormController implements Initializable{
	
	//dependencia
	private Department entity;
	private DepartmentService service;
	
	@FXML
	private TextField txtId;
	@FXML
	private TextField txtName;
	@FXML
	private Button btnSave;
	@FXML
	private Button btnCancel;
	@FXML
	private Label labelErrorName;
	
	@FXML
	public void onActionBtnSave(ActionEvent event)
	{
		if(entity == null)
		{
			throw new IllegalStateException("Entity was null.");
		}
		
		if(service == null)
		{
			throw new IllegalStateException("Service was null.");
		}
		
		System.out.println("onActionBtnSave");
		
		try {
			entity = getFormData();		
			service.saveOrUpdate(entity);
			Utils.currentStage(event).close();
			
		} catch (DbException e) {
			// TODO: handle exception
			Alerts.showAlert("Erros saving department", null, e.getMessage(), AlertType.ERROR);
		}		
				
	}
	
	private Department getFormData() {
		// TODO Auto-generated method stub
		Department obj = new Department();
		obj.setId(Utils.tryParseToInt(txtId.getText()));
		obj.setName(txtName.getText());
		
		return obj;
	}

	@FXML
	public void onActionBtnCancel(ActionEvent event)
	{
		System.out.println("onActionBtnCancel");
		Utils.currentStage(event).close();
	}
	
	
	public void setDepartment(Department department)
	{
		entity = department;
	}
	
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
		Constraints.setTextFieldInteger(txtId);
		Constraints.setTextFieldMaxLength(txtName, 30);
	}
	
	public void updateFormData()
	{
		if(entity == null)
		{
			throw new IllegalStateException("Entity is null");
		}
		txtId.setText(entity.getId() == null ? "" : String.valueOf(entity.getId()));
		txtName.setText(entity.getName());
	}

}
