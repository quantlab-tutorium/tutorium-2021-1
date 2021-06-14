/**
 *
 */
package info.quantlab.tutorium.session08;

import java.util.stream.DoubleStream;

import net.finmath.montecarlo.BrownianMotion;
import net.finmath.montecarlo.BrownianMotionFromMersenneRandomNumbers;
import net.finmath.montecarlo.BrownianMotionFromRandomNumberGenerator;
import net.finmath.montecarlo.RandomVariableFactory;
import net.finmath.montecarlo.RandomVariableFromArrayFactory;
import net.finmath.randomnumbers.MersenneTwister;
import net.finmath.randomnumbers.RandomNumberGenerator;
import net.finmath.time.TimeDiscretization;
import net.finmath.time.TimeDiscretizationFromArray;

/**
 * @author Roland Bachl
 *
 */
public class Demo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// long start = System.currentTimeMillis();
		final int seed = 3141;
		final int numberOfFactors = 1;
		final int numberOfPaths = 1000000;
		final int numberOfTimeSteps = 10;
		final double deltaT = 1.0;

		final RandomVariableFactory randomVariableFactory = new RandomVariableFromArrayFactory();
		final TimeDiscretization timeDiscretization = new TimeDiscretizationFromArray(0, numberOfTimeSteps, deltaT);

		final BrownianMotion brownianMotion = new BrownianMotionFromMersenneRandomNumbers(timeDiscretization,
				numberOfFactors, numberOfPaths, seed, randomVariableFactory);

		RandomNumberGenerator randomNumberGenerator = new RandomNumberGenerator() {

			private static final long serialVersionUID = 1L;
			private MersenneTwister internal = new MersenneTwister(seed);

			@Override
			public double[] getNext() {
				return DoubleStream.generate(() -> internal.nextDoubleFast()).limit(numberOfTimeSteps * numberOfFactors)
						.toArray();
			}

			@Override
			public int getDimension() {
				return numberOfTimeSteps * numberOfFactors;
			}
		};

		final BrownianMotion brownianMotion2 = new BrownianMotionFromRandomNumberGenerator(timeDiscretization,
				numberOfFactors, numberOfPaths, randomNumberGenerator, randomVariableFactory);

		// System.out.println(System.currentTimeMillis() - start);
		//		double[] val = brownianMotion.getBrownianIncrement(5, 0).getRealizations();
		double val = brownianMotion.getBrownianIncrement(5, 0).getAverage();
		double val2 = brownianMotion2.getBrownianIncrement(5, 0).getAverage();

		// System.out.println(System.currentTimeMillis() - start);
		System.out.println(val);
		System.out.println(val2);
	}

}
