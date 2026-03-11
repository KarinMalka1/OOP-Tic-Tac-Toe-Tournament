/**
 * Human-controlled player.
 */
public class HumanPlayer implements Player {

    private static final String ERROR_INPUT_MSG =
            "Invalid mark position. Please choose a valid position: ";
    public static final String ERROR_POSITION_OCCUPIED_MSG =
            "Mark position is already occupied. Please choose a valid position: ";
    private static final String INSTRUCTION_MSG_PART1 = "Player ";
    private static final String INSTRUCTION_MSG_PART2 = ", type coordinates: ";
    private static final int TO_RECEIVE_DIGIT = 10;

    /**
     * Constructs a human player.
     */
    public HumanPlayer() {

    }

    /**
     * Reads input and performs a valid move.
     * @param board current game board
     * @param mark  player's mark
     */
    @Override
    public void playTurn(Board board, Mark mark){
        System.out.println(INSTRUCTION_MSG_PART1 + mark + INSTRUCTION_MSG_PART2);
        while (true) {
            int userInput =  KeyboardInput.readInt();
            int rowNum = userInput / TO_RECEIVE_DIGIT;
            int colNum = userInput % TO_RECEIVE_DIGIT;
            if (rowNum < 0 || rowNum > board.getSize() ||
                    colNum < 0 || colNum > board.getSize()) {
                System.out.println(ERROR_INPUT_MSG);
                continue;
            }
            else if (!board.putMark(mark, rowNum, colNum)){
                System.out.println(ERROR_POSITION_OCCUPIED_MSG);
                continue;
            }
            break;
        }
    }
}
