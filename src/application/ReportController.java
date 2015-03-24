package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class ReportController implements Initializable {
	
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
	@FXML
	private Button btnReport;
	@FXML
	private TextArea txtReport;
	@FXML
	private ComboBox<String> cmbReport;
	@FXML
	private void runReport(ActionEvent event) throws IOException{
		
		//if user hasn't selected a report type
		if(cmbReport.getValue() == null){
			txtReport.clear();
			txtReport.setText("Please select a report type");
		}
		
		//print out all accounts in databse
		else if(cmbReport.getValue().equals("Accounts")){
			txtReport.clear();
			try{
				// Get connection to database
				Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library_db", "root", "password");
				// create a statement
				Statement myStmt = myConn.createStatement();
				// execute sql query
				ResultSet accounts = myStmt.executeQuery("SELECT * FROM account");
				ResultSetMetaData rmsd = accounts.getMetaData();
				int numCol = rmsd.getColumnCount();
				while(accounts.next()){
					for(int i = 1; i < numCol; i++){
						String columnValue = accounts.getString(i);
						String value = String.format("%-20s", columnValue + "     ");
						txtReport.appendText(value);
						
					}
					txtReport.appendText("\n");
				
				}
							
			}
			catch(Exception exc){
				exc.printStackTrace();
			}
			
			}
			
		//print out all books in database
		else if(cmbReport.getValue().equals("Books")){
			txtReport.clear();
			
			try{
				// Get connection to database
				Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library_db", "root", "password");
				// create a statement
				Statement myStmt = myConn.createStatement();
				// execute sql query
				ResultSet books = myStmt.executeQuery("SELECT * FROM book");
				ResultSetMetaData rmsd = books.getMetaData();
				int numCol = rmsd.getColumnCount();
				while(books.next()){
					for(int i = 1; i < numCol; i++){
						String columnValue = books.getString(i);
						String value = String.format("%-20s", columnValue + "     ");
						txtReport.appendText(value);
						
					}
					txtReport.appendText("\n");
				
				}
							
			}
			catch(Exception exc){
				exc.printStackTrace();
			}
			
		}
		//print out all checked out books
		else if(cmbReport.getValue().equals("Checked Out")){
			txtReport.clear();
			
			try{
				// Get connection to database
				Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library_db", "root", "password");
				// create a statement
				Statement myStmt = myConn.createStatement();
				// execute sql query
				ResultSet books = myStmt.executeQuery("SELECT * FROM book WHERE checked_out = '1' ");
				ResultSetMetaData rmsd = books.getMetaData();
				int numCol = rmsd.getColumnCount();
				while(books.next()){
					for(int i = 1; i < numCol; i++){
						String columnValue = books.getString(i);
						String value = String.format("%-20s", columnValue + "     ");
						txtReport.appendText(value);
						
					}
					txtReport.appendText("\n");
				
				}
							
			}
			catch(Exception exc){
				exc.printStackTrace();
			}
			
		}
		else if(cmbReport.getValue().equals("Past Due")){
			txtReport.clear();
			
			try{
				// Get connection to database
				Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library_db", "root", "password");
				// create a statement
				Statement myStmt = myConn.createStatement();
				// execute sql query
				ResultSet books = myStmt.executeQuery("SELECT * FROM book WHERE checked_out = '1' AND date_out <= DATE_SUB(NOW(), INTERVAL 30 DAY)");
				ResultSetMetaData rmsd = books.getMetaData();
				int numCol = rmsd.getColumnCount();
				while(books.next()){
					for(int i = 1; i < numCol; i++){
						String columnValue = books.getString(i);
						String value = String.format("%-20s", columnValue + "     ");
						txtReport.appendText(value);
						
					}
					txtReport.appendText("\n");
				
				}
							
			}
			catch(Exception exc){
				exc.printStackTrace();
			}
			
			
			
		}
		
		
		
	}
	
	
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}
