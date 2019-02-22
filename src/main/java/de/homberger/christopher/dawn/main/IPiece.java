package de.homberger.christopher.dawn.main;

/**
 * IPiece
 */
public interface IPiece {
    /**
     * Check if the coordinate is in this piece 
     * @param m vertical coordinate
     * @param n horizontal coordinate
     * @return is containes in this piece
     */
    boolean contains(int m, int n);

    /**
     * Get the type
     * @return the Type of this Piece
     */
    Type getType();
}