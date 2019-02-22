package de.homberger.christopher.dawn.resources;

/**
 * GameConstants, which alter the game
 * @author Christopher Lukas Homberger
 * @version 0.9.1
 */
public class GameConstants {
    /**
     * Width of the Gameboard
     */
    public static final int BOARDWIDTH = 15;
    
    /**
     * Height of the Gameboard
     */
    public static final int BOARDHEIGHT = 11;

    /**
     * Bitmask of Piecesset with length from 2 to 7
     */
    public static final int PIECES_SET = 0xFC;

    /**
     * Number of phases per game
     */
    public static final int PHASES = 2;

    /**
     * Number of rounds per phase
     */
    public static final int ROUNDS = 6;

    /**
     * Action number of roll command
     */
    public static final int ROLL_ACTION = 0;

    /**
     * Action number of place command
     */
    public static final int PLACE_ACTION = 1;

    /**
     * Action number of move command
     */
    public static final int MOVE_ACTION = 2;

}