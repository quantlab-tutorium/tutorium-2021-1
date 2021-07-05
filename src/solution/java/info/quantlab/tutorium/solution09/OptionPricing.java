/**
 *
 */
package info.quantlab.tutorium.solution09;

import java.text.DecimalFormat;

import net.finmath.exception.CalculationException;
import net.finmath.functions.AnalyticFormulas;
import net.finmath.montecarlo.BrownianMotionFromMersenneRandomNumbers;
import net.finmath.montecarlo.RandomVariableFactory;
import net.finmath.montecarlo.RandomVariableFromArrayFactory;
import net.finmath.montecarlo.assetderivativevaluation.AssetModelMonteCarloSimulationModel;
import net.finmath.montecarlo.assetderivativevaluation.MonteCarloAssetModel;
import net.finmath.montecarlo.assetderivativevaluation.models.BachelierModel;
import net.finmath.montecarlo.assetderivativevaluation.models.BlackScholesModel;
import net.finmath.montecarlo.assetderivativevaluation.products.AbstractAssetMonteCarloProduct;
import net.finmath.montecarlo.assetderivativevaluation.products.DigitalOption;
import net.finmath.montecarlo.assetderivativevaluation.products.EuropeanOption;
import net.finmath.montecarlo.model.ProcessModel;
import net.finmath.montecarlo.process.EulerSchemeFromProcessModel;
import net.finmath.montecarlo.process.MonteCarloProcessFromProcessModel;
import net.finmath.time.TimeDiscretization;
import net.finmath.time.TimeDiscretizationFromArray;

/**
 * Example class of how to value asset derivatives using the finmath library.
 *
 * @author Roland Bachl
 *
 */
public class OptionPricing {

	private final long	seed;

	// Model properties
	private final double	initialValue;
	private final double	riskFreeRate;
	private final double	volatility;	//used either as normal (Bachelier) or log-normal (BS)

	// Process discretization properties
	private final int		numberOfPaths;
	private final int		numberOfTimeSteps;
	private final double	deltaT;

	private static final RandomVariableFactory randomVariableFactory =
			new RandomVariableFromArrayFactory();

	private transient AssetModelMonteCarloSimulationModel bsModel;
	private transient AssetModelMonteCarloSimulationModel baModel;

	// Java DecimalFormat for our output format
	private static final DecimalFormat numberFormatValue		= new DecimalFormat("0.00E00");


	public OptionPricing(long seed, double initialValue, double riskFreeRate, double volatility,
			int numberOfPaths, int numberOfTimeSteps, double deltaT) {
		super();
		this.seed = seed;
		this.initialValue = initialValue;
		this.riskFreeRate = riskFreeRate;
		this.volatility = volatility;
		this.numberOfPaths = numberOfPaths;
		this.numberOfTimeSteps = numberOfTimeSteps;
		this.deltaT = deltaT;
	}

