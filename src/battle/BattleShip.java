package battle;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

/**
 * This class allows to create a battleship with the grid size and the mode
 * @author 0xARK
 * @version 1.0.0
 */

public class BattleShip {

	private ArrayList<Ship> fleet;
	private Game gamePlay;
	private Mode mode;
	private int width;
	private int height;
	private final String DELIMITER = "\\s*:\\s*";

	/**
	 * This method allows to initialize a battleship game and the arraylist of ship
	 * @param fileName - The name of the configuration file
	 * @param playerName1 - The name of the player one
	 * @param playerName2 - The name of the player two
	 */

	public BattleShip(String fileName, String playerName1, String playerName2) {

		this.fleet = new ArrayList<Ship>();

		if (fileName.equals("") || fileName == null) {

			throw new IllegalArgumentException("[-] Error - BattleShip() - BattleShip : configuration file name is empty or null");

		}

		this.configure(fileName);
		this.printConfiguration();
		this.gamePlay = new Game(this.fleet, playerName1, playerName2, this.height, this.width, this.mode);

	}

	/**
	 * This method allows to initialize the current battle ship with a configuration file
	 * @param file - The configuration file name
	 */

	private void configure(String fileName) {

		try {

			Scanner read = new Scanner(new File(fileName));
			read.useDelimiter(DELIMITER);

			try {

				this.width = Integer.parseInt(read.next());
				this.height = Integer.parseInt(read.next());

				if (width < 3 || height < 3) {

					throw new NumberFormatException("[-] Error - configure() - BattleShip : The mimimal grid size is 3x3. Please modify the configuration file.");

				}

			} catch (NumberFormatException e) {

				System.err.println(e.getMessage());

			}

			read.next();

			try {

				this.mode = Mode.valueOf(read.next());

			} catch (IllegalArgumentException e) {

				System.err.println(e.getMessage());

			}

			while (read.hasNext()) {

				try {

					String name = read.next();
					int size = Integer.parseInt(read.next());

					if (size < 1) {

						throw new NumberFormatException("[-] Error - configure() - BattleShip : Ship size is too small. Please modify the configuration file.");

					}

					if  (size > this.width && size > this.height) {

						throw new NumberFormatException("[-] Error - configure() - BattleShip : Ship size > grid size. Please modify the configuration file.");

					}

					Ship s = new Ship(name, size);
					this.fleet.add(s);

				} catch (NumberFormatException e) {

					System.err.println(e.getMessage());

				}

			}

		} catch (FileNotFoundException error) {

			System.out.println("[-] Error - configure() - BattleShip : The file '" + fileName + "' was not found.");

		}

	}

	/**
	 * This method allows to display the configuration of the current game
	 */

	public void printConfiguration() {

		System.out.println("Taille de la grille : " + height + "x" + width + "\nMode de jeu : " + mode);

		for (Ship s : fleet) {

			System.out.println(s.toString());

		}

	}

}
