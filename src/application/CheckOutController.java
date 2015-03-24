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

public class CheckOutController implements Initializable{
	
	// back to main menu
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
	
	// FXML fields 
	@FXML
	private Button btnCheckOut;
	@FXML
	private TextField txtAccountID;
	@FXML
	private TextField txtBookID;
	@FXML
	private Label lblOutput;
	@FXML
	private void checkOutBook(ActionEvent event) throws IOException{
		//if all text fields are filled out
		if(txtAccountID.getText().trim().isEmpty() == false && txtBookID.getText().trim().isEmpty() == false)
		{
			
			
		
		
		try{
			// Get connection to database
			Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library_db", "root", "password");
			// create a statement
			Statement myStmt = myConn.createStatement();
			// execute sql query
			String sql = "UPDATE book "
					+ " SET checked_out = '1' , account_id = " + "'" + txtAccountID.getText().trim() + "'" 
					+ " WHERE book_id = " + "'" + txtBookID.getText().trim() + "'";
			
			myStmt.executeUpdate(sql);
			lblOutput.setText("Checked out, book is due in 30 days");
		
			
			
			
		}
		catch(Exception exc){
			exc.printStackTrace();
		}
		
		}
		
		else{
			
			lblOutput.setText("Fill out fields");
		}
	
	}
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	

}
