package main.java;

import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;

public class ClientEx {
    private BufferedReader in;
    private BufferedWriter out;
    private Socket socket;
    private ChatGUI gui;
    private String ip;
    private int port;

    public ClientEx(String ip, int port) {
        this.ip = ip;
        this.port = port;
        gui = new ChatGUI();

        gui.button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });

        gui.textField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });
    }

    private void sendMessage() {
        String outputMessage = gui.textField.getText();
        gui.textField.setText("");
        gui.textArea.append("나: " + outputMessage + "\n");
        try {
            out.write(outputMessage + "\n");
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        try {
            socket = new Socket(ip, port);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        try {
                            String inputMessage = in.readLine();
                            gui.textArea.append("서버: " + inputMessage + "\n");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    class ChatGUI extends JFrame {
        JTextArea textArea;
        JTextField textField;
        JButton button;
        JScrollPane scrollPane;

        public ChatGUI() {
            setSize(400, 400);
            setTitle("Chat");
            setDefaultCloseOperation(EXIT_ON_CLOSE);

            textArea = new JTextArea();
            textArea.setEditable(false);
            DefaultCaret caret = (DefaultCaret) textArea.getCaret();
            caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
            scrollPane = new JScrollPane(textArea);
            add(scrollPane, BorderLayout.CENTER);

            textField = new JTextField();
            button = new JButton("Send");
            JPanel panel = new JPanel();
            panel.setLayout(new BorderLayout());
            panel.add(textField, BorderLayout.CENTER);
            panel.add(button, BorderLayout.EAST);
            add(panel, BorderLayout.SOUTH);

            setVisible(true);
        }
    }

    public static void main(String[] args) {
        new ConnectDialog();
    }

    static class ConnectDialog extends JDialog {
        private JTextField ipField;
        private JTextField portField;
        private JButton connectButton;

        public ConnectDialog() {
            setSize(200, 130);
            setTitle("Connect");
            setModal(true);

            ipField = new JTextField("192.168.0.100");
            portField = new JTextField("6000");
            connectButton = new JButton("Connect");

            connectButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String ip = ipField.getText();
                    int port = Integer.parseInt(portField.getText());
                    new ClientEx(ip, port).run();
                    dispose();
                }
            });

            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(3, 1));
            panel.add(ipField);
            panel.add(portField);
            panel.add(connectButton);
            add(panel);

            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            setVisible(true);
        }
    }
}
