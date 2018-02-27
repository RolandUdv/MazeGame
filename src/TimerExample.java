import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
 
public class TimerExample extends JFrame implements ActionListener
{
    private JTextField secsField, minsField;
    private JLabel secsLabel, minsLabel;
    private int ticks = 0;
    private Timer timer;
 
    public static void main (String[] args)
    {
        TimerExample frame = new TimerExample();
        frame.setSize(300,100);
        frame.createGUI();
        frame.setVisible(true);
    }
 
    private void createGUI()
    {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Container window = getContentPane();
        window.setLayout(new FlowLayout() );
 
        minsLabel = new JLabel("Mins:  ");
        window.add(minsLabel);
 
        minsField = new JTextField(2);
        window.add(minsField);
 
        secsLabel = new JLabel("    Secs:   ");
        window.add(secsLabel);
 
        secsField = new JTextField(2);
        window.add(secsField);
        timer = new Timer(1000, this);
        timer.start();
    }
 
    public void actionPerformed(ActionEvent event)
    {
        minsField.setText(Integer.toString(ticks / 60));
        secsField.setText(Integer.toString(ticks % 60));
        ticks = ticks + 1;
    }
}