package _04_Maze_Maker;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class MazeMaker {

    private static int rows;
    private static int cols;

    private static Maze maze;

    private static Random randGen = new Random();
    private static Stack<Cell> uncheckedCells = new Stack<Cell>();

    public static Maze generateMaze(int r, int c) {
        rows = r;
        cols = c;
        maze = new Maze(rows, cols);

        // 1. Pick a random cell along the border and remove its exterior wall.
        //    This will be the starting point. Then select a random cell along
        //    the opposite wall and remove its exterior wall. This will be the
        //    finish line.
        int randomColumn1 = randGen.nextInt(c);
        int randomColumn2 = randGen.nextInt(c);

        maze.getCell(0, randomColumn1).setNorthWall(false);
        maze.getCell(4, randomColumn2).setSouthWall(false);
        // 2. select a random cell in the maze to start 
        int randomCellColumn = randGen.nextInt(c);
        int randomCellRow = randGen.nextInt(r);

        // 3. call the selectNextPath method with the randomly selected cell
        selectNextPath(maze.getCell(randomCellRow, randomCellColumn));

        return maze;
    }

    // 4. Complete the selectNextPathMethod
    private static void selectNextPath(Cell currentCell) {

        // A. SET currentCell as visited
        currentCell.setBeenVisited(true);
        // B. check for unvisited neighbors using the cell
        ArrayList<Cell> unvisitedNeighbors = getUnvisitedNeighbors(currentCell);
        // C. if has unvisited neighbors,
        if (!unvisitedNeighbors.isEmpty()){
            int randomCell = randGen.nextInt(unvisitedNeighbors.size());
            uncheckedCells.push(unvisitedNeighbors.get(randomCell));
            removeWalls(currentCell, unvisitedNeighbors.get(randomCell));

            currentCell = unvisitedNeighbors.get(randomCell);
            currentCell.setBeenVisited(true);
            selectNextPath(currentCell);
        }
        // C1. select one at random.

        // C2. push it to the stack

        // C3. remove the wall between the two cells

        // C4. make the new cell the current cell and SET it as visited

        // C5. call the selectNextPath method with the current cell

        else {
            if (!uncheckedCells.isEmpty()){
                currentCell = uncheckedCells.pop();
                selectNextPath(currentCell);
            }
        }
        // D. if all neighbors are visited

        // D1. if the stack is not empty

        // D1a. pop a cell from the stack

        // D1b. make that the current cell

        // D1c. call the selectNextPath method with the current cell

    }

    // This method will check if c1 and c2 are adjacent.
    // If they are, the walls between them are removed.
    private static void removeWalls(Cell c1, Cell c2) {
        if (c1.getRow() == c2.getRow()) {
            if (c1.getCol() > c2.getCol()) {
                c1.setWestWall(false);
                c2.setEastWall(false);
            } else {
                c1.setEastWall(false);
                c2.setWestWall(false);
            }
        } else {
            if (c1.getRow() > c2.getRow()) {
                c1.setNorthWall(false);
                c2.setSouthWall(false);
            } else {
                c1.setSouthWall(false);
                c2.setNorthWall(false);
            }
        }
    }

    // This method returns a list of all the neighbors around the specified
    // cell that have not been visited. There are up to 4 neighbors per cell.
    //          1
    //       2 cell 3
    //          4
    private static ArrayList<Cell> getUnvisitedNeighbors(Cell c) {
        int row = c.getRow();
        int col = c.getCol();

        ArrayList<Cell> unvisitedNeighbors = new ArrayList<Cell>();

        if (row > 0 && !maze.getCell(row - 1, col).hasBeenVisited()) {
            unvisitedNeighbors.add(maze.getCell(row - 1, col));
        }

        if (col > 0 && !maze.getCell(row, col - 1).hasBeenVisited()) {
            unvisitedNeighbors.add(maze.getCell(row, col - 1));
        }

        if (row < rows - 1 && !maze.getCell(row + 1, col).hasBeenVisited()) {
            unvisitedNeighbors.add(maze.getCell(row + 1, col));
        }

        if (col < cols - 1 && !maze.getCell(row, col + 1).hasBeenVisited()) {
            unvisitedNeighbors.add(maze.getCell(row, col + 1));
        }

        return unvisitedNeighbors;
    }
}
