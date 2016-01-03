package View;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import Model.Connections;
import Model.ParseSQlite;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewFocusModel;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Databasemanager {

	private RadioButton movie;
	@FXML
	private RadioButton series;
	@FXML
	private Button save;
	@FXML
	private Button update;
	@FXML
	private Button delete;
	@FXML
	private TextField Idtext;
	@FXML
	private TextField Titletext;
	@FXML
	private TextField Runtimetext;
	@FXML
	private TextField Genretext;
	@FXML
	private TextField Actorstext;
	@FXML
	private TextField Directortext;
	@FXML
	private TextArea finaldata;
	
	String database = "";
	private PreparedStatement pstmt = null;
	private String Id="";
	private String title="";
	private String runtime="";
	private String genre="";
	private String actors="";
	private String director="";

	

	public void start() {
		Stage stage = new Stage();

		try {
			Parent FMXLoader = FXMLLoader.load(getClass().getResource("Databasemanager.fxml"));
			Scene scene = new Scene(FMXLoader);
			scene.getStylesheets().add(getClass().getResource("choosedatabase.css").toExternalForm());
			stage.setScene(scene);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void typemovie() {
		database = "movie";
		System.out.println(database + "*");
	}

	public void typeseries() {
		database = "series";
		System.out.println(database + "*");
	}


	public void updateTable() {
		if(!database.equals("")){
		ParseSQlite parse = new ParseSQlite();
		Connections conn = new Connections();
		System.out.println(database + "*");
		Id=Idtext.getText().toString();
		title=Titletext.getText().toString();
		runtime=Runtimetext.getText().toString();
		genre=Genretext.getText().toString();
		actors=Actorstext.getText().toString();
		director=Directortext.getText().toString();
		parse.updateRow(Id, title, runtime, genre, actors, director, conn.SQLiteConnect(), database);
		finaldata.setText(parse.printOnlineData());
		}else{
			JOptionPane.showMessageDialog(null,
					"You have to choose a database before.", "Error.",
					JOptionPane.WARNING_MESSAGE);
		}
	}

public void searchData(){

	ParseSQlite parse = new ParseSQlite();
	Connections conn = new Connections();
	if(!database.equals("")){
	try {
		System.out.println("Search data.");
		Id=Idtext.getText().toString();
		title=Titletext.getText().toString();
		runtime=Runtimetext.getText().toString();
		genre=Genretext.getText().toString();
		actors=Actorstext.getText().toString();
		director=Directortext.getText().toString();
		parse.readWrittenParameters(Id, title, runtime, genre, actors, director, conn.SQLiteConnect(), database);
		finaldata.setText(parse.printOnlineData());
	}
	 catch (IOException e) {
		e.printStackTrace();
	}}else{
		JOptionPane.showMessageDialog(null,
				"You have to choose a database.", "Error.",
				JOptionPane.WARNING_MESSAGE);
	}
	finaldata.setText(parse.printOnlineData());
}
	
	public void delete(){
		ParseSQlite parse = new ParseSQlite();
		Connections conn = new Connections();
		if(!database.equals("")){
		try {
			parse.deleteData(database, Id, conn.SQLiteConnect());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}}else{
			JOptionPane.showMessageDialog(null,
					"You have to choose a database first.", "Error.",
					JOptionPane.WARNING_MESSAGE);
		}
	}
	
	
	
}
