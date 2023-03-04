package battle;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JOptionPane;
import javax.swing.JFrame;

/**
 * This class allows to create a battleship game
 * @author 0xARK
 * @version 1.0.0
 */

public class Game implements IGame {

	private Player auto;
	private Player captain;
	private Player current;
	private ShotResult result;
	private ArrayList<Ship> fleet;

	/**
	 * The constructor : it allows to initialize a game with the player's name, the grid size, the mode, and the fleet
	 * @param fleet - The fleet of the palyers
	 * @param playerName1 - The name of the player one
	 * @param playerName2 - The name of the player two
	 * @param height - The height of the grid
	 * @param width - The width of the grid
	 * @param mode - The play mode (Human - Human / Human - Computer / Computer - Computer)
	 */

	public Game(ArrayList<Ship> fleet, String playerName1, String playerName2, int height, int width, Mode mode) {

		if (fleet == null) {

			throw new IllegalArgumentException("[-] Error - Game() - Game : fleet is null");

		}

		this.fleet = fleet;

		if (mode == Mode.HH) {

			this.auto = new HumanPlayer(fleet, playerName1, height, width);
			this.captain = new HumanPlayer(fleet, playerName2, height, width);

		} else if (mode == Mode.HA) {

			this.auto = new AutoPlayer(fleet, playerName1, height, width);
			this.captain = new HumanPlayer(fleet, playerName2, height, width);

		} else if (mode == Mode.AA) {

			this.auto = new AutoPlayer(fleet, playerName1, height, width);
			this.captain = new AutoPlayer(fleet, playerName2, height, width);

		} else {

			throw new IllegalArgumentException("[-] Error - Game() - Game : play mode is invalid");

		}

		System.out.println(description());

		this.current = this.captain;

		start();

	}

	/**
	 * This method allows to dislay a short description of the game
	 * @return the description of the game
	 */

	public String description() {

		String desc = "+-----------------------------------------------------------+\n| ◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢ WELCOME IN MY BATTLESHIP ◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢|\n+-----------------------------------------------------------+\n|                                                           |\n| Fonctionnement du jeu :                                   |\n|                                                           |\n| Modifiez le fichier de configuration du jeu pour choisir  |\n| la flotte disponible ainsi que le mode de jeu. Choisissez |\n| le nom des joueurs et commencez !                         |\n|                                                           |\n| Chaque joueur joue tour à tour et entre les coordonnées   |\n| de la case qu'il souhaite toucher. L'objectif est de      |\n| couler tous les navires adverses. Bonne chance !          |\n|                                                           |\n+-----------------------------------------------------------+\n| ◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤◢◤ |\n+-----------------------------------------------------------+\n";

		return desc;

	}

	/**
	 * This method allows to change the current player to allow at his opponent to play
	 */

	private void changeCurrent() {

		if (this.current == this.captain) {
			this.current = this.auto;
		} else {
			this.current = this.captain;
		}

	}

	/**
	 * This method allows to the current player to make a new shot
	 * @param player - The current player
	 */

	public int[] readShot(Player player) {

		return player.newShot();

	}

	/**
	 * This method allows to analyze a shot make by a player and return his result
	 * @param shot - The coordinates of the shot
	 * @return MISS if the shot don't touch a ship, HIT if the shot touch a ship or SUNK if the shot touch a ship and sunk it as the same time
	 */

