package kobe;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class ParserTest {

    private final TaskList taskList = new TaskList();
    private final Ui ui = new Ui();
    private final Storage storage = new Storage("", taskList, ui);


    @BeforeEach
    void setUp() {
    }

    @Test
    public void readInput_incompleteDescription_exceptionThrow() {

        assertThrows(CustomExceptions.IncompleteDecriptionException.class, () -> {
            Parser.readInput("todo", taskList, storage, ui);
        });
    }

    @Test
    public void readInput_incorrectDescription_exceptionThrow() {
        assertThrows(CustomExceptions.IncorrectDecriptionException.class, () -> {
            Parser.readInput("deadlinedeadlindeadline", taskList, storage, ui);
        });
    }

//
//    @Test
//    public void readInput_normalInput() {
//        assertEquals(2, 2);
//    }

}