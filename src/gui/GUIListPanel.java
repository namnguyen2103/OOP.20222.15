package gui;

import javax.swing.*;

import datastructure.List;

import java.awt.*;
import java.awt.event.*;

public class GUIListPanel extends JPanel 
{

	
	public GUIListPanel() 
	{
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JLabel title = new JLabel("LIST");
		title.setFont(new Font("Arial", Font.PLAIN, 20));
		title.setAlignmentX(CENTER_ALIGNMENT);
		
		JLabel des = new JLabel("List data structure.");
		des.setAlignmentX(CENTER_ALIGNMENT);
		
		JPanel container = new JPanel();
		container.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		JButton listButton = new JButton("List");
		container.add(listButton);
		
		// Add action listeners to the buttons
        listButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
				// Close the store window
				JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(GUIListPanel.this);
				frame.dispose();

				// Create a new window
				new GUIQueueFrame();
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
