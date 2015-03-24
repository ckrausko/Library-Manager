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
import javafx.scene.control.Label;
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
	private Label lblAccountID;
	@FXML
	private void addAccount(ActionEvent event) throws IOException{
		

		if(txtFirstName.getText().trim().isEmpty() == false && txtLastName.getText().trim().isEmpty() == false && 
				txtAddress.getText().trim().isEmpty() == false && txtCity.getText().trim().isEmpty() == false &&
				txtState.getText().trim().isEmpty() == false && txtZip.getText().trim().isEmpty() == false )
		{
			
		
	
			try{
				// Get connection to database
				Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library_db", "root", "password");
				// create a statement
				Statement myStmt = myConn.createStatement();
				// execute sql query
				
				String sql = " insert into account " 
						+ " (first_name, last_name, address, city,  state, zip) "
						+ " values ( " + "'" + txtFirstName.getText()+ "'" + "," + "'" + txtLastName.getText()+ "'" + "," + "'" + txtAddress.getText() + "'" + ","
						+ "'" + txtState.getText() + "'" + "," + "'" + txtCity.getText() + "'" + "," + "'" + txtZip.getText() + "'" + " )";
						
				myStmt.executeUpdate(sql);
				
				
				ResultSet accountID = myStmt.executeQuery("SELECT account_id FROM account"
						+ " ORDER BY account_id DESC"
						+ " LIMIT 1");
				accountID.next();
				lblAccountID.setText( "Your Library Account ID # is : " + (accountID.getString(("account_id"))));
				
			}
			catch(Exception exc){
				exc.printStackTrace();
			}
		}
		else{
			lblAccountID.setText("Every field must be filled out");
			
			
		}
		
	}
	
	
	
	
	
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	

}
