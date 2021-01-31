package mike;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

import command.ListCommand;
import exception.MikeCommandExecutionException;

public class MikeTest {
    @Test
    public void mikeInitTest() {
        File inputFile = new File(System.getProperty("user.dir") + "/data/TodoList.txt");
        Scanner scanner = null;
        try {
            scanner = new Scanner(inputFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String mikeResponse = "";
        String expectedResponse = "Here are the tasks in your list:\n";
        try {
            scanner = new Scanner(inputFile);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        while (scanner.hasNextLine()) {
            expectedResponse += scanner.nextLine() + "\n";
        }


        Mike mike = new Mike();
        mike.mikeInit();

        try {
            mikeResponse = mike.getResponse(new ListCommand());
        } catch (MikeCommandExecutionException e) {
            e.printStackTrace();
        }
        assertEquals(expectedResponse, mikeResponse);
    }
}
