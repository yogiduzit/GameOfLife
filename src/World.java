package src;

import Lifeforms.Herbivore;
import Lifeforms.Plant;

/**
 * 
 */

/**
 * @author yogeshverma
 *
 */
public class World {
	
	private int width;
	
	private int height;
	
	private Cell[][] worldGrid;
	
	/**
	 * Creates a world.
	 * @param width
	 * @param height
	 */
	public World(final int width, final int height) {
		this.width = width;
		this.height = height;
		this.worldGrid = new Cell[this.height][this.width];
	}
	
	/**
	 * Initializes the world for the first time. 
	 */
	public void init() {
		for (int i = 0; i < this.height; i++) {
			for (int j = 0; j < this.width; j++) {
				this.worldGrid[i][j] = new Cell(this.worldGrid, j, i);
				int random = (int) (Math.random() * 100);
				
				/*
				 * The world has a 15% chance to spawn a herbivore and
				 * 20% chance to spawn a plant.
				 */
				if (random >= 85) {
					this.worldGrid[i][j].setResident(new Herbivore(this.worldGrid[i][j]));
				} else if (random >= 65) {
					this.worldGrid[i][j].setResident(new Plant(this.worldGrid[i][j]));
				}
			}
		}
	}
	
	/**
	 * Width getter
	 * @return width, an int
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Height getter
	 * @return height, an int
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * World grid getter
	 * @return worldGrid, a Cell[][]
	 */
	public Cell[][] getWorldGrid() {
		return worldGrid;
	}

	
}
