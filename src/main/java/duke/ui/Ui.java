package duke.ui;

import duke.task.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private final Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public void printDukeLogo() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
    }

    public void printPartition() {
        System.out.println("\n/**********************************************************/\n");
    }

    public void printIntroduction() {
        printDukeLogo();
        System.out.println("Hello! I'm Duke.\n" + "What can I do for you?");
        printPartition();
    }

    public void printExitMessage() {
        System.out.println("Bye. Hope to see you again soon!\n");
        printDukeLogo();
    }

    public void printStorageIoError() {
        System.out.println("OOPS! An error occurred while attempting to create/retrieve storage file.");
        printPartition();
    }

    public void printUpdateIoError(IOException ie) {
        System.out.println("OOPS! An error occurred while attempting to update storage file:\n"
                + ie.getMessage());
        printPartition();
    }

    public void printDukeException(DukeException de) {
        System.out.println("OOPS! " + de.getMessage());
        printPartition();
    }

    public void printTasks(TaskList tasks) {
        ArrayList<Task> taskList = tasks.getTasks();
        for (int i = 1; i <= taskList.size(); i++) {
            System.out.println("" + i + ". " + taskList.get(i - 1).getStatus());
        }
        printPartition();
    }

    public void printNumTasks(TaskList taskList) {
        System.out.println("Now you have " + taskList.getTasks().size() + " tasks in the list.");
        printPartition();
    }

    public void printAddTaskMessage(Task added, TaskList taskList) {
        System.out.println("Got it. I've added this task:\n" + added.getStatus());
        printNumTasks(taskList);
    }

    public void printMarkDone(Task marked) {
        System.out.println("This task is marked as done:\n" + marked.getStatus());
        printPartition();
    }

    public void printDeleteTask(Task deleted, TaskList taskList) {
        System.out.println("This task has been removed:\n" + deleted.getStatus());
        printNumTasks(taskList);
    }

    public String readCommand() {
        return this.sc.nextLine();
    }

    public void closeScanner() {
        this.sc.close();
    }
}
