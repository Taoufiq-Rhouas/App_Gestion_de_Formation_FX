package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class EmployeeController implements Initializable {
	
	ObservableList<String> ChoiceBoxList = FXCollections.observableArrayList("safi", "agadir", "tanger", "casablanca");
	
	@FXML
	private Label message;
	
	@FXML
	private TextField inputID;
	
	@FXML
	private TextField inputLibelle;
	
	@FXML
	private TextField inputVille;
	
	@FXML
	private TextField inputFname;
	
	@FXML
	private TextField inputLname;

	@FXML
	private Button btnInsert;
	
	@FXML
	private ChoiceBox<String> localisation;
	
	@FXML
	private TableView<Formation> tvBox;
	
	@FXML
	private TableColumn<Formation, Integer> colID;
	
	@FXML
	private TableColumn<Formation, String> colLibelle;
	
	@FXML
	private TableColumn<Formation, String> colDescription;
	
	@FXML
	private TableColumn<Formation, String> colVille;
	
	@FXML
	private WebView maps;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		showFormation();
		
		WebEngine web = maps.getEngine();
		web.load("https://yassinaibi.github.io/gestion-de-formation-map/index.html?X=32.29740462539648&Y=-9.233969451787024");
	}
	
	@FXML
	private void handeleButtonAction(ActionEvent event) {
		if(event.getSource() == btnInsert) {
			insertData();
		}
	}
	
	public Connection getConnection() {
		Connection conn;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/javafx2", "root", "");
			//System.out.println("connected");
			return conn;
		}
		catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
			return null;
		}
	}
	
		public ObservableList<Formation> getFormationList() {
			ObservableList<Formation> formationList = FXCollections.observableArrayList();
			Connection conn = getConnection();
			String query = "SELECT * FROM  formation";
			Statement st;
			ResultSet rs;
			
			try {
				st = conn.createStatement();
				rs = st.executeQuery(query);
				
				Formation formation;
				while(rs.next()) {
					formation = new Formation(rs.getInt("id"), rs.getString("libelle"), rs.getString("description"), rs.getString("ville"));
					formationList.add(formation);
				}
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			return formationList;
		}

		public void showFormation() {
			
			try {
				ObservableList<Formation> list = getFormationList();
				
				colID.setCellValueFactory(new PropertyValueFactory<Formation, Integer>("id"));
				colLibelle.setCellValueFactory(new PropertyValueFactory<Formation, String>("libelle"));
				colDescription.setCellValueFactory(new PropertyValueFactory<Formation, String>("description"));
				colVille.setCellValueFactory(new PropertyValueFactory<Formation, String>("ville"));

				tvBox.setItems(list);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				System.out.println(e);
			}
		}
		
		private void insertData() {
			String query = "INSERT INTO `reservation`(`libelle`, `ville`, `fName`, `lName`, `formationID`) VALUES ('"+inputLibelle.getText()+"','"+inputVille.getText()+"','"+inputFname.getText()+"','"+inputLname.getText()+"',"+inputID.getText()+")";
			message.setText("inserted");
			
			executeQuery(query);
			showFormation();
			
			inputID.setText("");
			inputLibelle.setText("");
			inputVille.setText("");
			inputFname.setText("");
			inputLname.setText("");
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
		
		
		@FXML
		private void handeleMousseAction(MouseEvent event) {
			Formation fomation = tvBox.getSelectionModel().getSelectedItem();
			//System.out.println("id: " + employee.getId());
			//System.out.println("salary: " + employee.getSalary());
			
			inputID.setText(""+fomation.getId());
			inputLibelle.setText(fomation.getLibelle());
			inputVille.setText(fomation.getVille());
			
			if(fomation.getVille().equals("safi")) {
				WebEngine web = maps.getEngine();
				web.load("https://yassinaibi.github.io/gestion-de-formation-map/index.html?X=32.29740462539648&Y=-9.233969451787024");
			}
			else if(fomation.getVille().equals("agadir")) {
				WebEngine web = maps.getEngine();
				web.load("https://yassinaibi.github.io/gestion-de-formation-map/index.html?X=30.424250302361035&Y=-9.59191950518671");
			}
			else if(fomation.getVille().equals("tanger")) {
				WebEngine web = maps.getEngine();
				web.load("https://yassinaibi.github.io/gestion-de-formation-map/index.html?X=35.774346561838954&Y=-5.786970071038289");
			}
			else if(fomation.getVille().equals("casablanca")) {
				WebEngine web = maps.getEngine();
				web.load("https://yassinaibi.github.io/gestion-de-formation-map/index.html?X=33.56423913535208&Y=-7.66089451123138");
			}
			
			//WebEngine web = maps.getEngine();
			//web.load("https://yassinaibi.github.io/gestion-de-formation-map/index.html?X=33.29740462539648&Y=-10.233969451787024");
			//colVille.setText(fomation.getVille());
		}
}
