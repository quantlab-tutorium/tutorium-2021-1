/**
 *
 */
package info.quantlab.tutorium.session07;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

/**
 * @author Roland Bachl
 *
 */
public class SetOfDiceTest {

	private static final int numberOfDice = 2;
	private static final int numberOfCasts = (int) 1e6;

	@Test
	void testFairVsLoaded() {

		DieFactory fairDieFactory = new BasicDieFactory();
		DieFactory loadedDieFactory = new LoadedDieFactory();

		SetOfDice fairDice = fairDieFactory.getSetOfDice(numberOfDice);
		SetOfDice loadedDice = loadedDieFactory.getSetOfDice(numberOfDice);

		double fairAverage = Stream.generate(() -> fairDice.cast()).limit(numberOfCasts).mapToInt(Integer::intValue)
				.average().getAsDouble();
		double loadedAverage = Stream.generate(() -> loadedDice.cast()).limit(numberOfCasts).mapToInt(Integer::intValue)
				.average().getAsDouble();

		assertTrue(fairAverage < loadedAverage);
	}

	@Test
	void testSixOccurences() {

		DieFactory fairDieFactory = new BasicDieFactory();
		DieFactory loadedDieFactory = new LoadedDieFactory();

		Die fairDie = fairDieFactory.getDie();
		Die loadedDie = loadedDieFactory.getDie();

		double fairRate = (double) Stream.generate(() -> fairDie.cast()).limit(numberOfCasts)
				.mapToInt(Integer::intValue).filter(d -> d == 6).count() / numberOfCasts;
		double loadedRate = (double) Stream.generate(() -> loadedDie.cast()).limit(numberOfCasts)
				.mapToInt(Integer::intValue).filter(d -> d == 6).count() / numberOfCasts;

		assertEquals(.167, fairRate, .001);
		assertEquals(.25, loadedRate, .001);
	}
}
