package data;

import java.sql.SQLOutput;
import java.util.Scanner;

public class Main {
    private static PasswordManager pm = new PasswordManager();

    public static void main(String[] args) {

        System.out.println("Welcome in your Password Manager");
        Scanner scanner =new Scanner(System.in);



        while (true) {

            System.out.println("\nChoose a option!");
            System.out.println("1: New Password");
            System.out.println("2: Show Password");
            System.out.println("3: Delete Password");
            System.out.println("4: Show AllPasswords");
            System.out.println("5: Finish");

            System.out.print("Your Choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Not Implemented");
                    addPassword();
                    break;
                case 2:
                    System.out.println("Not Implemented");
                    //showPassword();
                    break;
                case 3:
                    System.out.println("Not Implemented");
                    //deletePassword();
                    break;
                case 4:
                    System.out.println("Not Implemented");
                    //showAllPasswords();
                    break;
                case 5:
                    System.out.println("Not Implemented");
                    //finish();
                    break;
                default:
                    System.out.println("Upps Something went wrong");
            }
        }

    }
    private static void addPassword() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Website: ");
        String website = scanner.next();

        System.out.print("Benutzername: ");
        String username = scanner.next();

        System.out.print("Passwort: ");
        String password = scanner.next();

        pm.addPassword(website, username, password);
    }
}
