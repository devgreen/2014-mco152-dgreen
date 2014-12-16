package green.test2;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

public class DatebookTest {

	/**
	 * 
	 * @param year
	 *            4 digit year
	 * @param month
	 *            Calendar.JANUARY, Calendar.FEBRUARY...
	 * @param dayOfMonth
	 *            starting from 1
	 * @return A Date from the specified parameters
	 */
	private Date getDate(int year, int month, int dayOfMonth) {
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(year, month, dayOfMonth, 0, 0, 0);
		return calendar.getTime();
	}

	@Test
	/**
	 * After calling addSingleEvent() verify that the Event is returned when calling getEvents()
	 */
	public void testAddSingleEvent() {
		Datebook datebook = new Datebook();

		// given an event
		Event event = new Event("EVENT 1", 1200);
		Date today = getDate(2014, Calendar.NOVEMBER, 25);

		// when the event is added today
		datebook.addSingleEvent(event, today);

		// then the event is returned for today
		List<Event> list = datebook.getEvents(today);
		Assert.assertNotNull(list);
		Assert.assertEquals(1, list.size());
		Assert.assertSame(event, list.get(0));

		// then the event is not returned tomorrow
		Date tomorrow = getDate(2014, Calendar.NOVEMBER, 26);
		list = datebook.getEvents(tomorrow);
		Assert.assertNotNull(list);
		Assert.assertTrue(list.isEmpty());
	}

	@Test
	/**
	 * After calling addYearlyEvent() verify that the Event is returned when calling getEvents()
	 */
	public void testAddYearlyEvent() {

		Datebook datebook = new Datebook();
		Event event = new Event("EVENT 1", 1200);
		Event event2 = new Event("EVENT 1", 1200);
		datebook.addYearlyEvent(event, 329);
		datebook.addYearlyEvent(event2, 330);
		Date today = getDate(2014, Calendar.NOVEMBER, 25);
		List<Event> list = datebook.getEvents(today);
		Assert.assertNotNull(list);
		Assert.assertEquals(1, list.size());
		Assert.assertSame(event, list.get(0));

	}

	@Test
	/**
	 * After calling addMonthlyEvent() verify that the Event is returned when calling getEvents()
	 */
	public void testAddMonthlyEvent() {
		Datebook datebook = new Datebook();
		Event event = new Event("EVENT 1", 1200);
		datebook.addMonthlyEvent(event, 25);
		Date today = getDate(2014, Calendar.NOVEMBER, 25);
		List<Event> list = datebook.getEvents(today);
		Assert.assertNotNull(list);
		Assert.assertEquals(1, list.size());
		Assert.assertSame(event, list.get(0));

	}

	@Test
	/**
	 * After calling addWeeklyEvent() verify that the Event is returned when calling getEvents()
	 */
	public void testAddWeeklyEvent() {
		Datebook datebook = new Datebook();
		Event event = new Event("EVENT 1", 1200);
		Event event2 = new Event("EVENT 2", 1300);
		Event event3 = new Event("EVENT 3", 1400);
		datebook.addWeeklyEvent(event, Calendar.TUESDAY);
		datebook.addWeeklyEvent(event2, Calendar.TUESDAY);
		datebook.addWeeklyEvent(event3, Calendar.WEDNESDAY);
		Date today = getDate(2014, Calendar.NOVEMBER, 25);
		List<Event> list = datebook.getEvents(today);
		Assert.assertNotNull(list);
		Assert.assertEquals(2, list.size());
		Assert.assertSame(event, list.get(0));
		Assert.assertSame(event2, list.get(1));

	}

	@Test
	/**
	 * After calling addDailyEvent() verify that the Event is returned when calling getEvents()
	 */
	public void testAddDailyEvent() {
		Datebook datebook = new Datebook();
		Event event = new Event("EVENT 1", 1200);
		datebook.addDailyEvent(event);
		Date today = getDate(2014, Calendar.NOVEMBER, 25);
		List<Event> list = datebook.getEvents(today);
		Assert.assertNotNull(list);
		Assert.assertEquals(1, list.size());
		Assert.assertSame(event, list.get(0));

	}

	@Test
	/**
	 * After adding multiple Events, verify that they are all returned from getEvents() in the correct order.
	 */
	public void testGetEventsReturnsSortedList() {
		Datebook datebook = new Datebook();
		Event event1 = new Event("EVENT 1", 100);
		Event event2 = new Event("EVENT 2", 200);
		Event event3 = new Event("EVENT 3", 300);
		Event event4 = new Event("EVENT 4", 400);
		Event event5 = new Event("EVENT 5", 400);

		datebook.addMonthlyEvent(event5, 26);
		datebook.addMonthlyEvent(event1, 25);
		datebook.addDailyEvent(event3);
		datebook.addWeeklyEvent(event4, Calendar.TUESDAY);
		datebook.addDailyEvent(event2);

		Date today = getDate(2014, Calendar.NOVEMBER, 25);
		List<Event> list = datebook.getEvents(today);
		Assert.assertNotNull(list);
		Assert.assertEquals(4, list.size());
		Assert.assertSame(event1, list.get(0));
		Assert.assertSame(event2, list.get(1));
		Assert.assertSame(event3, list.get(2));
		Assert.assertSame(event4, list.get(3));

		Date tomorrow = getDate(2014, Calendar.NOVEMBER, 26);
		list = datebook.getEvents(tomorrow);
		Assert.assertNotNull(list);
		Assert.assertEquals(3, list.size());
		Assert.assertSame(event2, list.get(0));
		Assert.assertSame(event3, list.get(1));
		Assert.assertSame(event5, list.get(2));

	}

}
