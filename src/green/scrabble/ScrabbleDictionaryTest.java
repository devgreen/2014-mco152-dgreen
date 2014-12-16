package green.scrabble;



import java.io.FileNotFoundException;

import org.junit.Assert;
import org.junit.Test;

public class ScrabbleDictionaryTest {

	
	@Test
	public void testContainsTrue() throws FileNotFoundException{
		ScrabbleDictionary dictionary = new ScrabbleDictionary ();
		
		Assert.assertTrue(dictionary.contains("HELLO"));
		
		Assert.assertTrue(dictionary.contains("Hello"));
		
	}

	
	public void testContainsFalse() throws FileNotFoundException{
		ScrabbleDictionary dictionary = new ScrabbleDictionary ();
		
		Assert.assertFalse(dictionary.contains("HELLO"));
		
		Assert.assertFalse(dictionary.contains("Hello"));
		
	}
	
	public void testContainsNull() throws FileNotFoundException{
		
		ScrabbleDictionary dictionary = new ScrabbleDictionary ();
		
		Assert.assertFalse(dictionary.contains(null));
		
		
		
	}
	
	
}
