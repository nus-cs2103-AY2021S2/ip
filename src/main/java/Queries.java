public enum Queries {
    ECHO,
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
