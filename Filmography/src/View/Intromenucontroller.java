package View;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import Model.*;

public class Intromenucontroller extends Application {
	Readonline read = new Readonline();
	Readoffline off = new Readoffline();
	Databasemanager data = new Databasemanager();
	Manager mana = new Manager();
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent FMXLoader = FXMLLoader.load(getClass().getResource("Choosedatabase.fxml"));
			Scene scene = new Scene(FMXLoader);
			scene.getStylesheets().add(getClass().getResource("choosedatabase.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void readOnline(){
		read.start();
	}
	
	
	public void lookFor(){
		off.start();
	}
	
	public void tableMenu(){
		data.start();
	}
	
	public void dataConfg(){
		mana.start();
	}
	
	
	public static void main (String [] args){
		Connections conn = new Connections();
		conn.SQLiteConnect();
		launch(args);
		
		
	}
	
	
	
}
