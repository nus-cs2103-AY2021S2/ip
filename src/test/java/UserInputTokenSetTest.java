import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import duke.parser.UserInputTokenSet;


/**
 * Test wrapping of UserInputTokenSet dictionary.
 *
 * Relatively trivial.
 */
public class UserInputTokenSetTest {

    private UserInputTokenSet tokenSet;

    @BeforeEach
    void init() {
        tokenSet = new UserInputTokenSet();
    }

    @Test
    void set() {
        tokenSet.set("text", "foo");
        tokenSet.set("randomStringOfCharacters", "bar");
    }

    @Test
    void set_get() {
        tokenSet.set("text", "hello");
        assertEquals(tokenSet.get("text"), "hello");
        assertEquals(tokenSet.get(""), "");
        assertEquals(tokenSet.get("textDoesNotExist"), "");
    }

    @Test
    void set_contains() {
        tokenSet.set("text", "hello");
        assertTrue(tokenSet.contains("text"));
        assertFalse(tokenSet.contains(""));
        assertFalse(tokenSet.contains("textDoesNotExist"));
    }
}
