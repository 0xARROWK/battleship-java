package battle;
import java.util.ArrayList;
import java.util.Iterator;
import view.GridTableFrame;

/**
 * This class allows to create a player object
 * @author 0xARK
 * @version 1.0.0
 */

public abstract class Player {

	protected Square[][] myGrid;
	protected Square[][] opponentGrid;
	protected String name;
	protected int width;
	protected int height;
	protected ArrayList<Ship> fleet;
	protected GridTableFrame myOtframe;
	protected GridTableFrame opponentOtframe;

	/**
	 * The constructor : it allows to initialize the fleet of the current player, his name and his grid size
	 * @param fleet - The list of ship of the current player
	 * @param name - The name of the current player
	 * @param height - The height of the grid of the current player
	 * @param width - The width of the grid of the current player
	 */

	public Player(ArrayList<Ship> fleet, String name, int height, int width) {

		if (fleet == null) {

            throw new IllegalArgumentException("[-] Error - Player() - Player : fleet is null");

        }

        createCopy(fleet);

        if (name.equals("") || name == null) {

            throw new IllegalArgumentException("[-] Error - Player() - Player : player name is empty or null");

        }

        this.name = name;

        if (width < 3 || height < 3) {

            throw new IllegalArgumentException("[-] Error - Player() - Player : grid width or grid height is smaller than 3");

        }

        this.width = width;
        this.height = height;

        initializeMyGrid();
        initializeOpponentGrid();

	}

	/**
	 * This method allows to copy the fleet of the current player for modify it
	 * @param fleet - The list of ship of the current player
	 */

	protected void createCopy(ArrayList<Ship> fleet) {

		this.fleet = new ArrayList<Ship>();

		for (Ship s : fleet) {

			this.fleet.add(new Ship(s.getName(), s.getSize()));

		}

	}

	/**
	 * This method allows to create the battle ship grid of the current player
	 */

	protected void initializeMyGrid() {

		this.myGrid = new Square[this.width][this.height];

		for (int i = 0; i < myGrid.length; i++) {

			for (int j = 0; j < myGrid[i].length; j++) {

				myGrid[i][j] = new Square(i, j);

			}

		}

	}

	/**
	 * This method allows to create the battle ship grid of the opponent
	 */

	protected void initializeOpponentGrid() {

		this.opponentGrid = new Square[this.width][this.height];

		for (int i = 0; i < opponentGrid.length; i++) {

			for (int j = 0; j < opponentGrid[i].length; j++) {

				opponentGrid[i][j] = new Square(i, j);

			}

		}

	}

	/**
	 * This method allows to know if a square is hit or not
	 */

	public boolean isSunk(int x, int y) {

		boolean sunk = false;
		boolean found = false;

		Iterator<Ship> it = this.fleet.iterator();

		while (it.hasNext() && !found) {

			Ship s = it.next();

			if (x == s.getX() && y == s.getY()) {

				sunk = s.isSunk();
				found = true;

			}

		}

		return sunk;

	}

	/**
	 * This method allows to know if all the fleet of a player is sunk
	 * @return true if all the fleet of the current player is sunk or false
	 */

	public boolean allSunk() {

		boolean allSunk = true;

		Iterator<Ship> it = this.fleet.iterator();

		while (it.hasNext() && allSunk) {

			Ship s = it.next();

			if (!s.isSunk()) {

				allSunk = false;

			}

		}

		return allSunk;

	}

	/**
	 * This method allows to display the grid of the current player
	 */

	public void displayMygrid() {
		myOtframe = new GridTableFrame(myGrid);
		myOtframe.showIt("MyGrid");
	}

	/**
	 * This method allows to display the grid of the opponent player
	 */

	public void displayOpponentGrid() {
		opponentOtframe = new GridTableFrame(opponentGrid);
		opponentOtframe.showIt("OpponentGrid");
	}

	public abstract int[] newShot();
	public abstract void shipPlacement();

}
