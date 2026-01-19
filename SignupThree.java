package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class SignupThree extends JFrame implements ActionListener {

    JRadioButton r1, r2, r3, r4;
    JCheckBox c1, c2, c3, c4, c5, c6, c7;
    JButton submit, cancel;

    SignupThree(String formno) {
    // You can save formno if you want
    // long formNumber = Long.parseLong(formno); // optional

    setLayout(null);
    getContentPane().setBackground(Color.white);

    JLabel l1 = new JLabel("PAGE 3: ACCOUNT DETAILS");
    l1.setFont(new Font("Raleway", Font.BOLD, 22));
    l1.setBounds(260, 30, 400, 40);
    add(l1);

    // Account Type
    JLabel type = new JLabel("Account Type:");
    type.setFont(new Font("Raleway", Font.BOLD, 22));
    type.setBounds(100, 100, 200, 30);
    add(type);

    r1 = new JRadioButton("Saving Account");
    r1.setBounds(100, 140, 200, 25);
    r1.setBackground(Color.white);
    add(r1);

    r2 = new JRadioButton("Fixed Deposit Account");
    r2.setBounds(350, 140, 300, 25);
    r2.setBackground(Color.white);
    add(r2);

    r3 = new JRadioButton("Current Account");
    r3.setBounds(100, 180, 200, 25);
    r3.setBackground(Color.white);
    add(r3);

    r4 = new JRadioButton("Recurring Deposit Account");
    r4.setBounds(350, 180, 300, 25);
    r4.setBackground(Color.white);
    add(r4);

    ButtonGroup group = new ButtonGroup();
    group.add(r1);
    group.add(r2);
    group.add(r3);
    group.add(r4);

    // Card Details
    JLabel card = new JLabel("Card No:");
    card.setFont(new Font("Raleway", Font.BOLD, 22));
    card.setBounds(100, 240, 200, 30);
    add(card);

    JLabel number = new JLabel("XXXX-XXXX-XXXX-4184");
    number.setFont(new Font("Raleway", Font.BOLD, 20));
    number.setBounds(330, 240, 300, 30);
    add(number);

    JLabel pin = new JLabel("PIN:");
    pin.setFont(new Font("Raleway", Font.BOLD, 22));
    pin.setBounds(100, 310, 200, 30);
    add(pin);

    JLabel numberr = new JLabel("XXXX");
    numberr.setFont(new Font("Raleway", Font.BOLD, 20));
    numberr.setBounds(330, 310, 300, 30);
    add(numberr);

    // Services
    JLabel services = new JLabel("Services Required:");
    services.setFont(new Font("Raleway", Font.BOLD, 22));
    services.setBounds(100, 380, 250, 30);
    add(services);

    c1 = new JCheckBox("ATM");
    c1.setBounds(100, 420, 200, 30);
    c1.setBackground(Color.white);
    add(c1);

    c2 = new JCheckBox("Internet Banking");
    c2.setBounds(350, 420, 200, 30);
    c2.setBackground(Color.white);
    add(c2);

    c3 = new JCheckBox("Mobile Banking");
    c3.setBounds(100, 460, 200, 30);
    c3.setBackground(Color.white);
    add(c3);

    c4 = new JCheckBox("Email Alerts");
    c4.setBounds(350, 460, 200, 30);
    c4.setBackground(Color.white);
    add(c4);

    c5 = new JCheckBox("Cheque Book");
    c5.setBounds(100, 500, 200, 30);
    c5.setBackground(Color.white);
    add(c5);

    c6 = new JCheckBox("E-Statement");
    c6.setBounds(350, 500, 200, 30);
    c6.setBackground(Color.white);
    add(c6);

    c7 = new JCheckBox("I hereby declare that the above entered details are correct");
    c7.setBounds(100, 540, 600, 30);
    c7.setBackground(Color.white);
    add(c7);

    submit = new JButton("SUBMIT");
    submit.setBounds(250, 600, 120, 35);
    submit.setBackground(Color.black);
    submit.setForeground(Color.white);
    add(submit);

    cancel = new JButton("CANCEL");
    cancel.setBounds(400, 600, 120, 35);
    cancel.setBackground(Color.black);
    cancel.setForeground(Color.white);
    add(cancel);

    submit.addActionListener(this);
    cancel.addActionListener(this);

    setSize(850, 820);
    setLocation(350, 50);
    setUndecorated(true);
    setVisible(true);
}


    SignupThree() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == submit) {

            String accountType = null;
            if (r1.isSelected()) accountType = "Saving Account";
            else if (r2.isSelected()) accountType = "Fixed Deposit Account";
            else if (r3.isSelected()) accountType = "Current Account";
            else if (r4.isSelected()) accountType = "Recurring Deposit Account";

            if (accountType == null) {
                JOptionPane.showMessageDialog(this, "Select Account Type");
                return;
            }

            if (!c7.isSelected()) {
                JOptionPane.showMessageDialog(this, "Please confirm declaration");
                return;
            }

            Random random = new Random();
            String cardnumber = "" + (Math.abs(random.nextLong()) % 9000000000000000L + 1000000000000000L);
            String pinnumber = "" + (random.nextInt(9000) + 1000);

            String facility = "";
            if (c1.isSelected()) facility += "ATM ";
            if (c2.isSelected()) facility += "Internet Banking ";
            if (c3.isSelected()) facility += "Mobile Banking ";
            if (c4.isSelected()) facility += "Email Alerts ";
            if (c5.isSelected()) facility += "Cheque Book ";
            if (c6.isSelected()) facility += "E-Statement ";

            try {
                Conn conn = new Conn();
                String query =
                        "INSERT INTO signupthree VALUES ('1234','" +
                                accountType + "','" +
                                cardnumber + "','" +
                                pinnumber + "','" +
                                facility + "')";
                conn.s.executeUpdate(query);

                JOptionPane.showMessageDialog(this,
                        "Account Created Successfully\nCard No: " + cardnumber + "\nPIN: " + pinnumber);

                setVisible(false);

            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new SignupThree("");
    }
}
