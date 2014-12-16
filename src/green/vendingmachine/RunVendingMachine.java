package green.vendingmachine;

import java.io.IOException;

import java.text.DecimalFormat;
import java.util.Scanner;

public class RunVendingMachine {

	// print inventory
	// make selection
	// input money
	// input selection
	// output change
	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		Inventory inventory = new Inventory();
		try {
			inventory.load("inventory.txt");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Money money = new Money(10, 10, 10, 10);
		VendingMachine vendingMachine = new VendingMachine(inventory, money);
		double balance = 0.0;

		int dollarsPaid = 0;
		int quartersPaid = 0;
		int dimesPaid = 0;
		int nickelsPaid = 0;
		boolean purchase = false;
		DecimalFormat formatter = new DecimalFormat("$0.00");
		System.out.println(inventory.toString());
		System.out.println("Add Money/Make Selection?");
		System.out.println("1.Dollar");
		System.out.println("2.Quarter");
		System.out.println("3.Dime");
		System.out.println("4.Nickel");
		System.out.println("or enter in the Item Code");

		do {
			try {

				String input = keyboard.next();
				if ("1".equals(input)) {
					balance += 1;

					dollarsPaid++;
					vendingMachine.getPaid().setNumDollars(dollarsPaid);
					//vendingMachine.getBank().setNumDollars(vendingMachine.getBank().getNumDollars() + 1);
					System.out.println("Balance: " + formatter.format(balance));
				} else if ("2".equals(input)) {
					balance += .25;
					balance = Math.round(balance * 100.0) / 100.0;

					quartersPaid++;
					vendingMachine.getPaid().setNumQuarters(quartersPaid);
					//vendingMachine.getBank().setNumQuarters(vendingMachine.getBank().getNumQuarters() + 1);
					System.out.println("Balance: " + formatter.format(balance));
				} else if ("3".equals(input)) {
					balance += .1;
					balance = Math.round(balance * 100.0) / 100.0;

					dimesPaid++;
					vendingMachine.getPaid().setNumDimes(dimesPaid);
					//vendingMachine.getBank().setNumDimes(vendingMachine.getBank().getNumDimes() + 1);
					System.out.println("Balance: " + formatter.format(balance));
				} else if ("4".equals(input)) {
					balance += .05;
					balance = Math.round(balance * 100.0) / 100.0;
					nickelsPaid++;
					vendingMachine.getPaid().setNumNickles(nickelsPaid);
					//vendingMachine.getBank().setNumNickles(vendingMachine.getBank().getNumNickles() + 1);
					System.out.println("Balance: " + formatter.format(balance));
				} else {
					String code = input.toUpperCase();

					Money change = vendingMachine.buy(code);

					System.out.println("Dispensing " + inventory.get(code).getName());
					System.out.println("Change " + formatter.format(change.getTotal()));

					purchase = true;

				}

			} catch (CodeNotFoundException e) {
				System.out.println(e.getMessage());
			} catch (NotEnoughPaidException e) {
				System.out.println(e.getMessage());
			} catch (NotEnoughChangeException e) {
				System.out.println(e.getMessage());
			}

		} while (!purchase);

	}

}
