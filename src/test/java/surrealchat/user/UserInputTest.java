package surrealchat.user;

import java.util.Scanner;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserInputTest {
    @Test
    public void testGetInputNumber() {
        assertEquals(new UserInput(new Scanner(System.in)).getInputNumber("2103"),
                2103);
    }
}
