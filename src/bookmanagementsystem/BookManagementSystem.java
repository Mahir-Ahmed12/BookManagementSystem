package bookmanagementsystem;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Scanner;

public class BookManagementSystem {

    static final String FILE_NAME = "books.txt";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n*********** Book Management System************");
            System.out.println("1. Write to File");
            System.out.println("2. Read File");
            System.out.println("3. Append to File");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            // Guard against non-numeric input crashing the program
            while (!sc.hasNextInt()) {
                System.out.print("Invalid input. Enter a number (1-4): ");
                sc.next();
            }
            choice = sc.nextInt();
            sc.nextLine(); // consume leftover newline

            switch (choice) {
                case 1:
                    writeToFile(sc);
                    break;
                case 2:
                    readFile();
                    break;
                case 3:
                    appendToFile(sc);
                    break;
                case 4:
                    System.out.println("Exiting program. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 4.");
            }

        } while (choice != 4);

        sc.close();
    }

    // Option 1: Write to File (creates file if it doesn't exist, overwrites contents)
    static void writeToFile(Scanner sc) {
        File file = new File(FILE_NAME);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            System.out.println("\nEnter details of 5 books:");
            for (int i = 1; i <= 5; i++) {
                System.out.print("\nBook " + i + " ID: ");
                String id = sc.nextLine();
                System.out.print("Book " + i + " Title: ");
                String title = sc.nextLine();

                bw.write(id + "," + title);
                bw.newLine();
            }
            System.out.println("\nBooks written to file successfully!");

        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    // Option 2: Read File
    static void readFile() {
        File file = new File(FILE_NAME);

        if (!file.exists()) {
            System.out.println("\nFile does not exist yet. Please write to the file first (Option 1).");
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            int count = 1;
            System.out.println("\n--- Book Records ---");

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",", 2);
                if (parts.length == 2) {
                    System.out.println(count + ". Book ID: " + parts[0] + " | Title: " + parts[1]);
                } else {
                    System.out.println(count + ". " + line);
                }
                count++;
            }

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    // Option 3: Append to File
    static void appendToFile(Scanner sc) {
        File file = new File(FILE_NAME);

        if (!file.exists()) {
            System.out.println("\nFile does not exist yet. Please write to the file first (Option 1).");
            return;
        }

        // 'true' in FileWriter enables append mode, so existing content is kept
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))) {
            System.out.println("\nEnter details of 2 additional books:");
            for (int i = 1; i <= 2; i++) {
                System.out.print("\nBook " + i + " ID: ");
                String id = sc.nextLine();
                System.out.print("Book " + i + " Title: ");
                String title = sc.nextLine();

                bw.write(id + "," + title);
                bw.newLine();
            }
            System.out.println("\nBooks appended to file successfully!");

        } catch (IOException e) {
            System.out.println("Error appending to file: " + e.getMessage());
        }
    }
}