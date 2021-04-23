package info.quantlab.tutorium.solution01;
/**
 *
 */


import java.util.Scanner;

/**
 * Introduce yourself to Java.
 *
 * @author Roland Bachl
 *
 */
public class Introductions {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String name;

		// Check if a name was given at launch
		if (args.length > 0) {
			// Arguments separated by a space are broken up into an array. We join them together.
			name = String.join(" ", args);
		} else {
			name = getName();
		}

		System.out.println(name + ", pleasure to meet you.");
	}

	/**
	 * Get the user name via the command line.
	 *
	 * @return The user name.
	 */
	private static String getName() {
		System.out.println("Hi, what's your name?");
		System.out.print("[Your name:] ");

		Scanner scanner = new Scanner(System.in);
		String name = scanner.nextLine();

		scanner.close(); // In most cases you want to close a Scanner and InputStream to free up resources. Take care
							// that in this case we are also closing System.in! Fortunately we know we won't need it
							// again, so we don't need to take further care.

		return name;
	}
}
