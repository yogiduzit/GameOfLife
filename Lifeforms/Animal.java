/**
 * 
 */
package Lifeforms;

import java.util.List;

import src.Cell;

/**
 * @author yogeshverma
 *
 */
abstract class Animal extends Lifeform {
	
	public Animal(Cell cell) {
		super(cell);
	}
	/**
	 * Can eat other lifeforms.
	 * @param neighbour, an adjacent cell
	 */
	abstract void eat(Cell neighbour);
	abstract void move(Cell neighbour);
	abstract List<Cell> getMovableNeighbours(List<Cell> cells);
	
}
