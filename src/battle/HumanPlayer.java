package battle;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * This class allows to create a human player
 * @author 0xARK
 * @version 1.0.0
 */

public class HumanPlayer extends Player {

	private JFrame frame;
	private final String DELIMITER = "\\s*:\\s*";

	/**
	 * The constructor : it allows to initialize the player constructor
	 * @param fleet - The fleet of the current human player
	 * @param name - The name of the current human player
	 * @param height - The height of the current grid game
	 * @param width - The width of the current grid game
	 */

	public HumanPlayer(ArrayList<Ship> fleet, String name, int height, int width) {

		super(fleet, name, height, width);
		this.frame = new JFrame("Action");
		shipPlacement();

	}

	/**
	 * This method allows to verifiy if a ship is to close of another (at least one square between two boats)
	 */

	public void shipPlacement() {

		readConfiguration();

		for (Ship s : this.fleet) {

			if (s.getDirection() == Direction.HORIZONTAL) {

				int x = s.getX();
				int y = s.getY();

				int lastX = x + s.getSize() - 1;
				int i = x;

				while (i <= lastX) {

					if (i == x) {

						if (x == 0) {

							if (y == 0) {

								if (!this.myGrid[i][y + 1].isFree()) {
									throw new IllegalArgumentException("[-] Error HumanPlayer : Two ship are to close. Please modify the ship configuration file.");
								}

							} else if (y == height - 1) {

								if (!this.myGrid[i][y - 1].isFree()) {
									throw new IllegalArgumentException("[-] Error HumanPlayer : Two ship are to close. Please modify the ship configuration file.");
								}

							} else {

								if (!this.myGrid[i][y + 1].isFree() || !this.myGrid[i][y - 1].isFree()) {
									throw new IllegalArgumentException("[-] Error HumanPlayer : Two ship are to close. Please modify the ship configuration file.");
								}

							}

						} else {

							if (y == 0) {

								if (!this.myGrid[i - 1][y].isFree() || !this.myGrid[i - 1][y + 1].isFree() || !this.myGrid[i][y + 1].isFree()) {
									throw new IllegalArgumentException("[-] Error HumanPlayer : Two ship are to close. Please modify the ship configuration file.");
								}

							} else if (y == height - 1) {

								if (!this.myGrid[i - 1][y].isFree() || !this.myGrid[i - 1][y - 1].isFree() || !this.myGrid[i][y - 1].isFree()) {
									throw new IllegalArgumentException("[-] Error HumanPlayer : Two ship are to close. Please modify the ship configuration file.");
								}

							} else {

								if (!this.myGrid[i - 1][y].isFree() || !this.myGrid[i - 1][y + 1].isFree() || !this.myGrid[i][y + 1].isFree() || !this.myGrid[i - 1][y - 1].isFree() || !this.myGrid[i][y - 1].isFree()) {
									throw new IllegalArgumentException("[-] Error HumanPlayer : Two ship are to close. Please modify the ship configuration file.");
								}

							}

						}

					}

					if (i == lastX) {

						if (lastX == width - 1) {

							if (y == 0) {

								if (!this.myGrid[i][y + 1].isFree()) {
									throw new IllegalArgumentException("[-] Error HumanPlayer : Two ship are to close. Please modify the ship configuration file.");
								}

							} else if (y == height - 1) {

								if (!this.myGrid[i][y - 1].isFree()) {
									throw new IllegalArgumentException("[-] Error HumanPlayer : Two ship are to close. Please modify the ship configuration file.");
								}

							} else {

								if (!this.myGrid[i][y + 1].isFree() || !this.myGrid[i][y - 1].isFree()) {
									throw new IllegalArgumentException("[-] Error HumanPlayer : Two ship are to close. Please modify the ship configuration file.");
								}

							}

						} else {

							if (y == 0) {

								if (!this.myGrid[i + 1][y].isFree() || !this.myGrid[i + 1][y + 1].isFree() || !this.myGrid[i][y + 1].isFree()) {
									throw new IllegalArgumentException("[-] Error HumanPlayer : Two ship are to close. Please modify the ship configuration file.");
								}

							} else if (y == height - 1) {

								if (!this.myGrid[i + 1][y].isFree() || !this.myGrid[i + 1][y - 1].isFree() || !this.myGrid[i][y - 1].isFree()) {
									throw new IllegalArgumentException("[-] Error HumanPlayer : Two ship are to close. Please modify the ship configuration file.");
								}

							} else {

								if (!this.myGrid[i + 1][y].isFree() || !this.myGrid[i + 1][y + 1].isFree() || !this.myGrid[i][y + 1].isFree() || !this.myGrid[i + 1][y - 1].isFree() || !this.myGrid[i][y - 1].isFree()) {
									throw new IllegalArgumentException("[-] Error HumanPlayer : Two ship are to close. Please modify the ship configuration file.");
								}

							}

						}

					}

					if (i != x && i != lastX) {

						if (y == 0) {

							if (!this.myGrid[i][y + 1].isFree()) {
								throw new IllegalArgumentException("[-] Error HumanPlayer : Two ship are to close. Please modify the ship configuration file.");
							}

						} else if (y == height - 1) {

							if (!this.myGrid[i][y - 1].isFree()) {
								throw new IllegalArgumentException("[-] Error HumanPlayer : Two ship are to close. Please modify the ship configuration file.");
							}

						} else {

							if (!this.myGrid[i][y + 1].isFree() || !this.myGrid[i][y - 1].isFree()) {
								throw new IllegalArgumentException("[-] Error HumanPlayer : Two ship are to close. Please modify the ship configuration file.");
							}

						}

					}

					i++;

				}

			} else if (s.getDirection() == Direction.VERTICAL) {

				int x = s.getX();
				int y = s.getY();

				int lastY = y + s.getSize() - 1;
				int i = y;

				while (i <= lastY) {

					if (i == y) {

						if (y == 0) {

							if (x == 0) {

								if (!this.myGrid[x + 1][i].isFree()) {
									throw new IllegalArgumentException("[-] Error HumanPlayer : Two ship are to close. Please modify the ship configuration file.");
								}

							} else if (x == width - 1) {

								if (!this.myGrid[x - 1][i].isFree()) {
									throw new IllegalArgumentException("[-] Error HumanPlayer : Two ship are to close. Please modify the ship configuration file.");
								}

							} else {

								if (!this.myGrid[x + 1][i].isFree() || !this.myGrid[x - 1][i].isFree()) {
									throw new IllegalArgumentException("[-] Error HumanPlayer : Two ship are to close. Please modify the ship configuration file.");
								}

							}

						} else {

							if (x == 0) {

								if (!this.myGrid[x][i - 1].isFree() || !this.myGrid[x + 1][i - 1].isFree() || !this.myGrid[x + 1][i].isFree()) {
									throw new IllegalArgumentException("[-] Error HumanPlayer : Two ship are to close. Please modify the ship configuration file.");
								}

							} else if (x == width - 1) {

								if (!this.myGrid[x - 1][i].isFree() || !this.myGrid[x - 1][i - 1].isFree() || !this.myGrid[x][i - 1].isFree()) {
									throw new IllegalArgumentException("[-] Error HumanPlayer : Two ship are to close. Please modify the ship configuration file.");
								}

							} else {

								if (!this.myGrid[x - 1][i].isFree() || !this.myGrid[x + 1][i - 1].isFree() || !this.myGrid[x + 1][i].isFree() || !this.myGrid[x - 1][i - 1].isFree() || !this.myGrid[x][i - 1].isFree()) {
									throw new IllegalArgumentException("[-] Error HumanPlayer : Two ship are to close. Please modify the ship configuration file.");
								}

							}

						}

					}

					if (i == lastY) {

						if (lastY == height - 1) {

							if (x == 0) {

								if (!this.myGrid[x + 1][i].isFree()) {
									throw new IllegalArgumentException("[-] Error HumanPlayer : Two ship are to close. Please modify the ship configuration file.");
								}

							} else if (x == width - 1) {

								if (!this.myGrid[x - 1][i].isFree()) {
									throw new IllegalArgumentException("[-] Error HumanPlayer : Two ship are to close. Please modify the ship configuration file.");
								}

							} else {

								if (!this.myGrid[x + 1][i].isFree() || !this.myGrid[x - 1][i].isFree()) {
									throw new IllegalArgumentException("[-] Error HumanPlayer : Two ship are to close. Please modify the ship configuration file.");
								}

							}

						} else {

							if (x == 0) {

								if (!this.myGrid[x + 1][i].isFree() || !this.myGrid[x + 1][i + 1].isFree() || !this.myGrid[x][i + 1].isFree()) {
									throw new IllegalArgumentException("[-] Error HumanPlayer : Two ship are to close. Please modify the ship configuration file.");
								}

							} else if (x == width - 1) {

								if (!this.myGrid[x - 1][i].isFree() || !this.myGrid[x - 1][i + 1].isFree() || !this.myGrid[x][i + 1].isFree()) {
									throw new IllegalArgumentException("[-] Error HumanPlayer : Two ship are to close. Please modify the ship configuration file.");
								}

							} else {

								if (!this.myGrid[x + 1][i].isFree() || !this.myGrid[x + 1][i + 1].isFree() || !this.myGrid[x][i + 1].isFree() || !this.myGrid[x - 1][i + 1].isFree() || !this.myGrid[x - 1][i].isFree()) {
									throw new IllegalArgumentException("[-] Error HumanPlayer : Two ship are to close. Please modify the ship configuration file.");
								}

							}

						}

					}

					if (i != y && i != lastY) {

						if (x == 0) {

							if (!this.myGrid[x + 1][i].isFree()) {
								throw new IllegalArgumentException("[-] Error HumanPlayer : Two ship are to close. Please modify the ship configuration file.");
							}

						} else if (x == width - 1) {

							if (!this.myGrid[x - 1][i].isFree()) {
								throw new IllegalArgumentException("[-] Error HumanPlayer : Two ship are to close. Please modify the ship configuration file.");
							}

						} else {

							if (!this.myGrid[x + 1][i].isFree() || !this.myGrid[x - 1][i].isFree()) {
								throw new IllegalArgumentException("[-] Error HumanPlayer : Two ship are to close. Please modify the ship configuration file.");
							}

						}

					}

					i++;

				}

			}

		}

	}

