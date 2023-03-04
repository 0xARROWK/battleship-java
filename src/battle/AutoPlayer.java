package battle;
import java.util.ArrayList;
import java.util.Random;

/**
 * This class allows to create a battle ship auto player
 * @author 0xARK
 * @version 1.0.0
 */

public class AutoPlayer extends Player {

	private int[][] allShot;

	/**
	 * The constructor : it allows to initialize the player constructor
	 * @param fleet - The fleet of the current auto player
	 * @param name - The name of the current auto player
	 * @param height - The height of the current grid game
	 * @param width - The width of the current grid game
	 */

	public AutoPlayer(ArrayList<Ship> fleet, String name, int height, int width) {

		super(fleet, name, height, width);
		shipPlacement();

	}

	/**
	 * This method allows to verifiy if a ship is to close of another (at least one square between two boats)
	 */

	public void shipPlacement() {

		this.allShot = new int[width][height];

		int totalCaseNb = this.width * this.height;
		int shipCaseNb = 0;

		for (Ship s : this.fleet) {

			shipCaseNb += 3 * s.getSize() + 6;

		}

		if ((shipCaseNb * 100)/totalCaseNb > 80) {

			throw new IllegalArgumentException("[-] Error - AutoPlayer : The grid is too small to place all these ship. Please modify his size in the configuration file.");

		}

		for (Ship s : this.fleet) {

			int[][] placementVerified = new int[this.width][this.height];

			Random r = new Random();

			boolean placement;
			int x = 0;
			int y = 0;
			Direction d;

			do {

				// we choose a direction

				if (r.nextBoolean()) {
					d = Direction.HORIZONTAL;
				} else {
					d = Direction.VERTICAL;
				}

				// we try to find new coordinates to place the current ship

				do {

					if (d == Direction.VERTICAL) {

						x = r.nextInt(this.width);
						y = r.nextInt(this.height - s.getSize());

					} else if (d == Direction.HORIZONTAL) {

						x = r.nextInt(this.width - s.getSize());
						y = r.nextInt(this.height);

					}

				} while (placementVerified[x][y] == 1);

				// we look if this placement is valid

				placement = checkPlacement(s, d, x, y);

				// if the first direction return false at these coordinates, we retry with the second direction

				if (!placement && d == Direction.VERTICAL) {
					if (this.width > x + s.getSize() - 1) {
						placement = checkPlacement(s, Direction.HORIZONTAL, x, y);
						if (placement) {
							d = Direction.HORIZONTAL;
						}
					}
				} else if (!placement && d == Direction.HORIZONTAL) {
					if (this.height > y + s.getSize() - 1) {
						placement = checkPlacement(s, Direction.VERTICAL, x, y);
						if (placement) {
							d = Direction.VERTICAL;
						}
					}
				}

				// we have already verified this placement

				placementVerified[x][y] = 1;

				// if the placement was not found, we look if we have verified all the placement possible

				if (!placement) {

					boolean allVerified = true;
					int i = 0;
					int j = 0;

					while (i < this.width && allVerified) {

						while (j < this.height && allVerified) {

							if (placementVerified[i][j] == 0) {

								allVerified = false;

							}

							j++;

						}

						i++;

					}

					if (allVerified) {
						throw new IllegalArgumentException("[-] Error - AutoPlayer : The grid is too small to place all these ship. Please modify his size in the configuration file.");
					}

				}

			} while (!placement);

			s.setDirection(d);
			s.setX(x);
			s.setY(y);

			if (s.getDirection() == Direction.HORIZONTAL) {

				for (int i = x; i < x + s.getSize(); i++) {

					this.myGrid[i][y].setBusy();

				}

			} else if (s.getDirection() == Direction.VERTICAL) {

				for (int i = y; i < y + s.getSize(); i++) {

					this.myGrid[x][i].setBusy();

				}

			}

		}

	}

	/**
	 * This method allows to verify if we can place a ship at the coordinate x,y
	 * @param s - The ship
	 * @param d - The direction of the ship
	 * @param x - The x coordinate of the ship
	 * @param y - The y coordinate of the ship
	 * @return true if we can place the ship here (at least on square between this ship and others ship) or false
	 */

