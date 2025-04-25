package data;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;



public class PasswordManagerGUI {
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
                String website = websiteField.getText();
                String username = usernameField.getText();
                String password = passwordField.getText();

                outputArea.append("Website: " + website + ", Username: " + username + ", Password: " + password);

                websiteField.setText("");
                usernameField.setText("");
                passwordField.setText("");
            }
        });
        JButton showButton = new JButton("Show ALL Password");
        JButton deleteButton = new JButton("Delet Password");
        JButton saveButton = new JButton("Save & Exit");

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
