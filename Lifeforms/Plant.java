package Lifeforms;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import src.Cell;

/**
 * 
 */

/**
 * A static lifeform that pollinates periodically
 * @author yogeshverma
 */
public class Plant extends Lifeform {
	
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
		spawn();
	}
	
	/**
	 * Spawn a plant in a random empty neighbouring cell.
	 * neighbours meet the conditions.
	 */
	public void spawn() {
		List<Cell> neighbours = this.currCell.getNeighbours();
		
		if (neighbours.size() == 0 || !canPollinate(neighbours, 4, 3)) {
			return;
		}
		
		int emptyIndex = (int) Math.random() * Cell.getEmptyCells(neighbours).size();
		if (emptyIndex != -1) {
			Cell emptyCell = neighbours.get(emptyIndex);
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
	private boolean canPollinate(List<Cell> neighbours, int reqPlant, int reqEmpty) {
		
		int plantCount = 0;
		int emptyCount = 0;
		
		for (Cell cell : neighbours) {
			if (cell.getResident() == null) {
				emptyCount++;
			} else if (cell.getResident() instanceof Plant) {
				plantCount++;
			}
		}
		if (plantCount == reqPlant & emptyCount >= reqEmpty) {
			return true;
		}
		return false;
	}

}
