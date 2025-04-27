package data;

import secure.SavePassword;

import java.sql.SQLOutput;
import java.util.Scanner;

public class Main {
    private static PasswordManager pm = new PasswordManager();
    private static SavePassword savePassword;

    public static void main(String[] args) {

        System.out.println("Welcome in your Password Manager");
        Scanner scanner =new Scanner(System.in);
        System.out.print("Master-Passwort zum Entschlüsseln eingeben: ");
        String pw = scanner.nextLine();
        pm.loadFromFile(pw);

        savePassword = new SavePassword(pm);



        while (true) {

            System.out.println("\nChoose a option!");
            System.out.println("1: New Password");
            System.out.println("2: Show Password");
            System.out.println("3: Delete Password");
            System.out.println("4: Show all Passwords");
            System.out.println("5: Safe all Passwords");
            System.out.println("6: Finish");

            System.out.print("Your Choice: ");
            int choice = scanner.nextInt();
            //scanner.close();

            switch (choice) {
                case 1:
                    System.out.print("Website: ");
                    String website = scanner.next();

                    System.out.print("Benutzername: ");
                    String username = scanner.next();

                    System.out.print("Passwort: ");
                    String password = scanner.next();
                    pm.addPassword(website,username,password);
                    break;
                case 2:
                    pm.showAllPasswords();
                    scanner.nextLine();
                    System.out.print("Choose Password index to Show:");
                    int indexSingle = scanner.nextInt();
                    System.out.println(pm.getEntrys().get(indexSingle));
                    break;
                case 3:
                    pm.showAllPasswords();
                    System.out.print("Choose Password index to Delete: ");
                    int index = scanner.nextInt();
                    pm.deletePassword(index);
                    break;
                case 4:
                    pm.showAllPasswords();
                    break;
                case 5:
                    System.out.print("Insert Masterpassword: ");
                    String masterPassword = scanner.next();
                    savePassword.saveToFile(masterPassword);
                    break;
                case 6:
                    System.out.print("Insert Masterpassword for Exit: ");
                    String masterPasswordFinish = scanner.next();
                    savePassword.saveToFile(masterPasswordFinish);
                    System.exit(0);
                    break;
                default:
                    System.out.println("Upps Something went wrong");
            }
        }

    }
}
