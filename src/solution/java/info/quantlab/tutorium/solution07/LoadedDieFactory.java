/**
 *
 */
package info.quantlab.tutorium.solution07;

import java.util.concurrent.ThreadLocalRandom;

import info.quantlab.tutorium.session07.Die;

/**
 * A factory for 1-6 dice with a one-in-four chance of getting a six.
 *
 * @author Roland Bachl
 *
 */
public class LoadedDieFactory extends AbstractDieFactory {

	@Override
	public Die getDie() {
		return new LoadedDie();
	}

	/**
	 * A loaded 1-6 die.
	 *
	 * @author Roland Bachl
	 *
	 */
	public class LoadedDie implements Die {

		@Override
		public int cast() {
			int result = ThreadLocalRandom.current().nextInt(20);
			if (result > 14) {
				return 6;
			}
			return (result % 5) + 1;
		}

	}
}
