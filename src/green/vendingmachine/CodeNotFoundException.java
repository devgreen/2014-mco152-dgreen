package green.vendingmachine;

public class CodeNotFoundException extends Exception {
	public CodeNotFoundException(){
		super ("Code Not Found");
	}

	private static final long serialVersionUID = 2L;

}
