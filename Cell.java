/** 
 * Cell.java
 * Phuong Nguyen Ngoc 
 * CS231
 * Project: Search Simulation: Sudoku
*/

import java.awt.Graphics;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
public class Cell {
    private int row;
    private int col;
    private int value;
    private boolean locked;

    //a default constructor that initialize a cell 
    public Cell() {
        this.row = 0;
        this.col = 0;
        this.value = 0;
        this.locked = false;
    }

    //a constructor that takes on parameters to initialize the cell with specific postion and value
    public Cell(int row, int col, int value) {
        this.row = row;
        this.col = col;
        this.value = value;
        this.locked = false;
    }

     //a constructor that takes on parameters to initialize the cell with specific postion and value and state of lock
    public Cell(int row, int col, int value, boolean locked) {
        this.row = row;
        this.col = col;
        this.value = value;
        this.locked = locked;
    }

    //method returns row
    public int getRow(){
        return row;
    }

    //method returns col
    public int getCol(){
        return col;
    }

    //method returns value
    public int getValue(){
        return value;
    }

    //method sets a cell's value
    public void setValue(int newval) {
        this.value = newval;
    }

    //method returns whether the cell is locked or not
    public boolean isLocked() {
        return locked;
    }

    //method sets the lock state of the cell
    public void setLocked(boolean lock) {
        this.locked = lock;
    }

    //method creates a clone of the cell with the same position, value and lock state
    public Cell clone() {
        Cell newCell = new Cell(this.row, this.col, this.value, this.locked);
        return newCell;
    }

    //toString method describes the cell's position, value and lock state
    public String toString() {
        String cellField = "Cell position: row:" + row + "col: " + col;
        cellField += "value: " + value;
        cellField += "locked:" + locked;
        return cellField;
    }

    //method draws the cell according to its value and position
    public void draw( Graphics g, int x0, int y0, int scale) {
        //an array of possible values in which the index is the same as the value
        char[] value = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        
        if (this.getValue() == 1) {
            g.setColor(Color.blue);
        } else if (this.getValue() == 2) {
            g.setColor(Color.cyan);
        } else if (this.getValue() == 3) {
            g.setColor(Color.lightGray);
        } else if (this.getValue() == 4) {
            g.setColor(Color.green);
        } else if (this.getValue() == 5) {
            g.setColor(Color.magenta);
        } else if (this.getValue() == 6) {
            g.setColor(Color.orange);
        } else if (this.getValue() == 7) {
            g.setColor(Color.pink);
        } else if (this.getValue() == 8) {
            g.setColor(Color.red);
        } else if (this.getValue() == 9) {
            g.setColor(Color.yellow);
        } else {
            g.setColor(Color.white);
        }
        g.drawChars(value, this.getValue(), 1, x0 * scale, y0 * scale);
    }

    public static void main (String[] args) {
        Cell cell = new Cell(1,2,3,true);
        System.out.println(cell.getRow());
        System.out.println(cell.getCol());
        System.out.println(cell.getValue());
        cell.setValue(4);
        System.out.println(cell.getValue());
        System.out.println(cell.isLocked());
        cell.setLocked(false);
        System.out.println(cell.isLocked());
        System.out.println(cell.clone());

    }
}


