import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Storage {
    // source: https://stackoverflow.com/questions/28947250/create-a-directory-if-it-does-not-exist-and-then-create-the-files-in-that-direct
    // inserts correct file path separator on *nix and Windows
    private static Path dataPath;

    Storage(String filePath) {
        String[] filePathSplit = filePath.split("(?:.(?!/))+$", 2);
        String fileDirectory = filePathSplit[0];
        String file = filePathSplit[1];
        File directory = new File(fileDirectory);
        if (! directory.exists()){
            directory.mkdir();
        }
        dataPath = Paths.get(filePath);
    }

    static void saveData(String taskList) {
        try {
            // save all tasks again if TaskList has tasks
            if (taskList.length() > 0) {
                // splitting by "  %d. [" incase the task description uses periods and digits as well
                ArrayList<String> tasksWithoutIndex = new ArrayList<>(List.of(taskList.split("  \\d. \\[")));
                String toSave = tasksWithoutIndex.stream()
                        .reduce((a, b) -> a + b)
                        .get();

                // adapted from: https://attacomsian.com/blog/java-save-string-to-text-file
                // write string to a file
                Files.writeString(dataPath,
                        toSave,
                        StandardCharsets.UTF_8,
                        StandardOpenOption.CREATE,
                        StandardOpenOption.TRUNCATE_EXISTING);
            } else {
                Files.deleteIfExists(dataPath);
            }
        } catch (IOException ignore) {
            // program is safe to continue
            // StandardOpenOption.CREATE handles the case when file does not exist
        }
    }

    ArrayList<Task> load() throws IOException {
        return Files.readAllLines(dataPath,
                StandardCharsets.UTF_8)
                .stream()
                .map(this::readTaskFromData)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    private Task readTaskFromData(String line) {
        boolean taskIsDone = (line.charAt(3) == 'X');
        Task output = null;

        if (line.startsWith("T]")) {
            String expression = line.split("] ", 2)[1];
            output = new Todo(expression);
        } else if (line.startsWith("D]")) {
            String expression = line.split("] ", 2)[1];
            String[] dateTimeSplit = expression.split(" \\(by:", 2);
            LocalDateTime deadline = LocalDateTime.parse(dateTimeSplit[1].trim(),
                    DateTimeFormatter.ofPattern("d MMM yyyy HH:mm)"));
            output = new Deadline(dateTimeSplit[0], deadline);
        } else if (line.startsWith("E]")) {
            String expression = line.split("] ", 2)[1];
            String[] descriptionSplit = expression.split(" \\(at: ", 2);
            String[] durationSplit = descriptionSplit[1].split(" to ");
            LocalDateTime start = LocalDateTime.parse(durationSplit[0].trim(),
                    DateTimeFormatter.ofPattern("d MMM yyyy HH:mm"));
            LocalDateTime end = LocalDateTime.parse(durationSplit[1].trim(),
                    DateTimeFormatter.ofPattern("d MMM yyyy HH:mm)"));
            output = new Event(descriptionSplit[0], start, end);
        }
        if (taskIsDone) {
            assert output != null;
            output.markAsDone();
        }
        return output;
    }
}
