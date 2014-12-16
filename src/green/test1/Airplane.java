package green.test1;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import green.test1.Seat;
import green.test1.UnknownSeatException;

public class Airplane {

	/**
	 * Construct a new Airplane with the specified configuration and number of
	 * rows. The configuration is a String with letters specifying a seat's
	 * position in the row and a "_" for the aisle.
	 * 
	 * For instance, an Airplane with configuration, ABC_DEFGH_JKL would be
	 * three seats, then an aisle, then 5 seats, then an aisle, then 3 seats.
	 * 
	 * @param configuration
	 * @param numRows
	 */

	private Map<String, Seat> map;
	private String config;
	private int rows;
	private String code;
	private ArrayList<Seat> plane;

	public Airplane(String configuration, int numRows) {
		config = configuration;
		rows = numRows;
		map = new HashMap<String, Seat>();
		plane = new ArrayList<Seat>();
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < configuration.length(); j++) {
				if (configuration.charAt(j) != '_') {
					code = String.valueOf(i + 1) + configuration.charAt(j);
					Seat seat = new Seat(i + 1, configuration.charAt(j));
					Seat value = map.get(code);
					if (value == null) {
						map.put(code, seat);
						plane.add(seat);
					}
				}
			}
		}
	}

	/**
	 * @return the total number of EMPTy seats on the plane.
	 */
	public int getNumEmptySeats() {
		int count = 0;
		for (int i = 0; i < plane.size(); i++) {

			if (!plane.get(i).isOccupied()) {
				count++;
			}
		}
		return count;
	}

	/**
	 * @return true if the plane is full, otherwise false.
	 */
	public boolean isFull() {
		int count = 0;
		for (int i = 0; i < map.size(); i++) {
			if (map.get(code).isOccupied()) {
				count++;
			}
		}
		if (count == plane.size()) {
			return true;
		}
		return false;
	}

	/**
	 * @param code
	 * @return true if the seat is occupied, otherwise false.
	 * @throws UnknownSeatException
	 *             if the seat code is not found in the plane.
	 */
	public boolean isOccupied(String code) throws UnknownSeatException {
		if (!map.containsKey(code)) {
			throw new UnknownSeatException();
		}
		return map.get(code).isOccupied();
	}

	/**
	 * Sets the seat as occupied/unoccupied
	 * 
	 * @param code
	 * @param occupied
	 * @throws UnknownSeatException
	 *             if the seat code is not found in the plane.
	 */
	public void setOccupied(String code, boolean occupied) throws UnknownSeatException {
		if (!map.containsKey(code)) {
			throw new UnknownSeatException();

		} else {
			map.get(code).setOccupied(occupied);
		}

	}

	/**
	 * Set all seats by their codes as occupied
	 * 
	 * @param codes
	 * @throws UnknownSeatException
	 *             if the seat code is not found in the plane.
	 */
	public void occupy(String... codes) throws UnknownSeatException {
		for (String code : codes) {
			if (!map.containsKey(code)) {
				throw new UnknownSeatException();

			} else {
				map.get(code).setOccupied(true);
			}

		}

	}

	/**
	 * Sets all seats as occupied
	 * 
	 * @param seats
	 */
	public void occupy(List<Seat> seats) {
		for (Seat s : seats) {
			s.setOccupied(true);

		}
	}

	/**
	 * Returns the seat specified by it's code
	 * 
	 * @param code
	 * @throws UnknownSeatException
	 *             if the seat code is not found in the plane.
	 */
	public Seat getSeat(String code) throws UnknownSeatException {
		if (!map.containsKey(code)) {
			throw new UnknownSeatException();
		} else {
			Seat seat = map.get(code);
			return seat;
		}

	}

	/**
	 * @return total number of seats on the plane
	 */
	public int getNumSeats() {
		return plane.size();
	}

	/**
	 * Returns the Airplane specified in text format.
	 * 
	 * The first line should be the configuration, prepended by 4 spaces Each
	 * row in the plane gets a line which starts with The row number, padded
	 * with leading zeros so that is is always 3 digits. A space Then for each
	 * seat, either a "." for an empty seat, "O" for an occupied seat and "_"
	 * for an aisle.
	 * 
	 * Example. AB_CD_EF\n 001 .._.._..\n 002 .._.._..\n 003 .._.._..\n
	 * 
	 * 
	 */
	public String toString() {
		StringBuilder info = new StringBuilder();
		int counter = 0;
		DecimalFormat formatter = new DecimalFormat("000");
		info.append("    ");
		info.append(config);
		info.append("\n");
		for (int i = 0; i < rows; i++) {
			info.append(formatter.format(i + 1));
			info.append(" ");
			for (int j = 0; j < config.length(); j++) {
				if (config.charAt(j) == '_') {
					info.append('_');
				} else if (!plane.get(counter).isOccupied()) {
					info.append(".");
					counter++;
				} else if (plane.get(counter).isOccupied()) {
					info.append("O");
					counter++;
				}

			}
			info.append("\n");

		}
		return info.toString();
	}

	/**
	 * 
	 * @param numSeatsTogeather
	 *            the number of seats to occupy.
	 * @return A list of occupied seats.
	 * @throws FullPlaneException
	 *             if the plane is full
	 * @throws NotEnoughSeatsTogeatherException
	 *             if there are not enough seats next to each other.
	 */
	public List<Seat> occupySeats(int numSeatsTogeather) throws FullPlaneException, NotEnoughSeatsTogeatherException {
		int found;
		int counter = 0;
		List<Seat> list = new ArrayList<Seat>();
		if (isFull()) {
			throw new FullPlaneException();
		}
		boolean possible = false;

		for (int i = 0; i < rows; i++) {
			found = 0;
			list.clear();
			for (int j = 0; j < config.length(); j++) {
				if (config.charAt(j) == '_') {
					found = 0;
					list.clear();
				} else if (plane.get(counter).isOccupied()) {
					counter++;

				}

				else if (!plane.get(counter).isOccupied()) {
					list.add(plane.get(counter));
					counter++;
					found++;
					if (found == numSeatsTogeather) {
						possible = true;
						for (int k = 0; k < list.size(); k++) {
							list.get(k).setOccupied(true);

						}
						return list;

					}

				}

			}

		}

		throw new NotEnoughSeatsTogeatherException();

	}

}
