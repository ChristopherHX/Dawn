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
 * Starts the Dawn Game with the Terminal UI
 * with its main loop
 * @author Christopher Lukas Homberger
 * @version 0.9.1
 */
public final class DawnGameApp {
    /**
     * Prohibit contruction of this static final class
     */
    private DawnGameApp() {
    }

    /**
     * Starts the terminal user interface
     * and handles the Terminal input
     * @param args The ignored arguments of this console application
     */
    public static void main(String[] args) {
        // conains all allowed commands
        Command[] commands = new Command[] {new StateCommand(), new PrintCommand(), new SetVestaCeresCommand(), 
            new RollCommand(), new PlaceCommand(), new MoveCommand(), new ShowResultCommand(), new ResetCommand()};
        // Instance of the Dawn Game main logic to share across the Commands
        Dawn dawn = new Dawn();
        // Compiles the quit regex to speed up matching
        Pattern quit = Pattern.compile(CommandRegex.QUIT_PATTERN);
        // Label to break the inner loop and continue the mainloop
        mainloop: while (true) {
            // wait for next command
            String line = Terminal.readLine();
            // Quit the programm if it matches the quit pattern
            if (quit.matcher(line).matches()) {
                return;
            }
            for (final Command command : commands) {
                // Try to invoke the nth command with that line
                if (command.tryInvoke(line, dawn)) {
                    // Successfully invoked wait for next command
                    continue mainloop;
                }
            }
            // Prints error if no pattern matches (Invalid Command or Argument)
            Terminal.printError(Localisation.INVALID_COMMAND_OR_ARGUMENT);
        }
    }
}
