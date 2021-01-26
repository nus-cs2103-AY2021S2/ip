package com.tjtanjin.ip;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class UiTest {

    //reused from https://www.baeldung.com/java-testing-system-out-println
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

    @Test
    void showWelcome_whenInvokePrintln_thenOutputCaptorSuccess() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String expected = "Hello from\n" + logo + "\nWhat can I do for you?";
        Ui.showWelcome();
        assertEquals(expected, outputStreamCaptor.toString().trim());
    }

    @Test
    void showDivider_whenInvokePrintln_thenOutputCaptorSuccess() {
        String expected = "------------------------------------";
        Ui.showDivider();
        assertEquals(expected, outputStreamCaptor.toString().trim());
    }

    @Test
    void showInfo_whenInvokePrintln_thenOutputCaptorSuccess() {
        String expected = "Test print information message!";
        Ui.showInfo(expected);
        assertEquals(expected, outputStreamCaptor.toString().trim());
    }

    @Test
    void showError_whenInvokePrintln_thenOutputCaptorSuccess() {
        String errMsg = "Test print error message!";
        String expected = "Error: " + "Test print error message!";
        Ui.showError(errMsg);
        assertEquals(expected, outputStreamCaptor.toString().trim());
    }
}
