/**
 * Factory for creating player instances.
 */
public class PlayerFactory {


    /**
     * Constructs a player factory.
     */
    public PlayerFactory() {

    }

    /**
     * Creates a player of the given type.
     * @param type player type
     * @return new player instance, or null if invalid
     */
    public Player buildPlayer(String type) {
        switch (type) {
            case "human":
                return new HumanPlayer();
            case "whatever":
                return new WhateverPlayer();
            case "naive":
                return new NaivePlayer();
            case "smart":
                return new SmartPlayer();
            default:
                return null;
        }
    }
}