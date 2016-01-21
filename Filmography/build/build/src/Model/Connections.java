package Model;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import javax.swing.JOptionPane;
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
			connDB = DriverManager.getConnection("jdbc:sqlite:Filmografía.sqlite");
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

	public void dropTables(String database1, String database2, Connection conn) {
		System.out.println(database1 +" "+database2+"*");
		String create = "DROP TABLE ";
		int rows = 0;
		if (!database1.equals("")) {
			create += database1;
			try {
				PreparedStatement stmt = conn.prepareStatement(create);
				rows = stmt.executeUpdate();
				create="DROP TABLE ";
				stmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if (!database2.equals("")) {
			create += database2;
			try {
				PreparedStatement stmt = conn.prepareStatement(create);
				rows = stmt.executeUpdate();
				create="DROP TABLE ";
				stmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		
		
	}

	public void createTables(String database1, String database2, Connection conn) {

		if(database1.equals("") && database2.equals("")){
			JOptionPane.showMessageDialog(null,
					"You have to select at least 1 database first.", "Error.",
					JOptionPane.WARNING_MESSAGE);
		}else{
		String create = " CREATE TABLE";
		int rows = 0;

		if (!database1.equals("")) {
			create += " " + database1;
			create += " ( Id VARCHAR(10) PRIMARY KEY, Title VARCHAR(50) NOT NULL, Runtime VARCHAR(20) NOT NULL, Genre VARCHAR(30) NOT NULL, Year VARCHAR(20), Plot VARCHAR(255) NOT NULL, Actors VARCHAR(255) NOT NULL, Director VARCHAR(50) NOT NULL)";
			System.out.println(create);
			try {
				PreparedStatement stmt = conn.prepareStatement(create);
				rows = stmt.executeUpdate();
				stmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
        create =" CREATE TABLE";
		}
		if (!database2.equals("")) {

			create += " " + database2;
			create += " ( Id VARCHAR(10) PRIMARY KEY, Title VARCHAR(50) NOT NULL, Runtime VARCHAR(20) NOT NULL, Genre VARCHAR(30) NOT NULL, Year VARCHAR(20), Plot VARCHAR(255) NOT NULL, Actors VARCHAR(255) NOT NULL, Director VARCHAR(50) NOT NULL )";
			System.out.println(create);
			try {
				PreparedStatement stmt = conn.prepareStatement(create);
				rows = stmt.executeUpdate();
				stmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			create =" CREATE TABLE";
		}
		}
	
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
