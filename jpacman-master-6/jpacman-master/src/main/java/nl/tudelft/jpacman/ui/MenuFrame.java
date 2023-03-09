package nl.tudelft.jpacman.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

public class MenuFrame extends JFrame {

    private JTextField nameField;

    public MenuFrame() {
        setTitle("My Game Menu");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());

        // Add a label for the title
        JLabel titleLabel = new JLabel("Welcome to My Game");
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Add a panel for the form
        JPanel formPanel = new JPanel(new FlowLayout());
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JLabel nameLabel = new JLabel("Name:");
        nameField = new JTextField(20);
        nameField.setFont(new Font("Tahoma", Font.PLAIN, 14));
        formPanel.add(nameLabel);
        formPanel.add(nameField);

        mainPanel.add(formPanel, BorderLayout.CENTER);

        // Add a panel for the button
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton startButton = new JButton("Start");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startGame();
            }
        });
        buttonPanel.add(startButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        setContentPane(mainPanel);
    }

    private void startGame() {
        String name = nameField.getText();
        if (name.trim().isEmpty()) {
            name = "anonymous";
        }

        // write name to file
        try (PrintWriter out = new PrintWriter(new FileWriter("C:\\Users\\LENOVO\\Downloads\\jpacman-master-6\\jpacman-master\\src\\main\\resources\\name.txt", StandardCharsets.UTF_8,true))) {
            out.println(name);
        } catch (IOException e) {
            e.printStackTrace();
        }



        JFrame levelSelectFrame = new LevelSelect().frame;
        levelSelectFrame.setVisible(true);
        dispose();
    }



    public static void main(String[] args) {
        MenuFrame menuFrame = new MenuFrame();
        menuFrame.setVisible(true);
    }
}
