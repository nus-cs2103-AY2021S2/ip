import java.io.*;
import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Storage {
    public Storage() { // default Storage uses current directory as save/load location
    }

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

    public ArrayList<Task> load() throws DukeException {
        try {
            File file = new File("duke_saved_tasks");
            boolean isAlreadyPresent = !file.createNewFile();

            if (isAlreadyPresent) {
                FileReader fileReader = new FileReader(file);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                String currLine = bufferedReader.readLine();
                ArrayList<Task> ret = new ArrayList<>();

                while (currLine != null) {
                    String[] split = currLine.split(" \\| ");
                    String icon = split[0];
                    String isDone = split[1];
                    String description = split[2];

                    switch (icon) {
                    case "D":
                        Deadline deadline = new Deadline(description, LocalDateTime.parse(split[3]));
                        if (isDone.equals("T")) {
                            deadline.setDone();
                        }
                        ret.add(deadline);
                        break;

                    case "E":
                        Event event = new Event(description, LocalDateTime.parse(split[3]));
                        if (isDone.equals("T")) {
                            event.setDone();
                        }
                        ret.add(event);
                        break;

                    case "T":
                        ToDo toDo = new ToDo(description);
                        if (isDone.equals("T")) {
                            toDo.setDone();
                        }
                        ret.add(toDo);
                        break;
                    }

                    currLine = bufferedReader.readLine();
                }

                return ret;

            } else {
                throw new DukeException("No existing save file, creating new save now.\n");
            }

        } catch (IOException e) {
            throw new DukeException("Error loading file, creating new save now.\n");
        }

    }
}

