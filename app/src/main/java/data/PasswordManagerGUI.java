package data;

import org.checkerframework.checker.units.qual.A;
import secure.SavePassword;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;


public class PasswordManagerGUI {

    private static PasswordManager pm = new PasswordManager();
    private static SavePassword savePassword;

    public static void main(String[] args){



        JFrame frame = new JFrame();
        frame.setTitle("Password Manager");
        frame.setSize(600,400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(3, 2));


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

        JButton deleteButton = new JButton("Delete Password");
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
                        outputArea.append("Password saved successfully. \n");
                    } catch (Exception ex) {
                        outputArea.append("Error saving passwords: " + ex.getMessage() + "\n");
                        return;
                    }

                    System.exit(0);
                } else {
                    outputArea.append("Save cancelled. No master password entered. \n");
                }
            }
        });

        inputPanel.add(websiteLabel);
        inputPanel.add(websiteField);

        inputPanel.add(usernameLabel);
        inputPanel.add(usernameField);

        inputPanel.add(passwordLabel);
        inputPanel.add(passwordField);

        frame.add(inputPanel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        buttonPanel.add(addButton);
        buttonPanel.add(showButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(saveButton);

        frame.add(buttonPanel, BorderLayout.SOUTH);

        JScrollPane scrollPane = new JScrollPane(outputArea);
        frame.add(scrollPane, BorderLayout.CENTER);



        frame.setVisible(true);
    }


}
