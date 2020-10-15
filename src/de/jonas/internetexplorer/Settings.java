package de.jonas.internetexplorer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Settings {

    static JCheckBox box1, box2, box3, box4;
    static JTextField Field;

    public Settings() {
        JFrame frame = new JFrame("Einstellungen");
        frame.setBounds(0,0,300,400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLayout(null);

        JCheckBox read = new JCheckBox("Nur lesen");
        read.setBounds(20,30,100,20);
        read.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(box1.isSelected()) {
                    box2.setSelected(false);
                    HTMLRendering.Edit = false;
                }
            }
        });

        JCheckBox write = new JCheckBox("Lesen und schreiben");
        write.setBounds(20,55,150,20);
        write.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(box2.isSelected()) {
                    box1.setSelected(false);
                    HTMLRendering.Edit = true;
                }
            }
        });

        JLabel Strich = new JLabel("_____________________________________", JLabel.CENTER);
        Strich.setBounds(-10,80,300,20);

        JCheckBox offline = new JCheckBox("Aus verzeichnis wählen");
        offline.setBounds(20,125,200,20);
        offline.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(box3.isSelected()) {
                    box4.setSelected(false);
                    HTMLRendering.Offline = true;
                    Field.setEnabled(false);
                    Field.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2, true));
                }
            }
        });

        final JTextField field = new JTextField();
        field.setBounds(15,170,250,25);
        field.setEnabled(false);
        field.setForeground(Color.GRAY);
        field.setText("https://www.example.de");
        field.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2, true));

        JCheckBox online = new JCheckBox("Website wählen");
        online.setBounds(20,145,150,20);
        online.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(box4.isSelected()) {
                    box3.setSelected(false);
                    HTMLRendering.Offline = false;
                    field.setEnabled(true);
                    field.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));
                } else {
                    field.setEnabled(false);
                    field.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2, true));
                }
            }
        });

        JButton button = new JButton("Fertig");
        button.setFont(new Font("Arial", Font.BOLD, 20));
        button.setBounds(10,250,260,30);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(box1.isSelected() || box2.isSelected()) {
                    if(box3.isSelected() || box4.isSelected()) {
                        HTMLRendering.WebAdresse = field.getText();
                        new HTMLRendering();
                        frame.dispose();
                    } else {
                        Waehlen();
                    }
                } else {
                    Waehlen();
                }
            }
        });

        box1 = read;
        box2 = write;
        box3 = offline;
        box4 = online;
        Field = field;

        frame.add(read);
        frame.add(write);
        frame.add(Strich);
        frame.add(offline);
        frame.add(online);
        frame.add(field);
        frame.add(button);
        frame.setVisible(true);
    }

    private static JFrame Waehlen() {
        JFrame frame = new JFrame("Bitte wählen...");
        frame.setBounds(0,0,350,150);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);

        JLabel label = new JLabel("<html>Du musst alles korrekt ausfüllen!</html>", JLabel.CENTER);
        label.setBounds(0,0,330,60);

        JButton button = new JButton("Verstanden");
        button.setBounds(40,65,250,20);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

        frame.add(label);
        frame.add(button);
        frame.setVisible(true);
        return frame;
    }

}
