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
	
	private JButton upButton;
	private JButton downButton;
	private JButton leftButton;
	private JButton rightButton;
	private JButton blankButton1;
	private JButton blankButton2;
	private JButton blankButton3;
	private JButton blankButton4;
	private JButton blankButton5;
	
	//Button icon
	private Icon iconAct;
	private Icon iconStart;
	private Icon iconReset;
	private Icon iconSand;
	private Icon iconBall;
	//private Icon iconStart;
	
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
	public static void main(String[] args) 
	{
	    gameGUI frame = new gameGUI();
	    frame.setSize(875, 600);
	    frame.createGUI();
	    frame.show();
	}
	
	//Icon size
	/*public static void main(String[] args) 
	{
	    iconAct frame = new iconAct();
	    frame.setSize(600, 700);
	    frame.bottomPanel();
	    frame.setVisible(true);
	}*/

	//Game layout and design, functions
	private void createGUI() {
	    setDefaultCloseOperation(EXIT_ON_CLOSE);
	    Container window = getContentPane();
	    window.setLayout(new FlowLayout() );
	    
	    //Top Panel
	    topPanel = new JPanel();
	    topPanel.setLayout(new GridLayout(13, 16) );
	    topPanel.setPreferredSize(new Dimension(550, 500));
	    topPanel.setBackground(Color.WHITE);
	    window.add(topPanel);
	    
	    //Top Panel Grid Layout
	    for(int nCount=0; nCount<207; nCount++) 
	    {
	    	numberButton = new JButton(""+nCount);
	    	//numberButton.setBorderPainted(false);
	    if(nCount<19)
	    {
	    		iconSand = new ImageIcon(Toolkit.getDefaultToolkit().createImage(gameGUI.class.getResource("sand.jpg")));
		    	numberButton.setIcon(iconSand);
		}
	    
	    if(nCount==20)
	    {
    		iconBall = new ImageIcon(Toolkit.getDefaultToolkit().createImage(gameGUI.class.getResource("sand60x60.png")));
	    	numberButton.setIcon(iconBall);
	    }
	    
		if(nCount==90)
		{
			numberButton.setForeground(Color.ORANGE);
		}
		
			topPanel.add(numberButton);
			numberButton.addActionListener(this);
	    }
	    
	    //Icons
	    //Act Icon
	    try
	    {
        	iconAct = new ImageIcon(Toolkit.getDefaultToolkit().createImage(gameGUI.class.getResource("step.png")));
        }
	    catch (Exception e)
        {
            System.err.println("Baby Icon ImageIcon "+e);
        }
	    
	    //Start Icon
	    try
	    {
        	iconStart = new ImageIcon(Toolkit.getDefaultToolkit().createImage(gameGUI.class.getResource("run.png")));
        }
	    catch (Exception e)
        {
            System.err.println("Baby Icon ImageIcon "+e);
        }
	    
	    //Reset Icon
	    try
	    {
        	iconReset = new ImageIcon(Toolkit.getDefaultToolkit().createImage(gameGUI.class.getResource("reset.png")));
        }
	    catch (Exception e)
        {
            System.err.println("Baby Icon ImageIcon "+e);
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
	    //bottomPanelLeft.setBackground(Color.RED);
	    window.add(bottomPanelLeft);
	    
	    //Bottom Panel Right
	    bottomPanelRight = new JPanel();
	    bottomPanelRight.setPreferredSize(new Dimension(377, 50));
	    bottomPanelRight.setBackground(Color.GREEN);
	    window.add(bottomPanelRight);

	    //Start button on bottom left Panel
	    actButton = new JButton("Act");
        actButton.setIcon(iconAct);
	    bottomPanelLeft.add(actButton);
	    actButton.addActionListener(this);
	    
	    //Start button on bottom left Panel
	    startButton = new JButton("Start");
	    startButton.setIcon(iconStart);
	    bottomPanelLeft.add(startButton);
	    startButton.addActionListener(this);
	    
	    //Start button on bottom left Panel
	    resetButton = new JButton("Reset");
	    resetButton.setIcon(iconReset);
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