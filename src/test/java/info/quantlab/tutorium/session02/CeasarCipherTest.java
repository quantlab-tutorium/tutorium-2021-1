package info.quantlab.tutorium.session02;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Locale;
import java.util.Random;

import org.junit.jupiter.api.Test;

class CeasarCipherTest {

	private static String textToCipher = "This is a sample sentence that should be encoded and"
			+ " afterwards decoded.";
	private static int shift = new Random().nextInt(26);

	@Test
	void test() {
		Cipher testCipher  = info.quantlab.tutorium.session02.CeasarCipher.of(shift);
		Cipher checkCipher = info.quantlab.tutorium.solution02.CeasarCipher.of(shift);

		String actual	= testCipher.encode(textToCipher);
		String expected = checkCipher.encode(textToCipher);
		assertEquals(actual.toLowerCase(Locale.GERMAN), expected.toLowerCase(Locale.GERMAN));

		actual = testCipher.decode(actual);
		expected = checkCipher.decode(expected);
		assertEquals(actual.toLowerCase(Locale.GERMAN), expected.toLowerCase(Locale.GERMAN));
	}

}
