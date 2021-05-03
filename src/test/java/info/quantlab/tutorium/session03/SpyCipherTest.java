package info.quantlab.tutorium.session03;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Random;

import org.junit.jupiter.api.Test;

import info.quantlab.tutorium.session02.Cipher;

public class SpyCipherTest {
	private static String textToCipher1 = "This message better stay secret!";
	private static String textToCipher2 = "Another top secret message.";
	private static int shift = new Random().nextInt(26);

	@Test
	void test() {
		Cipher testCipher = SpyCipher.of(shift);

		testCipher.encode(textToCipher1);
		testCipher.decode(testCipher.encode(textToCipher2));

		String log = ((SpyCipher) testCipher).getLog();
		String[] entries = log.split("\n");
		assertEquals(3, entries.length);

		StringBuilder check = new StringBuilder();
		for(int i = 0; i < entries.length; i++) {
			String message = entries[i].split("\\[|\\]")[1];
			check.append(message);
		}

		assertEquals((textToCipher1 + textToCipher2 + textToCipher2).toLowerCase(),
				check.toString().toLowerCase());
	}
}
