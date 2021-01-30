package sharadhr.duke.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import sharadhr.duke.command.Command;
import sharadhr.duke.command.DeleteCommand;
import sharadhr.duke.command.DoneCommand;
import sharadhr.duke.command.ListCommand;
import sharadhr.duke.exception.DukeInvalidArgumentException;

/**
 * A class to accept and parse user inputs from the input stream.
 */
public class Input
{
    private static BufferedReader reader;
    private static final Pattern whitespace = Pattern.compile("\\s+");
    private static final Pattern slashCommands = Pattern.compile("(?i)\\/((at)|(by)|(on))");
    
    static String readline()
    {
        try
        {
            return reader.readLine();
        }
        catch (Exception e)
        {
            System.err.println("I/O exception occurred.");
            return "";
        }
    }
    
    /////////////////////////////////////////////////////////////////////////
    // Instance
    /////////////////////////////////////////////////////////////////////////
    
    protected String line;
    protected String[] tokens;
    
    public Input()
    {
        reader = new BufferedReader(new InputStreamReader(System.in));
    }
    
    /**
     * Gets the next line in the input stream, and stores it. Returns this object
     * for method chaining.
     * 
     * @return this {@link Input} object, with the cursor advanced by one line.
     */
    public Input nextLine()
    {
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
    public String[] getTokens()
    {
        this.tokens = this.tokens.length == 0 ? whitespace.split(this.line.trim()) : this.tokens;
        return this.tokens;
    }
    
    /**
     * Returns the current line of input as a {@link Stream} of {@link String}s,
     * trimmed and tokenised by any whitespace character.
     * 
     * @return the current line of input as a {@link Stream}<{@link String}>.
     */
    public Stream<String> getTokenStream()
    {
        return Stream.of(this.getTokens());
    }
    
    String getFirstToken()
    {
        return this.getTokens()[0];
    }
    
    public Command getCommand() throws DukeInvalidArgumentException
    {
        switch (Command.whichCommand(this.getFirstToken()))
        {
            case TODO:
                return null;
            case DEADLINE:
                // try
                // {
                //     tasks.addTask(new Deadline(reader.getFirstToken(), reader.getTime()));
                // }
                // catch (DukeException e)
                // {
                //     System.err.println(e);
                //     continue;
                // }
                break;
            case EVENT:
                // try
                // {
                //     tasks.addTask(new Event(reader.getDetail(), reader.getTime()));
                // }
                // catch (DukeException e)
                // {
                //     System.err.println(e);
                //     continue;
                // }
                break;
            case LIST:
                return new ListCommand(this.tokensWithoutFirst());
            case DONE:
                return new DoneCommand(this.tokensWithoutFirst());
            case DELETE:
                return new DeleteCommand(this.tokensWithoutFirst());
            case EMPTY:
                // writer.say("Empty input; please enter something.");
                return null;
            case INVALID:
                // try
                // {
                //     throw new DukeInvalidCommandException(command, Duke.class.getName());
                // }
                // catch (DukeException e)
                // {
                //     writer.say("Invalid command; try again.");
                // }
                // continue;
            case BYE:
                break;
            default:
                break;
            
        }
        return null;
    }
    
    public String[] tokensWithoutFirst()
    {
        return this.getTokenStream().skip(1).toArray(String[]::new);
    }
    
    public String inputWithoutFirstToken()
    {
        return this.getTokenStream().skip(1).collect(Collectors.joining(""));
    }
    
    public String getDetail()
    {
        return Stream.of(this.tokens).skip(1).takeWhile(slashCommands.asMatchPredicate().negate())
                .collect(Collectors.joining(" "));
    }
    
    public String getTime()
    {
        return Stream.of(this.tokens).dropWhile(slashCommands.asMatchPredicate().negate()).skip(1)
                .collect(Collectors.joining(" "));
    }
    
    public void close()
    {
        try
        {
            reader.close();
        }
        catch (IOException e)
        {
            
            System.err.println("I/O Exception occurred.");
        }
    }
}