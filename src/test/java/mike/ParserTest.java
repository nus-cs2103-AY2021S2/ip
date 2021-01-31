package mike;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

import command.Command;
import command.DeadlineCommand;
import command.EventCommand;
import command.ListCommand;
import command.TodoCommand;
import exception.MikeInvalidInputException;

public class ParserTest {
    @Test
    public void parserCommandTest() {
        Scanner scanner = null;
        Command actualCommand = null;
        try {
            scanner = new Scanner(new File(System.getProperty("user.dir") + "/src/test/test.txt"));
            actualCommand = Parser.parseInput(scanner);
            if (!(actualCommand instanceof ListCommand)) {
                throw new MikeInvalidInputException("failed");
            }
            actualCommand = Parser.parseInput(scanner);
            if (!(actualCommand instanceof TodoCommand)) {
                throw new MikeInvalidInputException("failed");
            }
            actualCommand = Parser.parseInput(scanner);
            if (!(actualCommand instanceof EventCommand)) {
                throw new MikeInvalidInputException("failed");
            }
            actualCommand = Parser.parseInput(scanner);
            if (!(actualCommand instanceof DeadlineCommand)) {
                throw new MikeInvalidInputException("failed");
            }
        } catch (MikeInvalidInputException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
