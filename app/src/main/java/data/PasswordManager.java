package data;

import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;
import java.util.List;

public class PasswordManager {

    List<PasswordEntry> entrys = new ArrayList<>();

    public void addPassword(String website, String username, String password){
        PasswordEntry entry = new PasswordEntry(website, username, password);
        entrys.add(entry);
        System.out.println("Passwort Saved");
    }
}
