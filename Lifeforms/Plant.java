package Lifeforms;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import src.Cell;

/**
 * 
 */

/**
 * @author yogeshverma
 *
 */
public class Plant extends Lifeform {
	
	/**
	 * Creates a plant.
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
		
		if (neighbours.size() == 0 || !canPollinate(neighbours)) {
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
	 * @param neighbours
	 * @return
	 */
	private boolean canPollinate(List<Cell> neighbours) {
		
		int plantCount = 0;
		int emptyCount = 0;
		
		for (int i = 0; i < neighbours.size(); i++) {
			Cell cell = neighbours.get(i);
			if (cell.getResident() == null) {
				emptyCount++;
			} else if (cell.getResident() instanceof Plant) {
				plantCount++;
			}
		}
		if (plantCount == 4 & emptyCount >= 3) {
			return true;
		}
		return false;
	}

}
