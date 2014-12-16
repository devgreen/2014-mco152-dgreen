package green.vendingmachine;

public class Money {

	private int numDollars;
	private int numQuarters;
	private int numDimes;
	private int numNickles;

	public Money() {

	}

	public Money(int numDollars, int numQuarters, int numDimes, int numNickles) {
		this.numDollars = numDollars;
		this.numQuarters = numQuarters;
		this.numDimes = numDimes;
		this.numNickles = numNickles;
	}

	public void add(Money money) {
		this.numDollars += money.getNumDollars();
		this.numQuarters += money.getNumQuarters();
		this.numDimes += money.getNumDimes();
		this.numNickles += money.getNumNickles();

	}

	public Money remove(double amount) throws NotEnoughChangeException {

		Money change = new Money(0, 0, 0, 0);
		if (amount > this.getTotal()) {
			throw new NotEnoughChangeException();

		}
		while (amount > 0) {
			if (amount >= 1 && this.getNumDollars() > 0) {

				numDollars--;
				amount = amount - 1;
				change.numDollars++;

			} else if (amount >= .25 && this.getNumQuarters() > 0) {

				numQuarters--;
				amount = amount - .25;
				amount = Math.round(amount * 100.0) / 100.0;

				change.numQuarters++;

			} else if (amount >= .1 && this.getNumDimes() > 0) {

				numDimes--;
				amount = amount - .1;
				amount = Math.round(amount * 100.0) / 100.0;

				change.numDimes++;

			} else if (amount >= .05 && this.getNumNickles() > 0 && this.getNumNickles() * .05 >= amount) {

				numNickles--;
				amount = amount - .05;
				amount = Math.round(amount * 100.0) / 100.0;

				change.numNickles++;
			} else {
				throw new NotEnoughChangeException();
			}

		}
		return change;

	}

	public double getTotal() {
		double total = numDollars * 1 + numQuarters * .25 + numDimes * .1 + numNickles * .05;
		total = Math.round(total * 100.0) / 100.0;
		return total;

	}

	public int getNumDollars() {
		return numDollars;
	}

	public void setNumDollars(int numDollars) {
		this.numDollars = numDollars;
	}

	public int getNumQuarters() {
		return numQuarters;
	}

	public void setNumQuarters(int numQuarters) {
		this.numQuarters = numQuarters;
	}

	public int getNumNickles() {
		return numNickles;
	}

	public void setNumNickles(int numNickles) {
		this.numNickles = numNickles;
	}

	public int getNumDimes() {
		return numDimes;
	}

	public void setNumDimes(int numDimes) {
		this.numDimes = numDimes;
	}

}
