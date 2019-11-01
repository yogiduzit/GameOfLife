package Lifeforms;
/**
 * 
 */

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import src.Cell;

/**
 * @author yogeshverma
 *
 */
public class Herbivore extends Lifeform implements Animal {
	
	private int health;
	
	public Herbivore(final Cell cell) {
		super(cell);
		this.health = 100;
		this.color = Color.YELLOW;
	}
	
	public void update() {
		this.turnCount++;
		if (this.health == 0) {
			this.die();
			return;
		}
		
		List<Cell> neighbours = this.currCell.getNeighbours();
		List<Cell> emptyNeighbours = new ArrayList<Cell>();
		
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
		
		
		int random = (int) (Math.random() * emptyNeighbours.size());
		Cell neighbour = emptyNeighbours.get(random);
		
		if (neighbour.getResident() instanceof Plant) {
			eat(neighbour);
		} else if (neighbour.getResident() == null) {
			move(neighbour);
		}
	}
	
	private void move(Cell neighbour) {
		Lifeform resident = this;
		this.health -= 20;
		
		this.currCell.setResident(null);
		this.setCell(neighbour);
		neighbour.setResident(resident);
		
	}
	
	public void eat(Cell neighbour) {
		move(neighbour);
		if (health < 100) {
			this.health += 20;
		}
		
	}
}
