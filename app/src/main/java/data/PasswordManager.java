package data;

import secure.EncryptionHelper;

import java.awt.image.AreaAveragingScaleFilter;
import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;

public class PasswordManager {

    List<PasswordEntry> entrys = new ArrayList<>();

    public void addPassword(String website, String username, String password) {
        PasswordEntry entry = new PasswordEntry(website, username, password);
        entrys.add(entry);
        System.out.println("Passwort Saved");
    }

    public void showAllPasswords() {
        if (entrys.isEmpty()) {
            System.out.println("Nothing to show");
        } else {
            for (PasswordEntry ent : entrys) {
                System.out.println("Index " + entrys.indexOf(ent) + ": " + ent);
            }
        }
    }

    public void deletePassword(int index) {
        if (index < 0 || index >= entrys.size()) {
            System.out.println("No Entrys");
        } else {
            PasswordEntry removed = entrys.remove(index);
            System.out.println("Gelöscht " + removed);
        }
    }

    public List<PasswordEntry> getEntrys() {
        return entrys;
    }

    public void loadFromFile(String masterPassword) {
        try {
            File file = new File("passwords.enc");
            if (!file.exists()) {
                System.out.println("no saved Passwords");
                return;
            }

            // Reade file
            String encrypted = new String(Files.readAllBytes(file.toPath()));

            // decrypt
            String decrypt = EncryptionHelper.decrypt(encrypted, masterPassword);

            // parse file
            String[] lines = decrypt.split("\n");
            for (String line : lines) {
                String[] parts = line.split(";");
                if (parts.length == 3) {
                    PasswordEntry entry = new PasswordEntry(parts[0], parts[1], parts[2]);
                    entrys.add(entry);
                }
            }
            System.out.println("Passwords loaded");

        } catch (Exception e) {
            System.out.println("Load mistake: " + e.getMessage());
        }

    }
}









