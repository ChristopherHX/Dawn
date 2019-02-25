package de.homberger.christopher.dawn.ui.terminal.commands;

import java.util.regex.MatchResult;
import java.util.regex.Pattern;

import de.homberger.christopher.dawn.main.Dawn;
import de.homberger.christopher.dawn.ui.terminal.CommandRegex;
import de.homberger.christopher.ui.terminal.Command;
import de.homberger.christopher.ui.terminal.resources.Localisation;
import edu.kit.informatik.Terminal;

/**
 * ResetCommand
 * calls reset on the game, then prints OK
 * @author Christopher Lukas Homberger
 * @version 0.9.2
 */
public class ResetCommand extends Command<Dawn> {
    /**
     * Resets the Game
     */
    public ResetCommand() {
        super(Pattern.compile(CommandRegex.RESET_PATTERN));
    }

    @Override
    public void invoke(MatchResult res, Dawn dawn) {
        dawn.reset();
        Terminal.printLine(Localisation.SUCCESS);
    }
}