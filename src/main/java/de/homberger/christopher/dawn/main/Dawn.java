package de.homberger.christopher.dawn.main;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import de.homberger.christopher.dawn.resources.GameConstants;
import de.homberger.christopher.dawn.resources.Localisation;

/**
 * Models the Dawn Game
 * with its main logic and game rules
 * @author Christopher Lukas Homberger
 * @version 0.9.1
 */
public class Dawn {
    /**
     * pieces placed on the board
     */
    private List<IPiece> active;
    /**
     * Bitmask of available Pieces,
     * piece with length 2 is stored as 1 << 2 ( 2^2, 4 ),
     * DAWN piece with length 7 is stored as 1 << 7 ( 2^7, 128 )
     */
    private int missioncontrollpieces;
    /**
     * Rolled number / length of placed mission controll piece
     */
    private int rolled;
    /**
     * Current phase
     */
    private int phase;
    /**
     * current round
     */
    private int round;
    /**
     * current action i / ii / iii
     */
    private int action;
    /**
     * vesta piece backup for gameresult avoid searching
     */
    private VestaCeresPiece vesta;
    /**
     * Curent vesta / Ceres piece to move around
     */
    private VestaCeresPiece vc;

    /**
     * Initialize the Dawn game and resets
     */
    public Dawn() {
        active = new ArrayList<>();
        reset();
    }

    /**
     * Get the Piece at that coordinate
     * @param m vertical coordinate
     * @param n horizontal coordinate
     * @return the piece or null if none are there
     */
    public IPiece state(int m, int n) {
        for (final IPiece piece : active) {
            if (piece.contains(m, n)) {
                return piece;
            }
        }
        return null;
    }

    /**
     * Place Vesta or Ceres
     * @param m vertical coordinate
     * @param n horizontal coordinate
     * @throws IllegalAccessError Method not allowed, field is used
     */
    public void setVC(int m, int n) {
        if (phase < 0 || phase >= GameConstants.PHASES || round != 0) {
            throw new IllegalAccessError(Localisation.PROHIBIT_OPERATION);
        }
        if (state(m, n) != null) {
            throw new IllegalAccessError(Localisation.USED_FIELD);
        }
        vc = new VestaCeresPiece(m, n, phase == 0 ? Type.VESTA : Type.CERES);
        // Backup Vesta for faster results calculation
        if (phase == 0) {
            vesta = vc;
        }
        active.add(vc);
        round++;
    }

    /**
     * Rolls the dice with an explicit number
     * @param code 2-6 normal pieces, 7 DAWN
     * @throws IllegalAccessError Method not allowed
     */
    public void roll(int code) throws IllegalAccessError {
        if (phase < 0 || phase >= GameConstants.PHASES || round < 1 || round > GameConstants.ROUNDS
        || action != GameConstants.ROLL_ACTION) {
            throw new IllegalAccessError(Localisation.PROHIBIT_OPERATION);
        }
        rolled = code;
        action++;
    }

    /**
     * Place a mission controll piece
     * @param m1 vertical coordinate 1 of rectangle
     * @param n1 horizontal coordinate 1 of rectangle
     * @param m2 vertical coordinate 2 of rectangle
     * @param n2 horizontal coordinate 2 of rectangle
     * @throws IllegalAccessError Method not allowed, piece not exists or used
     */
    public void place(int m1, int n1, int m2, int n2) throws IllegalAccessError {
        if (phase < 0 || phase >= GameConstants.PHASES || round < 1 || round > GameConstants.ROUNDS
        || action != GameConstants.PLACE_ACTION) {
            throw new IllegalAccessError(Localisation.PROHIBIT_OPERATION);
        }
        int ml = Math.abs(m1 - m2) + 1;
        int nl = Math.abs(n1 - n2) + 1;
        if (ml <= 0 || nl <= 0 || ml > 1 && nl > 1) {
            throw new IllegalAccessError(Localisation.INVALID_PIECE);
        }
        int length = Math.max(ml, nl);
        int toplace = 1 << length;
        if ((toplace & missioncontrollpieces) != toplace) {
            throw new IllegalAccessError(Localisation.USED_PIECE);
        }
        // Force to use next to shouldplaced piece
        {
            int shouldplaced = 1 << rolled;
            if (toplace != shouldplaced) {
                while (toplace > shouldplaced) {
                    if ((shouldplaced & missioncontrollpieces) == shouldplaced) {
                        throw new IllegalAccessError(Localisation.INVALID_PIECE);                    
                    }
                    shouldplaced <<= 1;
                }
                while (toplace < shouldplaced) {
                    if ((shouldplaced & missioncontrollpieces) == shouldplaced) {
                        throw new IllegalAccessError(Localisation.INVALID_PIECE);                    
                    }
                    shouldplaced >>= 1;
                }
            }
        }
        int m = Math.min(m1, m2);
        int n = Math.min(n1, n2);
        // Test for uses fields
        for (int i = 0; i < ml; i++) {
            for (int j = 0; j < nl; j++) {
                if (state(m + i, n + j) != null) {
                    throw new IllegalAccessError(Localisation.USED_FIELD);
                }
            }
        }
        active.add(new MissionControllPiece(m, n, ml == 1 ? Orientation.HORIZONTAL : Orientation.VERTICAL, length));
        // Sync rolled with length to force placed piece length to comform the rules,
        // not like the Version 1 - 2 of the Public Test
        rolled = length;
        // Removes the piece
        missioncontrollpieces &= ~toplace;
        // If no moves are possible for vc stepover to next round
        if (((vc.getY() + 1) >= GameConstants.BOARDHEIGHT || state(vc.getY() + 1, vc.getX()) != null)
        && ((vc.getY() - 1) < 0 || state(vc.getY() - 1, vc.getX()) != null)
        && ((vc.getX() + 1) >= GameConstants.BOARDWIDTH || state(vc.getY(), vc.getX() + 1) != null)
        && ((vc.getX() - 1) < 0 || state(vc.getY(), vc.getX() - 1) != null)) {
            nextRound();
        } else {
            // Move to next action
            action++;
        }
    }

