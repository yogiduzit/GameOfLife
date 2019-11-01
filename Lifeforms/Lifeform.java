package Lifeforms;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import src.Cell;
import src.GameFrame;

/**
 * 
 */

/**
 * Defines an organism along with abstract methods.
 * @author yogeshverma
 */
public abstract class Lifeform {
	
	protected int health;
	
	protected Color color;
	
	protected Cell currCell;
	
	public int turnCount;
	
	/**
	 * Constructs the lifeform<br>
	 * <h1>Initializes</h1>
	 * <ul>
	 * <li> current cell that this lifeform belongs to</li>
	 * <li> turnCount, current turnCount</li>
	 * </ul>
	 * @param cell
	 */
	public Lifeform(final Cell cell) {
		this.currCell = cell;
		this.turnCount = GameFrame.turnCount;

	}
	
	/** Controls how a lifeform will update on click. */
	public abstract void update();
	
	
	public void die() {
		this.currCell.setResident(null);
	}

	public Color getColor() {
		return this.color;
	}
	
	public void setCell(Cell cell) {
		this.currCell = cell;
	}
	
}
