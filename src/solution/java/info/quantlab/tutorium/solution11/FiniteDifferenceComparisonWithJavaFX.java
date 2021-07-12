package info.quantlab.tutorium.solution11;

import java.util.function.DoubleUnaryOperator;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

/**
 * Compare the errors of forward, backward and central finite difference methods for the first derivative of given
 * mathematical functions.
 *
 * @author Roland Bachl
 *
 */
public class FiniteDifferenceComparisonWithJavaFX extends Application {

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

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(@SuppressWarnings("exports") Stage primaryStage) {

		// Organizing parameters
		DoubleUnaryOperator[] functions = new DoubleUnaryOperator[] { function1, function2, function3 };
		DoubleUnaryOperator[] derivatives = new DoubleUnaryOperator[] { derivative1, derivative2, derivative3 };
		String[] functionNames = new String[] { function1Name, function2Name, function3Name };

		// Charts for each function are collected in a single pane
		HBox pane = new HBox();

		// Loop over functions
		for (int f = 0; f < functions.length; f++) {

			// Prepare axis and chart
			final NumberAxis xAxis = new NumberAxis(xMin, xMax, 0.5);
			final NumberAxis yAxis = new NumberAxis();
			xAxis.setLabel("scale = log\u2081\u2080(h)  (h = 10^{scale})");
			yAxis.setLabel("Approximation error");

			final LineChart<Number, Number> chart = new LineChart<Number, Number>(xAxis, yAxis);
			chart.setTitle("Finite Difference Derivative of " + functionNames[f] + " at x = " + evaluationPoint);
			chart.setPrefSize(600, 500);
			chart.setCreateSymbols(false); // Turn off dots in the series

			// Create data series
			Series<Number, Number> forwardSeries = new Series<Number, Number>();
			Series<Number, Number> backwardSeries = new Series<Number, Number>();
			Series<Number, Number> centralSeries = new Series<Number, Number>();
			forwardSeries.setName("Forward difference");
			backwardSeries.setName("Backward difference");
			centralSeries.setName("Central difference");

			// Fill in data
			double analyticDerivative = derivatives[f].applyAsDouble(evaluationPoint);
			double deltaScale = (xMax - xMin) / numberOfPoints;
			for (int i = 0; i < numberOfPoints; i++) {
				double scale = xMin + i * deltaScale;
				double shift = scaleTransform.applyAsDouble(scale);
				double forwardError = forwardDifference(functions[f], evaluationPoint, shift) - analyticDerivative;
				double backwardError = backwardDifference(functions[f], evaluationPoint, shift) - analyticDerivative;
				double centralError = centralDifference(functions[f], evaluationPoint, shift) - analyticDerivative;

				forwardSeries.getData().add(new Data<Number, Number>(scale, forwardError));
				backwardSeries.getData().add(new Data<Number, Number>(scale, backwardError));
				centralSeries.getData().add(new Data<Number, Number>(scale, centralError));
			}

			// Add series to chart
			@SuppressWarnings("unchecked")
			Series<Number, Number>[] series = new Series[] { forwardSeries, backwardSeries, centralSeries };
			chart.getData().addAll(series);
			pane.getChildren().add(chart);
		}

		// Make sure charts are allowed to resize horizontally
		for (Node chart : pane.getChildren()) {
			HBox.setHgrow(chart, Priority.ALWAYS);
		}

		// Build scene and stage
		Scene scene = new Scene(pane, 1800, 500);

		primaryStage.setTitle("Finite Difference error comparison");

		primaryStage.setScene(scene);
		primaryStage.show();
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
