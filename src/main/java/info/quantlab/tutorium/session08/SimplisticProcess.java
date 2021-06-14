package info.quantlab.tutorium.session08;

/**
 * A simplistic process. Can be viewed as the discretization of a single realization of a stochastic process.
 *
 * @author Roland Bachl
 *
 */
public interface SimplisticProcess {

	/**
	 * The index of the last discretization point of the process.
	 *
	 * @return The index.
	 */
	int getMaxIndex();

	/**
	 * The time at the given index of the discretization.
	 *
	 * @param index the index.
	 * @return The time.
	 */
	double getTimeAtIndex(int index);

	/**
	 * The value at the given index of the discretization.
	 *
	 * @param index the index.
	 * @return The value.
	 */
	double getValueAtIndex(int index);

}