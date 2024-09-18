import java.util.Random;

public class Minesweeper {

    // Data members
    private char[][] board;   // The game board where cells will be displayed
    private boolean[][] mines; // Array to track the locations of mines
    private boolean[][] revealed; // Array to track which cells have been revealed
    private int rows; // Number of rows in the board
    private int cols; // Number of columns in the board
    private int numMines; // Number of mines in the game
    private boolean gameOver; // Boolean to check if the game is over

    // Constructor to initialize the board with the specified dimensions and number of mines
    public Minesweeper(int rows, int cols, int numMines) {
        this.rows = rows;
        this.cols = cols;
        this.numMines = numMines;
        this.board = new char[rows][cols];
        this.mines = new boolean[rows][cols];
        this.revealed = new boolean[rows][cols];
        this.gameOver = false;

        // Call methods to initialize the board and place mines
    }
    public boolean getGameOver(){
        return this.gameOver;
    }
    public void setGameOver(boolean status)
    {
        this.gameOver = status;

    }
    // Method to initialize the game board with empty values
    private void initializeBoard() {
        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < cols; y++) {
                board[x][y] = '-'; // unrevealed cells
            }
        }
    }

    // Method to randomly place mines on the board
    private void placeMines() {
        int minesDown = 0;
        Random rand = new Random();  // Create a single instance of Random
    
    while (minesDown < numMines) {
        int randRow = rand.nextInt(rows);  // random row 
        int randCol = rand.nextInt(cols);  // random column 
        
        if (!mines[randRow][randCol]) {         // check if a mine is already placed at the location
            mines[randRow][randCol] = true;  // place a mine
            minesDown++;  
        }
    }
    }

    // Method to calculate numbers on the board for non-mine cells
    private int calculateNumbers() {
        
        for (int i =0; i< rows; i++){
            for(int j =0; j< cols; j++){
                if(mines[i][j] == true){
                    continue;
                }
                int mineCount = 0;
                for (int x = -1; x <= 1; x++) {
                    for (int y = -1; y <= 1; y++) {

                        int newRow = i + x;
                        int newCol = j + y;
                        
                        if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols) {
                            if (mines[newRow][newCol]) {
                                mineCount++;
                            }
                        }
                    }
                }
            }
        }
        board[i][j] =mineCount;
        
}

private boolean isInBounds(int row, int col) {
    return row >= 0 && row < rows && col >= 0 && col < cols;
    }

    // Method to display the current state of the board
    public void displayBoard() {
        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < cols; y++) {
            if (revealed[x][y]) {
                System.out.print(board[x][y] + " ");
            } else {
                System.out.print("-");  // hide unrevealed cells
            }
        }
        System.out.println();
    }
    }

    // Method to handle a player's move (reveal a cell or place a flag)
    public void playerMove(int row, int col, String action) {
        if (checkBounds() == true && this.action == "reveal"){
            revealCell(row, col);

        }
        else if(this.action == "flag"){
            flagCell(this.row, this.col);

        }
        else if(this.action == "unflag"){
            unflagCell(this.row, this.col);
        }
    }

    private boolean checkBounds( ){
        if(row < 0 || row >= rows || col < 0 || col >= cols){
            System.out.println("stay within the bounds!");
            return false;
        }
        return true;
    }

    // Method to check if the player has won the game
    public boolean checkWin() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (!mines[i][j] && !revealed[i][j]) {                  // If a cell is not a mine and it has not been revealed, the player has not won
                    return false;
                }
            
            return true;
        }
    }
}

            // Method to check if the player has lost the game

        public boolean checkLoss(int row, int col) {
        for(int i =0; i < rows; i ++){
            for (int j =0;j < cols; j++){
                if(mines[i][j]==revealed[row][col]){
            setGameOver(true);
        }
    }
}
            
            
        return false;
    }

    // Method to reveal a cell (and adjacent cells if necessary)
    public void revealCell(int row, int col) {
        revealed[row][col] = true;
        checkLoss(row,col);
        }
    

    // Method to flag a cell as containing a mine
    private void flagCell(int row, int col) {
        if(!revealed[row][col]){
            board[row][col] = 'F';  //if not revealed marks with an F
        }
    }

    // Method to unflag a cell
    private void unflagCell(int row, int col) {
        if (board[row][cols] == 'F'){
            board[rows][cols] ='-'; //undo the flag back to a -
        }
    }
}
