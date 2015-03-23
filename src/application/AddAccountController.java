package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddAccountController implements Initializable {
	//go back to main menu
	@FXML
	private Button btnBack;
	@FXML
	private void sceneBack(ActionEvent event) throws IOException{
		Parent mainWindowParent = FXMLLoader.load(getClass().getResource("Main.fxml"));
		Scene mainWindowScene = new Scene(mainWindowParent);
		Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		app_stage.setScene(mainWindowScene);
		app_stage.show();
	}
	
	//add account 
	@FXML
	private Button btnAddAcct;
	@FXML
	private TextField txtFirstName;
	@FXML
	private TextField txtLastName;
	@FXML
	private TextField txtAddress;
	@FXML
	private TextField txtState;
	@FXML
	private TextField txtCity;
	@FXML
	private TextField txtZip;
	@FXML
	private void addAccount(ActionEvent event) throws IOException{
		
		System.out.println(txtFirstName.getText()) ;
		System.out.println(txtLastName.getText()) ;
		System.out.println(txtAddress.getText()) ;
		System.out.println(txtState.getText()) ;
		System.out.println(txtCity.getText()) ;
		System.out.println(txtZip.getText()) ;
		
	
		try{
			// Get connection to database
			Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library_db", "root", "Runningman168");
			// create a statement
			Statement myStmt = myConn.createStatement();
			// execute sql query
			String sql = " insert into account " 
					+ " (first_name, last_name, address, city,  state, zip) "
					+ " values ( " + "'" + txtFirstName.getText()+ "'" + "," + "'" + txtLastName.getText()+ "'" + "," + "'" + txtAddress.getText() + "'" + ","
					+ "'" + txtState.getText() + "'" + "," + "'" + txtCity.getText() + "'" + "," + "'" + txtZip.getText() + "'" + " )";
					
			myStmt.executeUpdate(sql);
			
		}
		catch(Exception exc){
			exc.printStackTrace();
		}
		
	}
	
	
	
	
	
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	

}
