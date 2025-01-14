package subnet.project.classes;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.formdev.flatlaf.FlatDarculaLaf;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class App extends JFrame implements ActionListener{
   JLabel maintitle;
   JPanel panel;
   JTextField textField;
   JButton actionButton;
   JTextArea infotext;
   String invitationText = "welcome to subnet_me" + "\n" + "enter network to below to have it subnetted";

    App(){
      FlatDarculaLaf.setup();

      this.setSize(400,400);
      this.setDefaultCloseOperation(EXIT_ON_CLOSE);
      this.setTitle("subnetMe");
      this.setResizable(false);

      panel = new JPanel();
      panel.setPreferredSize(new Dimension(400,450));
      panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
      panel.setBackground(Color.DARK_GRAY);
      
      

      maintitle = new JLabel("Subnet_Me");
      maintitle.setBounds(0,0,100,100);
      maintitle.setAlignmentX(CENTER_ALIGNMENT);

      infotext = new JTextArea(invitationText);
      infotext.setMaximumSize(new Dimension(500,50));
      infotext.setAlignmentX(Component.CENTER_ALIGNMENT);
      infotext.setEditable(false);
      infotext.setFocusable(false);
      
      textField = new JTextField();
      textField.setMaximumSize(new Dimension(200,30));
      textField.setAlignmentX(CENTER_ALIGNMENT);
      textField.setFocusable(true);
      textField.putClientProperty("JTextField.placeholderText", "172.18.30.16");


      actionButton = new JButton("subnet");
      actionButton.setAlignmentX(CENTER_ALIGNMENT);
      actionButton.addActionListener(this);

      panel.add(maintitle);
      panel.add(Box.createVerticalStrut(50));
      panel.add(infotext);
      panel.add(Box.createVerticalStrut(50));
      panel.add(textField);
      panel.add(Box.createVerticalStrut(30));
      panel.add(actionButton);
      this.add(panel);
      this.setVisible(true);

    }


    public static boolean isDecimalNotation(String input){
          String ipv4Pattern = "^((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$";

          return input.matches(ipv4Pattern);
    }

    public static String identifyClass(String input){
        if(!isDecimalNotation(input)){
            return "invalid ip address";
            //replace with pop up message
        }

        //split the ip address into octets
        String[] octets = input.split("\\.");
        int firstOctet = Integer.parseInt(octets[0]);

         // Determine the class
    if (firstOctet >= 1 && firstOctet <= 126) {
      //perform class A operation.
      return "Class A";
  } else if (firstOctet >= 128 && firstOctet <= 191) {
      //perform class B operation.
      return "Class B";
  } else if (firstOctet >= 192 && firstOctet <= 223) {
      //perform class C operation
      return "Class C";
  } else {
      //show error dialog
      return "Not Class A, B, or C (e.g., Class D/E)";
  }


    }

    


    @Override
    public void actionPerformed(ActionEvent e) {
      String input = textField.getText();

      if (!isDecimalNotation(input)) {
          javax.swing.JOptionPane.showMessageDialog(this, "Invalid IP Address. Please enter a valid IP.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
      } else {
          String ipClass = identifyClass(input);
  
          int result = javax.swing.JOptionPane.showConfirmDialog(
              this,
              "Valid IP Address. IP Class: " + ipClass + "\nProceed to the next page?",
              "Success",
              javax.swing.JOptionPane.OK_CANCEL_OPTION,
              javax.swing.JOptionPane.INFORMATION_MESSAGE
          );
  
          if (result == javax.swing.JOptionPane.OK_OPTION) {
              this.dispose(); // Close the current window
  
              // Assuming a default of 4 subnet bits, modify if user input is required
              int subnetBits = 4;
              new validnetwork(input, subnetBits, ipClass);
          }
      }
      
        
    }
}
