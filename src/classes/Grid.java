package classes;

import java.util.ArrayList;

public class Grid {
	private ArrayList<Cell> cells;
	private int width;
	private int height;
	private Cell[][] grid;

	public Grid(ArrayList<Cell> cells, int w, int h) throws CellCoordinateOutOfBoundariesException {
		setWidth(w);
		setHeight(h);
		grid = new Cell[height][width];
		this.cells = cells;

		// Carica le celle nella griglia
		for (int i = 0; i < cells.size(); i++) {
			if (cells.get(i).getX() > w || cells.get(i).getY() > h)
				throw new CellCoordinateOutOfBoundariesException("");
			else
				grid[cells.get(i).getX()][cells.get(i).getY()] = cells.get(i);
		}

	}

	public Cell[][] getGrid() {
		return grid;
	}

	public void setGrid(Cell[][] grid) {
		this.grid = grid;
	}

	public Grid(int w, int h) throws CellCoordinateOutOfBoundariesException {
		this(new ArrayList<Cell>(), w, h);
	}

	public String print() {
		return null;
	}

	public Grid tick() throws NegativeCoordinateException {
		ArrayList<Cell> tickedCells = new ArrayList<Cell>();

		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				if (grid[i][j].isAlive() && countAliveNeighbors(i, j) < 2)
					tickedCells.add(new Cell(i, j, false));
				else if (grid[i][j].isAlive() && (countAliveNeighbors(i, j) == 2 || countAliveNeighbors(i, j) == 3))
					tickedCells.add(new Cell(i, j, true));
				else if (grid[i][j].isAlive() && countAliveNeighbors(i, j) > 3)
					tickedCells.add(new Cell(i, j, false));
				else if (!grid[i][j].isAlive() && countAliveNeighbors(i, j) == 3)
					tickedCells.add(new Cell(i, j, true));
			}
		}

		return new Grid(tickedCells, width, height);
	}

	private int countAliveNeighbors(int x, int y) {
		int count = 0;

		// Upper-Left Angle
		if (x == 0 && y == 0) {
			if (grid[x][y + 1].isAlive())
				count++;
			if (grid[x + 1][y].isAlive())
				count++;
			if (grid[x + 1][y + 1].isAlive())
				count++;
		}

		// Upper Side
		else if (x == 0 && y != 0 && y < width - 1) {

			if (grid[x][y - 1].isAlive())
				count++;
			if (grid[x][y + 1].isAlive())
				count++;
			if (grid[x + 1][y - 1].isAlive())
				count++;
			if (grid[x + 1][y].isAlive())
				count++;
			if (grid[x + 1][y + 1].isAlive())
				count++;
		}

		else if (y == 0 && x != 0 && x < height - 1) {

			if (grid[x - 1][y].isAlive())
				count++;
			if (grid[x + 1][y].isAlive())
				count++;
			if (grid[x - 1][y + 1].isAlive())
				count++;
			if (grid[x][y + 1].isAlive())
				count++;
			if (grid[x + 1][y + 1].isAlive())
				count++;
		}

		// Left Side

		return count;
	}

	public ArrayList<Cell> getCells() {
		return cells;
	}

	public void setCells(ArrayList<Cell> cells) {
		this.cells = cells;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
}
