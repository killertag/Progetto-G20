package it.unipv.www.g20.model.operator;

/**
 * This class is referred to a cinema cashier.
 *
 * @see Operator
 */
public class Cashier extends Operator {

	public Cashier(String nickname) {
		super(nickname);
		setType(TypeOperator.CASHIER);
	}
}
