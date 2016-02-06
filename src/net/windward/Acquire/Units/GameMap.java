/*
 * ----------------------------------------------------------------------------
 * "THE BEER-WARE LICENSE"
 * As long as you retain this notice you can do whatever you want with this
 * stuff. If you meet an employee from Windward meet some day, and you think
 * this stuff is worth it, you can buy them a beer in return. Windward Studios
 * ----------------------------------------------------------------------------
 */

package net.windward.Acquire.Units;

import net.windward.Acquire.TRAP;
import org.dom4j.Element;

import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * The game board/map.
 */
public class GameMap {

	private MapTile [] [] tiles;

	private int height;

	private int width;

	public GameMap(int width, int height) {
		this.width = width;
		this.height = height;

		tiles = new MapTile[width][];
		for (int x = 0; x < width; x++)
		{
			tiles[x] = new MapTile[height];
			for (int y = 0; y < height; y++)
				tiles[x][y] = new MapTile(x, y);
		}
	}

	/**
	 * The map squares. This is in the format [x][y].
	 * @param x The 0-based x location on the board of the requested tile.
	 * @param y The 0-based y location on the board of the requested tile.
	 */
	public MapTile getTiles(int x, int y) {
		return tiles[x][y];
	}

	/**
	 * The number of tiles on the board/map in the y direction.
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * The number of tiles on the board/map in the x direction.
	 */
	public int getWidth() {
		return width;
	}

	public Boolean IsTileUndeveloped(PlayerTile tile)
	{
		return tiles[tile.getX()][tile.getY()].getType() == MapTile.TYPE_UNDEVELOPED;
	}

	public Boolean IsTileUnplayable(PlayerTile tile)
	{
		return tiles[tile.getX()][tile.getY()].getType() != MapTile.TYPE_UNDEVELOPED &&
				tiles[tile.getX()][tile.getY()].getType() != MapTile.TYPE_UNPLAYABLE_NO_AVAIL_CHAINS;
	}

	public static GameMap fromXml(Element elemMap, ArrayList<HotelChain> hotels) {

		GameMap map = new GameMap(Integer.parseInt(elemMap.attribute("width").getValue()), Integer.parseInt(elemMap.attribute("height").getValue()));

		int x = 0;
		for (Object elem : elemMap.elements("column")) {
			Element elemColOn = (Element) elem;
			StringTokenizer tokRows = new StringTokenizer(elemColOn.getStringValue(), ";");
			int y = 0;
			while (tokRows.hasMoreElements()) {
				String strTile = tokRows.nextToken();
				StringTokenizer tokParts = new StringTokenizer(strTile, ":");
				int type = Integer.parseInt(tokParts.nextToken());
				if (!tokParts.hasMoreElements())
					map.tiles[x][y] = new MapTile(type, x, y);
				else {
					String hotelName = tokParts.nextToken();
					HotelChain hotel = null;
					for (HotelChain h : hotels)
						if (h.getName().equals(hotelName)) {
							hotel = h;
							break;
						}
					map.tiles[x][y] = new MapTile(type, hotel, x, y);
				}
				y++;
			}
			x++;
		}

		return map;
	}
}
