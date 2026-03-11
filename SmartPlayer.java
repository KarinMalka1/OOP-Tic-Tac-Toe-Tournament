/**
 * Smart player that prioritizes central and strategic columns
 * while keeping the same looping structure.
 */
public class SmartPlayer implements Player {

    private static final int START_COL_OPTION = 2;

    /**
     * Plays a turn by scanning the board in a smart column order.
     * Prefers center and mid columns before edges to increase win rate.
     * @param board current game board
     * @param mark  player's mark
     */
    @Override
    public void playTurn(Board board, Mark mark) {
        int boardSize = board.getSize();

        // build column order (center first, then sides)
        int[] columnOrder = buildColumnOrder(boardSize);

        // try placing the mark using same nested structure
        for (int colIndex = 0; colIndex < columnOrder.length; colIndex++) {
            int col = columnOrder[colIndex];
            for (int row = 0; row < boardSize; row++) {
                if (board.putMark(mark, row, col)) {
                    return;
                }
            }
        }
    }

    /**
     * Creates an array representing the order of columns to play:
     * center then one step right then one step left then two right
     * and then two left and so on.
     * @param boardSize size of the board
     * @return array of column indices in preferred order
     */
    private int[] buildColumnOrder(int boardSize) {
        int[] order = new int[boardSize];
        int center = boardSize / 2;
        int index = 0;

        order[index++] = center;

        for (int offset = 1; offset <= center; offset++) {
            if (center + offset < boardSize) {
                order[index++] = center + offset;
            }
            if (center - offset >= 0) {
                order[index++] = center - offset;
            }
        }
        return order;
    }
}

























