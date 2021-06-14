/**
 *
 */
package info.quantlab.tutorium.session08;

import net.finmath.randomnumbers.RandomNumberGenerator;

/**
 * An implementation of {@link SimplisticProcess} built from a {@link RandomNumberGenerator}. Internally values are
 * stored as increments, but access through the interface only allows for accumulated values.
 *
 * @author Roland Bachl
 *
 */
public class SimplisticProcessFromGenerator implements SimplisticProcess {

	/**
	 * Construct a general process from the given generator and increment transform.
	 *
	 * @param times     The time points of the discretization.
	 * @param generator The generator of uniform random numbers.
	 */
	public SimplisticProcessFromGenerator(double[] times, RandomNumberGenerator generator) {
		super();
		// TODO Please use a constructor of this type. You can write the class either *eager* or *lazy*.
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
		// TODO Bonus. An additional transformation allows for a general implementation for multiple types of processes.
	}

	@Override
	public int getMaxIndex() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getTimeAtIndex(int index) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getValueAtIndex(int index) {
		// TODO Auto-generated method stub
		return 0;
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

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
}
