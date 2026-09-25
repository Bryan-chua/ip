package bob;

public class Parser {
    public Parser() {  
    }

    public int getTaskNumber(String userCmd, String action) throws BobException {
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

    public String getDescription(String userCmd, String action) throws BobException {
        String description = userCmd.substring(action.length()).trim();
        if (description.isEmpty()) {
            throw new BobException("Please provide a valid task!");
        }

        return description;
    }
}
