package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class ChangePin extends JFrame implements ActionListener {

    JPasswordField oldPin, newPin, confirmPin;
    JButton change, back;
    String pinnumber;

    ChangePin(String pinnumber) {
        this.pinnumber = pinnumber;

        setLayout(null);

        JLabel title = new JLabel("CHANGE PIN");
        title.setFont(new Font("System", Font.BOLD, 18));
        title.setBounds(150, 30, 200, 30);
        add(title);

        JLabel l1 = new JLabel("Old PIN:");
        l1.setBounds(50, 80, 150, 25);
        add(l1);

        JLabel l2 = new JLabel("New PIN:");
        l2.setBounds(50, 120, 150, 25);
        add(l2);

        JLabel l3 = new JLabel("Confirm PIN:");
        l3.setBounds(50, 160, 150, 25);
        add(l3);

        oldPin = new JPasswordField();
        oldPin.setBounds(200, 80, 150, 25);
        add(oldPin);

        newPin = new JPasswordField();
        newPin.setBounds(200, 120, 150, 25);
        add(newPin);

        confirmPin = new JPasswordField();
        confirmPin.setBounds(200, 160, 150, 25);
        add(confirmPin);

        change = new JButton("Change PIN");
        change.setBounds(70, 220, 120, 30);
        add(change);

        back = new JButton("Back");
        back.setBounds(220, 220, 120, 30);
        add(back);

        change.addActionListener(this);
        back.addActionListener(this);

        setSize(420, 320);
        setLocation(500, 250);
        setUndecorated(true);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == back) {
            setVisible(false);
            new Transactions(pinnumber).setVisible(true);
            return;
        }

        String oldP = oldPin.getText();
        String newP = newPin.getText();
        String confirmP = confirmPin.getText();

        if (oldP.equals("") || newP.equals("") || confirmP.equals("")) {
            JOptionPane.showMessageDialog(null, "All fields are required");
            return;
        }

        if (!newP.equals(confirmP)) {
            JOptionPane.showMessageDialog(null, "New PIN does not match");
            return;
        }

        try {
            Conn c = new Conn();

            // Check old pin
            java.sql.ResultSet rs = c.s.executeQuery(
                "select * from bank where pin = '" + pinnumber + "'"
            );

            if (!rs.next()) {
                JOptionPane.showMessageDialog(null, "Invalid Old PIN");
                return;
            }

            // Update PIN in bank table
            c.s.executeUpdate(
                "update bank set pin = '" + newP + "' where pin = '" + pinnumber + "'"
            );

            JOptionPane.showMessageDialog(null, "PIN Changed Successfully");

            setVisible(false);
            new Transactions(newP).setVisible(true);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ✅ For testing only
    public static void main(String[] args) {
        new ChangePin("");
    }
}

