package duke.handler;

public enum Queries {
    TODO,
    DEADLINE,
    EVENT,
    LIST,
    FIND,
    DONE,
    DELETE,
    BYE;

    /**
     * Checks if given value is amongst the list of acceptable queries.
     * @param value The string value to be checked.
     * @return Boolean statuf of whether value is a found query.
     */
    public static boolean containsValue(String value) {
        for (Queries query : Queries.values()) {
            if (query.name().equals(value)) {
                return true;
            }
        }
        return false;
    }
}
