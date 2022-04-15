package duke.parser;

import java.util.HashMap;

/**
 * Wrapper for user input tokens.
 *
 * Essentially composition with map as attribute.
 * Class form used instead of simple dictionary to standardize formats, as well as
 * only expose required functionality, i.e. check, get, set keys.
 */
public class UserInputTokenSet {

    private final HashMap<String, String> data = new HashMap<>();

    /** Sole constructor */
    public UserInputTokenSet() {}

    /**
     * Stores key-text pair.
     *
     * @param key key
     * @param text string value
     */
    public void set(String key, String text) {
        data.put(key, text);
    }

    /** Checks if key exists.
     *
     * @param key key
     * @return string value
     */
    public boolean contains(String key) {
        return data.containsKey(key);
    }

    /**
     * Returns value corresponding to key if exists, otherwise empty string.
     *
     * @param key key
     * @return string value
     */
    public String get(String key) {
        if (!contains(key)) {
            return "";
        }
        return data.get(key);
    }
}
