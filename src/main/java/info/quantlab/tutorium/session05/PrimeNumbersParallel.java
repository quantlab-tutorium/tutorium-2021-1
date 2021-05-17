/**
 *
 */
package info.quantlab.tutorium.session05;

import java.util.List;

/**
 * Calculates prime numbers in parallel.
 *
 * @author Roland Bachl
 *
 */
public class PrimeNumbersParallel implements PrimeNumbers {

	//Singleton pattern to enforce there is only ever one instance of PrimeNumbersParallel created.
	/**
	 * Cached instance of PrimeNumberParallel
	 */
	private static final PrimeNumbers instance = new PrimeNumbersParallel();

	/**
	 * Private constructor.
	 */
	private PrimeNumbersParallel() {
		super();
	}

	/**
	 * @return The instance of PrimeNumberParallel.
	 */
	public static PrimeNumbers getInstance() {
		return instance;
	}

	@Override
	public List<Long> getPrimes(long minNumber, long maxNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Long> getPrimes() {
		// TODO Auto-generated method stub
		return null;
	}

}
