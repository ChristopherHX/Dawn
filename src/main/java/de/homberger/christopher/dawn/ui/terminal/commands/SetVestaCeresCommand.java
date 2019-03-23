package de.homberger.christopher.dawn.ui.terminal.commands;

import java.util.regex.MatchResult;
import java.util.regex.Pattern;

import de.homberger.christopher.dawn.main.Dawn;
import de.homberger.christopher.dawn.ui.terminal.CommandRegex;
import de.homberger.christopher.ui.terminal.Command;
import de.homberger.christopher.ui.terminal.resources.Localisation;

/**
 * SetVestaCeresCommand
 * instruct the game to place vesta or ceres on the board
 * @author Christopher Lukas Homberger
 * @version 0.9.2
 */
public class SetVestaCeresCommand extends Command<Dawn> {
    /**
     * Places Vesat or Ceres or failes
     */
    public SetVestaCeresCommand() {
        super(Pattern.compile(CommandRegex.SET_VC_PATTERN));
    }

    @Override
    public void invoke(MatchResult res, Dawn dawn) {
        try {
            dawn.setVC(Integer.parseInt(res.group(1)), Integer.parseInt(res.group(2)));
            System.out.println(Localisation.SUCCESS);
        } catch (IllegalAccessError e) {
            System.err.println(e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println(Localisation.INVALID_COMMAND_OR_ARGUMENT + ", " + e.getMessage());
        }
    }
}