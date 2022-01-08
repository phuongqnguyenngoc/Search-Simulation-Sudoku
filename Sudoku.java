/** 
 * Sudoku.java
 * Phuong Nguyen Ngoc 
 * CS231
 * Project: Search Simulation: Sudoku
*/


import java.util.Random;
public class Sudoku {
    private Board theBoard;
    private LandscapeDisplay display;

    //a constructor to initialzie a Sudoku game with every grid being 0
    public Sudoku() {
        
        this.theBoard = new Board();
        
        for (int i = 0; i < Board.Size; i ++) {
            for (int j = 0; j < Board.Size; j ++) {
                theBoard.set(i, j , 0);
            }
        }
        this.display = new LandscapeDisplay(theBoard, 40);
        this.display.addSudoku(this);
    }

    //a constructor to initialize a Sudoku game with N initial random valid values
    public Sudoku( int N) {
        Random rand = new Random();
        
        this.theBoard = new Board();
        for (int i = 0; i < Board.Size; i ++) {
            for (int j = 0; j < Board.Size; j ++) {
                theBoard.set(i, j , 0);
            }
        }

        //a for loop a generate N random valid value and set the board accordingly
        for (int i = 0; i < N; i ++) {
            int rand_row = rand.nextInt(9);
            int rand_col = rand.nextInt(9);
            int rand_val = rand.nextInt(9) + 1;
            if (theBoard.value(rand_row, rand_col) == 0 && theBoard.validValue(rand_row, rand_col, rand_val) == true) {
                theBoard.set(rand_row, rand_col, rand_val, true);
            } else { //if the value is not valid, decrement i so that N does not change
                i -= 1;
            }
        }

        

        this.display = new LandscapeDisplay(theBoard, 30);
        //add a method that has two actionlistener methods to the Sudoku board so that it can evoke method in this class
        this.display.addSudoku(this);


    }

    //method returns the Cell with the fewest solutions
    public Cell findBestCell () { 
        Cell min = new Cell();
        int minSol = 100;
        //loop through all the cells in the board
        for (int r = 0; r < Board.Size; r ++) {
            for ( int c = 0; c < Board.Size; c ++) {
                if (theBoard.value(r, c) == 0) {
                    int retval = theBoard.numOfSol(r, c);
                    //if there is no solution to the cell, return null
                    if (retval == 0) {
                        return null;
                    } //if this cell's number of solutions is less than the last smallest number of solutions, it will replace the last one
                    if (retval < minSol) {
                        minSol = retval;
                        min = new Cell(r, c, 0); //set the location of the cell accordingly
                    }
                
                } 
            }
        }
        //loop from 1 to 9 to find the minimum valid value 
        for (int i = 1; i < 10 ; i ++) {
                if (theBoard.validValue(min.getRow(), min.getCol(), i) == true) {
                    min.setValue(i);
                    break;
                }
            
        }
        return min;
    } 
    
    //a solve method that solves the board using the findBestCell method to begin with
        public boolean solve(int delay) {
        CellStack tracking = new CellStack(100);
        int time = 0;
        int numLocked = theBoard.numLocked();
        while (tracking.size() < Board.Size * Board.Size - numLocked) {
            // System.out.println(theBoard);
            time ++;
            if( delay > 0 ) {
                try {
                    Thread.sleep(delay);
                }
                catch(InterruptedException ex) {
                    System.out.println("Interrupted");
                }
                display.repaint();
            }
            Cell best = this.findBestCell();

            //if there is a best cell, set the position on the board and value accordingly and push the cell on the stack
            if (best !=  null) {
                tracking.push(best);
                theBoard.set(best.getRow(), best.getCol(), best.getValue());
                // System.out.println(best);
            } else { //if there is no best cell, backtrack
                boolean stuck = true;
                while (stuck == true && tracking.size() > 0) {
                    //remove the top cell of the stack
                    Cell last = tracking.pop();
                    //try a bigger value until there is a valid value 
                    for (int v = last.getValue() + 1; v < 10; v ++ ) {
                        if (theBoard.validValue(last.getRow(), last.getCol(), v) == true) {
                            last.setValue(v);
                            theBoard.set(last.getRow(), last.getCol(), v);
                            tracking.push(last);
                            stuck = false;
                            break;                           
                        } 
                    } if (stuck) { //if there is no valid value, set the cell to 0
                        theBoard.set(last.getRow(), last.getCol(), 0);
                    }
                    
                    
                 //empty stack means we have backtracked all the way to the beginnig, so the board cannot be solved   
                }  if (tracking.size() == 0) {
                    System.out.println(time);
                    System.out.println("Unsolved");
                    return false;
                }

                 
                
            }

        }
        System.out.println(time);
        System.out.println("Solved");
        return true;
    }

    
    
    //a toString method to print the board in a text version
    public String toString () {
        String test = "";
        for (int i = 0; i < 9; i ++) {
            for (int j = 0; j < 9; j ++) {
                test += theBoard.value(i,j);
                test += " ";
                if (j % 3 == 2) {
                    test += "|";
                  }
            }
            test += "\n";
            if (i % 3 == 2) {
                test += "---------------------" + "\n";
            }
        }
        

        return test;
    }

    //method to reset the board by modifying the current board
    public void reset () {
        System.out.println("reset");
        Random rand = new Random();
        for (int i = 0; i < Board.Size; i ++) {
            for (int j = 0; j < Board.Size; j ++) {
                theBoard.set(i, j, 0);
            }
        }

        for (int i = 0; i < 20; i ++) {
            int rand_row = rand.nextInt(9);
            int rand_col = rand.nextInt(9);
            int rand_val = rand.nextInt(9) + 1;
            if (theBoard.value(rand_row, rand_col) == 0 && theBoard.validValue(rand_row, rand_col, rand_val) == true) {
                theBoard.set(rand_row, rand_col, rand_val);
            } else {
                i -= 1;
            }
        } display.repaint();

    }


    public static void main(String[] args) {
        Sudoku sudoku = new Sudoku(10);
        System.out.println("this is the initial board \n" + sudoku);
        System.out.println(sudoku.findBestCell().getCol());
        System.out.println(sudoku.findBestCell().getRow());
        System.out.println(sudoku.findBestCell().getValue());
        boolean solved = sudoku.solve(10);
        System.out.println("this is the solved board \n" + sudoku);
        System.out.println(solved);
        

        



    }
}