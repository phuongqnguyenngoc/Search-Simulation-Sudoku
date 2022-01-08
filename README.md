# Search-Simulation-Sudoku
This project centers in the application of 2D-array stack and implementation of a depth-first search algorithm to solve Sudoku games. The program randomly generates a 9*9 Sudoku board with 10 initial values. When you run it, it will show a window that display the board and the process of solving it. You can choose to reset the board by clicking "reset" button, then solve the new board by clicking "solve". In the terminal, it will show you the first board that is generated, how many steps it takes to solve that board and whether the board is soluble or not. If you reset the board, it will show the button you clicked, whether the board is soluble, and how many steps it take to solve it. 

To solve this Sudoku game, instead of using a naive approach (fill lowest possible number from the top left corner and backtrack until you can not find any possible solution), I choose to fill the grid with the fewest possible solutions. With this algorithm, I can solve the board faster than with the traditional solution (the traditional version is in SudokuNormal.java)

To run the program, please run the file Sudoku.java. There is also a simulation version which creates n games with m initial values and solve them. This simulation allows us to see the change in probability in solubity when we increase the number of initial values. To run this version, please run the file Simulation.java followed by n and m in which n is the number of boards you want to create and m is the number of initial values for the boards. 

<img width="329" alt="Screen Shot 2019-09-30 at 5 59 27 PM" src="https://user-images.githubusercontent.com/60492418/93058708-f0498280-f699-11ea-8131-bf0a6108f18c.png">




