import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Duke {
    TaskList taskList;
    Parser parser;

    public static void main(String[] args) throws Exception{
        Scanner scan = new Scanner(System.in);
        Duke iceBear = new Duke();
        iceBear.taskList.addToDo("kek");
        iceBear.taskList.listTask();
        /* while (true) {
            Parser parser = new Parser();
            String nextCommand = scan.nextLine();
            if (nextCommand.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else {

            }
        } */
    }

    public static void printString(String[] input) {
        for (int i = 0; i < input.length; i++) {
            System.out.println(input[i]);
        }
    }

    public Duke() {
        this.taskList = new TaskList();
        this.parser = new Parser();
        System.out.println("Hello! I'm Icebear");
        System.out.println("What can I do for you?");
    }
}