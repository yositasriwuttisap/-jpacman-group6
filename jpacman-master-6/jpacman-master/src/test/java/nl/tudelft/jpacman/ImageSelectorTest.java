package nl.tudelft.jpacman;

import nl.tudelft.jpacman.Launcher;
import nl.tudelft.jpacman.ui.ImageSelector;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

public class ImageSelectorTest {

    private String levelName;

    @Test
    void testLeftButton() {
        ImageSelector imageSelector = new ImageSelector();
        int initialIndex = imageSelector.currentIndex;
        imageSelector.leftButton.doClick();
        assertEquals(4, imageSelector.currentIndex);
        assertNotNull(imageSelector.imageLabel.getIcon());
        assertNotEquals(initialIndex, imageSelector.currentIndex);
    }
    @Test
    void testRightButton() {
        ImageSelector imageSelector = new ImageSelector();
        int initialIndex = imageSelector.currentIndex;
        imageSelector.rightButton.doClick();
        assertEquals(1, imageSelector.currentIndex);
        assertNotNull(imageSelector.imageLabel.getIcon());
        assertNotEquals(initialIndex, imageSelector.currentIndex);
    }
    @Test
    void testPlayButtonWrapAround() {
        ImageSelector imageSelector = new ImageSelector();
        int initialIndex = imageSelector.currentIndex;
        imageSelector.currentIndex = imageSelector.imageFiles.length - 1;
        imageSelector.playButton.doClick();
        assertEquals(4, imageSelector.currentIndex);
        assertNotNull(imageSelector.imageLabel.getIcon());
        assertNotEquals(initialIndex, imageSelector.currentIndex);
    }
    @Test
    void testPlayButtonActionListener() {
        ImageSelector imageSelector = new ImageSelector();
        levelName = imageSelector.levelName;
        // Simulate a play button click
        imageSelector.playButton.doClick();

        // Verify that the expected game level is launched
        String expectedLevelName = "/board" + imageSelector.currentIndex + ".txt";
        Assertions.assertEquals(expectedLevelName, levelName);
    }
}
