package server.handler.adminHandler;

import javax.servlet.http.HttpServletRequest;

import org.rythmengine.Rythm;

import server.domain.cinema.Cinema;

public class LoadShowStat {
	
	public static String doAction(HttpServletRequest req) {
		
		String title = req.getParameter("title");
		String messagge;
		
		try {
			messagge = Rythm.render("imported/showTableAdmin.html", 
					Cinema.getCinema().getMovieShowingList(title), title);
		} catch (Exception e) {
			e.toString();
			messagge = "Error with server";
		}
		
		return messagge;
	}

}
