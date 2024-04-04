package main.java;

import javax.swing.*;
import java.awt.*;

public class ThreadTimerEx extends JFrame {
    public ThreadTimerEx(){
        setTitle("Thread를 상속받은 타이머예제");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container c = getContentPane();
        c.setLayout(new FlowLayout());

        JLabel timerLabel = new JLabel();
        timerLabel.setFont(new Font("Gothic",Font.ITALIC,70));
        c.add(timerLabel);

        TimerThread th = new TimerThread(timerLabel);

        setSize(800, 300);
        setVisible(true);

        th.start();
    }

    public static void main(String[] args){
        new ThreadTimerEx();
    }
}
