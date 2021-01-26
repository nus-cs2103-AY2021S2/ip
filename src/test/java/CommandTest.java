import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class CommandTest  {
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