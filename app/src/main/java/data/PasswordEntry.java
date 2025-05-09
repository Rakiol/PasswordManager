package data;

public class PasswordEntry {

    String website;
    String username;
    String password;

    public PasswordEntry(String website, String username, String password) {
        this.website = website;
        this.username = username;
        this.password = password;

    }

    @Override
    public String toString(){
        return "Website: " + website + ", Benutzername: " + username + ", Passwort: " + password;
    }

    public String getPassword(){
        return password;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public String getWebsite(){
        return website;
    }
    public void setWebsite(String website){
        this.website = website;
    }
    public String getUsername(){
        return username;
    }
    public void setUsername(String username){
        this.username = username;
    }

}