	/**
	 * @param args
	 * @throws CalculationException
	 */
	public static void main(String[] args) throws CalculationException {

		OptionPricing op = new OptionPricing(
				3141,	//		seed,
				1,		//		initialValue,
				0.05,	//		riskFreeRate,
				0.3,	//		volatility,
				10000,	//		numberOfPaths,
				10,		//		numberOfTimeSteps,
				0.5);	//		deltaT);


		double optionMaturity	= 1;
		double optionStrike		= 1.2;

		double bsCallOptionValueAnalytic	= op.getBlackScholesCallOptionValueAnalytic(
				optionMaturity, optionStrike);
		double bsDigitalOptionValueAnalytic	= op.getBlackScholesDigitalOptionValueAnalytic(
				optionMaturity, optionStrike);
		double bsCallOptionValue	= op.getBlackScholesCallOptionValue(optionMaturity,
				optionStrike);
		double bsDigitalOptionValue = op.getBlackScholesDigitalOptionValue(optionMaturity,
				optionStrike);
		double baCallOptionValue	= op.getBachelierCallOptionValue(optionMaturity,
				optionStrike);
		double baDigitalOptionValue	= op.getBachelierDigitalOptionValue(optionMaturity,
				optionStrike);
		double bsStraddleOptionValue = op.getBlackScholesStraddleOptionValue(optionMaturity,
				optionStrike);
		double baStraddleOptionValue = op.getBachelierStraddleOptionValue(optionMaturity,
				optionStrike);

		System.out.println("The value of a European call option under the Black-Scholes model"
				+ " (analytic):\t" + numberFormatValue.format(bsCallOptionValueAnalytic));
		System.out.println("The value of a digital option under the Black-Scholes model (analytic):"
				+ "\t\t" + numberFormatValue.format(bsDigitalOptionValueAnalytic));
		System.out.println("The value of a European call option under the Black-Scholes model:"
				+ "\t\t"	+ numberFormatValue.format(bsCallOptionValue));
		System.out.println("The value of a digital option under the Black-Scholes model:\t\t\t"
				+ numberFormatValue.format(bsDigitalOptionValue));
		System.out.println("The value of a European call option under the Bachelier model:\t\t\t"
				+ numberFormatValue.format(baCallOptionValue));
		System.out.println("The value of a digital option under the Bachelier model:\t\t\t"
				+ numberFormatValue.format(baDigitalOptionValue));
		System.out.println("The value of a straddle option under the Black-Scholes model:\t\t\t"
				+ numberFormatValue.format(bsStraddleOptionValue));
		System.out.println("The value of a straddle option under the Bachelier model:\t\t\t"
				+ numberFormatValue.format(baStraddleOptionValue));
	}

	/**
	 * Value a European call option under the Black-Scholes model.
	 *
	 * @param optionMaturity
	 * @param optionStrike
	 * @return The value of the option
	 */
	public double getBlackScholesCallOptionValueAnalytic(double optionMaturity,
			double optionStrike) {
		return AnalyticFormulas.blackScholesOptionValue(
				initialValue, riskFreeRate, volatility, optionMaturity, optionStrike);
	}

	/**
	 * Value a digital option under the Black-Scholes model.
	 *
	 * @param optionMaturity
	 * @param optionStrike
	 * @return The value of the option
	 */
	public double getBlackScholesDigitalOptionValueAnalytic(double optionMaturity,
			double optionStrike) {
		return AnalyticFormulas.blackScholesDigitalOptionValue(
				initialValue, riskFreeRate, volatility, optionMaturity, optionStrike);
	}

	/**
	 * Value a European call option under the Black-Scholes model.
	 *
	 * @param optionMaturity
	 * @param optionStrike
	 * @return The value of the option
	 * @throws CalculationException
	 */
	public double getBlackScholesCallOptionValue(double optionMaturity,
			double optionStrike) throws CalculationException {

		// Create the valuation model
		final AssetModelMonteCarloSimulationModel model = getBlackScholesModel();

		// Create a product
		final AbstractAssetMonteCarloProduct option	= new EuropeanOption(
				optionMaturity, optionStrike);

		// Value the product with Monte Carlo
		final double valueMonteCarlo	= option.getValue(model);

		return valueMonteCarlo;
	}

	/**
	 * Value a digital option under the Black-Scholes model.
	 *
	 * @param optionMaturity
	 * @param optionStrike
	 * @return The value of the option
	 * @throws CalculationException
	 */
	public double getBlackScholesDigitalOptionValue(double optionMaturity,
			double optionStrike) throws CalculationException {

		// Create the valuation model
		final AssetModelMonteCarloSimulationModel model = getBlackScholesModel();

		// Create a product
		final AbstractAssetMonteCarloProduct option	= new DigitalOption(
				optionMaturity, optionStrike);

		// Value the product with Monte Carlo
		final double valueMonteCarlo	= option.getValue(model);

		return valueMonteCarlo;
	}

	/**
	 * Value a European call option under the Bachelier model.
	 *
	 * @param optionMaturity
	 * @param optionStrike
	 * @return The value of the option
	 * @throws CalculationException
	 */
	public double getBachelierCallOptionValue(double optionMaturity,
			double optionStrike) throws CalculationException {

		// Create the valuation model
		final AssetModelMonteCarloSimulationModel model = getBachelierModel();

		// Create a product
		final AbstractAssetMonteCarloProduct option	= new EuropeanOption(
				optionMaturity, optionStrike);

		// Value the product with Monte Carlo
		final double valueMonteCarlo	= option.getValue(model);

		return valueMonteCarlo;
	}

