/**
 *
 */
package info.quantlab.tutorium.solution02;

import java.util.Locale;

import info.quantlab.tutorium.session02.Cipher;

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

	private final int shift;

	/**
	 * Returns a new instance of {@link CeasarCipher}.
	 *
	 * @param shift The number of letters to be shifted by.
	 * @return An instance of CeasarCipher.
	 */
	public static Cipher of(int shift) {
		return new CeasarCipher(shift);
	}

	/**
	 * Private constructor.
	 *
	 * @param shift The number of letters to be shifted by.
	 */
	private CeasarCipher(int shift) {
		super();
		this.shift = shift % 26;
	}

	/**
	 * Encodes a message by replacing each letter with the letter that is shift letters down the alphabet.
	 *
	 * @param message The message to be encoded.
	 * @return The encoded message.
	 */
	@Override
	public String encode(String message) {
		String encoded = "";
		int lastCharValue = 'z';
		int alphabetLength = 'z' - 'a' + 1;
		for (char character: message.toLowerCase(Locale.GERMAN).toCharArray()) {
			if(isLetter(character)) {
				int charNoRotation = character + shift;
				int charValue = charNoRotation <= lastCharValue ? charNoRotation :
					charNoRotation - alphabetLength;
				encoded += (char) charValue;
			} else {
				encoded += character;
			}
		}
		return encoded;
	}

	/**
	 * Decodes a message by replacing each letter with the letter that is shift letters down the alphabet.
	 *
	 * @param message The message to be decoded.
	 * @return The decoded message.
	 */
	@Override
	public String decode(String message) {
		String decoded = "";
		int firstCharValue = 'a';
		int alphabetLength = 'z' - 'a' + 1;
		for (char character: message.toLowerCase(Locale.GERMAN).toCharArray()) {
			if(isLetter(character)) {
				int charNoRotation = character - shift;
				int charValue = charNoRotation < firstCharValue ? charNoRotation + alphabetLength :
					charNoRotation;
				decoded += (char) charValue;
			} else {
				decoded += character;
			}
		}
		return decoded;
	}

	private static boolean isLetter(char character) {
		return (character >= 'a' && character <= 'z');
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int shift = 7;

		Cipher cipher = of(shift);
		String string = "ABCdefghijklmnopqrstuvwXyzäöü";
		System.out.println(string);
		System.out.println(cipher.encode(string));
		System.out.println(cipher.decode(cipher.encode(string)));

	}

}
