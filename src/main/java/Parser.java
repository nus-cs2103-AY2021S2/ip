import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;

/**
 * Deals with user commands.
 */
public class Parser {
    protected static Scanner sc = new Scanner(System.in);

    /**
     * Checks user input and catches exceptions.
     *
     * @param storage Storage of data.
     * @param tasks TaskList of tasks.
     */
    public static void run(Storage storage, TaskList tasks) {
        while (true) {
            try {
                String userInput = sc.nextLine();

                // Save data when user says bye
                if (userInput.equals("bye")) {
                    storage.saveData(tasks);
                    Ui.sayBye();
                    break;
                }
                Parser.startResponse(userInput, tasks);
            } catch (IllegalArgumentException e) {
                System.out.println("\nOh no Flamingo! I need more information to do that.\n");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("\nOh no Flamingo! The task does not exist.\n");
            } catch (NullPointerException e) {
                System.out.println("\nOh no Flamingo! There is nothing in the list.\n");
            } catch (FileNotFoundException e) {
                System.out.println("\nOh no Flamingo! I cannot load the save data!\n");
            } catch (Exception e) {
                System.out.println("\nOh no Flamingo! I didn't understand that.\n");
            }
        }
    }

    /**
     * Makes sense of the user input.
     *
     * @param userInput User input.
     * @param tasks TaskList of tasks.
     * @throws Exception If user input is unknown.
     */
    public static void startResponse(String userInput, TaskList tasks) throws Exception {
        if (userInput.equals("list")) {
            if (TaskList.numTasks == 0) {
                throw new NullPointerException();
            }
            tasks.listTasks();
        } else if (userInput.contains("done")) {
            int taskNumber = Integer.parseInt(userInput.substring(5));
            if (taskNumber <= 0 || taskNumber > TaskList.numTasks) {
                throw new IndexOutOfBoundsException();
            }
            tasks.markAsDone(taskNumber);
        } else if (userInput.contains("todo")) {
            if (userInput.length() <= 5) {
                throw new IllegalArgumentException();
            }
            Task currentTask = new Todo(userInput.substring(5));
            tasks.addTask(currentTask);
        } else if (userInput.contains("deadline")) {
            if (userInput.length() <= 9 || !userInput.contains("/")) {
                throw new IllegalArgumentException();
            }
            int temp = userInput.indexOf('/') - 1;
            Task currentTask = new Deadline(userInput.substring(9, temp),
                    LocalDate.parse(userInput.substring(temp + 5)));
            tasks.addTask(currentTask);
        } else if (userInput.contains("event")) {
            if (userInput.length() <= 5 || !userInput.contains("/")) {
                throw new IllegalArgumentException();
            }
            int temp = userInput.indexOf('/') - 1;
            Task currentTask = new Event(userInput.substring(6, temp),
                    LocalDateTime.parse(userInput.substring(temp + 5)));
            tasks.addTask(currentTask);
        } else if (userInput.contains("delete")) {
            int taskNumber = Integer.parseInt(userInput.substring(7));
            if (taskNumber <= 0 || taskNumber > TaskList.numTasks) {
                throw new IndexOutOfBoundsException();
            }
            tasks.deleteTask(taskNumber);
        } else if (userInput.contains("find")) {
            if (userInput.length() <= 5) {
                throw new IllegalArgumentException();
            }
            String keyword = userInput.substring(5);
            tasks.findString(keyword);
        } else {
            throw new Exception();
        }
    }
}
