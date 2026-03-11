/**
 * Represents a square game board used to store and manage mark positions.
 * Supports placing marks, checking cell values, and tracking board size.
 */
public class Board {
    private int size;
    private Mark[][] board;
    private static final int DEFAULT_BOARD_SIZE = 4;


    /**
     * Constructs a board with the default size (4x4).
     */
    public Board() {
        createBoard(DEFAULT_BOARD_SIZE);
    }

    /**
     * Constructs a board with a custom size.
     * @param size board side length
     */
    public Board(int size){
        createBoard(size);
    }

    /**
     * I added this function to initializes the
     * board with the given size and fills
     * all cells with the BLANK mark.
     * @param size  the length of side of the board
     */
    private void createBoard(int size) {
        this.size = size;
        this.board = new Mark[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = Mark.BLANK;
            }
        }
    }

    /**
     * Returns the board size.
     * @return board size
     */
    public int getSize(){
        return this.size;
    }

    /**
     * Places a mark at the given position if valid and empty.
     * @param mark mark to place
     * @param row  target row
     * @param col  target column
     * @return true if mark placed, false otherwise
     */
    public boolean putMark(Mark mark, int row, int col){
        // if the coordinate in the board is not BLANK
        if (row < 0 || row >= getSize() || col < 0 || col >= getSize()){
            return false;
        }
        if (board[row][col] != Mark.BLANK){
            return false;
        }
        board[row][col] = mark;
        return true;
    }

    /**
     * Returns the mark at the specified position.
     * @param row row index
     * @param col column index
     * @return mark at position, or BLANK if invalid
     */
    public Mark getMark(int row, int col){
        if (row < 0 || row >= getSize() || col < 0 || col >= getSize()){
            return Mark.BLANK;
        }
        return board[row][col];
    }

}
