package server.domain.cinema;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import server.domain.cinema.theatre.Theatre;
import server.domain.exception.SeatException;

/**
 * This class is referred to a movie projection in the cinema.
 */

public class MovieShowing {
	private String id;
	private LocalDateTime date;
	private double price;
	private Theatre theatre;
	private String movie;


	public MovieShowing(String id, String movie, LocalDateTime date, Theatre theatre, double price) {
		this.id=id;
		this.date=date;
		this.price=price;
		this.movie=movie;
		this.theatre = theatre;
	}

	public void editShowing(Theatre theatre, double price) {
		this.theatre=theatre;
		this.price=price;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public String getDateToString() {
		return date.format(DateTimeFormatter.ofPattern("dd MMM uuuu HH:mm"));
	}

	public String getMovie() {
		return movie;
	}

	public String getTheatreName() {
		return theatre.getTheatreName();
	}

	public Theatre getTheatre() {
		return theatre;
	}

	public double getPrice() {
		return price;
	}

	public String getId() {
		return id;
	}
	
	@Override
	public String toString() {
		return  getDateToString() + ", theatre: " + getTheatreName();
	}
}