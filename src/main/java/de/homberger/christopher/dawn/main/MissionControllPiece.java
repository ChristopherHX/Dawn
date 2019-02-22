package de.homberger.christopher.dawn.main;

import de.homberger.christopher.dawn.resources.GameConstants;
import de.homberger.christopher.dawn.resources.Localisation;

/**
 * MissionControllPiece
 * @author Christopher Lukas Homberger
 * @version 0.9.1
 */
public class MissionControllPiece implements IPiece {
    private final int y;
    private final int x;
    private final Orientation orientation;
    private final int length;

    /**
     * Create a new MissionControllPiece
     * @param y vertical coordinate
     * @param x horizontal coordinate
     * @param orientation vertical or hoizontal piece
     * @param length length in orientation
     * @throws IllegalAccessError it's an invalid piece
     */
    public MissionControllPiece(int y, int x, Orientation orientation, int length) throws IllegalAccessError {
        this.y = y;
        this.x = x;
        this.orientation = orientation;
        this.length = length;
        // ToDo RangeCheck
        int minwidth = (orientation == Orientation.HORIZONTAL && length == 7) ? -6 : 0;
        int maxwidth = GameConstants.BOARDWIDTH + ((orientation == Orientation.HORIZONTAL && length == 7) ? 6 : 0);
        int minheight = (orientation == Orientation.VERTICAL && length == 7) ? -6 : 0;
        int maxheight = GameConstants.BOARDHEIGHT + ((orientation == Orientation.VERTICAL && length == 7) ? 6 : 0);
        if (length != 7 && (y < minwidth || y + getHeight() > maxheight
        || x < minheight || x + getWidth() > maxwidth)) {
            throw new IllegalAccessError(Localisation.INVALID_PIECE);
        }
    }

    /**
     * Get the width of this piece
     */
    private int getWidth() {
        return orientation == Orientation.HORIZONTAL ? length : 1;
    }

    /**
     * Get the height of this piece
     */
    private int getHeight() {
        return orientation == Orientation.VERTICAL ? length : 1;
    }

    @Override
    public boolean contains(int m, int n) {
        return y <= m && m < y + getHeight() && x <= n && n < x + getWidth();
    }

    @Override
    public Type getType() {
        return Type.NORMAL;
    }    
}