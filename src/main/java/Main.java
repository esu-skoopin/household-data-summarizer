/**
 * Acts as an entry point to a household data summarizer app
 */

import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        String input;
        boolean done = false;
        System.out.println("Welcome!\n");

        while (!done) {
            System.out.println("""
                    What would you like to do?
                    A. See the parameters of a valid dataset
                    B. Summarize a test dataset
                    C. Summarize a custom dataset
                    Q. Quit
                    """);
            System.out.print("Type in your option: ");
            input = scanner.nextLine().replaceAll("[^a-zA-Z]", "").toUpperCase();
            switch (input) {
                case "A":
                    System.out.println("""
                            \nValid datasets are .csv or .txt files with lines of data representing information about a group of residents. Each line of data
                            must include the following information about a resident exactly as specified: first name, last name, street address, city, state
                            abbreviation and age.
                            
                            Passed datasets that do not fit the above requirements may yield unexpected results.
                            
                            Example of a valid line of data:
                            "Dave","Smith","123 Main St","Seattle","WA","43"
                            
                            =========================================
                            
                            """);
                    break;
                case "B":
                    System.out.println();
                    HouseholdDataSummarizer.summarizeHouseholdData(new File("src/main/java/test.csv"));
                    System.out.println("=========================================\n");
                    break;
                case "C":
                    System.out.print("Paste the path to your dataset (.csv or .txt only) here: ");
                    input = scanner.nextLine().trim();
                    System.out.println();
                    HouseholdDataSummarizer.summarizeHouseholdData(new File(input));
                    System.out.println("=========================================\n");
                    break;
                case "Q":
                    done = true;
                    break;
                default:
                    System.out.println("\nNot a valid option.\n");
                    break;
            }
        }
        scanner.close();
        System.out.println("Successfully exited program.");
    }
}