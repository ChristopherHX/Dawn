package de.homberger.christopher.dawn.ui.terminal.commands;

import java.util.regex.MatchResult;
import java.util.regex.Pattern;

import de.homberger.christopher.dawn.main.Dawn;
import de.homberger.christopher.dawn.resources.Localisation;
import de.homberger.christopher.dawn.ui.terminal.Command;
import de.homberger.christopher.dawn.ui.terminal.CommandRegex;
import edu.kit.informatik.Terminal;

/**
 * SetVestaCeresCommand
 */
public class SetVestaCeresCommand extends Command {
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
            Terminal.printLine(Localisation.SUCCESS);
        } catch (IllegalAccessError e) {
            Terminal.printError(e.getMessage());
        }
    }
}