    /**
     * Moves Vesta / Ceres if it's possible
     * @param path to move the Vesta or Ceres Piece
     * @throws IllegalAccessError Method not allowed or illegal path array
     */
    public void move(int[][] path) throws IllegalAccessError {
        if (phase < 0 || phase >= GameConstants.PHASES || round < 1 || round > GameConstants.ROUNDS
        || action != GameConstants.MOVE_ACTION) {
            throw new IllegalAccessError(Localisation.PROHIBIT_OPERATION);
        }
        if (path == null || path.length < 1 || path.length > rolled || path[0] == null
        || ((path[0][0] != vc.getY() || Math.abs(path[0][1] - vc.getX()) != 1)
        && (path[0][1] != vc.getX() || Math.abs(path[0][0] - vc.getY()) != 1))) {
            throw new IllegalAccessError(Localisation.ILLEGAL_PATH);
        }
        // Backup Vesta / Ceres old position
        int x = vc.getX();
        int y = vc.getY();
        try {
            for (int i = 0; i < path.length - 1; i++) {
                if (path[i] == null || (path[i][0] != path[i + 1][0] || Math.abs(path[i][1] - path[i + 1][1]) != 1)
                && (path[i][1] != path[i + 1][1] || Math.abs(path[i][0] - path[i + 1][0]) != 1)) {
                    throw new IllegalAccessError(Localisation.ILLEGAL_PATH);                
                }
                if (state(path[i][0], path[i][1]) != null) {
                    throw new IllegalAccessError(Localisation.USED_FIELD);
                }
                vc.setPos(path[i][0], path[i][1]);
            }
            if (state(path[path.length - 1][0], path[path.length - 1][1]) != null) {
                throw new IllegalAccessError(Localisation.USED_FIELD);
            }
            vc.setPos(path[path.length - 1][0], path[path.length - 1][1]);
        } catch (IllegalAccessError e) {
            // Restore Vesta / Ceres if failed
            vc.setPos(y, x);
            // rethrow exception to caller
            throw e;
        }
        nextRound();
    }

    /**
     * Move to the next round or finish the game
     */
    private void nextRound() {
        action = 0;
        round++;
        if (round == 7) {
            round = 0;
            phase++;
            if (phase == 1) {
                vc = null;
                missioncontrollpieces = GameConstants.PIECES_SET;
            }
        }
    }

    /**
     * Recursivly calculate free fieds
     * @param fields Hashmap to store hashs of already done free fields
     * @param y vertical coordinate
     * @param x horizontal coordinate
     */
    private void calculateFreeFields(HashSet<Integer> fields, int y, int x) {
        // Check upper
        if ((y + 1) < GameConstants.BOARDHEIGHT && state(y + 1, x) == null) {
            // hashcode coordiantes only use the lower bits therefore store both in one int
            int code = (y + 1) << 16 + x;
            // If new fields call recusivly again
            if (fields.add(code)) {
                calculateFreeFields(fields, y + 1, x);
            }
        }
        // Check lower
        if ((y - 1) >= 0 && state(y - 1, x) == null) {
            // hashcode coordiantes only use the lower bits therefore store both in one int
            int code = (y - 1) << 16 + x;
            // If new fields call recusivly again
            if (fields.add(code)) {
                calculateFreeFields(fields, y - 1, x);
            }
        }
        // Check right
        if ((x + 1) < GameConstants.BOARDWIDTH && state(y, x + 1) == null) {
            // hashcode coordiantes only use the lower bits therefore store both in one int
            int code = y << 16 + (x + 1);
            // If new fields call recusivly again
            if (fields.add(code)) {
                calculateFreeFields(fields, y, x + 1);
            }
        }
        // Check left
        if ((x - 1) >= 0 && state(y, x - 1) == null) {
            // hashcode coordiantes only use the lower bits therefore store both in one int
            int code = y << 16 + (x - 1);
            // If new fields call recusivly again
            if (fields.add(code)) {
                calculateFreeFields(fields, y, x - 1);
            }
        }
    }

    /**
     * Calcualte the Result
     * F(V) free field around Vesta {@code calculateFreeFields}
     * F(C) free field around Ceres {@code calculateFreeFields}
     * E = max{F(C),F(V)}+[max{F(C),F(V)}−min{F(C),F(V)}]
     * or simply E=2*max{F(C),F(V)}−min{F(C),F(V)}
     * @return the result E
     * @throws IllegalAccessError game is running
     */
    public int getResult() {
        if (phase != 2) {
            throw new IllegalAccessError(Localisation.PROHIBIT_OPERATION);
        }
        HashSet<Integer> fields = new HashSet<>();
        calculateFreeFields(fields, this.vesta.getY(), this.vesta.getX());
        int fv = fields.size();
        fields.clear();
        calculateFreeFields(fields, this.vc.getY(), this.vc.getX());
        int fc = fields.size();
        return 2 * Math.max(fv, fc) - Math.min(fv, fc);
    }

    /**
     * Resets the Game
     */
    public void reset() {
        active.clear();
        missioncontrollpieces = GameConstants.PIECES_SET;
        rolled = 0;
        phase = 0;
        round = 0;
        action = 0;
    }
}