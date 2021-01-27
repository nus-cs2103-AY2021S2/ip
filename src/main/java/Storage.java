import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Storage {
    // source: https://www.sghill.net/how-do-i-make-cross-platform-file-paths-in-java.html
    // inserts correct file path separator on *nix and Windows
    private static java.nio.file.Path dataPath = java.nio.file.Paths.get(
            "../../../data",
            "olaf.txt");

    static void saveData() {
        try {
            if (TaskList.tasks.size() > 0) {
                String tasksToStringIterable = TaskList.tasks.stream()
                        .map(Task::toString)
                        .reduce((a, b) -> a + "\n" + b)
                        .get();

                // adapted from: https://attacomsian.com/blog/java-save-string-to-text-file
                // write string to a file
                Files.writeString(dataPath,
                        tasksToStringIterable,
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

    static void readData() throws IOException {
        TaskList.tasks = Files.readAllLines(dataPath,
                StandardCharsets.UTF_8)
                .stream()
                .map(Storage::readTaskFromData)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    private static Task readTaskFromData(String line) {
        boolean taskIsDone = (line.charAt(4) == 'X');
        Task output = null;

        if (line.startsWith("[T]")) {
            String expression = line.split("] ", 2)[1];
            output = new Todo(expression);
        } else if (line.startsWith("[D]")) {
            String expression = line.split("] ", 2)[1];
            String[] dateTimeSplit = expression.split(" \\(by:", 2);
            LocalDateTime deadline = LocalDateTime.parse(dateTimeSplit[1].trim(),
                    DateTimeFormatter.ofPattern("d MMM yyyy HH:mm)"));
            output = new Deadline(dateTimeSplit[0], deadline);
        } else if (line.startsWith("[E]")) {
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
