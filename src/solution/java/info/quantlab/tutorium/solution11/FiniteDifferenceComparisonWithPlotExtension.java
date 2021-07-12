package info.quantlab.tutorium.solution11;

import java.text.DecimalFormat;
import java.util.List;
import java.util.function.DoubleUnaryOperator;

import net.finmath.plots.Named;
import net.finmath.plots.Plot2D;

/**
 * Compare the errors of forward, backward and central finite difference methods for the first derivative of given
 * mathematical functions.
 *
 * @author Roland Bachl
 *
 */
public class FiniteDifferenceComparisonWithPlotExtension {

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

		plotFiniteDifferenceApproximationError(evaluationPoint, function1, derivative1, function1Name);
		plotFiniteDifferenceApproximationError(evaluationPoint, function2, derivative2, function2Name);
		plotFiniteDifferenceApproximationError(evaluationPoint, function3, derivative3, function3Name);

	}

	/**
	 * Plot a chart of the errors in the finite difference approximation of the given function with various shift sizes.
	 *
	 * @param evaluationPoint
	 * @param function
	 * @param analyticDerivative
	 * @param functionName
	 */
	private static void plotFiniteDifferenceApproximationError(double evaluationPoint, DoubleUnaryOperator function,
			DoubleUnaryOperator analyticDerivative, String functionName) {

		// Create mappings from X to Y
		DoubleUnaryOperator forwardDifferenceApproxError = scale -> {

			double shift = scaleTransform.applyAsDouble(scale);

			double finiteDifferenceApproximation = forwardDifference(function, evaluationPoint, shift);
			double derivativeAnalytic = analyticDerivative.applyAsDouble(evaluationPoint);

			double error = finiteDifferenceApproximation - derivativeAnalytic;

			return error;
		};
		DoubleUnaryOperator backwardDifferenceApproxError = scale -> {

			double shift = scaleTransform.applyAsDouble(scale);

			double finiteDifferenceApproximation = backwardDifference(function, evaluationPoint, shift);
			double derivativeAnalytic = analyticDerivative.applyAsDouble(evaluationPoint);

			double error = finiteDifferenceApproximation - derivativeAnalytic;

			return error;
		};
		DoubleUnaryOperator centralDifferenceApproxError = scale -> {

			double shift = scaleTransform.applyAsDouble(scale);

			double finiteDifferenceApproximation = centralDifference(function, evaluationPoint, shift);
			double derivativeAnalytic = analyticDerivative.applyAsDouble(evaluationPoint);

			double error = finiteDifferenceApproximation - derivativeAnalytic;

			return error;
		};

		// Wrap to List<Named<DoubleUnaryOperator>>
		List<Named<DoubleUnaryOperator>> plotFunctions = List.of(
				new Named<DoubleUnaryOperator>("Forward difference", forwardDifferenceApproxError),
				new Named<DoubleUnaryOperator>("Backward difference", backwardDifferenceApproxError),
				new Named<DoubleUnaryOperator>("Central difference", centralDifferenceApproxError));

		// Construct the plot
		Plot2D plot = new Plot2D(xMin, xMax, numberOfPoints, plotFunctions);
		try {
			plot.setYAxisNumberFormat(new DecimalFormat("0.0"))
			.setTitle("Finite Difference Derivative of " + functionName + " at x = " + evaluationPoint)
			.setXAxisLabel("scale = log\u2081\u2080(h)  (h = 10^{scale})")
			.setYAxisLabel("Approximation error")
			.setIsLegendVisible(true)
			.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
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

		double valueUpShift = function.applyAsDouble(evaluationPoint + shift);
		double value = function.applyAsDouble(evaluationPoint);

		return (valueUpShift - value) / shift;
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

		double value = function.applyAsDouble(evaluationPoint);
		double valueDownShift = function.applyAsDouble(evaluationPoint - shift);

		return (value - valueDownShift) / shift;
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

		double valueUpShift = function.applyAsDouble(evaluationPoint + shift);
		double valueDownShift = function.applyAsDouble(evaluationPoint - shift);

		return (valueUpShift - valueDownShift) / shift / 2;
	}

}
