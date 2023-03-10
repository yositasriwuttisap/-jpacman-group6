package nl.tudelft.jpacman.ui;

import nl.tudelft.jpacman.Launcher;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ImageSelector extends JFrame implements ActionListener {
    public JFrame frame;
    // instance variables
    private JLabel imageLabel;
    private int currentIndex = 0;
    private String[] imageFiles = new String[] {"src/main/resources/img/image0.png", "src/main/resources/img/image1.png", "src/main/resources/img/image2.png"
    ,"src/main/resources/img/image3.png","src/main/resources/img/image4.png"};

    private ImageIcon leftIcon, rightIcon, backIcon, playIcon;
    private JButton leftButton, rightButton, backButton, playButton;
    private int selectedLevel;

    // constructor
    public ImageSelector() {
        super("Map JPacman");

        // create UI components
        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel buttonPanel = new JPanel(new BorderLayout());
        JPanel arrowPanel = new JPanel(new GridLayout(1, 2));
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel imagePanel = new JPanel(new BorderLayout());
        JPanel containerImage = new JPanel(new BorderLayout());
        imageLabel = new JLabel();

        // create image icons and buttons
        leftIcon = new ImageIcon("src/main/resources/img/left-arrow.png");
        rightIcon = new ImageIcon("src/main/resources/img/right-arrow.png");
        backIcon = new ImageIcon("src/main/resources/img/back-button.png");
        playIcon = new ImageIcon("src/main/resources/img/start_button_128_49.png");
        leftButton = new JButton(leftIcon);
        rightButton = new JButton(rightIcon);
        //backButton = new JButton(backIcon);
        playButton = new JButton(playIcon);
        playButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Launch game with selected level
                // Launch game with selected level
                System.out.println("Launching game with level " + currentIndex);

                String levelName = "/board" + currentIndex + ".txt";
                System.out.println(levelName);
                Launcher.board(levelName);

                // Close the ImageSelector window
                dispose();

                // Open the Launcher window
                Launcher.launch();
            }
        });


        // create empty border with a 10-pixel right margin

        // set button properties
        leftButton.addActionListener(this);
        rightButton.addActionListener(this);
        leftButton.setFocusPainted(false);
        rightButton.setFocusPainted(false);
        leftButton.setContentAreaFilled(false);
        rightButton.setContentAreaFilled(false);
        //backButton.setContentAreaFilled(false); // make button transparent
        //backButton.setFocusPainted(false); // remove focus border
        playButton.setContentAreaFilled(false); // make button transparent
        playButton.setFocusPainted(false); // remove focus border

        // set back button and play button size
        //backButton.setPreferredSize(new Dimension(128, 48));
        playButton.setPreferredSize(new Dimension(128, 48));

        containerImage.setBorder(BorderFactory.createMatteBorder(
            5, 5, 0, 5, Color.white));

        arrowPanel.setBorder(BorderFactory.createMatteBorder(
            0, 5, 5, 5, Color.white));
        // add components to panels
        arrowPanel.add(leftButton);
        arrowPanel.add(rightButton);
        //bottomPanel.add(backButton);

        Dimension minSize = new Dimension(20, 0);
        Dimension prefSize = new Dimension(20, 0);
        Dimension maxSize = new Dimension(Short.MAX_VALUE, 100);
        bottomPanel.add(new Box.Filler(minSize, prefSize, maxSize));

        bottomPanel.add(playButton);
        buttonPanel.add(arrowPanel, BorderLayout.CENTER);
        buttonPanel.add(bottomPanel, BorderLayout.SOUTH);
        imagePanel.add(imageLabel, BorderLayout.CENTER);

        // set background color of panels
        mainPanel.setBackground(new Color(32, 13, 41));
        buttonPanel.setBackground(new Color(32, 13, 41));
        arrowPanel.setBackground(new Color(32, 13, 41));
        bottomPanel.setBackground(new Color(32, 13, 41));
        imagePanel.setBackground(new Color(32, 13, 41));
        containerImage.setBackground(new Color(32, 13, 41));

        // set background color of arrow panel and button on arrow panel
        arrowPanel.setBackground(Color.decode("#200D29"));
        leftButton.setBackground(Color.decode("#200D29"));
        rightButton.setBackground(Color.decode("#200D29"));

        // remove border button
        //backButton.setBorderPainted(false);
        playButton.setBorderPainted(false);
        leftButton.setBorderPainted(false);
        rightButton.setBorderPainted(false);

        // set padding
        imagePanel.setBorder(new EmptyBorder(50, 50, 50, 50));
        containerImage.add(imagePanel, BorderLayout.CENTER);

        mainPanel.add(containerImage, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // set frame properties
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        setSize(600, 800);
        setVisible(true);

        // set initial image
        setImage(currentIndex);
    }


    // event handler for button clicks
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton) {
            currentIndex--;
            if (currentIndex < 0) {
                currentIndex = imageFiles.length - 1;
            }
        } else if (e.getSource() == playButton) {
            currentIndex++;
            if (currentIndex >= imageFiles.length) {
                currentIndex = 0;
            }
        } else if (e.getSource() == leftButton) {
            currentIndex--;
            if (currentIndex < 0) {
                currentIndex = imageFiles.length - 1;
            }
        } else if (e.getSource() == rightButton) {
            currentIndex++;
            if (currentIndex >= imageFiles.length) {
                currentIndex = 0;
            }
        }
        setImage(currentIndex);

    }



    // method to set the displayed image
    private void setImage(int index) {
        ImageIcon imageIcon = new ImageIcon(imageFiles[index]);
        Image image = imageIcon.getImage().getScaledInstance(imageLabel.getWidth(), imageLabel.getHeight(), Image.SCALE_SMOOTH);
        imageLabel.setIcon(new ImageIcon(image));
    }

    // main method
    public static void main(String[] args) {
        new ImageSelector();
    }


}
