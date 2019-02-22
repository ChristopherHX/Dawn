package de.homberger.christopher.dawn.ui.terminal.commands;

import java.util.regex.MatchResult;
import java.util.regex.Pattern;

import de.homberger.christopher.dawn.main.Dawn;
import de.homberger.christopher.dawn.resources.Localisation;
import de.homberger.christopher.dawn.ui.terminal.Command;
import de.homberger.christopher.dawn.ui.terminal.CommandRegex;
import edu.kit.informatik.Terminal;

/**
 * PlaceCommand
 * instructs the game to place a mission controll piece
 * @author Christopher Lukas Homberger
 * @version 0.9.1
 */
public class PlaceCommand extends Command {
    /**
     * place mission controll piece
     */
    public PlaceCommand() {
        super(Pattern.compile(CommandRegex.PLACE_PATTERN));
    }

    @Override
    public void invoke(MatchResult res, Dawn dawn) {
        try {
            dawn.place(Integer.parseInt(res.group(1)), Integer.parseInt(res.group(2)),
            Integer.parseInt(res.group(3)), Integer.parseInt(res.group(4)));
            Terminal.printLine(Localisation.SUCCESS);
        } catch (IllegalAccessError e) {
            Terminal.printError(e.getMessage());
        } catch (IllegalArgumentException e) {
            Terminal.printError(Localisation.INVALID_COMMAND_OR_ARGUMENT + ", " + e.getMessage());
        }
    }
}