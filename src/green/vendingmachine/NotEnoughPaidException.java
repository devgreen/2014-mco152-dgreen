package green.vendingmachine;

public class NotEnoughPaidException extends Exception {
	public NotEnoughPaidException(){
		super ("Not Enough Paid");
	}

	private static final long serialVersionUID = 2L;

}