	/**
	 * This method allows to read the ship configuration file of a the current player with for each ship, his name, his coordinates and his direction
	 */

	private void readConfiguration() {

		try {

			String userInput = JOptionPane.showInputDialog(this.frame, "Enter the path of the ship configuration file for their placement (player " + this.name + "):");
			if (userInput.equals("") || userInput == null) {

				throw new IllegalArgumentException("[-] Error - readConfiguration() - HumanPlayer : configuration file name is empty or null");

			}

			File fileName = new File(userInput);

			if (!fileName.exists()) {
				throw new IllegalArgumentException("[-] Error - readConfiguration() - HumanPlayer : configuration file name does not exist");
			}

			Scanner in = new Scanner(fileName);
			in.useDelimiter(DELIMITER);

			int shipNumber = 0;
			boolean search = true;

			while (in.hasNext() && search) {

				String name = in.next();

				boolean found = false;
				Iterator<Ship> it = this.fleet.iterator();

				while (it.hasNext() && !found) {

					Ship s = it.next();

					if (s.getName().toUpperCase().equals(name.toUpperCase())) {

						found = true;

						Direction direction = null;
						int x = 0;
						int y = 0;

						try {

							x = Integer.parseInt(in.next());
							y = Integer.parseInt(in.next());

							if (x < 0) {

								throw new IllegalArgumentException("[-] Error - readConfiguration() - HumanPlayer : the x coordinate of a ship is smaller than 0. Please modify the ship configuration file.");

							}

							if (y < 0) {

								throw new IllegalArgumentException("[-] Error - readConfiguration() - HumanPlayer : the y coordinate of a ship is smaller than 0. Please modify the ship configuration file.");

							}

						} catch (NumberFormatException e) {

							System.err.println(e.getMessage());

						}

						try {

							direction = Direction.valueOf(in.next());

						} catch (IllegalArgumentException e) {

							System.err.println(e.getMessage());

						}

						if (direction == Direction.HORIZONTAL && x + s.getSize() >= this.width) {

							throw new IllegalArgumentException("[-] Error - readConfiguration() - HumanPlayer : The x coordinate of a ship is out of the grid. Please modify the ship configuration file.");

						}

						if (direction == Direction.VERTICAL && y + s.getSize() >= this.height) {

							throw new IllegalArgumentException("[-] Error - readConfiguration() - HumanPlayer : The y coordinate of a ship is out of the grid. Please modify the ship configuration file.");

						}

						s.setDirection(direction);
						s.setX(x);
						s.setY(y);
						int size = s.getSize();
						if (direction == Direction.HORIZONTAL) {

							for (int i = x; i < x + size; i++) {

								this.myGrid[i][y].setBusy();

							}

						} else if (direction == Direction.VERTICAL) {

							for (int i = y; i < y + size; i++) {

								this.myGrid[x][i].setBusy();

							}

						}

						shipNumber++;

					}

				}

				if (fleet.size() == shipNumber) {

					search = false;

				}

			}

			in.close();

			if (fleet.size() != shipNumber) {

				throw new IllegalArgumentException("[-] Error - readConfiguration() - HumanPlayer : Not enough ships provided in the configuration file.");

			}

		} catch (FileNotFoundException e) {

			System.err.println(e.getMessage());

		}

	}

