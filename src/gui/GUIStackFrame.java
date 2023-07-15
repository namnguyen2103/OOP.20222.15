package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import datastructure.Stack;

public class GUIStackFrame extends JFrame {

	private Stack stack;
	private JTextField demoStack;
	private JTextField statusStack;
	private JButton createBtn;
	private JButton insertBtn;
	private JButton sortBtn;
	private JButton findBtn;
	private JButton deleteBtn;
	private JButton resetBtn;

	public GUIStackFrame() {
		Container cp = getContentPane();
		cp.setLayout(new BorderLayout());
		
		cp.add(CreateMain(), BorderLayout.CENTER); 
		cp.add(createOperations(), BorderLayout.SOUTH);

		setVisible(true);
		setTitle("Stack");
		setSize(1200, 400);
		setLocationRelativeTo(null);	
	}

	JPanel createOperations() {
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

		        JOptionPane.showMessageDialog(null, "A stack has been created successfully!.");
		        stack = new Stack(capacity);
		        changeText("The stack is currently empty. Please insert elements.", "A stack with size " + capacity + " has been created!");
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
				stack.insert(element);
				if (!stack.isFull()) {
					changeText("Current stack: " + stack.toString(), "The element " + element + " has been added!");
				}
				else {
					changeText("Current stack: " + stack.toString(), "The element " + element + " has been added! The stack has reached its max capacity.");
				}
				updateButtons();
			}
		});
		operations.add(insertBtn);
		insertBtn.setEnabled(false);
		
		sortBtn = new JButton("Sort");
		sortBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stack.sort();
				JOptionPane.showMessageDialog(null, "The stack has been sorted successfully!.");
				changeText("Current stack: " + stack.toString(), "The stack has been sorted.");
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
				
				String[] texts = stack.find(elementToFind[0]);
				SwingUtilities.invokeLater(new Runnable() {
					@Override
		            public void run() {		            	
		                GUISlideshow slideshowGUI = new GUISlideshow(texts);
		                slideshowGUI.setVisible(true);
		            }
			    });
				
				updateButtons();
				changeText("Current stack: " + stack.toString(), texts[texts.length-1]);
			}
		});
		operations.add(findBtn);
		findBtn.setEnabled(false);
		
		deleteBtn = new JButton("Delete");
		deleteBtn.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {		        
		    	int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete the top element?", "Confirmation", JOptionPane.YES_NO_OPTION);
	            if (result == JOptionPane.YES_OPTION) {
		            int deleted = stack.delete();
		            JOptionPane.showMessageDialog(null, "The element has been deleted successfully!.");
		            if (!stack.isEmpty()) {
						changeText("Current stack: " + stack.toString(), "The element " + deleted + " has been deleted!");
					}
					else {
						changeText("The stack is currently empty. Please insert elements.", "The element " + deleted + " has been deleted! The stack is empty now.");
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
				GUIStackFrame.this.dispose();
            	new GUIStackFrame();
			}
		});
		operations.add(resetBtn);		
		
		return operations;
	}
	
	JPanel CreateMain()
	{
		JPanel stackDemonstration = new JPanel();
		stackDemonstration.setLayout(new BoxLayout(stackDemonstration, BoxLayout.Y_AXIS));
		
		String fontFamily = "Verdana";
        int fontStyle = Font.PLAIN;
        int fontSize = 20;
        Font font = new Font(fontFamily, fontStyle, fontSize);
		
		stackDemonstration.add(new JLabel("Current Stack: "));
		demoStack = new JTextField(100);
		demoStack.setFont(font);
		demoStack.setEditable(false);
		stackDemonstration.add(demoStack);
		
		stackDemonstration.add(new JLabel("Stack Status: "));
		statusStack = new JTextField(100);
		statusStack.setText("Please create a stack first!");
		statusStack.setFont(font);
		statusStack.setEditable(false);
		stackDemonstration.add(statusStack);
		
		return stackDemonstration;
		
	}
	
	private void changeText(String str1, String str2) {
		demoStack.setText(str1);
		statusStack.setText(str2);
	}
	
	private void updateButtons() {
		if (stack.isEmpty()) {
			deleteBtn.setEnabled(false);
			sortBtn.setEnabled(false);
			findBtn.setEnabled(false);
		}
		else {
			deleteBtn.setEnabled(true);
			sortBtn.setEnabled(true);
			findBtn.setEnabled(true);
		}
		
		if (stack.isFull()) {
			insertBtn.setEnabled(false);
		}
		else {
			insertBtn.setEnabled(true);
		}
	}
}