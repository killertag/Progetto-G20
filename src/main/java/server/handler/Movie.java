package server.handler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.rythmengine.Rythm;

public class Movie implements IHandler {
	
	private static Movie instance = null;
	
	private String selectedMovie;
	
	 private Movie() {
	 }
	  
	
	 public static Movie getInstance() {
		 
		 if (instance == null) 
			 instance = new Movie(); 
	  
		 return instance; 
	    } 

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		/* verr� salvato il titolo del fiml da cercare, da passarre 
		 * all'applicazione che lo cercher� nella base di dati, e ne ritorner�
		 * le informazioni, da renderizzare nell'html
		 */
		this.selectedMovie = req.getParameter("title");
		
		//metodi di caricamento della pagina del film
		
		
		Map<String, Object > params = new HashMap<>();
		this.getMovieInf(params);
		this.getAvaliableShow(params);
		
		
		resp.getWriter().write(Rythm.render("movieInformation.html", params));
		
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// non server probabilmente
		
	}
	
	private void getMovieInf(Map<String, Object> params) {
		
		//prendo valori dall'applicazione
		String imgUrl;
		String title;
		String description;
		String year;
		String duration;
		String genere;
		String price;
		
		//putto i valori nella mappa
		params.put("imgUrl", "prova");
		params.put("title", selectedMovie);
		params.put("description", "prova");
		params.put("year", "prova");
		params.put("duration", "prova");
		params.put("genere", "prova");
		params.put("price", "prova");
		
		return;
		
	}
	
	private void getAvaliableShow(Map<String, Object> params) {
		
		List<String> idShowList = new ArrayList<>();
		
		idShowList.add("show1");
		idShowList.add("ishow");
		idShowList.add("sfdadf");
		
		params.put("idShowList", idShowList);
		
		return;
		
		
	}
	
}


