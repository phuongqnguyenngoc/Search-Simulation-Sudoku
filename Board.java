/** 
 * Board.java
 * Phuong Nguyen Ngoc 
 * CS231
 * Project: Search Simulation: Sudoku
*/
import java.io.*;
import java.awt.Graphics;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileReader;
public class Board {
  private String filename;
  private Cell [][] field;
  public static final int Size = 9; //constant value that can be accessed from other classes
  
  // a constructor to initialize the field value;
  public Board(){
    filename = "";
    field = new Cell[Board.Size][Board.Size];
    for (int i = 0; i < Board.Size; i ++) {
      for (int j = 0; j < Board.Size; j ++) {
        field[i][j] = new Cell();
        field[i][j].setValue(0);
      }
    }
  }

  //toString method to print out the board in text version
  public String toString() {
    String printBoard = "";

    for (int i = 0; i < Board.Size; i ++) {
      for (int j = 0; j < Board.Size; j ++) {
        printBoard += field[i][j].getValue();
        printBoard += " ";
        // use the remainder division to draw the line 
        if (j % 3 == 2) {
          printBoard += "|";
        }
      }
      printBoard += "\n";
      if (i % 3 == 2) {
        printBoard += "---------------------" + "\n";
      }
    }
    return printBoard;
  }
  // method returns the number of column
    public int getCols() {
      int cols = field.length;
      return cols;
    }

    //method returns the number of row
    public int getRows() {
      int rows = field[0].length; 
      return rows;
    }
 
    //method returns the cell at a specific location
    public Cell get(int r, int c) {
      return field[r][c];
    }

    //method returns whether the cell at a specific position is locked
    public boolean isLocked(int r, int c) {
      boolean locked = false;
      if (field[r][c].getValue() != 0) {
        return true;
      }
      return locked;
    }

    //method returns the number of locked cells
    public int numLocked() {  
      int numLocked = 0;
      //loop through the board to check whether each cell is locked or not
      for (int i = 0; i < Board.Size; i ++) {
        for (int j = 0; j < Board.Size; j ++) {
          if (field[i][j].isLocked() == true) {
            numLocked ++;
          }
        }
      }
      return numLocked;
    }
    
    //method sets a cell at a specific location the value and whether it is locked or not
    public void set(int r, int c, int val, boolean locked) {
      field[r][c].setValue(val);
      field[r][c].setLocked(locked);
    }

    //method gets the value of the cell at a specific position
    public int value(int r, int c) {
      int value = field[r][c].getValue();
      return value;
    }

    //method sets value of the cell at a specific position
    public void set(int r, int c, int value) {
      field[r][c].setValue(value);
    }

    //method sets the locked state of the cell at a specific position
    public void set(int r, int c, boolean locked) {
      field[r][c].setLocked(locked);
    }
    
    //method checks whether a value is valid for a cell at a specific position
    public boolean validValue(int row, int col, int value) {
      boolean validVal = true;
      
      int a = row / 3; // division to locate which 3x3 area the cell is in
      int b = col / 3;
      for (int i = 0; i < Board.Size; i ++) { 
        if ( value ==  this.value(row, i) ) { //loop through row
          return false;
        } 
        
        if ( value == this.value(i, col)) { //loop through col
          return false;
        } 
      }
        
      for (int i = a * 3; i < a * 3 + 3; i ++) { //loop through 3 * 3 area
        for (int j = b * 3; j < b *3 + 3; j ++) {
          if (value == this.value(i, j)) {
            return false;
          } 
        }
      }
      return validVal; //if the value satisfies all 3 loops, it is valid
    }

    //method returns the number of solutions available for a cell at a specific position
    public int numOfSol(int r, int c) {
      int sol = 0;

      //loop through value from 1 to 9
      for (int v = 1; v < Board.Size + 1; v ++) {
          boolean possible = this.validValue(r, c, v);
          if (possible == true) { //if a value is valid, add 1 to sol
              sol ++;
          }
        }
            
          
        return sol;
      } 
        

    

      //method checks whether the board is solved or not 
    public boolean validSolution() {
      boolean validSol = true;
      //loop through all the cells on the board
      for (int i = 0; i < Board.Size; i ++) {
        for (int j = 0; j < Board.Size; j ++) {
          //if a cell has a value of 0 or it is not valid, return false
          if (this.value(i, j) == 0 || this.validValue(i, j, this.value(i, j)) == false) {
            validSol = false;
          }
        }
      }
      return validSol;
    }
    //method that reads the file that is entered in the command line
    public boolean read(String filename) {
        try {
         
          // assign to a variable of type FileReader a new FileReader object, passing filename to the constructor
          FileReader fileReader = new FileReader(filename);
          // assign to a variable of type BufferedReader a new BufferedReader, passing the FileReader variable to the constructor
          BufferedReader bufferReader = new BufferedReader(fileReader);
          // assign to a variable of type String line the result of calling the readLine method of your BufferedReader object.
          String line = bufferReader.readLine();
          int a = 0;
          int b = 0;

          // start a while loop that loops while line isn't null
          while (line != null) {
            
            // assign to an array of type String the result of calling split on the line with the argument "[ ]+"
            String[] splitItems = line.split("[ ]+");
            
            //go through strings on this line
            b = 0;
            for (int i = 0; i < splitItems.length; i ++) {
              if (splitItems[i].length() > 0) {
                //convert the string to int
                int convert = Integer.parseInt(splitItems[i]); 
                this.set(a, b, convert);
                b ++;
              }
              
            }
            //go to the next line
            a ++;
            
            line = bufferReader.readLine();
            
            
            

          }              
          // call the close method of the BufferedReader
          bufferReader.close();
          return true;
        }

        catch(FileNotFoundException ex) {
          System.out.println("Board.read():: unable to open file " + filename );
        }
        catch(IOException ex) {
          System.out.println("Board.read():: error reading file " + filename);
        }
    
        return false;
      }

      //method draws each cell on the board
      public void draw(Graphics g, int scale) {
        for (int i = 0; i < Board.Size; i ++) {
          for (int j = 0; j < Board.Size; j ++) {
            field[i][j].draw(g, j + 1, i + 1, scale);
            //if the cell lies near the border, draw a white line to define the border
            if ( j % 3 == 2) {
              g.setColor(Color.white);
              g.drawLine( (j + 1) * scale + scale / 2, (i) * scale + scale / 2, (j+1)*scale + scale /2, (i+1) * scale + scale / 2 );
            } if (i % 3 == 2) {
              g.setColor(Color.white);
              g.drawLine( (j) * scale + scale / 2 , (i + 1) * scale + scale / 2, (j + 1) * scale + scale /2, (i + 1) * scale + scale / 2 );
            }
          }
        }
      }

      public static void main(String[] args) {
        Board board = new Board();
        
        board.read(args[0]);
        board.set(1,1,0);
        board.set(1,8,0);
        board.set(8,5,0);

        System.out.println(board);
        System.out.println(board.validValue(1, 1, 3));
        System.out.println(board.validValue(1, 8, 2));
        System.out.println(board.validValue(8, 5, 4));
        System.out.println(board.value(2,3));
        System.out.println(board.numOfSol(5,4));
        System.out.println(board.getCols());
      }
}
