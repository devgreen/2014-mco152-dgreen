package green.vendingmachine;

import org.junit.Assert;
import org.junit.Test;

public class MoneyTest {

	@Test
	public void testAdd() {
		Money money = new Money();
		Money add = new Money(10, 10, 10, 10);
		money.add(add);
		Assert.assertEquals(
				money.getNumDollars() + money.getNumQuarters() + money.getNumDimes() + money.getNumNickles(),
				add.getNumDollars() + add.getNumQuarters() + add.getNumDimes() + add.getNumNickles());

	}

	@Test
	public void testGetTotal() {
		Money add = new Money(10, 10, 10, 10);
		Assert.assertEquals(14, add.getTotal(), 0);

	}

	@Test
	public void testRemove() throws NotEnoughChangeException {
		Money money = new Money(10, 10, 10, 10);
		money.remove(.45);

		Assert.assertEquals(13.55, money.getTotal(), 0);

	}

	@Test
	public void testRemoveException() {
		Money money = new Money(0, 0, 0, 0);
		try {
			money.remove(.45);
			Assert.fail("NotEnoughChange Exception failed");
		} catch (NotEnoughChangeException e) {
			e.printStackTrace();
		}

	}

	/*
	 * @Test public void testTestRemoveAll() throws NotEnoughChangeException {
	 * Money money = new Money(1, 1, 1, 1); Assert.assertEquals(1.40,
	 * money.remove(1.40).getTotal(),0);
	 * 
	 * 
	 * }
	 */

}
