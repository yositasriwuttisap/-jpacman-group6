package nl.tudelft.jpacman.ui;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

public class GamePanel extends JPanel {
    private JLabel logoLabel;
    private JTextField nameField;
    private JButton startButton;

    public GamePanel() {
        setLayout(new GridBagLayout());

        setBackground(new Color(32, 13, 41));
        // Create the logo label and add it to the panel
        ImageIcon logoIcon = new ImageIcon("src/main/resources/img/jpacicon_400_221.png");
        setSize(800, 800);
        logoLabel = new JLabel(logoIcon);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.NORTH;
        add(logoLabel, gbc);

        // Create the name input field and add it to the panel
        nameField = new JTextField(20);
        nameField.setFont(new Font("Tahoma", Font.PLAIN, 20));
        nameField.setPreferredSize(new Dimension(200, 30));
        Border border = BorderFactory.createDashedBorder(Color.white, 3, 2, 1, false);
        nameField.setBorder(border);
        nameField.setBackground(new Color(48, 35, 174));
        nameField.setOpaque(true); // Set the text field to be opaque
        nameField.setForeground(Color.white); // Set the foreground color of the text field
        nameField.setText(""); // Set the placeholder text of the text field
        nameField.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(nameField);


        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(10, 0, 0, 0);
        add(nameField, gbc);

        // Create the start game button and add it to the panel
        ImageIcon startIcon = new ImageIcon("src/main/resources/img/play-button.png");
        startButton = new JButton(startIcon);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startGame();
                ImageSelector selector = new ImageSelector();
                selector.setVisible(true);
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(GamePanel.this);
                frame.dispose();
            }
        });
        buttonPanel.add(startButton);


        startButton.setPreferredSize(new Dimension(128, 49));
        startButton.setBackground(new Color(32, 13, 41));
        startButton.setBorderPainted(false); // Remove the button border
        startButton.setContentAreaFilled(false);
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.insets = new Insets(20, 0, 0, 0);
        add(startButton, gbc);

    }


    private void startGame() {
        String name = nameField.getText();
        if (name.trim().isEmpty()) {
            name = "anonymous";
        }

        // Get current date and time
        String dateTime = java.time.LocalDateTime.now().toString();

        // write name and date/time to file
        try (PrintWriter out = new PrintWriter(new FileWriter("src/main/resources/name.txt", StandardCharsets.UTF_8, true))) {
            out.println(name + "," + dateTime);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Menu JPacman");
        GamePanel gamePanel = new GamePanel();
        frame.getContentPane().add(gamePanel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}


