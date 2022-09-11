/**
 * An up/down counter with a simple GUI.
 * 
 * @author Lynn Marshall
 * @version November 15, 2012
 */
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Observer;
import java.util.Observable;

public class Counter implements ActionListener
{
   /* The current value of the counter. */
   private int count;
   
   // The constants
   public static final int MINIMUM = 0;
   public static final int RESET_TO = 5;
   public static final int MAXIMUM = 10;
   
   /* A JTextField displays the current value of the counter. */
   private JTextField counterDisplay;
   
   /* The button that is clicked to increment the counter. */
   private JButton upButton;

   /* The button that is clicked to decrement the counter. */
   private JButton downButton;

   /* The button that is clicked to reset the counter. */
   private JButton resetButton;
   
   public Counter() {
      // model
      count = 0;
      
      // JFrame
      JFrame frame = new JFrame("Counter");
      Container contentPane = frame.getContentPane();
      contentPane.setLayout(new GridLayout(2, 1));
      
      // Top area (counterDisplay): current counter value
      counterDisplay = new JTextField();
      counterDisplay.setEditable(false);
      counterDisplay.setFont(new Font(null, Font.BOLD, 18));
      counterDisplay.setHorizontalAlignment(JTextField.RIGHT);
      contentPane.add(counterDisplay);
      
      /* Update the view to reflect the initial state of the model. */
      counterDisplay.setText("" + count); 
      
      // Bottom Area (buttonPanel): buttons
      
      // The Up, Down and Reset buttons are arranged horizontally in a JPanel
      JPanel buttonPanel = new JPanel();
      buttonPanel.setLayout(new GridLayout(1, 3));

      upButton = new JButton("Up");
      buttonPanel.add(upButton);

      downButton = new JButton("Down");
      buttonPanel.add(downButton);
      
      /* Initially the Down button is disabled. */
      downButton.setEnabled(false);      

      resetButton = new JButton("Reset");
      buttonPanel.add(resetButton);

      /* Place the buttons below the counter display. */
      contentPane.add(buttonPanel);
      
      // register buttons as listeners
      upButton.addActionListener(this); 
      downButton.addActionListener(this);
      resetButton.addActionListener(this);     

      // finish setting up the frame
      frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
      frame.pack();
      frame.setResizable(false);
      frame.setVisible(true);
   }
   
   /** This action listener is called when the user clicks on 
    * any of the GUI's buttons. 
    */
    public void actionPerformed(ActionEvent e)
    {
        JButton button = (JButton)e.getSource();
        
        if (button == downButton) {
            count--;
        } else if (button == upButton) {
            count++;
        } else if (button == resetButton) {
            count = RESET_TO;
        }
        
        // Ensure that the right buttons are enabled.
        downButton.setEnabled(count != MINIMUM);
        upButton.setEnabled(count != MAXIMUM);
        resetButton.setEnabled(count != RESET_TO);
        
        // update the display
        counterDisplay.setText("" + count); 
   }    
}