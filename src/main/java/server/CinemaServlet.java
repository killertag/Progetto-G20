package server;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.rythmengine.Rythm;

import pizza_now.Order;
import pizza_now.Orders;
import pizza_now.Pizzas;


public class CinemaServlet extends HttpServlet {
	
	//qui dobbiamo lavorare noi

	/*
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (req.getPathInfo().equals("/add")) {
			resp.getWriter().write(Rythm.render("add.rtm", Pizzas.all()));
		}
		else if (req.getPathInfo().equals("/edit")) {
			Order order = Orders.get(req.getParameter("id"));
			resp.getWriter().write(Rythm.render("edit.rtm", order, Pizzas.all()));
		}
		else {
			resp.getWriter().write(Rythm.render("list.rtm", Orders.all()));
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (req.getPathInfo().equals("/save")) {
			Orders.add(req.getParameter("pizza"), req.getParameter("fullname"), req.getParameter("address"));			
		} 
		else if (req.getPathInfo().equals("/update")) {
			Orders.update(req.getParameter("id"), req.getParameter("pizza"), req.getParameter("fullname"), req.getParameter("address"));			
		} 
		else {
			Orders.delete(req.getParameter("id"));
		}
		resp.sendRedirect("/");
	}
}*/
}


