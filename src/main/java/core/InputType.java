package core;

import java.util.function.Predicate;

public enum InputType {
    LIST(x -> x.toLowerCase().startsWith("list")),
    ADD(x -> true),
    UNKNOWN(x -> true);

    private Predicate<String> toMatch;
    InputType(Predicate<String> toMatch) {
        this.toMatch = toMatch;
    }

    public boolean doesMatch(String x) {
        if(this.toMatch == null || x == null)
            return false;

        return toMatch.test(x);
    }

}
