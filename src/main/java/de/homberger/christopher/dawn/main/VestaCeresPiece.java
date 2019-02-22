package de.homberger.christopher.dawn.main;

import de.homberger.christopher.dawn.resources.Localisation;

/**
 * Vesta / Ceres piece
 * @author Christopher Lukas Homberger
 * @version 0.9.1
 */
public class VestaCeresPiece implements IPiece {
    private int y;
    private int x;
    private final Type type;

    /**
     * Create Vesta or Ceres
     * @param y vertical coordinate
     * @param x horizontal coordinate
     * @param type Vesta or Ceres
     */
    public VestaCeresPiece(int y, int x, Type type) {
        if (type == Type.NORMAL) {
            throw new IllegalAccessError(Localisation.PROHIBIT_OPERATION);
        }
        this.y = y;
        this.x = x;
        this.type = type;
        // ToDo RangeCheck
    }

    /**
     * Set's new position
     * @param y new vertical coordinate
     * @param x new horizontal coordinate
     */
    public void setPos(int y, int x) {
        this.y = y;
        this.x = x;
    }
    
    /**
     * @return the horizontal coordinate x
     */
    public int getX() {
        return x;
    }

    /**
     * @return the vertical coordinate y
     */
    public int getY() {
        return y;
    }

    @Override
    public boolean contains(int m, int n) {
        return y == m && x == n;
    }

    @Override
    public Type getType() {
        return type;
    }

    
}