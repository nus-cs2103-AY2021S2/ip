package mike;

import command.*;
import exception.MikeInvalidInputException;
import org.junit.Test;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.Scanner;
import static org.junit.Assert.assertEquals;

public class ParserTest {
    @Test
    public void ParserTest() {
        Scanner scanner = null;
        Command actualCommand = null;
        try {
            scanner = new Scanner(new File(System.getProperty("user.dir") + "/test.txt"));
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
