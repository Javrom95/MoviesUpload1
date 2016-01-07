package Model;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import javax.annotation.processing.SupportedSourceVersion;
import javax.swing.JOptionPane;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import View.Databasemanager;

public class ParseSQlite {

	Connections conn = new Connections();
	private int rows;
	private StringBuilder sb = new StringBuilder();
	private PreparedStatement pstmt = null;
	private int coma = 0;

	public void getDataToSee(Document doc) {
		try {
			NodeList movieList = doc.getElementsByTagName("movie");
			Node p = movieList.item(0);
			if (p == null) {
				System.out.println("Nodo null.");
			}
			if (p.getNodeType() == Node.ELEMENT_NODE) {
				Element movie = (Element) p;

				sb.append("Info: ");
				sb.append("\n");
				sb.append("Title: ");
				sb.append(movie.getAttribute("title"));
				sb.append("\n");
				sb.append("Release Date: ");
				sb.append(movie.getAttribute("year"));
				sb.append("\n");
				sb.append("Runtime: ");
				sb.append(movie.getAttribute("runtime"));
				sb.append("\n");
				sb.append("Genre: ");
				sb.append(movie.getAttribute("genre"));
				sb.append("\n");
				sb.append("Synopsis: ");
				sb.append(movie.getAttribute("plot"));
				sb.append("\n");
				sb.append("Actors: ");
				sb.append(movie.getAttribute("actors"));
				sb.append("\n");
				sb.append("Director: ");
				sb.append(movie.getAttribute("director"));
				sb.append("\n");

			}

		} catch (NullPointerException e) {
			System.out.println("Error 404. File Not Found.");
		}

	}

	public String printOnlineData() {
		return sb.toString();
	}


