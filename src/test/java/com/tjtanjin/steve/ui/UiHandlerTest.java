package com.tjtanjin.steve.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javafx.scene.layout.AnchorPane;

public class UiHandlerTest extends AnchorPane {

    //reused from https://www.baeldung.com/java-testing-system-out-println
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private final UiHandler uiHandler = new UiHandler();

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
        uiHandler.showWelcome();
        assertEquals(expected, outputStreamCaptor.toString().trim());
    }

    @Test
    void showInfo_whenInvokePrintln_thenOutputCaptorSuccess() {
        String expected = "Test print information message!";
        uiHandler.showInfo(expected);
        assertEquals(expected, outputStreamCaptor.toString().trim());
    }

    @Test
    void showError_whenInvokePrintln_thenOutputCaptorSuccess() {
        String errMsg = "Test print error message!";
        String expected = "Error: " + "Test print error message!";
        uiHandler.showError(errMsg);
        assertEquals(expected, outputStreamCaptor.toString().trim());
    }
}
