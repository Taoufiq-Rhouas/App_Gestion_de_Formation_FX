package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class SampleController implements Initializable {
	
	@FXML
	private Button btnAdmin;
	
	@FXML
	private Button btnEmployee;
	
	@FXML
	private Button logIn;
	
	@FXML
	private TextField user;
	
	@FXML
	private TextField pass;
	
	private String username = "admine";
	private String password = "admine";
	
	String checkUser;
	String checkPassword;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}
	
	@FXML
	private void handeleButtonAction(ActionEvent event) throws SQLException {
		if(event.getSource() == btnAdmin) {
			GoToAdmine();
		}
		else if(event.getSource() == btnEmployee) {
			GoToEmployee();
		}
		else if(event.getSource() == logIn) {
			LogIn();
		}
	}

	public Connection getConnection() {
		Connection conn;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/javafx2", "root", "");
			System.out.println("connected");
			return conn;
		}
		catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
			return null;
		}
	}
	
	public void GoToAdmine() {
		Stage primaryStage = new Stage();
		try {
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("AdminLogIn.fxml"));
			Scene scene = new Scene(root,800,800);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void LogIn() throws SQLException {
		checkUser = user.getText().toString();
		checkPassword = pass.getText().toString();
		
		if(checkUser.equals(username) && checkPassword.equals(password)) {
			Stage primaryStage = new Stage();
			try {
				BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("MyFormation.fxml"));
				Scene scene = new Scene(root,1600,800);
				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primaryStage.setScene(scene);
				primaryStage.show();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		else {
			System.out.println("ERROR");
			Alert a = new Alert(AlertType.NONE); 
            // set alert type 
            a.setAlertType(AlertType.ERROR);
            a.setContentText("incorrect username or password");
            // show the dialog 
            a.show();
            
    		user.setText("");
    		pass.setText("");
		}		
	}

	public void GoToEmployee() {
		Stage primaryStage = new Stage();
		try {
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("Employee.fxml"));
			Scene scene = new Scene(root,1600,800);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
		
		
		/*
		private void insertData() {
			
			String query = "INSERT INTO `formation`(`libelle`, `description`) VALUES ('"+inputLibelle.getText()+"','"+inputDescription.getText()+"')";
			message.setText("inserted");
			
			executeQuery(query);
			showEmployee();
		}

		private void updateData() {
			
			//empCalculatedSalary = Integer.parseInt(inputSalary.getText()) + calculerSalaire();
			
			String query = "UPDATE `formation` SET `libelle`='"+inputLibelle.getText()+"',`description`='"+inputDescription.getText()+"' WHERE id ="+inputID.getText()+"";
			message.setText("updated");
			
			executeQuery(query);
			showEmployee();
		}
		
		private void deleteData() {
			String query = "DELETE FROM `formation` WHERE id ="+inputID.getText()+"";
			message.setText("deleted");

			executeQuery(query);
			showEmployee();
		}

		private void executeQuery(String query) {
			// TODO Auto-generated method stub
			Connection conn = getConnection();
			Statement st;
			try {
				st = conn.createStatement();
				st.executeUpdate(query);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		*/
}
