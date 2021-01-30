package prerthan.duke.IO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import prerthan.duke.Duke;
import prerthan.duke.command.AddCommand;
import prerthan.duke.command.ByeCommand;
import prerthan.duke.command.Command;
import prerthan.duke.command.DeleteCommand;
import prerthan.duke.command.DoneCommand;
import prerthan.duke.command.FindCommand;
import prerthan.duke.command.ListCommand;
import prerthan.duke.exception.DukeInvalidArgumentException;
import prerthan.duke.exception.DukeInvalidCommandException;

/**
 * A class to accept and parse user inputs from the input stream.
 */
public class Input {
    private static BufferedReader reader;
    private static final Pattern whitespace = Pattern.compile("\\s+");
    private static final Pattern slashCommands = Pattern.compile("(?i)\\/((from)|(to)|(at)|(by)|(on))");

    static String readline() {
        try {
            return reader.readLine();
        } catch (Exception e) {
            System.err.println("I/O exception occurred.");
            return "";
        }
    }

    /////////////////////////////////////////////////////////////////////////
    // Instance
    /////////////////////////////////////////////////////////////////////////

    protected String line;
    protected String[] tokens;

    public Input() {
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    /**
     * Gets the next line in the input stream, and stores it. Returns this object
     * for method chaining.
     * 
     * @return this {@link Input} object, with the cursor advanced by one line.
     */
    public Input nextLine() {
        this.line = readline();
        this.tokens = whitespace.split(this.line.trim());
        return this;
    }

    /**
     * Returns the current line of input as a {@link String} array of tokens, where
     * the {@link String} is trimmed and separated by any whitespace character.
     * 
     * @return the current line, tokenised.
     */
    public String[] getTokens() {
        this.tokens = this.tokens.length == 0 ? whitespace.split(this.line.trim()) : this.tokens;
        return this.tokens;
    }

    /**
     * Returns the current line of input as a {@link Stream} of {@link String}s,
     * trimmed and tokenised by any whitespace character.
     * 
     * @return the current line of input as a {@link Stream}<{@link String}>.
     */
    public Stream<String> getTokenStream() {
        return Stream.of(this.getTokens());
    }

    String getFirstToken() {
        return this.getTokens()[0];
    }

    public Command getCommand() throws DukeInvalidArgumentException, DukeInvalidCommandException {
        String commandString = this.getFirstToken();
        Command.CommandName cmd = Command.whichCommand(commandString);
        switch (cmd) {
        case TODO:
            return new AddCommand(this.getDetail(), cmd);
        case DEADLINE:
            return new AddCommand(this.getDetail(), this.getFirstTimeString(), cmd);
        case EVENT:
            return new AddCommand(this.getDetail(), this.getFirstTimeString(), this.getNextTimeString(), cmd);
        case LIST:
            return new ListCommand(this.tokensWithoutFirst());
        case DONE:
            return new DoneCommand(this.tokensWithoutFirst());
        case FIND:
            return new FindCommand(this.tokensWithoutFirst());
        case DELETE:
            return new DeleteCommand(this.tokensWithoutFirst());
        case EMPTY:
            Duke.output.say("Empty input; please enter something.");
            return null;
        case INVALID:
            throw new DukeInvalidCommandException(commandString, Input.class.getSimpleName());
        case BYE:
            return new ByeCommand(commandString);
        default:
            break;
        }
        return null;
    }

    public String[] tokensWithoutFirst() {
        return this.getTokenStream().skip(1).toArray(String[]::new);
    }

    public String inputWithoutFirstToken() {
        return this.getTokenStream().skip(1).collect(Collectors.joining(" "));
    }

    public String getDetail() {
        return this.getTokenStream().takeWhile(slashCommands.asMatchPredicate().negate())
                .collect(Collectors.joining(" "));
    }

    public String getFirstTimeString() {
        return this.getTokenStream().dropWhile(slashCommands.asMatchPredicate().negate()).skip(1)
                .takeWhile(slashCommands.asMatchPredicate().negate()).collect(Collectors.joining(" "));
    }

    public String getNextTimeString() {
        return this.getTokenStream().dropWhile(slashCommands.asMatchPredicate().negate()).skip(1)
                .dropWhile(slashCommands.asMatchPredicate().negate()).skip(1).collect(Collectors.joining(" "));
    }

    public void close() {
        try {
            reader.close();
        } catch (IOException e) {
            System.err.println("I/O Exception occurred.");
        }
    }
}