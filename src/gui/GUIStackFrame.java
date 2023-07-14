package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUIStackFrame extends JPanel {
	public GUIStackFrame() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JLabel title = new JLabel("STACK");
		title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 20));
		title.setAlignmentX(CENTER_ALIGNMENT);
		
		JLabel des = new JLabel("Stack description");
		des.setAlignmentX(CENTER_ALIGNMENT);
		
		JPanel container = new JPanel();
		container.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		JButton listButton = new JButton("Stack");
		container.add(listButton);
		
		// Add action listeners to the buttons
        listButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
				// Close the store window
				JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(GUIStackFrame.this);
				frame.dispose();

				// Create a new window
				JFrame stackFrame = new JFrame();
				Container cp = stackFrame.getContentPane();
				cp.setLayout(new BorderLayout());

				cp.add(createMenuBar(), BorderLayout.NORTH);
				cp.add(new JPanel(), BorderLayout.CENTER); // Blank canvas to show animation
				cp.add(createOperations(), BorderLayout.SOUTH);

				stackFrame.setVisible(true);
				stackFrame.setTitle("Stack");
				stackFrame.setSize(1200, 400);
            }
        });

		this.add(Box.createVerticalGlue());
		this.add(title);
		this.add(des);
		this.add(Box.createVerticalGlue());
		this.add(container);
		
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
	}


	JPanel createOperations() {
		JPanel operations = new JPanel();
		operations.setLayout(new GridLayout(1, 5, 10, 10));

		JButton create = new JButton("Create");
		JButton insert = new JButton("Insert");
		JButton sort = new JButton("Sort");
		JButton find = new JButton("Find");
		JButton delete = new JButton("Delete");

		operations.add(create);
		operations.add(insert);
		operations.add(sort);
		operations.add(find);
		operations.add(delete);

		return operations;
	}


	JMenuBar createMenuBar() {
		JMenuBar menuBar = new JMenuBar();
        JMenu optionMenu = new JMenu("Option");
        
        JMenuItem helpMenuItem = new JMenuItem("Help");
        helpMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	JOptionPane.showMessageDialog(new JFrame("Help"), "Some detailed explanation of the project and its usage.");
            }
        });
        optionMenu.add(helpMenuItem);

        JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
				System.exit(0);
            }
        });
        optionMenu.add(exitMenuItem);        
        menuBar.add(optionMenu);
        menuBar.setLayout(new FlowLayout(FlowLayout.LEFT));
        return menuBar;
	}	
}
