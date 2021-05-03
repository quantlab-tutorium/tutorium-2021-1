/**
 * 
 */
package info.quantlab.tutorium.solution02;

/**
 * From Wikipedia:
 * In mathematics, the Fibonacci numbers, commonly denoted Fn, form a sequence, called the Fibonacci
 * sequence, such that each number is the sum of the two preceding ones, starting from 0 and 1.
 *
 * That is,
 * F_{0}=0, F_{1}=1,
 * F_{n}=F_{n-1}+F_{n-2}, for n larger 1.
 *
 * The beginning of the sequence is thus:
 * 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, \ldots
 *
 * @author Roland Bachl
 *
 */
public class Fibonacci {

	/**
	 * Sums all even numbers in the Fibonacci sequence up to a given limit.
	 *
	 * @param limit The largest number to be considered.
	 * @return The sum of even Fibonacci numbers smaller or equal to the limit.
	 */
	public static int sumEven(int limit) {
		int previousFibonacci = 1;
		int currentFibonacci = 2;
		int evenFibonacciSum = 0;

		while(currentFibonacci <= limit) {
			if (currentFibonacci % 2 == 0) {
				evenFibonacciSum += currentFibonacci;
			}
			int newFibonacci = currentFibonacci + previousFibonacci;
			previousFibonacci = currentFibonacci;
			currentFibonacci = newFibonacci;
		}

		return evenFibonacciSum;
	}
}
