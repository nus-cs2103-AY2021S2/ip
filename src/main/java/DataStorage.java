import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DataStorage {
    private static final String DATA_FILE_PATH = String.format(".%sdata%stasks.json", File.separator, File.separator);
    private File dataFile;
    private ObjectMapper mapper;

    /**
     * Constructs a DataStorage object.
     */
    public DataStorage() {
        dataFile = new File(DATA_FILE_PATH);
        mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    /**
     * Initialises the backing store for future data storage.
     * @throws DukeException If unable to write to the filesystem.
     */
    public void createBackingStoreIfNotExists() throws DukeException {
        try {
            dataFile.getParentFile().mkdirs();
            if (dataFile.createNewFile()) {
                saveTasks(new ArrayList<>());
            }
        } catch (IOException ioException) {
            throw new DukeException(String.format("An error occurred while setting up the backing store for Duke. Perhaps this might help:\n%s", ioException.getMessage()));
        }
    }

    /**
     * Returns tasks on the backing store.
     * @return Tasks read from the backing store.
     * @throws DukeException If error occurs when reading from the backing store.
     */
    public List<Task> readTasks() throws DukeException {
        try (BufferedInputStream biStream = new BufferedInputStream(new FileInputStream(dataFile))) {
            return mapper.readerFor(new TypeReference<ArrayList<Task>>() {
            }).readValue(biStream);
        } catch (MismatchedInputException mie) {
            return new ArrayList<Task>();
        } catch (IOException ioException) {
            throw new DukeException(String.format("An error occurred while reading the backing store for Duke. Perhaps this might help:\n%s", ioException.getMessage()));
        }
    }

    /**
     * Saves tasks to the backing store.
     * @param tasks List of tasks to save.
     * @throws DukeException If error occurs when writing to the backing store.
     */
    public void saveTasks(List<Task> tasks) throws DukeException {

        try (BufferedOutputStream boStream = new BufferedOutputStream(new FileOutputStream(dataFile, false))) {
            mapper.writerFor(new TypeReference<List<Task>>() {
            }).writeValue(boStream, tasks);
        } catch (IOException ioException) {
            throw new DukeException(String.format("An error occurred while saving tasks to the backing store for Duke. Perhaps this might help:\n%s", ioException.getMessage()));
        }
    }
}
