/**
 * 
 */
package Lifeforms;

import src.Cell;

/**
 * @author yogeshverma
 *
 */
public interface Animal {
	/**
	 * Can eat other lifeforms.
	 * @param neighbour, an adjacent cell
	 */
	void eat(Cell neighbour);
}
