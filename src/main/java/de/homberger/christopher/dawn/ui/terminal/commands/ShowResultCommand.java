package de.homberger.christopher.dawn.ui.terminal.commands;

import java.util.regex.MatchResult;
import java.util.regex.Pattern;

import de.homberger.christopher.dawn.main.Dawn;
import de.homberger.christopher.ui.terminal.Command;
import de.homberger.christopher.dawn.ui.terminal.CommandRegex;
import edu.kit.informatik.Terminal;

/**
 * ShowResultCommand
 * calculate result value and print it to the Terminal
 * @author Christopher Lukas Homberger
 * @version 0.9.2
 */
public class ShowResultCommand extends Command<Dawn> {
    /**
     * Prints the result or fails
     */
    public ShowResultCommand() {
        super(Pattern.compile(CommandRegex.SHOW_RESULT_PATTERN));
    }

    @Override
    public void invoke(MatchResult res, Dawn dawn) {
        try {
            Terminal.printLine(dawn.getResult());
        } catch (IllegalAccessError e) {
            Terminal.printError(e.getMessage());
        }
    }
}