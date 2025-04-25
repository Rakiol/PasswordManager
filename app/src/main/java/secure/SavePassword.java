package secure;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import data.*;

public class SavePassword {

    private PasswordManager passwordManager;

    public SavePassword(PasswordManager passwordManager) {
        this.passwordManager = passwordManager;
    }


    public void saveToFile(String masterPassword) {
        List<PasswordEntry> entrys = passwordManager.getEntrys();
        StringBuilder sb = new StringBuilder();

        // 1. Alle Einträge als CSV-Zeilen zusammenbauen
        for (PasswordEntry entry : entrys) {
            sb.append(entry.getWebsite()).append(";");
            sb.append(entry.getUsername()).append(";");
            sb.append(entry.getPassword()).append("\n");
        }

        try {
            // 2. Text verschlüsseln
            String encrypted = EncryptionHelper.encrypt(sb.toString(), masterPassword);

            // 3. In Datei schreiben
            FileWriter writer = new FileWriter("passwords.enc");
            writer.write(encrypted);
            writer.close();

            System.out.println("Alle Passwörter wurden gespeichert.");
        } catch (Exception e) {
            System.out.println("Fehler beim Speichern: " + e.getMessage());
        }
    }
}

