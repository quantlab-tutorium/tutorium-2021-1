package info.quantlab.tutorium.session02;

/**
 * A cipher algorithm to en- and decode messages.
 *
 * @author Roland Bachl
 *
 */
public interface Cipher {

	/**
	 * Encodes a message by replacing each letter with the letter that is shift letters down the
	 * alphabet.
	 *
	 * @param message The message to be encoded.
	 * @return The encoded message.
	 */
	String encode(String message);

	/**
	 * Decodes a message by replacing each letter with the letter that is shift letters down the
	 * alphabet.
	 *
	 * @param message The message to be decoded.
	 * @return The decoded message.
	 */
	String decode(String message);

}