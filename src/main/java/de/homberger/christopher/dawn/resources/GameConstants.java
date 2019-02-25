package de.homberger.christopher.dawn.resources;

/**
 * GameConstants, which alter the game
 * @author Christopher Lukas Homberger
 * @version 0.9.2
 */
public class GameConstants {
    /**
     * Width of the Gameboard (> 0)
     */
    public static final int BOARDWIDTH = 15;
    
    /**
     * Height of the Gameboard (> 0)
     */
    public static final int BOARDHEIGHT = 11;

    /**
     * smallest piece in the set, have to >= 1
     */
    public static final int SMALLEST_PIECE = 2;

    
    /**
     * biggest piece in the set, have to >= SMALLEST_PIECE
     */
    public static final int BIGGEST_PIECE = 7;

    /**
     * Bitmask of Piecesset with length from SMALLEST_PIECE to BIGGEST_PIECE
     */
    public static final int PIECES_SET = (1 << (BIGGEST_PIECE + 1)) - (1 << SMALLEST_PIECE);

    /**
     * Number of phases per game
     * (PHASES > 2 needs code additions no more nature figures)
     * ()
     */
    public static final int PHASES = 2;

    /**
     * Number of rounds per phase
     * allowed from 1 to maxvalue
     */
    public static final int ROUNDS = 6;

    /**
     * Action number of roll command
     * (Please not modify)
     */
    public static final int ROLL_ACTION = 0;

    /**
     * Action number of place command
     * (Please not modify)
     */
    public static final int PLACE_ACTION = 1;

    /**
     * Action number of move command
     * (Please not modify)
     */
    public static final int MOVE_ACTION = 2;

}