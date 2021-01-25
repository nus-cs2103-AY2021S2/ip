import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    protected static ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String greet = "    ____________________________________________________________\n"
                + "     Hello! I'm Duke\n"
                + "     What can I do for you?\n"
                + "    ____________________________________________________________\n";
        System.out.println(greet);

        Scanner sc = new Scanner(System.in);
        try {
            File dir = new File("src/main/java/data");
            if(!dir.exists()) {
                if(dir.mkdir()) {
                    System.out.println("     Data directory created.");
                }
            }
            File file = new File("src/main/java/data/duke.txt");
            if(file.createNewFile()) {
                System.out.println("     Data file created.\n");
            } else {
                System.out.println("     There is an existing data file.\n");
            }
            Scanner readData = new Scanner(file);
            while(readData.hasNextLine()) {
                String[] currTask = readData.nextLine().split(" // ");
                String type = currTask[0];
                switch (type) {
                    case "T":
                        tasks.add(new Todo(Integer.parseInt(currTask[1]), currTask[2]));
                        break;
                    case "D":
                        tasks.add(new Deadline(Integer.parseInt(currTask[1]), currTask[2], currTask[3]));
                        break;
                    case "E":
                        tasks.add(new Event(Integer.parseInt(currTask[1]), currTask[2], currTask[3]));
                        break;
                }
            }
            run(sc.nextLine(), sc);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        sc.close();
    }

    private static void run(String input, Scanner sc) {
        System.out.println("    ____________________________________________________________");
        try {
            String[] inputArr = input.split(" ");
            Command cmd = Command.valueOf(inputArr[0].toUpperCase());
            switch (cmd) {
                case BYE:
                    if (inputArr.length == 1) {
                        System.out.println("     Bye. Hope to see you again soon!");
                    }
                    try {
                        FileWriter cleaner = new FileWriter("src/main/java/data/duke.txt", false);
                        cleaner.close();
                        FileWriter fw = new FileWriter("src/main/java/data/duke.txt", true);
                        for (Task curr : tasks) {
                            fw.write(curr.getFileString());
                            fw.write(System.lineSeparator());
                        }
                        fw.close();
                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;
                case LIST:
                    if (inputArr.length == 1) {
                        System.out.println("     Here are the tasks in your list:");
                        for (int i = 0; i < tasks.size(); i++) {
                            Task curr = tasks.get(i);
                            System.out.println("     " + (i + 1) + "." + curr.toString());
                        }
                    }
                    break;
                case DONE:
                case DELETE:
                    if (inputArr.length < 3) {
                        if (inputArr.length == 1) {
                            throw new DukeException("       OOPS!!! The task number cannot be empty.");
                        } else if (!isNumeric(inputArr[1])) {
                            throw new DukeException("       OOPS!!! The task number must be numeric.");
                        } else {
                            int i = Integer.parseInt(inputArr[1]) - 1;
                            if (i > (tasks.size() - 1) || i < 0) {
                                throw new DukeException("       OOPS!!! The task number is out of range.");
                            } else {
                                switch (cmd) {
                                    case DONE:
                                        tasks.get(i).markAsDone();
                                        Task curr = tasks.get(i);
                                        System.out.println("     Nice! I've marked this task as done:\n"
                                                + "       " + curr.toString());
                                        break;
                                    case DELETE:
                                        System.out.println("     Noted. I've removed this task:\n"
                                                + "       " + tasks.remove(i).toString() + "\n"
                                                + "     Now you have " + tasks.size() + " tasks in the list.");
                                        break;
                                }
                            }
                        }
                    }
                    break;
                case TODO:
                case EVENT:
                case DEADLINE:
                    switch (cmd) {
                        case TODO:
                            if (inputArr.length == 1) {
                                throw new DukeException("       OOPS!!! The description of a todo "
                                        + "cannot be empty.");
                            } else {
                                tasks.add(new Todo(0, input.substring(5)));
                            }
                            break;
                        case DEADLINE:
                            if (inputArr.length == 1) {
                                throw new DukeException("       OOPS!!! The description and due date "
                                        + "of a deadline cannot be empty.");
                            } else {
                                String[] details = input.substring(9).split(" /by ");
                                if (details.length == 1) {
                                    throw new DukeException("       OOPS!!! The due date of a deadline "
                                            + "cannot be empty.");
                                } else {
                                    tasks.add(new Deadline(0, details[0], details[1]));
                                }
                            }
                            break;
                        case EVENT:
                            if (inputArr.length == 1) {
                                throw new DukeException("       OOPS!!! The description and time frame "
                                        + "of an event cannot be empty.");
                            } else {
                                String[] details = input.substring(6).split(" /at ");
                                if (details.length == 1) {
                                    throw new DukeException("       OOPS!!! The time frame of an event "
                                            + "cannot be empty.");
                                } else {
                                    tasks.add(new Event(0, details[0], details[1]));
                                }
                            }
                            break;
                    }
                    System.out.println("     Got it. I've added this task:\n"
                            + "       " + tasks.get(tasks.size() - 1).toString() + "\n"
                            + "     Now you have " + tasks.size() + " tasks in the list.");
                    break;
                default:
                    throw new DukeException("       OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        } catch (DukeException ex) {
            System.out.println(ex.getMessage());
        } catch (IllegalArgumentException ex) {
            System.out.println("       OOPS!!! I'm sorry, but I don't know what that means :-(");
        } finally {
            System.out.println("    ____________________________________________________________\n");
            if (!input.equals("bye")) {
                run(sc.nextLine(), sc);
            }
        }
    }

    private static boolean isNumeric(String str) {
        if (str == null || str.length() == 0) {
            return false;
        }
        return str.chars().allMatch(Character::isDigit);
    }
}