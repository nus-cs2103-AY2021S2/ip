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
    private final PrintStream STANDARD_OUT = System.out;
    private final ByteArrayOutputStream OUTPUT_STREAM_CAPTOR = new ByteArrayOutputStream();
    private final UiHandler UI_HANDLER = new UiHandler();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(OUTPUT_STREAM_CAPTOR));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(STANDARD_OUT);
    }

    @Test
    void showWelcome_whenInvokePrintln_thenOutputCaptorSuccess() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String expected = "Hello from\n" + logo + "\nWhat can I do for you?";
        UI_HANDLER.showWelcome();
        assertEquals(expected, OUTPUT_STREAM_CAPTOR.toString().trim());
    }

    @Test
    void showInfo_whenInvokePrintln_thenOutputCaptorSuccess() {
        String expected = "Test print information message!";
        UI_HANDLER.showResponse(expected);
        assertEquals(expected, OUTPUT_STREAM_CAPTOR.toString().trim());
    }

    @Test
    void showError_whenInvokePrintln_thenOutputCaptorSuccess() {
        String errMsg = "Test print error message!";
        String expected = "Error: " + "Test print error message!";
        UI_HANDLER.showResponse(errMsg);
        assertEquals(expected, OUTPUT_STREAM_CAPTOR.toString().trim());
    }
}
