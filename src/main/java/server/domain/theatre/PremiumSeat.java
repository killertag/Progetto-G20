package server.domain.theatre;

import javax.persistence.*;

@Entity
@DiscriminatorValue("PremiumSeat")
public class PremiumSeat extends Seat{
	@Column(name="addition")
	private static double addition = 20; //aggiunta in percentuale
	
	public PremiumSeat(String position) {
		super(position);
	}

	public static double getAddition() {
		return addition;
	}

	public static void setAddition(double addition) {
		PremiumSeat.addition = addition;
	}

	@Override
	public String toString() {
		return super.toString() + ", type: Premium";
	}
}
