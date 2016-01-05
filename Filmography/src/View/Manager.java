package View;

import Model.Connections;
import Model.ParseSQlite;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Manager {

	@FXML
	private CheckBox movie1;
	@FXML
	private CheckBox series1;
	@FXML
	private CheckBox movie2;
	@FXML
	private CheckBox series2;
	
	private String database1="";
	private String database2="";

	
	
	
	
	
	
	
	
	public void checkCreate() {
		if(movie1.isSelected()){
			database1 = "movie";
		}else{
			database1 = "";
		}
		if(series1.isSelected()){
			database2 = "series";
		}else{
			database2 = "";
		}
	}

	public void checkDelete() {
		if(movie2.isSelected()){
			database1 = "movie";
			System.out.println(database1);
		}else{
			database1 = "";
		}
		if(series2.isSelected()){
			database2 = "series";
			System.out.println(database2);
		}else{
			database2 = "";
		}
	}

	public void start() {
		Stage stage = new Stage();

		try {
			Parent FMXLoader = FXMLLoader.load(getClass().getResource("Manager.fxml"));
			Scene scene = new Scene(FMXLoader);
			scene.getStylesheets().add(getClass().getResource("choosedatabase.css").toExternalForm());
			stage.setScene(scene);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void createTable() {
		checkCreate();
		Connections conn = new Connections();
		conn.dropTables(database1, database2, conn.SQLiteConnect());
		System.out.println("Dropped.");
		conn.createTables(database1, database2, conn.SQLiteConnect());
		System.out.println("Created.");
	}

	public void deleteTable() {
		checkDelete();
		Connections conn = new Connections();
		ParseSQlite parse = new ParseSQlite();
		parse.deleteTables(database1, database2, conn.SQLiteConnect());
		System.out.println("Deleted.");
	}

}
