import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class Duke {
    public static ArrayList<Task> ls = new ArrayList<Task>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Duke: Hello I'm Duke, what can I do for you?");
        System.out.println("-----------------------------------------------------");
        System.out.println("Enter an input ('bye' to quit): ");
        String userInput = sc.nextLine();
        System.out.println("User Input: " + userInput);
        while (!"bye".equals(userInput)) {
            if ("list".equals(userInput)) {
                Duke.printList();
            } else if (userInput.startsWith("done ")) {
                try {
                    Duke.setAsDone(userInput);
                } catch (DukeException ex) {
                    System.out.println(ex);
                }
            } else if (userInput.startsWith("delete ")) {
                try {
                    Duke.deleteFromList(userInput);
                } catch (DukeException ex) {
                    System.out.println(ex);
                }
            } else {
                try {
                    Duke.addTask(userInput);
                } catch (DukeException ex) {
                    System.out.println(ex);
                }
            }
            System.out.println("Enter an input ('bye' to quit): ");
            userInput = sc.nextLine();
            System.out.println("User Input: " + userInput);
        }
        System.out.println("Duke: Bye, hope to see you again! :)");
        System.out.println("-----------------------------------------------------");
    }

    public static void printList() {
        System.out.println("Here are the tasks in your list:");
        for (int i=1; i<=Duke.ls.size(); i++) {
            System.out.println("  " + i + ". " + Duke.ls.get(i-1));
        }
        System.out.println("-----------------------------------------------------");
    }

    public static void setAsDone(String userInput) throws DukeException {
        try {
            int indexToMark = Integer.parseInt(userInput.substring(5));
            Task ts = Duke.ls.get(indexToMark - 1);
            ts.markAsDone();
            System.out.println("Nice! I've marked this task as done: ");
            System.out.println("  " + ts);
            System.out.println("-----------------------------------------------------");
        } catch (NumberFormatException | IndexOutOfBoundsException ex) {
            throw new ArgumentException(4);
        }
    }

    public static void deleteFromList(String userInput) throws DukeException {
        try {
            int indexToDelete = Integer.parseInt(userInput.substring(7));
            Task ts = Duke.ls.get(indexToDelete - 1);
            Duke.ls.remove(indexToDelete - 1);
            System.out.println("Okay! I've removed this task: ");
            System.out.println("  " + ts);
            System.out.println("Now you have " + Duke.ls.size() + " tasks in the list.");
            System.out.println("-----------------------------------------------------");
        } catch (NumberFormatException | IndexOutOfBoundsException ex) {
            throw new ArgumentException(4);
        }
    }

    public static void addTask(String userInput) throws DukeException {
        if (userInput.startsWith("todo ")) {
            String[] splits = userInput.split("todo ");
            if (splits.length == 2) {
                Todo addedTask = new Todo(Arrays.asList(splits).get(1));
                Duke.ls.add(addedTask);
                System.out.println("Got it, I've added this task to the list: ");
                System.out.println("  " + addedTask);
            } else {
                throw new ArgumentException(1);
            }
        } else if (userInput.startsWith("deadline ")) {
            String[] splits = userInput.split("deadline |/by ");
            System.out.println(Arrays.asList(splits));
            if ((splits.length == 3) && !(splits[1].equals("")) && !(splits[2].equals(""))) {
                Deadline addedTask = new Deadline(Arrays.asList(splits).get(1), Arrays.asList(splits).get(2));
                Duke.ls.add(addedTask);
                System.out.println("Got it, I've added this task to the list: ");
                System.out.println("  " + addedTask);
            } else {
                throw new ArgumentException(2);
            }
        } else if (userInput.startsWith("event ")) {
            String[] splits = userInput.split("event |/at ");
            if ((splits.length == 3) && !(splits[1].equals("")) && !(splits[2].equals(""))) {
                Event addedTask = new Event(Arrays.asList(splits).get(1), Arrays.asList(splits).get(2));
                Duke.ls.add(addedTask);
                System.out.println("Got it, I've added this task to the list: ");
                System.out.println("  " + addedTask);
            } else {
                throw new ArgumentException(3);
            }
        } else {
            throw new KeywordException();
        }
        System.out.println("Now you have " + Duke.ls.size() + " tasks in the list.");
        System.out.println("-----------------------------------------------------");
    }

}
