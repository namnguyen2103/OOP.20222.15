package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUIListFrame extends JPanel {
	public GUIListFrame() {
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
				JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(GUIListFrame.this);
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
	
	JPanel createOperations() {
		JPanel operations = new JPanel();
		operations.setLayout(new GridLayout(1, 5, 10, 10));

		createBtn = new JButton("Create");
		createBtn.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{

		       JOptionPane.showMessageDialog(null, "A list has been created successfully!.");
		       list = new List();
		       changeText("The list is currently empty. Please insert elements.", "A list has been created!");
		       updateButtons();
		       createBtn.setEnabled(false);
		       insertBtn.setEnabled(true);
			}
		});
		operations.add(createBtn);
		
		insertBtn = new JButton("Insert");
		insertBtn.addActionListener(new ActionListener() 
		{
		    public void actionPerformed(ActionEvent e) 
		    {
		        boolean validInput = false;
		        int element = 0, id = 0;

		        while (!validInput) {
		            String elementInput = JOptionPane.showInputDialog(null, "Please input an integer for the element:", "Input the element", JOptionPane.QUESTION_MESSAGE);
		            String idInput = JOptionPane.showInputDialog(null, "Please input an integer for the ID:", "Input the ID", JOptionPane.QUESTION_MESSAGE);

		            if (elementInput == null || idInput == null) {
		                return;
		            }

		            try 
		            {
		                element = Integer.parseInt(elementInput);
		                id = Integer.parseInt(idInput);
		                validInput = true;
		            } catch (NumberFormatException ex) {
		                JOptionPane.showMessageDialog(null, "Invalid input! Please enter a valid integer.", "Error", JOptionPane.ERROR_MESSAGE);
		            }
		        }

		        JOptionPane.showMessageDialog(null, "The element has been inserted successfully!");
		        list.insert(element, id);

		        changeText("Current list: " + list.toString(), "The element " + element + " with ID " + id + " has been added!");

		        updateButtons();
		    }
		});
		operations.add(insertBtn);
		insertBtn.setEnabled(false);
		
		sortBtn = new JButton("Sort");
		sortBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				list.sort();
				JOptionPane.showMessageDialog(null, "The list has been sorted successfully!.");
				changeText("Current list: " + list.toString(), "The list has been sorted.");
				sortBtn.setEnabled(false);
			}
		});
		operations.add(sortBtn);
		sortBtn.setEnabled(false);
		
		findBtn = new JButton("Find");
		findBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean validInput = false;
				final int[] elementToFind = new int[1];
				
				while (!validInput) {
		            String input = JOptionPane.showInputDialog(null, "Please input an integer:", "Input the element", JOptionPane.QUESTION_MESSAGE);
		            
		            if (input == null) {
		                return;
		            }
		            try {
		            	elementToFind[0] = Integer.parseInt(input);
		                validInput = true;
		            } catch (NumberFormatException ex) {
		                JOptionPane.showMessageDialog(null, "Invalid input! Please enter a valid integer.", "Error", JOptionPane.ERROR_MESSAGE);
		            }
		        }
				String[] texts = list.find(elementToFind[0]);
				SwingUtilities.invokeLater(new Runnable() {
					@Override
		            public void run() {		            	
		                GUISlideshow slideshowGUI = new GUISlideshow(texts);
		                slideshowGUI.setVisible(true);
		            }
			    });				
				updateButtons();
				changeText("Current list: " + list.toString(), texts[texts.length-1]);
			}
		});
		operations.add(findBtn);
		findBtn.setEnabled(false);
		
		deleteBtn = new JButton("Delete");
		deleteBtn.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String idInput = JOptionPane.showInputDialog(null, "Please input the ID of the element to delete:", "Input ID", JOptionPane.QUESTION_MESSAGE);
		        if (idInput == null) 
		        {
		            return;
		        }

		        try 
		        {
		            int idToDelete = Integer.parseInt(idInput);
		            int deleted = list.delete(idToDelete);

		            if (deleted != -1) 
		            {
		                JOptionPane.showMessageDialog(null, "The element with ID " + idToDelete + " has been deleted successfully!");
		                if (!list.isEmpty()) 
		                {
		                    changeText("Current list: " + list.toString(), "The element with ID " + idToDelete + " has been deleted!");
		                } else {
		                    changeText("The list is currently empty. Please insert elements.", "The element with ID " + idToDelete + " has been deleted! The list is empty now.");
		                }
		                updateButtons();
		            } 
		            else
		            {
		                JOptionPane.showMessageDialog(null, "The element with ID " + idToDelete + " does not exist in the list!", "Error", JOptionPane.ERROR_MESSAGE);
		            }
		        } 
		        catch (NumberFormatException ex) 
		        {
		            JOptionPane.showMessageDialog(null, "Invalid input! Please enter a valid integer for the ID.", "Error", JOptionPane.ERROR_MESSAGE);
		        }
		    }
		});
		
		operations.add(deleteBtn);
		deleteBtn.setEnabled(false);
		
		resetBtn = new JButton("Reset");
		resetBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GUIListFrame.this.dispose();
            	new GUIListFrame();
			}
		});
		operations.add(resetBtn);		
		
		return operations;
	}	
}
