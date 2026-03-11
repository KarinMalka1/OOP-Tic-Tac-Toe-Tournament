import java.util.Random;

/**
 * Player that places marks at random positions.
 */
public class WhateverPlayer implements Player {
    private Random random;

    /**
     * Constructs a random-based player.
     */
    public WhateverPlayer(){
        this.random = new Random();
    }

    /**
     * Plays a turn by choosing a random empty cell.
     * @param board current game board
     * @param mark  player's mark
     */
    @Override
    public void playTurn(Board board, Mark mark) {
        while (true) {
            int rowNum = random.nextInt(board.getSize());
            int colNum = random.nextInt(board.getSize());

            boolean ifSuccess = board.putMark(mark, rowNum, colNum);
            if (!ifSuccess) {
                continue;
            }
            break;
        }
    }
}
