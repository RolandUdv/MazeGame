import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
	
public class gameGUI extends JFrame implements ActionListener 
{

	    private JButton button;
	    private JPanel topPanel;
	    private JPanel rightPanel;
	    private JPanel bottomPanel;

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
	        
	        //Right Panel
	        rightPanel = new JPanel();
	        rightPanel.setPreferredSize(new Dimension(200, 500));
	        rightPanel.setBackground(Color.ORANGE);
	        window.add(rightPanel);
	        
	        //Bottom Panel
	        bottomPanel = new JPanel();
	        bottomPanel.setPreferredSize(new Dimension(755, 50));
	        bottomPanel.setBackground(Color.RED);
	        window.add(bottomPanel);

	        //Button on Right Panel
	        button = new JButton("Press me");
	        rightPanel.add(button);
	        button.addActionListener(this);
	    }
	    
	    private JTextField secsField, minsField;
	    private JLabel secsLabel, minsLabel;
	    private int ticks = 0;
	    private Timer timer;		
	    
	    private void timeStart() {
	    setDefaultCloseOperation(EXIT_ON_CLOSE);
	    Container rightPanel = getContentPane();
	    rightPanel.setLayout(new FlowLayout() );
	        
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
    }
 
    public void timerAction(ActionEvent event)
    {
        minsField.setText(Integer.toString(ticks / 60));
        secsField.setText(Integer.toString(ticks % 60));
        ticks = ticks + 1;
    }
}


