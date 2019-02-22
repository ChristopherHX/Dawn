package de.homberger.christopher.dawn.ui.terminal.commands;

import java.util.regex.MatchResult;
import java.util.regex.Pattern;

import de.homberger.christopher.dawn.main.Dawn;
import de.homberger.christopher.dawn.resources.Localisation;
import de.homberger.christopher.dawn.ui.terminal.Command;
import de.homberger.christopher.dawn.ui.terminal.CommandRegex;
import edu.kit.informatik.Terminal;

/**
 * RollCommand
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
            dawn.roll(res.group(1).length() == 1 ? Integer.parseInt(res.group(1)) : 7);
            Terminal.printLine(Localisation.SUCCESS);
        } catch (IllegalAccessError e) {
            Terminal.printError(e.getMessage());
        }
    }
}