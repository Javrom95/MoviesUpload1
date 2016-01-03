package Model;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
public class Connections {

	private String finalURL, name, type;
	private URLConnection conn;
	private Document doc = null;
	private Document docu = null;
	private Connection connDB = null;
		
	
	public String formURL(String name, String type) {
		name = name.replace(' ', '+');
		finalURL = "http://www.omdbapi.com/?t=" + name + "&y=&plot=full&type=" + type + "&r=xml";
		return finalURL;
	}
	
	// get database connection.

	public Connection SQLiteConnect() {
		try {
			Class.forName("org.sqlite.JDBC");
			connDB = DriverManager
					.getConnection("jdbc:sqlite:Filmografía.sqlite");
			System.out.println("Successful connection.");
			return connDB;
		} catch (Exception e) {
			System.out.println("Error in database connection.");
			System.exit(0);
			return null;
		}
	}
		private InputStream getXMLInputStream(String urlString) throws IOException {
			URL url = null;
			InputStream stream = null;

			try {
				url = new URL(urlString);

				HttpURLConnection connection = (HttpURLConnection) url.openConnection();
				connection.connect();

				stream = connection.getInputStream();

			} catch (MalformedURLException e) {

			}
			return stream;
		}

		public Document transformXML(String name, String database) {
			try {
				DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
				DocumentBuilder builder = factory.newDocumentBuilder();
				doc = (Document) builder.parse(getXMLInputStream(formURL(name, database)));
			} catch (IOException e) {
				System.out.println("Couldn�t find what you were looking for:");
			} catch (Exception e) {
				System.out.println("Error in transformX.M.L.");
			}
			docu = doc;
			return doc;
		}

		public Document getDocu() {
			return docu;
		}
		
		
	
	
}
