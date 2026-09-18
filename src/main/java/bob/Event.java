package bob;

/**
 * Represents a task that takes place over a period of time.
 */
public class Event extends Task {

    /** Start time description for this event. */
    protected String from;
    /** End time description for this event. */
    protected String to;

    /**
     * Creates an incomplete event task.
     *
     * @param description Description of task.
     * @param from Start time description.
     * @param to End time description.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toDataString() {
        return "E | " + (isDone ? "1" : "0") + " | " + Storage.escapeField(description)
            + " | " + Storage.escapeField(from) + " | " + Storage.escapeField(to);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
