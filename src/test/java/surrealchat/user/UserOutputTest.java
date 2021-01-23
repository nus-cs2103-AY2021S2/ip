package surrealchat.user;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserOutputTest {
    @Test
    public void testSpellTaskType(){
        assertEquals(new UserOutput(false).spellTaskType("T"), "todo");
        assertEquals(new UserOutput(false).spellTaskType("D"), "deadline");
        assertEquals(new UserOutput(false).spellTaskType("E"), "event");
    }
}
