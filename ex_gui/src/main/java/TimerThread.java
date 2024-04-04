package main.java;

import javax.swing.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class TimerThread extends Thread{
    private JLabel timerLabel;

    public TimerThread(JLabel timerLabel){
        this.timerLabel = timerLabel;
    }

    @Override
    public void run(){
        LocalDateTime now = LocalDateTime.now();
        while (true){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH시 mm분 ss초");
            String formattedNow = now.format(formatter);
            timerLabel.setText(formattedNow);
            now = now.plusSeconds(1);
            try{
                Thread.sleep(1000);
            }catch (InterruptedException e){
                return;
            }
        }
    }
}
