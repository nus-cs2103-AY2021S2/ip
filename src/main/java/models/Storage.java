package models;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Storage {
    private final String filePath;
    private final String directoryPath;

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
            Scanner sc = new Scanner(localFile);
            List<Optional<? extends Todo>> existingTodosList = new ArrayList<>();
            while (sc.hasNextLine()) {
                // get line, splitting by special character delimiter |
                List<String> line = Arrays.asList(sc.nextLine().split("\\|"));

                // line = [type, isDone, message, extraMessage (event / deadline)]
                String type = line.get(0);
                boolean isDone = line.get(1) == "1";
                String message = line.get(2);

                switch (type) {

                    case "T":
                        // create new todo
                        existingTodosList.add(Optional.ofNullable(new Todo(message, isDone)));
                        break;

                    case "D":
                        // create new deadline
                        existingTodosList.add(
                                Optional.ofNullable(new Deadline(message, isDone, line.get(3))));
                        break;

                    case "E":
                        // create new event
                        existingTodosList
                                .add(Optional.ofNullable(new Event(message, isDone, line.get(3))));
                        break;

                }
            }
            sc.close();
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
            if (databaseDirectory.exists()) {
                // delete existing file if it exists in directory
                File existingDatabase = new File(this.filePath);
                if (existingDatabase.exists()) {
                    // noinspection ResultOfMethodCallIgnored
                    existingDatabase.delete();
                }
            } else {
                // create directory if it doesn't exist
                // noinspection ResultOfMethodCallIgnored
                databaseDirectory.mkdir();
            }

            // Init to write file in append mode
            FileWriter writer = new FileWriter(new File(this.filePath), true);

            // loop through list and write to file
            todosList.forEach(optTodo -> {
                // lineToWrite will be written at the end
                String lineToWrite;

                // Check if Todo is an Event or Deadline
                if (optTodo.map(todo -> todo instanceof Event).orElse(false)) {
                    lineToWrite = optTodo.map(todo -> {
                        Event event = (Event) todo;
                        return String.format("E|%s|%s|%s", event.isTodoDone() ? "1" : "0",
                                event.getRawMessage(), event.getEventTime());
                    }).orElse("");
                } else if (optTodo.map(todo -> todo instanceof Deadline).orElse(false)) {
                    lineToWrite = optTodo.map(todo -> {
                        Deadline deadline = (Deadline) todo;
                        return String.format("D|%s|%s|%s", deadline.isTodoDone() ? "1" : "0",
                                deadline.getRawMessage(), deadline.getDeadline());
                    }).orElse("");
                } else {
                    lineToWrite = optTodo.map(todo -> String.format("T|%s|%s",
                            todo.isTodoDone() ? "1" : "0", todo.getRawMessage())).orElse("");
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
