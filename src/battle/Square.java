package battle;

/**
 * This class allows to create a battle ship grid square by square
 * @author 0xARK
 * @version 1.0.0
 */

public class Square {

	private int x;
	private int y;
	private boolean free;
	private boolean hit;

	public Square(int x, int y) {

		this.free = true;
		this.hit = false;

		if (x < 0 || y < 0) {

			throw new IllegalArgumentException("[-] Error - Square() : x or y is smaller than 0");

		}

	}

	/**
	 * This method allows to declare a square as not free
	 */

	public void setBusy() {

		this.free = false;

	}

	/**
	 * This method allows to declare a square as hit
	 */

	public void setHit() {

		this.hit = true;

	}

	/**
	 * This method allows to get the x position of a square
	 * @return The x position of the current square
	 */

	public int getX() {

		return this.x;

	}

	/**
	 * This method allows to get the y position of a square
	 * @return The y position of the current square
	 */

	public int getY() {

		return this.y;

	}

	/**
	 * This method allows to verify if a square is free or not
	 * @return true if the square is free or false
	 */

	public boolean isFree() {

		return this.free;

	}

	/**
	 * This method allows to verify if a square is hit or not
	 * @return true if the square is hit or false
	 */

	public boolean isHit() {

		return this.hit;

	}

	/**
	 * This method allows to display all information about a square
	 * @return The information about the current square
	 */

	public String toString() {

		return "\n(x, y) : (" + this.x + ", " + this.y + ")\nFree : " + this.free + "\nHit : " + this.hit;

	}

}
