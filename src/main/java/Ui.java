import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Ui {

    /**
     * Prints a welcome message on the console when Duke is first opened.
     */
    public static String printHello() {
        return "Hello, this is " + "Duke!" + " What can I do for you today? " +
                "\nFor the list of commands, type \"help\"";
    }

    /**
     * Prints a goodbye message on the console when Duke is closed after detecting
     * the exit command.
     */
    public static String sayGoodbye() {
        return "Bye, see you soon! Don't miss me too much.";
    }

    /**
     * Provides commands available to user.
     * @return list of all usable commands
     */
    public static String getHelp() {
        String introduction = "Hey! Here are the commands you can give me:\n";
        String addTodo = "To add a todo, write \"todo\" followed by a task description.\n";
        String addTask = "To add a task with deadline, write \"task\" " +
                "followed by task description, then \"by\" YYYY-MM-DD.\n";
        String addEvent = "To add an event, write \"event\" " +
                "followed by event description, then \"at\" YYYY-MM-DD.\n";
        String showList = "Use \"list\" to see all your tasks.\n";
        String markDone = "Use \"done\" and the task's index to mark your task as complete.\n";
        String deleteItem = "Use \"delete\" and the task's index to delete your task.\n";
        String bye = "Use \"bye\" to close me!";
        return introduction + addTodo + addTask + addEvent + showList + markDone + deleteItem + bye;
    }

    /**
     * Prints a horizontal line to format text.
     */
    public static void formatText() {
        System.out.println("******************************************************");
    }
}
