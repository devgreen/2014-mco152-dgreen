package green.test1;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class AirplaneTest {

	@Test
	public void testToStringWithEmptyPlane() {
		Airplane plane = new Airplane("AB_CD_EF", 3);
		Assert.assertEquals("    AB_CD_EF\n" + "001 .._.._..\n" + "002 .._.._..\n" + "003 .._.._..\n", plane.toString());
	}

	@Test
	public void testToStringWithFullPlane() throws UnknownSeatException {
		Airplane plane = new Airplane("AB_CD_EF", 3);
		plane.occupy("1A", "1B", "1C", "1D", "1E", "1F", "2A", "2B", "2C", "2D", "2E", "2F", "3A", "3B", "3C", "3D",
				"3E", "3F");
		Assert.assertEquals("    AB_CD_EF\n" + "001 OO_OO_OO\n" + "002 OO_OO_OO\n" + "003 OO_OO_OO\n", plane.toString());
	}

	@Test
	public void testGetNumSeats() {
		Airplane plane = new Airplane("AB_CD_EF", 3);
		Assert.assertEquals(18, plane.getNumSeats());

		Airplane plane2 = new Airplane("A_B_C", 3);
		Assert.assertEquals(9, plane2.getNumSeats());

	}

	@Test
	public void testGetNumEmptySeats() throws UnknownSeatException {
		Airplane plane = new Airplane("AB_CD_EF", 3);
		Assert.assertEquals(18, plane.getNumEmptySeats());

		Airplane plane2 = new Airplane("AB_CD_EF", 2);
		plane2.occupy("1A", "1C", "1D", "1E", "1F", "2A", "2B", "2C", "2E", "2F");
		Assert.assertEquals(2, plane2.getNumEmptySeats());

	}

	@Test
	public void testIsFull() throws UnknownSeatException {
		Airplane plane = new Airplane("AB_CD_EF", 3);
		plane.occupy("1A", "1B", "1C", "1D", "1E", "1F", "2A", "2B", "2C", "2D", "2E", "2F", "3A", "3B", "3C", "3D",
				"3E", "3F");
		Assert.assertTrue(plane.isFull());

	}

	@Test
	public void testGetSeatThrowsUnknownSeatException() {
		Airplane plane = new Airplane("AB_CD_EF", 3);
		try {
			plane.getSeat("6A");
			Assert.fail();
		} catch (UnknownSeatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testOccupySeats() throws UnknownSeatException, FullPlaneException, NotEnoughSeatsTogeatherException {
		Airplane plane = new Airplane("AB_CDE_FG", 3);
		List<Seat> seats = new ArrayList<Seat>();
		seats = plane.occupySeats(3);
		Assert.assertEquals(3, seats.size());

	}

	@Test
	public void testOccupySeatsThrowsNotEnoughSeatsTogeatherException() throws FullPlaneException {
		/*
		 * Airplane plane = new Airplane("AB_CD_EF", 3); plane.occupy("1A",
		 * "1C", "1D", "1E", "2A", "2B", "2C", "2D", "2F", "3A", "3B", "3C",
		 * "3D", "3E"); List<Seat> seats = new ArrayList<Seat>(); try { seats =
		 * plane.occupySeats(2); Assert.fail(); } catch
		 * (NotEnoughSeatsTogeatherException e) { // TODO Auto-generated catch
		 * block e.printStackTrace(); }
		 */

		Airplane plane2 = new Airplane("AB_CD_EF", 3);
		List<Seat> seats2 = new ArrayList<Seat>();
		try {
			seats2 = plane2.occupySeats(4);
			Assert.fail();
		} catch (NotEnoughSeatsTogeatherException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	public void testOccupySeatsThrowsFullPlaneException() throws NotEnoughSeatsTogeatherException, UnknownSeatException {
		Airplane plane = new Airplane("AB_CD_EF", 3);
		plane.occupy("1A", "1B", "1C", "1D", "1E", "1F", "2A", "2B", "2C", "2D", "2E", "2F", "3A", "3B", "3C", "3D",
				"3E", "3F");

		List<Seat> seats = new ArrayList<Seat>();

		try {
			seats = plane.occupySeats(2);
			Assert.fail();
		} catch (FullPlaneException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
