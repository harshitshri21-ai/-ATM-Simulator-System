package bank.management.system;

import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.awt.event.*;

public class SignupTwo extends JFrame implements ActionListener {

    long random;
    JComboBox<String> religionBox, categoryBox, incomeBox, educationBox, occupationBox;
    JRadioButton seniorYes, seniorNo, existYes, existNo;
    JTextField panTextField, aadharTextField;
    JButton next;
    private final String formno;

    SignupTwo(String formno) {
        this.formno= formno;
        setLayout(null);

        Random ran = new Random();
        random = Math.abs((ran.nextLong() % 9000L) + 1000L);

        setLayout(null);
        setTitle("NEW ACCOUNT APPLICATION FORM - PAGE 2");

        JLabel additionalDetails = new JLabel("Page 2: ADDITIONAL DETAILS");
        additionalDetails.setFont(new Font("Raleway", Font.BOLD, 26));
        additionalDetails.setBounds(250, 50, 400, 30);
        add(additionalDetails);

        JLabel religion = new JLabel("Religion:");
        religion.setBounds(100, 120, 200, 30);
        religion.setBackground(Color.white);
        add(religion);

        religionBox = new JComboBox<>(new String[]{"Hindu", "Muslim", "Sikh", "Christian", "Others"});
        religionBox.setBounds(300, 120, 400, 30);
        religionBox.setBackground(Color.white);
        add(religionBox);

        JLabel category = new JLabel("Category:");
        category.setBounds(100, 170, 200, 30);
        add(category);

        categoryBox = new JComboBox<>(new String[]{"General", "OBC", "SC", "ST", "Other"});
        categoryBox.setBounds(300, 170, 400, 30);
        add(categoryBox);

        JLabel income = new JLabel("Income:");
        income.setBounds(100, 220, 200, 30);
        add(income);

        incomeBox = new JComboBox<>(new String[]{"Null", "<1,50,000", "<2,50,000", "<5,00,000", "Above 5,00,000"});
        incomeBox.setBounds(300, 220, 400, 30);
        add(incomeBox);

        JLabel education = new JLabel("Education:");
        education.setBounds(100, 270, 200, 30);
        add(education);

        educationBox = new JComboBox<>(new String[]{"Non-Graduate", "Graduate", "Post-Graduate", "Doctorate"});
        educationBox.setBounds(300, 270, 400, 30);
        add(educationBox);

        JLabel occupation = new JLabel("Occupation:");
        occupation.setBounds(100, 320, 200, 30);
        add(occupation);

        occupationBox = new JComboBox<>(new String[]{"Salaried", "Self-Employed", "Business", "Student", "Retired"});
        occupationBox.setBounds(300, 320, 400, 30);
        add(occupationBox);

        JLabel pan = new JLabel("PAN No:");
        pan.setBounds(100, 370, 200, 30);
        add(pan);

        panTextField = new JTextField();
        panTextField.setBounds(300, 370, 400, 30);
        add(panTextField);

        JLabel aadhar = new JLabel("Aadhar No:");
        aadhar.setBounds(100, 420, 200, 30);
        add(aadhar);

        aadharTextField = new JTextField();
        aadharTextField.setBounds(300, 420, 400, 30);
        add(aadharTextField);

        JLabel senior = new JLabel("Senior Citizen:");
        senior.setBounds(100, 470, 200, 30);
        add(senior);

        seniorYes = new JRadioButton("Yes");
        seniorNo = new JRadioButton("No");
        seniorYes.setBounds(300, 470, 100, 30);
        seniorNo.setBounds(450, 470, 100, 30);

        ButtonGroup seniorGroup = new ButtonGroup();
        seniorGroup.add(seniorYes);
        seniorGroup.add(seniorNo);

        add(seniorYes);
        add(seniorNo);

        JLabel exist = new JLabel("Existing Account:");
        exist.setBounds(100, 520, 200, 30);
        add(exist);

        existYes = new JRadioButton("Yes");
        existNo = new JRadioButton("No");
        existYes.setBounds(300, 520, 100, 30);
        existNo.setBounds(450, 520, 100, 30);

        ButtonGroup existGroup = new ButtonGroup();
        existGroup.add(existYes);
        existGroup.add(existNo);

        add(existYes);
        add(existNo);

        next = new JButton("NEXT");
        next.setBounds(620, 580, 80, 30);
        next.addActionListener(this);
        add(next);

        getContentPane().setBackground(Color.white);
        setSize(850, 700);
        setLocation(350, 50);
        setUndecorated(true);
        setVisible(true);
    }

public void actionPerformed(ActionEvent ae) {

    String senior = seniorYes.isSelected() ? "Yes" : "No";
    String existing = existYes.isSelected() ? "Yes" : "No";

    try {
        Conn c = new Conn();
        String query = "insert into signuptwo values('" + random + "','" +
                religionBox.getSelectedItem() + "','" +
                categoryBox.getSelectedItem() + "','" +
                incomeBox.getSelectedItem() + "','" +
                educationBox.getSelectedItem() + "','" +
                occupationBox.getSelectedItem() + "','" +
                panTextField.getText() + "','" +
                aadharTextField.getText() + "','" +
                senior + "','" +
                existing + "')";
        c.s.executeUpdate(query);

        JOptionPane.showMessageDialog(null, "Page 2 Completed Successfully");

        // Hide current page and open SignupThree
        setVisible(false); // hide this page
        new SignupThree(this.formno).setVisible(true); // pass the form number

    } catch (Exception e) {
        e.printStackTrace();
    }
}


    public static void main(String[] args) {
        new SignupTwo("");
    }
}
