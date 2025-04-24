package data;

import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;

public class PasswordManager {

    List<PasswordEntry> entrys = new ArrayList<>();

    public void addPassword(String website, String username, String password){
        PasswordEntry entry = new PasswordEntry(website, username, password);
        entrys.add(entry);
        System.out.println("Passwort Saved");
    }

    public void showAllPasswords(){
        if (entrys.isEmpty()){
            System.out.println("Nothing to show");
        }    else {
            for (PasswordEntry ent : entrys) {
                System.out.println("Index " + entrys.indexOf(ent) + ": " + ent);
            }
        }
    }
    public void deletePassword(int index){
        if (index < 0 || index >= entrys.size()) {
            System.out.println("No Entrys");
        } else {
            PasswordEntry removed = entrys.remove(index);
            System.out.println("Gelöscht " + removed);
        }
    }








}
