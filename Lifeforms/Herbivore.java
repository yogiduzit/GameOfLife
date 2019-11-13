package Lifeforms;
/**
 * 
 */

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import src.Cell;
import src.RandomGenerator;

/**
 * Defines a plant-eating lifeform
 * @author yogeshverma
 */
public class Herbivore extends Animal implements CarnEdible, OmniEdible {
	
	public Herbivore(final Cell cell) {
		super(cell);
		this.health = 100;
		this.color = Color.YELLOW;
	}
	
	/**
	 * Update on click.
	 */
	public void update() {
		super.update();
		
		if (this.health == 0) {
			this.die();
			return;
		}
		
		this.health -= 20;
		
		List<Cell> neighbours = this.currCell.getNeighbours();
		List<Cell> filteredNeighbours = this.getMovableNeighbours(neighbours);
		
		if (filteredNeighbours.size() == 0) {
			return;
		}
		
		/*
		 * Pick a random empty cell.
		 */
		int random = (int) (Math.random() * filteredNeighbours.size());
		Cell neighbour = filteredNeighbours.get(random);
		
		if (neighbour.getResident() instanceof HerbEdible) {
			eat(neighbour);
		} else if (neighbour.getResident() == null) {
			move(neighbour);
		}
		
		this.giveBirth();
	}
	
	/**
	 * Move to a random adjacent cell.
	 * @param neighbour, the neighbouring cell.
	 */
	protected void move(Cell neighbour) {
		Lifeform resident = this;
		
		this.currCell.setResident(null);
		this.setCell(neighbour);
		neighbour.setResident(resident);
		
	}
	
	/**
	 * Eat a neighbouring plant.
	 */
	protected void eat(Cell neighbour) {
		move(neighbour);
		if (health < 100) {
			this.health = 100;
		}
	}
	
	List<Cell> getMovableNeighbours(List<Cell> cells) {
		List<Cell> filteredNeighbours = new ArrayList<Cell>();
		
		/*
		 * Filter the neighbouring cells that 
		 * are either empty or contain a plant
		 * as a resident.
		 */
		for (Cell cell : cells) {
			if (cell.getResident() == null || cell.getResident() instanceof HerbEdible){
				filteredNeighbours.add(cell);
			}
		}
		
		return filteredNeighbours;
	}
	
	protected void giveBirth() {
		List<Cell> neighbours = this.currCell.getNeighbours();
		
		if (neighbours.size() == 0 || !canGiveBirth(neighbours, 1, 2, 2)) {
			return;
		}
		
		List<Cell> emptyNeighbours = Cell.getEmptyCells(neighbours);
		int emptyIndex = (int) (Math.random() * emptyNeighbours.size());
		if (emptyIndex != -1) {
			Cell emptyCell = emptyNeighbours.get(emptyIndex);
			Herbivore herbivore = new Herbivore(emptyCell);
			emptyCell.setResident(herbivore);
		}
	}
	
	boolean canGiveBirth(List<Cell> neighbours, int reqLifeform, int reqEmpty, int reqFood) {
		
		int herbCount = 0;
		int emptyCount = 0;
		int edibleCount = 0;
		
		for (Cell cell : neighbours) {
			if (cell.getResident() == null) {
				emptyCount++;
			} else if (cell.getResident() instanceof Herbivore) {
				herbCount++;
			} else if (cell.getResident() instanceof HerbEdible) {
				edibleCount++;
			}
		}
		if (herbCount >= reqLifeform && emptyCount >= reqEmpty && edibleCount >= reqFood) {
			return true;
		}
		return false;
	}
}
