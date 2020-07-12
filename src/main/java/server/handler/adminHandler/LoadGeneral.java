package server.handler.adminHandler;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.rythmengine.Rythm;

import server.domain.cinema.Cinema;
import server.domain.exception.SeatException;

public class LoadGeneral {
	
	public static String doAction(HttpServletRequest req) {
		
		Map<String,Integer> forMovie = new HashMap<>();
		Map<String,Integer> forTheatre = new HashMap<>();
		String messagge;
		
		//creo la prima mappa per i film (guardare adminGeneral.html)
		try {
			for (String movie: Cinema.getCinema().getMovieList()) {
				Integer value = Cinema.getCinema().getMovieShowingList(movie).size();
				forMovie.put(movie, value);
			}
			for (String theatre: Cinema.getCinema().getTheatreList()) {
				Integer value = 0; //qualcosa per vedere la lunghezza
				forTheatre.put(theatre, value);
			}
			messagge = Rythm.render("imported/adminGeneral.html",forMovie, forTheatre);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			messagge = "Error from server, sorry :(";
		}

		
		return messagge;

	}

}