package Model;

public class Visual {
	private String Id;
	private String title;
	private String date;
	private String lenght;
	private String genre;
	private String synopsis;
	private String actors;
	private String director;

	// The setter and getters that will get the data.
	public void setTitle(String title) {
		this.title = title;
		
	}

	public void setId(String Id) {
		this.Id = Id;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public void setLenght(String lenght) {
		this.lenght = lenght;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}

	public void setActors(String actors) {
		this.actors = actors;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	/**
	 * This method gets the values and add them to a string.
	 */
	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder();
		sb.append("Info: ");
		sb.append("\n");
		sb.append("Register Id: ");
		sb.append(Id);
		sb.append("\n");
		sb.append("Title: ");
		sb.append(title);
		sb.append("\n");
		sb.append("Release Date: ");
		sb.append(date);
		sb.append("\n");
		sb.append("Lenght: ");
		sb.append(lenght);
		sb.append("\n");
		sb.append("Genre: ");
		sb.append(genre);
		sb.append("\n");
		sb.append("Synopsis: ");
		sb.append(synopsis);
		sb.append("\n");
		sb.append("Actors: ");
		sb.append(actors);
		sb.append("\n");
		sb.append("Director: ");
		sb.append(director);
		sb.append("\n");

		return sb.toString();
	}

	public String toStringOnline() {

		StringBuilder sb = new StringBuilder();
		sb.append("Info: ");
		sb.append("\n");
		sb.append("Title: ");
		sb.append(title);
		sb.append("\n");
		sb.append("Release Date: ");
		sb.append(date);
		sb.append("\n");
		sb.append("Lenght: ");
		sb.append(lenght);
		sb.append("\n");
		sb.append("Genre: ");
		sb.append(genre);
		sb.append("\n");
		sb.append("Synopsis: ");
		sb.append(synopsis);
		sb.append("\n");
		sb.append("Actors: ");
		sb.append(actors);
		sb.append("\n");
		sb.append("Director: ");
		sb.append(director);
		sb.append("\n");

		return sb.toString();
	}

}
