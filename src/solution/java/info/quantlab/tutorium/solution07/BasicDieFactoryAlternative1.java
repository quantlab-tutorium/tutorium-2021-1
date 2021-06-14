/**
 *
 */
package info.quantlab.tutorium.solution07;

import java.util.Random;

import info.quantlab.tutorium.session07.Die;

/**
 * A factory for simple 1-6 dice.
 *
 * <p>
 * Alternative implementation using a separate random number generator per die. The factory produces seeds for those
 * generators. This (probably) ensures that the generated random sequences are not correlated.
 *
 * <p>
 * The downside is that the multiple generators require additional memory and their creation takes time. Possibly
 * concurrency issues, if a single die is accessed from multiple threads in parallel or multiple factories are used.
 *
 * @author Roland Bachl
 *
 */
public class BasicDieFactoryAlternative1 extends AbstractDieFactory {

	private Random seedGenerator = new Random();

	@Override
	public Die getDie() {
		return new Die() {

			private Random generator = new Random(seedGenerator.nextLong());

			@Override
			public int cast() {
				return generator.nextInt(6) + 1;
			}
		};
	}

}
