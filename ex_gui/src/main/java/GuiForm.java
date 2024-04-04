package main.java;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GuiForm extends JFrame {

    public GuiForm(){
        setTitle("test");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel topPanel = new JPanel(new BorderLayout());
        JPanel centerPanel = new JPanel(new GridLayout(4, 4, 5, 5));
        JPanel bottomPanel = new JPanel(new BorderLayout());
        topPanel.setMinimumSize(new Dimension(300,40));
        bottomPanel.setMinimumSize(new Dimension(300,40));



        topPanel.setBackground(Color.gray);
        JLabel tlabel = new JLabel("수식입력");
        tlabel.setFont(new Font("돋움",Font.PLAIN,15));
        topPanel.add(tlabel, BorderLayout.WEST);
        topPanel.add(new JTextField(), BorderLayout.CENTER);
        mainPanel.add(topPanel, BorderLayout.NORTH);

        for (int i = 0; i <= 9; i++) {
            centerPanel.add(new JButton(String.valueOf(i)));
        }
        centerPanel.add(new JButton("CE"));

        JButton plus = new JButton("+");
        plus.addActionListener(new MyAction());
        centerPanel.add(plus);
        centerPanel.add(createButton("-", Color.CYAN));
        centerPanel.add(createButton("X", Color.CYAN));
        centerPanel.add(createButton("/", Color.CYAN));
        centerPanel.add(createButton("계산",Color.CYAN));
        mainPanel.add(centerPanel, BorderLayout.CENTER);

        bottomPanel.setBackground(Color.yellow);
        JLabel blabel = new JLabel("계산결과");
        blabel.setFont(new Font("돋움",Font.PLAIN,15));
        bottomPanel.add(blabel, BorderLayout.WEST);
        bottomPanel.add(new JTextField(), BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        getContentPane().add(mainPanel);

        setSize(300,300);
        setVisible(true);
    }

    private JButton createButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setBackground(color);
        return button;
    }

    public static void main(String[] args){
        GuiForm form = new GuiForm();
        System.out.println("test");
    }
    public class MyAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JButton b = (JButton)e.getSource();
            if(b.getText().equals("+"))
                b.setText("플러스");
            else
                b.setText("+");

            GuiForm.this.setTitle(b.getText());
        }
    }
}
