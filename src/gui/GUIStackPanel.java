package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUIStackPanel extends JPanel{
    public GUIStackPanel() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("STACK");
        title.setFont(new Font("Arial", Font.PLAIN, 20));
        title.setAlignmentX(CENTER_ALIGNMENT);

        JLabel des = new JLabel("Last-In-First-Out (LIFO) data structure.");
		des.setAlignmentX(CENTER_ALIGNMENT);

        JPanel container = new JPanel();
		container.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		JButton listButton = new JButton("Stack");
		container.add(listButton);

		// Add action listeners to the buttons
        listButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
				// Close the store window
				JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(GUIStackPanel.this);
				frame.dispose();

				// Create a new window
				new GUIStackFrame();
            }
        });
		
		this.add(Box.createVerticalGlue());
		this.add(title);
		this.add(des);
		this.add(Box.createVerticalGlue());
		this.add(container);		
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }
}
