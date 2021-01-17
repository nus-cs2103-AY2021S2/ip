public class Task {
  protected String description;
  protected boolean isDone;

  public Task(String description) {
    this.description = description.trim();
    this.isDone = false;
  }

  public void markDone() {
    this.isDone = true;
  }

  public String toString() {
    char done = this.isDone ? 'X' : ' ';
    return String.format("[%c] %s", done, this.description);
  }
}
