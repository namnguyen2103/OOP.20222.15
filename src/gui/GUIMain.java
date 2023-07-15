package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import gui.*;

public class GUIMain extends JFrame {

	JPanel createNorth() {
		JPanel north = new JPanel();
		north.setLayout(new BoxLayout(north, BoxLayout.Y_AXIS));
		north.add(createMenuBar());
		north.add(createHeader());
		return north;
	}
	
	JPanel createHeader() {
        JPanel header = new JPanel();
        header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));
        
        JLabel appNameLabel = new JLabel("Project 01");
        appNameLabel.setFont(new Font("Century Schoolbox", Font.BOLD, 30));
        appNameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        header.add(appNameLabel);

        JLabel appDescriptionLabel = new JLabel("Demonstration of basic operations on List, Stack, Queue");
        appDescriptionLabel.setFont(new Font("Century", Font.PLAIN, 14));
        appDescriptionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        header.add(appDescriptionLabel);
        return header;
    }
	
	JMenuBar createMenuBar() {
		JMenuBar menuBar = new JMenuBar();
        JMenu optionMenu = new JMenu("Option");
        
        JMenuItem mainMenuItem = new JMenuItem("Main Menu");
        mainMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	GUIMain.this.dispose();
            	new GUIMain();
            }
        });
        optionMenu.add(mainMenuItem); 
        
        JMenuItem helpMenuItem = new JMenuItem("Help");
        helpMenuItem.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) 
            {
            	SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        //Turn off metal's use of bold fonts
                        UIManager.put("swing.boldMetal", Boolean.FALSE);
                        new TextFieldDemo("Help").setVisible(true);
                    }
                });
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

	
	JPanel createCenter() {		
		JPanel center = new JPanel();
		center.setLayout(new GridLayout(1, 3, 10, 10));
		
		center.add(new GUIListFrame());
		center.add(new GUIStackPanel());
		center.add(new GUIQueuePanel());		
		return center;
	}
	
	public GUIMain() {
		Container cp = getContentPane();
		cp.setLayout(new BorderLayout());
		
		cp.add(createNorth(), BorderLayout.NORTH);
		cp.add(createCenter(), BorderLayout.CENTER);
		
		setVisible(true);
		setTitle("Project 01");
		setSize(900, 500);
		setLocationRelativeTo(null);
	}
	
	public static void main(String [] args) {
		new GUIMain();
	}
}
