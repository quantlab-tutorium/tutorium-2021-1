/**
 *
 */
package info.quantlab.tutorium.session05;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * No exercise here, just something to look at.
 *
 * @author Roland Bachl
 *
 */
public class ParallelStreamDemo {

	private static int length = (int) 1e4;

	/**
	 * @param args
	 * @throws IOException
	 * @throws ExecutionException
	 * @throws InterruptedException
	 */
	public static void main(String[] args)
			throws IOException, InterruptedException, ExecutionException {

		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				System.out.println("Hello");
			}
		});
		System.in.read(new byte[2]);

		thread.start();
		System.in.read(new byte[2]);

		ExecutorService executor = Executors.newSingleThreadExecutor();
		executor.submit(() -> System.out.println("Hello"));
		System.in.read(new byte[2]);
		executor.shutdown();



		List<String> list = List.of("H", "i", "e", "l", "i", "l", "o");
		Stream<String> stream = list.stream();

		stream = stream.filter(s -> !s.equals("i"));
		stream = stream.map(s -> s.toUpperCase());

		String output = stream.collect(Collectors.joining());

		System.out.println(output);
		System.in.read(new byte[2]);

		System.out.println(list.stream()
				.filter(s -> !s.equals("i"))
				.map(s -> s.toUpperCase())
				.collect(Collectors.joining()));
		System.in.read(new byte[2]);



		System.out.println("Sequential");
		Counter count1 = new Counter();
		int[] ints = IntStream.range(0, length).map(i -> count1.val++).toArray();
		System.out.println(Arrays.toString(ints));
		System.in.read(new byte[2]);

		System.out.println("Parallel");
		Counter count2 = new Counter();
		ints = IntStream.range(0, length).parallel().map(i -> count2.val++).toArray();
		System.out.println(Arrays.toString(ints));
		System.in.read(new byte[2]);

		System.out.println("Parallel sorted");
		System.out.println(Arrays.toString(Arrays.stream(ints).sorted().toArray()));
		System.out.println("Unique entries: " + Arrays.stream(ints).distinct().count());
		System.in.read(new byte[2]);


		System.out.println("Synchronized parallel sorted");
		SynchCounter count3 = new SynchCounter();
		ints = IntStream.range(0, length).parallel().map(
				i -> count3.increment()).sorted().toArray();
		System.out.println(Arrays.toString(ints));
		System.out.println("Unique entries: " + Arrays.stream(ints).distinct().count());
		System.in.read(new byte[2]);


		System.out.println("On custom ForkJoinPool");
		SynchCounter count4 = new SynchCounter();
		ForkJoinPool customPool = new ForkJoinPool(256);
		ints = customPool.submit(
				() -> IntStream.range(0, length).parallel().map(
						i -> count4.increment()).sorted().toArray()).get();
		System.out.println(Arrays.toString(ints));
		System.out.println("Unique entries: " + Arrays.stream(ints).distinct().count());

	}

	private static class Counter {
		private int val = 0;
	}

	private static class SynchCounter {
		private int val = 0;

		synchronized int increment() {
			return val++;
		}
	}
}
