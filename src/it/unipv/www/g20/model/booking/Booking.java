package it.unipv.www.g20.model.booking;

import java.util.ArrayList;
import java.util.Calendar;

import it.unipv.www.g20.model.exception.NotFoundException;

public class Booking implements Bookable {
	private static int generateIdTicket = 0;
	private String idBooking;
	private ArrayList<Ticket> ticketList;
	private Calendar date;

	public Booking(Calendar date) {
		this.date = date;
		ticketList = new ArrayList<>();
	}

	@Override
	public Ticket createTicket(String id, String info) {
		Ticket ticket = new Ticket(idBooking+generateIdTicket, info);
		ticketList.add(ticket);
		return ticket;
	}

	public Ticket getTicket(int idTicket) {
		return ticketList.get(idTicket);
	}

	public int searchTicket(String id) throws NotFoundException {
		for(int i=0; i<ticketList.size(); i++) {
			if(ticketList.get(i).getId().equalsIgnoreCase(id))
				return i;
		}
		throw new NotFoundException("Ticket not found!");
	}

	@Override
	public boolean deleteTicket(String idTicket) {
		try {
			int pos = searchTicket(idTicket);
			ticketList.remove(pos);
			return true;
		}catch(NotFoundException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	public String getIdBooking() {
		return idBooking;
	}


	public String getDateBooking() {
		return date.toString();
	}
}
