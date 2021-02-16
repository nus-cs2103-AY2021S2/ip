package checklst.parser;

public class MethodPair {

    private final String description;
    private final CheckedFunction<String[], String> method;

    /**
     * Creates a new Method Pair of Description and Function to be run.
     * @param description Description of method.
     * @param method Functional Interface method to be run on call.
     */
    public MethodPair(String description, CheckedFunction<String[], String> method) {
        this.description = description;
        this.method = method;
    }

    public String getDescription() {
        return this.description;
    }

    public CheckedFunction<String[], String> getMethod() {
        return this.method;
    }

}
