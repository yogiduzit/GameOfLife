/**
 * 
 */
package Lifeforms;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import src.Cell;

/**
 * @author yogeshverma
 *
 */
public class Carnivore extends Animal implements OmniEdible {
	
	public Carnivore(Cell cell) {
		super(cell);
		this.health = 100;
		this.color = Color.RED;
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
		
		if (neighbour.getResident() instanceof CarnEdible) {
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
			if (cell.getResident() == null || cell.getResident() instanceof CarnEdible){
				filteredNeighbours.add(cell);
			}
		}
		
		return filteredNeighbours;
	}
	
	protected void giveBirth() {
		List<Cell> neighbours = this.currCell.getNeighbours();
		
		if (neighbours.size() == 0 || !canGiveBirth(neighbours, 1, 3, 2)) {
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
	
	boolean canGiveBirth(List<Cell> neighbours, int reqLifeform, int reqEmpty, int reqFood) {
		
		int carnCount = 0;
		int emptyCount = 0;
		int edibleCount = 0;
		
		for (Cell cell : neighbours) {
			if (cell.getResident() == null) {
				emptyCount++;
			} else if (cell.getResident() instanceof Carnivore) {
				carnCount++;
			} else if (cell.getResident() instanceof CarnEdible) {
				edibleCount++;
			}
		}
		if (carnCount >= reqLifeform && emptyCount >= reqEmpty && edibleCount >= reqFood) {
			return true;
		}
		return false;
	}

}
