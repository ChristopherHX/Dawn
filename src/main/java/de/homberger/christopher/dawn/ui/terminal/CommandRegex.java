package de.homberger.christopher.dawn.ui.terminal;

/**
 * CommandRegex
 * stores all regex for command input
 */
public class CommandRegex {
    /**
     * coordiante check
     */
    public static final String INNER_COORDINATE = "(10|[0-9]);(1[0-4]|[0-9])";

    /**
     * extended range coordinate check for dawn piece
     */
    public static final String DAWN_COORDINATE = "(1[0-6]|[0-9]|-[1-6]);(1[0-9]|20|[0-9]|-[1-6])";

    /**
     * state command regex
     */
    public static final String STATE_PATTERN = "state " + INNER_COORDINATE;

    /**
     * print command regex
     */
    public static final String PRINT_PATTERN = "print";

    /**
     * set vesta ceres command regex
     */
    public static final String SET_VC_PATTERN = "set-vc " + INNER_COORDINATE;

    /**
     * roll command regex
     */
    public static final String ROLL_PATTERN = "roll ([2-6]|DAWN)";

    /**
     * place command regex
     */
    public static final String PLACE_PATTERN = "place " + DAWN_COORDINATE + ":" + DAWN_COORDINATE;

    /**
     * move command regex
     */
    public static final String MOVE_PATTERN = "move (" + INNER_COORDINATE + "(:" + INNER_COORDINATE + ")*)";

    /**
     * Show Result command regex
     */
    public static final String SHOW_RESULT_PATTERN = "show-result";

    /**
     * Reset command regex
     */
    public static final String RESET_PATTERN = "reset";

    /**
     * Quit command regex
     */
    public static final String QUIT_PATTERN = "quit";
}