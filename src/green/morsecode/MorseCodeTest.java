package green.morsecode;



import org.junit.Assert;
import org.junit.Test;

public class MorseCodeTest {

	@Test
	public void testToMorseCode(){
	MorseCode code = new MorseCode();
	String morse = code.toMorseCode("Testing 123");
	Assert.assertEquals("- . ... - .. -. --. / .---- ..--- ...--" , morse);
	}
	@Test
	public void testToPlainText(){
	MorseCode code = new MorseCode();
	String text = code.toPlainText("--. .-. ---.. / - --- / ... . . / -.-- --- ..-");
	Assert.assertEquals("GR8 TO SEE YOU" , text);
	}
	

}
