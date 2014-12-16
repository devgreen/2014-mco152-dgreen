package green.triangle;

import org.junit.Assert;
import org.junit.Test;

public class TriangleTest {

	@Test
	public void testToString() {
		Triangle triangle = new Triangle(2);

		Assert.assertEquals(" *\n***", triangle.toString());

		Triangle triangle2 = new Triangle(3);
		Assert.assertEquals("  *\n * *\n*****", triangle2.toString());

	}
}
