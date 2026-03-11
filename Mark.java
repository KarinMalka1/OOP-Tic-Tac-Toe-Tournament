/**
 * Represents possible marks on the board.
 */
enum Mark{
    BLANK, X, O;

    /**
     * Returns a printable string for the mark.
     * @return "X", "O", or null for BLANK
     */
    @Override
    public String toString() {
        switch (this) {
            case X:
                return "X";
            case O:
                return "O";
            default:
                return null;
        }
    }

}
