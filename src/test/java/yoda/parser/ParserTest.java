package yoda.parser;

import org.junit.jupiter.api.Test;
import yoda.command.*;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ParserTest {
    @Test
    void parseTest() {
        Command addCommand = Parser.parse("todo read a book");
        Command listCommand = Parser.parse("list -t");
        Command editCommand = Parser.parse("delete 0");
        Command helpCommand = Parser.parse("help");
        Command errorCommand = Parser.parse("invalidCommand");
        assertAll("Checking if correct type of command is issued",
                () -> assertTrue(addCommand instanceof AddCommand),
                () -> assertTrue(listCommand instanceof ListCommand),
                () -> assertTrue(editCommand instanceof EditCommand),
                () -> assertTrue(helpCommand instanceof HelpCommand),
                () -> assertTrue(errorCommand instanceof HelpCommand)
        );
    }
}