	public void getDataToWrite(Document doc, String Id, String database, Connection connect) {

		try {
			NodeList movieList = doc.getElementsByTagName("movie");
			Node p = movieList.item(0);
			if (p != null) {
				if (p.getNodeType() == Node.ELEMENT_NODE) {
					Element movie = (Element) p;
					PreparedStatement state = connect.prepareStatement("INSERT INTO " + database
							+ " (Id, Title, Runtime, Genre, Year, Plot, Actors, Director) VALUES (?,?,?,?,?,?,?,?)");

					state.setString(1, Id);
					state.setString(2, movie.getAttribute("title"));
					state.setString(3, movie.getAttribute("runtime"));
					state.setString(4, movie.getAttribute("genre"));
					state.setString(5, movie.getAttribute("year"));
					state.setString(6, movie.getAttribute("plot"));
					state.setString(7, movie.getAttribute("actors"));
					state.setString(8, movie.getAttribute("director"));

					rows = state.executeUpdate();
					System.out.println(rows + " rows added.");

					state.close();
				}
			} else {
				JOptionPane.showMessageDialog(null, "There's no data no write.", "Error.", JOptionPane.WARNING_MESSAGE);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			// If the database name is incorrect, the method will launch again.
		}
	}


	public void readWrittenData(String database, String Id, Connection connect) throws IOException {
		int separ = 0;
		try {
			PreparedStatement stmt = connect.prepareStatement("Select * from " + database + " where Id='" + Id + "'");
			ResultSet rs = stmt.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					if (separ > 0) {
						sb.append("\n");
						sb.append("\n");
						sb.append("---------------------------------------------");
						sb.append("\n");
						sb.append("\n");
					}
					separ++;
					sb.append("Info: ");
					sb.append("\n");
					sb.append("Id: ");
					sb.append(rs.getString("Id"));
					sb.append("\n");
					sb.append("Title: ");
					sb.append(rs.getString("title"));
					sb.append("\n");
					sb.append("Release Date: ");
					sb.append(rs.getString("year"));
					sb.append("\n");
					sb.append("Runtime: ");
					sb.append(rs.getString("runtime"));
					sb.append("\n");
					sb.append("Genre: ");
					sb.append(rs.getString("genre"));
					sb.append("\n");
					sb.append("Synopsis: ");
					sb.append(rs.getString("plot"));
					sb.append("\n");
					sb.append("Actors: ");
					sb.append(rs.getString("actors"));
					sb.append("\n");
					sb.append("Director: ");
					sb.append(rs.getString("director"));
					sb.append("\n");

				}
				System.out.println(sb.toString());
			} else {
				JOptionPane.showMessageDialog(null, "There's no data with that Id.", "Error.",
						JOptionPane.WARNING_MESSAGE);
			}
			rs.close();
			stmt.close();
		} catch (Exception ex) {
			System.out.println("Please, write a correct database name.");
			ex.printStackTrace();
			// If the database�s name isn�t correct, the method will launch
			// again.
			// readWrittenData(conn.lookForType(), conn.lookForName(),
			// conn.SQLiteConnect());
		}
	}

	public void readWrittenParameters(String id, String title, String runtime, String genre, String actors,
			String director, Connection conn, String database) throws IOException {
		String select = "Select * from " + database;

		if (!id.equals("")) {
			if (coma == 0) {
				select += " WHERE ";
			}
			select += " Id= '" + id + "'";
			coma++;
		}
		if (!title.equals("")) {
			if (coma == 0) {
				select += " WHERE ";
			} else {
				select += " AND ";
			}
			select += " title= '" + title + "' ";
			coma++;
		}
		if (!runtime.equals("")) {
			if (coma == 0) {
				select += " WHERE ";
			} else {
				select += " AND ";
			}
			select += " runtime= '" + runtime + "' ";
			coma++;
		}
		if (!genre.equals("")) {
			if (coma == 0) {
				select += " WHERE ";
			} else {
				select += " AND ";
			}
			select += " genre= '" + genre + "' ";
			coma++;
		}
		if (!actors.equals("")) {
			if (coma == 0) {
				select += " WHERE ";
			} else {
				select += " AND ";
			}
			select += " actors= '" + actors + "' ";
			coma++;
		}
		if (!director.equals("")) {
			if (coma == 0) {
				select += " WHERE ";
			} else {
				select += " AND ";
			}
			select += " director= '" + genre + "' ";
			coma++;
		}
		System.out.println(select);
		try {
			PreparedStatement stmt = conn.prepareStatement(select);
			ResultSet rs = stmt.executeQuery();
			int separ = 0;
			if (rs != null) {
				while (rs.next()) {
					if (separ > 0) {
						sb.append("\n");
						sb.append("\n");
						sb.append("---------------------------------------------");
						sb.append("\n");
						sb.append("\n");
					}
					separ++;
					sb.append("Info: ");
					sb.append("\n");
					sb.append("Id: ");
					sb.append(rs.getString("Id"));
					sb.append("\n");
					sb.append("Title: ");
					sb.append(rs.getString("title"));
					sb.append("\n");
					sb.append("Release Date: ");
					sb.append(rs.getString("year"));
					sb.append("\n");
					sb.append("Lenght: ");
					sb.append(rs.getString("runtime"));
					sb.append("\n");
					sb.append("Genre: ");
					sb.append(rs.getString("genre"));
					sb.append("\n");
					sb.append("Synopsis: ");
					sb.append(rs.getString("plot"));
					sb.append("\n");
					sb.append("Actors: ");
					sb.append(rs.getString("actors"));
					sb.append("\n");
					sb.append("Director: ");
					sb.append(rs.getString("director"));
					sb.append("\n");

				}
				System.out.println(sb.toString());
			} else {
				System.out.println("Data not found.");
			}
			rs.close();
			stmt.close();
		} catch (Exception ex) {
			System.out.println("Please, write a correct database name.");
			ex.printStackTrace();
			// If the database�s name isn�t correct, the method will launch
			// again.
			// readWrittenData(conn.lookForType(), conn.lookForName(),
			// conn.SQLiteConnect());
		}
	}

	public void deleteData(String database, String Id, Connection connect) throws IOException {

		if(!Id.equals("")){
		try {
			String delete = "Delete  from " + database + " where Id = '" + Id + "'";
				PreparedStatement stmt = connect.prepareStatement(delete);
				rows = stmt.executeUpdate();
				System.out.println(rows + " rows deleted.");
				System.out.println(delete);
				stmt.close();	
		} catch (Exception ex) {
			System.out.println("Please, write a correct database name.");
			ex.printStackTrace();
		}
		}else{
			JOptionPane.showMessageDialog(null,
					"You have to write the Id of the register.", "Error.",
					JOptionPane.WARNING_MESSAGE);
		}
	}

	public void dropTables(String database1, String database2, Connection conn) {
		String create = "DROP TABLE IF EXISTS ";
		int rows = 0;
		byte count = 0;
		if (!database1.equals("")) {
			create += database1;
			count++;
		}
		if (!database2.equals("")) {
			if (count > 0) {
				create += ",";
			}
			create += database2;
		}
		System.out.println(create);
		try {
			PreparedStatement stmt = conn.prepareStatement(create);
			rows = stmt.executeUpdate();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deleteTables(String database1, String database2, Connection conn) {

		if (database1.equals("") && database2.equals("")) {
			JOptionPane.showMessageDialog(null, "You have to select at least 1 database first.", "Error.",
					JOptionPane.WARNING_MESSAGE);
		} else {
			String create = "DELETE FROM ";
			if (!database1.equals("")) {	
				create += database1;
				try {
					PreparedStatement stmt = conn.prepareStatement(create);
					rows = stmt.executeUpdate();
					stmt.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			create = "DELETE FROM ";
			if (!database2.equals("")) {

				create += database2;
				try {
					PreparedStatement stmt = conn.prepareStatement(create);
					rows = stmt.executeUpdate();
					stmt.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

	}

	public void updateRow(String id, String title, String runtime, String genre, String actors, String director,
			Connection conn, String database) {
		String update = "Update " + database + " SET ";
		if (!id.equals("")) {
			update += "Id= '" + id + "'";
			coma++;
		}
		if (!title.equals("")) {
			if (coma > 0) {
				update += ", ";
			}
			update += "title= '" + title + "' ";
			coma++;
		}
		if (!runtime.equals("")) {
			if (coma > 0) {
				update += ", ";
			}
			update += "runtime= '" + runtime + "' ";
			coma++;
		}
		if (!genre.equals("")) {
			if (coma > 0) {
				update += ", ";
			}
			update += "genre= '" + genre + "' ";
			coma++;
		}
		if (!actors.equals("")) {
			if (coma > 0) {
				update += ", ";
			}
			update += "actors= '" + actors + "' ";
			coma++;
		}
		if (!director.equals("")) {
			if (coma > 0) {
				update += ", ";
			}
			update += "director= '" + genre + "' ";
			coma++;
		}
		update += "WHERE Id ='" + id + "' ";
		System.out.println(update);
		try {
			//Databasemanager data = new Databasemanager();
			PreparedStatement stmt = conn.prepareStatement(update);
			rows = stmt.executeUpdate();
			System.out.println("Updated row.");
			readWrittenData(database, id, conn);
			stmt.close();
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "You have to instert the id of the register to update.", "Error.",
					JOptionPane.WARNING_MESSAGE);
		} catch (Exception e) {

		}
	}

	public String checkId(String database, String Id, Connection conn){
		String select="Select Id from "+database+" where Id='"+Id+"'";
		System.out.println(select);
		String Idcheck="";
		try {
			PreparedStatement stmt = conn.prepareStatement(select);
			ResultSet rs = stmt.executeQuery();
			Idcheck=rs.getString("Id");
			System.out.println(Idcheck);
			rs.close();
			stmt.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		return Idcheck;
	}
	
}
