/**
 *
 */
package info.quantlab.tutorium.session05;

import java.util.List;

/**
 * Calculates prime numbers.
 *
 * @author Roland Bachl
 *
 */
public interface PrimeNumbers {

	/**
	 * Checks for all numbers between 0 (inclusive) and maxNumber (exclusive) whether they
	 * are prime and returns a list of all primes found.
	 *
	 * @param maxNumber
	 * @return list of primes
	 */
	default List<Long> getPrimes(long maxNumber) {
		return getPrimes(0, maxNumber);
	}

	/**
	 * Checks for all numbers between minNumber (inclusive) and maxNumber (exclusive) whether they
	 * are prime and returns a list of all primes found.
	 *
	 * @param minNumber
	 * @param maxNumber
	 * @return list of primes
	 */
	List<Long> getPrimes(long minNumber, long maxNumber);

	/**
	 * Checks for all positive numbers whether they are prime. This method runs indefinitely
	 * unless the user cancels the operation. After cancellation returns the list of found primes.
	 *
	 * @return list of primes
	 */
	List<Long> getPrimes();
}
