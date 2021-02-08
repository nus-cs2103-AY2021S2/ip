package duke.models;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Storage {
    /** Constant containing index of type character for any Todo */
    private static final int TODO_TYPE_INDEX = 0;
    /** Constant containing index of is done integer for any Todo */
    private static final int TODO_IS_DONE_INDEX = 1;
    /** Constant containing index of message String for any Todo */
    private static final int TODO_MESSAGE_INDEX = 2;
    /** Constant containing index of extra message for Deadline and Event */
    private static final int TODO_EXTRA_MESSAGE_INDEX = 3;
    /** Constant defining false for isDone for database file */
    private static final String DATABASE_IS_DONE_FALSE = "0";
    /** Constant defining true for isDone for database file */
    private static final String DATABASE_IS_DONE_TRUE = "1";

    /** filePath containing saved Todos */
    private final String filePath;
    /** directory of file containing saved Todos */
    private final String directoryPath;

    /**
     * Creates a new instance of Storage which is responsible for storing and retrieving
     * tasks saved on a user's PC
     * @param filePath file path of the file containing the tasks
     * @param directoryPath directory path of the file containing the tasks
     */
    public Storage(String filePath, String directoryPath) {
        this.filePath = filePath;
        this.directoryPath = directoryPath;
    }

    /**
     * Attempt to retrieve a local save of the user's tasks on their pc as a list, if not found,
     * return the an empty list
     *
     * @return List containing either existing Todos based on data file or an empty list
     */
    public List<Optional<? extends Todo>> retrieveLocalDatabase() {
        try {
            // get local file
            File localFile = new File(this.filePath);
            // start scanner for file
            Scanner sc = new Scanner(localFile);
            // init empty array
            List<Optional<? extends Todo>> existingTodosList = new ArrayList<>();
            // scan through inputs in file
            while (sc.hasNextLine()) {
                // get line, splitting by special character delimiter |
                List<String> line = Arrays.asList(sc.nextLine().split("\\|"));

                // line = [type, isDone, message, extraMessage (event / deadline)]
                String type = line.get(TODO_TYPE_INDEX);
                // isDone would be "1" if done, "0" if not done
                boolean isDone = line.get(TODO_IS_DONE_INDEX).equals(DATABASE_IS_DONE_TRUE);
                String message = line.get(TODO_MESSAGE_INDEX);

                // @formatter:off
                switch (type) {
                case "T":
                    // create new todo
                    existingTodosList.add(Optional.of(new Todo(message, isDone)));
                    break;
                case "D":
                    // create new deadline
                    existingTodosList.add(Optional.of(
                                new Deadline(message,
                                            isDone,
                                            line.get(TODO_EXTRA_MESSAGE_INDEX))));
                    break;
                case "E":
                    // create new event
                    existingTodosList.add(Optional.of(
                                new Event(message,
                                        isDone,
                                        line.get(TODO_EXTRA_MESSAGE_INDEX))));
                    break;
                default:
                    // should not reach default case
                    break;
                }
            }
            // close scanner
            sc.close();
            // return arraylist of todos
            return existingTodosList;
        } catch (Exception e) {
            // exception will be caught if no existing data file is found
            // e.printStackTrace();
            return new ArrayList<>();
        }
    }

    /**
     * Saves all tasks from the todosList into the local database
     *
     * @param todosList List of todos passed to be saved into local database
     */
    public void saveTasksToLocalDatabase(List<Optional<? extends Todo>> todosList) {
        try {
            // check if directory exists, if not, create it
            // else, delete file's current contents
            File databaseDirectory = new File(this.directoryPath);
            File existingDatabase = new File(this.filePath);
            if (databaseDirectory.exists()) {
                // delete existing file if it exists in directory
                if (existingDatabase.exists()) {
                    // noinspection ResultOfMethodCallIgnored
                    existingDatabase.delete();
                }
            } else {
                // create directory if it doesn't exist
                // noinspection ResultOfMethodCallIgnored
                databaseDirectory.mkdir();
            }

            // at this point, databaseDirectory should exist
            assert !databaseDirectory.exists()
                    : "Database directory should be created but does not exist";
            // and existingDatabase should be deleted before writing a new one
            assert !existingDatabase.exists(): "Database file should be be deleted but exists";

            // Init to write file in append mode
            FileWriter writer = new FileWriter(this.filePath, true);

            // loop through list and write to file
            todosList.forEach(optTodo -> {
                // lineToWrite will be written at the end
                String lineToWrite;

                // Check if Todo is an Event or Deadline
                if (optTodo.map(todo -> todo instanceof Event).orElse(false)) {
                    lineToWrite = optTodo.map(todo -> {
                        Event event = (Event) todo;
                        return String.format("E|%s|%s|%s", event.isTodoDone()
                                        ? DATABASE_IS_DONE_TRUE
                                        : DATABASE_IS_DONE_FALSE,
                                event.getRawMessage(), event.getEventTime());
                    }).orElse("");
                } else if (optTodo.map(todo -> todo instanceof Deadline).orElse(false)) {
                    lineToWrite = optTodo.map(todo -> {
                        Deadline deadline = (Deadline) todo;
                        return String.format("D|%s|%s|%s", deadline.isTodoDone()
                                        ? DATABASE_IS_DONE_TRUE
                                        : DATABASE_IS_DONE_FALSE,
                                deadline.getRawMessage(), deadline.getDeadline());
                    }).orElse("");
                } else {
                    lineToWrite = optTodo.map(todo -> String.format("T|%s|%s",
                            todo.isTodoDone() ? DATABASE_IS_DONE_TRUE : DATABASE_IS_DONE_FALSE,
                            todo.getRawMessage())).orElse("");
                }

                // Write todo to line in database
                try {
                    writer.write(String.format("%s\n", lineToWrite));
                } catch (IOException e) {
                    e.printStackTrace();
                }

            });

            // close writer on complete
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
