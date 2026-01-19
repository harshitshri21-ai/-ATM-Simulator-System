package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class MiniStatement extends JFrame {

    String pinnumber;

    MiniStatement(String pinnumber) {
        this.pinnumber = pinnumber;

        setLayout(null);

        JLabel title = new JLabel("Mini Statement");
        title.setFont(new Font("System", Font.BOLD, 18));
        title.setBounds(150, 20, 200, 30);
        add(title);

        JLabel card = new JLabel("PIN : " + pinnumber);
        card.setBounds(20, 60, 300, 20);
        add(card);

        JTextArea area = new JTextArea();
        area.setEditable(false);

        JScrollPane pane = new JScrollPane(area);
        pane.setBounds(20, 90, 360, 250);
        add(pane);

        try {
            Conn c = new Conn();
            java.sql.ResultSet rs = c.s.executeQuery(
                "select * from bank where pin = '" + pinnumber + "'"
            );

            int balance = 0;

            while (rs.next()) {
                area.append(
                    rs.getString("date") + "    " +
                    rs.getString("type") + "    " +
                    rs.getString("amount") + "\n"
                );

                if (rs.getString("type").equals("Deposit")) {
                    balance += Integer.parseInt(rs.getString("amount"));
                } else {
                    balance -= Integer.parseInt(rs.getString("amount"));
                }
            }

            area.append("\n--------------------------");
            area.append("\nCurrent Balance : Rs " + balance);

        } catch (Exception e) {
            e.printStackTrace();
        }

        setSize(400, 400);
        setLocation(500, 200);
        setUndecorated(true);
        setVisible(true);
    }

    // ✅ MAIN METHOD (FOR TESTING)
    public static void main(String[] args) {
        new MiniStatement("");
    }
}
