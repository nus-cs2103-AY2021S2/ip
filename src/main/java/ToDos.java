/**
   * ToDos inherits Task
   * @param description the description of the task
   */

@SuppressWarnings("checkstyle:JavadocType")
class ToDos extends Task {

    @SuppressWarnings({"checkstyle:WhitespaceAround", "checkstyle:SingleSpaceSeparator"})
    public ToDos(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
