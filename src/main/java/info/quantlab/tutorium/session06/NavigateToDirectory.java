/**
 *
 */
package info.quantlab.tutorium.session06;


import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import javax.swing.JOptionPane;

/**
 * This class does not contain a coding exercise, but you can run the main to get more information.
 *
 * @author Roland Bachl
 *
 */
public class NavigateToDirectory {

	private static final File directory = new File("src/test/resources/info/quantlab/tutorium/session06");

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		String message = "There is nothing to be coded in this exercise. Instead this exercise is about creating your "
				+ "own project and managing dependencies with and without the use of maven.\n" + "In " + directory
				+ " you will find two test classes. However, these require some dependencies and "
				+ "will not work by themselves.\n\n"
				+ "Please create two new projects and set up their dependencies such that you can run the tests.\n\n"
				+ "First start with the BrownianMotionTest from the finmath-lib. Please import the lib as a Project "
				+ "using your IDE's build management tools. If you like you can set up the modules, but this is "
				+ "optional.\n"
				+ "Second, set up a project for RandomVariableGPUTest from the finmath-lib-cuda-extensions. This time "
				+ "try to use maven to manage the dependencies. We would like to use version 4.1.7 of the "
				+ "extension.\n\n\n"
				+ "Hints:\n"
				+ " * You will need to configure your project to use JUnit4. JUnit5 will not work with the tests as "
				+ "they are.\n"
				+ " * Take care which package you put the tests into. Either you create a package of the name "
				+ "specified in the class file or you adjust the file.\n"
				+ " * Some tests may fail. Especially, when you don't have a dedicated GPU installed on your computer. "
				+ "Don't worry, it's fine as long as the tests themselves are running.";

		String title = "Session 6";
		String openOption = "Open file directory";
		Object[] options = { openOption, "Close" };

		int selection = JOptionPane.showOptionDialog(null, message, title, JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.PLAIN_MESSAGE, null, options,
				openOption);

		if (selection == 0) {
			try {
				Desktop desktop = Desktop.getDesktop();
				desktop.open(directory);
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null,
						"Failed to open system explorer. You should be able to find the tests here:\n\n"
								+ directory.getAbsolutePath(),
								title, JOptionPane.WARNING_MESSAGE);
			}
		}

	}

}
