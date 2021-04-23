package info.quantlab.tutorium.session02;

/**
 * From Wikipedia:
 * In cryptography, a Caesar cipher, also known as Caesar's cipher, the shift cipher, Caesar's code
 * or Caesar shift, is one of the simplest and most widely known encryption techniques. It is a type
 * of substitution cipher in which each letter in the plaintext is replaced by a letter some fixed
 * number of positions down the alphabet. For example, with a left shift of 3, D would be replaced
 * by A, E would become B, and so on. The method is named after Julius Caesar, who used it in his
 * private correspondence.
 *
 * @author Roland Bachl
 *
 */
public class CeasarCipher implements Cipher {

	/**
	 * Returns a new instance of {@link CeasarCipher}.
	 *
	 * @param shift The number of letters to be shifted by.
	 * @return An instance of CeasarCipher.
	 */
	public static Cipher of(int shift) {
		// TODO Please implement this.

		return null;
	}

	/**
	 * Encodes a message by replacing each letter with the letter that is shift letters down the alphabet.
	 *
	 * @param message The message to be encoded.
	 * @return The encoded message.
	 */
	@Override
	public String encode(String message) {
		//TODO Please implement this.

		return null;
	}

	/**
	 * Decodes a message by replacing each letter with the letter that is shift letters down the alphabet.
	 *
	 * @param message The message to be decoded.
	 * @return The decoded message.
	 */
	@Override
	public String decode(String message) {
		//TODO Please implement this.

		return null;
	}

}