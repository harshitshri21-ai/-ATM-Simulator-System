package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class BalanceEnquiry extends JFrame {

    String pinnumber;

    BalanceEnquiry(String pinnumber) {
        this.pinnumber = pinnumber;

        setLayout(null);

        JLabel title = new JLabel("Balance Enquiry");
        title.setFont(new Font("System", Font.BOLD, 18));
        title.setBounds(120, 30, 200, 30);
        add(title);

        JLabel balanceLabel = new JLabel();
        balanceLabel.setFont(new Font("System", Font.BOLD, 16));
        balanceLabel.setBounds(50, 100, 300, 30);
        add(balanceLabel);

        JButton back = new JButton("Back");
        back.setBounds(140, 180, 100, 30);
        add(back);

        int balance = 0;

        try {
            Conn c = new Conn();
            java.sql.ResultSet rs = c.s.executeQuery(
                "select * from bank where pin = '" + pinnumber + "'"
            );

            while (rs.next()) {
                if (rs.getString("type").equals("Deposit")) {
                    balance += Integer.parseInt(rs.getString("amount"));
                } else {
                    balance -= Integer.parseInt(rs.getString("amount"));
                }
            }

            balanceLabel.setText("Your Current Balance is Rs " + balance);

        } catch (Exception e) {
            e.printStackTrace();
        }

        back.addActionListener(e -> {
            setVisible(false);
            new Transactions(pinnumber).setVisible(true);
        });

        setSize(400, 300);
        setLocation(500, 250);
        setUndecorated(true);
        setVisible(true);
    }

    // ✅ MAIN METHOD (FOR TESTING ONLY)
    public static void main(String[] args) {
        new BalanceEnquiry("");
    }
}
