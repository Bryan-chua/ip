package bob;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Runs the Bob task manager through a text interface.
 */
public class Bob {
    /** Whether task data can be saved safely during the current run. */
    private static boolean isStorageAvailable = true;

    private Bob() {
    }

    private static ArrayList<Task> loadTasks() {
        try {
            return Storage.loadTasks();
        } catch (BobException exception) {
            isStorageAvailable = false;
            System.out.println("I could not load your saved tasks: " + exception.getMessage());
            System.out.println("Starting with an empty task list instead.");
        } catch (IOException exception) {
            isStorageAvailable = false;
            System.out.println("I could not read the data file. Starting with an empty task list.");
        }
        return new ArrayList<>();
    }

    private static void saveTasks(ArrayList<Task> tasks) {
        if (!isStorageAvailable) {
            System.out.println("I could not save your tasks. Please check the data file and try again.");
            return;
        }

        try {
            Storage.saveTasks(tasks);
        } catch (IOException exception) {
            isStorageAvailable = false;
            System.out.println("I could not save your tasks. Please check the data file and try again.");
        }
    }

    /**
     * Starts the text interface.
     *
     * @param args Command-line arguments; not used.
     */
    public static void main(String[] args) {
        Ui ui = new Ui();
        Parser parser = new Parser();
        ui.showIntro();
        String userCmd = ui.readCommand();
        ArrayList<Task> tasks = loadTasks();
        while (!userCmd.equals("bye")) {
            try {
                // list
                if (userCmd.equals("list")) {
                    ui.showTasklist(tasks);
                } 
                // mark
                else if (userCmd.equals("mark") || userCmd.startsWith("mark ")) {
                    int taskNumber = parser.getTaskNumber(userCmd, "mark");
                    if (taskNumber < 1 || taskNumber > tasks.size()) {
                        throw new BobException("The task number does not exist.");
                    }
                    tasks.get(taskNumber - 1).markAsDone();
                    saveTasks(tasks);
                    System.out.println("marked as done: " + tasks.get(taskNumber - 1).description);
                } 
                // unmark
                else if (userCmd.equals("unmark") || userCmd.startsWith("unmark ")) {
                    int taskNumber = parser.getTaskNumber(userCmd, "unmark");
                    if (taskNumber < 1 || taskNumber > tasks.size()) {
                        throw new BobException("The task number does not exist.");
                    }

                    tasks.get(taskNumber - 1).markAsNotDone();
                    saveTasks(tasks);
                    System.out.println("marked as not done: " + tasks.get(taskNumber - 1).description);
                } 
                // todo
                else if (userCmd.equals("todo") || userCmd.startsWith("todo ")) {
                    String description = parser.getDescription(userCmd, "todo");
                    Todo todo = new Todo(description);
                    tasks.add(todo);
                    saveTasks(tasks);

                    System.out.println("Got it. I've added this task:");
                    System.out.println(todo);
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                }
                // deadline
                else if (userCmd.equals("deadline") || userCmd.startsWith("deadline ")) {
                    String details = userCmd.substring("deadline".length());
                    if (details.isEmpty()) {
                        throw new BobException("Please provide a valid task.");
                    }
                    if (!details.contains(" /by ")) {
                        throw new BobException("Please provide a valid deadline");
                    }

                    int byIndex = details.indexOf(" /by ");
                    String description = details.substring(0, byIndex).trim();
                    String by = details.substring(byIndex + " /by ".length()).trim();
                    if (description.isEmpty()) {
                        throw new BobException("Please provide a valid task.");
                    }
                    if (by.isEmpty()) {
                        throw new BobException("Please provide a valid deadline");
                    }

                    Deadline deadline = new Deadline(description, by);
                    tasks.add(deadline);
                    saveTasks(tasks);

                    System.out.println("Got it. I've added this task:");
                    System.out.println(deadline);
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                }
                // event
                else if (userCmd.equals("event") || userCmd.startsWith("event ")) {
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

                    Event event = new Event(description, from, to);
                    tasks.add(event);
                    saveTasks(tasks);

                    System.out.println("Got it. I've added this task:");
                    System.out.println(event);
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                }
                // delete
                else if (userCmd.equals("delete") || userCmd.startsWith("delete ")) {
                    int taskNumber = parser.getTaskNumber(userCmd, "delete");
                    if (taskNumber < 1 || taskNumber > tasks.size()) {
                        throw new BobException("The task number does not exist.");
                    }
                    
                    Task removedTask = tasks.remove(taskNumber - 1);
                    saveTasks(tasks);
                    System.out.println("Noted. I've removed this task:");
                    System.out.println(removedTask);
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                }
                else {
                    throw new BobException("I'm sorry, I don't quite get you!");
                }
            }
            catch (BobException exception) {
                ui.showError(exception.getMessage());
            }
            userCmd = ui.readCommand();
        }
        ui.showBye();
        ui.closeScanner();
    }
}
