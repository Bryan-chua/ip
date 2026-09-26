package bob;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

public class Parser {
    public Parser() {  
    }

    public int parseTaskNumber(String userCmd, String action) throws BobException {
        String taskNumberStr = userCmd.substring(action.length()).trim();
        if (taskNumberStr.isEmpty()) {
            throw new BobException("This cannot be empty!");
        }
        int taskNumber;
        try {
            taskNumber = Integer.parseInt(taskNumberStr);
        }
        catch (NumberFormatException exception) {
            throw new BobException("Please provide a valid task number!");
        }

        return taskNumber;
    }

    public String parseDescription(String userCmd, String action) throws BobException {
        String description = userCmd.substring(action.length()).trim();
        if (description.isEmpty()) {
            throw new BobException("Please provide a valid task!");
        }

        return description;
    }

    public String[] parseDeadline(String userCmd) throws BobException {
        String details = userCmd.substring("deadline".length());
        if (details.isEmpty()) {
            throw new BobException("Please provide a valid task.");
        }
        if (!details.contains(" /by ")) {
            throw new BobException("Please provide a valid deadline.");
        }

        int byIndex = details.indexOf(" /by ");
        String description = details.substring(0, byIndex).trim();
        String by = details.substring(byIndex + " /by ".length()).trim();
        if (description.isEmpty()) {
            throw new BobException("Please provide a valid task.");
        }
        if (by.isEmpty()) {
            throw new BobException("Please provide a valid deadline.");
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu HHmm").withResolverStyle(ResolverStyle.STRICT);
        LocalDateTime deadlineInfo;
        try {
            deadlineInfo = LocalDateTime.parse(by, formatter);
        } catch (DateTimeParseException exception) {
            throw new BobException("Please use the deadline format DD/MM/YYYY HHMM! eg. 04/05/2004 1200");
        }
        LocalDateTime now = LocalDateTime.now();
        if (!deadlineInfo.isAfter(now)) {
            throw new BobException("Please give a deadline in the future!");
        }
        Duration duration = Duration.between(now, deadlineInfo);
        String[] output = {description, by, String.valueOf(duration.toDays()), String.valueOf(duration.toHoursPart())};
        return output;
    }

    public String[] parseEvent(String userCmd) throws BobException {
        String details = userCmd.substring("event".length());
        if (details.isEmpty()) {
            throw new BobException("Please provide a valid task.");
        }
        if (!details.contains(" /from ") || !details.contains(" /to ")) {
            throw new BobException("Please provide valid duration.");
        }

        int fromIndex = details.indexOf(" /from ");
        int fromStartIndex = fromIndex + " /from ".length();
        int toIndex = details.indexOf(" /to ");
        if (fromStartIndex > toIndex) {
            throw new BobException("Please give a valid timeline!");
        }

        String description = details.substring(0, fromIndex).trim();
        String from = details.substring(fromStartIndex, toIndex).trim();
        String to = details.substring(toIndex + " /to ".length()).trim();
        if (description.isEmpty()) {
            throw new BobException("Please provide a valid task.");
        }
        if (from.isEmpty() || to.isEmpty()) {
            throw new BobException("Please provide a valid deadline");
        }
        String[] output = {description, from, to};
        return output;
    }
}