	/**
	 * Value a digital option under the Bachelier model.
	 *
	 * @param optionMaturity
	 * @param optionStrike
	 * @return The value of the option
	 * @throws CalculationException
	 */
	public double getBachelierDigitalOptionValue(double optionMaturity,
			double optionStrike) throws CalculationException {

		// Create the valuation model
		final AssetModelMonteCarloSimulationModel model = getBachelierModel();

		// Create a product
		final AbstractAssetMonteCarloProduct option	= new DigitalOption(
				optionMaturity, optionStrike);

		// Value the product with Monte Carlo
		final double valueMonteCarlo	= option.getValue(model);

		return valueMonteCarlo;
	}

	/**
	 * Value a straddle option under the Black-Scholes model.
	 *
	 * @param optionMaturity
	 * @param optionStrike
	 * @return The value of the option
	 * @throws CalculationException
	 */
	public double getBlackScholesStraddleOptionValue(double optionMaturity,
			double optionStrike) throws CalculationException {

		// Create the valuation model (see <code>getModel</code>)
		final AssetModelMonteCarloSimulationModel model = getBlackScholesModel();

		// Create a product
		final AbstractAssetMonteCarloProduct option	= new StraddleOption(
				optionMaturity, optionStrike);

		// Value the product with Monte Carlo
		final double valueMonteCarlo	= option.getValue(model);

		return valueMonteCarlo;
	}

	/**
	 * Value a straddle option under the Bachelier model.
	 *
	 * @param optionMaturity
	 * @param optionStrike
	 * @return The value of the option
	 * @throws CalculationException
	 */
	public double getBachelierStraddleOptionValue(double optionMaturity,
			double optionStrike) throws CalculationException {

		// Create the valuation model (see <code>getModel</code>)
		final AssetModelMonteCarloSimulationModel model = getBachelierModel();

		// Create a product
		final AbstractAssetMonteCarloProduct option	= new StraddleOption(
				optionMaturity, optionStrike);

		// Value the product with Monte Carlo
		final double valueMonteCarlo	= option.getValue(model);

		return valueMonteCarlo;
	}

	/**
	 * Build a Black-Scholes model to use for Monte-Carlo simulation.
	 *
	 * @return the finished model
	 */
	private AssetModelMonteCarloSimulationModel getBlackScholesModel()
	{
		/*
		 * Lazy initialize the model
		 */
		if(bsModel == null) {
			// Create the time discretization
			final TimeDiscretization timeDiscretization = new TimeDiscretizationFromArray(0.0,
					numberOfTimeSteps, deltaT);

			// Create the model
			final ProcessModel blackScholesModel = new BlackScholesModel(initialValue, riskFreeRate,
					volatility);

			// Create a corresponding MC process
			final MonteCarloProcessFromProcessModel process = new EulerSchemeFromProcessModel(
					blackScholesModel, new BrownianMotionFromMersenneRandomNumbers(timeDiscretization,
							1 /* numberOfFactors */, numberOfPaths, (int) seed,
							randomVariableFactory));

			// Build simulation model
			bsModel = new MonteCarloAssetModel(process);
		}


		return bsModel;
	}

	/**
	 * Build a Bachelier model to use for Monte-Carlo simulation.
	 *
	 * @return the finished model
	 */
	private AssetModelMonteCarloSimulationModel getBachelierModel()
	{
		/*
		 * Lazy initialize the model
		 */
		if(baModel == null) {
			// Create the time discretization
			final TimeDiscretization timeDiscretization = new TimeDiscretizationFromArray(0.0,
					numberOfTimeSteps, deltaT);

			// Create the model
			final ProcessModel bachelierModel = new BachelierModel(initialValue, riskFreeRate,
					volatility);

			// Create a corresponding MC process
			final MonteCarloProcessFromProcessModel process = new EulerSchemeFromProcessModel(
					bachelierModel, new BrownianMotionFromMersenneRandomNumbers(timeDiscretization,
							1 /* numberOfFactors */, numberOfPaths, (int) seed,
							randomVariableFactory));

			// Build simulation model
			baModel = new MonteCarloAssetModel(process);
		}

		return baModel;
	}

}
