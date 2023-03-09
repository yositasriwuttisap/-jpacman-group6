package nl.tudelft.jpacman.ui;
import nl.tudelft.jpacman.Launcher;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LevelSelect {
    public JFrame frame;
    private JButton[] levelButtons;
    private int selectedLevel;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    LevelSelect window = new LevelSelect();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public LevelSelect() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblNewLabel = new JLabel("Select Level");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblNewLabel.setBounds(160, 24, 138, 25);
        frame.getContentPane().add(lblNewLabel);

        JPanel panel = new JPanel();
        panel.setBounds(55, 60, 328, 126);
        frame.getContentPane().add(panel);
        panel.setLayout(new GridLayout(0, 5, 10, 10));

        levelButtons = new JButton[5];
        for (int i = 0; i < 5; i++) {
            levelButtons[i] = new JButton("Level " + (i+1));
            levelButtons[i].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JButton button = (JButton) e.getSource();
                    for (int j = 0; j < levelButtons.length; j++) {
                        if (button == levelButtons[j]) {
                            selectedLevel = j+1;
                            levelButtons[j].setBackground(Color.GREEN);
                        } else {
                            levelButtons[j].setBackground(null);
                        }
                    }
                }
            });
            panel.add(levelButtons[i]);
        }

        JButton btnPlay = new JButton("Play");
        btnPlay.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Launch game with selected level
                System.out.println("Launching game with level " + selectedLevel);

                String levelName = "/board" + selectedLevel + ".txt";
                System.out.println(levelName);
                Launcher.board(levelName);
                Launcher.launch();

                // Close the level select window
                frame.dispose();
            }
        });

        btnPlay.setBounds(174, 208, 89, 23);
        frame.getContentPane().add(btnPlay);



    }
}
