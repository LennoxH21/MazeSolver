package edu.bloomu.maze;

/**
 * This program takes a maze (2D char array) and it's dimensions
 * and displays all solutions to the maze.
 *
 * @author Lennox Haynes
 */

public class MazeSolver {

    private int row; // Rows in this maze.
    private int col; // Columns in this maze.
    private char[][] maze;
    private int solCount; // Stores the number of solutions for this maze.

    public MazeSolver(char[][] maze, int r, int c)
    {
        this.row = r;
        this.col = c;
        this.maze = new char[r][c];

        // Copies everything from the given char[][] into the maze.
        for (int rows = 0; rows < this.maze.length; rows++)
        {
            // Copies everything into this maze.
            System.arraycopy(maze[rows], 0, this.maze[rows],
                    0, this.maze[rows].length);
        }
    }

    /**
     * Prints out the Graphical Solutions to this maze.
     *
     * @param r Row of current position
     * @param c Column of current position
     */
    public void solve(int r, int c)
    {
        if (isSolved(r, c)) // BASE CASE
        {
            this.solCount++;
            print();
        }
        else // RECURSIVE CASE
        {
            // CHECKS AND MOVES LEFT
            if (moveLeft(r, c))
            {
                this.maze[r][c - 1] = 'p';
                solve(r, c - 1);
            }

            // CHECKS AND MOVES RIGHT
            if (moveRight(r, c))
            {
                this.maze[r][c + 1] = 'p';
                solve(r, c + 1);
            }

            // CHECKS AND MOVES UP
            if (moveUp(r, c))
            {
                this.maze[r - 1][c] = 'p';
                solve(r - 1, c);
            }

            // CHECKS AND MOVES DOWN
            if (moveDown(r, c))
            {
                this.maze[r + 1][c] = 'p';
                solve(r + 1, c);
            }
        }
        this.maze[r][c] = '0'; // BACKTRACKING
    }

    /**
     *
     * @param r Row of current position.
     * @param c Column of current position.
     *
     * @return true if you can go to the Left, false otherwise.
     */
    private boolean moveLeft(int r, int c)
    {
        return (c - 1 >= 0) && (this.maze[r][c - 1] == '0');
    }

    /**
     *
     * @param r Row of current position.
     * @param c Column of current position.
     *
     * @return true if you can go to the Right, false otherwise.
     */
    private boolean moveRight(int r, int c)
    {
        return (c + 1 < this.col) && (this.maze[r][c + 1] == '0');
    }

    /**
     *
     * @param r Row of current position.
     * @param c Column of current position.
     *
     * @return true if you can go Up, false otherwise.
     */
    private boolean moveUp(int r, int c)
    {
        return (r - 1 >= 0) && (this.maze[r - 1][c] == '0');
    }

    /**
     *
     * @param r Row of current position.
     * @param c Column of current position.
     *
     * @return true if you can go Down, false otherwise.
     */
    private boolean moveDown(int r, int c)
    {
        return (r + 1 < this.row) && (this.maze[r + 1][c] == '0');
    }

    /**
     *
     * @param r Row of current position.
     * @param c Column of current position.
     *
     * @return true if the exit ('e') is in a neighboring position, false otherwise.
     */
    private boolean isSolved(int r, int c) {
        return  ((c + 1 < this.col) && (this.maze[r][c + 1] == 'e')) || // Left
                ((c - 1 >= 0) && (this.maze[r][c - 1] == 'e')) || // Right
                ((r - 1 >= 0) && (this.maze[r - 1][c] == 'e')) || // Up
                ((r + 1 < this.row) && (this.maze[r + 1][c] == 'e')); // Down
    }

    /**
     * Prints out this maze and which solution it's currently on.
     */
    private void print()
    {
        // Prints which Solution is currently being displayed.
        System.out.println("SOLUTION: " + this.solCount);

        // Prints out the maze.
        for (char[] chars : this.maze)
        {
            for (char aChar : chars)
            {
                if (aChar == 'p') {
                    System.out.print("* ");
                }
                else if (aChar == '0'){
                    System.out.print("  ");
                }
                else if (aChar == '1'){
                    System.out.print("[]");
                }
                else {
                    System.out.print(aChar +  " ");
                }
            }
            System.out.println();
        }
        System.out.println();

    }
}
