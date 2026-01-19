package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class FastCash extends JFrame implements ActionListener {

    JButton b1, b2, b3, b4, b5, b6, back;
    String pinnumber;

    FastCash(String pinnumber) {
        this.pinnumber = pinnumber;

        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        JLabel image = new JLabel(new ImageIcon(i2));
        image.setBounds(0, 0, 900, 900);
        add(image);

        JLabel text = new JLabel("SELECT WITHDRAWAL AMOUNT");
        text.setBounds(225, 300, 400, 35);
        text.setForeground(Color.WHITE);
        text.setFont(new Font("System", Font.BOLD, 16));
        image.add(text);

        b1 = new JButton("Rs 100");
        b2 = new JButton("Rs 500");
        b3 = new JButton("Rs 1000");
        b4 = new JButton("Rs 2000");
        b5 = new JButton("Rs 5000");
        b6 = new JButton("Rs 10000");
        back = new JButton("Back");

        b1.setBounds(170, 415, 150, 30);
        b2.setBounds(355, 415, 150, 30);
        b3.setBounds(170, 450, 150, 30);
        b4.setBounds(355, 450, 150, 30);
        b5.setBounds(170, 485, 150, 30);
        b6.setBounds(355, 485, 150, 30);
        back.setBounds(255, 520, 150, 30);

        JButton[] btn = {b1, b2, b3, b4, b5, b6, back};
        for (JButton b : btn) {
            b.addActionListener(this);
            image.add(b);
        }

        setSize(900, 900);
        setLocation(300, 0);
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

        String amount = ((JButton) ae.getSource()).getText().substring(3).trim();
        int withdrawAmount = Integer.parseInt(amount);

        try {
            Conn c = new Conn();

            java.sql.ResultSet rs = c.s.executeQuery(
                    "select * from bank where pin = '" + pinnumber + "'"
            );

            int balance = 0;

            while (rs.next()) {
                if (rs.getString("type").equals("Deposit")) {
                    balance += Integer.parseInt(rs.getString("amount"));
                } else {
                    balance -= Integer.parseInt(rs.getString("amount"));
                }
            }

            if (balance < withdrawAmount) {
                JOptionPane.showMessageDialog(null, "Insufficient Balance");
                return;
            }

            // ✅ FIXED INSERT QUERY (MATCHES TABLE)
            c.s.executeUpdate(
                "insert into bank(pin, date, type, amount) values('" 
                + pinnumber + "', NOW(), 'Withdraw', '" + withdrawAmount + "')"
            );

            JOptionPane.showMessageDialog(null,
                    "Rs " + withdrawAmount + " Debited Successfully");

            setVisible(false);
            new Transactions(pinnumber).setVisible(true);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new FastCash("");
    }
}
