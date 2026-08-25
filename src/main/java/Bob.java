import java.util.Scanner;

public class Bob {
    static void outputList(String[] input) {
        int count = 0;
        while(count < 100 && input[count] != null){
            System.out.println(count+1 + ". " + input[count]);
            count++;
        }
        return;
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
        int count = 0;

        // chatbot
        System.out.println(intro);
        System.out.println(ask);

        Scanner scanner = new Scanner(System.in);
        String userCmd = scanner.nextLine();
        while(!userCmd.equals("bye")){
            if(userCmd.equals("list")){
                outputList(input);
            }
            else{
                System.out.println("added: " + userCmd);
                input[count++] = userCmd;
            }
            userCmd = scanner.nextLine();
        }
        System.out.println(bye);
        scanner.close();
    }
}
