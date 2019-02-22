package de.homberger.christopher.dawn.ui.terminal.commands;

import java.util.regex.MatchResult;
import java.util.regex.Pattern;

import de.homberger.christopher.dawn.main.Dawn;
import de.homberger.christopher.dawn.resources.GameConstants;
import de.homberger.christopher.dawn.resources.Localisation;
import de.homberger.christopher.dawn.ui.terminal.Command;
import de.homberger.christopher.dawn.ui.terminal.CommandRegex;
import edu.kit.informatik.Terminal;

/**
 * RollCommand
 * Rolls the dice with specfic value and instruct the game to use it
 * @author Christopher Lukas Homberger
 * @version 0.9.1
 */
public class RollCommand extends Command {
    /**
     * Rolls the deteministic pseudo dice
     */
    public RollCommand() {
        super(Pattern.compile(CommandRegex.ROLL_PATTERN));
    }

    @Override
    public void invoke(MatchResult res, Dawn dawn) {
        try {
            // converts argument to number or DAWN the only allowed code which must not be a number as biggest piece
            // number range is checked in main game
            dawn.roll(res.group(1).equals(Localisation.DAWN)
            ? GameConstants.BIGGEST_PIECE : Integer.parseInt(res.group(1)));
            // Succeed, without errors
            Terminal.printLine(Localisation.SUCCESS);
        } catch (IllegalAccessError e) {
            Terminal.printError(e.getMessage());
        } catch (IllegalArgumentException e) {
            Terminal.printError(Localisation.INVALID_COMMAND_OR_ARGUMENT + ", " + e.getMessage());
        }
    }
}