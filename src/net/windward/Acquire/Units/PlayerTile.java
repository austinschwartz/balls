/*
 * ----------------------------------------------------------------------------
 * "THE BEER-WARE LICENSE"
 * As long as you retain this notice you can do whatever you want with this
 * stuff. If you meet an employee from Windward meet some day, and you think
 * this stuff is worth it, you can buy them a beer in return. Windward Studios
 * ----------------------------------------------------------------------------
 */

package net.windward.Acquire.Units;

/**
 * A tile held by a player or waiting to be drawn by a player. There is one for each tile location on the board.
 */
public class PlayerTile {

	private int x;

	private int y;

	public PlayerTile(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * The X location on the board. (In the board game this has values 1..12 but this property is 0 based.)
	 */
	public int getX() {
		return x;
	}

	/**
	 * The Y location on the board. (In the board game this has values A..I.)
	 */
	public int getY() {
		return y;
	}

	public String toString() {
		return "[" + x + ',' + y + ']';
	}
}
