/**
 *
 */
package info.quantlab.tutorium.session03;

import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * Calculates the value the function sin(x)/((x+pi)(x-pi)).
 *
 * @author Roland Bachl
 *
 */
public class ContinuousExtension {

	public static void main(String[] args) {

		double x1 = 1.0;
		double x2 = Math.PI;

		// Let's make the output a little nicer..
		NumberFormat formatter = new DecimalFormat(" 0.0000;-0.0000");

		System.out.println("The function at x= " + formatter.format(x1) + " equals "
				+ formatter.format(evaluateFunction(x1)) + ".");
		System.out.println("The function at x= " + formatter.format(x2) + " equals "
				+ formatter.format(evaluateFunction(x2)) + ".");
	}

	/**
	 * Calculates the value the function sin(x)/((x+pi)(x-pi)).
	 *
	 * @param argument at which to evaluate.
	 * @return The value of the function.
	 */
	public static double evaluateFunction(double argument) {

		double numerator = Math.sin(argument);
		double denominator = (argument + Math.PI) * (argument - Math.PI);

		return checkedDivision(numerator, denominator);

	}

	/**
	 * Calculates the division of the numerator by the denominator and checks that the result is a regular number.
	 *
	 * @param numerator
	 * @param denominator
	 * @return The fraction.
	 */
	private static double checkedDivision(double numerator, double denominator) {
		double answer = numerator / denominator;
		if (!Double.isFinite(answer)) {
			throw new ArithmeticException(
					"Not a mathematically sound division ( " + numerator + " / " + denominator + " ).");
		}
		return answer;
	}
}
