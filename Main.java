import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.Buffer;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class Main {

    public static void main(String[] args) {

        // DECLARE PROFILE
        Profile profile = new Profile();

        // DECLARE OTHER VARIABLES
        String homePageChoice = "";
        String file = "usernames.txt"; // Specify the file name

        // INITIATE USERNAMES.TXT
        createFile(file);
        System.out.println("File exists: " + file);

        // ASK USER TO LOGIN OR CREATE PROFILE
        homePageChoice = runHomePage(homePageChoice);

        // USER CHOICE TO LOGIN
        if (homePageChoice.equals("1")) {
            loginToProfile();
        }
        // USER CHOICE TO CREATE PROFILE
        else {
            // Open scanner
            Scanner scanner = new Scanner(System.in);

            // BE SURE USERNAME ISN'T TAKEN
            String username = "";
            boolean usernameTaken = false;

            System.out.println("What do you want your username to be?");
            if (scanner.hasNextLine()) {
                username = scanner.nextLine();
                try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                    String line;
                    while ((line = br.readLine()) != null) {
                        if (line.equals(username)) {
                            System.out.println("That username is already taken");
                            usernameTaken = true;
                            break;
                        }
                    }
                } catch (IOException e) {
                    System.err.println("Error reading the file: " + e.getMessage());
                }
            } else {
                System.err.println("No input for username.");
            }

            if (!usernameTaken) {
                // Continue with the rest of the profile creation
                System.out.print("What is your first name?");
                String firstName = scanner.nextLine();
                System.out.println("What is your last name?");
                String lastName = scanner.nextLine();
                System.out.print("What is your gender? ");
                String gender = scanner.nextLine();
                System.out.print("What is your email? ");
                String email = scanner.nextLine();
                System.out.print("What is your birthDate? ");
                String birthDate = scanner.nextLine();
                System.out.print("What is your relationship status? ");
                String relationshipStatus = scanner.nextLine();

                // SET PROFILE INFORMATION
                profile.setUsername(username);
                profile.setFirstName(firstName);
                profile.setLastName(lastName);
                profile.setGender(gender);
                profile.setEmail(email);
                profile.setBirthDate(birthDate);
                profile.setRelationshipStatus(relationshipStatus);

                // ADD USERNAME TO USERNAME FILE
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
                // The second parameter 'true' in FileWriter constructor appends to the file
                // Write a new line
                writer.newLine();
                // Write some content
                writer.write(username);
                System.out.println(username + " added to the file successfully.");
                } catch (IOException e) {
                System.err.println("Error writing to the file: " + e.getMessage());
                }
                }

                // CREATE A GOOD LOOKING PROFILE PAGE FOR THEM
                // ******

                String pageFile = username + ".txt";
                createFile(pageFile);
                String firstName = profile.getFirstName();
                String lastName = profile.getLastName();
                
                try (BufferedWriter page = new BufferedWriter(new FileWriter(pageFile, true))){
                    page.write("PAL profile page of " + firstName + lastName);
                } catch (IOException e) {
                    e.printStackTrace(); // Handle the exception according to your application's needs
                }

            // Close scanner
            scanner.close();

            }
        }

    public static boolean createFile(String fileName) {
        System.out.println("Create file function started runnning");

        File file = new File(fileName);
        try {
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getAbsolutePath());
                return true;
            } else {
                System.out.println("File '" + fileName + "' already exists.");
                return false;
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            return false;
        }
    }

    public static String runHomePage(String homePageChoice) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to PAL! Where friends are friendly! ");
        while (!homePageChoice.equals("1") && !homePageChoice.equals("2")) {
            System.out.println("Choose an option below: \n1. Login \n2. Create Profile");
            homePageChoice = scanner.nextLine();

            if (homePageChoice.equals("1")) {
                loginToProfile();
            } else if (homePageChoice.equals("2")) {
                // Additional functionality for creating a profile can be added here
            } else {
                System.out.println("Not a valid input!");
            }
        }

        scanner.close();

        return homePageChoice;
    }

    public static boolean loginToProfile() {
        // Add login logic here
        return true;
    }
}