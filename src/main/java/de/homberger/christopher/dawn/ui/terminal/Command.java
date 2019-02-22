package de.homberger.christopher.dawn.ui.terminal;

import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.homberger.christopher.dawn.main.Dawn;

/**
 * Command super class of all terminal commands
 */
public abstract class Command {
    private final Pattern pattern;

    /**
     * Create a new Command with a specfic regex pattern
     * @param pattern Regex pattern for the Command
     */
    public Command(Pattern pattern) {
        this.pattern = pattern;
    }

    /**
     * Try to Math the Command and invokes it
     * @param line Terminal line input
     * @param dawn Game Object to be passed to invoke
     * @return true if it had mached and was invoked
     */
    public boolean tryMatch(String line, Dawn dawn) {
        Matcher match = pattern.matcher(line);
        if (match.matches()) {
            invoke(match.toMatchResult(), dawn);
            return true;
        }
        return false;
    }

    /**
     * Invokes the Command
     * @param res Parsed Arguments
     * @param dawn Dawn game to change game state
     */
    public abstract void invoke(MatchResult res, Dawn dawn);
}