/**
   * ToDos inherits Task
   * @param description the description of the task
   */

class ToDos extends Task {

    public ToDos(String description)  {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
