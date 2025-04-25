package data;

import org.checkerframework.checker.units.qual.A;
import secure.SavePassword;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.JTextArea;



public class PasswordManagerGUI {

    private static PasswordManager pm = new PasswordManager();
    private static SavePassword savePassword;

    public static void main(String[] args){



        JFrame frame = new JFrame();
        frame.setTitle("Password Manager");
        frame.setSize(600,400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        JTextArea outputArea = new JTextArea(10, 40);
        outputArea.setEditable(false);

        JLabel websiteLabel = new JLabel("Website");
        JTextField websiteField = new JTextField(20);

        JLabel usernameLabel = new JLabel("Username");
        JTextField usernameField = new JTextField(20);

        JLabel passwordLabel = new JLabel("Password");
        JTextField passwordField = new JTextField(20);

        JButton addButton = new JButton("Add Password");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                outputArea.setText("");
                String website = websiteField.getText();
                String username = usernameField.getText();
                String password = passwordField.getText();

                pm.addPassword(website, username, password);

                outputArea.append("Password added for " + website + "\n");

                websiteField.setText("");
                usernameField.setText("");
                passwordField.setText("");
            }

        });

        JButton showButton = new JButton("Show ALL Password");
        showButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                outputArea.setText("");

                String masterPassword = javax.swing.JOptionPane.showInputDialog("Enter master password to load passwords:");

                if (masterPassword != null && !masterPassword.isEmpty()) {
                    try {
                        pm.loadFromFile(masterPassword);

                        outputArea.setText("");

                        List<PasswordEntry> allEntrys = pm.getAllPasswords();
                        if (allEntrys.isEmpty()) {
                            outputArea.append("No saved Passwords");
                        } else {
                            for (PasswordEntry entry : allEntrys) {
                                outputArea.append(allEntrys.indexOf(entry) + " : " + entry.toString() + "\n");
                            }
                        }
                    } catch (Exception ex) {
                        outputArea.append("Error loading passwords: " + ex.getMessage() + "\n");
                    }
                } else {
                    outputArea.append("Load cancelled. No master password entered.\n");
                }
            }
        });

        JButton deleteButton = new JButton("Delet Password");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                outputArea.setText("");
                List<PasswordEntry> allEntrys = pm.getAllPasswords();
                if (allEntrys.isEmpty()){
                    outputArea.append("No Passwords");
                } else {
                    for (PasswordEntry entry : allEntrys) {
                        outputArea.append(allEntrys.indexOf(entry) + " : " + entry.toString() + "\n");
                    }
                }
                String input = javax.swing.JOptionPane.showInputDialog("Enter the number of the password to delete:");
                try {
                    int index = Integer.parseInt(input);
                    pm.deletePassword(index);
                } catch (NumberFormatException ex) {
                    outputArea.append("Invalid Input");
                } catch (Exception ex) {
                    outputArea.append("Error: " + ex.getMessage() + "\n");
                }
            }
        });


        JButton saveButton = new JButton("Save & Exit");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                savePassword = new SavePassword(pm);
                String masterPassword = javax.swing.JOptionPane.showInputDialog("Enter master password to save:");
                if (masterPassword != null && !masterPassword.isEmpty()) {
                    try {
                        savePassword.saveToFile(masterPassword);
                        outputArea.append("Password saed successfully. \n");
                    } catch (Exception ex) {
                        outputArea.append("Error saving passwords: " + ex.getMessage() + "\n");
                        return;
                    }

                    System.exit(0);
                } else {
                    outputArea.append("Saved cancelled. No master password entered. \n");
                }
            }
        });

        frame.add(websiteLabel);
        frame.add(websiteField);

        frame.add(usernameLabel);
        frame.add(usernameField);

        frame.add(passwordLabel);
        frame.add(passwordField);

        frame.add(addButton);
        frame.add(showButton);

        frame.add(deleteButton);
        frame.add(saveButton);

        frame.add(outputArea);


        frame.setVisible(true);
    }





}
