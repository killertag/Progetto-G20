package server.domain.showing;

import java.util.HashMap;

import server.domain.exception.SeatException;
import server.domain.theatre.Seat;

public class Availability {
	private HashMap <Seat,Boolean> availability;

	public Availability(HashMap<String, Seat> list) {
		availability=new HashMap<>();
		genAvailabilityList(list);

	}

	private void genAvailabilityList(HashMap<String, Seat> list) {
		HashMap<String,Seat> seats = list;
		for(String s : list.keySet())
			availability.put(seats.get(s), true);
	}

	protected boolean searchAvailability(String seat) throws SeatException {
		return availability.get(findSeat(seat));
	}

	private Seat findSeat(String seat) throws SeatException {
		for(Seat s : availability.keySet())
			if(s.getPosition().equalsIgnoreCase(seat))
				return s;
		throw new SeatException("Seat's not found");
	}

	protected void changeAvailability(String[] seats, boolean value) throws SeatException{
		for(String s : seats)
			if(!value)
				occupySeat(s);
			else
				availability.replace(findSeat(s), false);
	}

	private void occupySeat(String seat) throws SeatException {
		Seat s = findSeat(seat);
		if(availability.get(s)!=true)
			throw new SeatException("Seat are alredy booked");
		else
			availability.replace(s, true);
	}
}
