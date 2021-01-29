package surrealchat.user;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Scanner;

import org.junit.jupiter.api.Test;

public class UserInputTest {
    /**
     * Tests whether a number in string form can be parsed into int.
     */
    @Test
    public void testGetInputNumber() {
        assertEquals(new UserInput(new Scanner(System.in)).getInputNumber("2103"),
                2103);
    }
}
