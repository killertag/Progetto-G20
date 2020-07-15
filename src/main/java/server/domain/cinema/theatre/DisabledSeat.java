package server.domain.cinema.theatre;

/**
 * This class represents a seat made available for disabled person.
 * @see Theatre
 **/
public class DisabledSeat extends Seat {
	public DisabledSeat(String position) {
		super(position);
	}

	//50% reduction for the disabled seat to the price of the showing
	@Override
	public double getAddition() {
		return 0.5;
	}

	@Override
	public TypeSeat getType() {
		return TypeSeat.DISABLED;
	}

	@Override
	public String toString() {
		return super.toString() + ", type: Disabled";
	}
}
