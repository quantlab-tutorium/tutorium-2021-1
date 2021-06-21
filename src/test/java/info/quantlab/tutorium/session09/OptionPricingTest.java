/**
 *
 */
package info.quantlab.tutorium.session09;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import net.finmath.exception.CalculationException;

/**
 * @author Roland Bachl
 *
 */
public class OptionPricingTest {

	private static info.quantlab.tutorium.session09.OptionPricing testPricing;
	private static info.quantlab.tutorium.solution09.OptionPricing checkPricing;

	private static final double testAccuracy			= 1e-10;
	private static final int	seed					= 3141;

	// Model properties
	private static final double	initialValue			= 1.0;
	private static final double	riskFreeRate			= 0.05;
	private static final double	volatility				= 0.30;

	// Process discretization properties
	private static final int		numberOfPaths		= 10000;
	private static final int		numberOfTimeSteps	= 10;
	private static final double		deltaT				= 0.5;

	// Option parameters to be tested
	private static final double optionMaturity			= 1;
	private static final double optionStrike			= 1.2;

	@BeforeAll
	public static void setup() {
		testPricing = new info.quantlab.tutorium.session09.OptionPricing(
				seed,
				initialValue,
				riskFreeRate,
				volatility,
				numberOfPaths,
				numberOfTimeSteps,
				deltaT);
		checkPricing = new info.quantlab.tutorium.solution09.OptionPricing(
				seed,
				initialValue,
				riskFreeRate,
				volatility,
				numberOfPaths,
				numberOfTimeSteps,
				deltaT);
	}

	@Test
	public void testBlackScholesCallOptionValueAnalytic() {

		double test = testPricing.getBlackScholesCallOptionValueAnalytic(
				optionMaturity, optionStrike);
		double check = checkPricing.getBlackScholesCallOptionValueAnalytic(
				optionMaturity, optionStrike);

		assertEquals(check, test, testAccuracy);
	}

	@Test
	public void testBlackScholesDigitalOptionValueAnalytic() {

		double test = testPricing.getBlackScholesDigitalOptionValueAnalytic(
				optionMaturity, optionStrike);
		double check = checkPricing.getBlackScholesDigitalOptionValueAnalytic(
				optionMaturity, optionStrike);

		assertEquals(check, test, testAccuracy);
	}

	@Test
	public void testBlackScholesCallOptionValue() throws CalculationException {

		double test = testPricing.getBlackScholesCallOptionValue(
				optionMaturity, optionStrike);
		double check = checkPricing.getBlackScholesCallOptionValue(
				optionMaturity, optionStrike);

		assertEquals(check, test, testAccuracy);
	}

	@Test
	public void testBlackScholesDigitalOptionValue() throws CalculationException {

		double test = testPricing.getBlackScholesDigitalOptionValue(
				optionMaturity, optionStrike);
		double check = checkPricing.getBlackScholesDigitalOptionValue(
				optionMaturity, optionStrike);

		assertEquals(check, test, testAccuracy);
	}

	@Test
	public void testBachelierCallOptionValue() throws CalculationException {

		double test = testPricing.getBachelierCallOptionValue(
				optionMaturity, optionStrike);
		double check = checkPricing.getBachelierCallOptionValue(
				optionMaturity, optionStrike);

		assertEquals(check, test, testAccuracy);
	}

	@Test
	public void testBachelierDigitalOptionValue() throws CalculationException {

		double test = testPricing.getBachelierDigitalOptionValue(
				optionMaturity, optionStrike);
		double check = checkPricing.getBachelierDigitalOptionValue(
				optionMaturity, optionStrike);

		assertEquals(check, test, testAccuracy);
	}

	@Test
	public void testBlackScholesStraddleOptionValue() throws CalculationException {

		double test = testPricing.getBlackScholesStraddleOptionValue(
				optionMaturity, optionStrike);
		double check = checkPricing.getBlackScholesStraddleOptionValue(
				optionMaturity, optionStrike);

		assertEquals(check, test, testAccuracy);
	}

	@Test
	public void testBachelierStraddleOptionValue() throws CalculationException {

		double test = testPricing.getBachelierStraddleOptionValue(
				optionMaturity, optionStrike);
		double check = checkPricing.getBachelierStraddleOptionValue(
				optionMaturity, optionStrike);

		assertEquals(check, test, testAccuracy);
	}
}
