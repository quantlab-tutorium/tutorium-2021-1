package info.quantlab.tutorium.session11;

import java.util.function.DoubleUnaryOperator;

/**
 * Compare the errors of forward, backward and central finite difference methods for the first derivative of given
 * mathematical functions.
 *
 * @author Roland Bachl
 *
 */
public class FiniteDifferenceComparison {

	// Parameters for the plot
	private static DoubleUnaryOperator scaleTransform = a -> Math.pow(10, a);
	private static double xMin = -16.5;
	private static double xMax = -14;
	private static int numberOfPoints = 200;

	// The functions to plot
	private static double evaluationPoint = 1.0;

	private static DoubleUnaryOperator function1 = a -> Math.log(a);
	private static DoubleUnaryOperator derivative1 = a -> 1 / a;
	private static String function1Name = "log(x)";

	private static DoubleUnaryOperator function2 = a -> a * a;
	private static DoubleUnaryOperator derivative2 = a -> 2 * a;
	private static String function2Name = "x^2";

	private static DoubleUnaryOperator function3 = a -> Math.sin(a);
	private static DoubleUnaryOperator derivative3 = a -> Math.cos(a);
	private static String function3Name = "sin(x)";

	public static void main(String[] args) {
		// TODO
	}

	/**
	 * Calculate the forward difference of the given function at the evaluation point using the given shift.
	 *
	 * @param function
	 * @param evaluationPoint
	 * @param shift
	 * @return The forward difference
	 */
	private static double forwardDifference(DoubleUnaryOperator function, double evaluationPoint, double shift) {
		// TODO
		return 0;
	}

	/**
	 * Calculate the backward difference of the given function at the evaluation point using the given shift.
	 *
	 * @param function
	 * @param evaluationPoint
	 * @param shift
	 * @return The backward difference
	 */
	private static double backwardDifference(DoubleUnaryOperator function, double evaluationPoint, double shift) {
		// TODO
		return 0;
	}

	/**
	 * Calculate the central difference of the given function at the evaluation point using the given shift.
	 *
	 * @param function
	 * @param evaluationPoint
	 * @param shift
	 * @return The central difference
	 */
	private static double centralDifference(DoubleUnaryOperator function, double evaluationPoint, double shift) {
		// TODO
		return 0;
	}

}
