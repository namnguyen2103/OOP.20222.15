package gui;

import javax.swing.*;

import datastructure.List;

import java.awt.*;
import java.awt.event.*;

public class GUIListFrame extends JPanel 
{
	private List list;
	private JTextField demolist;
	private JTextField statuslist;
	private JButton createBtn;
	private JButton insertBtn;
	private JButton sortBtn;
	private JButton findBtn;
	private JButton deleteBtn;
	private JButton resetBtn;
	
	public GUIListFrame() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JLabel title = new JLabel("LIST");
		title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 20));
		title.setAlignmentX(CENTER_ALIGNMENT);
		
		JLabel des = new JLabel("Stack description");
		des.setAlignmentX(CENTER_ALIGNMENT);
		
		JPanel container = new JPanel();
		container.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		JButton listButton = new JButton("List");
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
				stackFrame.setTitle("List");
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


	JPanel createOperations() 
	{
		JPanel operations = new JPanel();
		operations.setLayout(new GridLayout(1, 5, 10, 10));

		createBtn = new JButton("Create");
		createBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean validInput = false;
		        int capacity = 0;

		        while (!validInput) {
		            String input = JOptionPane.showInputDialog(null, "Enter a positive integer as the capacity:", "Input", JOptionPane.QUESTION_MESSAGE);
		            
		            if (input == null) {
		                return;
		            }
		            try {
		                capacity = Integer.parseInt(input);
		                if (capacity > 0) {
		                    validInput = true;
		                } else {
		                    JOptionPane.showMessageDialog(null, "Invalid input! Please enter a positive integer.", "Error", JOptionPane.ERROR_MESSAGE);
		                }
		            } catch (NumberFormatException ex) {
		                JOptionPane.showMessageDialog(null, "Invalid input! Please enter a valid positive integer.", "Error", JOptionPane.ERROR_MESSAGE);
		            }
		        }
		        
		        JOptionPane.showMessageDialog(null, "A queue has been created successfully!.");
		        list = new List();
		        changeText("The queue is currently empty. Please insert elements.", "A queue with size " + capacity + " has been created!");
		        updateButtons();
		        createBtn.setEnabled(false);
			}
		});
		operations.add(createBtn);
		
		insertBtn = new JButton("Insert");
		insertBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean validInput = false;
		        int element = 0;
				
				while (!validInput) {
		            String input = JOptionPane.showInputDialog(null, "Please input an integer:", "Input the element", JOptionPane.QUESTION_MESSAGE);
		            
		            if (input == null) {
		                return;
		            }
		            try {
		                element = Integer.parseInt(input);
		                validInput = true;
		            } catch (NumberFormatException ex) {
		                JOptionPane.showMessageDialog(null, "Invalid input! Please enter a valid integer.", "Error", JOptionPane.ERROR_MESSAGE);
		            }
		        }
				
				JOptionPane.showMessageDialog(null, "The element has been inserted successfully!.");
				queue.insert(element);
				if (!queue.isFull()) {
					changeText("Current queue: " + queue.toString(), "The element " + element + " has been added!");
				}
				else {
					changeText("Current queue: " + queue.toString(), "The element " + element + " has been added! The queue has reached its max capacity.");
				}
				updateButtons();
			}
		});
		operations.add(insertBtn);
		insertBtn.setEnabled(false);
		
		sortBtn = new JButton("Sort");
		sortBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				queue.sort();
				JOptionPane.showMessageDialog(null, "The queue has been sorted successfully!.");
				changeText("Current queue: " + queue.toString(), "The queue has been sorted.");
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
				changeText("Current queue: " + queue.toString(), texts[texts.length-1]);
			}
		});
		operations.add(findBtn);
		findBtn.setEnabled(false);
		
		deleteBtn = new JButton("Delete");
		deleteBtn.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {		        
		    	int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete the rear element?", "Confirmation", JOptionPane.YES_NO_OPTION);
	            if (result == JOptionPane.YES_OPTION) {
		            int deleted = queue.delete();
		            JOptionPane.showMessageDialog(null, "The element has been deleted successfully!.");
		            if (!queue.isEmpty()) {
						changeText("Current queue: " + queue.toString(), "The element " + deleted + " has been deleted!");
					}
					else {
						changeText("The queue is currently empty. Please insert elements.", "The element " + deleted + " has been deleted! The queue is empty now.");
					}
					updateButtons();
		        }
		    }
		});
		operations.add(deleteBtn);
		deleteBtn.setEnabled(false);
		
		resetBtn = new JButton("Reset");
		resetBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GUIQueueFrame.this.dispose();
            	new GUIQueueFrame();
			}
		});
		operations.add(resetBtn);		
		
		return operations;
	}	
	
	JPanel createMain()
	{
		JPanel queueDemonstration = new JPanel();
		queueDemonstration.setLayout(new BoxLayout(queueDemonstration, BoxLayout.Y_AXIS));
		
		String fontFamily = "Verdana";
        int fontStyle = Font.PLAIN;
        int fontSize = 20;
        Font font = new Font(fontFamily, fontStyle, fontSize);
		
		queueDemonstration.add(new JLabel("Current Queue: "));
		demoQueue = new JTextField(100);
		demoQueue.setFont(font);
		demoQueue.setEditable(false);
		queueDemonstration.add(demoQueue);
		
		queueDemonstration.add(new JLabel("Queue Status: "));
		statusQueue = new JTextField(100);
		statusQueue.setText("Please create a queue first!");
		statusQueue.setFont(font);
		statusQueue.setEditable(false);
		queueDemonstration.add(statusQueue);
		
		return queueDemonstration;
		
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
