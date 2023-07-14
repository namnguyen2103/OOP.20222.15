package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUISlideshow extends JFrame {
    private JPanel slideshowPanel;
    private JButton playButton;
    private JButton pauseButton;
    private JButton backButton;
    private JButton nextButton;
    private JButton exitButton;
    private JLabel windowLabel;

    private String[] windows;
    private int currentWindowIndex;
    private boolean isPaused;

    public GUISlideshow(String[] windows) {
    	this.windows = windows;
    	
        setTitle("Slideshow");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        
        slideshowPanel = new JPanel();
        slideshowPanel.setLayout(new BorderLayout());
        
        windowLabel = new JLabel("<html><div style='text-align: center; vertical-align: middle;'>" + windows[currentWindowIndex].replace("<", "&lt;").replace(">", "&gt;") + "</div></html>", SwingConstants.CENTER);
        windowLabel.setFont(new Font("Arial", Font.BOLD, 16));
        slideshowPanel.add(windowLabel, BorderLayout.CENTER);
        
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());
        
        playButton = new JButton("Play");
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playSlideshow();
            }
        });
        controlPanel.add(playButton);
        
        pauseButton = new JButton("Pause");
        pauseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pauseSlideshow();
            }
        });
        controlPanel.add(pauseButton);
        pauseButton.setEnabled(false);
        
        backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showPreviousWindow();
            }
        });
        controlPanel.add(backButton);
        backButton.setEnabled(false);
        
        nextButton = new JButton("Next");
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showNextWindow();
            }
        });
        controlPanel.add(nextButton);
        
        exitButton = new JButton("Exit");
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the current window
            }
        });
        controlPanel.add(exitButton);
        
        add(slideshowPanel, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.SOUTH);
    }

    private void playSlideshow() {
        playButton.setEnabled(false);
        pauseButton.setEnabled(true);
        backButton.setEnabled(false);
        nextButton.setEnabled(false);
        isPaused = false;

        Thread slideshowThread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < windows.length; i++) {
                    final int index = i;
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            if (isPaused) {
                                return;
                            }
                            windowLabel.setText("<html><div style='text-align: center; vertical-align: middle;'>" + windows[index].replace("<", "&lt;").replace(">", "&gt;").replace("\n", "<br/>") + "</div></html>");
                            currentWindowIndex = index; 
                        }
                    });
                    try {
                        Thread.sleep(3000); 
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        playButton.setEnabled(true);
                        pauseButton.setEnabled(false);
                        updateButtonStates();
                    }
                });
            }
        });

        slideshowThread.start();
    }

    private void pauseSlideshow() {
        isPaused = true;
        pauseButton.setEnabled(false);
        playButton.setEnabled(true);
        backButton.setEnabled(true);
        nextButton.setEnabled(true);
    }

    private void showPreviousWindow() {
        currentWindowIndex--;
        if (currentWindowIndex < 0) {
            currentWindowIndex = 0; // Limit the index to 0 for the first slide
        }
        windowLabel.setText("<html><div style='text-align: center; vertical-align: middle;'>" + windows[currentWindowIndex].replace("<", "&lt;").replace(">", "&gt;").replace("\n", "<br/>") + "</div></html>");
        updateButtonStates(); // Update button states
    }

    private void showNextWindow() {
        currentWindowIndex++;
        if (currentWindowIndex >= windows.length) {
            currentWindowIndex = windows.length - 1; // Limit the index to the last slide
        }
        windowLabel.setText("<html><div style='text-align: center; vertical-align: middle;'>" + windows[currentWindowIndex].replace("<", "&lt;").replace(">", "&gt;").replace("\n", "<br/>") + "</div></html>");
        updateButtonStates(); // Update button states
    }

    private void updateButtonStates() {
        backButton.setEnabled(currentWindowIndex != 0); // Disable the Back button if it's the first slide
        nextButton.setEnabled(currentWindowIndex != windows.length - 1); // Disable the Next button if it's the last slide
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
            	String[] windows = {
                        "First you sort it like this \naaaaaaaa",
                        "Next you sort it like that",
                        "After that you will do these",
                        "Then you will do those",
                        "Finally we are done"
                };
                GUISlideshow slideshowGUI = new GUISlideshow(windows);
                slideshowGUI.setVisible(true);
            }
        });
    }
}

