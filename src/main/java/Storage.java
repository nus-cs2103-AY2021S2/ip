import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Storage {
    public Storage() { // default Storage uses current directory as save/load location
    }

    /**
     * Saves the tasks in taskList into save file "duke_saved_tasks" in the same directory.
     *
     * @param taskList TaskList containing the tasks to be saved.
     */
    public void save(TaskList taskList) {
        try {
            FileWriter fileWriter = new FileWriter("duke_saved_tasks");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            ArrayList<Task> tasks = taskList.getTasks();
            for (Task t : tasks) {
                bufferedWriter.write(t.toLog() + "\n");
            }

            bufferedWriter.flush();

        } catch (IOException e) {
            System.out.println(e);
        }
    }

    /**
     * Builds and return an ArrayList of Tasks from the saved tasks in the save file "duke_saved_tasks" in the same
     * directory.
     *
     * @return ArrayList of Tasks from the saved tasks in the save file "duke_saved_tasks"
     * @throws DukeException Thrown exception when an error occurs during loading, according to the reason specified in
     * error body.
     */
    public ArrayList<Task> load() throws DukeException {
        try {
            File file = new File("duke_saved_tasks");
            boolean fileIsAlreadyPresent = !file.createNewFile();

            if (fileIsAlreadyPresent) {
                FileReader fileReader = new FileReader(file);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                String currLine = bufferedReader.readLine();
                ArrayList<Task> loadedTasks = new ArrayList<>();

                while (currLine != null) {
                    String[] split = currLine.split(" \\| ");
                    String icon = split[0];
                    String isDone = split[1];
                    String description = split[2];

                    switch (icon) {
                    case "D":
                        Deadline deadline = new Deadline(description, LocalDateTime.parse(split[3]));
                        setDoneStatus(deadline, isDone);
                        loadedTasks.add(deadline);
                        break;

                    case "E":
                        Event event = new Event(description, LocalDateTime.parse(split[3]));
                        setDoneStatus(event, isDone);
                        loadedTasks.add(event);
                        break;

                    case "T":
                        ToDo toDo = new ToDo(description);
                        setDoneStatus(toDo, isDone);
                        loadedTasks.add(toDo);
                        break;

                    default:
                        throw new DukeException("Wrong save format, unable to load.");
                    }

                    currLine = bufferedReader.readLine();
                }

                return loadedTasks;

            } else {
                throw new DukeException("No existing save file, creating new save now.\n");
            }

        } catch (IOException e) {
            throw new DukeException("Error loading file, creating new save now.\n");
        }

    }

    private void setDoneStatus(Task task, String isDone) {
        if (isDone.equals("T")) {
            task.setDone();
        }
    }
}

