/** 
 * CellStack.java
 * Phuong Nguyen Ngoc 
 * CS231
 * Project: Search Simulation: Sudoku
*/

import java.util.Arrays;
public class CellStack {
    private Cell [] cell;
    private int capacity;
    private int top;
    // public cellStack() {
    //     cellStack = new Cell [30];
        
    //     capacity = cellStack.length;
    //     nextFreeSpace = 0;
    // }

    // public cellStack (int stack, int capacity, int nextFreeSpace) {
    //     this.cellStack = new Cell [capacity]
    // }

    public CellStack() {
        capacity = 10;
        cell = new Cell[capacity];
        top = 0;
    }

    public CellStack(int initSize) {
        this.capacity = initSize;
        cell = new Cell[capacity];
        top = 0;
    }

    public void push(Cell c) {
        if (top == capacity) {
            Cell[] biggerCell = new Cell[capacity * 2];
            for (int i = 0; i < capacity; i ++) {
                biggerCell[i] = cell[i];
            }
            cell = biggerCell;
        }
        cell[top] = c;
        top ++;       
    }

    public Cell peek () {
		return cell[top -1];
	}
	
	//get top element and remove
	public Cell pop () {
        if (top == 0) {
            return null;
        }
		Cell retval = peek();
		cell[top - 1] = null;
		top--;
		return retval;
	}

    

    public int size() {
        return top;
    }

    public boolean empty() {
        boolean isEmpty = false;
        if (top == 0) {
            return true;
        }
        return isEmpty;
    }
}