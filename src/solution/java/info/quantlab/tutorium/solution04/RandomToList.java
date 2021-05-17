package info.quantlab.tutorium.solution04;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Create a {@link java.util.Random} from the given seed and return a list of first size doubles of the sequence.
 *
 * @author Roland Bachl
 *
 */
public class RandomToList {

	/**
	 * Create a list of random Double.
	 *
	 * @param seed The seed for the rng.
	 * @param size The intended size of the list.
	 * @return The list of random Double.
	 */
	public static List<Double> getList(long seed, int size) {
		return new Random(seed).doubles(size).boxed().collect(Collectors.toList());
	}

}
