package mike;

import command.ListCommand;
import exception.MikeCommandExecutionException;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

public class MikeTest {
    @Test
    public void MikeInitTest() throws FileNotFoundException {
        File inputFile = new File("../../data/TodoList.txt");
        Scanner scanner = new Scanner(inputFile);
        String mikeResponse = "";
        String expectedResponse = "Here are the tasks in your list:\n";
        try {
            scanner = new Scanner(inputFile);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        while (scanner.hasNextLine()){
            expectedResponse += scanner.nextLine() + "\n";
        }


        Mike mike = new Mike();
        mike.mikeInit("../../data/TodoList.txt");
        try {
            mikeResponse = mike.getResponse(new ListCommand());
        } catch (MikeCommandExecutionException e) {
            e.printStackTrace();
        }
        assertEquals(expectedResponse, mikeResponse);
    }
}
