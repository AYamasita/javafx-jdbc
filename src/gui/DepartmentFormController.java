package gui;

import java.net.URL;
import java.util.ResourceBundle;

import gui.util.Constraints;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.entities.Department;

public class DepartmentFormController implements Initializable{
	
	private Department entity;
	
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
	public void onActionBtnSave()
	{
		System.out.println("onActionBtnSave");
	}
	
	@FXML
	public void onActionBtnCancel()
	{
		System.out.println("onActionBtnCancel");
	}
	
	
	public void setDepartment(Department department)
	{
		entity = department;
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
		txtId.setText(String.valueOf(entity.getId()));
		txtName.setText(entity.getName());
	}

}