	/**
	 * This method allows to make a shot on the opponent grid of the current player
	 * @return an int array with the x coordinate and the y coordinate
	 */

	public int[] newShot() {

		int[] coordinate = new int[2];
		String userInput = JOptionPane.showInputDialog(this.frame, "Hey, " + this.name + ", please enter coordinates of the new shot at format xy :");
		int x = 0;
		int y = 0;

		try {

			if (userInput.length() != 2) {
				throw new IllegalArgumentException("[-] Error - newShot() - HumanPlayer : Coordinate must be entered at the format xy");
			}

			String regex = "[0-9]{2}";
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(userInput);

			if (!matcher.matches()) {
				throw new IllegalArgumentException("[-] Error - newShot() - HumanPlayer : Coordinate must be numbers");
			}

			x = Integer.parseInt(userInput.substring(0, 1));
			y = Integer.parseInt(userInput.substring(1));

			if (x < 0 || y < 0) {
				throw new NumberFormatException("[-] Error - newShot() - HumanPlayer : A coordinate of the new shot is smaller than the grid size.");
			}

			if (x >= this.width || y >= this.height) {
				throw new NumberFormatException("[-] Error - newShot() - HumanPlayer : A coordinate of the new shot is higher than the grid size.");
			}

			coordinate[0] = x;
			coordinate[1] = y;

		} catch (IllegalArgumentException e) {

			System.out.println("[-] Error - newShot() - HumanPlayer : " + e.getMessage());

		}

		return coordinate;

	}

}
