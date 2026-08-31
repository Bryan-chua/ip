import java.util.Scanner;

public class Bob {
    static void outputList(String[] input, boolean[] isDone) {
        int count = 0;
        while (count < input.length && input[count] != null) {
            String statusIcon = isDone[count] ? "X" : " ";
            System.out.println(count + 1 + ".[" + statusIcon + "] " + input[count]);
            count++;
        }
    }

    public static void main(String[] args) {
        // responses
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
        
        // variable creation
        String[] input = new String[100];
        boolean[] isDone = new boolean[100];
        int count = 0;

        // chatbot
        System.out.println(intro);
        System.out.println(ask);

        Scanner scanner = new Scanner(System.in);
        String userCmd = scanner.nextLine();
        while (!userCmd.equals("bye")) {
            if (userCmd.equals("list")) {
                outputList(input, isDone);
            } else if (userCmd.startsWith("mark ")) {
                int taskNumber = Integer.parseInt(userCmd.substring(5).trim());
                isDone[taskNumber - 1] = true;
                System.out.println("marked as done: " + input[taskNumber - 1]);
            } else if (userCmd.startsWith("unmark ")) {
                int taskNumber = Integer.parseInt(userCmd.substring(7).trim());
                isDone[taskNumber - 1] = false;
                System.out.println("marked as not done: " + input[taskNumber - 1]);
            } else {
                System.out.println("added: " + userCmd);
                input[count++] = userCmd;
            }
            userCmd = scanner.nextLine();
        }
        System.out.println(bye);
        scanner.close();
    }
}
