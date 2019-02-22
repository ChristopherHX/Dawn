package de.homberger.christopher.dawn.ui.terminal;

import java.util.regex.Pattern;

import de.homberger.christopher.dawn.main.Dawn;
import de.homberger.christopher.dawn.resources.Localisation;
import de.homberger.christopher.dawn.ui.terminal.Command;
import de.homberger.christopher.dawn.ui.terminal.CommandRegex;
import de.homberger.christopher.dawn.ui.terminal.commands.PlaceCommand;
import de.homberger.christopher.dawn.ui.terminal.commands.PrintCommand;
import de.homberger.christopher.dawn.ui.terminal.commands.ResetCommand;
import de.homberger.christopher.dawn.ui.terminal.commands.RollCommand;
import de.homberger.christopher.dawn.ui.terminal.commands.SetVestaCeresCommand;
import de.homberger.christopher.dawn.ui.terminal.commands.MoveCommand;
import de.homberger.christopher.dawn.ui.terminal.commands.ShowResultCommand;
import de.homberger.christopher.dawn.ui.terminal.commands.StateCommand;
import edu.kit.informatik.Terminal;

/**
 * Hello world!
 */
public final class App {
    /**
     * Prohibit contruction of this static final class
     */
    private App() {
    }

    /**
     * Starts the terminal user interface
     * @param args The ignored arguments of the program.
     */
    public static void main(String[] args) {
        // conatins all allowed commands
        Command[] commands = new Command[] {new StateCommand(), new PrintCommand(), new SetVestaCeresCommand(), 
            new RollCommand(), new PlaceCommand(), new MoveCommand(), new ShowResultCommand(), new ResetCommand()};
        Dawn dawn = new Dawn();
        Pattern quit = Pattern.compile(CommandRegex.QUIT_PATTERN);
        while (true) {
            String line = Terminal.readLine();
            // Quit the programm if it matches the quit pattern
            if (quit.matcher(line).matches()) {
                return;
            }
            boolean matched = false;
            for (final Command command : commands) {
                matched = command.tryMatch(line, dawn);
                if (matched) {
                    break;
                }
            }
            // Prints error if no pattern matches
            if (!matched) {
                Terminal.printError(Localisation.INVALID_COMMAND);
            }
        }
    }
}
