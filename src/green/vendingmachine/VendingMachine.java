package green.vendingmachine;

public class VendingMachine {

	private Inventory inventory;
	private Money bank;

	/**
	 * The amount of money the person has put into the Vending Machine so far
	 */
	private Money paid;

	public VendingMachine(Inventory inventory, Money bank) {
		this.inventory = inventory;
		this.bank = bank;
		paid = new Money();
	}

	public Inventory getInventory() {
		return inventory;
	}

	public Money getBank() {
		return bank;
	}

	public Money getPaid() {
		return paid;
	}

	/**
	 * Add additional Money to the machine
	 * 
	 * @param additional
	 * @return the amount that the person has put into the machine
	 */
	public double pay(Money additional) {
		paid.add(additional);
		return paid.getTotal();
	}

	/**
	 * 
	 * @param code
	 * @return the amount of change as a Money object
	 * @throws CodeNotFoundException
	 *             if there is no item with that code
	 * @throws NotEnoughPaidException
	 *             if paid is not enough to buy the item
	 * @throws NotEnoughChangeException
	 *             if the transaction cannot be completed because there isn't
	 *             enough money in the vending machine for the change
	 */
	public Money buy(String code) throws CodeNotFoundException, NotEnoughPaidException, NotEnoughChangeException {
		if (inventory.get(code) != null) {
			if (inventory.get(code).getQuantity() == 0){
				Money money = new Money (0,0,0,0);
				return money;
				
			}
			if (paid.getTotal() >= inventory.get(code).getPrice()) {
				bank.add(paid);
				double changeNeeded = paid.getTotal() - inventory.get(code).getPrice();
				changeNeeded = Math.round(changeNeeded * 100.0) / 100.0;
				Money change = bank.remove(changeNeeded);
				inventory.removeOne(code);
				paid.setNumDollars(0);
				paid.setNumQuarters(0);
				paid.setNumDimes(0);
				paid.setNumNickles(0);
				return change;
			} else {
				throw new NotEnoughPaidException();

			}

		} else {
			throw new CodeNotFoundException();
		}

	}

}
