package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener {

    JButton login, signup, clear;
    JTextField cardTextField;
    JPasswordField pinTextField;

    public Login() {

        setTitle("ATM Login");
        setLayout(null);

        // Logo
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/logo.jpg"));
        Image i2 = i1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        JLabel label = new JLabel(new ImageIcon(i2));
        label.setBounds(70, 10, 100, 100);
        add(label);

        JLabel text = new JLabel("Welcome to ATM");
        text.setFont(new Font("Osward", Font.BOLD, 38));
        text.setBounds(200, 40, 400, 40);
        add(text);

        JLabel cardno = new JLabel("Card No:");
        cardno.setFont(new Font("Raleway", Font.BOLD, 28));
        cardno.setBounds(100, 150, 150, 40);
        add(cardno);

        cardTextField = new JTextField();
        cardTextField.setBounds(300, 150, 230, 30);
        add(cardTextField);

        JLabel pin = new JLabel("PIN:");
        pin.setFont(new Font("Raleway", Font.BOLD, 28));
        pin.setBounds(120, 220, 150, 40);
        add(pin);

        pinTextField = new JPasswordField();
        pinTextField.setBounds(300, 220, 230, 30);
        add(pinTextField);

        login = new JButton("SIGN IN");
        login.setBounds(300, 300, 100, 30);
        login.setBackground(Color.black);
        login.setForeground(Color.white);
        login.addActionListener(this);
        add(login);

        clear = new JButton("CLEAR");
        clear.setBounds(430, 300, 100, 30);
        clear.setBackground(Color.black);
        clear.setForeground(Color.white);
        clear.addActionListener(this);
        add(clear);

        signup = new JButton("SIGN UP");
        signup.setBounds(300, 350, 230, 30);
        signup.setBackground(Color.black);
        signup.setForeground(Color.white);
        signup.addActionListener(this);
        add(signup);

        getContentPane().setBackground(Color.white);

        setSize(800, 400);
        setLocation(350, 200);
        setUndecorated(true);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == clear) {
            cardTextField.setText("");
            pinTextField.setText("");

        } else if (e.getSource() == login) {

            String cardnumber = cardTextField.getText().trim();
            String pinnumber = new String(pinTextField.getPassword()).trim();

            if (cardnumber.isEmpty() || pinnumber.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter Card Number and PIN");
                return;
            }

            try {
                Conn conn = new Conn();
                if (conn.c == null) {
                    JOptionPane.showMessageDialog(null, "Database connection failed");
                    return;
                }

                String query = "SELECT * FROM login WHERE cardno=? AND pin=?";
                PreparedStatement ps = conn.c.prepareStatement(query);
                ps.setString(1, cardnumber);
                ps.setString(2, pinnumber);

                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    JOptionPane.showMessageDialog(null, "Login Successful!");
                    setVisible(false);
                    new Transactions(pinnumber).setVisible(true); // Pass PIN to Transactions
                } else {
                    JOptionPane.showMessageDialog(null, "Incorrect Card Number or PIN");
                }

                rs.close();
                ps.close();
                conn.c.close();

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
            }

        } else if (e.getSource() == signup) {
            setVisible(false);
            new SignupOne().setVisible(true); // Make sure SignupOne exists
        }
    }

    public static void main(String[] args) {
        new Login();
    }
}
