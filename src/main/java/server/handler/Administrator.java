package server.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.rythmengine.Rythm;

import server.domain.cinema.Cinema;
import server.domain.cinema.TypeCategory;
import server.domain.exception.SearchException;

public class Administrator implements IHandler {
	private static Administrator instance = null;

	private Administrator() {
	}


	public static Administrator getInstance() {
		if (instance == null)
			instance = new Administrator();

		return instance;
	}


	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.getWriter().write(Rythm.render("administrator.html", TypeCategory.values(), Cinema.getCinema().getTitleMovieList()));
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String message = "";

		switch (req.getParameter("requestPost")) {

		case "addMovie":
			message = this.addMovie(req);
			break;

		case "getMovieInf":
			message = this.getMovieInf(req);
			break;

		case "removeMovie":
			message = this.removeMovie(req);
			break;

		case "editM":
			message = this.editMovie(req);
		}


		resp.getWriter().write(message);


	}


	/*
	 * Tutti i metodi di amministrazione, richiamati dal post
	 */


	private String editMovie(HttpServletRequest req) {
		String title = req.getParameter("title");
		String plot = req.getParameter("plot");
		String cover = "../statics/images/cover/" + req.getParameter("cover") + ".jpg";


		try {
			int duration = Integer.valueOf(req.getParameter("duration"));

			//Cinema.getCinema().searchMovie(title).setCategory(TypeCategory.valueOf(req.getParameter("category")));
			Cinema.getCinema().searchMovie(title).setDuration(duration);
			Cinema.getCinema().searchMovie(title).setPathCover(cover);
			Cinema.getCinema().searchMovie(title).setPlot(plot);
			Cinema.getCinema().searchMovie(title).setTitle(title);

		}
		catch (SearchException e1) {
			System.out.println(e1);
			return "Error: " + title + " already exists";
		}
		catch (Exception e1) {
			System.out.println(e1);
			return "Incorrect or missing data";
		}

		return title + " succefully added. Reload to see changes";
	}

	private String removeMovie(HttpServletRequest req) {

		String title = req.getParameter("title");

		try {
			Cinema.getCinema().deleteMovie(title);
		}
		catch (SearchException e) {
			return title + " not found. Reload to see changes";
		}

		return title + " succefully removed. Reload to see changes";
	}


	private String getMovieInf(HttpServletRequest req) {

		String title = req.getParameter("title");
		server.domain.cinema.Movie movie;

		try {
			movie = Cinema.getCinema().searchMovie(title);
		}
		catch (SearchException e) {
			return "Error@" + title + " not found. Reload to see changes";
		}

		String inf = movie.getTitle() + "@" + String.valueOf(movie.getDuration()) +
				"@" + movie.getPlot();

		return inf;
	}


	private String addMovie(HttpServletRequest req) {

		String title = req.getParameter("title");
		String plot = req.getParameter("plot");
		String cover = "../statics/images/cover/" + req.getParameter("cover") + ".jpg";


		try {
			int duration = Integer.valueOf(req.getParameter("duration"));

			Cinema.getCinema().createMovie(title, duration, plot, cover,
					TypeCategory.valueOf(req.getParameter("category")));
		}
		catch (SearchException e) {
			System.out.println(e);
			return "Error: " + title + " already exists";
		}
		catch (Exception e) {
			System.out.println(e);
			return "Incorrect or missing data";
		}

		return title + " succefully added. Reload to see changes";



	}

}
