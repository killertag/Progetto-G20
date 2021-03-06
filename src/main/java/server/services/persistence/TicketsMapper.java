package server.services.persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import server.domain.cinema.Ticket;
import server.exception.ObjectNotFoundException;
import server.exception.SearchException;

/**
 * It is the mapper of the table "Tickets"
 */
public class TicketsMapper extends AbstractPersistenceMapper {
	private Map<String, Ticket> tickets;
	private ShowingsMapper sm;

	/**
	 * Initialize tickets with
	 * the tickets which are registered  when the system is set up.
	 * @throws SQLException
	 */
	public TicketsMapper(ShowingsMapper sm) throws SQLException {
		super("TICKETS");
		this.tickets = new HashMap<>();
		this.sm=sm;
		setUp();
	}

	@Override
	protected Object getObjectFromTable(String OID) throws SQLException, ObjectNotFoundException {
		PreparedStatement pstm = conn.prepareStatement("select ticketCode, movieTitle, occupiedSeat, showingID, totalPrice from TICKETS join MOVIESHOWINGS on TICKETS.showingID = MOVIESHOWINGS.id WHERE ticketCode = ?");
		pstm.setString(1,OID);
		ResultSet rs = pstm.executeQuery();
		if(!rs.next())
			throw new ObjectNotFoundException();

		try {
			return new Ticket(rs.getString(1),rs.getString(2),rs.getString(3),
					(server.domain.cinema.MovieShowing)sm.get(rs.getString(4)), rs.getDouble(5));
		} catch (ObjectNotFoundException e) {
			return new Ticket(rs.getString(1),rs.getString(2),rs.getString(3),
					null, rs.getDouble(5));
		}
	}


	@Override
	public synchronized void updateTable(String OID, Object obj)throws SQLException {
	}

	@Override
	protected Object getObjectFromCache(String OID) {
		if(this.tickets.containsKey(OID))
			return this.tickets.get(OID);
		return null;
	}

	@Override
	protected void updateCache(String OID,Object obj) {
		this.tickets.remove(OID);
		this.tickets.put(OID,(Ticket)obj);
	}

	@Override
	public synchronized void delete(String OID) throws SQLException, SearchException {
		PreparedStatement stm = conn.prepareStatement("DELETE FROM " + super.tableName + " WHERE ticketCode!='' and ticketCode=?");
		stm.setString(1, OID);
		stm.execute();

		this.tickets.remove(OID);
	}


	@Override
	public synchronized void put(String OID, Object obj) throws SQLException{
		Ticket t = (Ticket)obj;

		PreparedStatement pstm = conn.prepareStatement("INSERT INTO "+tableName+" VALUES(?,?,?,?,?)");

		pstm.setString(1,OID);
		pstm.setString(2, t.getTheatre());
		pstm.setObject(3, t.getShowingId());
		pstm.setString(4,t.getSeat());
		pstm.setDouble(5,Math.round(t.getTotalPrice()*100)/100);

		pstm.execute();
		updateCache(OID,t);
	}

	protected void setUp() throws SQLException {
		Statement stm = super.conn.createStatement();
		ResultSet rs = stm.executeQuery("select ticketCode, movieTitle, occupiedSeat, showingID, totalPrice from TICKETS join MOVIESHOWINGS on TICKETS.showingID = MOVIESHOWINGS.id");

		while (rs.next()){
			try {
				Ticket tmp = new Ticket(rs.getString(1),rs.getString(2),rs.getString(3),
						(server.domain.cinema.MovieShowing)sm.get(rs.getString(4)), rs.getDouble(5));
				this.tickets.put(rs.getString(1),tmp);
			} catch (ObjectNotFoundException e) {
				Ticket tmp = new Ticket(rs.getString(1),rs.getString(2),rs.getString(3),
						null, rs.getDouble(5));
				this.tickets.put(rs.getString(1),tmp);
			}
		}
	}

	protected List<Ticket> getTicketListForShowing(String OID_showing) throws SQLException {
		List<Ticket> tickets = new ArrayList<>();
		PreparedStatement stm = conn.prepareStatement("select ticketCode, movieTitle, occupiedSeat, showingID, totalPrice from TICKETS join MOVIESHOWINGS on TICKETS.showingID = MOVIESHOWINGS.id where showingID = ?");
		stm.setString(1, OID_showing);
		ResultSet rs = stm.executeQuery();
		while (rs.next()){


			try {
				Ticket t = new Ticket(rs.getString(1),rs.getString(2),rs.getString(3),
						(server.domain.cinema.MovieShowing)sm.get(rs.getString(4)), rs.getDouble(5));
				updateCache(t.getCode(),t);
				tickets.add(t);
			} catch (SQLException | ObjectNotFoundException e) {
				e.printStackTrace();
			}
		}
		return tickets;
	}

	protected synchronized Map<String, Ticket> getTickets() {
		return tickets;
	}
}
