package green.test2;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A Datebook holds Events
 * 
 * 
 * You can obtain the day of week, day of month and day of year for a particular
 * Date by using the following code.
 * 
 * Date date = ... ; Calendar calendar = Calendar.getInstance();
 * calendar.setTime(date); int dayOf = calendar.get(field);
 * 
 * Where field is one of Calendar.DAY_OF_YEAR, Calendar.DAY_OF_MONTH,
 * Calendar.DAY_OF_WEEK
 * 
 * Refer to the code in DatebookTest on how to construct a Date object.
 * 
 * Refer to documentation on the Calendar class
 * https://docs.oracle.com/javase/7/docs/api/java/util/Calendar.html
 * 
 */

public class Datebook {

	/**
	 * Add a single Event to the Datebook for a particular date. This is a
	 * non-recurring event.
	 * 
	 * @param event
	 * @param date
	 */

	private Map<Date, List<Event>> map = new HashMap<Date, List<Event>>();
	private Map<Integer, List<Event>> dayMap = new HashMap<Integer, List<Event>>();
	private Map<Integer, List<Event>> monthMap = new HashMap<Integer, List<Event>>();
	private Map<Integer, List<Event>> yearMap = new HashMap<Integer, List<Event>>();
	private ArrayList<Event> dailyEvents = new ArrayList<Event>();

	// private ArrayList<Event> events = new ArrayList<Event>();

	public void addSingleEvent(Event event, Date date) {
		if (map.containsKey(date)) {
			map.get(date).add(event);

			// events.add(event);
		} else {
			ArrayList<Event> list = new ArrayList<Event>();
			list.add(event);
			map.put(date, list);

		}

	}

	/**
	 * Adds an Event to a Datebook that is recurring every day. For instance, a
	 * wake up alarm.
	 */
	public void addDailyEvent(Event event) {
		dailyEvents.add(event);
	}

	/**
	 * Adds an Event to the Datebook that is recurring the same day every week.
	 * For instance, a class starts at the same time once a week.
	 * 
	 * @param dayOfWeek
	 *            This is a constant from the Calendar class. (ex.
	 *            Calendar.MONDAY, Calendar.TUESDAY...)
	 * 
	 */
	public void addWeeklyEvent(Event event, int dayOfWeek) {
		// Integer day = dayOfWeek;
		if (dayMap.containsKey(dayOfWeek)) {
			dayMap.get(dayOfWeek).add(event);

			// events.add(event);
		} else {
			ArrayList<Event> list = new ArrayList<Event>();
			list.add(event);
			dayMap.put(dayOfWeek, list);

		}

	}

	/**
	 * Adds an Event to the Datebook that is recurring the same day every month.
	 * For instance, you always get paid on the 1st and the 15th of the month.
	 * 
	 * @param dayOfMonth
	 *            this is the day of the month starting with 1
	 */
	public void addMonthlyEvent(Event event, int dayOfMonth) {
		// Integer month = dayOfMonth;

		if (monthMap.containsKey(dayOfMonth)) {
			monthMap.get(dayOfMonth).add(event);

			// events.add(event);
		} else {
			ArrayList<Event> list = new ArrayList<Event>();
			list.add(event);
			monthMap.put(dayOfMonth, list);

		}

	}

	/**
	 * Adds an Event to the Datebook that is recurring the same day every year.
	 * For instance, a birthday.
	 * 
	 * @param dayOfYear
	 *            this is the day of the year starting with 1 and ending with
	 *            365
	 */
	public void addYearlyEvent(Event event, int dayOfYear) {
		// Integer year = dayOfYear;
		if (yearMap.containsKey(dayOfYear)) {
			yearMap.get(dayOfYear).add(event);

			// events.add(event);
		} else {
			ArrayList<Event> list = new ArrayList<Event>();
			list.add(event);
			yearMap.put(dayOfYear, list);

		}

	}

	/**
	 * 
	 * @return a List of Events for the specified date. The Events should be
	 *         sorted by their timeOfDay. If no events occur on that day then an
	 *         empty List should be returned.
	 */
	public List<Event> getEvents(Date date) {
		ArrayList<Event> list = new ArrayList<Event>();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		Integer dayOf = calendar.get(Calendar.DAY_OF_WEEK);
		Integer month = calendar.get(Calendar.DAY_OF_MONTH);
		Integer year = calendar.get(Calendar.DAY_OF_YEAR);

		if (map.get(date) != null) {
			list.addAll(map.get(date));
		}

		if (dayMap.get(dayOf) != null) {
			list.addAll(dayMap.get(dayOf));
		}

		if (monthMap.get(month) != null) {
			list.addAll(monthMap.get(month));
		}

		if (yearMap.get(year) != null) {
			list.addAll(yearMap.get(year));
		}

		list.addAll(dailyEvents);

		Collections.sort(list);
		return list;
	}

}
