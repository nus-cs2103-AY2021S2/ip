package duke.parser;

import duke.exceptions.DukeParseException;
import duke.parser.CommandParser;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


public class CommandParserTest {

    private static Stream<Arguments> provideGetArguments() {
        return Stream.of(
                Arguments.of("done 3 ", "3" ),
                Arguments.of("list", "" ),
                Arguments.of("deadline something /by this date \t" , "something /by this date" )
        );
    }
    @ParameterizedTest
    @MethodSource("provideGetArguments")
    public void getArguments_correctArguments_success(String input, String expected) {
        CommandParser p = new CommandParser(input);
        String actual = null;
        try {
            actual = p.getArguments();
        } catch (DukeParseException e) {
            System.out.println("Hello");
            fail();
        }
        assertEquals(actual , expected);
    }
}
