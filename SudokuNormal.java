/** 
 * SudokuNormal.java
 * Phuong Nguyen Ngoc 
 * CS231
 * Project: Search Simulation: Sudoku
*/

import java.util.Random;
public class SudokuNormal {
    private Board theBoard;
    private LandscapeDisplay display;

    //a constructor to initialzie a Sudoku game with every grid being 0
    public SudokuNormal() {
        
        this.theBoard = new Board();
        
        for (int i = 0; i < Board.Size; i ++) {
            for (int j = 0; j < Board.Size; j ++) {
                theBoard.set(i, j , 0);
            }
        }
        this.display = new LandscapeDisplay(theBoard, 40);
    }

    //a constructor to initialize a Sudoku game with N initial random valid values
    public SudokuNormal( int N) {
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
            } else {
                i -= 1;
            }
        }
        this.display = new LandscapeDisplay(theBoard, 30);
    }
    
    //a solve method that goes through all cell one by one from the top left corner and fills in the smallest value
        public boolean solve(int delay) {
        CellStack tracking = new CellStack(100);
        int time = 0;
        int numLocked = theBoard.numLocked();
        int row = 0;
        int col = 0;
        int val = 1;
        while (tracking.size() < Board.Size * Board.Size - numLocked) {
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

            // if the cell is locked, skip it
            if (theBoard.get(row, col).isLocked()) {
                if (col < Board.Size - 1) {
                    col ++;
                    continue;
                } else {
                    col = 0;
                    row ++;
                    continue;
                }
                
            }
            //loop through the value from 1 to 9, choose the smallest valid value
            for (int i = val; i < 10; i ++) {
                if (theBoard.validValue(row, col, i) == true) {
                    val = i;
                    break;
                } else {
                    val = 10;
                }
            }

            //if there is a possible solution, set the cell's position and value accordingly and push it onto the stack
            if (val < 10) {
                Cell next = new Cell(row, col, val);
                theBoard.set(row, col, val);
                tracking.push(next);
                if (col < Board.Size - 1) {
                    col ++;
                } else {
                    col = 0;
                    row ++;
                } 
                val = 1; 
            } else { //if there is no possible value, backtrack
                //take the top cell out of the stack, get the current row and col the same as the last's
                //assign the current value to the last's value + 1
                //all these values are reset to go through the while loop
                theBoard.set(row,col,0);
                    Cell last = tracking.pop(); 
                    row = last.getRow(); 
                    col = last.getCol();
                    val = last.getValue() + 1; 
                    last.setValue(0); //set the last's value to 0
                   
                 if (tracking.size() == 0) { //if we have backtracked all the way from the beginning, the board cannot be solved
                    System.out.println(time);
                    return false;
                }
            } 
        } 
        System.out.println(time);
        return true;
    } 
        


            
    

    
    
 //a toString method to print the board in text version   
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




    public static void main(String[] args) {
        SudokuNormal sudoku = new SudokuNormal(10);
        System.out.println("this is the initial board \n" + sudoku);
        System.out.println(sudoku.solve(1));
        System.out.println("this is the solved board \n" + sudoku);
        

        



    }
}