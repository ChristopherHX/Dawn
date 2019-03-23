package de.homberger.christopher.dawn.ui.terminal.commands;

import java.util.regex.MatchResult;
import java.util.regex.Pattern;

import de.homberger.christopher.dawn.main.Dawn;
import de.homberger.christopher.dawn.ui.terminal.CommandRegex;
import de.homberger.christopher.ui.terminal.Command;
import de.homberger.christopher.ui.terminal.resources.Localisation;

/**
 * PlaceCommand
 * instructs the game to place a mission controll piece
 * @author Christopher Lukas Homberger
 * @version 0.9.2
 */
public class PlaceCommand extends Command<Dawn> {
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
            System.out.println(Localisation.SUCCESS);
        } catch (IllegalAccessError e) {
            System.err.println(e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println(Localisation.INVALID_COMMAND_OR_ARGUMENT + ", " + e.getMessage());
        }
    }
}