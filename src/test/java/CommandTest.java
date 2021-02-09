import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class CommandTest {
    @Test
    public void createCommandNoArgs_incompleteInstruction_exceptionThrown() {
        assertThrows(EmptyDescriptionException.class, () -> {
            new Command("todo");
        });
    }

    @Test
    public void createCommand_unknownInstruction_exceptionThrown() {
        assertThrows(UnknownCommandException.class, () -> {
            new Command("hello", "args");
        });
    }
}
