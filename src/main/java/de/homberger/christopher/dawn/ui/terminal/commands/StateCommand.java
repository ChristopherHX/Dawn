package de.homberger.christopher.dawn.ui.terminal.commands;

import java.util.regex.MatchResult;
import java.util.regex.Pattern;

import de.homberger.christopher.dawn.main.Dawn;
import de.homberger.christopher.dawn.main.IPiece;
import de.homberger.christopher.dawn.resources.Localisation;
import de.homberger.christopher.dawn.ui.terminal.Command;
import de.homberger.christopher.dawn.ui.terminal.CommandRegex;
import edu.kit.informatik.Terminal;

/**
 * StateCommand
 * retrieve the state of a board field
 * @author Christopher Lukas Homberger
 * @version 0.9.1
 */
public class StateCommand extends Command {
    /**
     * Check the state of the coordinate
     */
    public StateCommand() {
        super(Pattern.compile(CommandRegex.STATE_PATTERN));
    }

    /**
     * Gets the String representation of that piece for Terminal output
     * @param piece piece to get the string representation
     * @return the string representation for the console
     */
    public static String state(IPiece piece) {
        if (piece == null) {
            return "-";
        } else {
            switch (piece.getType()) {
                case CERES:
                    return "C";
                case NORMAL:
                    return "+";
                case VESTA:
                    return "V";
                default:
                    return null;
            }
        }
    }

    @Override
    public void invoke(MatchResult res, Dawn dawn) {
        int m = Integer.parseInt(res.group(1));
        int n = Integer.parseInt(res.group(2));
        try {
            Terminal.printLine(state(dawn.state(m, n)));            
        } catch (IllegalArgumentException e) {
            Terminal.printError(Localisation.INVALID_COMMAND_OR_ARGUMENT + ", " + e.getMessage());
        }
    }
}