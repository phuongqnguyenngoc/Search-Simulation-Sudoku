/** 
 * Simulation.java
 * Phuong Nguyen Ngoc 
 * CS231
 * Project: Search Simulation: Sudoku
*/

public class Simulation {
    int game;
    int solved;
    int unsolved;
    int initvalue;


    //a constructor that takes on 2 parameters to initialize the simulation
    public Simulation(int game, int initvalue) {
        this.game = game;
        this.initvalue = initvalue;
        this.solved = 0;
        this.unsolved = 0;
    
        //keep track of the number of solved and unsolved boards
        for (int i = 0; i < game; i ++) {
            Sudoku sudoku = new Sudoku(initvalue);
            if (sudoku.solve(0) == true) {
                ++ solved;
            } else if (sudoku.solve(0) == false) {
                ++ unsolved;
                
            }
        } 
    }

   
//toString method to print the final result in the terminal
    public String toString () {
        String result = "After " + game + " games of " + this.initvalue + " init values ";
        result += this.solved + " solved game; " + this.unsolved + " unsolved game";
        return result;
    }

    public static void main(String [] args) {
        //the command line takes in input to determine the number of games and initial values for them
        int numGame = Integer.parseInt(args[0]);
        int numInitVal = Integer.parseInt(args[1]);
        Simulation simulation = new Simulation(numGame, numInitVal);
        System.out.println(simulation);



    }
}