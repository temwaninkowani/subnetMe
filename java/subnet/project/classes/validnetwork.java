package subnet.project.classes;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Objects;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class validnetwork extends JFrame implements ActionListener{
JPanel panel2;
JLabel maintitle2;
JTable table;
JButton backBtn;
JScrollPane scrollPane;
List<Objects[]> validnetworks;
String[] columnnames;
helperMethods hMethods;
DefaultTableModel defaultTableModel;




public validnetwork(String input,int subnetBits,String ipClass){
    this.setSize(400,450);
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    this.setTitle("subnetMe");


    backBtn = new JButton("BACK");
    backBtn.setAlignmentX(CENTER_ALIGNMENT);
    backBtn.addActionListener(this);

      panel2 = new JPanel();
      panel2.setPreferredSize(new Dimension(400,450));
      panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
      panel2.setBackground(Color.DARK_GRAY);

      columnnames = new String[]{"CLASS","SUBNET MASK","NUM OF SUBNETS","HOSTS PER SUBNET"};
      defaultTableModel = new DefaultTableModel(columnnames,0);
      populateTable(input,subnetBits,ipClass);

      //retreive desired information.
      table = new JTable(defaultTableModel);
      scrollPane = new JScrollPane(table);
      scrollPane.setPreferredSize(new Dimension(300,350));
      scrollPane.setAlignmentX(CENTER_ALIGNMENT);

      maintitle2 = new JLabel("Subnet_Me");
      maintitle2.setBounds(0,0,100,100);
      maintitle2.setAlignmentX(CENTER_ALIGNMENT);


      panel2.add(maintitle2);
      panel2.add(scrollPane);
      panel2.add(Box.createVerticalStrut(30));
      panel2.add(backBtn);
      this.add(panel2);
      this.setVisible(true);
}


     private void populateTable(String input, int subnetBits, String ipClass) {
        String data = "";
        switch (ipClass) {
            case "Class A":
                data = helperMethods.performClassASubnetting(input, subnetBits);
                break;
            case "Class B":
                data = helperMethods.performClassBSubnetting(input, subnetBits);
                break;
            case "Class C":
                data = helperMethods.performClassCSubnetting(input, subnetBits);
                break;
            default:
                JOptionPane.showMessageDialog(this, "Unsupported IP Class: " + ipClass, "Error", JOptionPane.ERROR_MESSAGE);
                return;
        }

        addToTable(ipClass, data);
    }



    private void addToTable(String classType, String data) {
      String[] lines = data.split("\n");
      String subnetMask = lines[1].split(": ")[1];
      String numberOfSubnets = lines[2].split(": ")[1];
      String hostsPerSubnet = lines[3].split(": ")[1];

      defaultTableModel.addRow(new Object[]{classType, subnetMask, numberOfSubnets, hostsPerSubnet});
  }


@Override
public void actionPerformed(ActionEvent e) {
    this.dispose();
    new App();

  }
    
}
