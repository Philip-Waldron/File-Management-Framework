package com.studentportal.ui;

import com.studentportal.Memento.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.lang.String;
import java.util.Arrays;
import java.util.Collections;

public class AnnouncementsUI_teacher extends Ui {

    private JFrame AnnouncementsUI_teacher_frame;

    private JLabel MessageLabel;

    private JTextArea messageField;

    private JButton Cancel;

    private JButton Submit;

    private JButton Undo;

    Originator ori=new Originator();
    MementoConcrete m;
    CareTaker careTaker = new CareTaker();

    public AnnouncementsUI_teacher() {
        AnnouncementsUI_teacher_frame = new JFrame("Announcement");
        AnnouncementsUI_teacher_frame.setSize(400, 200);
        AnnouncementsUI_teacher_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        AnnouncementsUI_teacher_frame.setLocationRelativeTo(null);
        AnnouncementsUI_teacher_frame.setLayout(new GridLayout(2, 3));

        MessageLabel = new JLabel("Message Area: ");
        AnnouncementsUI_teacher_frame.add(MessageLabel);
        messageField = new JTextArea();
        messageField.setLineWrap(true);
        AnnouncementsUI_teacher_frame.add(messageField );

        messageField.addKeyListener(new KeyAdapter(){
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == 32) {
                    String []message=  messageField.getText().split(" ");
                    careTaker.saveMemento(ori.createMemento( new ArrayList<String>(Arrays.asList(message)) ));
                }
            }
        });
        Cancel=new JButton("Cancel");
        Cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hide();
            }
        });
        AnnouncementsUI_teacher_frame.add(Cancel);

        Submit=new JButton("Submit");
        AnnouncementsUI_teacher_frame.add(Submit);
        Submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //save teacher number, announce time,message content to database
            }
        });
        Undo=new JButton("Undo");
        Undo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                m = careTaker.getLastMemento();
                if (m == null) return;
                messageField.setText( ori.Undo(m));

            }
        });
        AnnouncementsUI_teacher_frame.add(Undo);

}

    public  static  void main(String [] args){
        AnnouncementsUI_teacher a=new AnnouncementsUI_teacher();
        a.show();

    }
}
