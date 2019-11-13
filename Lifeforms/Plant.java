package Lifeforms;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import src.Cell;
import src.RandomGenerator;

/**
 * 
 */

/**
 * A static lifeform that pollinates periodically
 * @author yogeshverma
 */
public class Plant extends Lifeform implements HerbEdible, OmniEdible {
	
	/**
	 * Constructs a plant
	 * @param cell
	 */
	public Plant(final Cell cell) {
		super(cell);
		this.color = Color.GREEN;
	}
	
	/**
	 * Update on click.
	 */
	public void update() {
		this.turnCount++;
		this.giveBirth();
	}
	
	/**
	 * Spawn a plant in a random empty neighbouring cell.
	 * neighbours meet the conditions.
	 */
	protected void giveBirth() {
		List<Cell> neighbours = this.currCell.getNeighbours();
		
		if (neighbours.size() == 0 || !canGiveBirth(neighbours, 2, 3, 0)) {
			return;
		}
		
		List<Cell> emptyNeighbours = Cell.getEmptyCells(neighbours);
		int emptyIndex = (int) (Math.random() * emptyNeighbours.size());
		if (emptyIndex != -1) {
			Cell emptyCell = emptyNeighbours.get(emptyIndex);
			Plant plant= new Plant(emptyCell);
			emptyCell.setResident(plant);
		}
		
	}
	
	/**
	 * Checks if the plant can pollinate.
	 * @param neighbours, a collection of the adjacent cells
	 * @param reqPlant, no. of plants required for pollination
	 * @param reqEmpty, no. of empty cells required for pollination.
	 * @return
	 */
	boolean canGiveBirth(List<Cell> neighbours, int reqLifeform, int reqEmpty, int reqFood) {
		
		int plantCount = 0;
		int emptyCount = 0;
		
		for (Cell cell : neighbours) {
			if (cell.getResident() == null) {
				emptyCount++;
			} else if (cell.getResident() instanceof Plant) {
				plantCount++;
			}
		}
		if (plantCount >= reqLifeform & emptyCount >= reqEmpty) {
			return true;
		}
		return false;
	}

}
