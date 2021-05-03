package info.quantlab.tutorium.session03;

import static info.quantlab.tutorium.session03.ContinuousExtension.evaluateFunction;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class ContinuousExtensionTest {

	@Test
	void testEvaluateFunction() {
		double[] arguments = new double[] { -2 * Math.PI, -Math.PI, 0, Math.PI, 2 * Math.PI };

		for (double arg : arguments) {
			assertEquals(0, evaluateFunction(arg), 1E-16);
		}
	}

}
