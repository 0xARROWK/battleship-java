package battle;

/**
 * This class allows to create a ship with his parameters : direction, position, name, size and number of hit
 * @author 0xARK
 * @version 1.0.0
 */

public class Ship {

	private String name;
	private int size;
	private int xOrigin;
	private int yOrigin;
	private int hitNumber;
	private Direction direction;

	/**
	 * The constructor : it initialize a ship with default values or with the values passed in parameters
	 * @param name - The name of the ship
	 * @param size - The size of the ship
	 */

	public Ship(String name, int size) {

		this.name = "";
		this.size = 0;
		this.xOrigin = 0;
		this.yOrigin = 0;
		this.hitNumber = 0;
		this.direction = null;

		if (name != null && !name.equals("") && size > 0) {

			this.name = name;
			this.size = size;

		}

	}

	/**
	 * This method allows to place the current ship at a new x coordinate
	 * @param x - The new x coordinate
	 */

	public void setX(int x) {

		this.xOrigin = x;

	}

	/**
	 * This method allows to place the current ship at a new y coordinate
	 * @param y - The new y coordinate
	 */

	public void setY(int y) {

		this.yOrigin = y;

	}

	/**
	 * This method allows to change the dircetion of the current ship
	 * @param d - The new direction
	 */

	public void setDirection(Direction d) {

		this.direction = d;

	}

	/**
	 * This method allows to get the x coordinate of a ship
	 * @return The x coordinate of the current ship ship
	 */

	public int getX() {

		return this.xOrigin;

	}

	/**
	 * This method allows to get the y coordinate of a ship
	 * @return The y coordinate of the current ship ship
	 */

	public int getY() {

		return this.yOrigin;

	}

	/**
	 * This method allows to get the direction of a ship
	 * @return The direction of the current ship ship
	 */

	public Direction getDirection() {

		return this.direction;

	}

	/**
	 * This method allows to access to the name of the current ship
	 */

	public String getName() {

		return this.name;

	}

	/**
	 * This method allows to access to the size of the current ship
	 */

	public int getSize() {

		return this.size;

	}

	/**
	 * This method allows to display the current ship configuration
	 * @return The string with the name and the size of this ship
	 */

	public String toString() {

		return "\nName : " + name + "\nSize : " + size + "\nX-Origin : " + xOrigin + "\nY-Origin : " + yOrigin + "\nDirection : " + direction + "\nNumber of hits : " + hitNumber;

	}

	/**
	 * This method allows to add a hit to the current ship
	 */

	public void addHit() {

		this.hitNumber++;

	}

	/**
	 * This method allows to verify if the current ship is sunk or not
	 * @return true if the ship is sunk (number of hit equal to his size) or false
	 */

	public boolean isSunk() {

		boolean sunk = false;

		if (this.hitNumber == this.size) {

			sunk = true;

		}

		return sunk;

	}

	/**
	 * This method allows to look if a shot touch the current ship
	 * @param x - The x coordinate of the shot
	 * @param y - The y coordinate of the shot
	 * @return true if the shot touch the current ship or false
	 */

	public boolean contains(int x, int y) {

		boolean contain = false;

		if (this.direction == Direction.HORIZONTAL) {

			int lastX = this.xOrigin - size;

			if (this.yOrigin == y && (x >= xOrigin && x <= lastX)) {

				contain = true;

			}

		} else if (this.direction == Direction.VERTICAL) {

			int lastY = this.yOrigin - size;

			if (this.xOrigin == x && (y >= yOrigin && y <= lastY)) {

				contain = true;

			}

		}

		return contain;

	}

}
