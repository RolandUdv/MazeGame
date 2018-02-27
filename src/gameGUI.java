import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
	
public class gameGUI extends JFrame implements ActionListener 
{
	//Button variables
	private JButton startButton;
	private JButton actButton;
	private JButton resetButton;
	private JButton exitButton;
	private JButton numberButton;
	private JButton optionButton1;
	private JButton optionButton2;
	private JButton optionButton3;
	
	//Panel variables
	private JPanel topPanel;
	private JPanel rightPanel;
	private JPanel bottomPanelLeft;
	private JPanel bottomPanelRight;
	
	//Timer variables
    private JTextField secsField, minsField;
    private JLabel secsLabel, minsLabel;
    private int ticks = 0;
    private Timer timer;
	
	//Game frame size
	public static void main(String[] args) {
	    gameGUI frame = new gameGUI();
	    frame.setSize(875, 600);
	    frame.createGUI();
	    frame.show();
	}

	//Game layout and design, functions
	private void createGUI() {
	    setDefaultCloseOperation(EXIT_ON_CLOSE);
	    Container window = getContentPane();
	    window.setLayout(new FlowLayout() );
	    
	    //Top Panel
	    topPanel = new JPanel();
	    topPanel.setLayout(new GridLayout(10, 10) );
	    topPanel.setPreferredSize(new Dimension(550, 500));
	    topPanel.setBackground(Color.WHITE);
	    window.add(topPanel);
	    
	    //Top Panel Grid Layout
	    for(int nCount=0; nCount<100; nCount++) 
	    {
	    	numberButton = new JButton(""+nCount);
	    if(nCount==9)
	    {
	    	numberButton.setForeground(Color.RED);
		}
		if(nCount==90)
		{
			numberButton.setForeground(Color.ORANGE);
		}
		
			topPanel.add(numberButton);
			numberButton.addActionListener(this);
	    }
	        
	    //Right Panel 1
	    rightPanel = new JPanel();
	    rightPanel.setPreferredSize(new Dimension(200, 500));
	    rightPanel.setBackground(Color.ORANGE);
	    window.add(rightPanel);
	    
        minsLabel = new JLabel("Mins:  ");
        rightPanel.add(minsLabel);
 
        minsField = new JTextField(2);
        rightPanel.add(minsField);
 
        secsLabel = new JLabel("    Secs:   ");
        rightPanel.add(secsLabel);
 
        secsField = new JTextField(2);
        rightPanel.add(secsField);
        timer = new Timer(1000, this);
        timer.start();
	        
	    //Bottom Panel Left
	    bottomPanelLeft = new JPanel();
	    bottomPanelLeft.setPreferredSize(new Dimension(377, 50));
	    bottomPanelLeft.setBackground(Color.RED);
	    window.add(bottomPanelLeft);
	    
	    //Bottom Panel Right
	    bottomPanelRight = new JPanel();
	    bottomPanelRight.setPreferredSize(new Dimension(377, 50));
	    bottomPanelRight.setBackground(Color.GREEN);
	    window.add(bottomPanelRight);

	    //Start button on bottom left Panel
	    actButton = new JButton("Act");
	    bottomPanelLeft.add(actButton);
	    actButton.addActionListener(this);
	    
	    //Start button on bottom left Panel
	    startButton = new JButton("Start");
	    bottomPanelLeft.add(startButton);
	    startButton.addActionListener(this);
	    
	    //Start button on bottom left Panel
	    resetButton = new JButton("Reset");
	    bottomPanelLeft.add(resetButton);
	    resetButton.addActionListener(this);
	    
	    //Option 1 button on bottom left Panel
	    optionButton1 = new JButton("Option 1");
	    optionButton1.setPreferredSize(new Dimension(85, 25));
	    rightPanel.add(optionButton1);
	    optionButton1.addActionListener(this);
	    
	    //Option 2 button on bottom left Panel
	    optionButton2 = new JButton("Option 2");
	    optionButton2.setPreferredSize(new Dimension(85, 25));
	    rightPanel.add(optionButton2);
	    optionButton2.addActionListener(this);
	    
	    //Option 3 button on bottom left Panel
	    optionButton3 = new JButton("Option 3");
	    optionButton3.setPreferredSize(new Dimension(85, 25));
	    rightPanel.add(optionButton3);
	    optionButton3.addActionListener(this);
	    
	    //Exit button on bottom left Panel
	    exitButton = new JButton("Exit");
	    exitButton.setPreferredSize(new Dimension(85, 25));
	    rightPanel.add(exitButton);
	    exitButton.addActionListener(this);
	}
	
    public void actionPerformed(ActionEvent e) 
    {
        //Exit program
        //System.exit(0);


	        minsField.setText(Integer.toString(ticks / 60));
	        secsField.setText(Integer.toString(ticks % 60));
	        ticks = ticks + 1;
	    }
	
}