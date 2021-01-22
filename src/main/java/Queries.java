public enum Queries {
    ADD,
    LIST,
    DONE,
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
