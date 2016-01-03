package View;

import java.io.IOException;

import javax.swing.JOptionPane;

import Model.Connections;
import Model.ParseSQlite;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Readoffline {
	
	@FXML
	private TextField id;
	@FXML
	private TextArea textarea;
	@FXML
	private RadioButton movie;
	@FXML
	private RadioButton series;
	
	private String database = "";
	
	public void start() {
		Stage stage = new Stage();

		try {
			Parent FMXLoader = FXMLLoader.load(getClass().getResource("Readoffline.fxml"));
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
	
	public void read(){
		ParseSQlite parse = new ParseSQlite();
		Connections conn = new Connections();
		if(!database.equals("")){
		try {
			parse.readWrittenData(database, id.getText(), conn.SQLiteConnect());
			textarea.setText(parse.printOnlineData());
		} catch (IOException e) {
			e.printStackTrace();
		}}else{
			JOptionPane.showMessageDialog(null,
					"You have to select the database first.", "Error.",
					JOptionPane.WARNING_MESSAGE);
		}
	}
	
}
