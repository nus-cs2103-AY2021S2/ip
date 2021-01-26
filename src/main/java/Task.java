public class Task {

  protected String description;
  protected boolean isDone;

  public Task(String description) {
    this.description = description;
    this.isDone = false;
  }

  public String getDescription() {
    return description;
  }

  public String getStatusIcon() {
    return (isDone ? "\u2718" : " "); //return tick or X symbols
  }

  public void taskDone() {
    this.isDone = true;
  }

  @Override
  public String toString() {
    return "[" + this.getStatusIcon() + "] " + this.description;
  }

  public String saveText() {
    return  "| " + this.getStatusIcon() +   "| " + this.getDescription();
  }
}