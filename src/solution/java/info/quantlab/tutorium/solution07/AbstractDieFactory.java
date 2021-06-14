/**
 *
 */
package info.quantlab.tutorium.solution07;

import java.util.stream.Stream;

import info.quantlab.tutorium.session07.Die;
import info.quantlab.tutorium.session07.DieFactory;
import info.quantlab.tutorium.session07.SetOfDice;
import info.quantlab.tutorium.session07.SimpleSetOfDice;


/**
 * An abstract die factory that may serve as a basis for a {@link DieFactory}.
 *
 * @author Roland Bachl
 *
 */
public abstract class AbstractDieFactory implements DieFactory {


	@Override
	public SetOfDice getSetOfDice(int numberOfDice) {
		return new SimpleSetOfDice(Stream.generate(() -> getDie()).limit(numberOfDice).toArray(Die[]::new));
	}

}
