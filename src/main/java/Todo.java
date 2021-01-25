public class Todo extends Task {

  protected String description;

  public Todo(String description) {
    super(description);
  }

  @Override
  public String toString() {
    return "[T]" + super.toString();
  }

  @Override
  public String saveText() {
    return "T" + "," + this.getStatusIcon() + "," + this.getDescription();

  }


}
