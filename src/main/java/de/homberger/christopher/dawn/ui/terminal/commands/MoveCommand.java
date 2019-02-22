package de.homberger.christopher.dawn.ui.terminal.commands;

import java.util.ArrayList;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.homberger.christopher.dawn.main.Dawn;
import de.homberger.christopher.dawn.resources.Localisation;
import de.homberger.christopher.dawn.ui.terminal.Command;
import de.homberger.christopher.dawn.ui.terminal.CommandRegex;
import edu.kit.informatik.Terminal;

/**
 * MoveCommand
 * @author Christopher Lukas Homberger
 * @version 0.9.1
 */
public class MoveCommand extends Command {
    private final Pattern coordinate;

    /**
     * Moves the Peaces
     */
    public MoveCommand() {
        super(Pattern.compile(CommandRegex.MOVE_PATTERN));
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
        }
    }
}