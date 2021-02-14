package util;

import java.util.HashMap;
import java.util.List;

public class CommandMap {
    private final String command;
    private final String description;
    private final HashMap<String, List<String>> argMap;

    public CommandMap(String command, String description,
                      HashMap<String, List<String>> argMap) {
        this.command = command;
        this.description = description;
        this.argMap = argMap;
    }

    public String getCommand() {
        return command;
    }

    public String getDescription() {
        return description;
    }

    public HashMap<String, List<String>> getArgMap() {
        return argMap;
    }

    public boolean containsArg(String key) {
        return argMap.containsKey(key);
    }

    public List<String> getArg(String key) {
        return argMap.get(key);
    }
}
