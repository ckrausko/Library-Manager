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

public class AddBookController implements Initializable{
	
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
	// add a book
	@FXML
	private Button btnAddBook;
	@FXML
	private TextField txtFirstName;
	@FXML
	private TextField txtLastName;
	@FXML
	private TextField txtTitle;
	@FXML
	private TextField txtISBN;
	@FXML
	private TextField txtGenre;
	@FXML
	private Label lblBookID;
	
	@FXML
	private void addBook(ActionEvent event) throws IOException{
		
		if(txtFirstName.getText().trim().isEmpty() == false  && txtLastName.getText().trim().isEmpty() == false && txtISBN.getText().trim().isEmpty() == false &&
				txtGenre.getText().trim().isEmpty() == false){
			
		
			try{
				// Get connection to database
				Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library_db", "root", "password");
				// create a statement
				Statement myStmt = myConn.createStatement();
				// execute sql query
				
				String sql = " insert into book " 
						+ " (first_name, last_name, title, isbn,  genre) "
						+ " values ( " + "'" + txtFirstName.getText()+ "'" + "," + "'" + txtLastName.getText()+ "'" + "," + "'" + txtTitle.getText() + "'" + ","
						+ "'" + txtISBN.getText() + "'" + "," + "'" + txtGenre.getText() + "'"  + " )";
						
				myStmt.executeUpdate(sql);
				
				
				ResultSet bookID = myStmt.executeQuery("SELECT book_id FROM book"
						+ " ORDER BY book_id DESC"
						+ " LIMIT 1");
				bookID.next();
				lblBookID.setText( "The Book ID # is : " + (bookID.getString(("book_id"))));
			}
			catch(Exception exc){
				exc.printStackTrace();
			}
		
		}
		else{
			
			lblBookID.setText ("All fields must be entered");
		}
		
		
	}
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	

}
