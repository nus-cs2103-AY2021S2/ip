package checklst.storage;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import exception.ChecklstException;

public class StorageTest {

    private final Storage storage = new Storage();

    @Test
    public void addCommandTest() {
        String testString = "Hello World";
        String[] testInput = testString.split(" ");
        this.storage.addCommand(testInput);
        assertEquals(testString, this.storage.getCommandHistory().get(0));
    }

    @Test
    public void removeLastCommandTest() {
        String testString = "Hello World";
        String[] testInput = testString.split(" ");
        this.storage.addCommand(testInput);
        this.storage.removeLastCommand();
        assertTrue(this.storage.getCommandHistory().size() == 0);
    }
    
}