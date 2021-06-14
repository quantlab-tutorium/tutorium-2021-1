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
 * Alternative implementation, where all dice from this factory share a single generator.
 *
 * <p>
 * Hence access to the generator is synchronized to ensure thread safety in parallel applications. This may slow down
 * the program, when parallelized.
 *
 * @author Roland Bachl
 *
 */
public class BasicDieFactoryAlternative2 extends AbstractDieFactory {

	private Random generator = new Random();

	@Override
	public Die getDie() {
		return () -> getCast();
	}

	private synchronized int getCast() {
		return generator.nextInt(6) + 1;
	}
}
