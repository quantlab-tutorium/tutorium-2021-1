/**
 *
 */
package info.quantlab.tutorium.session07;

/**
 * A factory for dice and sets of dice.
 *
 * @author Roland Bachl
 *
 */
public interface DieFactory {

	/**
	 * Build a die.
	 *
	 * @return the die.
	 */
	Die getDie();

	/**
	 * Build a set of dice with the given number of dice.
	 *
	 * @param numberOfDice The number of dice in the set.
	 * @return the set of dice.
	 */
	SetOfDice getSetOfDice(int numberOfDice);
}
