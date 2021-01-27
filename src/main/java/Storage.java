import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;

import exception.DukeException;

public class Storage {
    protected static String sessionFile = "./saved_session";

    protected ArrayList<Task> tasks;
    protected ArrayList<String> inputs;

    /**
     * Saves current session state to file
     */
    public void saveHistory() {
        File file = new File(sessionFile);
        try {
            if (file.exists()) {
                file.delete();
            }
            file.createNewFile();

            PrintStream out = new PrintStream(file);
            for (int i = 0; i < inputs.size(); i++) {
                out.println(inputs.get(i));
            }
            out.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * Load previous session state from file
     * @throws IOException
     */
    public void loadHistory() throws IOException {
        if (!new File(sessionFile).exists()) {
            return;
        }
        InputStream fileInStream = new FileInputStream(sessionFile);
        BufferedReader reader = getReader(fileInStream);

        String line;
        while ((line = reader.readLine()) != null) {
            try {
                Duke.parseInput(line);
                inputs.add(line);
            } catch (DukeException e) {
                // undefined behaviour
                // Old command encountered
            }
        }
    }

    private static BufferedReader getReader(InputStream in) {
        return new BufferedReader(new InputStreamReader(in));
    }
}
