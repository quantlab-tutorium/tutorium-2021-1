/**
 *
 */
package info.quantlab.tutorium.solution08;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

import info.quantlab.tutorium.session08.SimplisticProcess;
import net.finmath.functions.NormalDistribution;
import net.finmath.functions.PoissonDistribution;
import net.finmath.randomnumbers.RandomNumberGenerator;

/**
 * An implementation of {@link SimplisticProcess} built from a {@link RandomNumberGenerator}. Internally values are
 * stored as increments, but access through the interface only allows for accumulated values.
 *
 * @author Roland Bachl
 *
 */
public class SimplisticProcessFromGenerator implements SimplisticProcess {

	private final double[] timeIncrements;
	private final double[] valueIncrements;

	/**
	 * Construct a Brownian motion from the given random number generator.
	 *
	 * @param times     The time points of the discretization.
	 * @param generator The generator of uniform random numbers.
	 */
	public SimplisticProcessFromGenerator(double[] times, RandomNumberGenerator generator) {
		this(times, generator, /* Transform defaults to normal transform */
				(timeIncrement, randomNumbers) -> NormalDistribution.inverseCumulativeDistribution(randomNumbers[0])
						* Math.sqrt(timeIncrement));
	}

	/**
	 * Construct a general process from the given generator and increment transform.
	 *
	 * @param times     The time points of the discretization.
	 * @param generator The generator of uniform random numbers.
	 * @param transform The tranform to turn the output of the generator into the proper increments.
	 */
	public SimplisticProcessFromGenerator(double[] times, RandomNumberGenerator generator,
			IncrementTransform transform) {
		super();

		timeIncrements = new double[times.length - 1];
		valueIncrements = new double[timeIncrements.length];

		for (int i = 0; i < timeIncrements.length; i++) {
			timeIncrements[i] = times[i + 1] - times[i];
			valueIncrements[i] = transform.apply(timeIncrements[i], generator.getNext());
		}
	}

	@Override
	public int getMaxIndex() {
		return timeIncrements.length + 1;
	}

	@Override
	public double getTimeAtIndex(int index) {
		return Arrays.stream(timeIncrements).limit(index).sum();
	}

	@Override
	public double getValueAtIndex(int index) {
		return Arrays.stream(valueIncrements).limit(index).sum();
	}

	/**
	 * Functional interface for a method to transform uniform random numbers into increments of a process.
	 *
	 * @author Roland Bachl
	 *
	 */
	public interface IncrementTransform {

		/**
		 * Apply the transform.
		 *
		 * @param timeIncrement The current time increment.
		 * @param randomNumbers The uniform random numbers needed for the increment.
		 * @return The increment of the value.
		 */
		double apply(double timeIncrement, double... randomNumbers);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		int maxTime = 10;

		double[] times = IntStream.range(0, maxTime).mapToDouble(i -> i).toArray();

		RandomNumberGenerator singleGenerator = new RandomNumberGenerator() {
			private static final long serialVersionUID = -7331565156917400117L;

			@Override
			public double[] getNext() {
				return new double[] { ThreadLocalRandom.current().nextDouble() };
			}

			@Override
			public int getDimension() {
				return 1;
			}
		};

		RandomNumberGenerator dualGenerator = new RandomNumberGenerator() {
			private static final long serialVersionUID = -2029672988999768040L;

			@Override
			public double[] getNext() {
				return ThreadLocalRandom.current().doubles(2).toArray();
			}

			@Override
			public int getDimension() {
				return 2;
			}
		};

		IncrementTransform normalTransform = (timeIncrement, randomNumbers)
				-> NormalDistribution.inverseCumulativeDistribution(randomNumbers[0]) * Math.sqrt(timeIncrement);
		IncrementTransform poissonTransform = (timeIncrement, randomNumbers)
				-> new PoissonDistribution(timeIncrement).inverseCumulativeDistribution(randomNumbers[0])
					* (randomNumbers[1] < .5 ? 1 : -1);

		SimplisticProcess brownianMotion = new SimplisticProcessFromGenerator(times, singleGenerator, normalTransform);
		SimplisticProcess poissonProcess = new SimplisticProcessFromGenerator(times, dualGenerator, poissonTransform);

		System.out.println("index\t\tBrownian motion\t\tJump process");
		for (int i = 0; i < maxTime; i++) {

			System.out.format("%d\t\t%f\t\t%f\n", i, brownianMotion.getValueAtIndex(i),
					poissonProcess.getValueAtIndex(i));
		}
	}

}
