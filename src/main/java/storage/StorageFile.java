package storage;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.fasterxml.jackson.databind.json.JsonMapper;

import data.TaskList;

public class StorageFile {

    private static final String workingDirPath = System.getProperty("user.dir");
    private static final String DEFAULT_STORAGE_FILE_PATH = java.nio.file.Paths
            .get(workingDirPath, "saveFile.json")
            .toString();
    private final ObjectMapper mapper = JsonMapper.builder()
            .findAndAddModules()
            .build();

    private File saveFile;

    public StorageFile() {
        this(DEFAULT_STORAGE_FILE_PATH);
    }

    /**
     * StorageFile constructor
     *
     * @param filePath
     * @throws IOException
     */
    public StorageFile(String filePath) {
        saveFile = new File(filePath);
    }

    /**
     * Writes the given tasks to the savefile
     *
     * @param tasks
     * @throws IOException
     */
    public void save(TaskList tasks) throws IOException {
        saveFile.createNewFile();
        try (BufferedOutputStream outSaveFile = new BufferedOutputStream(new FileOutputStream(saveFile, false))
        ) {
            // https://github.com/FasterXML/jackson-databind/pull/1309
            mapper.writeValue(outSaveFile, tasks);
        }
    }

    /**
     * Reads from the savefile and creates tasks
     *
     * @return TaskList
     * @throws IOException
     */
    public TaskList load() throws IOException {
        try (BufferedInputStream inSaveFile = new BufferedInputStream(new FileInputStream(saveFile))
        ) {
            return mapper.readValue(inSaveFile, TaskList.class);
        } catch (MismatchedInputException mie) {
            // empty saveFile.json or invalid file format
            return new TaskList();
        }
    }
}
