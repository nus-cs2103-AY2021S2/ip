import javafx.application.Application;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Region;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

import javafx.stage.Stage;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class that runs the entire Duke application
 */
public class Duke {

    private static final String FOLDER_PATH = "./src/main/java/data/";
    private static final String FILE_NAME = "All Tasks.txt";


    public String getResponse(String input) {
        //Scanner scanner = new Scanner(System.in);
        //scanner.useDelimiter("\n");
        //only take in lines and not by whitespace, coz have one case where " " keeps the sc running to the next
        //String relPath = "./src/main/java/data/All Tasks.txt";//for runtest.sh put .. coz the path for that is diff
        // compared to this

        try {
            //File f = new File("./");
            //System.out.println(f.getAbsolutePath());//to get the path to see which path java is looking
            ArrayList<Task> prevTasks = FileAccessor.readFromTasks(FOLDER_PATH + FILE_NAME, new ArrayList<Task>());
            TaskList.setList(prevTasks);
        } catch (FileNotFoundException | IllegalArgumentException e) {
            //System.out.println("EXCEPTION");
            try {
                Files.createDirectory(Paths.get(FOLDER_PATH));
                TaskList.setList(new ArrayList<Task>());
            } catch (IOException e1) {
                System.out.println(Ui.lineGetter() + " Cannot create new directory\n" + Ui.lineGetter());
            }
            //shld just be ioexception, shldnt come to this catch block
            //File f = new File(relPath); //no need to create file here will get auto created when writing
        }

        String response = "";
        //response += Ui.introduce(); dont want to respond each time!

        input = Parser.trimWhiteSpaces(input);
        if (input.equals("bye")) {
            response += Ui.exit();
        } else if (input.length() == 0) {
            //if just enter spaces
            response += Ui.informOnlySpaces();
        } else if (input.equals("list")) {
            try {
                response += TaskList.printList();
            } catch (IllegalArgumentException e) {
                response += e.getMessage();
            }
        } else {
            try {
                String[] split = Parser.splitFirstAndRest(input);
                if (split[0].equals("done")) {
                    try {
                        int num = Parser.makeToInt(split[1]);
                        Task done = TaskList.doneTask(num - 1);
                        response += Ui.doneTask(done);
                    } catch (NumberFormatException | IndexOutOfBoundsException e) {
                        response += e.getMessage();
                    }
                } else if (split[0].equals("delete")) {
                    try {
                        int num = Parser.makeToInt(split[1]);
                        Task del = TaskList.deleteTask(num - 1);
                        response += Ui.deleteTask(del, TaskList.getListSize());
                    } catch (NumberFormatException | IndexOutOfBoundsException e) {
                        response += e.getMessage();
                    }
                } else if (split[0].equals("find")) {
                    try {
                        response += TaskList.findTask(split[1]);
                    } catch (IllegalArgumentException e) {
                        response += e.getMessage();
                    }
                } else if (split[0].equals("todo") || split[0].equals("deadline")
                        || split[0].equals("event")) {
                    Task task;
                    if (split[0].equals("todo")) {
                        task = new Todo(split[1]);
                    } else if (split[0].equals("deadline")) {
                        task = new Deadline(split[1]);
                    } else {
                        task = new Event(split[1]);
                    }
                    TaskList.addTask(task);
                    response += Ui.addTask(task, TaskList.getListSize());
                } else {
                    throw new IllegalArgumentException();
                }

                try {
                    FileAccessor.writeToTasks(FOLDER_PATH + FILE_NAME, TaskList.getList());
                } catch (IOException e) {
                    System.out.println(Ui.informUnableSave());
                }
            } catch (IllegalArgumentException e) {
                response += Ui.informIllegalArgExc();
            } catch (DateTimeParseException e) {
                response += Ui.informDateTimeParseExc();
            } catch (ArrayIndexOutOfBoundsException e) {
                response += e.getMessage();
            }
        }
        return response;
    }
}
