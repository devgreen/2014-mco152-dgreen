package green.vendingmachine;

public class NotEnoughChangeException extends Exception {
	public NotEnoughChangeException(){
		super ("not enough change");
	}

	private static final long serialVersionUID = 2L;

}
