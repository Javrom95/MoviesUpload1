package View;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.JOptionPane;

import Model.*;

public class Readonline {

	@FXML
	private TextField name;
	@FXML
	private TextArea textarea;
	@FXML
	private RadioButton movie;
	@FXML
	private RadioButton series;
	@FXML
	private Button save1;
	@FXML
	private TextField Id;
	
	private String nomb="";
	private String database = "";
	

	public void start() {
		Stage stage = new Stage();

		try {
			Parent FMXLoader = FXMLLoader.load(getClass().getResource("Readonline.fxml"));
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
		System.out.println(database);
	}

	public void typeseries() {
		database = "series";
		System.out.println(database);
	}

	public void read() {
		ParseSQlite parse = new ParseSQlite();
		Connections conn = new Connections();
		nomb=name.getText();
		parse.getDataToSee(conn.transformXML(nomb, database));
		textarea.setText(parse.printOnlineData());
		
	}
	
	public void Write(){
		ParseSQlite parse = new ParseSQlite();
		Connections conn = new Connections();
		if(!Id.getText().toString().equals("")){
		if(!database.equals("")){
			if(!parse.checkId(database, Id.getText(), conn.SQLiteConnect()).equals(Id.getText())){
				System.out.println(parse.checkId(database, Id.getText(), conn.SQLiteConnect()));
				parse.getDataToWrite(conn.transformXML(name.getText(), database),Id.getText().toString(), database, conn.SQLiteConnect());
			}else{
				JOptionPane.showMessageDialog(null,
						"You can't put an Id in a database twice.", "Error.",
						JOptionPane.WARNING_MESSAGE);
			}
		}else{
			JOptionPane.showMessageDialog(null,
					"You have to choose a database first.", "Error.",
					JOptionPane.WARNING_MESSAGE);
		}	
		}else{
			JOptionPane.showMessageDialog(null,
					"You have to write the Id of the new register.", "Error.",
					JOptionPane.WARNING_MESSAGE);
		}		
	}
}
