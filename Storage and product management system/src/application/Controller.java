package application;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;


public class Controller{
	
	@FXML
    private TextField Valinta;
	
    @FXML
    private TextArea textarea;
	
    @FXML
    private TextField hinta;

    @FXML
    private Button lisaa;

    @FXML
    private TextField lkm;

    @FXML
    private Button muokkaa;

    @FXML
    private Button poista;
    
    @FXML
    private ListView<String> listview;
    

    @FXML
    private TextField tuote;

    @FXML
    private Font x1;

    @FXML
    private Color x2;

    @FXML
    private Font x3;

    @FXML
    private Color x4;
    
    
    
    public Controller() {  }
    
    public void initialize() {
    	
    	updatelist();
    	
    	}
    
    
    public void updatelist( ) {
    	Database dbConn = new Database();
    	Connection cnxn = dbConn.getCnxn();
    	
    	String query = "SELECT ID, tuote, lukumaara, hinta FROM varasto";

        // send and execute SQL query in Database software
    	try{
    	Statement st = cnxn.createStatement();
        ResultSet rs = st.executeQuery(query);
    	
    	
    	List<String> results = new ArrayList<>();

        while(rs.next())
         {
           results.add(rs.getString(1)+"          "+(rs.getString(2)) + "          Varastossa: "+ (rs.getString(3))+"          " +(rs.getString(4))+"e"  );
         }

        ObservableList<String> list = FXCollections.observableArrayList(results);
        listview.setItems(list);
        
    	}catch (SQLException e) {
        	// TODO Auto-generated catch block

        	e.printStackTrace();
        	}}
    	
    

    	
    	
    

    @FXML
    void lisaaTuote(ActionEvent event) {
    	
    	Database dbConn = new Database();
    	Connection cnxn = dbConn.getCnxn();

    	// create JDBC statement object
    	Statement st;
    	try{
    		Integer.parseInt(lkm.getText());
    		Double.parseDouble(hinta.getText());
    		st = cnxn.createStatement();

    	// prepare SQL query
    	String updt = "INSERT INTO varasto (tuote, lukumaara, hinta) VALUES ('" + tuote.getText() + "','" + lkm.getText()
        + "','" + hinta.getText() + "')";
    	
    	
    	textarea.setText("");
    	// send and execute SQL query in Database software
    	st.executeUpdate(updt);
    	
    	updatelist();
    	}catch (SQLException e) {
        	// TODO Auto-generated catch block

        	e.printStackTrace();
        	}
    	catch (NumberFormatException e) {
    		textarea.setText("Lukum‰‰r‰ tai hinta ei ole numero!");
    	}
    		}

    	

    

    @FXML
    void muokkaaTuote(ActionEvent event) {
    	
    	Database dbConn = new Database();
    	Connection cnxn = dbConn.getCnxn();
    	
    	PreparedStatement ps;
    	
    	try{
    		Integer.parseInt(lkm.getText());
    		Double.parseDouble(hinta.getText());

        	
        	String query = "update varasto set tuote=? where Id=? ";
        	ps = cnxn.prepareStatement(query);
        	ps.setString(1, tuote.getText());
        	ps.setString(2, Valinta.getText());
        	ps.executeUpdate();
        	
        	String query2 = "update varasto set lukumaara=? where Id=? ";
        	ps = cnxn.prepareStatement(query2);
        	ps.setString(1, lkm.getText());
        	ps.setString(2, Valinta.getText());
        	ps.executeUpdate();
        	
        	String query3 = "update varasto set hinta=? where Id=? ";
        	ps = cnxn.prepareStatement(query3);
        	ps.setString(1, hinta.getText());
        	ps.setString(2, Valinta.getText());
        	ps.executeUpdate();


        	
        	textarea.setText("");
        	updatelist();
        	}catch (SQLException e) {
            	// TODO Auto-generated catch block

            	e.printStackTrace();
            	}
    	catch (NumberFormatException e) {
    		textarea.setText("Lukum‰‰r‰ tai hinta ei ole numero!");
    	}}
    	

    

    @FXML
    void poistaTuote(ActionEvent event) {
    	Database dbConn = new Database();
    	Connection cnxn = dbConn.getCnxn();
    	
    	
    	Statement st;
    	try{
        	st = cnxn.createStatement();
        	
        	String varaston_valinta = Valinta.getText();

        	// prepare SQL query
        	String updt = "DELETE FROM varasto WHERE id="+varaston_valinta+";";
        	

        	// send and execute SQL query in Database software
        	st.executeUpdate(updt);
        	
        	//String query = "SELECT MAX(ID) FROM varasto";
        	//ResultSet rs = st.executeQuery(query);
        	//rs.next();
        	//int myMaxId = rs.getInt(1);
        	//String updt2 = "ALTER TABLE varasto AUTO_INCREMENT ="+myMaxId+";";                           //t‰‰ oli joku mun yritys resetoida auto_increment mut ei se toiminu.
        	//st.executeUpdate(updt2);
        	
        	updatelist();
        	}catch (SQLException e) {
            	// TODO Auto-generated catch block

            	e.printStackTrace();
            	}}

    }
  

    	

    


