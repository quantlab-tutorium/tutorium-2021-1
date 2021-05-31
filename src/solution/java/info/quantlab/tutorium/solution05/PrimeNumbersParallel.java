/**
 *
 */
package info.quantlab.tutorium.solution05;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import info.quantlab.tutorium.session05.PrimeNumbers;

/**
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

	public static void main(String[] args) {

		long time = System.currentTimeMillis();
		System.out.println("Total number of primes: " +
				getInstance().getPrimes().size());
		System.out.format("Time ellapsed: %4ds\n", (System.currentTimeMillis()-time) / 1000);
	}

	@Override
	public List<Long> getPrimes(long minNumber, long maxNumber) {
		return LongStream.range(minNumber, maxNumber)
				.parallel()
				.filter(n -> isPrime(n))
				.boxed()
				.collect(Collectors.toList());
	}

	public static boolean isPrime(long n) {

		if (n % 2L == 0L) {
			return n == 2L;
		}

		for (long i = 3L; i * i <= n; i += 2L) {
			if (n % i == 0L) {
				return false;
			}
		}

		if (n == 1L) {
			return false;
		}

		return true;
	}

	@Override
	public List<Long> getPrimes() {
		//main is dispatcher
		ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(
				Math.max(Runtime.getRuntime().availableProcessors() - 2, 1));

		//Check for input
		Thread listener = new Thread(new Runnable() {

			@Override
			public void run() {

				try {
					System.in.read();
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					// The finally block stops the executor either when System.in.read returns or
					// in case an error is thrown.
					executor.shutdown();
				}
			}
		}, "Listener");

		listener.start();

		//Check primes
		long current = 2;
		List<Long> primes = Collections.synchronizedList(new ArrayList<Long>());

		while(! executor.isShutdown()) {
			if(executor.getQueue().size() < 1000) {
				long n = current++;
				try {
					executor.submit(new Runnable() {

						@Override
						public void run() {
							if(isPrime(n)) {
								primes.add(n);
								System.out.println("Fetching primes, hit enter to stop. "
										+ "Latest prime: " + n);
							}
						}
					});
				} catch (RejectedExecutionException e) { }

			}
		}
		try {
			executor.awaitTermination(10, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			System.out.println("Final primes took longer than 10 seconds to compute.");
		}
		return primes;
	}
}
