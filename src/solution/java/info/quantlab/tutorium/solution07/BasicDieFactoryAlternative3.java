/**
 *
 */
package info.quantlab.tutorium.solution07;

import java.util.concurrent.ThreadLocalRandom;

import info.quantlab.tutorium.session07.Die;

/**
 * A factory for simple 1-6 dice.
 *
 * <p>
 * This is the implementation I used as solution. It does not keep it's own random number generator, but uses the ones
 * from ThreadLocalRandom. This avoids synchronization issues and locking of threads, but takes away some of the
 * control, like a seed for example.
 *
 * <p>
 * (Note also, this abbreviated version may fail the test, because the compiler reuses the lambda expression.)
 *
 * @author Roland Bachl
 *
 */
public class BasicDieFactoryAlternative3 extends AbstractDieFactory {

	@Override
	public Die getDie() {
		return () -> ThreadLocalRandom.current().nextInt(6) + 1;
	}

}
