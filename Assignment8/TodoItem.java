package Todo;

public class TodoItem {
    private final String task;
    private boolean isDone;

    public TodoItem(String task) {
        this.task = task;
        this.isDone = false;
    }

    public String getTask() {
        return task;
    }

    public boolean isDone() {
        return isDone;
    }

    public void markDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        return (isDone ? "[✔] " : "[ ] ") + task;
    }
}
