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

public class CheckInController implements Initializable{
	
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
	// check in book
	@FXML
	private Button btnCheckIn;
	@FXML
	private Label lblOutput;
	@FXML
	private TextField txtBookID;
	@FXML
	private void checkInBook(ActionEvent event) throws IOException{
		
		if(txtBookID.getText().trim().isEmpty() == false){
			
		

		try{
			// Get connection to database
			Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library_db", "root", "password");
			// create a statement
			Statement myStmt = myConn.createStatement();
			// execute sql query
			
			String sql = "UPDATE book "
					+ " SET checked_out = '0' "
					+ " WHERE book_id = " + "'" + txtBookID.getText().trim() + "'";
					
			myStmt.executeUpdate(sql);
			lblOutput.setText("Book check back in");
			
			
		}
		catch(Exception exc){
			exc.printStackTrace();
		}
		
		}
		else{
			
			lblOutput.setText("Please enter a book ID#");
			
		}
		
	}
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	

}
