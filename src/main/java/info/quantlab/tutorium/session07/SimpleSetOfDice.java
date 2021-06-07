/**
 *
 */
package info.quantlab.tutorium.session07;

import java.util.Arrays;
import java.util.Collection;
import java.util.Set;

/**
 * A {@link SetOfDice} made from individual dice.
 *
 * @author Roland Bachl
 *
 */
public class SimpleSetOfDice implements SetOfDice {

	private final Die[] dice;

	/**
	 * Collect a set of dice.
	 *
	 * @param dice The dice to be used.
	 */
	public SimpleSetOfDice(Die... dice) {
		super();
		// make sure there are no duplicate dice.
		long distinctDice = Arrays.stream(dice).distinct().count();
		if (distinctDice != dice.length) {
			throw new IllegalArgumentException("No duplicate dice allowed!");
		}
		this.dice = dice;
	}

	/**
	 * Collect a set of dice.
	 *
	 * @param dice The dice to be used.
	 */
	public SimpleSetOfDice(Collection<Die> dice) {
		this(dice.stream().toArray(Die[]::new));
	}

	/**
	 * Return the individual dice of the set.
	 *
	 * @return The set of dice.
	 */
	public Set<Die> getDice() {
		return Set.of(dice);
	}

	/**
	 * Cast the dice.
	 *
	 * @return The result of the cast.
	 */
	@Override
	public int cast() {
		return Arrays.stream(dice).mapToInt(d -> d.cast()).sum();
	}

}
