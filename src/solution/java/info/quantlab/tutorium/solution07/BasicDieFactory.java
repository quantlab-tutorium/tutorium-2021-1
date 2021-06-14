/**
 *
 */
package info.quantlab.tutorium.solution07;

import java.util.concurrent.ThreadLocalRandom;

import info.quantlab.tutorium.session07.Die;

/**
 * A factory for simple 1-6 dice.
 *
 * @author Roland Bachl
 *
 */
public class BasicDieFactory extends AbstractDieFactory {

	@Override
	public Die getDie() {
		return new BasicDie();
	}

	/**
	 * A simple 1-6 die.
	 *
	 * @author Roland Bachl
	 *
	 */
	public class BasicDie implements Die {

		@Override
		public int cast() {
			return ThreadLocalRandom.current().nextInt(6) + 1;
		}

	}
}
