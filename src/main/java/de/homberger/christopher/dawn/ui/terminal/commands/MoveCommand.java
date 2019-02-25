package de.homberger.christopher.dawn.ui.terminal.commands;

import java.util.ArrayList;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.homberger.christopher.dawn.main.Dawn;
import de.homberger.christopher.dawn.ui.terminal.CommandRegex;
import de.homberger.christopher.ui.terminal.Command;
import de.homberger.christopher.ui.terminal.resources.Localisation;
import edu.kit.informatik.Terminal;

/**
 * MoveCommand
 * Translates input not path array and instucts the game to move vesta or ceres
 * @author Christopher Lukas Homberger
 * @version 0.9.2
 */
public class MoveCommand extends Command<Dawn> {
    private final Pattern coordinate;

    /**
     * Moves the Peaces
     */
    public MoveCommand() {
        super(Pattern.compile(CommandRegex.MOVE_PATTERN));
        // store coordinate pattern for the move path
        coordinate = Pattern.compile(CommandRegex.INNER_COORDINATE);
    }

    @Override
    public void invoke(MatchResult res, Dawn dawn) {
        String spath = res.group(1);
        ArrayList<int[]> apath = new ArrayList<>();
        Matcher m = coordinate.matcher(spath);
        while (m.find()) {
            apath.add(new int[]{Integer.parseInt(m.group(1)), Integer.parseInt(m.group(2))});
        }
        int[][] path = new int[apath.size()][];
        try {
            dawn.move(apath.toArray(path));
            Terminal.printLine(Localisation.SUCCESS);
        } catch (IllegalAccessError e) {
            Terminal.printError(e.getMessage());
        } catch (IllegalArgumentException e) {
            Terminal.printError(Localisation.INVALID_COMMAND_OR_ARGUMENT + ", " + e.getMessage());
        }
    }
}