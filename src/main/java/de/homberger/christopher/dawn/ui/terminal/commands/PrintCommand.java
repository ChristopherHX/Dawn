package de.homberger.christopher.dawn.ui.terminal.commands;

import java.util.regex.MatchResult;
import java.util.regex.Pattern;

import de.homberger.christopher.dawn.main.Dawn;
import de.homberger.christopher.dawn.resources.GameConstants;
import de.homberger.christopher.dawn.ui.terminal.Command;
import de.homberger.christopher.dawn.ui.terminal.CommandRegex;
import edu.kit.informatik.Terminal;

/**
 * PrintCommand
 */
public class PrintCommand extends Command {
    /**
     * Print the Peaces
     */
    public PrintCommand() {
        super(Pattern.compile(CommandRegex.PRINT_PATTERN));
    }

    @Override
    public void invoke(MatchResult res, Dawn dawn) {
        for (int i = 0; i < GameConstants.BOARDHEIGHT; i++) {
            StringBuilder line = new StringBuilder();
            for (int j = 0; j < GameConstants.BOARDWIDTH; j++) {
                line.append(StateCommand.state(dawn.state(i, j)));
            }
            Terminal.printLine(line.toString());
        }
    }
}