	public ShotResult analyzeShot(int[] shot) {

		ShotResult sr = ShotResult.MISS;

		if (this.current == this.auto) {

			captain.myGrid[shot[0]][shot[1]].setHit();

			if (!captain.myGrid[shot[0]][shot[1]].isFree() && !auto.opponentGrid[shot[0]][shot[1]].isHit()) {

				sr = ShotResult.HIT;

				for (Ship s : captain.fleet) {

					if (s.getDirection() == Direction.HORIZONTAL && s.getX() <= shot[0] && shot[0] <= (s.getX() + s.getSize() - 1) && shot[1] == s.getY()) {

						s.addHit();
						auto.opponentGrid[shot[0]][shot[1]].setHit();
						auto.opponentGrid[shot[0]][shot[1]].setBusy();
						if (s.isSunk()) {
							sr = ShotResult.SUNK;
						}

					} else if (s.getDirection() == Direction.VERTICAL && s.getY() >= shot[1] && shot[1] <= (s.getY() + s.getSize() - 1) && shot[0] == s.getX()) {

						s.addHit();
						auto.opponentGrid[shot[0]][shot[1]].setHit();
						auto.opponentGrid[shot[0]][shot[1]].setBusy();
						if (s.isSunk()) {
							sr = ShotResult.SUNK;
						}

					}

				}

			} else {
				auto.opponentGrid[shot[0]][shot[1]].setHit();
			}

		} else if (this.current == this.captain) {

			auto.myGrid[shot[0]][shot[1]].setHit();

			if (!auto.myGrid[shot[0]][shot[1]].isFree() && !captain.opponentGrid[shot[0]][shot[1]].isHit()) {

				sr = ShotResult.HIT;

				for (Ship s : auto.fleet) {

					if (s.getDirection() == Direction.HORIZONTAL && s.getX() <= shot[0] && shot[0] <= (s.getX() + s.getSize() - 1) && shot[1] == s.getY()) {

						s.addHit();
						captain.opponentGrid[shot[0]][shot[1]].setHit();
						captain.opponentGrid[shot[0]][shot[1]].setBusy();
						if (s.isSunk()) {
							sr = ShotResult.SUNK;
						}

					} else if (s.getDirection() == Direction.VERTICAL && s.getY() <= shot[1] && shot[1] <= (s.getY() + s.getSize() - 1) && shot[0] == s.getX()) {

						s.addHit();
						captain.opponentGrid[shot[0]][shot[1]].setHit();
						captain.opponentGrid[shot[0]][shot[1]].setBusy();
						if (s.isSunk()) {
							sr = ShotResult.SUNK;
						}

					}

				}

			} else {
				captain.opponentGrid[shot[0]][shot[1]].setHit();
			}

		}

		return sr;

	}

	public boolean allSunk(Player aPlayer) {

		boolean all = aPlayer.allSunk();

		return all;

	}

	public void start() {

		JFrame alert = new JFrame();

 		while (!allSunk(this.current)) {

			if (this.current == this.captain) {
				captain.displayMygrid();
 				captain.displayOpponentGrid();
 			} else {
 				auto.displayMygrid();
 				auto.displayOpponentGrid();
 			}

 			int[] shot = readShot(this.current);

 			this.result = analyzeShot(shot);

 			if (this.captain instanceof HumanPlayer) {

 				String msg = "";

 				if (this.result == ShotResult.MISS) {
 					msg = "x = " + shot[0] + ", y = " + shot[1] + " : Oops, you have miss your shot " + this.current.name;
 				} else if (result == ShotResult.HIT) {
 					msg = "x = " + shot[0] + ", y = " + shot[1] + " : Well played, you have hit a ship " + this.current.name;
 				} else {
 					msg = "x = " + shot[0] + ", y = " + shot[1] + " : Yeah ! You have sunk a ship, " + this.current.name;
 				}

 				alert.setVisible(true);
 				JOptionPane.showMessageDialog(alert, msg);
 				alert.setVisible(false);

 			}

 			if (this.current == this.captain) {
 				this.captain.myOtframe.dispose();
 				this.captain.opponentOtframe.dispose();
 			} else {
 				this.auto.myOtframe.dispose();
 				this.auto.opponentOtframe.dispose();
 			}

 			changeCurrent();

 		}

 		endOfGame();

	}

	public void endOfGame() {

		String win = "__        _____ _   _\n\\ \\      / /_ _| \\ | |\n \\ \\ /\\ / / | ||  \\| |\n  \\ V  V /  | || |\\  |\n   \\_/\\_/  |___|_| \\_|";

		if (this.current == this.captain) {
			System.out.println(win + "\n\nCongratulations " + this.auto.name + ", you win !");
 		} else if (this.current == this.auto) {
 			System.out.println(win + "\n\nCongratulations " + this.captain.name + ", you win !");
 		}

	}


}
