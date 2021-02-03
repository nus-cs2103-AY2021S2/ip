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

    public static boolean containsValue(String value) {
        for (Queries query : Queries.values()) {
            if (query.name().equals(value)) {
                return true;
            }
        }
        return false;
    }
}
