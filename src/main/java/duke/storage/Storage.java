package duke.storage;

import duke.exception.DukeException;
import duke.task.Task;

import java.util.List;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public List<Task> load() throws DukeException {
        FileReader fr = new FileReader();

        return fr.readFile(filePath);
    }

    public void save(String modifiedResult) throws DukeException{
        DukeFileWriter fw = new DukeFileWriter();

        fw.writeFile(filePath, modifiedResult);
    }
}
