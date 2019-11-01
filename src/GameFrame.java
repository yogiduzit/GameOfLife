package src;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import Lifeforms.Lifeform;


/**
 * Responsible for displaying the shapes in
 * GUI format.
 * @author yogeshverma
 *
 */
public class GameFrame extends JFrame {
	
	private World world;
	
	public static int turnCount;
	
	private final int cellWidth;
	
	private final int cellHeight;
	
	private static final long serialVersionUID = -3241700680132651844L;
	
	
	/**
	 * Create the frame to display the world.
	 * @param world the World
	 */
	public GameFrame(World world) {
		super("Assignment 2a");
		
		turnCount = 0;
		
		this.world = world;
		this.cellWidth = this.getWidth() / this.world.getWidth();
		this.cellHeight = this.getHeight() / this.world.getHeight();
		
		/*
		 * Adding an event listener to the cell. 
		 */
		this.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				onClick();
			}
		});
		
		GridLayout layout = new GridLayout(this.world.getWidth(), this.world.getHeight());
		this.setLayout(layout);
	}
	
	/**
	 * Initializes the world.
	 */
	public void init() {
		for (int i = 0; i < this.world.getHeight(); i++) {
			for (int j = 0; j < this.world.getWidth(); j++) {
				Cell cell = this.world.getWorldGrid()[i][j];
				
				cell.addMouseListener(new MouseAdapter() {
					public void mousePressed(MouseEvent e) {
						onClick();
					}
				});
			}
		}
		this.displayGrid();
	}
	
	/**
	 * Updates the game on click.
	 */
	private void update() {
		getContentPane().removeAll();
		
		for (int i = 0; i < this.world.getHeight(); i++) {
			for (int j = 0; j < this.world.getWidth(); j++) {
				Cell cell = this.world.getWorldGrid()[i][j];
				cell.update();
			}
		}
		this.displayGrid();
		this.revalidate();
		this.repaint();

	}
	
	/**
	 * Display the world grid as GUI, in JFrame.
	 */
	private void displayGrid() {
		for (int i = 0; i < this.world.getHeight(); i++) {
			for (int j = 0; j < this.world.getWidth(); j++) {
				Cell cell = this.world.getWorldGrid()[i][j];
				setButtonAppearance(cell);
				setButtonOrientation(cell, i, j, this.cellWidth, this.cellHeight);
				this.add(cell);
			}
		}
	}
	
	/**
	 * Set the appearance of the button.
	 * @param cell, A cell
	 */
	private void setButtonAppearance(Cell cell) {
		Lifeform resident = cell.getResident();
		
		cell.setBackground(Color.WHITE);
		
		/* Set the color if this cell has a resident */
		if (resident != null && resident.getColor() != null) {
			Color buttonColor = resident.getColor();
			cell.setBackground(buttonColor);

		}
		cell.setOpaque(true);
	}
	
	/**
	 * Set the orientation of the cell.
	 * @param cell, a Cell
	 * @param y, the yCoordinate in grid
	 * @param x, the xCoordinate in grid
	 * @param width, width of the grid
	 * @param height, height of the grid
	 */
	private void setButtonOrientation(Cell cell, int y, int x, int width, int height) {
		/* Setting the dimensions */
		cell.setBounds(x * width, y * height, width, height);
	}
	
	/**
	 * Decides what happens on button click.
	 */
	private void onClick() {
		turnCount += 1;
		this.update();
	}
}