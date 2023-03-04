import battle.BattleShip;

/**
 * This class allows to start a new battle ship with the name of the 2 players, and the configuration file name
 * @author 0xARK
 * @version 1.0.0
 */

public class LaunchBattle {

	/**
	 * The constructor : it allows to start a new battle ship
	 * @param args - The command line args with the configuration file name, and the name of the 2 players
	 */

	public static void main(String[] args) {

		if (args.length == 3) {

			BattleShip bs = new BattleShip(args[0], args[1], args[2]);

		} else System.err.println("[-] Usage : java -jar battleship.jar <config_file_name> <player_name_one> <player_name_two>");

	}

}
