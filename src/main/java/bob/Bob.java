package bob;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Runs the Bob task manager through a text interface.
 */
public class Bob {
    private Bob() {
    }

    private static void outputList(ArrayList<Task> tasks) {
        int count = 0;
        for (Task task : tasks) {
            System.out.println((count + 1) + "." + task);
            count++;
        }
    }

    /**
     * Starts the text interface.
     *
     * @param args Command-line arguments; not used.
     */
    public static void main(String[] args) {
        String intro = " _   _      _ _          ___ _                  ____        _     _ \n"
            + "| | | | ___| | | ___    |_ _|' _ __ ___       | __ )  ___ | |__ | |\n"
            + "| |_| |/ _ \\ | |/ _ \\    | || | '_ ` _ \\      |  _ \\ / _ \\| '_ \\| |\n"
            + "|  _  |  __/ | | (_) |   | || | | | | | |     | |_) | (_) | |_) |_|\n"
            + "|_| |_|\\___|_|_|\\___( ) |___|_|_| |_| |_|     |____/ \\___/|_.__/(_)\n"
            + "                   |/                                             \n";

        String ask = " _________________________ \n"
            + "|                         |\n"
            + "|  What can I do for you? |\n"
            + "|_________________________|\n";

        String bye = " ___________________________________ \n"
            + "|                                   |\n"
            + "|  Bye! Hope to see you again soon! |\n"
            + "|___________________________________|\n";

        ArrayList<Task> tasks = new ArrayList<>();

        System.out.println(intro);
        System.out.println(ask);

        Scanner scanner = new Scanner(System.in);
        String userCmd = scanner.nextLine();
        while (!userCmd.equals("bye")) {
            try {
                // list
                if (userCmd.equals("list")) {
                    outputList(tasks);
                } 
                // mark
                else if (userCmd.equals("mark") || userCmd.startsWith("mark ")) {
                    String taskNumberStr = userCmd.substring("mark".length()).trim();
                    if (taskNumberStr.isEmpty()) {
                        throw new BobException("This cannot be empty!");
                    }
                    
                    int taskNumber;
                    try {
                        taskNumber = Integer.parseInt(taskNumberStr);
                    }
                    catch (NumberFormatException exception) {
                        throw new BobException("Please provide a valid task number.");
                    }

                    if (taskNumber < 1 || taskNumber > tasks.size()) {
                        throw new BobException("The task number does not exist.");
                    }
                    tasks.get(taskNumber - 1).markAsDone();
                    System.out.println("marked as done: " + tasks.get(taskNumber - 1).description);
                } 
                // unmark
                else if (userCmd.equals("unmark") || userCmd.startsWith("unmark ")) {
                    String taskNumberStr = userCmd.substring("unmark".length()).trim();
                    if (taskNumberStr.isEmpty()) {
                        throw new BobException("This cannot be empty!");
                    }
                    
                    int taskNumber;
                    try {
                        taskNumber = Integer.parseInt(taskNumberStr);
                    }
                    catch (NumberFormatException exception) {
                        throw new BobException("Please provide a valid task number.");
                    }

                    if (taskNumber < 1 || taskNumber > tasks.size()) {
                        throw new BobException("The task number does not exist.");
                    }

                    tasks.get(taskNumber - 1).markAsNotDone();
                    System.out.println("marked as not done: " + tasks.get(taskNumber - 1).description);
                } 
                // todo
                else if (userCmd.equals("todo") || userCmd.startsWith("todo ")) {
                    String description = userCmd.substring("todo".length()).trim();
                    if (description.isEmpty()) {
                        throw new BobException("Please provide a valid task.");
                    }

                    Todo todo = new Todo(description);
                    tasks.add(todo);

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

                    System.out.println("Got it. I've added this task:");
                    System.out.println(event);
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                }
                // delete
                else if (userCmd.equals("delete") || userCmd.startsWith("delete ")) {
                    String taskNumberStr = userCmd.substring("delete".length()).trim();
                    if (taskNumberStr.isEmpty()) {
                        throw new BobException("This cannot be empty!");
                    }
                    
                    int taskNumber;
                    try {
                        taskNumber = Integer.parseInt(taskNumberStr);
                    }
                    catch (NumberFormatException exception) {
                        throw new BobException("Please provide a valid task number.");
                    }

                    if (taskNumber < 1 || taskNumber > tasks.size()) {
                        throw new BobException("The task number does not exist.");
                    }
                    
                    Task removedTask = tasks.remove(taskNumber-1);
                    System.out.println("Noted. I've removed this task:");
                    System.out.println(removedTask);
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                }
                else {
                    throw new BobException("I'm sorry, I don't quite get you!");
                }
            }
            catch (BobException exception) {
                System.out.println(exception.getMessage());
            }
            userCmd = scanner.nextLine();
        }
        System.out.println(bye);
        scanner.close();
    }
}
