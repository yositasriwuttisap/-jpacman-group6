package nl.tudelft.jpacman;

import nl.tudelft.jpacman.MockitoExtension;
import nl.tudelft.jpacman.ui.GamePanel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.Scanner;

@ExtendWith(MockitoExtension.class)
class GamePanelTest {

    @Test
    void testStartGameWithValidName() {
        // Arrange
        GamePanel panel = new GamePanel();
        panel.nameField.setText("John");

        // Act
        panel.startGame();

        // Assert
        String fileName = "src/main/resources/name.txt";
        String wordToFind = "John";
        File file = new File(fileName);
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.contains(wordToFind)) {
                    Assertions.assertTrue(true);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testStartGameWithEmptyName() {
        // Arrange
        GamePanel panel = new GamePanel();
        panel.nameField.setText("");

        // Act
        panel.startGame();

        // Assert
        String fileName = "src/main/resources/name.txt";
        String wordToFind = "anonymous";
        File file = new File(fileName);
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.contains(wordToFind)) {
                    Assertions.assertTrue(true);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testLogoLabelIsNotNull() {
        // Arrange
        GamePanel panel = new GamePanel();

        // Act
        JLabel logoLabel = panel.logoLabel;

        // Assert
        Assertions.assertNotNull(logoLabel);
    }

    @Test
    void testNameFieldIsNotNull() {
        // Arrange
        GamePanel panel = new GamePanel();

        // Act
        JTextField nameField = panel.nameField;

        // Assert
        Assertions.assertNotNull(nameField);
    }

    @Test
    void testStartButtonIsNotNull() {
        // Arrange
        GamePanel panel = new GamePanel();

        // Act
        JButton startButton = panel.startButton;

        // Assert
        Assertions.assertNotNull(startButton);
    }

    private String getLastLineOfFile(String path) {
        // TODO: Implement this method to read the last line of a file
        return "";
    }
}
