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

    public String getResponse(String input) {

        try {
            ArrayList<Task> prevTasks = FileAccessor.readFromTasks(new ArrayList<Task>());
            TaskList.setList(prevTasks);
        } catch (FileNotFoundException | IllegalArgumentException e) {
            //System.out.println("EXCEPTION");
            try {
                Files.createDirectory(Paths.get(FileAccessor.getFolderPath()));
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
                    FileAccessor.writeToFile(TaskList.getList());
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
