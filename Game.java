/**
 * Manages a single match between two players on a board.
 * Handles turns, win detection, and game flow until a result is reached.
 */
public class Game {
    private Player playerX;
    private Player playerO;
    private Renderer renderer;
    private Board board;
    private int winStreak;


    private static final int DEFAULT_WIN_STREAK = 3;

    /**
     * Constructs a game with default board size and winning streak.
     * @param playerX  player using mark X
     * @param playerO  player using mark O
     * @param renderer board renderer
     */
    public Game(Player playerX,Player
            playerO, Renderer renderer){
        createGame(playerX, playerO, renderer);
        this.winStreak = DEFAULT_WIN_STREAK;
        this.board = new Board();
    }

    /**
     * Constructs a game with a custom board size and winning streak.
     * @param playerX   player using mark X
     * @param playerO   player using mark O
     * @param size      board side length
     * @param winStreak consecutive marks required for victory
     * @param renderer  board renderer
     */
    public Game(Player playerX,Player
            playerO, int size, int winStreak, Renderer renderer){
        createGame(playerX, playerO, renderer);
        this.winStreak = winStreak;
        this.board = new Board(size);
    }

    /**
     * I added this function to initializes the game
     * by setting the two players and the renderer.
     * @param playerX   the player using the X mark
     * @param playerO   the player using the O mark
     * @param renderer  the renderer used to display the board
     */
    private void createGame(Player playerX,Player
            playerO, Renderer renderer){
        this.playerX = playerX;
        this.playerO = playerO;
        this.renderer = renderer;
    }

    /**
     * Returns the number of marks needed to win.
     * @return win streak length
     */
    public int getWinStreak(){
        return this.winStreak;
    }


    /**
     * Returns the board size.
     * @return board size
     */
    public int getBoardSize(){
        return board.getSize();
    }

    /**
     * I added this function that checks if a player has a full winning sequence starting from
     * a given cell in a specific direction.
     * @param board         the current game board
     * @param row           starting row index
     * @param col           starting column index
     * @param dirRow        direction step for row (e.g., 1, 0, -1)
     * @param dirCol        direction step for column (e.g., 1, 0, -1)
     * @param curPlayerMar  the mark of the current player
     * @return true if all cells in this direction match the player's mark,
     *         false otherwise
     */
    private boolean checkWinInSpecificDirection(Board board,
        int row, int col, int dirRow, int dirCol, Mark curPlayerMar) {

        int streakForWin = this.winStreak;
        int boardSize = board.getSize();

        for (int i = 0; i < streakForWin; i++){
            int rowPos = row + i*dirRow;
            int colPos = col + i*dirCol;
            if (rowPos < 0 || rowPos >= boardSize ||  colPos < 0 || colPos >= boardSize){
                return false;
            }

            if (board.getMark(rowPos, colPos) != curPlayerMar){
                return false;
            }
        }
        return true;
    }

    /**
     * I added this function that checks if the given player currently has a winning sequence
     * anywhere on the board.
     * @param curPlayerMark  the mark of the player to check
     * @return true if the player has a winning sequence, false otherwise
     */
    private boolean thereIsWin(Mark curPlayerMark){
        int size = board.getSize();
        for (int row = 0; row < size; row++){
            for (int col = 0; col < size; col++){
                if (board.getMark(row, col) == curPlayerMark){
                    // continue check all directions
                    if (checkWinInSpecificDirection(board, row, col, 1, 0, curPlayerMark)
                        || (checkWinInSpecificDirection(board, row, col, 0, 1, curPlayerMark)
                        || (checkWinInSpecificDirection(board, row, col, 1, 1, curPlayerMark)
                        || checkWinInSpecificDirection(board, row, col, 1, -1, curPlayerMark)))){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * I added this function that determines if the board is completely filled with marks.
     * @param board  the current game board
     * @return true if no blank cells remain, false otherwise
     */
    private boolean boardIsFull(Board board){
        for (int row = 0; row < getBoardSize(); row++){
            for (int col = 0; col < getBoardSize(); col++){
                if (board.getMark(row, col)==Mark.BLANK){
                   return false;
                }
            }
        }
        return true;
    }

    /**
     * Runs the game loop until a win or tie occurs.
     * @return winner mark, or BLANK if tie
     */
    public Mark run(){

        Mark curPlayerMark = Mark.X;
        while (true){
            renderer.renderBoard(board);

            if (curPlayerMark.equals(Mark.X)){
                playerX.playTurn(board, Mark.X);
                curPlayerMark = Mark.O;
            }
            else{
                playerO.playTurn(board, Mark.O);
                curPlayerMark = Mark.X;

            }

            if (thereIsWin(curPlayerMark)){
                return curPlayerMark;
            }

            if (boardIsFull(board)){
                return Mark.BLANK;
            }
        }
    }
}
