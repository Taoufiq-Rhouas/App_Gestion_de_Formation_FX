package application;


import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class SampleController implements Initializable {
	
	@FXML
	Button btnAdmin;
	
	@FXML
	Button btnEmployee;
	
	@FXML
	Button logIn;
	
	@FXML
	private WebView maps;
	
	private String username = "yassine";
	private String password = "yassine";

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		//WebEngine web = maps.getEngine();
		//web.load(getClass().getResource("index.html").toString());
		
		WebEngine web = maps.getEngine();
		web.load("https://yassinaibi.github.io/gestion-de-formation-map/index.html?X=32.29740462539648&Y=-9.233969451787024");
	}
	
	@FXML
	private void handeleButtonAction(ActionEvent event) {
		if(event.getSource() == btnAdmin) {
			GoToAdmine();
		}
		else if(event.getSource() == btnEmployee) {
			GoToEmployee();
		}
		else if(event.getSource() == logIn) {
			
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
        Connection conn = getConnection();
//      String checkQuery = "select * from registeredUser where user = ' "+uname+" ' and pass = ' "+pass+" ' ";
      String checkQuery = "select * from admine where user = 'yassine' and password = 'yassine' "; // i don't use id from database table.
      
      PreparedStatement preparedStatement = null;
      boolean status = false; //initially false

          preparedStatement = conn.prepareStatement(checkQuery);
          preparedStatement.setString(1, this.username); // dynamically sending username
          preparedStatement.setString(2, this.password); // sending password to checkquery statement
          ResultSet resultSet = preparedStatement.executeQuery();

          /* while (resultSet.next()) {
              return status;
          } */

          status = resultSet.next();
          preparedStatement.close();
          
          System.out.println("OK");
}
	
	public void GoToEmployee() {
		System.out.println("thisIsEmployee");
	}
}
