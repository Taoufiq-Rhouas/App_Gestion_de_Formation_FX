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
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class SampleController implements Initializable  {
	
	

	
	
	
	//////////////
	
	@FXML
	private Label ID;
	
	@FXML
	private Label Libelle;
	
	@FXML
	private Label Description;
	
	@FXML
	private TextField tflid;
	
	@FXML
	private TextField tfllibelle;
	
	@FXML
	private TextField tfldescription;
	
	@FXML
	private Button btnInsert;
	
	@FXML
	private Button btnUpdate;
	
	@FXML
	private Button btnDelete;
	
	@FXML
	private TableView<Formation> tvFormation;
	
	@FXML
	private TableColumn<Formation, Integer> colid;
	
	@FXML
	private TableColumn<Formation, String> collibelle;
	
	@FXML
	private TableColumn<Formation, String> coldescription;
	
	@FXML
	private TableColumn<Formation, Integer> colstatus;
	
	
	
	
	
	@FXML
	private void handeleButtonAction(ActionEvent event) {
		if(event.getSource() == btnInsert) {
			System.out.println("insertData()");
			insertRecord();
			//getConnection();
		}
		else if(event.getSource() == btnUpdate) {
			System.out.println("updateData()");
			updateRecodr();
		}
		else if(event.getSource() == btnDelete) {
			System.out.println("deleteData()");
			deleteButton();
		}
	}
	
	
	

	
	public Connection getConnection() {
		Connection conn;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/gestion_formation_javafx", "root" ,"");
			System.out.println("Connected");
			return conn;
		}catch (Exception ex) {
			System.out.println("Error : " + ex.getMessage());
			return null;
		}
	}
	
	public ObservableList<Formation> getFormationList(){
		
		ObservableList<Formation> formationlist = FXCollections.observableArrayList();
		Connection conn = getConnection();
		String query = "select * from formation";
		Statement st;
		ResultSet rs;
		try {
			st = conn.createStatement();
			rs = st.executeQuery(query);
			Formation  formations;
			while(rs.next()) {
				formations = new Formation(rs.getInt("id") , rs.getString("libelle") , rs.getString("description") , rs.getInt("statut"));
				formationlist.add(formations);
			}
			
			
		}catch (Exception ex) {
			ex.printStackTrace();
		}
		return formationlist;
	}
	
	
	public void showFormation() {
		ObservableList<Formation> List = getFormationList();
		colid.setCellValueFactory(new PropertyValueFactory<Formation , Integer>("id"));
		collibelle.setCellValueFactory(new PropertyValueFactory<Formation , String>("libelle"));
		coldescription.setCellValueFactory(new PropertyValueFactory<Formation , String>("description"));
		colstatus.setCellValueFactory(new PropertyValueFactory<Formation , Integer>("statut"));
		
		tvFormation.setItems(List);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		showFormation();
		
	}
	
	private void insertRecord() {
		String query = "INSERT INTO `formation`(`libelle`, `description`, `statut`) VALUES ('" + tfllibelle.getText() + "','" + tfldescription.getText() + "'," +1+ ")";
		executeQuery(query);
		showFormation();
	}
	
	private void updateRecodr() {
		String query = "UPDATE `formation` SET libelle = '"+ tfllibelle.getText() +"' , description = '"+ tfldescription.getText() +"'  where id =" + tflid.getText() +"";
		executeQuery(query);
		showFormation();
		
		
	}
	
	private void deleteButton() {
		String query = "DELETE FROM `formation` WHERE id =" + tflid.getText() +" ";
		executeQuery(query);
		showFormation();
		
		tflid.setText("");
		tfllibelle.setText("");
		tfldescription.setText("");
	}





	private void executeQuery(String query) {
		// TODO Auto-generated method stub
		Connection conn = getConnection();
		Statement st;
		try {
			st = conn.createStatement();
			st.executeUpdate(query);
		}catch (Exception ex){
			ex.printStackTrace();
		}
		
	}
	
	
	@FXML
    private void handeleMousseAction(MouseEvent event) {
		Formation formations = tvFormation.getSelectionModel().getSelectedItem();
        //System.out.println("id: " + employee.getId());
        //System.out.println("salary: " + employee.getSalary());
        
		tflid.setText(""+formations.getId());
		tfllibelle.setText(formations.getLibelle());
		tfldescription.setText(formations.getDescription());
    }
	
	
	
	
	
	
}
