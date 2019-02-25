package de.homberger.christopher.dawn.ui.terminal;

import java.util.Arrays;

import de.homberger.christopher.dawn.main.Dawn;
import de.homberger.christopher.dawn.ui.terminal.commands.PlaceCommand;
import de.homberger.christopher.dawn.ui.terminal.commands.PrintCommand;
import de.homberger.christopher.dawn.ui.terminal.commands.ResetCommand;
import de.homberger.christopher.dawn.ui.terminal.commands.RollCommand;
import de.homberger.christopher.dawn.ui.terminal.commands.SetVestaCeresCommand;
import de.homberger.christopher.dawn.ui.terminal.commands.MoveCommand;
import de.homberger.christopher.dawn.ui.terminal.commands.ShowResultCommand;
import de.homberger.christopher.dawn.ui.terminal.commands.StateCommand;
import de.homberger.christopher.ui.terminal.ConsoleApp;

/**
 * DawnApp
 * specifies the used commands without implicit quit
 */
public class DawnApp {

    public static void main(String[] args) {
        // Instance of the Dawn Game main logic to share across the Commands
        Dawn dawn = new Dawn();
        ConsoleApp<Dawn> app = new ConsoleApp<Dawn>(Arrays.asList(new StateCommand(), new PrintCommand(), new SetVestaCeresCommand(), 
            new RollCommand(), new PlaceCommand(), new MoveCommand(), new ShowResultCommand(), new ResetCommand()));
        app.run(dawn);
    }
}