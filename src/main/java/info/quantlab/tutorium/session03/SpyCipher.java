/**
 *
 */
package info.quantlab.tutorium.session03;


import java.time.LocalDateTime;

import info.quantlab.tutorium.session02.CeasarCipher;
import info.quantlab.tutorium.session02.Cipher;

/**
 * A very inconspicuously named cipher that works like regular cipher, except it will also log any
 * messages that are being encoded or decoded.
 *
 * @author Roland Bachl
 *
 */
public class SpyCipher implements Cipher {

	private Cipher cipher;
	private StringBuilder log;

	public static Cipher of(int shift) {
		return new SpyCipher(shift);
	}

	private SpyCipher(int shift) {
		super();
		this.cipher = CeasarCipher.of(shift);
	}

	public SpyCipher(Cipher cipher) {
		super();
		this.cipher = cipher;
	}

	@Override
	public String encode(String message) {
		log.append("Encoded: [" + message + "] at " + LocalDateTime.now() + "\n");
		return cipher.encode(message);
	}

	@Override
	public String decode(String message) {
		String decoded = cipher.decode(message);
		log.append("Decoded: [" + message + "] at " + LocalDateTime.now() + "\n");
		return decoded;
	}

	public String getLog() {
		return log.toString();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int shift = 7;
		String secretMessage = "This message better stay secret!";
		String plainMessage = "Do not decode.";

		Cipher cipher = of(shift);
		cipher.encode(secretMessage);
		cipher.decode(CeasarCipher.of(shift).encode(plainMessage));

		System.out.println("Reading from log:");
		String log = ((SpyCipher) cipher).getLog();
		System.out.println(log);

		if (log.toLowerCase().contains(secretMessage.toLowerCase())
				&& log.toLowerCase().contains(plainMessage.toLowerCase())) {
			System.out.println("Log successfully written.");
		} else {
			throw new RuntimeException("Something went wrong.");
		}
	}

}
