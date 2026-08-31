package bob;

/**
 * Represents a task that can be marked as done or not done.
 */
public class Task {
    /** Description of this task. */
    protected String description;
    /** Whether this task is done. */
    protected boolean isDone;

    /**
     * Creates an incomplete task with the given description.
     *
     * @param description Description of task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon for this task.
     *
     * @return `X` if the task is done, or a space otherwise.
     */
    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    /**
     * Marks this task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks this task as not done.
     */
    public void markAsNotDone() {
        isDone = false;
    }

    /**
     * Returns the display text for this task.
     *
     * @return Status icon followed by the task description.
     */
    public String getDisplayText() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
