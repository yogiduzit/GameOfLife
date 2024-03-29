package src;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import Lifeforms.Lifeform;

/**
 * 
 */

/**
 * Holds a resident.
 * @author yogeshverma
 */
public class Cell extends JPanel {
	
	/**
	 * Class UID that must be contained in all children classes
	 * of JPanel.
	 */
	private static final long serialVersionUID = -1095172567198019636L;
	
	private Lifeform resident;
	private Cell[][] worldGrid;
	public int x;
	public int y;
	
	/**
	 * Constructs a cell with the given coordinates
	 * @param worldGrid, a Cell[][]
	 * @param x, an int
	 * @param y, an int
	 */
	public Cell(final Cell[][] worldGrid, final int x, final int y) {
		this.worldGrid = worldGrid;
		this.x = x;
		this.y = y;
	}
	
	/**
	 * A reference to the grid of cells.
	 * @return
	 */
	public Cell[][] getWorldGrid() {
		return this.worldGrid;
	}

	/**
	 * Get the current resident of the cell.
	 * @return resident, a Lifeform
	 */
	public Lifeform getResident() {
		return resident;
	}
	
	/**
	 * Sets the resident of the cell.
	 * @param resident, a Lifeform
	 */
	public void setResident(Lifeform resident) {
		this.resident = resident;
	}
	
	/**
	 * Checks if the cell contains a resident.
	 * @return a boolean
	 */
	public boolean isOccupied() {
		return this.resident != null;
	}
	
	/**
	 * Update cell on click.
	 */
	public void update() {
		if (this.resident != null && resident.turnCount != GameFrame.turnCount) {
			resident.update();
		}
	}
	
	/**
	 * Returns neighbouring cells.
	 * @return neighbours, an ArrayList that contains neighbouring cells.
	 */
	public List<Cell> getNeighbours() {
		ArrayList<Cell> neighbours = new ArrayList<Cell>();
		
		/*
		 * Imagine a 3x3 square outside of particular element. 
		 * For the elements near the corner, their won't be any neighbours
		 * on certain sides.
		 */
		for (int neighbourX = this.x - 1; neighbourX <= this.x + 1; neighbourX++) {
			for (int neighbourY = this.y - 1; neighbourY <= this.y + 1; neighbourY++) {
				
				/*
				 * Enforcing conditions to check indices to
				 * prevent any exceptions.
				 */
				if (neighbourY >= 0 && neighbourX >= 0
					&& neighbourY < this.worldGrid.length && neighbourX < this.worldGrid[0].length
					&& !(neighbourY == y && neighbourX == x)) {
					
					neighbours.add(this.worldGrid[neighbourY][neighbourX]);
					
				}
			}
		}
		return neighbours;
	}
	
	/**
	 * Filters a list of cells and returns unoccupied cells.
	 * @param cells, a list of cells
	 * @return unoccupied cells.
	 */
	public static List<Cell> getEmptyCells(List<Cell> cells) {
		ArrayList<Cell> emptyCells = new ArrayList<Cell>();
		
		for (Cell cell : cells) {
			if (!cell.isOccupied()) {
				emptyCells.add(cell);
			}
		}
		 
		return emptyCells;
	}
	
	/**
	 * JPanel method to draw graphics
	 */
	public void paint(Graphics g) {
		super.paint(g);
		g.drawRect(0, 0, this.getWidth(), this.getHeight());
	}
}
