package bob;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private final Scanner scanner;
    private static final String intro = " _   _      _ _          ___ _                  ____        _     _ \n"
            + "| | | | ___| | | ___    |_ _|' _ __ ___       | __ )  ___ | |__ | |\n"
            + "| |_| |/ _ \\ | |/ _ \\    | || | '_ ` _ \\      |  _ \\ / _ \\| '_ \\| |\n"
            + "|  _  |  __/ | | (_) |   | || | | | | | |     | |_) | (_) | |_) |_|\n"
            + "|_| |_|\\___|_|_|\\___( ) |___|_|_| |_| |_|     |____/ \\___/|_.__/(_)\n"
            + "                   |/                                             \n";
    private static final String ask = " _________________________ \n"
        + "|                         |\n"
        + "|  What can I do for you? |\n"
        + "|_________________________|\n";
    private static final String bye = " ___________________________________ \n"
        + "|                                   |\n"
        + "|  Bye! Hope to see you again soon! |\n"
        + "|___________________________________|\n";

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public String readCommand() {
        String userCmd = scanner.hasNextLine() ? scanner.nextLine() : "bye";
        return userCmd;
    }

    public void showIntro() {
        System.out.println(intro);
        System.out.println(ask);
    }

    public void showBye() {
        System.out.println(bye);
    }

    public void showTasklist(ArrayList<Task> tasks){
        int count = 0;
        for (Task task : tasks) {
            System.out.println((count + 1) + ". " + task);
            count++;
        }
    }

    public void showMatchingTasks(ArrayList<Task> tasks, String keyword){
        int count = 1;
        System.out.println("Here are the matching tasks in your list:");
        for (Task task : tasks) {
            if (task.description.contains(keyword)) {
                System.out.println(count + ". " + task);
                count++;
            }
        }
    }

    public void showError(String message) {
        System.out.println("Oops! " + message);
    }

    public void closeScanner() {
        scanner.close();
    }
}
