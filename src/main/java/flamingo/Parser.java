package flamingo;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Deals with user commands.
 */
public class Parser {
    protected static Storage storage = Flamingo.getStorage();
    protected static TaskList tasks = Flamingo.getTasks();

    /**
     * Checks user input and catches exceptions.
     *
     * @param userInput User input.
     */
    public static String run(String userInput) {
        try {
            // Save data when user says bye
            if (userInput.equals("bye")) {
                storage.saveData(tasks);
            }
            return Parser.startResponse(userInput);
        } catch (IllegalArgumentException e) {
            return "Oh no Flamingo! I need more information to do that.";
        } catch (IndexOutOfBoundsException e) {
            return "Oh no Flamingo! The task does not exist.";
        } catch (NullPointerException e) {
            return "Oh no Flamingo! There is nothing in the list.";
        } catch (FileNotFoundException e) {
            return "Oh no Flamingo! I cannot load the save data!";
        } catch (Exception e) {
            return "Oh no Flamingo! I didn't understand that.";
        }
    }

    /**
     * Makes sense of the user input.
     * Calls the correct function depending on the user input.
     *
     * @param userInput User input.
     * @throws Exception If user input is unknown.
     */
    public static String startResponse(String userInput) throws Exception {
        if (userInput.equals("bye")) {
            return Ui.sayBye();
        } else if (userInput.equals("list")) {
            if (TaskList.numTasks == 0) {
                throw new NullPointerException();
            }
            return tasks.listTasks();
        } else if (userInput.contains("done")) {
            int taskNumber = Integer.parseInt(userInput.substring(5));
            if (taskNumber <= 0 || taskNumber > TaskList.numTasks) {
                throw new IndexOutOfBoundsException();
            }
            return tasks.markAsDone(taskNumber);
        } else if (userInput.contains("todo")) {
            if (userInput.length() <= 5) {
                throw new IllegalArgumentException();
            }
            Task currentTask = new Todo(userInput.substring(5));
            return tasks.addTask(currentTask);
        } else if (userInput.contains("deadline")) {
            if (userInput.length() <= 9 || !userInput.contains("/")) {
                throw new IllegalArgumentException();
            }
            int temp = userInput.indexOf('/') - 1;
            Task currentTask = new Deadline(userInput.substring(9, temp),
                    LocalDate.parse(userInput.substring(temp + 5)));
            return tasks.addTask(currentTask);
        } else if (userInput.contains("event")) {
            if (userInput.length() <= 5 || !userInput.contains("/")) {
                throw new IllegalArgumentException();
            }
            int temp = userInput.indexOf('/') - 1;
            Task currentTask = new Event(userInput.substring(6, temp),
                    LocalDateTime.parse(userInput.substring(temp + 5)));
            return tasks.addTask(currentTask);
        } else if (userInput.contains("delete")) {
            int taskNumber = Integer.parseInt(userInput.substring(7));
            if (taskNumber <= 0 || taskNumber > TaskList.numTasks) {
                throw new IndexOutOfBoundsException();
            }
            return tasks.deleteTask(taskNumber);
        } else if (userInput.contains("find")) {
            if (userInput.length() <= 5) {
                throw new IllegalArgumentException();
            }
            String keyword = userInput.substring(5);
            return tasks.findTask(keyword);
        } else {
            throw new Exception();
        }
    }
}
