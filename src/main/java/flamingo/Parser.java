package flamingo;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Deals with user commands.
 */
public class Parser {
    protected static Storage storage = Flamingo.getStorage();
    protected static TaskList activeTasks = Flamingo.getTasks();
    protected static ArchivedTaskList archivedTasks = Flamingo.getArchivedTasks();

    /**
     * Checks user input and catches exceptions.
     *
     * @param userInput User input.
     */
    public static String run(String userInput) {
        try {
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
            // Save data when user says bye
            storage.saveData(activeTasks);
            storage.saveArchive(archivedTasks);
            return Ui.sayBye();
        } else if (userInput.equals("list")) {
            checkListValidity();
            return activeTasks.listTasks();
        } else if (userInput.contains("done")) {
            int taskNumber = getTaskNumberFrom(userInput);
            checkDoneValidity(taskNumber);
            return activeTasks.markAsDone(taskNumber);
        } else if (userInput.contains("todo")) {
            checkTodoValidity(userInput);
            String todoDescription = getTodoDescriptionFrom(userInput);
            Task currentTask = new Todo(todoDescription);
            return activeTasks.addTask(currentTask);
        } else if (userInput.contains("deadline")) {
            checkDeadlineValidity(userInput);
            Task currentTask = getDeadlineTaskFrom(userInput);
            return activeTasks.addTask(currentTask);
        } else if (userInput.contains("event")) {
            checkEventValidity(userInput);
            Task currentTask = getEventTaskFrom(userInput);
            return activeTasks.addTask(currentTask);
        } else if (userInput.contains("delete")) {
            int taskNumber = getDeleteTaskNumFrom(userInput);
            checkDeleteValidity(taskNumber);
            return activeTasks.deleteTask(taskNumber);
        } else if (userInput.contains("find")) {
            checkFindValidity(userInput);
            String keyword = getKeywordFrom(userInput);
            return activeTasks.findTask(keyword);
        } else if (userInput.equals("archive")) {
            // View list of archived tasks
            checkArchiveListValidity();
            return archivedTasks.listArchivedTasks();
        } else if (userInput.equals("archive all")) {
            return activeTasks.archiveAllTasks();
        } else if (userInput.contains("unarchive")) {
            int taskNumber = getUnarchiveTaskNumFrom(userInput);
            checkUnarchiveValidity(taskNumber);
            return archivedTasks.unarchiveTask(taskNumber);
        } else if (userInput.contains("archive")) {
            // To archive a task
            int taskNumber = getArchivedTaskNumFrom(userInput);
            checkArchiveValidity(taskNumber);
            return activeTasks.archiveTask(taskNumber);
        } else {
            throw new Exception();
        }
    }

    private static void checkArchiveValidity(int taskNumber) {
        if (taskNumber <= 0 || taskNumber > TaskList.numTasks) {
            throw new IndexOutOfBoundsException();
        }
    }

    private static void checkUnarchiveValidity(int taskNumber) {
        if (taskNumber <= 0 || taskNumber > ArchivedTaskList.numArchivedTasks) {
            throw new IndexOutOfBoundsException();
        }
    }

    private static void checkListValidity() {
        if (TaskList.numTasks == 0) {
            throw new NullPointerException();
        }
    }

    private static void checkArchiveListValidity() {
        if (ArchivedTaskList.numArchivedTasks == 0) {
            throw new NullPointerException();
        }
    }

    private static void checkDoneValidity(int taskNumber) {
        if (taskNumber <= 0 || taskNumber > TaskList.numTasks) {
            throw new IndexOutOfBoundsException();
        }
    }

    private static void checkTodoValidity(String userInput) {
        if (userInput.length() <= 5) {
            throw new IllegalArgumentException();
        }
    }

    private static void checkDeadlineValidity(String userInput) {
        if (userInput.length() <= 9 || !userInput.contains("/")) {
            throw new IllegalArgumentException();
        }
    }

    private static void checkEventValidity(String userInput) {
        if (userInput.length() <= 5 || !userInput.contains("/")) {
            throw new IllegalArgumentException();
        }
    }

    private static void checkDeleteValidity(int taskNumber) {
        if (taskNumber <= 0 || taskNumber > TaskList.numTasks) {
            throw new IndexOutOfBoundsException();
        }
    }

    private static void checkFindValidity(String userInput) {
        if (userInput.length() <= 5) {
            throw new IllegalArgumentException();
        }
    }

    private static int getTaskNumberFrom(String userInput) {
        return Integer.parseInt(userInput.substring(5));
    }

    private static int getDeleteTaskNumFrom(String userInput) {
        return Integer.parseInt(userInput.substring(7));
    }

    private static String getTodoDescriptionFrom(String userInput) {
        return userInput.substring(5);
    }

    private static String getKeywordFrom(String userInput) {
        return userInput.substring(5);
    }

    private static Task getDeadlineTaskFrom(String userInput) {
        int temp = userInput.indexOf('/') - 1;
        Task task = new Deadline(userInput.substring(9, temp),
                LocalDate.parse(userInput.substring(temp + 5)));
        return task;
    }

    private static Task getEventTaskFrom(String userInput) {
        int temp = userInput.indexOf('/') - 1;
        Task task = new Event(userInput.substring(6, temp),
                LocalDateTime.parse(userInput.substring(temp + 5)));
        return task;
    }

    private static int getArchivedTaskNumFrom(String userInput) {
        return Integer.parseInt(userInput.substring(8));
    }

    private static int getUnarchiveTaskNumFrom(String userInput) {
        return Integer.parseInt(userInput.substring(10));
    }
}