	private boolean checkPlacement(Ship s, Direction d, int x, int y) {

		boolean valid = true;

		if (d == Direction.HORIZONTAL) {

			int lastX = x + s.getSize() - 1;
			int i = x;

			while (i <= lastX) {

				if (i == x) {

					if (x == 0) {

						if (y == 0) {

							if (!this.myGrid[i][y + 1].isFree()) {
								valid = false;
							}

						} else if (y == height - 1) {

							if (!this.myGrid[i][y - 1].isFree()) {
								valid = false;
							}

						} else {

							if (!this.myGrid[i][y + 1].isFree() || !this.myGrid[i][y - 1].isFree()) {
								valid = false;
							}

						}

					} else {

						if (y == 0) {

							if (!this.myGrid[i - 1][y].isFree() || !this.myGrid[i - 1][y + 1].isFree() || !this.myGrid[i][y + 1].isFree()) {
								valid = false;
							}

						} else if (y == height - 1) {

							if (!this.myGrid[i - 1][y].isFree() || !this.myGrid[i - 1][y - 1].isFree() || !this.myGrid[i][y - 1].isFree()) {
								valid = false;
							}

						} else {

							if (!this.myGrid[i - 1][y].isFree() || !this.myGrid[i - 1][y + 1].isFree() || !this.myGrid[i][y + 1].isFree() || !this.myGrid[i - 1][y - 1].isFree() || !this.myGrid[i][y - 1].isFree()) {
								valid = false;
							}

						}

					}

				}

				if (i == lastX) {

					if (lastX == width - 1) {

						if (y == 0) {

							if (!this.myGrid[i][y + 1].isFree()) {
								valid = false;
							}

						} else if (y == height - 1) {

							if (!this.myGrid[i][y - 1].isFree()) {
								valid = false;
							}

						} else {

							if (!this.myGrid[i][y + 1].isFree() || !this.myGrid[i][y - 1].isFree()) {
								valid = false;
							}

						}

					} else {

						if (y == 0) {

							if (!this.myGrid[i + 1][y].isFree() || !this.myGrid[i + 1][y + 1].isFree() || !this.myGrid[i][y + 1].isFree()) {
								valid = false;
							}

						} else if (y == height - 1) {

							if (!this.myGrid[i + 1][y].isFree() || !this.myGrid[i + 1][y - 1].isFree() || !this.myGrid[i][y - 1].isFree()) {
								valid = false;
							}

						} else {

							if (!this.myGrid[i + 1][y].isFree() || !this.myGrid[i + 1][y + 1].isFree() || !this.myGrid[i][y + 1].isFree() || !this.myGrid[i + 1][y - 1].isFree() || !this.myGrid[i][y - 1].isFree()) {
								valid = false;
							}

						}

					}

				}

				if (i != x && i != lastX) {

					if (y == 0) {

						if (!this.myGrid[i][y + 1].isFree()) {
							valid = false;
						}

					} else if (y == height - 1) {

						if (!this.myGrid[i][y - 1].isFree()) {
							valid = false;
						}

					} else {

						if (!this.myGrid[i][y + 1].isFree() || !this.myGrid[i][y - 1].isFree()) {
							valid = false;
						}

					}

				}

				i++;

			}

		} else if (d == Direction.VERTICAL) {

			int lastY = y + s.getSize() - 1;
			int i = y;

			while (i <= lastY) {

				if (i == y) {

					if (y == 0) {

						if (x == 0) {

							if (!this.myGrid[x + 1][i].isFree()) {
								valid = false;
							}

						} else if (x == width - 1) {

							if (!this.myGrid[x - 1][i].isFree()) {
								valid = false;
							}

						} else {

							if (!this.myGrid[x + 1][i].isFree() || !this.myGrid[x - 1][i].isFree()) {
								valid = false;
							}

						}

					} else {

						if (x == 0) {

							if (!this.myGrid[x][i - 1].isFree() || !this.myGrid[x + 1][i - 1].isFree() || !this.myGrid[x + 1][i].isFree()) {
								valid = false;
							}

						} else if (x == width - 1) {

							if (!this.myGrid[x - 1][i].isFree() || !this.myGrid[x - 1][i - 1].isFree() || !this.myGrid[x][i - 1].isFree()) {
								valid = false;
							}

						} else {

							if (!this.myGrid[x - 1][i].isFree() || !this.myGrid[x + 1][i - 1].isFree() || !this.myGrid[x + 1][i].isFree() || !this.myGrid[x - 1][i - 1].isFree() || !this.myGrid[x][i - 1].isFree()) {
								valid = false;
							}

						}

					}

				}

				if (i == lastY) {

					if (lastY == height - 1) {

						if (x == 0) {

							if (!this.myGrid[x + 1][i].isFree()) {
								valid = false;
							}

						} else if (x == width - 1) {

							if (!this.myGrid[x - 1][i].isFree()) {
								valid = false;
							}

						} else {

							if (!this.myGrid[x + 1][i].isFree() || !this.myGrid[x - 1][i].isFree()) {
								valid = false;
							}

						}

					} else {

						if (x == 0) {

							if (!this.myGrid[x + 1][i].isFree() || !this.myGrid[x + 1][i + 1].isFree() || !this.myGrid[x][i + 1].isFree()) {
								valid = false;
							}

						} else if (x == width - 1) {

							if (!this.myGrid[x - 1][i].isFree() || !this.myGrid[x - 1][i + 1].isFree() || !this.myGrid[x][i + 1].isFree()) {
								valid = false;
							}

						} else {

							if (!this.myGrid[x + 1][i].isFree() || !this.myGrid[x + 1][i + 1].isFree() || !this.myGrid[x][i + 1].isFree() || !this.myGrid[x - 1][i + 1].isFree() || !this.myGrid[x - 1][i].isFree()) {
								valid = false;
							}

						}

					}

				}

				if (i != y && i != lastY) {

					if (x == 0) {

						if (!this.myGrid[x + 1][i].isFree()) {
							valid = false;
						}

					} else if (x == width - 1) {

						if (!this.myGrid[x - 1][i].isFree()) {
							valid = false;
						}

					} else {

						if (!this.myGrid[x + 1][i].isFree() || !this.myGrid[x - 1][i].isFree()) {
							valid = false;
						}

					}

				}

				i++;

			}

		}

		return valid;

	}

	/**
	 * This method allows to make a shot on the opponent grid of the current player
	 * @return an int array with the x coordinate and the y coordinate
	 */

	public int[] newShot() {

		int[] coordinate = new int[2];

		Random r = new Random();

		int x = r.nextInt(this.width);
		int y = r.nextInt(this.height);

		while (allShot[x][y] == 1) {

			x = r.nextInt(this.width);
			y = r.nextInt(this.height);

		}

		allShot[x][y] = 1;

		coordinate[0] = x;
		coordinate[1] = y;

		return coordinate;

	}

}
