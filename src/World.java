package src;

import Lifeforms.Carnivore;
import Lifeforms.Herbivore;
import Lifeforms.Omnivore;
import Lifeforms.Plant;

/**
 * 
 */

/**
 * Consists of a collection of cells.
 * @author yogeshverma
 */
public class World {
	
	private int width;
	
	private int height;
	
	private Cell[][] worldGrid;
	
	/**
	 * Initializes a collection of cells.
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
				int random = (int) (Math.random() * 10000);
				
				/*
				 * The world has a 20% chance to spawn a herbivore and
				 * 10% chance to spawn a plant, 10% chance to spawn a carnivore
				 * and 5% chance to spawn an omnivore upon initialization.
				 */
				if (random >= 8000) {
					this.worldGrid[i][j].setResident(new Herbivore(this.worldGrid[i][j]));
				} else if (random >= 6000) {
					this.worldGrid[i][j].setResident(new Plant(this.worldGrid[i][j]));
				} else if (random >= 5000) {
					this.worldGrid[i][j].setResident(new Carnivore(this.worldGrid[i][j]));
				} else if (random >= 4500) {
					this.worldGrid[i][j].setResident(new Omnivore(this.worldGrid[i][j]));
				}
			}
		}
	}
	
	/**
	 * Return the width of the world.
	 * @return width, an int
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Returns the height of the world.
	 * @return height, an int
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Returns the 2D cell array i.e the world grid. 
	 * @return worldGrid, a Cell[][]
	 */
	public Cell[][] getWorldGrid() {
		return worldGrid;
	}

	
}
