package server.domain.cinema;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * This class identifies a ticket to show a movie.
 */
public class Ticket {
	private String code, movie, seat;
	private MovieShowing showing;
	private double totalPrice;

	public Ticket(String code, String movie, String seat, MovieShowing showing, double totalPrice) {
		this.code = code;
		this.movie=movie;
		this.showing=showing;
		this.seat=seat;
		this.totalPrice=totalPrice;
	}

	public String getMovie() {
		return movie;
	}

	public String getSeat() {
		return seat;
	}

	public LocalDateTime getDate() {
		return showing.getDate();
	}

	public String getDateToString() {
		return showing.getDate().format(DateTimeFormatter.ofPattern("dd MMM uuuu - HH:mm"));
	}

	public String getShowingId() {
		return showing.getId();
	}

	public MovieShowing getShowing() {
		return showing;
	}

	public String getTheatre() {
		return showing.getTheatreName();
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public String getCode() {
		return code;
	}

	@Override
	public String toString() {
		return "Code: " + code + "\nDate: " + showing.getDateToString() + "\nSeat: " + seat +"\nMovie: " + movie + "\nPrice: � "
				+ totalPrice;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((code == null) ? 0 : code.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ticket other = (Ticket) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		return true;
	}
}
