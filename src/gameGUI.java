import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
	
public class gameGUI extends JFrame implements ActionListener 
{

	private JButton startButton;
	private JButton actButton;
	private JButton resetButton;
	private JPanel topPanel;
	private JPanel rightPanel;
	private JPanel bottomPanelLeft;
	private JPanel bottomPanelRight;
	
	//Game frame size
	public static void main(String[] args) {
	    gameGUI frame = new gameGUI();
	    frame.setSize(875, 600);
	    frame.createGUI();
	    frame.show();
	}

	private void createGUI() {
	    setDefaultCloseOperation(EXIT_ON_CLOSE);
	    Container window = getContentPane();
	    window.setLayout(new FlowLayout() );

	    //Top Panel
	    topPanel = new JPanel();
	    topPanel.setPreferredSize(new Dimension(550, 500));
	    topPanel.setBackground(Color.WHITE);
	    window.add(topPanel);
	        
	    //Right Panel 1
	    rightPanel = new JPanel();
	    rightPanel.setPreferredSize(new Dimension(200, 500));
	    rightPanel.setBackground(Color.ORANGE);
	    window.add(rightPanel);
	        
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
	}
}





