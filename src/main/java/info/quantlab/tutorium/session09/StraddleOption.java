package info.quantlab.tutorium.session09;

import java.util.HashMap;
import java.util.Map;

import net.finmath.exception.CalculationException;
import net.finmath.modelling.Model;
import net.finmath.montecarlo.assetderivativevaluation.AssetModelMonteCarloSimulationModel;
import net.finmath.montecarlo.assetderivativevaluation.products.AbstractAssetMonteCarloProduct;
import net.finmath.stochastic.RandomVariable;

/**
 * Implements the valuation of a straddle option on a single asset.
 *
 * Given a model for an asset <i>S</i>, the straddle option with strike <i>K</i>, maturity <i>T</i>
 * pays the combination of a European call and put option of the same strike and maturity, i.e.
 * <br>
 * 	<i>V(T) = max(S(T) - K , 0) + max(K-S(T), 0)</i> in <i>T</i>.
 * <br>
 *
 * The <code>getValue</code> method of this class will return the random variable
 * <i>N(t) * V(T) / N(T)</i>, where <i>N</i> is the numeraire provided by the model.
 * If <i>N(t)</i> is deterministic, calling <code>getAverage</code> on this random variable will
 * result in the value. Otherwise a conditional expectation has to be applied.
 *
 * @author Roland Bachl
 */
public class StraddleOption extends AbstractAssetMonteCarloProduct {

	private final double maturity;
	private final double strike;
	private final Integer underlyingIndex = 0;

	/**
	 * Construct a product representing an straddle option on an asset S
	 * (where S the asset with index 0 from the model - single asset case).
	 *
	 * @param maturity The maturity T in the option payoff
	 * @param strike The strike K in the option payoff
	 */
	public StraddleOption(final double maturity, final double strike) {
		super();
		this.maturity			= maturity;
		this.strike				= strike;
	}

	@Override
	public RandomVariable getValue(final double evaluationTime,
			final AssetModelMonteCarloSimulationModel model) throws CalculationException {
		// Get underlying and numeraire

		// Get S(T)
		//TODO
		RandomVariable values		= null;


		// Discounting...
		final RandomVariable numeraireAtMaturity		= model.getNumeraire(maturity);
		final RandomVariable monteCarloWeights		= model.getMonteCarloWeights(maturity);
		values = values.div(numeraireAtMaturity).mult(monteCarloWeights);

		// ...to evaluation time.
		final RandomVariable	numeraireAtEvalTime					= model.getNumeraire(
				evaluationTime);
		final RandomVariable	monteCarloProbabilitiesAtEvalTime	= model.getMonteCarloWeights(
				evaluationTime);
		values = values.mult(numeraireAtEvalTime).div(monteCarloProbabilitiesAtEvalTime);

		return values;
	}

	@Override
	public Map<String, Object> getValues(final double evaluationTime, final Model model) {
		final Map<String, Object>  result = new HashMap<>();

		try {
			final double value = getValue(evaluationTime,
					(AssetModelMonteCarloSimulationModel) model).getAverage();
			result.put("value", value);
		} catch (final CalculationException e) {
			result.put("exception", e);
		}

		return result;
	}

	@Override
	public String toString() {
		return "StraddleOption [maturity=" + maturity + ", strike=" + strike
				+ ", underlyingIndex=" + underlyingIndex + "]";
	}
}
