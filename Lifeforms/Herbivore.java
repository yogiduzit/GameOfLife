package Lifeforms;
/**
 * 
 */

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import src.Cell;

/**
 * Defines a plant-eating lifeform
 * @author yogeshverma
 */
public class Herbivore extends Lifeform implements Animal {
	
	private int health;
	
	public Herbivore(final Cell cell) {
		super(cell);
		this.health = 100;
		this.color = Color.YELLOW;
	}
	
	/**
	 * Update on click.
	 */
	public void update() {
		this.turnCount++;
		
		if (this.health == 0) {
			this.die();
			return;
		}
		
		List<Cell> neighbours = this.currCell.getNeighbours();
		List<Cell> emptyNeighbours = new ArrayList<Cell>();
		
		/*
		 * Filter the neighbouring cells that 
		 * are either empty or contain a plant
		 * as a resident.
		 */
		for (int i = 0; i < neighbours.size(); i++) {
			Cell cell = neighbours.get(i);
			if (cell.getResident() == null || 
					!(cell.getResident() instanceof Herbivore)){
				emptyNeighbours.add(neighbours.get(i));
			}
		}
		
		if (emptyNeighbours.size() == 0) {
			return;
		}
		
		/*
		 * Pick a random empty cell.
		 */
		int random = (int) (Math.random() * emptyNeighbours.size());
		Cell neighbour = emptyNeighbours.get(random);
		
		if (neighbour.getResident() instanceof Plant) {
			eat(neighbour);
		} else if (neighbour.getResident() == null) {
			move(neighbour);
		}
	}
	
	/**
	 * Move to a random adjacent cell.
	 * @param neighbour, the neighbouring cell.
	 */
	private void move(Cell neighbour) {
		Lifeform resident = this;
		this.health -= 20;
		
		this.currCell.setResident(null);
		this.setCell(neighbour);
		neighbour.setResident(resident);
		
	}
	
	/**
	 * Eat a neighbouring plant.
	 */
	public void eat(Cell neighbour) {
		move(neighbour);
		if (health < 100) {
			this.health += 20;
		}
	}
}
