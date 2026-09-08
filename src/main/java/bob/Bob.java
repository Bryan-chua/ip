package bob;

import java.util.Scanner;

/**
 * Runs the Bob task manager through a text interface.
 */
public class Bob {
    private Bob() {
    }

    private static void outputList(Task[] tasks) {
        int count = 0;
        while (count < tasks.length && tasks[count] != null) {
            System.out.println((count + 1) + "." + tasks[count]);
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

        Task[] tasks = new Task[100];
        int count = 0;

        System.out.println(intro);
        System.out.println(ask);

        Scanner scanner = new Scanner(System.in);
        String userCmd = scanner.nextLine();
        while (!userCmd.equals("bye")) {
            if (userCmd.equals("list")) {
                outputList(tasks);
            } 
            else if (userCmd.startsWith("mark ")) {
                int taskNumber = Integer.parseInt(userCmd.substring(5).trim());
                tasks[taskNumber - 1].markAsDone();
                System.out.println("marked as done: " + tasks[taskNumber - 1].description);
            } 
            else if (userCmd.startsWith("unmark ")) {
                int taskNumber = Integer.parseInt(userCmd.substring(7).trim());
                tasks[taskNumber - 1].markAsNotDone();
                System.out.println("marked as not done: " + tasks[taskNumber - 1].description);
            } 
            else if (userCmd.startsWith("todo ")) {
                String description = userCmd.substring(4).trim();
                Todo todo = new Todo(description);
                tasks[count++] = todo;

                System.out.println("Got it. I've added this task:");
                System.out.println(todo);
                System.out.println("Now you have " + count + " tasks in the list.");
            }
            else if (userCmd.startsWith("deadline ")) {
                String details = userCmd.substring("deadline ".length());
                int byIndex = details.indexOf(" /by ");
                String description = details.substring(0, byIndex).trim();
                String by = details.substring(byIndex + " /by ".length()).trim();

                Deadline deadline = new Deadline(description, by);
                tasks[count++] = deadline;

                System.out.println("Got it. I've added this task:");
                System.out.println(deadline);
                System.out.println("Now you have " + count + " tasks in the list.");
            }
            else if (userCmd.startsWith("event ")) {
                String details = userCmd.substring("event ".length());
                int fromIndex = details.indexOf(" /from ");
                int toIndex = details.indexOf(" /to ");
                String description = details.substring(0, fromIndex).trim();
                String from = details.substring(fromIndex + " /from ".length(), toIndex).trim();
                String to = details.substring(toIndex + " /to ".length()).trim();

                Event event = new Event(description, from, to);
                tasks[count++] = event;

                System.out.println("Got it. I've added this task:");
                System.out.println(event);
                System.out.println("Now you have " + count + " tasks in the list.");
            }
            else {
                System.out.println("added: " + userCmd);
                tasks[count++] = new Task(userCmd);
            }
            userCmd = scanner.nextLine();
        }
        System.out.println(bye);
        scanner.close();
    }
}
