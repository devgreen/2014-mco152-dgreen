package green.vendingmachine;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

public class VendingMachineTest {

	@Test
	public void testPay() {
		Money money = new Money();
		Inventory inventory = new Inventory();
		VendingMachine vendMachine = new VendingMachine(inventory, money);
		Money additional = new Money(0, 0, 0, 1);

		Assert.assertEquals(vendMachine.pay(additional), .05, 0);

	}

	@Test
	public void testBuy() throws CodeNotFoundException, NotEnoughPaidException, NotEnoughChangeException, IOException {
		Money money = new Money(10, 10, 10, 10);
		Inventory inventory = new Inventory();
		inventory.load("inventory.txt");
		VendingMachine vendMachine = new VendingMachine(inventory, money);
		Money add = new Money(2, 0, 0, 0);
		vendMachine.pay(add);
		String code = "A01";
		Money change = vendMachine.buy(code);

		Assert.assertEquals(.45, change.getTotal(), 0);
		Assert.assertEquals(0, vendMachine.getPaid().getTotal(), 0);
		Assert.assertEquals(4, inventory.get(code).getQuantity());
		Assert.assertEquals(15.55, vendMachine.getBank().getTotal(), 0);

	}

	@Test
	public void testBuyOnlyNickels() throws CodeNotFoundException, NotEnoughPaidException, NotEnoughChangeException,
			IOException {
		Money money = new Money(0, 0, 0, 30);
		Inventory inventory = new Inventory();
		inventory.load("inventory.txt");
		VendingMachine vendMachine = new VendingMachine(inventory, money);
		Money add = new Money(2, 0, 0, 0);
		vendMachine.pay(add);
		String code = "A01";
		Money change = vendMachine.buy(code);
		Assert.assertEquals(.45, change.getTotal(), 0);

	}

	@Test
	public void testBuyOnlyDollars() throws CodeNotFoundException, NotEnoughPaidException, IOException {
		Money money = new Money(30, 0, 0, 0);
		Inventory inventory = new Inventory();
		inventory.load("inventory.txt");
		VendingMachine vendMachine = new VendingMachine(inventory, money);
		Money add = new Money(2, 0, 0, 0);
		vendMachine.pay(add);
		String code = "A01";
		try {
			Money change = vendMachine.buy(code);
			Assert.fail("NotEnoughChange Exception did not throw");
		} catch (NotEnoughChangeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	public void testThrowNotEnoughChangeException() throws CodeNotFoundException, NotEnoughPaidException, IOException {
		Money money = new Money(0, 0, 0, 1);
		Inventory inventory = new Inventory();
		inventory.load("inventory.txt");
		VendingMachine vendMachine = new VendingMachine(inventory, money);
		Money add = new Money(2, 0, 0, 0);
		vendMachine.pay(add);
		String code = "A01";
		try {
			Money change = vendMachine.buy(code);
			Assert.fail("NotEnoughChange Exception did not throw");
		} catch (NotEnoughChangeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	public void testThrowCodeNotFoundException() throws NotEnoughChangeException, NotEnoughPaidException, IOException {
		Money money = new Money(10, 10, 10, 10);
		Inventory inventory = new Inventory();
		inventory.load("inventory.txt");
		VendingMachine vendMachine = new VendingMachine(inventory, money);
		Money add = new Money(2, 0, 0, 0);
		vendMachine.pay(add);
		String code = "B55";

		try {
			Money change = vendMachine.buy(code);
			Assert.fail("CodeNotFound Exception did not throw");
		} catch (CodeNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	public void testThrowNotEnoughPaidException() throws NotEnoughChangeException, CodeNotFoundException, IOException {
		Money money = new Money(10, 10, 10, 10);
		Inventory inventory = new Inventory();
		inventory.load("inventory.txt");
		VendingMachine vendMachine = new VendingMachine(inventory, money);
		Money add = new Money(0, 0, 0, 1);
		vendMachine.pay(add);
		String code = "A01";

		try {
			Money change = vendMachine.buy(code);
			Assert.fail("NotEnoughPaidException did not throw");
		} catch (NotEnoughPaidException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/*
	 * @Test public void testAdd() throws CodeNotFoundException,
	 * NotEnoughPaidException, NotEnoughChangeException { Money money = new
	 * Money(10, 10, 10, 10); Inventory inventory = new Inventory(); Item item =
	 * new Item("A01", "Candybar", 1.55, 5); inventory.add(item); VendingMachine
	 * vendMachine = new VendingMachine(inventory, money); Money add = new
	 * Money(2, 0, 0, 0); vendMachine.pay(add); String code = "A01"; Money
	 * change = vendMachine.buy(code);
	 * 
	 * Assert.assertEquals(.45, change.getTotal(), 0); Assert.assertEquals(0,
	 * vendMachine.getPaid().getTotal(), 0); Assert.assertEquals(4,
	 * inventory.get(code).getQuantity());
	 * 
	 * }
	 */

}
